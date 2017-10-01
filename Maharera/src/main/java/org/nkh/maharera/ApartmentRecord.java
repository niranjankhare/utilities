package org.nkh.maharera;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nkh.utils.Parser;
public class ApartmentRecord {
	public Integer apartmentTypeNo = null;
	public String apartmentType = null;
	public String carpetArea = null;
	public Integer totalNumber = null;
	public Integer bookedNumber = null;
	
	Element source = null;
	public ApartmentRecord (Element htmlSource){
		this.source = htmlSource;
		setApartmentTypeNumber();
		setApartmentType();
		setCarpetArea();		
		setNumAparments();

		setNumBookedApartments();
	}
	

	
	public void setApartmentTypeNumber() {
 		this.apartmentTypeNo = Parser.getValue(source.getElementsByTag("td").get(0), Integer.class);
	}
	
	public void setApartmentType() {
//		this.buildingNo = Integer.parseInt(source.getElementsByTag("td").get(0).text());
 		this.apartmentType = Parser.getValue(source.getElementsByTag("td").get(1), String.class);
	}
	
 	private void setCarpetArea() {
		this.carpetArea = Parser.getValue(source.getElementsByTag("td").get(2), String.class);		
	}
	
	private void setNumAparments() {
		this.totalNumber = Parser.getValue(source.getElementsByTag("td").get(3), Integer.class);
		
	}
	
	private void setNumBookedApartments() {
		this.bookedNumber = Parser.getValue(source.getElementsByTag("td").get(4), Integer.class);
		
	}



	
}

