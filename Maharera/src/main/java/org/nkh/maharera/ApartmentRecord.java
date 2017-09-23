package org.nkh.maharera;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class ApartmentRecord {
	String apartmentType = "";
	String carpetArea = "";
	String totalNumber = "";
	String bookedNumber = "";
	
	Element source = null;
	public ApartmentRecord (Element htmlSource){
		this.source = htmlSource;
	}
	
	public static String apartmentType() {
		return null;
	}

	public static String carpetAreaSqM() {
		return null;
	}

	public static String numApartments() {
		return null;
	}

	public static String bookedApartments() {
		return null;
	}
}

