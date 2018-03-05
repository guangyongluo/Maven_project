package com.lwei.sax.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class ErrorProcessor extends DefaultHandler {
	public void warning(SAXParseException ex) throws SAXException {
		System.err.println("[Warning] " + getLocationString(ex) + ": " + ex.getMessage());
	}

	public void error(SAXParseException ex) throws SAXException {
		System.err.println("[Error] " + getLocationString(ex) + ": " + ex.getMessage());
	}

	public void fatalError(SAXParseException ex) throws SAXException {
		System.err.println("[Fatal Error] " + getLocationString(ex) + ": " + ex.getMessage());
	}

	/**
	 * ��ȡ���´�����߾�����ı�����λ�õ��кź��кš� �����ʵ���������󣬻���ȡ���Ĺ�����ʶ����ϵͳ��ʶ����
	 */
	private String getLocationString(SAXParseException ex) {
		StringBuffer str = new StringBuffer();

		String publicId = ex.getPublicId();
		if (publicId != null) {
			str.append(publicId);
			str.append(" ");
		}

		String systemId = ex.getSystemId();
		if (systemId != null) {
			str.append(systemId);
			str.append(':');
		}

		str.append(ex.getLineNumber());
		str.append(':');
		str.append(ex.getColumnNumber());

		return str.toString();
	}

	/**
	 * ���Ԫ�صĽ�����ǣ��Ա��ڲ鿴��ͬ���͵Ĵ�����ĵ�������Ӱ�졣
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("</" + qName + ">");
	}

	public static void main(String[] args) {
		try {
			// ����XMLReaderFactory�����࣬����XMLReader����
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			// �򿪽���������֤���ܡ�
			xmlReader.setFeature("http://xml.org/sax/features/validation", true);

			ErrorProcessor ep = new ErrorProcessor();
			xmlReader.setErrorHandler(ep);
			xmlReader.setContentHandler(ep);
			InputSource is = new InputSource(new FileInputStream("src/main/resources/com/lwei/sax/test/students2.xml"));
			xmlReader.parse(is);
		} catch (SAXException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}