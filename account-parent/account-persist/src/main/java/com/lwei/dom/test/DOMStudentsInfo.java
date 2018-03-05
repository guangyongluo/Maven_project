package com.lwei.dom.test;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMStudentsInfo {
	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			File file = new File("src/main/resources/com/lwei/dom/test/students.xml");
			Document doc = db.parse(file);

			NodeList nl = doc.getElementsByTagName("student");

			int len = nl.getLength();
			for (int i = 0; i < len; i++) {
				Element eltStu = (Element) nl.item(i);
				Node eltName = eltStu.getElementsByTagName("name").item(0);
				Node eltAge = eltStu.getElementsByTagName("age").item(0);

				String name = eltName.getFirstChild().getNodeValue();
				String age = eltAge.getFirstChild().getNodeValue();

				System.out.print("ÐÕÃû£º");
				System.out.println(name);

				System.out.print("ÄêÁä£º");
				System.out.println(age);
				System.out.println("------------------------------------------");
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}