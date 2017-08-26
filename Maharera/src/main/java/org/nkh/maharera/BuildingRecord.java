package org.nkh.maharera;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BuildingRecord {

	List<ApartmentRecord> Apartments = new ArrayList<ApartmentRecord>();
	
	public BuildingRecord (Element source){
		
		this.Apartments = null;
	}
	
}
