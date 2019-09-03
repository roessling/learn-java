package de.tu_darmstadt.informatik.skorvan.teacher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashSet;
import java.util.List;

import de.tu_darmstadt.informatik.skorvan.persistence.Crypto;
import de.tu_darmstadt.informatik.skorvan.persistence.Task;
import de.tu_darmstadt.informatik.skorvan.persistence.Zipper;
import de.tu_darmstadt.informatik.skorvan.student.StudentMain;
import de.tu_darmstadt.informatik.skorvan.teacher.gui.KnowledgeBaseEditor;
import de.tu_darmstadt.informatik.skorvan.teacher.gui.TeacherCallback;
import de.tu_darmstadt.informatik.skorvan.teacher.gui.TeacherGui;
import javafx.application.Application;
import javafx.stage.Stage;

public class TeacherMain extends Application implements TeacherCallback {

	private Task currentTask;

	private TeacherGui gui;

	@Override
	public void start(Stage primaryStage) throws Exception {
		gui = new TeacherGui(this);
		gui.init(primaryStage);

		KnowledgeBaseEditor.init(new Stage());
		
		if(!TeacherConfig.load() || TeacherConfig.getNextId() == null) {
			TeacherConfig.reset();
			TeacherConfig.setNextId("1");
			TeacherConfig.save();
		}
		
		newTask();
	}

	@Override
	public void newTask() {
		Task.TaskFactory factory = new Task.TaskFactory();
		factory.setId(TeacherConfig.getNextId());
		currentTask = factory.create();
		gui.showTask(currentTask);
	}

	private void advanceId() {
		int nextId = Integer.valueOf(TeacherConfig.getNextId());
		TeacherConfig.setNextId(String.valueOf(nextId + 1));
		TeacherConfig.save();
	}

	@Override
	public boolean openTasks(List<Path> taskFiles, boolean temp) {
		Task[] tasks = taskFiles.stream().map(p -> Task.load(p, temp)).toArray(Task[]::new);
		if (tasks != null) {
			gui.addTasks(tasks);
			return true;
		} else {
			gui.showLoadError();
			return false;
		}
	}

	// data must not contain empty test file (@5)
	@Override
	public boolean saveTask(String[] data, Path targetLocation) {
		Task.TaskFactory factory = new Task.TaskFactory();
		factory.setId(currentTask.getId());
		if(currentTask.getId().equals(TeacherConfig.getNextId()))
			advanceId();
		factory.setTitle(data[0]);
		factory.setDescription(data[1]);
		factory.setCodePrefix(data[2]);
		factory.setCodeSuffix(data[3]);
		factory.setClassName(data[4]);
		if (data[5] != null) {
			Path sourceTestFile;
			if (data[5].contains(File.separator))
				sourceTestFile = Paths.get(data[5]).toAbsolutePath();
			else if(currentTask.getLocation() != null)
				sourceTestFile = currentTask.getLocation().resolveSibling(data[5]);
			else {
				gui.showTestFileError();
				return false;
			}
			String targetTestFileName = sourceTestFile.getFileName().toString().replace(".java", "");
			if (sourceTestFile.getFileName().toString().endsWith(".java")) {			// chosen file is not encrypted -> encrypt to target location
				System.out.println("encrypting");
				Crypto.encrypt(sourceTestFile.toFile(), targetLocation.resolveSibling(targetTestFileName).toFile());
			} else if(!sourceTestFile.getParent().equals(targetLocation.getParent())) {		// file is encrypted, but location is different -> copy only
				System.out.println("copying");
				try {
					Files.copy(sourceTestFile, targetLocation.resolveSibling(targetTestFileName),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			} else {
				System.out.println("keeping");
			}
			factory.setTestFile(targetTestFileName);
		}
		factory.setLocation(targetLocation);
		Task oldTask = currentTask;
		currentTask = factory.create();
		gui.showTask(currentTask);
		gui.replaceTask(oldTask, currentTask);
		if (!Task.save(currentTask))
			return false;
		return true;
	}

	@Override
	public boolean saveTask(String[] data) {
		if (currentTask.getLocation() == null)
			return gui.showFileDialog(TeacherGui.SAVEXML);
		return saveTask(data, currentTask.getLocation());
	}

	@Override
	public void testTask() {
		StudentMain main = new StudentMain();
		main.setTaskLocation(currentTask.getLocation().toAbsolutePath());
		Stage stage = new Stage();
		main.start(stage);
	}

	@Override
	public boolean checkChanged(String[] data) {
		return !(eq(data[0], currentTask.getTitle()) && eq(data[1], currentTask.getDescription())
				&& eq(data[2], currentTask.getCodePrefix()) && eq(data[3], currentTask.getCodeSuffix())
				&& eq(data[4], currentTask.getClassName()) && eq(data[5], currentTask.getTestFile()));
	}

	private boolean eq(String a, String b) {
		if (a == null)
			return b == null || b.equals("");
		if (b == null)
			return a.equals("");
		return a.equals(b);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public boolean exportTasks(Task[] tasks, File zipLocation) {
		LinkedHashSet<String> names = new LinkedHashSet<>();
		LinkedHashSet<Path> data = new LinkedHashSet<>();
		for (Task t : tasks) {
			names.add(t.getLocation().getParent().getFileName().toString() + "/"); // ZIP
																					// requires
																					// /
																					// at
																					// the
																					// end
																					// for
																					// folders
			data.add(t.getLocation().getParent());

			names.add(t.getLocation().getParent().getFileName().resolve(t.getLocation().getFileName()).toString());
			data.add(t.getLocation());

			names.add(t.getTestFilePath().getParent().getFileName().resolve(t.getTestFilePath().getFileName())
					.toString());
			data.add(t.getTestFilePath());
		}
		return Zipper.zip(zipLocation, names, data);
	}

	@Override
	public void showTask(Task task) {
		currentTask = task;
		gui.showTask(task);
	}

	@Override
	public boolean importTasks(List<File> zips) {
		try {
			Path temp = Files.createTempDirectory("importedTasks");
			zips.forEach(p -> Zipper.unzip(p, temp));
			Task[] tasks = Task.loadAll(temp, true);
			if (tasks != null) {
				gui.addTasks(tasks);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
