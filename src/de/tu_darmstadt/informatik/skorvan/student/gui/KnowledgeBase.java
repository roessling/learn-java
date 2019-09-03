package de.tu_darmstadt.informatik.skorvan.student.gui;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.fxmisc.richtext.StyleClassedTextArea;

import de.tu_darmstadt.informatik.skorvan.persistence.KnowledgeBaseArticle;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class KnowledgeBase {

	private static Map<String, KnowledgeBaseArticle> articles;

	private static Stage stage;

	private static StyleClassedTextArea article;

	private static TreeView<String> tree;

	public static void init(Stage stage) {
		stage.setTitle("Java Wissensdatenbank");

		articles = new HashMap<String, KnowledgeBaseArticle>();
		LinkedList<KnowledgeBaseArticle> temp1 = new LinkedList<>(KnowledgeBaseArticle.loadAll(Paths.get("kb")));
		LinkedList<KnowledgeBaseArticle> temp2 = new LinkedList<>();
		TreeItem<String> rootItem = new TreeItem<>("Start");
		rootItem.setExpanded(true);

		while (!temp1.isEmpty()) {
			Iterator<KnowledgeBaseArticle> it = temp1.iterator();
			while (it.hasNext()) {
				KnowledgeBaseArticle a = it.next();
				articles.put(a.getTitle(), a);
				if (!a.getTitle().equals("Start")) {
					TreeItem<String> item = new TreeItem<>(a.getTitle());
					item.setExpanded(true);
					if (!addToChild(item, rootItem, a.getParents()))
						temp2.add(a);
				}
			}
			temp1 = temp2;
			temp2 = new LinkedList<>();
		}

		tree = new TreeView<>(rootItem);
		tree.getSelectionModel().clearSelection();
		tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null)
				return;
			if (!newValue.getValue().equals("Start"))
				showArticle(newValue.getValue());
			else if (oldValue == null)
				Platform.runLater(() -> tree.getSelectionModel().clearSelection());
			else
				Platform.runLater(() -> tree.getSelectionModel().select(oldValue));
		});

		article = new StyleClassedTextArea();
		article.setEditable(false);
		article.setWrapText(true);

		HBox root = new HBox();
		root.setPadding(new Insets(10d));
		root.setSpacing(10d);

		HBox.setHgrow(article, Priority.ALWAYS);
		root.getChildren().addAll(tree, article);

		Scene scene = new Scene(root, 1000, 600);
		scene.getStylesheets().add(KnowledgeBase.class.getResource("kb.css").toExternalForm());
		stage.setScene(scene);
		KnowledgeBase.stage = stage;
	}

	private static boolean addToChild(TreeItem<String> item, TreeItem<String> root, List<String> parents) {
		if (parents == null || parents.isEmpty()) {
			root.getChildren().add(item);
			return true;
		}
		List<TreeItem<String>> children = root.getChildren();
		Optional<TreeItem<String>> rightChild = children.stream().filter(t -> t.getValue().equals(parents.get(0)))
				.findFirst();
		if (rightChild.isPresent())
			return addToChild(item, rightChild.get(),
					parents.size() <= 1 ? Collections.emptyList() : parents.subList(1, parents.size()));
		return false;
	}

	private static void showArticle(String topic) {
		article.clear();
		article.insertText(0, articles.get(topic).getTitle() + "\n" + articles.get(topic).getText());
		stage.show();
		stage.requestFocus();
	}

	public static void show(String topic) {
		tree.getSelectionModel().select(findNode(topic, tree.getRoot()));
	}

	private static TreeItem<String> findNode(String topic, TreeItem<String> root) {
		if (root == null || root.getValue() == null)
			return null;
		if (root.getValue().equals(topic))
			return root;
		List<TreeItem<String>> children = root.getChildren();
		if (children == null || children.isEmpty())
			return null;
		for (TreeItem<String> i : children) {
			TreeItem<String> res = findNode(topic, i);
			if (res != null)
				return res;
		}
		return null;

	}

	public static Map<String, String> getKeywordMapping() {
		Map<String, String> mapping = new HashMap<>();
		articles.values().forEach(a -> a.getKeywords().forEach(k -> mapping.put(k, a.getTitle())));
		return mapping;
	}
}
