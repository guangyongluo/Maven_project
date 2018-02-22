package com.lwei.dom.execrise;

import java.util.Scanner;

public class StudentMain {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		System.out.println("�鿴����ѧ���ĳɼ�---------view");
		System.out.println("����ѧ��id�Ų�ѯ�ɼ�-------view id");
		System.out.println("���һ��ѧ����Ϣ ----------add");
		System.out.println("����ѧ��id�Ÿ���ѧ����Ϣ----change");
		System.out.println("����ѧ��id��ɾ��ѧ����Ϣ----delete");
		System.out.println("����ѧ����Ϣ -------------save");
		System.out.println("�˳�ϵͳ ----------------quit");

		StudentService studentService = new StudentService();

		String userOption = "";
		String studentId = "";
		int sid = 0;
		while (true) {
			userOption = scanner.nextLine();
			if(userOption.split(" ").length >= 2) {
				studentId = userOption.split(" ")[1];
				try {
					sid = Integer.parseInt(studentId);
				} catch (NumberFormatException e) {
					System.out.println("��������ȷ��ѧ��(����)�����磺view 1");
					continue;
				}
				if(sid > 0)
					userOption = "viewById";
				else {
					System.out.println("��������ȷ��ѧ��(����0)");
				    continue;	
				}
			}
			switch (userOption) {
			case "view":
				studentService.displayALLScore();
				break;
			case "viewById":
				studentService.displayStudentInfoById(sid);
				break;
			case "add":
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
                studentService.addStudentInfo(student);
                break;
			case "quit":
				System.exit(0);
			}
		}
	}

}
