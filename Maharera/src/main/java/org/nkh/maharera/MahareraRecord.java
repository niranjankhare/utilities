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

	public List<BuildingRecord> Buildings = new ArrayList<BuildingRecord>();
	private Element rawType = null;
	private Element divProject = null;

	public MahareraRecord(Document source) {
		this.record = source;
		setInferredType();
		setTypeInfo();
		setOwner();
		setProjectName();
		setPinCodeAreaSqM();
		setDivisionDistrict();
		setTalukaVillage();
		setProjectType(record);
		this.Buildings = getBuildings(record);
	}

	private void setInferredType() {
		rawType = record.getElementById("fldind");
		if (rawType == null)
			rawType = record.getElementById("fldFirm");
	}

	private void setTypeInfo() {
		Element info = record.getElementById("divInfoType");
		Element y = info.getElementsByAttributeValue("for", "PersonalInfoModel_InfoTypeValue").get(0);
		this.typeInfo = y.parent().siblingElements().text();
	}

	private void setOwner() {
		String info = rawType.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(0)
				.getElementsByClass("row").get(0).text();
		this.ownerName = info;

	}

	private void setProjectName() {
		this.divProject = record.getElementById("DivProject");
		String info = divProject.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(0)
				.getElementsByClass("row").get(0).text();
		this.projectName = info;
	}

	private void setPinCodeAreaSqM() {
		Element rootElement = divProject.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(1)
				.getElementsByClass("row").get(7).getElementsByClass("form-group").get(0);

		Element pin = rootElement.children().get(1);
		this.pinCode = pin.text();

		Element area = rootElement.children().get(3);
		this.projectArea = area.text();
	}

	private void setDivisionDistrict() {
		Element rootElement = divProject.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(1)
				.getElementsByClass("row").get(5).getElementsByClass("form-group").get(0);

		Element div = rootElement.children().get(1);
		this.division = div.text();

		Element dist = rootElement.children().get(3);
		this.district = dist.text();
	}

	// public static String getDistrict(Document doc) {
	// return null;
	// }

	private void setTalukaVillage() {
		Element rootElement = divProject.getElementsByClass("x_panel").get(0).getElementsByClass("x_content").get(1)
				.getElementsByClass("row").get(6).getElementsByClass("form-group").get(0);

		Element tal = rootElement.children().get(1);
		this.taluka = tal.text();

		Element vil = rootElement.children().get(3);
		this.village = vil.text();
	}

	// public static String getVillage(Document doc) {
	// return null;
	// }

	// private void setProjectAreaSqM(Document doc) {
	// return null;
	// }

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
	 * Pin Code Division District Taluka Village Area(In sqmts) Project Type
	 */
}
