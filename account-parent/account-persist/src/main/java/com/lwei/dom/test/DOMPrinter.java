package com.lwei.dom.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMPrinter {
	/**
	 * 输出节点的名字和节点的值。
	 */
	public static void printNodeInfo(Node node) {
		System.out.println(node.getNodeName() + " : " + node.getNodeValue());
	}

	/**
	 * 采用递归调用，输出指定节点下的所有后代节点。 注：为了简单起见，只对处理指令节点、元素节点、属性节点和文本节点进行了处理。
	 **/
	public static void printNode(Node node) {
		short nodeType = node.getNodeType();
		switch (nodeType) {
		case Node.PROCESSING_INSTRUCTION_NODE:
			System.out.println("-----------PI start-----------");
			printNodeInfo(node);
			System.out.println("-----------PI end-----------");
			break;
		case Node.ELEMENT_NODE:
			System.out.println("-----------Element start-----------");
			printNodeInfo(node);
			System.out.println("-----------Element end-----------");

			NamedNodeMap attrs = node.getAttributes();
			int attrNum = attrs.getLength();
			for (int i = 0; i < attrNum; i++) {
				Node attr = attrs.item(i);
				System.out.println("-----------Attribute start-----------");
				printNodeInfo(attr);
				System.out.println("-----------Attribute end-----------");
			}
			break;
		case Node.TEXT_NODE:
			System.out.println("-----------Text start-----------");
			printNodeInfo(node);
			System.out.println("-----------Text end-----------");
			break;
		default:
			break;
		}

		Node child = node.getFirstChild();
		while (child != null) {
			printNode(child);
			child = child.getNextSibling();
		}
	}

	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File("src/main/resources/com/lwei/dom/test/students.xml"));
			printNode(doc);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
