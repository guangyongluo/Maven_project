package com.lwei.dom.execrise;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Student student = new Student();
		System.out.println("������ѧ�ţ�");
        if(scanner.hasNextInt())
        	student.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.println("������������");
        if(scanner.hasNextLine())
        	student.setName(scanner.nextLine());
        System.out.println("������Java�ɼ���");
        if(scanner.hasNextInt())
        	student.setJavaScore(scanner.nextInt());
        scanner.nextLine();
        System.out.println("������Oracle�ɼ���");
        if(scanner.hasNextInt())
        	student.setOracelScore(scanner.nextInt());
        scanner.nextLine();
        System.out.println("������VB�ɼ���");
        if(scanner.hasNextInt())
            student.setVbScore(scanner.nextInt());
        scanner.nextLine();
        System.out.println(student);
        scanner.close();
	}

}
