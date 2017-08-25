package org.nkh.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import jdk.internal.org.xml.sax.SAXException;

public class Parser {
	public static Document parse() throws ParserConfigurationException, IOException, SAXException {
		
		URL is = Parser.class.getClassLoader().getResource("Record.html");
		File input= null;
		try {
			input = new File(is.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = Jsoup.parse(input, "UTF-8");
		
		return doc;
	}
	
//	Document doc = Jsoup.connect("http://example.com/").get();
//	String title = doc.title();
//	File input = new File("/tmp/input.html");
//	Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
}
