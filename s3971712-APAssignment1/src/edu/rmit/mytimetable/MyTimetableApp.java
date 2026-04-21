package edu.rmit.mytimetable;

import java.util.List;
import java.util.Scanner;

/** MyTimetableApp is the main console application 
 * that allows a student to search for courses, enroll, view enrolled courses and withdraw.
 */
public class MyTimetableApp {

    public static void main(String[] args) {
        MyTimetableApp app = new MyTimetableApp();
        app.run();
    }

  
    public void run() {
        Scanner scanner = new Scanner(System.in);

        // Set up the core objects.
        CourseCatalog catalog = createCatalogFromCsv();
        StudentTimetable studentTimetable = new StudentTimetable();

        boolean exitRequested = false;

        System.out.println("Welcome to MyTimetable!");
        System.out.println("--------------------------------------------------");

        // Main menu loop.
        while (!exitRequested) {
            printMainMenu();
            System.out.print("Please select: ");
        
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                // If the user just pressed Enter with no number, show the menu again.
                System.out.println("No option entered. Returning to main menu.");
                System.out.println("--------------------------------------------------");
                continue;
            }

            int choice = Integer.parseInt(line);


            switch (choice) {
                case 1:
                    handleSearchAndEnroll(scanner, catalog, studentTimetable);
                    break;
                case 2:
                    handleShowEnrolled(studentTimetable);
                    break;
                case 3:
                    handleWithdraw(scanner, studentTimetable);
                    break;
                case 4:
                    exitRequested = true;
                    System.out.println("Exiting MyTimetable. Goodbye!");
                    break;
                default:
                    System.out.println("Unknown option. Please try again.");
            }

            System.out.println("--------------------------------------------------");
        }

        scanner.close();
    }

    // Prints the main menu options to the console.
    
    private void printMainMenu() {
        System.out.println("> Select from main menu");
        System.out.println("--------------------------------------------------");
        System.out.println("  1) Search by keyword to enroll");
        System.out.println("  2) Show my enrolled courses");
        System.out.println("  3) Withdraw from a course");
        System.out.println("  4) Exit");
    }

    /**
     * Creates a CourseCatalog and loads courses from the CSV file.
     * Expects "course.csv" to be in the working directory. [file:3]
     */
    private CourseCatalog createCatalogFromCsv() {
        CourseCatalog catalog = new CourseCatalog();
        catalog.loadFromCsv("course.csv");
        return catalog;
    }

    /**
     * Handles searching for courses by keyword and enrolling in a selected course.
     */
    private void handleSearchAndEnroll(Scanner scanner,
                                       CourseCatalog catalog,
                                       StudentTimetable studentTimetable) {
        System.out.print("Please provide a keyword: ");
        String keyword = scanner.nextLine();

        List<Course> matchingCourses = catalog.searchByKeyword(keyword);

        if (matchingCourses.isEmpty()) {
            System.out.println("No matching courses found.");
            return;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("> Select from matching list");
        System.out.println("--------------------------------------------------");

        // Display all matching courses with numbers.
        for (int i = 0; i < matchingCourses.size(); i++) {
            Course course = matchingCourses.get(i);
            int displayNumber = i + 1;
            System.out.println("  " + displayNumber + ") " + course.getSummary());
        }

        // Extra option to go back to the main menu.
        int backOptionNumber = matchingCourses.size() + 1;
        System.out.println("  " + backOptionNumber + ") Go to main menu");

        System.out.print("Please select: ");
        int selection = Integer.parseInt(scanner.nextLine());

        if (selection == backOptionNumber) {
            // User chose to go back without enrolling.
            return;
        }

        // Convert from 1-based to 0-based index.
        int selectedIndex = selection - 1;

        if (selectedIndex >= 0 && selectedIndex < matchingCourses.size()) {
            Course selectedCourse = matchingCourses.get(selectedIndex);
            studentTimetable.enroll(selectedCourse);
            System.out.println("You have enrolled in the course " + selectedCourse.getName() + "!");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // Displays all courses currently in the student's timetable.
    
    private void handleShowEnrolled(StudentTimetable studentTimetable) {
        List<Course> enrolled = studentTimetable.getEnrolledCourses();

        if (enrolled.isEmpty()) {
            System.out.println("You don't have any courses enrolled.");
            return;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("You have enrolled into the following course(s):");
        System.out.println("--------------------------------------------------");

        for (int i = 0; i < enrolled.size(); i++) {
            Course course = enrolled.get(i);
            int displayNumber = i + 1;
            System.out.println("  " + displayNumber + ") " + course.getSummary());
        }
    }

    /**
     * Allows the student to withdraw from one of the enrolled courses.
     */
    private void handleWithdraw(Scanner scanner, StudentTimetable studentTimetable) {
        List<Course> enrolled = studentTimetable.getEnrolledCourses();

        if (enrolled.isEmpty()) {
            System.out.println("You don't have any courses to withdraw from.");
            return;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Please choose a course to withdraw:");
        System.out.println("--------------------------------------------------");

        for (int i = 0; i < enrolled.size(); i++) {
            Course course = enrolled.get(i);
            int displayNumber = i + 1;
            System.out.println("  " + displayNumber + ") " + course.getSummary());
        }

        System.out.print("Please select: ");
        int selection = Integer.parseInt(scanner.nextLine());

        int indexToRemove = selection - 1;

        if (indexToRemove >= 0 && indexToRemove < enrolled.size()) {
            Course removedCourse = enrolled.get(indexToRemove);
            studentTimetable.withdraw(indexToRemove);
            System.out.println("You have withdrawn from " + removedCourse.getName() + "!");
        } else {
            System.out.println("Invalid selection.");
        }
    }
}
