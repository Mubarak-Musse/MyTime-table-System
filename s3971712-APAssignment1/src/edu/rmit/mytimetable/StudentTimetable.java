package edu.rmit.mytimetable;

import java.util.ArrayList;
import java.util.List;

public class StudentTimetable {
	
	
	// StudentTimetable stores the list of courses that a single student
	 // is currently enrolled in and provides operations to enroll and withdraw.
	 
  
	    private List<Course> enrolledCourses;

	   
	    public StudentTimetable() {
	        this.enrolledCourses = new ArrayList<>();
	    }

	 
	    public void enroll(Course course) {
	        enrolledCourses.add(course);
	    }

	 
	    public void withdraw(int index) {
	        enrolledCourses.remove(index);
	    }

	 
	    public List<Course> getEnrolledCourses() {
	        return enrolledCourses;
	    }
	}

	
