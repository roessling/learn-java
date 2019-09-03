package de.tu_darmstadt.informatik.skorvan.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a KnowledgeBaseArticle that can be edited by the
 * teacher tool
 * 
 * @author Jan Skorvan <jan@skorvan.de>
 */
public class EditableKnowledgeBaseArticle extends KnowledgeBaseArticle {

	/**
	 * create a new EditableKnowledgeArticle with a given title
	 * 
	 * @param title
	 *            the title of the new article
	 */
	public EditableKnowledgeBaseArticle(String title) {
		this.title = title;
	}

	/**
	 * create a new EditableKnowledgeBaseArticle using an existing
	 * KnowledgeBaseArticle
	 * 
	 * @param base
	 *            the base article that shall be copied into a new instance of
	 *            EditableKnowledgeBaseArticle
	 */
	public EditableKnowledgeBaseArticle(KnowledgeBaseArticle base) {
		title = base.title;
		text = base.text;
		parents = new LinkedList<String>(base.parents);
		keywords = new LinkedList<String>(base.keywords);
	}

	/**
	 * set the article's text in the instance
	 * 
	 * @param text
	 *            the new article's text in this instance
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * move the file that contained this article to another location using the
	 * given list of parents
	 * 
	 * @param basePath
	 *            the base path of the knowledge base
	 * @param parents
	 *            the list of parents, representing the folder structure the
	 *            file goes into
	 * @return true if the new file was saved successfully, false otherwise
	 */
	public boolean move(Path basePath, List<String> parents) {
		delete(basePath);
		this.parents = parents;
		return save(basePath);
	}

	/**
	 * calculate the full path to the file containing this article, using the
	 * base path and the saved parent list
	 * 
	 * @param basePath
	 *            the base path to the knowledge base
	 * @return a Path to the file this article is saved to
	 */
	private Path getFullPath(Path basePath) {
		if (parents != null) {
			for (String s : parents) {
				basePath = basePath.resolve(s);
			}
		}
		return basePath.resolve(title + EXTENSION);
	}

	/**
	 * save this article to the correct file using the given base path for the
	 * knowledge base
	 * 
	 * @param basePath
	 *            the base path of the knowledge base
	 * @return true if the file was saved successfully, false otherwise
	 */
	public boolean save(Path basePath) {
		return XMLSerializer.serialize(getFullPath(basePath), this);
	}

	/**
	 * delete the file containing this article using the base path of the
	 * knowledge base
	 * 
	 * @param basePath
	 *            the base path of the knowledge base
	 */
	public void delete(Path basePath) {
		try {
			Files.deleteIfExists(getFullPath(basePath));
		} catch (IOException e) {
			System.err.println("deleting article " + title + ": " + e.getClass().getSimpleName());
		}
	}

	/**
	 * set the keywords of this article
	 * 
	 * @param keywords
	 *            the new keywords of this article
	 */
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
}
