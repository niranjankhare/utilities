package org.nkh.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nkh.maharera.District;
import org.xml.sax.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Parser {

	public static Document parse(String htmlResponse, Boolean isOnline) throws ParserConfigurationException, IOException, SAXException {
		Document doc  = null;
		if (isOnline){
			doc = Jsoup.connect(htmlResponse).get();
	
		} else {
			URL is = Parser.class.getClassLoader().getResource(htmlResponse);
			File input = null;
			try {
				input = new File(is.toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		doc = Jsoup.parse(input, "UTF-8");
		}
		return doc;
	}

	public static String getData(Document doc) {
		String info = doc.getElementById("DivProject").getElementsByClass("x_panel").get(0)
				.getElementsByClass("x_content").get(1).getElementsByClass("row").get(6).text();
		return info;
	}

	public static Elements fetch(String div, String dist) {
		String baseUrl = "https://maharerait.mahaonline.gov.in";
		String searchBaseURL = baseUrl+"/searchlist/search";
		String districtURL = baseUrl+"/Searchlist/GetDistrict";
		String searchPost = baseUrl+"/SearchList/Search";

		Connection.Response step1, step2, step3, step4;
		Document docStep1, docStep2, docStep3, docStep4;
		try {
			step1 = Jsoup.connect(searchBaseURL).method(Connection.Method.GET).execute();
			docStep1 = step1.parse();
			Elements divOptions = docStep1.getElementById("divDivision").getElementsByTag("option");
			Element division = findElementByText(div, divOptions);
			String DivID = division.attr("value");
			System.out.println("Got DivisionID" + DivID);
			step2 = Jsoup.connect(districtURL).method(Method.POST).header("content-type", "application/json")
					.ignoreContentType(true).requestBody("{\"DivID\":" + DivID + "}").execute();// {"DivID":1}
			docStep2 = step2.parse();
			String jsonBody = step2.body();
			List<District> districts = parseRequest(jsonBody);

			Integer distID = getDisctricID(districts, dist);
			step3 = Jsoup.connect(searchPost).method(Method.POST).ignoreContentType(true)
					.header("content-type", "application/x-www-form-urlencoded")
					.data("Type", "Promoter")
					.data("Division", DivID.toString())
					.data("District", distID.toString())
					.data("ID", "0")
					.data("pageTraverse", "1")
					.data("btnSearch", "Search")
					.execute();
			Document searchOne = step3.parse();
			String TotalRecords = searchOne.getElementById("TotalRecords").val();
			String TotalPages = searchOne.getElementById("TotalPages").val();
			Elements links = getLinks (searchOne);
			for (Integer i = 1; i < Integer.parseInt(TotalPages); i++){
				step3 = Jsoup.connect(searchPost).method(Method.POST).ignoreContentType(true)
						.header("content-type", "application/x-www-form-urlencoded")
						.data("Type", "Promoter")
						.data("Division", DivID.toString())
						.data("District", distID.toString())
						.data("ID", "0")
						.data("pageTraverse", "3")
						.data("CurrentPage", String.valueOf(i))
						.execute();
				Document searchTwo = step3.parse();
				links.addAll(getLinks (searchTwo));
			}
			System.out.println(links.size());
			System.out.println(links.size());
			return links;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return new Elements();
	}

	public static Element findElementByText(String text, Elements elements) {

		for (Element element : elements) {
			if (element.text().equalsIgnoreCase(text))
				return element;
		}
		return null;
	}

	public static Integer getDisctricID(List<District> searchList, String districtName) {

		for (District element : searchList) {
			if (element.Text.equalsIgnoreCase(districtName))
				return element.ID;
		}
		return null;
	}

	public static List<District> parseRequest(String requestJson) {
		Object returnObj = null;

		try {
			returnObj = new Gson().fromJson(requestJson, new TypeToken<List<District>>() {
			}.getType());
		} catch (Exception e) {
			returnObj = new Gson().fromJson(requestJson, Object.class);
		}

		return (List<District>) returnObj;
	}

	 public static Elements getLinks (Document document){
		 Element records = document.getElementById("DivBind");
			Element tableData = records.getElementsByTag("table").get(0).getElementsByTag("tbody").get(0);

			Elements links = tableData.getElementsByAttributeValueStarting("href", "/PrintPreview/PrintPreview");
			return links;
	 }
	/**
	 * Carpet Area (in Sqmts) Number of Apartment Number of Booked Apartment
	 */

}
