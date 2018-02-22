package com.lwei.dom.execrise;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StudentService {
	
	private static String XML_Path = "src/main/resources/com/lwei/dom/execrise/students.xml";

	public Document initialDoc() throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(XML_Path);
	}
	
	public void displayALLScore() throws Exception{
		Document doc = initialDoc();
		NodeList nodes = doc.getElementsByTagName("student");
		for(int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element)nodes.item(i);
			System.out.print("学号：\t" + element.getAttribute("id") + "\t");
			System.out.print("名字：\t" + getFirstElementContextValue(element, "name") + "\t");
			System.out.print("Java成绩：\t" + getFirstElementContextValue(element, "java") + "\t");
			System.out.print("Oracle成绩：\t" + getFirstElementContextValue(element, "oracle") + "\t");
			System.out.println("VB成绩：\t" + getFirstElementContextValue(element, "vb")+ "\t");
		}
	}
	
	public void displayStudentInfoById(int id) throws Exception{
		Document doc = initialDoc();
		NodeList nodes = doc.getElementsByTagName("student");
		for(int i = 0; i < nodes.getLength(); i++) {
	        Element element = (Element)nodes.item(i);
	        if(Integer.parseInt(element.getAttribute("id")) == id) {
	        	System.out.print("学号：\t" + element.getAttribute("id") + "\t");
				System.out.print("名字：\t" + getFirstElementContextValue(element, "name") + "\t");
				System.out.print("Java成绩：\t" + getFirstElementContextValue(element, "java") + "\t");
				System.out.print("Oracle成绩：\t" + getFirstElementContextValue(element, "oracle") + "\t");
				System.out.println("VB成绩：\t" + getFirstElementContextValue(element, "vb")+ "\t");
	        }
		}
	}
	
	public void addStudentInfo(Student student) throws Exception{
		Document doc = initialDoc();
		Node node = doc.getElementsByTagName("students").item(0);
		Element element = doc.createElement("student");
		element.setAttribute("id", Integer.toString(student.getId()));
		Element name = doc.createElement("name");
		name.setTextContent(student.getName());
		element.appendChild(name);
		Element course = doc.createElement("course");
		Element javaScore = doc.createElement("java");
		javaScore.setTextContent(Integer.toString(student.getJavaScore()));
		Element oracleScore = doc.createElement("oracle");
		oracleScore.setTextContent(Integer.toBinaryString(student.getOracelScore()));
		Element vbScore = doc.createElement("vb");
		vbScore.setTextContent(Integer.toString(student.getVbScore()));
		course.appendChild(javaScore);
		course.appendChild(oracleScore);
		course.appendChild(vbScore);
		node.appendChild(element);
		updateXML(doc);
	}
	
	public String getFirstElementContextValue(Element element, String name) {
		return element.getElementsByTagName(name).item(0).getTextContent();
	}
	
	public void updateXML(Document doc) throws Exception {
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.transform(new DOMSource(doc), new StreamResult(XML_Path));
	}
}
