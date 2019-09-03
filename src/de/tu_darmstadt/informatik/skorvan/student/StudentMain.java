package de.tu_darmstadt.informatik.skorvan.student;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.reactfx.util.Tuple2;

import de.tu_darmstadt.informatik.skorvan.compile.Compiler;
import de.tu_darmstadt.informatik.skorvan.compile.Precompiler;
import de.tu_darmstadt.informatik.skorvan.persistence.Crypto;
import de.tu_darmstadt.informatik.skorvan.persistence.FileUtility;
import de.tu_darmstadt.informatik.skorvan.persistence.HistoryItem;
import de.tu_darmstadt.informatik.skorvan.persistence.Task;
import de.tu_darmstadt.informatik.skorvan.persistence.Zipper;
import de.tu_darmstadt.informatik.skorvan.student.gui.Help;
import de.tu_darmstadt.informatik.skorvan.student.gui.HistoryGui;
import de.tu_darmstadt.informatik.skorvan.student.gui.KnowledgeBase;
import de.tu_darmstadt.informatik.skorvan.student.gui.ShowTestResultGui;
import de.tu_darmstadt.informatik.skorvan.student.gui.StudentCallback;
import de.tu_darmstadt.informatik.skorvan.student.gui.StudentGui;
import de.tu_darmstadt.informatik.skorvan.util.FileUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StudentMain extends Application implements StudentCallback {

	private Task currentTask;

	private StudentGui gui;

	private Path taskLocation;

	private static final Path TASKPATH = Paths.get("tasks");

	@Override
	public void start(Stage primaryStage) {
		gui = new StudentGui(this);
		gui.init(primaryStage);
//		primaryStage.getIcons().add(new Image(StudentMain.class.getResourceAsStream("../images/yes.png")));
				
		KnowledgeBase.init(new Stage());

		Help.init(new Stage(), this);
		HistoryGui.init(new Stage());
		ShowTestResultGui.start(new Stage());

		if (taskLocation == null) {
			loadTasks();
		} else {
			Task task = Task.load(taskLocation, false);
			gui.setAvailableTasks(new Task[] { task });
			gui.showTask(task);
			currentTask = task;
		}

		if (!Config.load() || Config.getJDKPath() == null) {
			Config.reset();
			gui.showConfigError();
			
		}
		System.out.println(System.getProperty("java.version") + "%%%%%version");
		if (System.getProperty("java.version").startsWith("1.8")) {
			System.out.println("setting java home to " + Config.getJDKPath());
			System.setProperty("java.home", Config.getJDKPath() + File.separator + "jre");
			System.out.println("java.home "+ Config.getJDKPath() + File.separator + "jre");
		}
	}

	private void loadTasks() {
		ArrayList<Task> tasks = new ArrayList<>(Arrays.asList(Task.loadAll(TASKPATH, false)));
		for (int i = tasks.size() - 1; i >= 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				if (tasks.get(i).getId().equals(tasks.get(j).getId())) {
					int result = gui.showDuplicate(tasks.get(i), tasks.get(j));
					if (result == StudentGui.EXIT) {
						Platform.exit();
						return;
					} else if (result == StudentGui.KEEP_FIRST) {
						try {
							Files.delete(tasks.get(j).getLocation());
							Files.delete(tasks.get(j).getTestFilePath());
						} catch (IOException e) {
							e.printStackTrace();
						}
						tasks.remove(j);
						break;
					} else if (result == StudentGui.KEEP_SECOND) {
						try {
							Files.delete(tasks.get(i).getLocation());
							Files.delete(tasks.get(i).getTestFilePath());
						} catch (IOException e) {
							e.printStackTrace();
						}
						tasks.remove(i);
					} else if (result == StudentGui.USE_FIRST) {
						tasks.remove(j);
					} else if (result == StudentGui.USE_SECOND) {
						tasks.remove(i);
						break;
					} else if (result == StudentGui.USE_NONE) {
						tasks.remove(i);
						tasks.remove(j);
						if (i - 1 == j) // removed the tasks i and i-1 ->
										// continue with i-2
							i--;
						break;
					}
				}
			}
		}
		gui.setAvailableTasks(tasks.toArray(new Task[0]));
		FileUtils.cleanUp(TASKPATH);
		currentTask = null;
	}

	public void setTaskLocation(Path taskLocation) {
		this.taskLocation = taskLocation;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void loadTask(Task task) {
		if (currentTask != null) {
			currentTask.setUserCode(gui.getCodeText());
			gui.setTaskTitle(currentTask.toString()); // TODO added to set task title right
		}
		currentTask = task;
		gui.showTask(task);
	}

	@Override
	public void save() {
		if (currentTask != null) {
			currentTask.setUserCode(gui.getCodeText());
			Task.save(currentTask);
		}
	}

	@Override
	public void run() {
		System.out.println("running");
		if (currentTask != null) {
			gui.clearConsole();
			String className = currentTask.getClassName();
			Tuple2<Integer, Integer> offset = currentTask.writeToJavaFile(gui.getCodeText());

			int lines = gui.getCodeText().split("\n").length;
			ErrorMessageWriter emw = new ErrorMessageWriter(out -> gui.addToCompiler(out + "\n"),
					out -> gui.addToPrecompiler(out + "\n"), gui, currentTask.getCodePrefix().split("\n").length
							+ 1 /* +1 for package info */,
					lines, gui.getCodeText().split("\n")[lines - 1].length());

			Precompiler.checkForErrors(gui.getCodeText(),
					currentTask.getLocation().resolveSibling(className + ".java").toString(), emw);

			if (Config.getJUnitPath() == null)
				gui.showConfigError();

			System.out.println("still running");

			Compiler.setClasspath(currentTask.getLocation().getParent().getParent().toString()
					+ System.getProperty("path.separator") + Config.getJUnitPath());
			String [] tmpStatsInfo = new String [4];
			tmpStatsInfo[0]= currentTask.getTitle(); // Save the title for the statistic table
			tmpStatsInfo[1]= 1+""; //Run: if the method run, save 1 
			if (Compiler.compile(currentTask.getLocation().resolveSibling(className + ".java").toFile(), emw,
					offset.get2(), offset.get1())) {
				// decipher test file to .java
				Crypto.decrypt(currentTask.getLocation().resolveSibling(currentTask.getTestFile()).toFile(),
						currentTask.getLocation().resolveSibling(currentTask.getTestFile() + ".java").toFile());
				boolean isTestSuccessful = TestRunner.runTests(new TestResultWriter(out -> gui.addToTests(out + "\n")),
						currentTask.getLocation().resolveSibling(currentTask.getTestFile() + ".java"),
						currentTask.getClassName(), offset.get2());
				// remove deciphered test file
				// Get data for statistic table

				tmpStatsInfo[2]=1+""; // Compiled number
				if(isTestSuccessful)
					tmpStatsInfo[3]=0+""; // Should be the fehler anzahl
				else
					tmpStatsInfo[3]=1+"";
				ShowTestResultGui.Show();
			} else{
				tmpStatsInfo[2]=0+""; // Compiled number
				tmpStatsInfo[3]=1+"";  // Should be the fehler anzahl
			}
			System.out.println("data for statistic: "+Arrays.toString(tmpStatsInfo));
			UpdateAndAddToStatsTable(tmpStatsInfo);
			currentTask.clean();
		} else {
			System.out.println("current task is null");
		}
	}
	public void UpdateAndAddToStatsTable(String [] newData){
		//1 check if functionName is already in the list or file
//		boolean isExist = HistoryGui.dataTestsOutput.stream()
//		.anyMatch(t-> t.getFunctionName().equals(newData[0]));
		
		HistoryItem tmp = HistoryGui.dataTestsOutput.stream()
				.filter(t->newData[0].equals(t.getFunctionName()))
				.findAny()
				.orElse(null);
		if(tmp !=null){
			int r = Integer.parseInt(tmp.getRuns())+ Integer.parseInt(newData[1]);
			int c = Integer.parseInt(tmp.getCompiled()) + Integer.parseInt(newData[2]);
			int f = Integer.parseInt(tmp.getFailed()) + Integer.parseInt(newData[3]);
			System.out.println("c"+ c+" r"+r+" f"+f);
			HistoryItem updatedItem = new HistoryItem(tmp.getFunctionName(),r+"",c+"",f+"");
			updatedItem.toString();
			int id = HistoryGui.dataTestsOutput.indexOf(tmp);
			if(id!= -1)
				HistoryGui.dataTestsOutput.set(id, updatedItem);
			
		} else{
			HistoryItem newItem = new HistoryItem(
					newData[0],newData[1],newData[2],newData[3]);

			HistoryGui.dataTestsOutput.add(newItem);
			String tmpLine = FileUtility.convertHItemInLine(newItem);
			
		}
	}

	@Override
	public void importTasks(List<File> zips) {
		zips.forEach(p -> Zipper.unzip(p, TASKPATH));
		loadTasks();
	}

	@Override
	public void reset() {
		gui.clearCodeArea();
		currentTask.setUserCode(null);
	}
}