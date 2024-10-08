/**
 * 
 */
package edu.ncsu.csc216.packdoption.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for SimpleListIterator Interface.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class SimpleListIteratorTest {

	/** Field for Sorted List */
	private SortedLinkedList<String> list;
	/** Field for Iterator */
	private SimpleListIterator<String> iterator;

	@BeforeEach
	void setUp() {
		list = new SortedLinkedList<>();
		list.add("Cookies");
		list.add("Cake");
		list.add("Ice Cream");
		iterator = list.iterator();
	}

	/**
	 * Test method for hasNext().
	 */
	@Test
	void testHasNext() {
		assertTrue(iterator.hasNext());
		iterator.next(); // "Cake"
		assertTrue(iterator.hasNext());
		iterator.next(); // "Cookies"
		assertTrue(iterator.hasNext());
		iterator.next(); // "Ice Cream"
		assertFalse(iterator.hasNext());
	}

	/**
	 * Test method for next().
	 */
	@Test
	void testNext() {
		assertEquals("Cake", iterator.next());
		assertEquals("Cookies", iterator.next());
		assertEquals("Ice Cream", iterator.next());
		assertThrows(NoSuchListElementException.class, () -> iterator.next());
	}

}
