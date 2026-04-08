package edu.rmit.mytimetable;

public class MyTimetableApp {

	public static void main(String[] args) {
		
		CourseCatalog catalog = new CourseCatalog();
		
		Course javaCourse = new Course(
				" Java Programming", 
				"120",
				"Year 1",
				" Face-to-face",
				" Wednesday",
				"11:30",
				2);
		
		Course pythonCourse = new Course( 
				"Python programming",
				"N/A",
				"Year 2",
				"Online",
				"Thursday",
				"14.00",
				2);
		
		catalog.addCourse(javaCourse);
		catalog.addCourse(pythonCourse);

		System.out.println("Welcome to MyTimeTable!");
		System.out.println("-----------------------");
		System.out.println("Sample course summary");
		System.out.println(Course.getSummary());
		

        System.out.println("All courses in catalog:");
        for (Course course : catalog.getAllCourses()) {
            System.out.println("- " + course.getSummary());
        }

        System.out.println();
        System.out.println("Search results for keyword 'programming':");
        for (Course course : catalog.searchByKeyword("programming")) {
            System.out.println("- " + course.getSummary());
        }
    }
		

	}

}
