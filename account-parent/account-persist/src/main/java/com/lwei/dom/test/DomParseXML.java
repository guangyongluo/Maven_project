package com.lwei.dom.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomParseXML {

	public static void main(String[] args) throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder dBuilder = dbf.newDocumentBuilder();
        
		Document doc = dBuilder.parse("src/main/resources/com/lwei/dom/test/classes.xml");
		
		//list(doc);
		//read(doc);
		//add(doc);
		
		//del(doc);
		update(doc);
	}
	
	public static void read(Document doc) {
		NodeList nodeList = doc.getElementsByTagName("ѧ��");
		for(int i = 0; i < nodeList.getLength(); i++) {
			Element student = (Element)nodeList.item(i);
			Node name = student.getElementsByTagName("����").item(0);
			System.out.println("ѧ��" + (i+1) + "������" + name.getTextContent());
			Node age = student.getElementsByTagName("����").item(0);
			System.out.println("ѧ��" + (i+1) + "���䣺" + age.getTextContent());
			Node introduce = student.getElementsByTagName("����").item(0);
			System.out.println("ѧ��" + (i+1) + "���ܣ�" + introduce.getTextContent());
			
		}
	}
	
	public static void list(Node node) {
		if(node.getNodeType() == node.ELEMENT_NODE)
		    System.out.println("���� ��" + node.getNodeName());
		
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			list(n);
		}
	}
	
	public static void add(Document doc) throws Exception{
		Element newStudent = doc.createElement("ѧ��");
		newStudent.setAttribute("�Ա�", "��");
		doc.getDocumentElement().appendChild(newStudent);
		
		Element stuName = doc.createElement("����");
		stuName.setTextContent("С��");
		newStudent.appendChild(stuName);
		
		Element stuAge = doc.createElement("����");
		stuAge.setTextContent("33");
		newStudent.appendChild(stuAge);
		
		Element stuIntro = doc.createElement("����");
		stuIntro.setTextContent("��һ���ú���");
		newStudent.appendChild(stuIntro);
		
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.transform(new DOMSource(doc), new StreamResult("src/main/resources/com/lwei/dom/test/classes.xml"));
		
	}
	
	public static void del(Document doc) throws Exception{
		NodeList nodeList = doc.getElementsByTagName("ѧ��");
		doc.getDocumentElement().removeChild(nodeList.item(1));
		
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.transform(new DOMSource(doc), new StreamResult("src/main/resources/com/lwei/dom/test/classes.xml"));
	}
	
	public static void update(Document doc) throws Exception{
		Element student = (Element)doc.getElementsByTagName("ѧ��").item(0);
		student.setAttribute("�Ա�", "��");
		
		Element name = (Element)student.getElementsByTagName("����").item(0);
		name.setTextContent("����");
		
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.transform(new DOMSource(doc), new StreamResult("src/main/resources/com/lwei/dom/test/classes.xml"));
	}

}
