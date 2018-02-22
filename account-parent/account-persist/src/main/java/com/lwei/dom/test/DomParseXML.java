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
		NodeList nodeList = doc.getElementsByTagName("学生");
		for(int i = 0; i < nodeList.getLength(); i++) {
			Element student = (Element)nodeList.item(i);
			Node name = student.getElementsByTagName("名字").item(0);
			System.out.println("学生" + (i+1) + "姓名：" + name.getTextContent());
			Node age = student.getElementsByTagName("年龄").item(0);
			System.out.println("学生" + (i+1) + "年龄：" + age.getTextContent());
			Node introduce = student.getElementsByTagName("介绍").item(0);
			System.out.println("学生" + (i+1) + "介绍：" + introduce.getTextContent());
			
		}
	}
	
	public static void list(Node node) {
		if(node.getNodeType() == node.ELEMENT_NODE)
		    System.out.println("名字 ：" + node.getNodeName());
		
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			list(n);
		}
	}
	
	public static void add(Document doc) throws Exception{
		Element newStudent = doc.createElement("学生");
		newStudent.setAttribute("性别", "男");
		doc.getDocumentElement().appendChild(newStudent);
		
		Element stuName = doc.createElement("姓名");
		stuName.setTextContent("小明");
		newStudent.appendChild(stuName);
		
		Element stuAge = doc.createElement("年龄");
		stuAge.setTextContent("33");
		newStudent.appendChild(stuAge);
		
		Element stuIntro = doc.createElement("介绍");
		stuIntro.setTextContent("是一个好孩子");
		newStudent.appendChild(stuIntro);
		
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.transform(new DOMSource(doc), new StreamResult("src/main/resources/com/lwei/dom/test/classes.xml"));
		
	}
	
	public static void del(Document doc) throws Exception{
		NodeList nodeList = doc.getElementsByTagName("学生");
		doc.getDocumentElement().removeChild(nodeList.item(1));
		
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.transform(new DOMSource(doc), new StreamResult("src/main/resources/com/lwei/dom/test/classes.xml"));
	}
	
	public static void update(Document doc) throws Exception{
		Element student = (Element)doc.getElementsByTagName("学生").item(0);
		student.setAttribute("性别", "男");
		
		Element name = (Element)student.getElementsByTagName("名字").item(0);
		name.setTextContent("罗葳");
		
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.transform(new DOMSource(doc), new StreamResult("src/main/resources/com/lwei/dom/test/classes.xml"));
	}

}
