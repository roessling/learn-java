package de.tu_darmstadt.informatik.skorvan.student.gui;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.StyledTextArea;
import org.reactfx.util.Tuple2;
import org.reactfx.util.Tuples;

import de.tu_darmstadt.informatik.skorvan.exercises.UnitResultItem;
import de.tu_darmstadt.informatik.skorvan.persistence.Task;
import de.tu_darmstadt.informatik.skorvan.student.Config;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import javafx.stage.Stage;

public class StudentGui implements ErrorMarker {

	public static final int EXIT = -1;

	public static final int KEEP_FIRST = 0;

	public static final int KEEP_SECOND = 1;

	public static final int USE_FIRST = 2;

	public static final int USE_SECOND = 3;

	public static final int USE_NONE = 4;
	public static final int WIDTH = 100;

	private static Pattern linkPattern;

	private static Map<String, String> linkMap;

	private StudentCallback callback;

	private Button[] toolbarButtons;

	private VBox availableTasksVbox;

	private HighlightedCodeArea codeArea;

	private StyledTextArea<LinkStyleDef, LinkStyleDef> currentTaskLabel;
	private StyledTextArea<LinkStyleDef, LinkStyleDef> precompilerOutput;
	private StyledTextArea<LinkStyleDef, LinkStyleDef> compilerOutput;
	private StyledTextArea<LinkStyleDef, LinkStyleDef> testsOutput;
	private StyledTextArea<LinkStyleDef, LinkStyleDef> currentTaskTitle;
	
	private ArrayList<Tuple2<Integer, Integer>> errors;
	private ArrayList<Tuple2<Integer, Integer>> warnings;

	private Stage stage;
	public static ObservableList<UnitResultItem> dataTestsOutput =
            FXCollections.observableArrayList();
	TreeItem<Object> rootNode = new TreeItem<>("\u00dcbungen");

	public StudentGui(StudentCallback callback) {
		this.callback = callback;
	}

