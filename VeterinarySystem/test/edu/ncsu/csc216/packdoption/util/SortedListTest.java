/**
 * 
 */
package edu.ncsu.csc216.packdoption.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for SortedList Interface.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class SortedListTest {

	/** Field for Sorted List */
	private SortedLinkedList<String> list;

	@BeforeEach
	void setUp() {
		list = new SortedLinkedList<>();
	}

	/**
	 * Test method for size().
	 */
	@Test
	void testSize() {
		assertEquals(0, list.size());
		list.add("Cookies");
		assertEquals(1, list.size());
		list.add("Cake");
		assertEquals(2, list.size());
	}

	/**
	 * Test method for isEmpty().
	 */
	@Test
	void testIsEmpty() {
		assertTrue(list.isEmpty());
		list.add("Cookies");
		assertFalse(list.isEmpty());
	}

	/**
	 * Test method for contains(E e).
	 */
	@Test
	void testContains() {
		assertFalse(list.contains("Cookies"));
		list.add("Cookies");
		assertTrue(list.contains("Cookies"));
		assertFalse(list.contains("Cake"));
	}

	/**
	 * Test method for add(E e).
	 */
	@Test
	void testAdd() {
		assertTrue(list.add("Cookies"));
		assertEquals(1, list.size());
		assertTrue(list.contains("Cookies"));
		assertEquals(0, list.indexOf("Cookies"));

		assertTrue(list.add("Cake"));
		assertEquals(2, list.size());
		assertTrue(list.contains("Cake"));
		assertEquals(0, list.indexOf("Cake"));
		assertEquals(1, list.indexOf("Cookies"));

		assertTrue(list.add("Ice Cream"));
		assertEquals(3, list.size());
		assertTrue(list.contains("Ice Cream"));
		assertEquals(1, list.indexOf("Cookies"));
		assertEquals(2, list.indexOf("Ice Cream"));
	}

	/**
	 * Test method for add(E e) with null.
	 */
	@Test
	void testAddNull() {
		assertThrows(NullPointerException.class, () -> list.add(null));
	}

	/**
	 * Test method for get(int index).
	 */
	@Test
	void testGet() {
		list.add("Cookies");
		list.add("Cake");
		list.add("Ice Cream");

		assertEquals("Cake", list.get(0));
		assertEquals("Cookies", list.get(1));
		assertEquals("Ice Cream", list.get(2));
	}

	/**
	 * Test method for get(int index) with invalid index.
	 */
	@Test
	void testGetInvalidIndex() {
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
		list.add("Cookies");
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
	}

	/**
	 * Test method for remove(int index).
	 */
	@Test
	void testRemove() {
		list.add("Cookies");
		list.add("Cake");
		list.add("Ice Cream");

		assertEquals("Cake", list.remove(0));
		assertEquals(2, list.size());
		assertFalse(list.contains("Cake"));
		assertEquals(0, list.indexOf("Cookies"));

		assertEquals("Ice Cream", list.remove(1));
		assertEquals(1, list.size());
		assertFalse(list.contains("Ice Cream"));
		assertEquals(0, list.indexOf("Cookies"));

		assertEquals("Cookies", list.remove(0));
		assertEquals(0, list.size());
		assertFalse(list.contains("Cookies"));
	}

	/**
	 * Test method for remove(int index) with invalid index.
	 */
	@Test
	void testRemoveInvalidIndex() {
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
		list.add("Cookies");
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
	}

	/**
	 * Test method for indexOf(E e).
	 */
	@Test
	void testIndexOf() {
		assertEquals(-1, list.indexOf("Cookies"));
		list.add("Cookies");
		assertEquals(0, list.indexOf("Cookies"));
		assertEquals(-1, list.indexOf("Cake"));
	}

	/**
	 * Test method for iterator().
	 */
	@Test
	void testIterator() {
		list.add("Cookies");
		list.add("Cake");
		list.add("Ice Cream");

		SimpleListIterator<String> it = list.iterator();
		assertTrue(it.hasNext());
		assertEquals("Cake", it.next());
		assertTrue(it.hasNext());
		assertEquals("Cookies", it.next());
		assertTrue(it.hasNext());
		assertEquals("Ice Cream", it.next());
		assertFalse(it.hasNext());
		assertThrows(NoSuchListElementException.class, () -> it.next());
	}

	/**
	 * Test method for toString().
	 */
	@Test
	void testToString() {
		assertEquals("", list.toString());
		list.add("Cookies");
		assertEquals("-Cookies", list.toString());
		list.add("Cake");
		assertEquals("-Cake\n-Cookies", list.toString());
		list.add("Ice Cream");
		assertEquals("-Cake\n-Cookies\n-Ice Cream", list.toString());
	}

	/**
	 * Test method for equals(Object obj).
	 */
	@Test
	void testEquals() {
		SortedLinkedList<String> list2 = new SortedLinkedList<>();
		assertTrue(list.equals(list2));
		list.add("Cookies");
		assertFalse(list.equals(list2));
		list2.add("Cookies");
		assertTrue(list.equals(list2));
		list.add("Cake");
		assertFalse(list.equals(list2));
		list2.add("Cake");
		assertTrue(list.equals(list2));
	}

	/**
	 * Test method for hashCode().
	 */
	@Test
	void testHashCode() {
		SortedLinkedList<String> list2 = new SortedLinkedList<>();
		assertEquals(list.hashCode(), list2.hashCode());
		list.add("Cookies");
		assertNotEquals(list.hashCode(), list2.hashCode());
		list2.add("Cookies");
		assertEquals(list.hashCode(), list2.hashCode());
		list.add("Cake");
		assertNotEquals(list.hashCode(), list2.hashCode());
		list2.add("Cake");
		assertEquals(list.hashCode(), list2.hashCode());
	}
}