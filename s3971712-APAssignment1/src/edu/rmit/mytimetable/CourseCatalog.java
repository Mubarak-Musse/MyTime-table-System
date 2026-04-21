package edu.rmit.mytimetable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {
	
	private List<Course> courses;
	
    /**
     * Loads courses from a CSV file and adds them to the catalog.
     * The CSV is expected to have a header line followed by course records.
     */
    /**
     * Loads courses from a CSV file and adds them to the catalog.
     * Follows the pattern demonstrated in the lecture transcript:
     *  - FileReader + BufferedReader
     *  - while ((line = reader.readLine()) != null) loop
     *  - split each line by comma into fields.
     *
     * @param filePath the path to the CSV file (e.g. "course.csv")
     */
    public void loadFromCsv(String filePath) {
        try {
            // Create a FileReader and wrap it in a BufferedReader.
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);

            // Read and ignore the header line.
            String line = reader.readLine(); // header

            // Read the rest of the file line by line.
            line = reader.readLine();
            while (line != null) {
                // Each line represents one course, fields separated by commas.
                String[] data = line.split(",");

                // Expected columns from course.csv:
                // 0: Course name
                // 1: Capacity
                // 2: Year
                // 3: Delivery mode
                // 4: Day of lecture
                // 5: Time of lecture
                // 6: Duration of lecture (hour)
                if (data.length >= 7) {
                    String name = data[0].trim();
                    String capacity = data[1].trim();
                    String year = data[2].trim();
                    String deliveryMode = data[3].trim();
                    String dayOfLecture = data[4].trim();
                    String timeOfLecture = data[5].trim();
                    String durationText = data[6].trim();

                    // In the CSV, duration can be "2" or "1.5".
                    // Transcript used Double.parseDouble then converted. [file:4]
                    double durationDouble = Double.parseDouble(durationText);
                    int durationHours = (int) Math.round(durationDouble);

                    Course course = new Course(
                            name,
                            capacity,
                            year,
                            deliveryMode,
                            dayOfLecture,
                            timeOfLecture,
                            durationHours
                    );

                    // Add the new course to the internal list, like accounts.add(account). [file:4]
                    addCourse(course);
                }

                // Read the next line.
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading courses from CSV: " + e.getMessage());
        }
    }


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
