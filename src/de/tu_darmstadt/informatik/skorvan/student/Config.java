package de.tu_darmstadt.informatik.skorvan.student;

import java.nio.file.Paths;

import de.tu_darmstadt.informatik.skorvan.persistence.XMLConvertable;
import de.tu_darmstadt.informatik.skorvan.persistence.XMLSerializer;
import nu.xom.Element;

/**
 * The configuration for the student tool, containing JDK and JUnit paths. The
 * chosen design for this class is a singleton instance, using static methods to
 * access its values. This allows the interface XMLConvertable to be
 * implemented, providing persistence utility
 * 
 * @author Jan Skorvan <jan@skorvan.de>
 */
public class Config implements XMLConvertable {

	// constant Strings for XML-Element names
	private static final String ROOT = "config";
	private static final String COMPILER = "compiler";
	private static final String JUNIT = "junit";

	// constant name of the XML file
	private static final String FILENAME = "config.xml";

	// Strings representing the path to the JDK folder and the JUnit JAR
	private String jdkPath;
	private String jUnitPath;

	// singleton instance
	static Config instance;

	/**
	 * load the configuration from the config file
	 * 
	 * @return true if loading was successful, false if there was an error
	 */
	public static boolean load() {
		instance = new Config();
		return XMLSerializer.deserialize(Paths.get(FILENAME), instance);
	}

	/**
	 * save the configuration in the config file
	 * 
	 * @return true if saving was successful, false if there was an error or the
	 *         config was not yet initialized
	 */
	public static boolean save() {
		if (instance == null)
			return false;
		return XMLSerializer.serialize(Paths.get(FILENAME), instance);
	}

	/**
	 * get the JDK folder path
	 * 
	 * @return a String representing the path to the JDK folder
	 */
	public static String getJDKPath() {
		return instance.jdkPath;
	}

	/**
	 * set the path to the JDK folder
	 * 
	 * @param jdkPath
	 *            a String representing the path to the JDK folder
	 */
	public static void setJDKPath(String jdkPath) {
		instance.jdkPath = jdkPath;
	}

	/**
	 * get the JUnit JAR path
	 * 
	 * @return a String representing the path to the JUnit JAR
	 */
	public static String getJUnitPath() {
		return instance.jUnitPath;
	}

	/**
	 * set the path to the JUnit JAR
	 * 
	 * @param jUnitPath
	 *            a String representing the path to the JUnit JAR
	 */
	public static void setJUnitPath(String jUnitPath) {
		instance.jUnitPath = jUnitPath;
	}

	@Override
	public Element convertToXML() {
		Element root = new Element(ROOT);

		Element comp = new Element(COMPILER);
		comp.appendChild(jdkPath);
		root.appendChild(comp);

		Element junit = new Element(JUNIT);
		junit.appendChild(jUnitPath);
		root.appendChild(junit);

		return root;
	}

	@Override
	public void convertFromXML(Element xml) {
		if (xml.getLocalName().equals(ROOT)) {
			int length = xml.getChildElements().size();
			for (int i = 0; i < length; i++)
				convertFromXML(xml.getChildElements().get(i));
		} else if (xml.getLocalName().equals(COMPILER))
			jdkPath = xml.getValue();
		else if (xml.getLocalName().equals(JUNIT))
			jUnitPath = xml.getValue();
		else
			System.out.println(getClass().getSimpleName() + ": unknown XML element found: " + xml.getLocalName());
	}

	/**
	 * reset the configuration, creating a new singleton without values
	 */
	public static void reset() {
		instance = new Config();
	}
}