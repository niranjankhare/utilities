package org.nkh.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xml.sax.*;

public class Parser {
	public static Document parse(String htmlResponse) throws ParserConfigurationException, IOException, SAXException {

		URL is = Parser.class.getClassLoader().getResource(htmlResponse);
		File input = null;
		try {
			input = new File(is.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = Jsoup.parse(input, "UTF-8");

		return doc;
	}

	

	

	/**
	 * Carpet Area (in Sqmts) Number of Apartment Number of Booked Apartment
	 */

}
