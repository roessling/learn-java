package de.tu_darmstadt.informatik.skorvan.persistence;

import nu.xom.Element;

/**
 * This interface has to implemented by every class that uses XMLSerializer to
 * save its contents to an XML file and load from it
 * 
 * @author Jan Skorvan <jan@skorvan.de>
 */
public interface XMLConvertable {

	/**
	 * convert this instance into an XML Element containing all information that
	 * is supposed to be saved to a file
	 * 
	 * @return an XML Element containing all information that is supposed to be
	 *         saved to a file
	 */
	public Element convertToXML();

	/**
	 * read all information from the given XML Element and load it into this
	 * instance
	 * 
	 * @param xml
	 *            the Element to read all information from
	 */
	public void convertFromXML(Element xml);

}
