/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for PackDoptionManager class.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class PackDoptionManagerTest {

	/** Path file for a valid file */
	private static final String VALID_FILE = "test-files/rescue_records.md";
	/** Path file for a invalid file */
	private static final String INVALID_FILE = "test-files/invalid_rescue_records.md";
	/** Path file for saving a file */
	private static final String SAVE_FILE = "test-files/saved_rescue_records.md";

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.manager.PackDoptionManager#getInstance()}.
	 */
	@Test
	void testGetInstance() {
		PackDoptionManager instance1 = PackDoptionManager.getInstance();
		PackDoptionManager instance2 = PackDoptionManager.getInstance();
		assertSame(instance1, instance2);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.manager.PackDoptionManager#newList()}.
	 */
	@Test
	void testNewList() {
		PackDoptionManager manager = PackDoptionManager.getInstance();
		manager.newList();
		assertEquals(0, manager.getRescueList().size());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.manager.PackDoptionManager#getRescueList()}.
	 */
	@Test
	void testGetRescueList() {
		PackDoptionManager manager = PackDoptionManager.getInstance();
		assertNotNull(manager.getRescueList());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.manager.PackDoptionManager#setChanged(boolean)}.
	 */
	@Test
	void testSetChanged() {
		PackDoptionManager manager = PackDoptionManager.getInstance();
		manager.setChanged(true);
		assertTrue(manager.isChanged());
		manager.setChanged(false);
		assertFalse(manager.isChanged());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.manager.PackDoptionManager#isChanged()}.
	 */
	@Test
	void testIsChanged() {
		PackDoptionManager manager = PackDoptionManager.getInstance();
		manager.setChanged(true);
		assertTrue(manager.isChanged());
		manager.setChanged(false);
		assertFalse(manager.isChanged());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.manager.PackDoptionManager#getFilename()}.
	 */
	@Test
	void testGetFilename() {
		PackDoptionManager manager = PackDoptionManager.getInstance();
		manager.setFilename("test-file.md");
		assertEquals("test-file.md", manager.getFilename());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.manager.PackDoptionManager#setFilename(java.lang.String)}.
	 */
	@Test
	void testSetFilename() {
		PackDoptionManager manager = PackDoptionManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> manager.setFilename(null));
		assertThrows(IllegalArgumentException.class, () -> manager.setFilename(" "));
		manager.setFilename("test-file.md");
		assertEquals("test-file.md", manager.getFilename());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.manager.PackDoptionManager#loadFile(java.lang.String)}.
	 */
	@Test
	void testLoadFile() {
		PackDoptionManager manager = PackDoptionManager.getInstance();
		manager.newList(); // Reset the list
		manager.loadFile(VALID_FILE);
		assertEquals(2, manager.getRescueList().size());
		assertEquals("Ms. Wuf's Rescue", manager.getRescueList().getRescue(0).getName());
		assertEquals("NCSU Rescue", manager.getRescueList().getRescue(1).getName());

		// Test loading invalid file
		assertThrows(IllegalArgumentException.class, () -> manager.loadFile(INVALID_FILE));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.manager.PackDoptionManager#saveFile(java.lang.String)}.
	 */
	@Test
	void testSaveFile() {
		PackDoptionManager manager = PackDoptionManager.getInstance();
		manager.loadFile(VALID_FILE);
		assertDoesNotThrow(() -> manager.saveFile(SAVE_FILE));

		// Test saving to an invalid location (e.g., a directory)
		assertThrows(IllegalArgumentException.class, () -> manager.saveFile("test-files"));
	}

}
