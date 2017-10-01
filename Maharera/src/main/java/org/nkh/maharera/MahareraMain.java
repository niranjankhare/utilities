package org.nkh.maharera;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.Csv;
import org.h2.tools.SimpleResultSet;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nkh.utils.Parser;
import org.sql2o.Sql2o;

public class MahareraMain {

	public static void main(String[] args) {
		String division = args[0];
		String district = args[1];
		Integer demoNumber = Integer.parseInt(args[2]);
		String dbFilePath = "";
		try {

			URL dbfile = MahareraMain.class.getClassLoader().getResource("maharera.mv.db");
			dbFilePath = new File(dbfile.toURI()).getParent() + "/";
			Elements recordLinks = Parser.fetch(division, district);
			String baseUrl = "https://maharerait.mahaonline.gov.in";
			List<MahareraRecord> listRecords = new ArrayList<MahareraRecord>();
			Integer i = 0;
			for (Element e : recordLinks) {
				String absUrl = e.absUrl("href");
				listRecords.add(new MahareraRecord(absUrl));
				// listRecords.add(new MahareraRecord("RecordCompany.html"));
				System.out.println("Adding record number:" + ++i);
				if (i >= demoNumber)
					break;
			}
			Sql2o db = new Sql2o("jdbc:h2:" + dbFilePath + "maharera", "sa", "");
			Connection openConnection = null;
			for (MahareraRecord record : listRecords) {

				try {
					openConnection = record.commitToDB(db);

				} catch (Exception r) {
					System.out.println("Error writting record to db!");
					r.printStackTrace();
					if (openConnection.isValid(2)) {
						try {
							openConnection.close();
						} catch (Exception e) {
							System.out.println("Warning: Error closing connection, maybe its alreasdy closed!");
						}
					}
				}
			} // for
			if (openConnection.isValid(2)) {
				new Csv().write(openConnection, "./testoutput.csv", "SELECT * FROM MAHARERA;", "UTF-8");
			}
			System.out.println("done");
			openConnection.clearWarnings();
			openConnection.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}	
}
