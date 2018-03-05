package com.lwei.dom4j.xpath.test;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XpathTest {

	public static void main(String[] args) throws Exception{
		SAXReader sreader = new SAXReader();
		
		Document doc = sreader.read("src/main/resources/com/lwei/dom4j/xpath/test/test.xml");
		
		List<Element> elements = doc.selectNodes("//BBB");
		
		System.out.println(elements.size());
	}
	
}
