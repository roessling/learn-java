package de.tu_darmstadt.informatik.skorvan.student.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDate;

import de.tu_darmstadt.informatik.skorvan.exercises.UnitResultItem;
import de.tu_darmstadt.informatik.skorvan.persistence.HistoryItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HistoryGui {
	
	private static Stage stage;
	private static final Path HISTORYPATH = Paths.get("history");
	public static ObservableList<HistoryItem> dataTestsOutput =
            FXCollections.observableArrayList();
	
	public static void init(Stage stage){
		
		stage.setTitle("Statistik");
		//Bottom Bar: Button to clear all entries in the table
		Button clearButton= new Button("Alle Einträge löschen");
		clearButton.setOnAction(evt->ClearTable());
		
		//BOrderPane Center: Table for all entries
		TableView<HistoryItem> tableResults = new TableView<>();
		tableResults.setEditable(false);
		
		TableColumn<HistoryItem, String> firstCol = new TableColumn<HistoryItem, String>("Aufgabenname");
		firstCol.setMinWidth(200);
		firstCol.setCellValueFactory(
				new PropertyValueFactory<HistoryItem, String>("functionName"));
		
		TableColumn<HistoryItem, String> secondCol = new TableColumn<HistoryItem, String>("Aufrufe");
		secondCol.setMinWidth(100);
		secondCol.setCellValueFactory(
				new PropertyValueFactory<HistoryItem, String>("runs"));
		
		TableColumn<HistoryItem, String> thirdCol = new TableColumn<HistoryItem, String>("Kompilieren Anzahl");
		thirdCol.setMinWidth(200);
		thirdCol.setCellValueFactory(
				new PropertyValueFactory<HistoryItem, String>("compiled"));
		
		TableColumn<HistoryItem, String> forthCol = new TableColumn<HistoryItem, String>("Fehleranzahl");
		forthCol.setMinWidth(150);
		forthCol.setCellValueFactory(
				new PropertyValueFactory<HistoryItem, String>("failed"));
		
		tableResults.getColumns().addAll(firstCol,secondCol,thirdCol,forthCol);
		tableResults.setItems(dataTestsOutput);
		BorderPane root = new BorderPane();
		root.setCenter(tableResults);
		root.setBottom(clearButton);
		root.setStyle("background-color: green;");
		Scene scene = new Scene(root,600,400);
		stage.setScene(scene);
		HistoryGui.stage = stage;
	}
	
	public static void ClearTable(){
		dataTestsOutput.clear();
	}
	
	/**
	 * check if the Dir exists or not and creates it.
	 * @param path the directory for the history/statistic
	 * @return true means the directory was created or it exists already
	 */
	private boolean makeDirIfDoesntExist(Path path){
		if(Files.notExists(HISTORYPATH)){
			try {
				Files.createDirectories(HISTORYPATH);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static void show(){
		stage.show();
	}

}
