package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Manages the scheduling of courses for a user, including maintaining a course catalog,
 * creating and modifying schedules, and exporting schedules to files.
 * @author Dania Swelam
 */
public class WolfScheduler {

	/** The list of courses available for scheduling */
    private ArrayList<Course> catalog;
    /** The list of courses currently scheduled by the user */
    private ArrayList<Activity> schedule;
    /** The title of the user's schedule */
    private String title;

    /** Default constructor constructs a new WolfScheduler with a default title, 
     * an empty catalog, and an empty schedule 
     */
    public WolfScheduler() {
        this.title = "My Schedule";
        this.catalog = new ArrayList<>();
        this.schedule = new ArrayList<>();
    }

    /**
     * Parameterized constructor constructs a new WolfScheduler with a default title, reads courses from the specified file, and adds them to the catalog.
     * @param filename The name of the file to read course records from.
     * @throws IllegalArgumentException If the file cannot be found or read.
     */
    public WolfScheduler(String filename) {
        this.title = "My Schedule";
        this.catalog = new ArrayList<>();
        this.schedule = new ArrayList<>();

        try {
            ArrayList<Course> coursesInputFile = CourseRecordIO.readCourseRecords(filename);
            this.catalog.addAll(coursesInputFile);
        } catch (IllegalArgumentException | FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot find file.", e);
        }
    }

    /**
     * Returns a size 4 array representation of the course catalog. Each row of the array
     * includes the name, section, title, and meetingString in the catalog.
     *
     * @return A 4D array representation of the course catalog.
     */
    public String[][] getCourseCatalog() {
            String [][] catalogArray = new String[catalog.size()][3];
            for (int i = 0; i < catalog.size(); i++) {
                Course c = catalog.get(i);
                catalogArray[i] = c.getShortDisplayArray();
            }
            return catalogArray;
        }

    /**
     * /**
	 * Returns a 4D array representation of the scheduled activities. 
	 *
     * @return A 4D array representation of the scheduled sctivities.
     */
    public String[][] getScheduledActivities() {
    	String [][] scheduledActivities = new String[schedule.size()][3];
        for (int i = 0; i < schedule.size(); i++) {
           Activity a = schedule.get(i);
            scheduledActivities[i] = a.getShortDisplayArray();
        }
        return scheduledActivities;
    }

    /**
     * Returns an array of length 7 containing the course name, section, title,
	 * credits, instructor ID, meeting string, and an empty string.
	 * @return A full 7D array representation of the scheduled activities.
     */
    public String[][] getFullScheduledActivities() {
    	String [][] scheduledActivities = new String[schedule.size()][6];
        for (int i = 0; i < schedule.size(); i++) {
           Activity a = schedule.get(i);
            scheduledActivities[i] = a.getLongDisplayArray();
        }
        return scheduledActivities;
    }

    
    /**
     * Retrieves a course from the catalog based on its name and section.
     * @param name    The name of the course.
     * @param section The section of the course.
     * @return The course from the catalog, or null if not found.
     */
    public Course getCourseFromCatalog(String name, String section) {
        for (Course course : catalog) {
            if (course.getName().equals(name) && course.getSection().equals(section)) {
                return course;
            }
        }
        return null; 
    }

    /**
     * Removes a course from the schedule based on its name and section.
     * @param idx the index of the Activity you want to remove from the schedule.
     * @return True if the course was removed, false otherwise.
     */
    public boolean removeActivityFromSchedule(int idx) {
        try {
            schedule.remove(idx);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
    
    /**
     * Returns the title of schedule.
     * @return The schedule title.
     */
    public String getScheduleTitle() {
        return title;
    }

    /**
     * Sets the schedule title to the provided value.
     * @param scheduleTitle The new schedule title.
     * @throws IllegalArgumentException If the provided title is null.
     */
    public void setScheduleTitle(String scheduleTitle) {
        if (scheduleTitle == null) {
            throw new IllegalArgumentException("Invalid Title.");
        }
        title = scheduleTitle;
    }

    /**
     * Adds a course to the schedule based on its name and section.
     * @param name    The name of the course.
     * @param section The section of the course.
     * @return True if the course was added, false otherwise.
     * @throws IllegalArgumentException If the user is already enrolled in the course.
     */
    public boolean addCourseToSchedule(String name, String section) {
        Course catalogCourse = getCourseFromCatalog(name, section);
        if (catalogCourse == null) {
            return false;
        }
        
        for (Activity activity : schedule) {
            // Check for conflicts
            try {
                catalogCourse.checkConflict(activity);
            } catch (ConflictException e) {
                throw new IllegalArgumentException("The course cannot be added due to a conflict.");
            }

            // Check for duplicates
            if (activity.isDuplicate(catalogCourse)) {
                throw new IllegalArgumentException("You are already enrolled in " + name);
            }
        }

        schedule.add(catalogCourse);
        return true;
    }
    


    /**
     * Resets the schedule, removing all courses.
     */
	public void resetSchedule() {
		schedule = new ArrayList<>();
		
	}

	/**
     * Exports the current schedule to a file.
     * @param filename The name of the file to export the schedule to.
     * @throws IllegalArgumentException If the file cannot be saved.
     */
	public void exportSchedule(String filename) {
        try {
            ActivityRecordIO.writeActivityRecords(filename, schedule);
        } catch (IOException e) {
            throw new IllegalArgumentException("The file cannot be saved.", e);
        }
    }
	
	/**
	 * Adds a new event to the schedule, checking for duplicates based on the event title.
	 * If a duplicate event is found, an IllegalArgumentException is thrown.
	 *
	 * @param eventTitle the title of the new event.
	 * @param eventMeetingDays the meeting days of the new event.
	 * @param eventStartTime the start time of the new event in military format.
	 * @param eventEndTime the end time of the new event in military format.
	 * @param eventDetails additional details or description of the new event.
	 * @throws IllegalArgumentException if a duplicate event already exists in the schedule.
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		Event newEvent = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);

	    for (Activity activity : schedule) {
	        if (activity.isDuplicate(newEvent)) {
	            throw new IllegalArgumentException("You have already created an event called " + eventTitle);
	        }
	    }
	    
	    for (Activity activity : schedule) {
	        try {
	            activity.checkConflict(newEvent);
	        } catch (ConflictException e) {
	            throw new IllegalArgumentException("The event cannot be added due to a conflict.");
	        }
	    }
	    schedule.add(newEvent);
	}
}
