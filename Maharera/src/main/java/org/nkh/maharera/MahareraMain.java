package org.nkh.maharera;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nkh.utils.Parser;
import org.sql2o.Sql2o;



public class MahareraMain {

	public static void main(String[] args) {
		
		
		try {
			
			URL dbfile = MahareraMain.class.getClassLoader().getResource("maharera.mv.db");
			String dbFilePath = new File (dbfile.toURI()).getParent()+"/";
			String division = "Amravati";
			String district = "Amravati";
			Integer demoNumber = 2;
			Elements recordLinks = Parser.fetch(division, district);
			String baseUrl = "https://maharerait.mahaonline.gov.in";
			List<MahareraRecord> listRecords = new ArrayList<MahareraRecord>();
			Integer i = 0;
			for (Element e:recordLinks){
				String absUrl = e.absUrl("href");
//				listRecords.add(new MahareraRecord(absUrl));
				listRecords.add(new MahareraRecord("RecordCompany.html"));
				System.out.println("Adding record number:" + ++i);
				if (i>=demoNumber)
					break;
			}
			Sql2o db =  new Sql2o ("jdbc:h2:"+dbFilePath+"maharera","sa","");
			for (MahareraRecord record : listRecords){
				record.commitToDB(db);
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
