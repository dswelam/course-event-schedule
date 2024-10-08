package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Represents an event with a title, meeting days, start time, end time, and
 * event details. Extends the Activity class.
 * 
 * @author Dania Swelam
 */
public class Event extends Activity {

	/** Optional Event details. */
	private String eventDetails;

	/**
	 * Constructs an Event object with the specified title, meeting days, start
	 * time, end time, and event details.
	 * 
	 * @param title        the title of the event
	 * @param meetingDays  the meeting days of the event
	 * @param startTime    the start time of the event
	 * @param endTime      the end time of the event
	 * @param eventDetails the details of the event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
	 * Gets the details of the event.
	 * 
	 * @return the event details
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the details of the event.
	 * 
	 * @param eventDetails the details to set for the event
	 * @throws IllegalArgumentException if eventDetails is null
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * Returns a short display array containing the title and meeting information of
	 * the event.
	 * 
	 * @return a short display array
	 */
	@Override
	public String[] getShortDisplayArray() {
		return new String[] { "", "", getTitle(), getMeetingString() };
	}

	/**
	 * Returns a long display array containing the title, meeting information, and
	 * event details of the event.
	 * 
	 * @return a long display array
	 */
	@Override
	public String[] getLongDisplayArray() {
		return new String[] { "", "", getTitle(), "", "", getMeetingString(), getEventDetails() };
	}

	/**
	 * Returns a string representation of the event.
	 * 
	 * @return a string representation of the event
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + ","
				+ getEventDetails();
	}

	/**
	 * Sets the meeting days and times for the activity.
	 *
	 * @param meetingDays a string representing meeting days
	 * @param startTime   the start time of the activity in military format (e.g.,
	 *                    1330 for 1:30 PM)
	 * @param endTime     the end time of the activity in military format (e.g.,
	 *                    1445 for 2:45 PM)
	 * @throws IllegalArgumentException if meetingString is null or empty, contains
	 *                                  invalid characters, or has duplicate days
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.isEmpty()) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			char day = meetingDays.charAt(i);

			for (int j = 0; j < i; j++) {
				if (day == meetingDays.charAt(j)) {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}

			switch (day) {
			case 'U':
			case 'M':
			case 'T':
			case 'W':
			case 'H':
			case 'F':
			case 'S':
				break;
			default:
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	/**
	 * Checks if activity is a duplicate of event.
	 * 
	 * @param activity Activity that is checked for duplicates.
	 * @return true if the activities are duplicates.
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
	    if (activity instanceof Event) {
	        Event otherEvent = (Event) activity;
	        return this.getTitle().equals(otherEvent.getTitle());
	    }
	    return false;
	}
}
