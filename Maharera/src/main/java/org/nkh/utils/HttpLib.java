package org.nkh.utils;

import java.io.*;
import java.net.*;

public class HttpLib {

	public static String doPost(String ulrEndPoint, String postBody) throws Exception {
		// "http://localhost:9292/Iitaccess/testpost.jsp"
		URL url = new URL(ulrEndPoint);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		PrintWriter out = new PrintWriter(connection.getOutputStream());
		// String name = "name="+URLEncoder.encode("myname", "UTF-8");
		// String email = "email="+URLEncoder.encode("email@email.com",
		// "UTF-8");
		out.println("query=" + postBody);
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = "", resp = "";

		while ((line = in.readLine()) != null) {
			resp = resp + line;
		}
		in.close();
		return resp;
	}

	public static String doGet(String ulrEndPoint) throws Exception {

		String url = ulrEndPoint;
		String charset = "UTF-8"; // Or in Java 7 and later, use the constant:
									// java.nio.charset.StandardCharsets.UTF_8.name()

		URLConnection connection = new URL(url).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = "", resp = "";

		while ((line = in.readLine()) != null) {
			resp = resp + line + "\n";
		}
		return resp;
	}

	public static String doPostEmpty(String ulrEndPoint) throws Exception {
		// "http://localhost:9292/Iitaccess/testpost.jsp"
		URL url = new URL(ulrEndPoint);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		PrintWriter out = new PrintWriter(connection.getOutputStream());
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = "", resp = "";

		while ((line = in.readLine()) != null) {
			resp = resp + line;
		}
		return resp;
	}
//
//	public static String myjavaclientPostJson(String ulrEndPoint, String jsonContent) throws Exception {
//		// "http://localhost:9292/Iitaccess/testpost.jsp"
//		URL url = new URL(ulrEndPoint);
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		connection.setRequestMethod("POST");
//		connection.setDoOutput(true);
//		connection.setRequestProperty("Accept", "application/json");
//		connection.setRequestProperty("Content-Type", "application/json");
//
//		PrintWriter out = new PrintWriter(connection.getOutputStream());
//		out.write(jsonContent);
//		out.close();
//
//		int responseCode = connection.getResponseCode();
//		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//		String line = "", resp = "";
//
//		while ((line = in.readLine()) != null) {
//			resp = resp + line;
//		}
//		out.close();
//		return resp;
//	}

}
