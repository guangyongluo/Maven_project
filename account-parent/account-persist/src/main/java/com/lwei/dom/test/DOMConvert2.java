package com.lwei.dom.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMConvert2 {
	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("src/main/resources/com/lwei/dom/test/students.xml");

			Element eltStu = doc.createElement("student");
			Element eltName = doc.createElement("name");
			Element eltAge = doc.createElement("age");

			Attr attr = doc.createAttribute("sn");
			attr.setValue("03");

			Text txtName = doc.createTextNode("王五");
			Text txtAge = doc.createTextNode("19");

			eltName.appendChild(txtName);
			eltAge.appendChild(txtAge);

			eltStu.appendChild(eltName);
			eltStu.appendChild(eltAge);

			eltStu.setAttributeNode(attr);

			Element eltRoot = doc.getDocumentElement();

			eltRoot.appendChild(eltStu);

			NodeList nl = doc.getElementsByTagName("student");

			Node nodeDel = (Element) nl.item(0);
			nodeDel.getParentNode().removeChild(nodeDel);

			Element eltChg = (Element) nl.item(0);
			Node nodeAgeChg = eltChg.getElementsByTagName("age").item(0);
			nodeAgeChg.getFirstChild().setNodeValue("22");

			// 利用文档节点创建一个DOM输入源
			DOMSource source = new DOMSource(doc);
			// 以converted.xml文件构造一个StreamResult对象，用于保存转换后结果。
			StreamResult result = new StreamResult(new File("src/main/resources/com/lwei/dom/test/converted.xml"));

			// 得到转换器工厂类的实例
			TransformerFactory tff = TransformerFactory.newInstance();
			// 创建一个新的转换器，用于执行恒等转换，
			// 即直接将DOM输入源的内容复制到结果文档中。
			Transformer tf = tff.newTransformer();
			// 执行转换
			tf.transform(source, result);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}