	public void init(Stage primaryStage) {
		primaryStage.setTitle("Aufgaben f\u00fcr den Einstieg in Java");

		// TOOLBAR (TOP)
		HBox toolbar = new HBox();
		toolbar.setPadding(new Insets(10d));
		toolbar.setSpacing(10d);
		toolbarButtons = new Button[8];

		toolbarButtons[0] = new Button("Speichern");
		toolbarButtons[0].setOnAction(evt -> callback.save());

		toolbarButtons[1] = new Button("Ausf\u00fchren");
		toolbarButtons[1].setOnAction(evt -> callback.run());

		toolbarButtons[2] = new Button("Zur\u00fccksetzen");
		toolbarButtons[2].setOnAction(evt -> callback.reset());

		toolbarButtons[3] = new Button("Importieren");
		toolbarButtons[3].setOnAction(evt -> importTasks());

		toolbarButtons[4] = new Button("Konfiguration");
		toolbarButtons[4].setOnAction(evt -> config());

		toolbarButtons[5] = new Button("Wissensdatenbank");
		toolbarButtons[5].setOnAction(evt -> KnowledgeBase.show("Start"));

		toolbarButtons[6] = new Button("Hilfe");
		toolbarButtons[6].setOnAction(evt -> Help.show());
		
		toolbarButtons[7] = new Button("Statistik");
		toolbarButtons[7].setOnAction(evt-> HistoryGui.show());

		Arrays.stream(toolbarButtons).forEach(b -> {
			b.setPrefWidth(120d);
		});
		toolbarButtons[0].setDisable(true);
		toolbarButtons[1].setDisable(true);
		toolbarButtons[2].setDisable(true);
		toolbar.getChildren().addAll(toolbarButtons);

		// AVAILABLE TASKS (LEFT)
		availableTasksVbox = new VBox();
		availableTasksVbox.setPadding(new Insets(0d, 20d, 0d, 10d));
		availableTasksVbox.setSpacing(10d);
		
		availableTasksVbox.getChildren().add(
				new Label("Es konnten keine Aufgaben geladen werden. Bitte den Ordner tasks \u00fcberpr\u00fcfen!"));
		ScrollPane tasks = new ScrollPane(availableTasksVbox);
		tasks.setHbarPolicy(ScrollBarPolicy.NEVER);
		tasks.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

		// CODE AREA (CENTER)
		codeArea = new HighlightedCodeArea();
		codeArea.setEditable(false);
		VirtualizedScrollPane<CodeArea> codePane = new VirtualizedScrollPane<>(codeArea);

		// CURRENT TASK (RIGHT)
//		VBox selectedTasknTitleVbox = new VBox();
//		selectedTasknTitleVbox.setSpacing(10d);
		GridPane gridpaneRight = new GridPane();
		gridpaneRight.setVgap(10d);
		
		// The title label of the task selected
		currentTaskTitle = new StyledTextArea<LinkStyleDef, LinkStyleDef>(new LinkStyleDef(), (a, b) -> {
		}, new LinkStyleDef(), (text, style) -> style.applyToText(text));
		currentTaskTitle.setEditable(false);
		currentTaskTitle.setPadding(new Insets(10));
		
		currentTaskTitle.setPrefWidth(280d);
		currentTaskTitle.setMinHeight(40d);
		
		// the description block of the task selected
		currentTaskLabel = new StyledTextArea<LinkStyleDef, LinkStyleDef>(new LinkStyleDef(), (a, b) -> {
		}, new LinkStyleDef(), (text, style) -> style.applyToText(text));
		currentTaskLabel.setEditable(false);
		currentTaskLabel.setPadding(new Insets(20));
		currentTaskLabel.setWrapText(true);
		currentTaskLabel.setPrefWidth(300d);
		currentTaskLabel.setPrefHeight(Integer.MAX_VALUE);
		
		currentTaskLabel.insertText(0, "Auf der linken Seite eine Aufgabe ausw\u00e4hlen, um zu beginnen.");
		VirtualizedScrollPane<?> currentTaskPane = new VirtualizedScrollPane<>(currentTaskLabel);
		currentTaskPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		currentTaskPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		
		gridpaneRight.setFillHeight(currentTaskPane, true);
		gridpaneRight.setVgrow(currentTaskTitle, Priority.SOMETIMES);
		
//		selectedTasknTitleVbox.getChildren().add(currentTaskTitle);
//		selectedTasknTitleVbox.getChildren().add(currentTaskPane);
		gridpaneRight.add(currentTaskTitle, 0, 0);
		gridpaneRight.add(currentTaskPane, 0, 1);
		
		// TABLEVIEW  for the test in a new scene or fenster
		TableView<UnitResultItem> tableResults = new TableView<>();
		tableResults.setEditable(false);
		TableColumn<UnitResultItem, String> firstCol = new TableColumn<UnitResultItem, String>("Erwartet");
		firstCol.setMinWidth(150);
		firstCol.setCellValueFactory(
                new PropertyValueFactory< UnitResultItem, String>("expected"));
		TableColumn<UnitResultItem, String> secondCol = new TableColumn<UnitResultItem, String>("Dein Ergebnis");
		secondCol.setMinWidth(150);
		secondCol.setCellValueFactory(
                new PropertyValueFactory<UnitResultItem, String>("actually"));
		TableColumn thirdCol = new TableColumn("Bewertung");
		thirdCol.setMinWidth(150);
		thirdCol.setCellValueFactory(
                new PropertyValueFactory<UnitResultItem, String>("result"));
		thirdCol.setCellFactory(new Callback<TableColumn, TableCell>() {

			@Override
			public TableCell<UnitResultItem, String> call(TableColumn param) {
				// TODO Auto-generated method stub
				return new TableCell<UnitResultItem, String>(){
					@Override
					public void updateItem(String item, boolean empty){
						super.updateItem(item, empty);
						if(!isEmpty()){
							this.setTextFill(Color.GREEN);
							if(item.contains("false")) 
	                            this.setTextFill(Color.RED);
							else if(item.contains("true"))
								this.setTextFill(Color.GREEN);
	                        setText(item);
						};
					}
				};
			}
		});
		
		tableResults.getColumns().addAll(firstCol,secondCol,thirdCol);
		
		// CONSOLE OUTPUT (BOTTOM)
		TabPane console = new TabPane();
		console.setPadding(new Insets(10));
		console.setPrefHeight(200d);
		console.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab precompilerTab = new Tab("Precompiler");
		precompilerOutput = new StyledTextArea<LinkStyleDef, LinkStyleDef>(new LinkStyleDef(), (a, b) -> {
		}, new LinkStyleDef(), (text, style) -> style.applyToText(text));
		precompilerOutput.setEditable(false);
		precompilerOutput.richChanges().subscribe((e) -> console.getSelectionModel().select(precompilerTab));
		VirtualizedScrollPane<?> p1 = new VirtualizedScrollPane<>(precompilerOutput);
		precompilerTab.setContent(p1);

		Tab compilerTab = new Tab("Compiler");
		compilerOutput = new StyledTextArea<LinkStyleDef, LinkStyleDef>(new LinkStyleDef(), (a, b) -> {
		}, new LinkStyleDef(), (text, style) -> style.applyToText(text));
		compilerOutput.setEditable(false);
		compilerOutput.richChanges().subscribe((e) -> console.getSelectionModel().select(compilerTab));
		VirtualizedScrollPane<?> p2 = new VirtualizedScrollPane<>(compilerOutput);
		compilerTab.setContent(p2);

		Tab testsTab = new Tab("Tests");
		testsOutput = new StyledTextArea<LinkStyleDef, LinkStyleDef>(new LinkStyleDef(), (a, b) -> {
		}, new LinkStyleDef(), (text, style) -> style.applyToText(text));
		testsOutput.setEditable(false);
		testsOutput.richChanges().subscribe((e) -> console.getSelectionModel().select(testsTab));
		VirtualizedScrollPane<?> p3 = new VirtualizedScrollPane<>(testsOutput);
		testsTab.setContent(p3);
		
		// Tests Replay In Table
		Tab testsDisplayTab = new Tab("Ergebnis");
		tableResults.setItems(dataTestsOutput);
		testsDisplayTab.setContent(tableResults);
		
		console.getTabs().addAll(precompilerTab, compilerTab, testsTab, testsDisplayTab);

		// ROOT PANE
		BorderPane root = new BorderPane();
		root.setTop(toolbar);
		root.setLeft(tasks);
		root.setCenter(codePane);
		root.setRight(gridpaneRight);
		root.setBottom(console);
		root.getStyleClass().add("borderpane");
		BorderPane.setMargin(gridpaneRight, new Insets(10d));
		BorderPane.setMargin(codePane, new Insets(10d));
		BorderPane.setMargin(tasks, new Insets(10d));
		root.getLeft().prefWidth(WIDTH);

		Scene scene = new Scene(root, 1400, 800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("java-keywords.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		this.stage = primaryStage;
	}
	public void setTaskTitle(String title){
		currentTaskTitle.replaceText( title);
	}
	public void setAvailableTasks(Task[] availableTasks) {
		if (availableTasks != null && availableTasks.length > 0) {
			Arrays.sort(availableTasks);
			availableTasksVbox.getChildren().clear();
			buildTreeViewOfTask(availableTasks);
//			Button[] taskButtons = new Button[availableTasks.length];
//			for (int i = 0; i < availableTasks.length; i++) {
//				final int j = i;
//				System.out.println(getLastDirectoryName(availableTasks[i].getLocation().getParent()));
//				taskButtons[i] = new Button(availableTasks[i].getTitle());
//				taskButtons[i].setOnAction(evt -> callback.loadTask(availableTasks[j]));
//				taskButtons[i].setMaxWidth(Double.MAX_VALUE);
//				taskButtons[i].setStyle("-fx-padding: 10px; -fx-border-insets: 0px; -fx-background-insets: 0px;");
//				// taskButtons[i].setAlignment(Pos.CENTER_RIGHT); TODO where?
//			}
////			availableTasksVbox.getChildren().clear();
//			availableTasksVbox.getChildren().addAll(taskButtons);
		} else {
			
			availableTasksVbox.getChildren().add(new Label(
					"Es konnten keine Aufgaben geladen werden. Mit dem Knopf \"Importieren\" k\u00f6nnen Sie heruntergeladene Aufgaben einbinden!"));
		}
	}
	
	/***
	 * The method build the exercises menu into a treeView and add the call event 
	 * @param availableTasks The available tasks to convert in treeItem.
	 */
	public void buildTreeViewOfTask(Task[] availableTasks){
		rootNode.setExpanded(true);
		for (Task task : availableTasks){
			TreeItem<Object> taskLeaf = new TreeItem<>(task);
//			TreeItem<Task> tmp = new TreeItem<Task>();
			
			boolean found = false;
			for(TreeItem<Object> taskType: rootNode.getChildren()){
				if(((String) taskType.getValue()).contentEquals(getLastDirectoryName(task.getLocation().getParent()))){
					taskType.getChildren().add(taskLeaf);
					found=true;
					break;
				}
			}
			if(!found){
				TreeItem<Object> taskType = new TreeItem<>(getLastDirectoryName(task.getLocation().getParent()));
				rootNode.getChildren().add(taskType);
				taskType.getChildren().add(taskLeaf);
			}
		}
		TreeView<Object> treeView = new TreeView<Object>(rootNode);
		treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> updateSelectedItem(newValue));
		availableTasksVbox.getChildren().addAll(treeView);
		
	}
	
	/***
	 * This method loads the exercise when the user clicks it.
	 * @param newValue the treeitem the user clicked
	 */
	private void updateSelectedItem(Object newValue){
		String val = ((TreeItem<?>) newValue).getValue().getClass().getSimpleName();
		if(val.equals("Task")){
			Task t = (Task) ((TreeItem<?>) newValue).getValue();
			callback.loadTask(t);
		}
	}

	public String getLastDirectoryName(Path directoryPath) {
		   int nameCount = directoryPath.getNameCount();
		   return directoryPath.getName(nameCount - 1).toString();
		}
	public String getCodeText() {
		return codeArea == null ? null : codeArea.getText();
	}

	public void showTask(Task task) {
		currentTaskLabel.clear();
		setTaskTitle(task.getTitle()); //TODO added to set task title right
		currentTaskLabel.insertText(0, task.getDescription());
		checkForLinks(task.getDescription(), 0, currentTaskLabel);
		codeArea.clear();
		codeArea.setEditable(true);
		if (task.getUserCode() != null)
			codeArea.replaceSelection(task.getUserCode());
		toolbarButtons[0].setDisable(false);
		toolbarButtons[1].setDisable(false);
		toolbarButtons[2].setDisable(false);
	}

	public void clearConsole() {
		testsOutput.clear();
		compilerOutput.clear();
		precompilerOutput.clear();
		dataTestsOutput.clear();
		ShowTestResultGui.dataTestsOutput.clear();
	}

	public void addToPrecompiler(String msg) {
		if (msg.equals("DONE\n")) {
			precompilerOutput.selectRange(0, 0);
			precompilerOutput.scrollBy(new Point2D(0d, -100d));
		} else {
			int length = precompilerOutput.getLength();
			precompilerOutput.replaceSelection(msg);
			checkForLinks(msg, length, precompilerOutput);
		}
	}

	public void addToCompiler(String msg) {
		int length = compilerOutput.getLength();
		if (msg.equals("DONE\n")) {
			compilerOutput.selectRange(0, 0);
			if (length > 300) {
				compilerOutput.replaceSelection(
						"Es liegen sehr viele Fehlermeldungen vor. Es wird empfohlen, erst einige wenige Fehler zu beheben, um dann erneut"
								+ " zu kompilieren. Viele Fehlermeldungen bedingen sich m\u00f6glicherweise gegenseitig.\n"
								+ "Beachten Sie auch den \"Precompiler\"-Tab, der weitere Informationen enthalten kann.\n\n");
			}
			compilerOutput.scrollBy(new Point2D(0d, -1000d));
		} else {
			compilerOutput.replaceSelection(msg);
			checkForLinks(msg, length, compilerOutput);
		}
	}

	private void checkForLinks(String msg, int length, StyledTextArea<LinkStyleDef, LinkStyleDef> text) {
		if (linkPattern == null || linkMap == null) {
			StringBuilder sb = new StringBuilder();
			linkMap = KnowledgeBase.getKeywordMapping();
			linkMap.keySet().forEach(keyword -> {
				if (!keyword.isEmpty())
					sb.append("(\\b" + Pattern.quote(keyword) + "\\b)|");
			});
			if (sb.length() <= 0)
				return;
			sb.deleteCharAt(sb.length() - 1);
			linkPattern = Pattern.compile(sb.toString());
		}
		Matcher matcher = linkPattern.matcher(msg);
		while (matcher.find()) {
			int start = matcher.start() + length;
			int end = matcher.end() + length;
			String group = matcher.group();
			text.setStyle(start, end, new LinkStyleDef(() -> {
				KnowledgeBase.show(linkMap.get(group));
			}));
		}
	}

	public void addToTests(String msg) {
		int length = testsOutput.getLength();
		testsOutput.replaceSelection(msg);
		checkForLinks(msg, length, testsOutput);
	}

	private void importTasks() {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().clear();
		fc.setTitle("Aufgaben importieren");
		fc.getExtensionFilters().add(new ExtensionFilter("gepackte Aufgaben (*.zip)", "*.zip"));
		List<File> result = fc.showOpenMultipleDialog(stage);
		if (result != null) {
			availableTasksVbox.getChildren().clear();
			callback.importTasks(result);
		}
	}

	public void clearCodeArea() {
		codeArea.clear();
	}

	@Override
	public int mark(int start, int end) {
		if (errors == null)
			errors = new ArrayList<>();
		errors.add(Tuples.t(start, end));
		return codeArea.getLineForPosition(start);
	}

	@Override
	public void mark(int startLine, int startCol, int endLine, int endCol) {
		if (errors == null)
			errors = new ArrayList<>();
		int lengthToStart = codeArea.getLengthUntilLine(startLine);
		if (startLine == endLine) {
			int errorLineCols = codeArea.getLengthUntilLine(startLine + 1) - lengthToStart - 1;
			if (endCol >= errorLineCols)
				endCol = errorLineCols;
		}
		errors.add(Tuples.t(lengthToStart + startCol - 1, codeArea.getLengthUntilLine(endLine) + endCol - 1)); // -1
																												// to
																												// adjust
																												// to
																												// 0-base
	}

	@Override
	public void markWarning(int startLine, int startCol, int endLine, int endCol) {
		if (warnings == null)
			warnings = new ArrayList<>();
		warnings.add(Tuples.t(codeArea.getLengthUntilLine(startLine) + startCol - 1,
				codeArea.getLengthUntilLine(endLine) + endCol - 1)); // -1 to
																		// adjust
																		// to
																		// 0-base
	}

	@Override
	public void show() {
		codeArea.markErrorsAndWarnings(errors, warnings);
		errors = null;
		warnings = null;
	}

	public void showConfigError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fehler");
		alert.setHeaderText(null);
		alert.setContentText(
				"Die Konfigurationsdatei wurde nicht gefunden oder ist besch\u00e4digt. Sie m\u00fcssen zum Fortfahren die Konfiguration vornehmen.");
		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK)
				config();
		});
	}

	public void config() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Konfiguration");
		GridPane pane = new GridPane();
		pane.setHgap(5d);
		pane.setVgap(5d);
		pane.add(new Label("JDK-Pfad"), 0, 0);
		pane.add(new Label("JUnit-Datei"), 0, 1);
		TextField jdk = new TextField();
		jdk.setText(Config.getJDKPath());
		jdk.setPrefWidth(300d);
		jdk.setPromptText("[...]" + File.separator + "Java" + File.separator
				+ "jdk[...]" + File.separator);
		if (System.getProperty("java.version").startsWith("9"))
			jdk.setDisable(true);
		pane.add(jdk, 1, 0);
		TextField junit = new TextField();
		junit.setText(Config.getJUnitPath());
		junit.setPrefWidth(300d);
		junit.setPromptText("[...]" + File.separator + "junit[...].jar");
		pane.add(junit, 1, 1);
		Button jdkButton = new Button("Durchsuchen...");
		jdkButton.setOnAction(evt -> {
			DirectoryChooser dc = new DirectoryChooser();
			dc.setTitle("JDK-Pfad ausw\u00e4hlen");
			File result = dc.showDialog(null);
			if (result != null)
				jdk.setText(result.getAbsolutePath());
		});
		if (System.getProperty("java.version").startsWith("9"))
			jdkButton.setDisable(true);
		pane.add(jdkButton, 2, 0);
		Button junitButton = new Button("Durchsuchen...");
		junitButton.setOnAction(evt -> {
			FileChooser dc = new FileChooser();
			dc.setTitle("JUnit-Datei ausw\u00e4hlen");
			dc.getExtensionFilters().clear();
			dc.getExtensionFilters().add(new ExtensionFilter("Java Archive (*.jar)", "*.jar"));
			File result = dc.showOpenDialog(null);
			if (result != null)
				junit.setText(result.getAbsolutePath());
		});
		pane.add(junitButton, 2, 1);

		DialogPane p = new DialogPane();
		p.setContent(pane);
		p.setHeader(null);
		p.setGraphic(null);
		p.getButtonTypes().add(ButtonType.OK);

		dialog.setDialogPane(p);
		dialog.showAndWait().ifPresent(result -> {
			if (result == ButtonType.OK) {
				Config.setJDKPath(jdk.getText());
				Config.setJUnitPath(junit.getText());
				System.out.println(System.getProperty("java.version"));
				if (System.getProperty("java.version").startsWith("1.8")) {
					System.out.println("setting java home to " + Config.getJDKPath());
					System.setProperty("java.home", Config.getJDKPath() + System.getProperty("file.separator") + "jre");
				}
				Config.save();
			}
		});
	}

	public int showDuplicate(Task task1, Task task2) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Duplikat in den Aufgaben gefunden");
		alert.getDialogPane().setHeaderText(null);
		alert.setContentText(
				"Es wurde ein Duplikat in den geladenen Aufgaben gefunden. Bitte w\u00e4hlen Sie eine Aktion aus.\n"
						+ "Aufgabe (1): " + task1.getTitle() + "\tin Datei " + task1.getLocation().toString() + "\n"
						+ "Aufgabe (2): " + task2.getTitle() + "\tin Datei " + task2.getLocation().toString());

		ButtonType keepSecond = new ButtonType("(1) l\u00f6schen");
		ButtonType keepFirst = new ButtonType("(2) l\u00f6schen");
		ButtonType useFirst = new ButtonType(String.valueOf("(1) laden"));
		ButtonType useSecond = new ButtonType(String.valueOf("(2) laden"));
		ButtonType useNone = new ButtonType(String.valueOf("keins laden"));
		ButtonType exit = new ButtonType(String.valueOf("beenden"));

		alert.getButtonTypes().setAll(keepSecond, keepFirst, useFirst, useSecond, useNone, exit);

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
		return EXIT;
	}

}
