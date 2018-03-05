package com.lwei.sax.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class SAXPrinter extends DefaultHandler {
	public void startDocument() throws SAXException {
		// ���XML������
		System.out.println("<?xml version='1.0' encoding='GBK'?>");
	}

	public void processingInstruction(String target, String data) throws SAXException {
		// ����ĵ��еĴ���ָ�
		System.out.println("<?" + target + " " + data + "?>");
	}

	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		// ���Ԫ�صĿ�ʼ��Ǽ������ԡ�
		System.out.print("<" + qName);
		int len = attrs.getLength();
		for (int i = 0; i < len; i++) {
			System.out.print(" ");
			System.out.print(attrs.getQName(i));
			System.out.print("=\"");
			System.out.print(attrs.getValue(i));
			System.out.print("\"");
		}
		System.out.print(">");
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		// ���Ԫ�ص��ַ��������ݡ�
		System.out.print(new String(ch, start, length));
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		// ���Ԫ�صĽ�����ǡ�
		System.out.print("</" + qName + ">");
	}

	public static void main(String[] args) {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = null;
		try {
			sp = spf.newSAXParser();
			File file = new File("src/main/resources/com/lwei/dom/test/students.xml");
			sp.parse(file, new SAXPrinter());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}