package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Activity class to check for conflicts between different activities.
 */
class ActivityTest {

	/**
	 * Test method for @CheckConflict. Structure: assertDoesNotThrow(() ->
	 * activityReference.checkConflict(otherActivityReference));
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		Activity a3 = new Event("Exercise", "MW", 800, 900, "Cardio with rest day on Wednesday.");
		Activity a4 = new Event("Exercise", "F", 800, 900, "Cardio with rest day on Wednesday.");
		Activity a5 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
		Activity a6 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");

		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
		assertDoesNotThrow(() -> a3.checkConflict(a4));
		assertDoesNotThrow(() -> a4.checkConflict(a3));
		assertDoesNotThrow(() -> a1.checkConflict(a3));
		assertDoesNotThrow(() -> a3.checkConflict(a1));
		assertDoesNotThrow(() -> a5.checkConflict(a6));
		assertDoesNotThrow(() -> a6.checkConflict(a5));
	}

	/**
	 * Tests method for @CheckConflict for invalid input that could result in a
	 * scheduling conflict between Courses.
	 * 
	 */
	@Test
	public void testCheckConflictWithCourseConflicts() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MTWHF", 1400, 1430);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MTWHF", 1330, 1445);
		Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MTWHF", 1445, 1530);
		Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MTWHF", 1400, 1430);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());

		Exception e3 = assertThrows(ConflictException.class, () -> a2.checkConflict(a3));
		assertEquals("Schedule conflict.", e3.getMessage());

		Exception e4 = assertThrows(ConflictException.class, () -> a3.checkConflict(a2));
		assertEquals("Schedule conflict.", e4.getMessage());

		Exception e5 = assertThrows(ConflictException.class, () -> a1.checkConflict(a4));
		assertEquals("Schedule conflict.", e5.getMessage());

		Exception e6 = assertThrows(ConflictException.class, () -> a4.checkConflict(a1));
		assertEquals("Schedule conflict.", e6.getMessage());

	}

	/**
	 * Tests method for @CheckConflict for invalid input that could result in a
	 * scheduling conflict between Events.
	 * 
	 */
	@Test
	public void testCheckConflictWithEventConflicts() {
		Activity a1 = new Event("Exercise", "TH", 1445, 1530, "Cardio with rest day on Wednesday.");
		Activity a2 = new Event("Exercise", "TH", 1330, 1445, "Cardio with rest day on Wednesday.");
		Activity a3 = new Event("Exercise", "MW", 1330, 1445, "Cardio with rest day on Wednesday.");
		Activity a4 = new Event("Exercise", "MW", 1445, 1530, "Cardio with rest day on Wednesday.");
		Activity a5 = new Event("Exercise", "W", 1400, 1430, "Cardio with rest day on Wednesday.");
		Activity a6 = new Event("Exercise", "MW", 1330, 1445, "Cardio with rest day on Wednesday.");
		Activity a7 = new Event("Exercise", "UMTWHFS", 1330, 1445, "Cardio with rest day on Wednesday.");
		Activity a8 = new Event("Exercise", "UMTWHFS", 1330, 1445, "Cardio with rest day on Wednesday.");

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());

		Exception e3 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
		assertEquals("Schedule conflict.", e3.getMessage());

		Exception e4 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
		assertEquals("Schedule conflict.", e4.getMessage());

		Exception e5 = assertThrows(ConflictException.class, () -> a5.checkConflict(a6));
		assertEquals("Schedule conflict.", e5.getMessage());

		Exception e6 = assertThrows(ConflictException.class, () -> a6.checkConflict(a5));
		assertEquals("Schedule conflict.", e6.getMessage());
		
		Exception e7 = assertThrows(ConflictException.class, () -> a7.checkConflict(a8));
		assertEquals("Schedule conflict.", e7.getMessage());

		Exception e8 = assertThrows(ConflictException.class, () -> a8.checkConflict(a7));
		assertEquals("Schedule conflict.", e8.getMessage());

	}

	/**
	 * Tests method for @CheckConflict for invalid input that could result in a
	 * scheduling conflict between Courses and Events.
	 * 
	 */
	@Test
	public void testCheckConflictWithEventAndCourseConflicts() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1445, 1530);
		Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "W", 1400, 1430);
		Activity a4 = new Event("Exercise", "MW", 1445, 1530, "Cardio with rest day on Wednesday.");
		Activity a5 = new Event("Exercise", "TH", 1300, 1445, "Cardio with rest day on Wednesday.");
		Activity a6 = new Event("Exercise", "MW", 1330, 1445, "Cardio with rest day on Wednesday.");
		Activity a7 = new Event("Exercise", "W", 1400, 1430, "Cardio with rest day on Wednesday.");

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a4));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a4.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());

		Exception e3 = assertThrows(ConflictException.class, () -> a2.checkConflict(a5));
		assertEquals("Schedule conflict.", e3.getMessage());

		Exception e4 = assertThrows(ConflictException.class, () -> a5.checkConflict(a2));
		assertEquals("Schedule conflict.", e4.getMessage());

		Exception e5 = assertThrows(ConflictException.class, () -> a3.checkConflict(a6));
		assertEquals("Schedule conflict.", e5.getMessage());

		Exception e6 = assertThrows(ConflictException.class, () -> a6.checkConflict(a3));
		assertEquals("Schedule conflict.", e6.getMessage());

		Exception e7 = assertThrows(ConflictException.class, () -> a1.checkConflict(a7));
		assertEquals("Schedule conflict.", e7.getMessage());

		Exception e8 = assertThrows(ConflictException.class, () -> a7.checkConflict(a1));
		assertEquals("Schedule conflict.", e8.getMessage());

		Exception e9 = assertThrows(ConflictException.class, () -> a3.checkConflict(a7));
		assertEquals("Schedule conflict.", e9.getMessage());

		Exception e10 = assertThrows(ConflictException.class, () -> a7.checkConflict(a3));
		assertEquals("Schedule conflict.", e10.getMessage());
	}  
	
	@Test
	public void testOtherDaysOfTheWeek() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MTWHF", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "T", 1445, 1600);
		Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "W", 1445, 1600);
		Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "H", 1445, 1600);
		Activity a5 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "F", 1445, 1600);
		
		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());
		
		Exception e2 = assertThrows(ConflictException.class, () -> a1.checkConflict(a3));
		assertEquals("Schedule conflict.", e2.getMessage());
		
		Exception e3 = assertThrows(ConflictException.class, () -> a1.checkConflict(a4));
		assertEquals("Schedule conflict.", e3.getMessage());
		
		Exception e4 = assertThrows(ConflictException.class, () -> a1.checkConflict(a5));
		assertEquals("Schedule conflict.", e4.getMessage());
	}
	
	@Test
	public void testOtherDaysOfTheWeeks() {
	    // Activities with meeting days that do not overlap
	    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MTWHF", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "T", 1500, 1600);
	    Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "W", 1500, 1600);
	    Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "H", 1500, 1600);
	    Activity a5 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "F", 1500, 1600);
	    
	    // Each activity has a different meeting day, so there should be no conflicts
	    assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));
	    assertDoesNotThrow(() -> a1.checkConflict(a3));
	    assertDoesNotThrow(() -> a3.checkConflict(a1));
	    assertDoesNotThrow(() -> a1.checkConflict(a4));
	    assertDoesNotThrow(() -> a4.checkConflict(a1));
	    assertDoesNotThrow(() -> a1.checkConflict(a5));
	    assertDoesNotThrow(() -> a5.checkConflict(a1));
	}
	

}
