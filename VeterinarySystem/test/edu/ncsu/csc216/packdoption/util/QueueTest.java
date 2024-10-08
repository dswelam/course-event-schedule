/**
 * 
 */
package edu.ncsu.csc216.packdoption.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for Queue Interface.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class QueueTest {

	/**
	 * Tests the add method of ArrayListQueue.
	 */
	@Test
	void testAdd() {
		Queue<String> queue = new ArrayListQueue<>();
		assertTrue(queue.add("first"));
		assertTrue(queue.add("second"));
		assertEquals(2, queue.size());

		assertThrows(NullPointerException.class, () -> {
			queue.add(null);
		});
	}

	/**
	 * Tests the remove method of ArrayListQueue.
	 */
	@Test
	void testRemove() {
		Queue<String> queue = new ArrayListQueue<>();
		queue.add("first");
		queue.add("second");
		assertEquals("first", queue.remove());
		assertEquals(1, queue.size());
		assertEquals("second", queue.remove());
		assertEquals(0, queue.size());

		assertThrows(NoSuchListElementException.class, queue::remove);
	}

	/**
	 * Tests the element method of ArrayListQueue.
	 */
	@Test
	void testElement() {
		Queue<String> queue = new ArrayListQueue<>();
		queue.add("first");
		queue.add("second");
		assertEquals("first", queue.element());
		assertEquals(2, queue.size());

		queue.remove();
		assertEquals("second", queue.element());

		queue.remove();
		assertThrows(NoSuchListElementException.class, queue::element);
	}

	/**
	 * Tests the size method of ArrayListQueue.
	 */
	@Test
	void testSize() {
		Queue<String> queue = new ArrayListQueue<>();
		assertEquals(0, queue.size());
		queue.add("first");
		assertEquals(1, queue.size());
		queue.add("second");
		assertEquals(2, queue.size());
		queue.remove();
		assertEquals(1, queue.size());
		queue.remove();
		assertEquals(0, queue.size());
	}

	/**
	 * Tests the isEmpty method of ArrayListQueue.
	 */
	@Test
	void testIsEmpty() {
		Queue<String> queue = new ArrayListQueue<>();
		assertTrue(queue.isEmpty());
		queue.add("first");
		assertFalse(queue.isEmpty());
		queue.remove();
		assertTrue(queue.isEmpty());
	}
}