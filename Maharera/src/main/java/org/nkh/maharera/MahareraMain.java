package org.nkh.maharera;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nkh.utils.Parser;



public class MahareraMain {

	public static void main(String[] args) {
		
		
		try {
			String division = "Amravati";
			String district = "Amravati";
			Elements recordLinks = Parser.fetch(division, district);
			String baseUrl = "https://maharerait.mahaonline.gov.in";
			List<MahareraRecord> listRecords = new ArrayList<MahareraRecord>();
			for (Element e:recordLinks){
				String absUrl = e.absUrl("href");
				Document onlineRecord = Parser.parse(absUrl, true);
				listRecords.add(new MahareraRecord(onlineRecord));
			}
//			Document docCompany = Parser.parse("RecordCompany.html");
//			MahareraRecord company  = new MahareraRecord(docCompany);
//			Document docIndividual = Parser.parse("RecordIndividual.html");
//			MahareraRecord rec  = new MahareraRecord(docIndividual);
//			
//			listRecords.add(rec);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * TODO 
 * PART ONE
 * 1. Hit the search url (1. GET)
 * 2. get the list of Divisions, id number (PARSE)
 * 3. get list of districts for div (2. POST)
 * 4.Post the search (3. POST, btnSearch)
 * 5. Post Loop (
 * PARSE  LINKS,SAVE
 * POST, CUrrentPaGE)
 * DONE PART ONE
 * 
 *  PART TWO
 *  loop
 *  each url
 *  parse and save data
 *  REcursive data for buildings:
 *  https://maharerait.mahaonline.gov.in/PrintPreview/PrintPreview/UHJvamVjdElEPTM4MDYmRGl2aXNpb249NSZVc2VySUQ9Mzk3NzAmUm9sZUlEPTEmQXBwSUQ9MzY2NzkmQWN0aW9uPVNFQVJDSA%3d%3d
 */
}
