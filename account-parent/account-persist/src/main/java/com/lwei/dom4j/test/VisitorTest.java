package com.lwei.dom4j.test;

import java.io.File;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

public class VisitorTest {
	public static void main(String[] args) {
		SAXReader saxReader = new SAXReader();
		File file = new File("src/main/resources/com/lwei/dom4j/test/students.xml");
		try {
			// 通过SAXReader从文件读取XML文档，创建dom4j文档树。
			Document doc = saxReader.read(file);
			// 调用accept方法，接受一个访问者对象。
			doc.accept(new MyVisitor());
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 定义我们自己的访问者类，这是一个静态的内部类。
	 */
	private static class MyVisitor extends VisitorSupport {
		/**
		 * 对于属性节点，打印属性的名字和值。
		 */
		public void visit(Attribute node) {
			System.out.println("attribute : " + node.getName() + " = " + node.getValue());
		}

		/**
		 * 对于处理指令节点，打印处理指令目标和数据。
		 */
		public void visit(ProcessingInstruction node) {
			System.out.println("PI : " + node.getTarget() + " " + node.getText());
		}

		/**
		 * 对于元素节点，判断是否只包含文本内容，如是，则打印标记的名字和 元素的内容。如果不是，则只打印标记的名字。
		 */
		public void visit(Element node) {
			if (node.isTextOnly()) {
				System.out.println("element : " + node.getName() + " = " + node.getText());
			} else {
				System.out.println("--------" + node.getName() + "--------");
			}
		}
	}
}