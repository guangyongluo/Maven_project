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
					System.out.println("请输入正确的学号(数字)，例如：view 1");
					continue;
				}
				if(sid <= 0) {
					System.out.println("请输入正确的学号(大于0)");
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
			        	System.out.println("请按照菜单输入！！！");
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
				    System.out.println("请按照菜单输入！！！");
				    displayMenu();
			}
		}
	}
	
	public static Student inputStudetnInfo(Scanner scanner) {
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
        return student;
	}
	
	public static void displayMenu() {
		System.out.println("查看所有学生的成绩---------view");
		System.out.println("按照学生id号查询成绩-------view id");
		System.out.println("添加一个学生信息 ----------add");
		System.out.println("按照学生id号更改学生信息----change id");
		System.out.println("按照学生id号删除学生信息----delete id");
		System.out.println("保存学生信息 -------------save");
		System.out.println("退出系统 ----------------quit");
	}

}
