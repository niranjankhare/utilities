package org.nkh.maharera;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BuildingRecord {
	Element source = null;
	public Integer buildingNo = null;
	public  String buildingProjectName = null;
	public  String buildingName = null;
	public  Integer numBasements = null;
	public  Integer numPlinths = null;
	public  Integer numPodiums = null;
	public  Integer numSupSlabs = null;
	public  Integer numStilts = null;
	public  Integer numOpenParkings = null;
	public  Integer numClosedParkings = null;
	
	List<ApartmentRecord> Apartments = new ArrayList<ApartmentRecord>();
	
	public BuildingRecord (Element htmlSource){
		this.source = htmlSource.nextElementSibling();
		setBuildingNo ();
		setBuildingProjectName();
		setBuildingName();
		setNumBasements();
		setNumPlinths();
		setNumPodiums();
		setNumSupStructSlabs();
		setNumStilts();
		setNumOpenParking();
		setNumClosedParking();
		setApartments (source.nextElementSibling());
	}
	
	private void setApartments (Element htmlSource){
		Elements apartments = htmlSource.getElementsByTag("tbody").get(0).getElementsByTag("tr");
		for (int i=1;i<apartments.size(); i++){
			this.Apartments.add(new ApartmentRecord(apartments.get(i)));
		}
	}
	
	public <T> T getValue(Element element, Class<?> returnType){
		String text = element.text();
		if (returnType==Integer.class){
			return (T)(Object)Integer.parseInt(text);
		} 
		else 
			return (T)text;
		
	}
 	public void setBuildingNo() {
//		this.buildingNo = Integer.parseInt(source.getElementsByTag("td").get(0).text());
 		this.buildingNo = getValue(source.getElementsByTag("td").get(0), Integer.class);
	}

	public void setBuildingProjectName() {
		this.buildingProjectName = getValue(source.getElementsByTag("td").get(1), String.class);
	}
	
	public void setBuildingName() {
		this.buildingName = getValue(source.getElementsByTag("td").get(2), String.class);
	}
	

	public void setNumBasements() {
		this.numBasements = getValue(source.getElementsByTag("td").get(3), Integer.class);
	}

	public void setNumPlinths() {
		this.numPlinths = getValue(source.getElementsByTag("td").get(4), Integer.class);
	}

	public void setNumPodiums() {
		this.numPodiums = getValue(source.getElementsByTag("td").get(5), Integer.class);
	}

	public void setNumSupStructSlabs() {
		this.numSupSlabs = getValue(source.getElementsByTag("td").get(6), Integer.class);
	}

	public void setNumStilts() {
		this.numStilts = getValue(source.getElementsByTag("td").get(7), Integer.class);
	}

	public void setNumOpenParking() {
		this.numOpenParkings = getValue(source.getElementsByTag("td").get(8), Integer.class);
	}

	public void setNumClosedParking() {
		this.numClosedParkings = getValue(source.getElementsByTag("td").get(9), Integer.class);
	}

	
}
