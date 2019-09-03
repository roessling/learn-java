package de.tu_darmstadt.informatik.skorvan.teacher;

import java.nio.file.Paths;

import de.tu_darmstadt.informatik.skorvan.persistence.XMLConvertable;
import de.tu_darmstadt.informatik.skorvan.persistence.XMLSerializer;
import nu.xom.Element;

public class TeacherConfig implements XMLConvertable {

	private static final String ROOT = "teacherconfig";

	private static final String NEXTID = "nextid";

	private static final String FILENAME = "teacherconfig.xml";

	private String nextId;

	private static TeacherConfig instance;

	public static String getNextId() {
		return instance.nextId;
	}

	public static void setNextId(String nextId) {
		instance.nextId = nextId;
	}

	/**
	 * load the configuration from the config file
	 * 
	 * @return true if loading was successful, false if there was an error
	 */
	public static boolean load() {
		instance = new TeacherConfig();
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

	@Override
	public void convertFromXML(Element xml) {
		if (xml.getLocalName().equals(ROOT)) {
			int length = xml.getChildElements().size();
			for (int i = 0; i < length; i++)
				convertFromXML(xml.getChildElements().get(i));
		} else if (xml.getLocalName().equals(NEXTID))
			nextId = xml.getValue();
		else
			System.out.println("TeacherConfig: unknown XML element: " + xml.getLocalName());
	}

	@Override
	public Element convertToXML() {
		Element root = new Element(ROOT);

		Element id = new Element(NEXTID);
		id.appendChild(nextId);

		root.appendChild(id);

		return root;
	}

	public static void reset() {
		instance = new TeacherConfig();
	}
}