package de.tu_darmstadt.informatik.skorvan.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.reactfx.util.Tuple2;
import org.reactfx.util.Tuples;

import nu.xom.Attribute;
import nu.xom.Element;

/**
 * this class contains all information necessary about a task for students
 * 
 * @author Jan Skorvan <jan@skorvan.de>
 */
public class Task implements XMLConvertable, Comparable<Task> {

	// XML attribute names
	static final String ROOT = "task";
	static final String ID = "id";
	static final String DESCRIPTION = "description";
	static final String TITLE = "title";
	static final String TEST_FILE = "test_file";
	static final String CLASS_NAME = "class_name";
	static final String CODE_PREFIX = "code_prefix";
	static final String CODE_SUFFIX = "code_suffix";
	static final String USER_CODE = "user_code";

	private String id;
	private String description;
	private String title;
	private String className;
	private String codePrefix;
	private String codeSuffix;
	private String testFile;
	private String userCode;
	// the location of the file this task was loaded from
	private Path location;

	/**
	 * private empty constructor. Existing Tasks are loaded via load() or
	 * loadAll()
	 */
	private Task() {

	}

	/**
	 * get the ID of this task
	 * 
	 * @return the ID of this task
	 */
	public String getId() {
		return id;
	}

	/**
	 * get the description text of this task
	 * 
	 * @return the description text of this task
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * get the title of this task
	 * 
	 * @return the title of this task
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * get the code prefix of this task
	 * 
	 * @return the code prefix of this task
	 */
	public String getCodePrefix() {
		return codePrefix;
	}

	/**
	 * get the code suffix of this task
	 * 
	 * @return the code suffix of this task
	 */
	public String getCodeSuffix() {
		return codeSuffix;
	}

	/**
	 * get the name of the test file for this task
	 * 
	 * @return the name of the test file for this task
	 */
	public String getTestFile() {
		return testFile;
	}

	/**
	 * get the path to the test file that has to be in the same location as the
	 * file this task was loaded from
	 * 
	 * @return the path to the test file
	 */
	public Path getTestFilePath() {
		return location.resolveSibling(testFile);
	}

	/**
	 * get the name of the class that is constructed by this task
	 * 
	 * @return the name of the class that is constructed by this task
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * get the code that the user saved after working on this task
	 * 
	 * @return the code that the user saved after working on this task
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * get the location of the file this task was loaded from
	 * 
	 * @return the location of the file this task was loaded from
	 */
	public Path getLocation() {
		return location;
	}

	/**
	 * set the code the user wants to save when working on this task
	 * 
	 * @param userCode
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * save a task to it's location
	 * 
	 * @param taskToSave
	 *            the task to save
	 * @return true if the task was saved successfully, false otherwise
	 */
	public static boolean save(Task taskToSave) {
		return XMLSerializer.serialize(taskToSave.location, taskToSave);
	}

	/**
	 * load a task from a given file
	 * 
	 * @param taskFile
	 *            the file to load a task from
	 * @param temp
	 *            true if the location of this task shall be omitted, false if
	 *            it shall be used for saving again
	 * @return the task loaded from the given file
	 */
	public static Task load(Path taskFile, boolean temp) {
		Task task = new Task();
		XMLSerializer.deserialize(taskFile, task);
		if (!temp)
			task.location = taskFile.toAbsolutePath();
		if (temp)
			task.testFile = taskFile.toAbsolutePath().getParent() + File.separator + task.testFile;
		return task;
	}

