package br.com.zara.horarios;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProjetosXMLHandler extends DefaultHandler {

	Boolean currentElement = false;
	String currentValue = null;
	public static ProjectList projectsList = null;

	public static ProjectList getSitesList() {
		return projectsList;
	}

	public static void setSitesList(ProjectList projectsList) {
		ProjetosXMLHandler.projectsList = projectsList;
	}

	/** Called when tag starts ( ex:- <nome>Projeto 1</nome> 
	 * -- <nome> )*/
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		currentElement = true;

		if (localName.equals("projetos"))
		{
			/** Start */ 
			projectsList = new ProjectList();
		}

	}

	/** Called when tag closing ( ex:- <nome>Projeto 1</nome> 
	 * -- <nome> )*/
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		currentElement = false;

		/** set value */ 
		if (localName.equalsIgnoreCase("nome"))
			projectsList.setName(currentValue);
	}

	/** Called to get tag characters ( ex:- <nome>Projeto 1</nome> 
	 * -- to get Projeto 1 Character ) */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (currentElement) {
			currentValue = new String(ch, start, length);
			currentElement = false;
		}
	}
}