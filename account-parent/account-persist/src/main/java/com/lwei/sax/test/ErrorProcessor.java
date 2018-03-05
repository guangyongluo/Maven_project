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
	 * 获取导致错误或者警告的文本结束位置的行号和列号。 如果是实体引发错误，还获取它的公共标识符和系统标识符。
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
	 * 输出元素的结束标记，以便于查看不同类型的错误对文档解析的影响。
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("</" + qName + ">");
	}

	public static void main(String[] args) {
		try {
			// 利用XMLReaderFactory工厂类，创建XMLReader对象。
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			// 打开解析器的验证功能。
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