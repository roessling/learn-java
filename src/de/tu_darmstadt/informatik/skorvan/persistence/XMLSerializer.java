package de.tu_darmstadt.informatik.skorvan.persistence;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.Serializer;

/**
 * this class contains methods to write to and read from XML files
 * 
 * @author Jan Skorvan <jan@skorvan.de>
 */
public class XMLSerializer {

	/**
	 * write an XMLConvertable to a file
	 * 
	 * @param xmlFile
	 *            the file to write to
	 * @param xmlConvertable
	 *            the object instance to write to the file
	 * @return true if the serialization was successful, false otherwise
	 */
	public static boolean serialize(Path xmlFile, XMLConvertable xmlConvertable) {
		if (!xmlFile.toFile().exists())
			try {
				if (xmlFile.getParent() != null)
					xmlFile.getParent().toFile().mkdirs();
				xmlFile.toFile().createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}
		
		Document doc = new Document(xmlConvertable.convertToXML());
		try (FileOutputStream fos = new FileOutputStream(xmlFile.toFile())) {
			Serializer ser = new Serializer(fos, "ISO-8859-1");
			ser.write(doc);
		} catch (IOException e) {
			System.err.println("Serialization error for XML file " + xmlFile);
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * load a file into an XMLConvertable
	 * 
	 * @param xmlFile
	 *            the file to read from
	 * @param xmlConvertable
	 *            the object instance to receive the input
	 * @return true if the deserialization was successful, false otherwise
	 */
	public static boolean deserialize(Path xmlFile, XMLConvertable xmlConvertable) {
		Builder builder = new Builder();
		try {
			xmlConvertable.convertFromXML(builder.build(xmlFile.toFile()).getRootElement());
		} catch (ParsingException | IOException e) {
			System.err.println("Deserialization error for XML file " + xmlFile);
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

}
