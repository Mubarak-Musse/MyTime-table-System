package edu.rmit.mytimetable;

public class Course {
	
	
	private String name;
	private String capacity;
	private String year;
	private String deliveryMode;
	private String dayOfLecture;
	private String timeOfLecture;
	private int durationHours;
	
	
	public Course(String name, String capacity, 
			String year, 
			String deliveryMode,
			String dayOfLecture, 
			String timeOflecture,
			int durationHours) {
		this.name = name;
		this.capacity = capacity; 
		this.year = year;
		this.deliveryMode = deliveryMode;
		this.dayOfLecture = dayOfLecture;
		this.durationHours = durationHours;
		
		
	}
	public String getSummary(){
	// 1. Compute end time from timeOfLecture + durationHours 
		String[] parts = timeOfLecture.split(":");
		int startHour = Integer.parseInt(parts[0]);
		String minutePart = parts[1];
		
		int endHour = startHour + durationHours;
		String endTime = endHour + ":" + minutePart;
		
		// final summary line 
		return name + "		" 
				+ deliveryMode + "		"
				+ dayOfLecture + "  "
				+ timeOfLecture + " _ " + endTime;
	}
	
	public String getName() {
		return name;
	}
	public String getCapacity() {
		return capacity;
	}
	public String getYear() {
		return year;
	}
	public String getDeliveryMode() {
		return deliveryMode;
	}
	public String getDayOfLecture() {
		return dayOfLecture;
	}
	public String getTimeOfLecture() {
		return timeOfLecture;
	}
	public int getDurationHours() {
		return durationHours;
	}
	
}

