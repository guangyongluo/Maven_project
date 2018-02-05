package com.lwei.dom.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
		read(doc);
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

}
