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
	 * 保存命令行参数传入的属性名字。
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	/**
	 * 保存命令行参数传入的属性的值。
	 */
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		// 判断栈是否为空，如果不为空，说明已经找到了匹配的学生，
		// 于是将其子元素的名字入栈。
		if (!tagsStack.empty()) {
			tagsStack.push(qName);
		} else {
			// 判断是否是<student>元素，如果是，则取出该元素的所有属性进行比对。
			// 如果找到匹配的学生，则将该元素的标记入栈。
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
		// 判断栈是否为空，如果不为空，说明已经找到了匹配的学生。
		if (!tagsStack.empty()) {
			// 得到栈顶的对象，判断是学生的姓名还是年龄，
			// 然后将字符数据保存到name或age变量中。
			// JDk1.5以下版本，调用为String tag=(String)tagsStack.peek();
			String tag = tagsStack.peek();

			if ("name".equals(tag))
				name = new String(ch, start, length);

			if ("age".equals(tag))
				age = new String(ch, start, length);
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (!tagsStack.empty()) {
			// 移除栈顶的对象。JDk1.5以下版本，调用为String tag=(String)tagsStack.pop();
			String tag = tagsStack.pop();
			// 判断移除栈顶对象后，栈是否为空，如果为空，并且移除的栈顶对象是
			// <student>元素的名字，则说明所有学生信息都已获取完毕，于是打印输出
			// 找到的学生信息，然后抛出SAXException异常，终止SAX解析器的解析过程。
			if (tagsStack.empty() && "student".equals(tag)) {
				System.out.println("name : " + name);
				System.out.println("age : " + age);
				throw new SAXException("找到匹配的学生。");
			}
		}
	}

	/**
	 * 如果endDocument()方法被调用，说明没有找到匹配的学生。
	 */
	public void endDocument() throws SAXException {
		System.out.println("没有找到匹配的学生。");
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

		// 从命令行参数中提取属性的名字。
		String str = args[0].substring(0, index);
		sl.setAttrName(str);

		// 从命令行参数中提取属性的值。
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