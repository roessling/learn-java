package de.tu_darmstadt.informatik.skorvan.teacher.gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.stream.Collectors;

import de.tu_darmstadt.informatik.skorvan.persistence.Task;
import de.tu_darmstadt.informatik.skorvan.student.gui.StudentGui;
import de.tu_darmstadt.informatik.skorvan.util.FileUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class TeacherGui {

	private static final int OPENTEST = 0;

	private static final int OPENXML = 1;

	public static final int SAVEXML = 2;
	
	public static final int EXPORTZIP = 3;
	
	public static final int IMPORTZIP = 4;
	
	private static final int KEEP_FIRST = 0;

	private static final int KEEP_SECOND = 1;

	private static final int USE_FIRST = 2;

	private static final int USE_SECOND = 3;

	private static final int USE_NONE = 4;

	private TeacherCallback callback;

	private Button[] toolbarButtons;

	private TextInputControl[] input;
	
	private ListView<Task> taskList;
	
	private Stage primaryStage;

	public TeacherGui(TeacherCallback callback) {
		this.callback = callback;
	}

	public void init(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("TeacherTool");

		// TOOLBAR (TOP)
		HBox toolbar = new HBox();
		toolbar.setSpacing(10d);
		toolbarButtons = new Button[8];

		toolbarButtons[0] = new Button("Neu");
		toolbarButtons[0].setOnAction(evt -> {
			if(checkChanged())
				callback.newTask();
		});

		toolbarButtons[1] = new Button("\u00d6ffnen");
		toolbarButtons[1].setOnAction(evt -> showFileDialog(OPENXML));
		
		toolbarButtons[2] = new Button("Importieren");
		toolbarButtons[2].setOnAction(evt -> showFileDialog(IMPORTZIP));

		toolbarButtons[3] = new Button("Speichern");
		toolbarButtons[3].setOnAction(
				evt -> callback.saveTask(Arrays.stream(input).map(i -> i.getText()).toArray(String[]::new)));

		toolbarButtons[4] = new Button("Speichern unter");
		toolbarButtons[4].setOnAction(evt -> showFileDialog(SAVEXML));

		toolbarButtons[5] = new Button("Speichern und Testen");
		toolbarButtons[5].setOnAction(evt -> {
			if (callback.saveTask(Arrays.stream(input).map(i -> i.getText()).toArray(String[]::new)))
				callback.testTask();
		});
		
		toolbarButtons[6] = new Button("Exportieren");
		toolbarButtons[6].setOnAction(evt -> showFileDialog(EXPORTZIP));
		toolbarButtons[6].setDisable(true);
		
		toolbarButtons[7] = new Button("Wissensdatenbank");
		toolbarButtons[7].setOnAction(evt -> KnowledgeBaseEditor.show(null));

		toolbar.getChildren().addAll(toolbarButtons);

		// INPUT AREAS (CENTER)
		input = new TextInputControl[6];

		input[0] = new TextField();
		input[0].setPromptText("Titel");

		input[1] = new TextArea();
		input[1].setPromptText("Beschreibung/Aufgabenstellung");
		((TextArea)input[1]).setWrapText(true);

		input[2] = new TextArea();
		input[2].setPromptText("Code-Pr\u00e4fix");

		input[3] = new TextArea();
		input[3].setPromptText("Code-Suffix");

		input[4] = new TextField();
		input[4].setPromptText("Klassenname");

		input[5] = new TextField();
		input[5].setPromptText("Test-Datei");

		Button testSearch = new Button("Durchsuchen...");
		testSearch.setOnAction(evt -> showFileDialog(OPENTEST));

		HBox test = new HBox();
		test.setSpacing(10d);
		test.getChildren().addAll(input[5], testSearch);
		HBox.setHgrow(input[5], Priority.ALWAYS);
		
		// TASK LIST (LEFT)
		taskList = new ListView<>();
		taskList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		taskList.getSelectionModel().selectedItemProperty().addListener((ov, oldVal, newVal) -> {
			if(newVal != null && checkChanged()) {
				callback.showTask(newVal);
				toolbarButtons[6].setDisable(false);
			}
			else if(newVal == null)
				toolbarButtons[6].setDisable(true);
		});

		// ROOT PANE
		VBox inputs = new VBox();
		inputs.setSpacing(10d);
		VBox.setVgrow(input[1], Priority.ALWAYS);
		VBox.setVgrow(input[2], Priority.ALWAYS);
		VBox.setVgrow(input[3], Priority.ALWAYS);
		inputs.getChildren().addAll(Arrays.copyOf(input, 5));
		inputs.getChildren().add(test);
		
		HBox inputAndList = new HBox();
		inputAndList.setSpacing(10d);
		HBox.setHgrow(inputs, Priority.ALWAYS);
		inputAndList.getChildren().addAll(taskList, inputs);
		
		VBox root = new VBox();
		root.setPadding(new Insets(10d));
		root.setSpacing(10d);
		root.getChildren().addAll(toolbar, inputAndList);

		Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
		scene.getStylesheets().add(StudentGui.class.getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void addTasks(Task[] tasks) {
		taskList.getItems().addAll(tasks);
		checkForDuplicates();
	}
	
	private void checkForDuplicates() {
		taskList.getItems().sort((task1, task2) -> task1.getId().compareTo(task2.getId()));
		ListIterator<Task> it = taskList.getItems().listIterator(taskList.getItems().size());
		while(it.hasPrevious()) {
			Task t1 = it.previous();		//t1 is first, but to the end of the iterator
			if(!it.hasPrevious())
				return;
			Task t2 = it.previous();		//t2 is second, but to the start of the iterator
			if(t1.getId().equals(t2.getId())) {
				if(t1.getLocation() != null && t1.getLocation().equals(t2.getLocation())) {
					it.remove();
					it.next();
					continue;
				}
				int result = showDuplicate(t1, t2);
				Task toRemove = null;
				if(result == KEEP_FIRST) {
					it.remove();
					it.next();
					toRemove = t2;
				} else if(result == KEEP_SECOND) {
					it.next();
					it.next();
					it.remove();
					toRemove = t1;
				} else if(result == USE_FIRST) {
					it.remove();
					it.next();
				} else if(result == USE_SECOND) {
					it.next();
					it.next();
					it.remove();
				} else if(result == USE_NONE) {
					it.remove();
					it.next();
					it.remove();
				}
				if(toRemove != null)
					try {
						Files.delete(toRemove.getLocation());
						Files.delete(toRemove.getTestFilePath());
						FileUtils.cleanUp(toRemove.getLocation().getParent());
					} catch (IOException e) {
						e.printStackTrace();
					}
			} else
				it.next();
		}
	}
	
	private int showDuplicate(Task task1, Task task2) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Duplikat in den Aufgaben gefunden");
		alert.getDialogPane().setHeaderText(null);
		alert.setContentText(
				"Es wurde ein Duplikat in den geladenen Aufgaben gefunden. Bitte w\u00e4hlen Sie eine Aktion aus.\n"
						+ "Aufgabe (1): " + task1.getTitle() + (task1.getLocation() == null ? "\taus Importierung (tempor\u00e4r)" : "\tin Datei " + task1.getLocation().toString()) + "\n"
						+ "Aufgabe (2): " + task2.getTitle() + (task2.getLocation() == null ? "\taus Importierung (tempor\u00e4r)" : "\tin Datei " + task2.getLocation().toString()));
		ButtonType keepSecond = new ButtonType("(1) l\u00f6schen");
		ButtonType keepFirst = new ButtonType("(2) l\u00f6schen");
		ButtonType useFirst = new ButtonType(String.valueOf("(1) laden"));
		ButtonType useSecond = new ButtonType(String.valueOf("(2) laden"));
		ButtonType useNone = new ButtonType(String.valueOf("keins laden"));

		alert.getButtonTypes().clear();
		if(task1.getLocation() != null)
			alert.getButtonTypes().add(keepSecond);
		if(task2.getLocation() != null)
			alert.getButtonTypes().add(keepFirst);
		alert.getButtonTypes().addAll(useFirst, useSecond, useNone);

		Optional<ButtonType> bt = alert.showAndWait();
		if (bt.isPresent()) {
			if (bt.get() == keepFirst)
				return KEEP_FIRST;
			if (bt.get() == keepSecond)
				return KEEP_SECOND;
			if (bt.get() == useFirst)
				return USE_FIRST;
			if (bt.get() == useSecond)
				return USE_SECOND;
			if (bt.get() == useNone)
				return USE_NONE;
		}
		return USE_SECOND;
	}

	public void showTask(Task task) {
		primaryStage.setTitle("Task ID: " + task.getId());
		input[0].setText(task.getTitle());
		input[1].setText(task.getDescription());
		input[2].setText(task.getCodePrefix());
		input[3].setText(task.getCodeSuffix());
		input[4].setText(task.getClassName());
		input[5].setText(task.getTestFile());
	}

	public void showLoadError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Fehler beim Laden");
		alert.setContentText("Die gew\u00e4hlte Aufgabe konnte nicht geladen werden.");
		alert.showAndWait();
	}

	public boolean checkChanged() {
		boolean hasChanged = callback
				.checkChanged(Arrays.stream(input).map(tip -> tip.getText()).toArray(String[]::new));
		if (hasChanged) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("\u00c4nderungen speichern?");
			alert.setContentText("M\u00f6chten Sie zuerst die \u00c4nderungen speichern?");
			alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().equals(ButtonType.YES)) {
				return callback.saveTask(Arrays.stream(input).map(i -> i.getText()).toArray(String[]::new));
			}
			if (result.get().equals(ButtonType.NO))
				return true;
			if (result.get().equals(ButtonType.CANCEL))
				return false;
		}
		return true;
	}

	public boolean showFileDialog(int type) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(Paths.get("").toAbsolutePath().toFile());
		fc.getExtensionFilters().clear();
		if (type == OPENTEST) {
			fc.setTitle("Testdatei \u00f6ffnen f\u00fcr Aufgabe " + input[0].getText());
			fc.getExtensionFilters().add(new ExtensionFilter("Java Source Files (*.java)", "*.java"));
			File result = fc.showOpenDialog(primaryStage);
			if (result != null) {
				input[5].setText(result.getAbsolutePath().toString());
				return true;
			}
			return false;
		} 
		else if (type == OPENXML) {
			fc.setTitle("Aufgabe(n) \u00f6ffnen");
			fc.getExtensionFilters().add(new ExtensionFilter("Aufgaben (*.xml)", "*.xml"));
			List<File> result = fc.showOpenMultipleDialog(primaryStage);
			if (result != null) {
				return callback.openTasks(result.stream().map(f -> f.toPath()).collect(Collectors.toList()), false);
			}
			return false;
		} else if (type == SAVEXML) {
			fc.setTitle("Aufgabe speichern");
			fc.getExtensionFilters().add(new ExtensionFilter("Aufgaben (*.xml)", "*.xml"));
			File result = fc.showSaveDialog(primaryStage);
			if (result != null) {
				return callback.saveTask(Arrays.stream(input).map(i -> i.getText()).toArray(String[]::new),
						result.toPath());
			}
			return false;
		} else if(type == IMPORTZIP) {
			fc.setTitle("Aufgaben importieren");
			fc.getExtensionFilters().add(new ExtensionFilter("gepackte Aufgaben (*.zip)", "*.zip"));
			List<File> result = fc.showOpenMultipleDialog(primaryStage);
			if(result != null) {
				return callback.importTasks(result);
			}
			return false;
		} else if(type == EXPORTZIP) {
			fc.setTitle("Exportieren");
			fc.getExtensionFilters().add(new ExtensionFilter("gepackte Aufgaben (*.zip)", "*.zip"));
			File result = fc.showSaveDialog(primaryStage);
			System.out.println(result.getName());
			if(result != null) {
				return callback.exportTasks(taskList.getSelectionModel().getSelectedItems().toArray(new Task[0]), result);
			}
			return false;
		} else
			throw new IllegalArgumentException("Unknown dialog type: " + type);
	}

	public void replaceTask(Task oldTask, Task newTask) {
		if(taskList.getItems().contains(oldTask))
			taskList.getItems().set(taskList.getItems().indexOf(oldTask), newTask);
		else
			taskList.getItems().add(newTask);
		taskList.getSelectionModel().select(newTask);
	}

	public void showTestFileError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.getDialogPane().setHeader(null);
		alert.setContentText("Die Testdatei konnte nicht gefunden werden");
		alert.setTitle("Fehler beim Speichern");
		alert.showAndWait();
	}

}