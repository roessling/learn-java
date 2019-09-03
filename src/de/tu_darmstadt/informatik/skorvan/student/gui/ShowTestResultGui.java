package de.tu_darmstadt.informatik.skorvan.student.gui;

import de.tu_darmstadt.informatik.skorvan.exercises.UnitResultItem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ShowTestResultGui {
	
	private static Stage stage;
	public static ObservableList<UnitResultItem> dataTestsOutput =
            FXCollections.observableArrayList();
	
	public  static void start(Stage primaryStage) {
		primaryStage.setTitle("Unit Tests Ergebnis");
		// TODO Auto-generated method stub
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
		tableResults.setItems(dataTestsOutput);
		Scene scene = new Scene(tableResults, 600, 400);
		primaryStage.setScene(scene);
		ShowTestResultGui.stage = primaryStage;
	}
	
	public static void Show(){
		stage.show();
	}

}
