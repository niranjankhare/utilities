package org.nkh.maharera;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class ApartmentRecord {
	public String apartmentType = null;
	public String carpetArea = null;
	public Integer totalNumber = null;
	public Integer bookedNumber = null;
	
	Element source = null;
	public ApartmentRecord (Element htmlSource){
		this.source = htmlSource;
		setApartmentType();
		setCarpetArea();		
		setNumAparments();

		setNumBookedApartments();
	}
	
	
	public <T> T getValue(Element element, Class<?> returnType){
		String text = element.text();
//		Object o = (T)new Object();
		if (returnType==Integer.class){
			return (T)(Object)Integer.parseInt(text);
		} 
		else 
			return (T)text;
		
	}
 	public void setApartmentType() {
//		this.buildingNo = Integer.parseInt(source.getElementsByTag("td").get(0).text());
 		this.apartmentType = getValue(source.getElementsByTag("td").get(1), String.class);
	}
	
 	private void setCarpetArea() {
		this.carpetArea = getValue(source.getElementsByTag("td").get(2), String.class);		
	}
	
	private void setNumAparments() {
		this.totalNumber = getValue(source.getElementsByTag("td").get(3), Integer.class);
		
	}
	
	private void setNumBookedApartments() {
		this.bookedNumber = getValue(source.getElementsByTag("td").get(4), Integer.class);
		
	}



	
}

