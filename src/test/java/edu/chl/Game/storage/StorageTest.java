package edu.chl.Game.storage;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tritonus.share.TDebug.AssertException;

public class StorageTest {


	@Test
	public void ReadAndWriTetest() {
		String fileName = "testFile.txt";
		String message = "test message";
		Writer.writeToFile(fileName, message);
		
		String messageResived = Reader.readFile(fileName).getFirst();
		assertEquals(message, messageResived);
		
		Writer.blankFile(fileName);
		assertTrue(Reader.readFile(fileName).isEmpty());
		
	}
	
	@Test
	public void fileNotFoundTest(){
		
		String notExistingFile = "notExistingFile.txt";
		assertTrue( Reader.readFile(notExistingFile).isEmpty());
		
		Writer.writeToFile(null, "");
		
		String messageResived = Reader.readFile(null).getFirst();
		assertEquals("", messageResived);
		
		
	}
	

}
