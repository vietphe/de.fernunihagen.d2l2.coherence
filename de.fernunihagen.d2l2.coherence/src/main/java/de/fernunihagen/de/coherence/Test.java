package de.fernunihagen.de.coherence;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Test {
	private static void read() throws ParserConfigurationException, SAXException, IOException {
		File file = new File("target/bincas/1003_aID1AD._anno.xmi");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
		        .newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = (Document) documentBuilder.parse(file);
		String usr =  document.getElementsByTagName("user").item(0).getTextContent();
		String pwd = document.getElementsByTagName("password").item(0).getTextContent();
	}
}
