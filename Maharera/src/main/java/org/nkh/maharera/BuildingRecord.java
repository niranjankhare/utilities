package org.nkh.maharera;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BuildingRecord {
	Element source = null;
	List<ApartmentRecord> Apartments = new ArrayList<ApartmentRecord>();
	
	public BuildingRecord (Element htmlSource){
		this.source = htmlSource.nextElementSibling();
		setApartments (source.nextElementSibling());
	}
	
	private void setApartments (Element htmlSource){
		Elements apartments = htmlSource.getElementsByTag("tbody").get(0).getElementsByTag("tr");
		for (int i=1;i<apartments.size(); i++){
			this.Apartments.add(new ApartmentRecord(apartments.get(i)));
		}
	}
	
	public static String buildingNo() {
		return null;
	}

	public static String buildingName() {
		return null;
	}

	public static String numBasements() {
		return null;
	}

	public static String numPlinths() {
		return null;
	}

	public static String numPodiums() {
		return null;
	}

	public static String numSupStructSlabs() {
		return null;
	}

	public static String numStilts() {
		return null;
	}

	public static String numOpenParking() {
		return null;
	}

	public static String numClosedParking() {
		return null;
	}

	
}
