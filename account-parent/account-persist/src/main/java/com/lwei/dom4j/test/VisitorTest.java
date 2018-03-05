package com.lwei.dom4j.test;

import java.io.File;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

public class VisitorTest {
	public static void main(String[] args) {
		SAXReader saxReader = new SAXReader();
		File file = new File("src/main/resources/com/lwei/dom4j/test/students.xml");
		try {
			// ͨ��SAXReader���ļ���ȡXML�ĵ�������dom4j�ĵ�����
			Document doc = saxReader.read(file);
			// ����accept����������һ�������߶���
			doc.accept(new MyVisitor());
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ���������Լ��ķ������࣬����һ����̬���ڲ��ࡣ
	 */
	private static class MyVisitor extends VisitorSupport {
		/**
		 * �������Խڵ㣬��ӡ���Ե����ֺ�ֵ��
		 */
		public void visit(Attribute node) {
			System.out.println("attribute : " + node.getName() + " = " + node.getValue());
		}

		/**
		 * ���ڴ���ָ��ڵ㣬��ӡ����ָ��Ŀ������ݡ�
		 */
		public void visit(ProcessingInstruction node) {
			System.out.println("PI : " + node.getTarget() + " " + node.getText());
		}

		/**
		 * ����Ԫ�ؽڵ㣬�ж��Ƿ�ֻ�����ı����ݣ����ǣ����ӡ��ǵ����ֺ� Ԫ�ص����ݡ�������ǣ���ֻ��ӡ��ǵ����֡�
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