package de.tu_darmstadt.informatik.skorvan.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nu.xom.Element;

/**
 * This class represents an article from the knowledge base. It consists of a
 * title, text, a list of parent articles and a list of keywords linking to this
 * article
 * 
 * @author Jan Skorvan <jan@skorvan.de>
 */
public class KnowledgeBaseArticle implements XMLConvertable {

	String title;
	String text;

	List<String> parents;
	List<String> keywords;

	// XML attribute names
	private static final String ROOT = "kbarticle";
	private static final String TITLE = "title";
	private static final String TEXT = "text";
	private static final String KEYWORDS = "keywords";
	private static final String KEYWORD = "keyword";

	// file extension
	static final String EXTENSION = ".xml";

	/**
	 * Create a new empty KnowledgeBaseArticle. Non-public constructor, load
	 * existing articles via load().
	 */
	KnowledgeBaseArticle() {
		keywords = new LinkedList<String>();
		keywords.add("");
		parents = new LinkedList<String>();
	}

	/**
	 * get the title of this article
	 * 
	 * @return the title of this article
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * get the text of this article
	 * 
	 * @return the text of this article
	 */
	public String getText() {
		return text;
	}

	/**
	 * get the parent articles of this article
	 * 
	 * @return a list of titles of this article's parent articles
	 */
	public List<String> getParents() {
		return parents;
	}

	/**
	 * get the keywords linking to this article
	 * 
	 * @return a list of keywords linking to this article
	 */
	public List<String> getKeywords() {
		return keywords;
	}

	/**
	 * load all KnowledgeBaseArticles located in the given folder and its
	 * sub-folders, using load(Path, Path)
	 * 
	 * @param kbFolder
	 *            the folder to load all articles from
	 * @return a collection of all KnowledgeBaseArticles loaded from the folder
	 */
	public static Collection<KnowledgeBaseArticle> loadAll(Path kbFolder) {
		Collection<KnowledgeBaseArticle> res = null;
		try (Stream<Path> paths = Files.walk(kbFolder)) {
			res = paths.filter(p -> p.getFileName().toString().endsWith(EXTENSION))
					.map(p -> load(kbFolder, kbFolder.relativize(p))).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * load a KnowledgeBaseArticle from the given sub-file in the given main
	 * folder
	 * 
	 * @param mainPath
	 *            a path to the base folder of the knowledge base
	 * @param articlePath
	 *            a path pointing to the article file, relative to the mainPath
	 * @return the KnowledgeBaseArticle loaded from the given file with its
	 *         parents set to the parent folders
	 */
	public static KnowledgeBaseArticle load(Path mainPath, Path articlePath) {
		KnowledgeBaseArticle art = new KnowledgeBaseArticle();
		XMLSerializer.deserialize(mainPath.resolve(articlePath), art);
		List<String> parents = new LinkedList<String>();
		if (articlePath.getParent() != null)
			articlePath.getParent().forEach(s -> parents.add(s.toString()));
		art.parents = parents;
		return art;
	}

	@Override
	public Element convertToXML() {
		Element root = new Element(ROOT);

		Element xml1 = new Element(TITLE);
		xml1.appendChild(title);
		root.appendChild(xml1);

		Element xml2 = new Element(TEXT);
		xml2.appendChild(text);
		root.appendChild(xml2);

		Element xml3 = new Element(KEYWORDS);
		for (String k : keywords) {
			Element xml4 = new Element(KEYWORD);
			xml4.appendChild(k);
			xml3.appendChild(xml4);
		}
		root.appendChild(xml3);

		return root;
	}

	@Override
	public void convertFromXML(Element xml) {
		if (xml.getLocalName().equals(ROOT)) {
			int length = xml.getChildElements().size();
			for (int i = 0; i < length; i++)
				convertFromXML(xml.getChildElements().get(i));
		} else if (xml.getLocalName().equals(KEYWORDS)) {
			keywords = new LinkedList<String>();
			int length = xml.getChildElements().size();
			for (int i = 0; i < length; i++)
				convertFromXML(xml.getChildElements().get(i));
		} else if (xml.getLocalName().equals(TITLE))
			title = xml.getValue();
		else if (xml.getLocalName().equals(TEXT))
			text = xml.getValue();
		else if (xml.getLocalName().equals(KEYWORD))
			keywords.add(xml.getValue());
		else
			System.err.println(getClass().getSimpleName() + ": found unknown xml element: " + xml.getLocalName());
	}

}
