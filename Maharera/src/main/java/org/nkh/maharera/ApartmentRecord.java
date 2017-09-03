package org.nkh.maharera;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class ApartmentRecord {
	Element source = null;
	public ApartmentRecord (Element htmlSource){
		this.source = htmlSource;
	}
}
