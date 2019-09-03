package de.tu_darmstadt.informatik.skorvan.teacher.gui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.StyleClassedTextArea;

import de.tu_darmstadt.informatik.skorvan.persistence.EditableKnowledgeBaseArticle;
import de.tu_darmstadt.informatik.skorvan.persistence.KnowledgeBaseArticle;
import de.tu_darmstadt.informatik.skorvan.util.FileUtils;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KnowledgeBaseEditor {

	private static Map<String, EditableKnowledgeBaseArticle> articles;

	private static StyleClassedTextArea article;
	
	private static TextArea keywords;

	private static Stage stage;

	private static TreeView<String> tree;

	private static String currentArticle;

	private static final Path BASE_PATH = Paths.get("kb");

	public static void init(Stage stage) {
		stage.setTitle("Java Wissensdatenbank - Editor");

		articles = new HashMap<String, EditableKnowledgeBaseArticle>();
		LinkedList<EditableKnowledgeBaseArticle> temp = new LinkedList<>();
		KnowledgeBaseArticle.loadAll(BASE_PATH).stream()
				.forEach(a -> temp.add(new EditableKnowledgeBaseArticle(a)));

		TreeItem<String> rootItem = new TreeItem<String>("Start"){
			@Override
			public String toString() {
				return (String) getValue();
			}
		};
		rootItem.setExpanded(true);

		ListIterator<EditableKnowledgeBaseArticle> it = temp.listIterator(temp.size());
		while (it.hasPrevious()) {
			EditableKnowledgeBaseArticle a = it.previous();
			articles.put(a.getTitle(), a);
			if (!a.getTitle().equals("Start")) {
				TreeItem<String> item = new TreeItem<String>(a.getTitle()){
					@Override
					public String toString() {
						return (String) getValue();
					}
				};
				item.setExpanded(true);
				addToChild(item, rootItem, a.getParents());
			}
		}

		tree = new TreeView<>(rootItem);
		updateTree(rootItem);

		article = new StyleClassedTextArea();
		article.setEditable(false);
		article.setWrapText(true);
		article.setPrefWidth(500d);
		
		keywords = new TextArea();
		keywords.setEditable(false);
		keywords.setWrapText(true);
		keywords.setPrefWidth(500d);
		keywords.setPrefHeight(100d);
		keywords.setPromptText("Schl\u00fcsselw\u00f6rter");

		VirtualizedScrollPane<StyleClassedTextArea> scrollPane = new VirtualizedScrollPane<>(article);

		Button[] toolbarButtons = new Button[4];
		toolbarButtons[0] = new Button("Neu");
		toolbarButtons[0].setOnAction(evt -> {
			if (!checkChanged())
				return;
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Thema des Eintrages");
			dialog.setContentText("Geben Sie das Thema des neuen Eintrages ein:");
			dialog.setHeaderText(null);
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent() && result.get() != "") {
				if (articles.containsKey(result.get())) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText(null);
					alert.setContentText("Es besteht bereits ein Artikel zu diesem Thema");
					alert.setTitle("Fehler");
					alert.showAndWait();
				} else {
					EditableKnowledgeBaseArticle art = new EditableKnowledgeBaseArticle(result.get());
					articles.put(result.get(), art);
					TreeItem<String> newItem = new TreeItem<String>(result.get()){
						@Override
						public String toString() {
							return (String) getValue();
						}
					};
					tree.getRoot().getChildren().add(newItem);
					updateTree(tree.getRoot());
					tree.getSelectionModel().select(newItem);
					art.save(BASE_PATH);
					toolbarButtons[3].fire();
				}
			}
		});

		toolbarButtons[1] = new Button("Speichern");
		toolbarButtons[1].setOnAction(evt -> save());
		toolbarButtons[1].setDisable(true);

		toolbarButtons[2] = new Button("L\u00f6schen");
		toolbarButtons[2].setOnAction(evt -> {
			TreeItem<String> selected = tree.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(selected.getValue() + " wirklich l\u00f6schen?");
			alert.setHeaderText(null);
			alert.setContentText(
					"Wollen Sie den Eintrag " + selected.getValue() + " und alle Untereintr\u00e4ge wirklich l\u00f6schen?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				alert.setTitle("Sicher?");
				alert.setContentText("Der Eintrag " + selected.getValue() + " und alle Untereintr\u00e4ge werden endg\u00fcltig vom Datentr\u00e4ger entfernt. Sind Sie sicher?");
				result = alert.showAndWait();
			} else
				return;
			if (result.isPresent() && result.get() == ButtonType.OK){
				removeAllChildren(selected);
				selected.getParent().getChildren().remove(selected);
				if(articles.size() == 1) {
					currentArticle = null;
					tree.getSelectionModel().clearSelection();
					article.clear();
					toolbarButtons[1].setDisable(true);
					toolbarButtons[2].setDisable(true);
				}
				updateTree(tree.getRoot());
			}
		});
		toolbarButtons[2].setDisable(true);

		toolbarButtons[3] = new Button("Bewegen");
		toolbarButtons[3].setOnAction(evt -> {
			TreeItem<String> selected = tree.getSelectionModel().getSelectedItem();
			List<TreeItem<String>> possibleChildren = findAllOtherChildren(selected, rootItem);
			
			ChoiceDialog<TreeItem<String>> dialog = new ChoiceDialog<>();
			dialog.getItems().addAll(possibleChildren);
			dialog.setSelectedItem(selected.getParent());
			dialog.setTitle("Elternartikel ausw\u00e4hlen");
			dialog.setHeaderText(null);
			dialog.setContentText("W\u00e4hlen Sie einen Artikel aus, unter dem der Artikel\n" + selected.getValue() + " abgelegt werden soll:");
			
			Optional<TreeItem<String>> choice = dialog.showAndWait();
			if(choice.isPresent()) {
				TreeItem<String> chosen = choice.get();
				selected.getParent().getChildren().remove(selected);
				chosen.getChildren().add(selected);
				updateTree(rootItem);
				moveArticleAndChildren(selected, chosen);
				tree.getSelectionModel().select(selected);
			}
		});
		toolbarButtons[3].setDisable(true);

		tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null) {
				toolbarButtons[1].setDisable(true);
				toolbarButtons[2].setDisable(true);
				toolbarButtons[3].setDisable(true);
				currentArticle = null;
				article.setEditable(false);
				keywords.setEditable(false);
			} else if (!newValue.getValue().equals(currentArticle)) {
				if (!newValue.getValue().equals("Start") && checkChanged()) {
					toolbarButtons[1].setDisable(false);
					toolbarButtons[2].setDisable(false);
					toolbarButtons[3].setDisable(false);
					currentArticle = newValue.getValue();
					article.setEditable(true);
					keywords.setEditable(true);
					show(newValue.getValue());
				} else {
					if(articles.size() <= 1 || oldValue == null)
						Platform.runLater(() -> tree.getSelectionModel().clearSelection());
					else if(!articles.containsKey(oldValue.getValue()))
						Platform.runLater(() -> tree.getSelectionModel().select(1));
					else
						Platform.runLater(() -> tree.getSelectionModel().select(oldValue));
				}
			}
		});

		HBox toolbar = new HBox();
		toolbar.setSpacing(10d);
		toolbar.getChildren().addAll(toolbarButtons);

		VBox textAreas = new VBox();
		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		textAreas.setSpacing(10d);
		textAreas.getChildren().addAll(scrollPane, keywords);
		
		HBox rest = new HBox();
		rest.setSpacing(10d);

		HBox.setHgrow(textAreas, Priority.ALWAYS);
		rest.getChildren().addAll(tree, textAreas);

		VBox root = new VBox();
		root.setPadding(new Insets(10d));
		root.setSpacing(10d);

		VBox.setVgrow(rest, Priority.ALWAYS);
		root.getChildren().addAll(toolbar, rest);

		Scene scene = new Scene(root, 1000, 600);
		stage.setScene(scene);
		KnowledgeBaseEditor.stage = stage;
	}
	
	private static void moveArticleAndChildren(TreeItem<String> article, TreeItem<String> newParent) {
		LinkedList<String> newParents = new LinkedList<>();
		TreeItem<String> temp = newParent;
		while(temp != null && !temp.getValue().equals("Start")) {
			newParents.add(0, temp.getValue());
			temp = temp.getParent();
		}
		articles.get(article.getValue()).move(BASE_PATH, newParents);
		if (article.getChildren() != null) {
			article.getChildren().forEach(t -> moveArticleAndChildren(t, article));
		}
		FileUtils.cleanUp(BASE_PATH);
	}
	
	private static List<TreeItem<String>> findAllOtherChildren(TreeItem<String> findChild, TreeItem<String> root) {
		if(root.equals(findChild))
			return Collections.emptyList();
		if(root.getChildren().isEmpty())
			return Collections.singletonList(root);
		ArrayList<TreeItem<String>> possibleChildren = new ArrayList<>();
		possibleChildren.add(root);
		for(TreeItem<String> ch: root.getChildren()) {
			possibleChildren.addAll(findAllOtherChildren(findChild, ch));
		}
		return possibleChildren;
	}
	
	private static void removeAllChildren(TreeItem<String> item) {
		if(item.getChildren() != null && !item.getChildren().isEmpty()) {
			item.getChildren().forEach(i -> removeAllChildren(i));
		}
		articles.remove(item.getValue()).delete(BASE_PATH);
	}

	private static void addToChild(TreeItem<String> item, TreeItem<String> root, List<String> parents) {
		if (parents == null || parents.isEmpty()) {
			root.getChildren().add(item);
			return;
		}
		List<TreeItem<String>> children = root.getChildren();
		TreeItem<String> rightChild = children.stream().filter(t -> t.getValue().equals(parents.get(0))).findFirst()
				.get();
		addToChild(item, rightChild,
				parents.size() <= 1 ? Collections.emptyList() : parents.subList(1, parents.size()));
	}

	private static void updateTree(TreeItem<String> current) {
		if (current == null || current.getChildren() == null)
			if(current.getValue().equals("Start"))
				return;
		List<TreeItem<String>> list = current.getChildren();
		list.sort((a, b) -> a.getValue().compareTo(b.getValue()));
		list.forEach(a -> updateTree(a));
	}

	// true -> continue, false -> abort
	private static boolean checkChanged() {
		if (currentArticle == null || !articles.containsKey(currentArticle))
			return true;
		String savedText = articles.get(currentArticle).getText();
		String currentText = article.getText();
		List<String> savedKeywords = articles.get(currentArticle).getKeywords();
		List<String> currentKeywords = Arrays.asList(keywords.getText().split(","));
		if (savedText == null)
			savedText = "";
		if (currentText == null)
			currentText = "";
		if (currentText.equals(savedText) && currentKeywords.equals(savedKeywords))
			return true;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Artikel speichern?");
		alert.setContentText("Wollen Sie den Artikel " + currentArticle + " speichern?");
		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent()) {
			if (result.get() == ButtonType.YES)
				return save();
			if (result.get() == ButtonType.NO)
				return true;
		}
		return false;
	}

	private static boolean save() {
		if (currentArticle == null)
			System.out.println("KB-Editor: save was not allowed to be called");
		articles.get(currentArticle).setText(article.getText());
		articles.get(currentArticle).setKeywords(Arrays.asList(keywords.getText().split(",")));
		return articles.get(currentArticle).save(BASE_PATH);
	}

	public static void show(String topic) {
		if (topic == null) {
			stage.show();
			return;
		}
		if (!articles.containsKey(topic)) {
			System.out.println("KnowledgeBaseEditor unknown topic: " + topic);
			return;
		}
		article.clear();
		if (articles.get(topic).getText() != null)
			article.insertText(0, articles.get(topic).getText());
		keywords.clear();
		if (articles.get(topic).getKeywords() != null)
			keywords.insertText(0, articles.get(topic).getKeywords().stream().collect(Collectors.joining(",")));
	}

}