	/**
	 * load all tasks in the given folder and its sub-folders
	 * 
	 * @param taskFolder
	 *            the folder to load all tasks from
	 * @param temp
	 *            true if the location of this task shall be omitted, false if
	 *            it shall be used for saving again
	 * @return an array of tasks that have been loaded from the folder
	 */
	public static Task[] loadAll(Path taskFolder, boolean temp) {
		Task[] res = null;
		if (taskFolder.toFile().exists()) {
			try (Stream<Path> paths = Files.walk(taskFolder)) {
				res = paths.filter(p -> p.getFileName().toString().endsWith(".xml")).map(p -> load(p, temp))
						.toArray(Task[]::new);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return res;
		}
		return new Task[0];
	}

	/**
	 * write this task to a .java file that contains package information, code
	 * prefix, user code and code suffix
	 * 
	 * @param userCode
	 *            the user's code that shall be inserted into the file
	 * @return a Tuple2 containing: (1) - the number of characters before the
	 *         user's code begins and (2) - the number of lines before the
	 *         user's code begins
	 */
	public Tuple2<Integer, Integer> writeToJavaFile(String userCode) {
		this.userCode = userCode;
		String relPath = location.getParent().getFileName().toString();
		Tuple2<Integer, Integer> offset = Tuples.t(0, 0);
		try (PrintWriter pw = new PrintWriter(location.resolveSibling(className + ".java").toFile())) {
			offset = offset.update1(offset.get1() + ("package " + relPath.replaceAll(Pattern.quote(File.separator), ".")
					+ ";" + System.getProperty("line.separator")).length());
			pw.println("package " + relPath.replaceAll(Pattern.quote(File.separator), ".") + ";");
			offset = offset.update1(offset.get1() + codePrefix.length());
			offset = offset.update2(offset.get2() + codePrefix.split("\n").length + 1); // +1
																						// for
																						// package
																						// info
			pw.println(codePrefix.replace('\t', ' '));
			pw.println(userCode.replace('\t', ' '));
			pw.println(codeSuffix.replace('\t', ' '));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return offset;
	}

	@Override
	public Element convertToXML() {
		Element xml1 = new Element(ROOT);
		xml1.addAttribute(new Attribute(ID, id));

		Element xml2 = new Element(DESCRIPTION);
		xml2.appendChild(description);
		xml1.appendChild(xml2);

		Element xml3 = new Element(TITLE);
		xml3.appendChild(title);
		xml1.appendChild(xml3);

		Element xml4 = new Element(CODE_PREFIX);
		xml4.appendChild(codePrefix);
		xml1.appendChild(xml4);

		Element xml5 = new Element(CODE_SUFFIX);
		xml5.appendChild(codeSuffix);
		xml1.appendChild(xml5);

		Element xml6 = new Element(CLASS_NAME);
		xml6.appendChild(className);
		xml1.appendChild(xml6);

		Element xml7 = new Element(TEST_FILE);
		xml7.appendChild(testFile);
		xml1.appendChild(xml7);

		if (userCode != null) {
			Element xml12 = new Element(USER_CODE);
			xml12.appendChild(userCode);
			xml1.appendChild(xml12);
		}

		return xml1;
	}

	@Override
	public void convertFromXML(Element xml) {
		if (xml.getLocalName().equals(ROOT)) {
			id = xml.getAttributeValue(ID);
			int length = xml.getChildElements().size();
			for (int i = 0; i < length; i++)
				convertFromXML(xml.getChildElements().get(i));
		} else if (xml.getLocalName().equals(ID))
			id = xml.getValue();
		else if (xml.getLocalName().equals(DESCRIPTION))
			description = xml.getValue();
		else if (xml.getLocalName().equals(TITLE))
			title = xml.getValue();
		else if (xml.getLocalName().equals(CODE_PREFIX))
			codePrefix = xml.getValue();
		else if (xml.getLocalName().equals(CODE_SUFFIX))
			codeSuffix = xml.getValue();
		else if (xml.getLocalName().equals(CLASS_NAME))
			className = xml.getValue();
		else if (xml.getLocalName().equals(TEST_FILE))
			testFile = xml.getValue();
		else if (xml.getLocalName().equals(USER_CODE))
			userCode = xml.getValue();
		else
			System.out.println("Unknown xml element found: " + xml.getLocalName());
	}

	/**
	 * delete all temporary files that have been created by executing this task
	 */
	public void clean() {
		try {
			Files.deleteIfExists(location.resolveSibling(className + ".java"));
			Files.deleteIfExists(location.resolveSibling(className + ".class"));
			Files.deleteIfExists(location.resolveSibling(testFile + ".java"));
			Files.deleteIfExists(location.resolveSibling(testFile + ".class"));
			Files.deleteIfExists(location.resolveSibling("temp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
//		return "Task (" + id + "): " + title;
		return title;
	}

	@Override
	public int compareTo(Task that) {
		return Integer.compare(Integer.valueOf(id), Integer.valueOf(that.id));
	}

	/**
	 * a factory for creating new tasks, used by the teacher tool only
	 * 
	 * @author Jan Skorvan <jan@skorvan.de>
	 */
	public static class TaskFactory {

		Task task;

		/**
		 * create a new TaskFactory
		 */
		public TaskFactory() {
			task = new Task();
		}

		/**
		 * set the ID of the task
		 * 
		 * @param id
		 *            the ID of the task
		 */
		public void setId(String id) {
			task.id = id;
		}

		/**
		 * set the title of the task
		 * 
		 * @param title
		 *            the title of the task
		 */
		public void setTitle(String title) {
			task.title = title;
		}

		/**
		 * set the description of the task
		 * 
		 * @param description
		 *            the description of the task
		 */
		public void setDescription(String description) {
			task.description = description;
		}

		/**
		 * set the code prefix of the task
		 * 
		 * @param codePrefix
		 *            the code prefix of the task
		 */
		public void setCodePrefix(String codePrefix) {
			task.codePrefix = codePrefix;
		}

		/**
		 * set the code suffix of the task
		 * 
		 * @param codeSuffix
		 *            the code suffix of the task
		 */
		public void setCodeSuffix(String codeSuffix) {
			task.codeSuffix = codeSuffix;
		}

		/**
		 * set the name of the test file of the task
		 * 
		 * @param testFile
		 *            the name of the test file of the task
		 */
		public void setTestFile(String testFile) {
			task.testFile = testFile;
		}

		/**
		 * set the class name of the task
		 * 
		 * @param className
		 *            the class name of the task
		 */
		public void setClassName(String className) {
			task.className = className;
		}

		/**
		 * set the location of the task
		 * 
		 * @param location
		 *            the location of the task
		 */
		public void setLocation(Path location) {
			task.location = location;
		}

		/**
		 * Create the task with the given parameters. A task must always have an
		 * ID before creating it
		 * 
		 * @return the task with the parameters set beforehand
		 * @throws IllegalStateException
		 *             when the ID was not set yet
		 */
		public Task create() {
			if (task.id == null)
				throw new IllegalStateException("Cannot create Task before setting ID");
			return task;
		}
		
	}
}
