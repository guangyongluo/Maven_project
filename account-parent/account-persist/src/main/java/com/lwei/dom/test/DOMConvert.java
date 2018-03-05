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

			// ------------------��ӽڵ�------------------
			// ������ʾһ��ѧ����Ϣ�ĸ�Ԫ�ؽڵ�
			Element eltStu = doc.createElement("student");
			Element eltName = doc.createElement("name");
			Element eltAge = doc.createElement("age");

			// ����<student>Ԫ�ص�sn���Խڵ�
			Attr attr = doc.createAttribute("sn");
			attr.setValue("03");

			// ��������ѧ����Ϣ���ı��ڵ�
			Text txtName = doc.createTextNode("����");
			Text txtAge = doc.createTextNode("19");

			// ���ı��ڵ����Ϊ��Ӧ��Ԫ�ؽڵ���ӽڵ�
			eltName.appendChild(txtName);
			eltAge.appendChild(txtAge);

			// ��name��age�ڵ����Ϊstudent�ڵ���ӽڵ�
			eltStu.appendChild(eltName);
			eltStu.appendChild(eltAge);

			// Ϊ<student>Ԫ�����sn���Խڵ�
			eltStu.setAttributeNode(attr);

			// �õ�XML�ĵ��ĸ�Ԫ�ء�
			Element eltRoot = doc.getDocumentElement();

			// ��student�ڵ����Ϊ��Ԫ�ص��ӽڵ�
			eltRoot.appendChild(eltStu);

			NodeList nl = doc.getElementsByTagName("student");

			// ------------------ɾ���ڵ�------------------
			Node nodeDel = nl.item(0);
			nodeDel.getParentNode().removeChild(nodeDel);

			// ------------------�޸Ľڵ�------------------
			// ע�⣺NodeList�����Ƕ�̬�ģ�����ǰ��ɾ���ڵ�Ĳ�����Ӱ�쵽NodeList����
			// NodeList�еĽڵ��������½������У���ʱ������Ϊ0�Ľڵ�����ǰ�ڵ�
			// �б�������Ϊ1�Ľڵ㡣
			Element eltChg = (Element) nl.item(0);
			Node nodeAgeChg = eltChg.getElementsByTagName("age").item(0);
			nodeAgeChg.getFirstChild().setNodeValue("22");

			// ����޸ĺ��ѧ����Ϣ��
			for (int i = 0; i < nl.getLength(); i++) {
				Element elt = (Element) nl.item(i);
				System.out.println("��ţ�" + elt.getAttribute("sn"));

				Node nodeName = elt.getElementsByTagName("name").item(0);
				Node nodeAge = elt.getElementsByTagName("age").item(0);

				String name = nodeName.getFirstChild().getNodeValue();
				String age = nodeAge.getFirstChild().getNodeValue();

				System.out.print("������");
				System.out.println(name);

				System.out.print("���䣺");
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