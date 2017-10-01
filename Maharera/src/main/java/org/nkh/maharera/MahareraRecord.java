package org.nkh.maharera;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nkh.utils.Parser;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.xml.sax.SAXException;

public class MahareraRecord {

	private URL remoteReference = null;
	private Document record = null;
	private Element divPInfo = null;
	private Element internalType = null;
	private Element divPromo = null;
	private Element divProject = null;
	private Element divBuilding = null;
	private String inferredType = "Individual";
	private String typeInfo = "";
	private String ownerName = "";
	private String projectName = "";
	private String pinCode = "";
	private String division = "";
	private String district = "";
	private String taluka = "";
	private String village = "";
	private String projectArea = "";
	private String projectType = "";
	private static Connection dbConnection = null;
	public List<BuildingRecord> Buildings = new ArrayList<BuildingRecord>();
	

	public MahareraRecord(String absoluteURL) {
		try {
//			absoluteURL="RecordIndividual.html";
			this.remoteReference = new URL (absoluteURL);
			this.record = Parser.parse(absoluteURL, true);
//			this.record = Parser.parse(absoluteURL, false);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.divPInfo = record.getElementById("DivPInfo");
		setInferredType();
		this.divPromo = record.getElementById("DivPromo");
		this.divProject = divPromo.getElementById("DivProject");
		this.divBuilding = divPromo.getElementById("DivBuilding");
		setTypeInfo();
		setOwner();
		setProjectName();
		setDivisionDistrict();
		setPinCodeAreaSqM();
		setTalukaVillage();
		setProjectType(record);
		setBuildings(divBuilding);
	}

	private void setInferredType() {
		this.internalType = divPInfo.getElementById("fldind"); 
		if (internalType == null)
			internalType = divPInfo.getElementById("fldFirm");
		this.inferredType = internalType.getElementsByTag("h2").get(0).text();
	}

	private void setTypeInfo() {
		Element info = divPInfo.getElementById("divInfoType").getElementsByClass("form-group").get(0);
		this.typeInfo = info.children().get(1).text();		
	}

	private void setOwner() {
		Element info = internalType.getElementsByClass("x_content").get(0).getElementsByClass("form-group").get(0);
		this.ownerName = info.children().get(1).text();
		
		if (typeInfo.equalsIgnoreCase("Individual")) {
			this.ownerName = this.ownerName + " "
			 + info.children().get(3).text();
		}
		
	}

	private void setProjectName() {
		Element info = divProject.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(0).getElementsByClass("form-group").get(0);
		this.projectName = info.children().get(1).text();
	}

	private void setPinCodeAreaSqM() {
		Element info = divProject.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(0).getElementsByClass("form-group").get(9);

		this.pinCode = info.children().get(1).text();
		this.projectArea = info.children().get(3).text();
	}

	private void setDivisionDistrict() {
		Element info = divProject.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(0).getElementsByClass("form-group").get(7);

		Element div = info.children().get(1);
		this.division = div.text();

		Element dist = info.children().get(3);
		this.district = dist.text();
	}

	// public static String getDistrict(Document doc) {
	// return null;
	// }

	private void setTalukaVillage() {
		Element info = divProject.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(0).getElementsByClass("form-group").get(8);
		Element tal = info.children().get(1);
		this.taluka = tal.text();
		Element vil = info.children().get(3);
		this.village = vil.text();
	}


	private void setProjectType(Document doc) {
		Element info = divProject.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(0).getElementsByClass("form-group").get(2);
		this.projectType = info.children().get(3).text();
	}

	


	public List<BuildingRecord> setBuildings(Element divBuilding2) {
		// TODO Auto-generated method stub
		Elements e = divBuilding2.getElementsByClass("table");
		Elements buildings = e.select("th:containsOwn(Project Name)");
		System.out.println();
		for (Element building: buildings){
			this.Buildings .add(new BuildingRecord(building.parent()));
		}
		return null;
	}
	/**
	 * Pin Code Division District Taluka Village Area(In sqmts) Project Type
	 */

	public java.sql.Connection commitToDB(Sql2o targetDb) {
		if (dbConnection == null)
			dbConnection = targetDb.open();
//		System.out.println("Commiting record to db");
		String querySET = "SET url='"+ this.remoteReference.toString()+"'"
				+ ", recordType='"+this.inferredType+"'"
				+ ", promotorName='"+this.ownerName+"'"
				+ ", projectName='"+this.projectName+"'"
				+ ", pinCode='"+this.pinCode+"'"
				+ ", division='"+this.division+"'"
				+ ", district='"+this.district+"'"
				+ ", taluka='"+this.taluka+"'"
				+ ", village='"+this.village+"'"
				+ ", area='"+this.projectArea+"'"
				+ ", projectType='"+this.projectType+"'";
		for (BuildingRecord building:Buildings){
			querySET = querySET 
					+ ", buildingNo='"+building.buildingNo +"'"
					+ ", buildingProjectName='"+ building.buildingProjectName + "'"
					+ ", buildingName='"+ building.buildingName + "'"
					+ ", numbasements='"+ building.numBasements + "'"
					+ ", numPlinths='"+ building.numPlinths + "'"
					+ ", numPodium='"+ building.numPodiums + "'"
					+ ", numSlabs='"+ building.numSupSlabs + "'"
					+ ", numStilts ='"+ building.numStilts + "'"
					+ ", numOpenParking='"+ building.numOpenParkings + "'"
					+ ", numClosedParking='"+ building.numClosedParkings + "'";
			for (ApartmentRecord apartment: building.Apartments){
				querySET = querySET 
						+ ", apartmentTypeNo='"+apartment.apartmentTypeNo +"'"
						+ ", apartmentType='"+apartment.apartmentType +"'"
						+ ", carpetArea='"+apartment.carpetArea +"'"
						+ ", numApartment='"+apartment.totalNumber +"'"
						+ ", numBookedApartment='"+apartment.bookedNumber +"'";
				Object o = dbConnection.createQuery("INSERT INTO MAHARERA "+querySET+";")
				.executeUpdate();
				System.out.println("Commiting record to db DONE");
				
			} // for each apartment
		} // for each building
		return dbConnection.getJdbcConnection();
	} // commit to db		

}
