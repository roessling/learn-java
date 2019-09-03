package de.tu_darmstadt.informatik.skorvan.example;

import java.io.InputStream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(20d));
		vbox.setSpacing(10d);
		
		Button button = new Button("Klick' Mich!");
		button.setOnAction((evt) -> {
			button.setText("Danke.");
		});
		button.setPrefSize(100d, 40d);
		button.setAlignment(Pos.TOP_CENTER);
		vbox.getChildren().add(button);
		
		Label label = new Label("Dies ist ein Label mit einer Nachricht.");
		label.setWrapText(true);
		vbox.getChildren().add(label);
		
		TextField textField = new TextField();
		textField.setPromptText("Eingabe");
		vbox.getChildren().add(textField);
		
		Scene scene = new Scene(vbox, 220, 180);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
		primaryStage.setTitle("TestTitle");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

class Lala {
}