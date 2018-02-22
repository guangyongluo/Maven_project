package com.lwei.dom.execrise;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Student student = new Student();
		System.out.println("请输入学号：");
        if(scanner.hasNextInt())
        	student.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.println("请输入姓名：");
        if(scanner.hasNextLine())
        	student.setName(scanner.nextLine());
        System.out.println("请输入Java成绩：");
        if(scanner.hasNextInt())
        	student.setJavaScore(scanner.nextInt());
        scanner.nextLine();
        System.out.println("请输入Oracle成绩：");
        if(scanner.hasNextInt())
        	student.setOracelScore(scanner.nextInt());
        scanner.nextLine();
        System.out.println("请输入VB成绩：");
        if(scanner.hasNextInt())
            student.setVbScore(scanner.nextInt());
        scanner.nextLine();
        System.out.println(student);
        scanner.close();
	}

}
