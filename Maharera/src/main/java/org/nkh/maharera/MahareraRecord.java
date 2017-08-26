package org.nkh.maharera;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.nkh.utils.Parser;

public class MahareraRecord {

	private URL remoteReference = null;
	private Document record = null;
	private String internalType = "Individual";
	private String typeInfo= null;
	
	public List<BuildingRecord> Buildings = new ArrayList<BuildingRecord>(); 
	public MahareraRecord (Document source ){
		this.record = source;
		this.internalType = Parser.getTypeInfo(record);
		this.typeInfo = Parser.getTypeInfo(record);
	}
}
