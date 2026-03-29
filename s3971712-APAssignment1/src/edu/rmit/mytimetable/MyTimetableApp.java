package edu.rmit.mytimetable;

public class MyTimetableApp {

	public static void main(String[] args) {
		
		Course Course = new Course(
				" Java Programming", 
				"120",
				"Year 1",
				" Face-to-face",
				" Wednesday",
				"11:30",
				2);

		System.out.println("Welcome to MyTimeTable!");
		System.out.println("-----------------------");
		System.out.println("Sample course summary");
		System.out.println(Course.getSummary());
		

	}

}
