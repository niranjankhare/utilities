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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jdk.internal.org.xml.sax.SAXException;

public class Parser {
	public static Document parse() throws ParserConfigurationException, IOException, SAXException {

		URL is = Parser.class.getClassLoader().getResource("RecordCompany.html");
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

	public static String getInternalType(Document doc) {
		Element rawType = doc.getElementById("fldind");
		if (rawType != null)
			return "Individual";
		else
			return "Non Individual";
	}

	public static String getTypeInfo(Document doc) {
		Element rawType = doc.getElementById("divInfoType");
		Element y = rawType.getElementsByAttributeValue("for", "PersonalInfoModel_InfoTypeValue").get(0);
		return y.parent().siblingElements().text();
	}
	
	public static String owner (){
		return null;
		
	}
	
	public static String projectName (){
		return null;
	}
	
	public static String pinCode(){
		return null;
	}
	
	public static String division(){
		return null;
	}
	
	public static String district(){
		return null;
	}
	
	public static String taluka(){
		return null;
	}
	
	public static String village(){
		return null;
	}

	public static String areaSqM (){
		return null;
	}
	
	public static String projectType (){
		return null;
	}
	
	public static String buildingNo () {
		return null;
	}
	
	public static String buildingName (){
		return null;
	}
	
	public static String numBasements (){
		return null;
	}
	
	public static String numPlinths (){
		return null;
	}
	
	public static String numPodiums (){
		return null;
	}
	
	public static String numSupStructSlabs (){
		return null;
	}
	
	public static String numStilts (){
		return null;
	}
	
	public static String numOpenParking (){
		return null;
	}
	
	public static String numClosedParking (){
		return null;
	}
	
	public static String apartmentType (){
		return null;
	}
	public static String carpetAreaSqM (){
		return null;
	}public static String numApartments (){
		return null;
	}public static String bookedApartments (){
		return null;
	}
	
	/**
	 * 		Carpet Area (in Sqmts)	Number of Apartment	Number of Booked Apartment
	 */
			
}
