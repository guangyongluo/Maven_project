package com.lwei.dom.test;

import java.io.File;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;

public class JDOMConvert {
	public static void main(String[] args) {
		SAXBuilder saxBuilder = new SAXBuilder();
		File file = new File("src/main/resources/com/lwei/dom/test/students.xml");
		try {
			Document doc = saxBuilder.build(file);
			// ------------------��ӽڵ�------------------
			// ������ʾһ��ѧ����Ϣ�ĸ�Ԫ�ؽڵ�
			Element eltStu = new Element("student");
			Element eltName = new Element("name");
			Element eltAge = new Element("age");

			// ����<name>��<age>Ԫ�ص��ı�����
			eltName.setText("����");
			eltAge.setText("19");

			// ��<name>��<age>Ԫ�����Ϊ<student>Ԫ�ص�����
			eltStu.addContent(eltName);
			eltStu.addContent(eltAge);

			// Ϊ<student>Ԫ����������sn��ֵΪ03
			eltStu.setAttribute("sn", "03");

			Element root = doc.getRootElement();
			root.addContent(eltStu);

			// ------------------ɾ���ڵ�------------------
			root.removeChild("student");

			// ------------------�޸Ľڵ�------------------
			root.getChild("student").getChild("age").setText("22");
			// Format fmt=Format.getRawFormat();
			XMLOutputter xmlOut = new XMLOutputter();
			Format fmt = Format.getPrettyFormat();
			fmt.setEncoding("GBK");
			fmt.setIndent("    ");
			xmlOut.setFormat(fmt);
			xmlOut.output(doc, System.out);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
		}
	}
}