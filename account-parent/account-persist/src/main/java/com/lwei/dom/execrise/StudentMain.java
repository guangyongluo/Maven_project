package com.lwei.dom.execrise;

import java.util.Scanner;

public class StudentMain {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		displayMenu();

		StudentService studentService = new StudentService();

		String userOption = "";
		//String studentId = "";
		int sid = 0;
		while (true) {
			userOption = scanner.nextLine();
			if(userOption.split(" ").length >= 2) {
				String operate = userOption.split(" ")[0];
				String studentId = userOption.split(" ")[1];
				try {
					sid = Integer.parseInt(studentId);
				} catch (NumberFormatException e) {
					System.out.println("��������ȷ��ѧ��(����)�����磺view 1");
					continue;
				}
				if(sid <= 0) {
					System.out.println("��������ȷ��ѧ��(����0)");
				    continue;	
				}
			    switch(operate) {
			        case "view":
			            userOption = "viewById";
			            break;
			        case "change":
			        	userOption = "changeById";
			        	break;
			        case "delete":
			        	userOption = "deleteById";
			        	break;
			        default:
			        	System.out.println("�밴�ղ˵����룡����");
					    displayMenu();
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
	                studentService.addStudentInfo(inputStudetnInfo(scanner));
	                break;
				case "changeById":
					studentService.updateStudentInfo(sid, inputStudetnInfo(scanner));
					break;
				case "deleteById":
					studentService.deleteStudentInfoById(sid);
					break;
				case "quit":
					scanner.close();
					System.exit(0);
				default:
				    System.out.println("�밴�ղ˵����룡����");
				    displayMenu();
			}
		}
	}
	
	public static Student inputStudetnInfo(Scanner scanner) {
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
        return student;
	}
	
	public static void displayMenu() {
		System.out.println("�鿴����ѧ���ĳɼ�---------view");
		System.out.println("����ѧ��id�Ų�ѯ�ɼ�-------view id");
		System.out.println("���һ��ѧ����Ϣ ----------add");
		System.out.println("����ѧ��id�Ÿ���ѧ����Ϣ----change id");
		System.out.println("����ѧ��id��ɾ��ѧ����Ϣ----delete id");
		System.out.println("����ѧ����Ϣ -------------save");
		System.out.println("�˳�ϵͳ ----------------quit");
	}

}
