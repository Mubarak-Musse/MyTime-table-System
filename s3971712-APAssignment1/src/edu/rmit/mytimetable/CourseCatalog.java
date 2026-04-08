package edu.rmit.mytimetable;


import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {
	
	private List<Course> courses;
	
	
	public CourseCatalog() { 
		
		this.courses = new ArrayList<>();
	}
	
	public void addCourse(Course course) {
		
		courses.add(course);
	}
	
	public List<Course> getAllCourses() {
		return courses;
	}
	
	public List<Course> searchByKeyword(String keyword) {
		List<Course> result = new ArrayList<>();
		
		String lowerKeyword = keyword.toLowerCase();
		
		for (Course course : courses) {
			String courseName = course.getName();
			if (courseName != null && courseName.toLowerCase().contains(lowerKeyword)) {
				
				result.add(course);
			}
		}
		return result;
	}
	

}
