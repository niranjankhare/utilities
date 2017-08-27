package org.nkh.maharera;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MahareraRecord {

	private URL remoteReference = null;
	private Document record = null;
	private String inferredType = "Individual";
	private String typeInfo= "";
	private String ownerName= "";
	private String projectName= "";
	private String pinCode = "";
	private String division= "";
	private String district= "";
	private String taluka= "";
	private String village= "";
	private String projectArea= "";
	private String projectType= "";
		
	public List<BuildingRecord> Buildings = new ArrayList<BuildingRecord>();
	private Element rawType = null;
	private Element divProject = null;
	
	
	public MahareraRecord (Document source ){
		this.record = source;
		setInferredType();
		setTypeInfo();
		setOwner();
		setProjectName();
		setPinCodeAreaSqM();
		setDivisionDistrict();
		setTalukaVillage();
		setProjectType(record);
		this.Buildings = getBuildings (record);
	}
	
	
	 private void setInferredType() {
		rawType  = record.getElementById("fldind");
		if (rawType == null )
			rawType = record.getElementById("fldFirm");		
	}

	 private void setTypeInfo() {
		Element typeInfo = record.getElementById("divInfoType");
		Element y = typeInfo.getElementsByAttributeValue("for", "PersonalInfoModel_InfoTypeValue").get(0);
//		return y.parent().siblingElements().text();
	}

	 private void setOwner() {
		 String info = rawType.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(0).getElementsByClass("row").get(0).text();
		ownerName= info;

	}
	
	 private void setProjectName() {
		divProject   = record.getElementById("DivProject");
		String info = divProject.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(0).getElementsByClass("row").get(0).text();
		
	}
	 

	 private void setPinCodeAreaSqM() {
		Element rootElement = divProject.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(1).getElementsByClass("row").get(7);
		String pinCode = rootElement.text();
		
	}

	 private void setDivisionDistrict() {
		
	}

//	public static String getDistrict(Document doc) {
//		return null;
//	}

	 private void setTalukaVillage() {
		
	}

//	public static String getVillage(Document doc) {
//		return null;
//	}

//	 private void setProjectAreaSqM(Document doc) {
//		return null;
//	}

	 private void setProjectType(Document doc) {
		
	}

	public static String buildingNo() {
		return null;
	}

	public static String buildingName() {
		return null;
	}

	public static String numBasements() {
		return null;
	}

	public static String numPlinths() {
		return null;
	}

	public static String numPodiums() {
		return null;
	}

	public static String numSupStructSlabs() {
		return null;
	}

	public static String numStilts() {
		return null;
	}

	public static String numOpenParking() {
		return null;
	}

	public static String numClosedParking() {
		return null;
	}

	public static String apartmentType() {
		return null;
	}

	public static String carpetAreaSqM() {
		return null;
	}

	public static String numApartments() {
		return null;
	}

	public static String bookedApartments() {
		return null;
	}

	public static List<BuildingRecord> getBuildings(Document record) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *	Pin Code	Division	District	Taluka	Village	Area(In sqmts)	Project Type
	 */
}
