package com.lwei.sax.test;

import java.io.File;
import java.io.IOException;

import java.util.Stack;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class StudentLookup extends DefaultHandler {
	private Stack<String> tagsStack = new Stack<String>();
	private String name;
	private String age;

	private String attrName;
	private String attrValue;

	/**
	 * ���������в���������������֡�
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	/**
	 * ���������в�����������Ե�ֵ��
	 */
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		// �ж�ջ�Ƿ�Ϊ�գ������Ϊ�գ�˵���Ѿ��ҵ���ƥ���ѧ����
		// ���ǽ�����Ԫ�ص�������ջ��
		if (!tagsStack.empty()) {
			tagsStack.push(qName);
		} else {
			// �ж��Ƿ���<student>Ԫ�أ�����ǣ���ȡ����Ԫ�ص��������Խ��бȶԡ�
			// ����ҵ�ƥ���ѧ�����򽫸�Ԫ�صı����ջ��
			if ("student".equals(qName)) {
				int len = attrs.getLength();
				for (int i = 0; i < len; i++) {
					if (attrName.equals(attrs.getQName(i)) && attrValue.equals(attrs.getValue(i))) {
						tagsStack.push(qName);
						break;
					}
				}
			}
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		// �ж�ջ�Ƿ�Ϊ�գ������Ϊ�գ�˵���Ѿ��ҵ���ƥ���ѧ����
		if (!tagsStack.empty()) {
			// �õ�ջ���Ķ����ж���ѧ���������������䣬
			// Ȼ���ַ����ݱ��浽name��age�����С�
			// JDk1.5���°汾������ΪString tag=(String)tagsStack.peek();
			String tag = tagsStack.peek();

			if ("name".equals(tag))
				name = new String(ch, start, length);

			if ("age".equals(tag))
				age = new String(ch, start, length);
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (!tagsStack.empty()) {
			// �Ƴ�ջ���Ķ���JDk1.5���°汾������ΪString tag=(String)tagsStack.pop();
			String tag = tagsStack.pop();
			// �ж��Ƴ�ջ�������ջ�Ƿ�Ϊ�գ����Ϊ�գ������Ƴ���ջ��������
			// <student>Ԫ�ص����֣���˵������ѧ����Ϣ���ѻ�ȡ��ϣ����Ǵ�ӡ���
			// �ҵ���ѧ����Ϣ��Ȼ���׳�SAXException�쳣����ֹSAX�������Ľ������̡�
			if (tagsStack.empty() && "student".equals(tag)) {
				System.out.println("name : " + name);
				System.out.println("age : " + age);
				throw new SAXException("�ҵ�ƥ���ѧ����");
			}
		}
	}

	/**
	 * ���endDocument()���������ã�˵��û���ҵ�ƥ���ѧ����
	 */
	public void endDocument() throws SAXException {
		System.out.println("û���ҵ�ƥ���ѧ����");
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("\nUsage: java StudentLookup key=value");
			System.exit(1);
		}
		int index = args[0].indexOf("=");
		if (-1 == index) {
			System.out.println("\nUsage: java StudentLookup key=value");
			System.exit(1);
		}
		StudentLookup sl = new StudentLookup();

		// �������в�������ȡ���Ե����֡�
		String str = args[0].substring(0, index);
		sl.setAttrName(str);

		// �������в�������ȡ���Ե�ֵ��
		str = args[0].substring(index + 1);
		sl.setAttrValue(str);

		SAXParserFactory spf = SAXParserFactory.newInstance();

		SAXParser sp = null;
		try {
			sp = spf.newSAXParser();
			File file = new File("src/main/resources/com/lwei/sax/test/students.xml");
			sp.parse(file, sl);
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}