package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Utility class for reading and writing Course records from/to text files.
 * This class provides functionality to read course records from a file and generate a list of
 * valid Courses. 
 * @author Dania Swelam
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  
	    ArrayList<Course> courses = new ArrayList<Course>(); 
	    while (fileReader.hasNextLine()) { 
	        try { 
	        	Course course = readCourse(fileReader.nextLine()); 
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course current = courses.get(i);

	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    duplicate = true;
	                    break; 
	                }
	            }

	            if (!duplicate) {
	                courses.add(course); 
	            } 
	        } catch (IllegalArgumentException e) {
	          //Empty catch block: error reading course record
	        }
	    }
	    
	    fileReader.close();
	    
	    return courses;
	}

    private static Course readCourse(String nextLine) throws InputMismatchException {
        
        try (Scanner scanner = new Scanner(nextLine)) {
            
            scanner.useDelimiter(",");

            String name = scanner.next();
            String title = scanner.next();
            String section = scanner.next();
            int creditHours = scanner.nextInt();
            String instructorId = scanner.next();
            String meetingDays = scanner.next();

            if ("A".equals(meetingDays)) {
                
                if (scanner.hasNext()) {
                    throw new IllegalArgumentException("Unexpected tokens after meetingDays 'A'.");
                }
                scanner.close();
                
                return new Course(name, title, section, creditHours, instructorId, meetingDays);
            } else {
            
                int startTime = scanner.nextInt();
                int endTime = scanner.nextInt();

                
                if (scanner.hasNext()) {
                    throw new IllegalArgumentException("Unexpected tokens after meetingDays.");
                }
                scanner.close();
               
                return new Course(name, title, section, creditHours, instructorId, meetingDays, startTime, endTime);
            }
        } catch (NoSuchElementException e) {
            
            throw new IllegalArgumentException("Invalid values in the input string.", e);
          }
    }

}