package de.tu_darmstadt.informatik.skorvan.student.gui;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Help {

	private static Stage stage;

	public static void init(Stage stage, Application app) {
		Help.stage = stage;

		stage.setTitle("Hilfe");

		WebView browser = new WebView();
		browser.setMaxHeight(Double.MAX_VALUE);
		try {
			browser.getEngine().load(new File("help" + File.separator + "index.html").toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		browser.getEngine().setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {

			@Override
			public WebEngine call(PopupFeatures arg0) {
				// grab the last hyperlink that has :hover pseudoclass
				Object o = browser.getEngine()
						.executeScript("var list = document.querySelectorAll( ':hover' );"
								+ "for (i=list.length-1; i>-1; i--) " + "{ if ( list.item(i).getAttribute('href') ) "
								+ "{ list.item(i).getAttribute('href'); break; } }");

				// open in native browser
				if (o != null)
					app.getHostServices().showDocument(o.toString());

				// prevent from opening in webView
				return null;
			}

		});

		stage.setScene(new Scene(browser, 1000, 700));

	}

	public static void show() {
		stage.show();
	}

}
