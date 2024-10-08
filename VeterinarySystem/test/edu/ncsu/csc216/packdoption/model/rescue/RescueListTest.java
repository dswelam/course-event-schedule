/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.rescue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for RescueList class.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class RescueListTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.RescueList#RescueList()}.
	 */
	@Test
	void testRescueList() {
		RescueList list = new RescueList();
		assertNotNull(list);
		assertEquals(0, list.size());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.RescueList#addRescue(edu.ncsu.csc216.packdoption.model.rescue.Rescue)}.
	 */
	@Test
	void testAddRescueRescue() {
		RescueList list = new RescueList();
		Rescue rescue1 = new Rescue("DaniaRescue");
		Rescue rescue2 = new Rescue("MikesRescue");

		list.addRescue(rescue1);
		assertEquals(1, list.size());
		assertEquals(rescue1, list.getRescue(0));

		list.addRescue(rescue2);
		assertEquals(2, list.size());
		assertEquals(rescue2, list.getRescue(1));

		IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> list.addRescue(rescue1));
		assertEquals(null, e1.getMessage());

		IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> list.addRescue((Rescue) null));
		assertEquals(null, e2.getMessage());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.RescueList#addRescue(java.lang.String)}.
	 */
	@Test
	void testAddRescueString() {
		RescueList list = new RescueList();
		list.addRescue("DaniaRescue");
		assertEquals(1, list.size());
		assertEquals("DaniaRescue", list.getRescue(0).getName());

		list.addRescue("MikesRescue");
		assertEquals(2, list.size());
		assertEquals("MikesRescue", list.getRescue(1).getName());

		IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> list.addRescue("DaniaRescue"));
		assertEquals(null, e1.getMessage());

		IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> list.addRescue((String) null));
		assertEquals(null, e2.getMessage());

		IllegalArgumentException e3 = assertThrows(IllegalArgumentException.class, () -> list.addRescue("  "));
		assertEquals(null, e3.getMessage());

		IllegalArgumentException e4 = assertThrows(IllegalArgumentException.class,
				() -> list.addRescue("MikeRescue\n"));
		assertEquals(null, e4.getMessage());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.RescueList#getRescue(int)}.
	 */
	@Test
	void testGetRescue() {
		RescueList list = new RescueList();
		Rescue rescue1 = new Rescue("DaniaRescue");
		Rescue rescue2 = new Rescue("MikesRescue");

		list.addRescue(rescue1);
		list.addRescue(rescue2);

		assertEquals(rescue1, list.getRescue(0));
		assertEquals(rescue2, list.getRescue(1));

		assertThrows(IndexOutOfBoundsException.class, () -> list.getRescue(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.getRescue(2));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.RescueList#size()}.
	 */
	@Test
	void testSize() {
		RescueList list = new RescueList();
		assertEquals(0, list.size());

		Rescue rescue1 = new Rescue("MikesRescue");
		list.addRescue(rescue1);
		assertEquals(1, list.size());

		Rescue rescue2 = new Rescue("DaniaRescue");
		list.addRescue(rescue2);
		assertEquals(2, list.size());
	}

}
