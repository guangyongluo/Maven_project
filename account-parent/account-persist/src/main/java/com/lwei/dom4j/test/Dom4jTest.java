package com.lwei.dom4j.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jTest {
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new File("src/main/resources/com/lwei/dom4j/test/myClass.xml"));
        //list(doc.getRootElement());
        //read(doc);
        //add(doc);
        del(doc);
	}
	
    public static void read(Document doc) {
    	Element root = doc.getRootElement();
    	List<Element> students = root.elements("学生");
    	Element student = students.get(0);
    	System.out.println(student.element("名字").getText());
    }
	
	public static void list(Element element) {
		System.out.println(element.getName());
		
		Iterator<Element> it = element.elementIterator();
		
		while(it.hasNext()) {
			Element child = it.next();
			//System.out.println(child.getName());
			list(child);
		}
	}
	
	public static void del(Document doc) {
//		Element student = doc.getRootElement().element("学生");
//		student.getParent().remove(student);
		
		Element student2 = (Element)doc.getRootElement().elements("学生").get(1);
		Attribute attr = student2.element("姓名").attribute("别名");
		student2.element("姓名").remove(attr);
		
		try {
			FileOutputStream fos = new FileOutputStream("src/main/resources/com/lwei/dom4j/test/myClass.xml");
			OutputFormat of = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(fos, of);
			writer.write(doc);
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void add(Document doc) {
		Element student = DocumentHelper.createElement("学生");
		Element studentName = DocumentHelper.createElement("姓名");
		Attribute attr = DocumentHelper.createAttribute(studentName, "別名", "及时雨");
		studentName.setText("宋江");
		studentName.add(attr);
		Element studentAge = DocumentHelper.createElement("年龄");
		studentAge.setText("33");
		Element studentIntro = DocumentHelper.createElement("介绍");
		studentIntro.setText("是个好孩子");
		
		student.add(studentName);
		student.add(studentAge);
		student.add(studentIntro);
		
		doc.getRootElement().add(student);
		
		try {
			FileOutputStream fos = new FileOutputStream("src/main/resources/com/lwei/dom4j/test/myClass.xml");
			OutputFormat of = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(fos, of);
			writer.write(doc);
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
