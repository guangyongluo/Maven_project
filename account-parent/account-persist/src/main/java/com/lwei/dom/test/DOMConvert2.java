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

			Text txtName = doc.createTextNode("����");
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

			// �����ĵ��ڵ㴴��һ��DOM����Դ
			DOMSource source = new DOMSource(doc);
			// ��converted.xml�ļ�����һ��StreamResult�������ڱ���ת��������
			StreamResult result = new StreamResult(new File("src/main/resources/com/lwei/dom/test/converted.xml"));

			// �õ�ת�����������ʵ��
			TransformerFactory tff = TransformerFactory.newInstance();
			// ����һ���µ�ת����������ִ�к��ת����
			// ��ֱ�ӽ�DOM����Դ�����ݸ��Ƶ�����ĵ��С�
			Transformer tf = tff.newTransformer();
			// ִ��ת��
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