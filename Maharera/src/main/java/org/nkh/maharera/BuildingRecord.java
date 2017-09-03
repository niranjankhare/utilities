package org.nkh.maharera;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BuildingRecord {
	Element source = null;
	List<ApartmentRecord> Apartments = new ArrayList<ApartmentRecord>();
	
	public BuildingRecord (Element htmlSource){
//		Element b1 = p.get(0).parent().nextElementSibling(); // Building 1 data
//		Element a1 = b1.nextElementSibling();
//		Element b2 = p.get(1).parent().nextElementSibling(); // Building 2 data
//		Element a2 = b2.nextElementSibling();
		this.source = htmlSource.nextElementSibling();
		setApartments (source.nextElementSibling());
	}
	
	private void setApartments (Element htmlSource){
		Elements apartments = htmlSource.getElementsByTag("tbody").get(0).getElementsByTag("tr");
		for (int i=1;i<apartments.size(); i++){
			this.Apartments.add(new ApartmentRecord(apartments.get(i)));
		}
	}
	
}
