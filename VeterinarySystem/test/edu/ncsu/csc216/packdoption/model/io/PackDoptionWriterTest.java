/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.packdoption.model.rescue.RescueList;

/**
 * Test class for PackDoptionWriter class.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class PackDoptionWriterTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.io.PackDoptionWriter#writeRescueFile(java.lang.String, edu.ncsu.csc216.packdoption.model.rescue.RescueList)}.
	 */
	@Test
	void testWriteRescueFile() {
		RescueList rescueRecords = PackDoptionReader.readRescueListFile("test-files/rescue_records.md");
		try {
			PackDoptionWriter.writeRescueFile("test-files/newrescue_records.md", rescueRecords);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

}
