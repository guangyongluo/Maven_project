package com.lwei.dom.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMConvert {
	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("src/main/resources/com/lwei/dom/test/students.xml");

			// ------------------添加节点------------------
			// 创建表示一个学生信息的各元素节点
			Element eltStu = doc.createElement("student");
			Element eltName = doc.createElement("name");
			Element eltAge = doc.createElement("age");

			// 创建<student>元素的sn属性节点
			Attr attr = doc.createAttribute("sn");
			attr.setValue("03");

			// 创建代表学生信息的文本节点
			Text txtName = doc.createTextNode("王五");
			Text txtAge = doc.createTextNode("19");

			// 将文本节点添加为对应的元素节点的子节点
			eltName.appendChild(txtName);
			eltAge.appendChild(txtAge);

			// 将name和age节点添加为student节点的子节点
			eltStu.appendChild(eltName);
			eltStu.appendChild(eltAge);

			// 为<student>元素添加sn属性节点
			eltStu.setAttributeNode(attr);

			// 得到XML文档的根元素。
			Element eltRoot = doc.getDocumentElement();

			// 将student节点添加为根元素的子节点
			eltRoot.appendChild(eltStu);

			NodeList nl = doc.getElementsByTagName("student");

			// ------------------删除节点------------------
			Node nodeDel = nl.item(0);
			nodeDel.getParentNode().removeChild(nodeDel);

			// ------------------修改节点------------------
			// 注意：NodeList对象是动态的，所以前面删除节点的操作会影响到NodeList对象，
			// NodeList中的节点对象会重新进行排列，此时，索引为0的节点是先前节点
			// 列表中索引为1的节点。
			Element eltChg = (Element) nl.item(0);
			Node nodeAgeChg = eltChg.getElementsByTagName("age").item(0);
			nodeAgeChg.getFirstChild().setNodeValue("22");

			// 输出修改后的学生信息。
			for (int i = 0; i < nl.getLength(); i++) {
				Element elt = (Element) nl.item(i);
				System.out.println("编号：" + elt.getAttribute("sn"));

				Node nodeName = elt.getElementsByTagName("name").item(0);
				Node nodeAge = elt.getElementsByTagName("age").item(0);

				String name = nodeName.getFirstChild().getNodeValue();
				String age = nodeAge.getFirstChild().getNodeValue();

				System.out.print("姓名：");
				System.out.println(name);

				System.out.print("年龄：");
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