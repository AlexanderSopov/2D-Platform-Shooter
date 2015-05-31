package edu.chl.Game.model.gameobject.item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ItemFactoryTest {
	
	private Item testItem;
	

	@Test
	public void testCreateItem() {
		
		// testing empty
		testItem = ItemFactory.createItem("");
		assertTrue(testItem == null);
		
		// testing null
		testItem = ItemFactory.createItem(null);
		assertTrue(testItem == null);
		
		// testing acuall items
		testItem = ItemFactory.createItem("hAt");
		assertTrue(testItem.getClass().getSimpleName().equals("Hat"));
		
		testItem = ItemFactory.createItem("nothinG");
		assertTrue(testItem.getClass().getSimpleName().equals("Nothing"));
		
		testItem = ItemFactory.createItem("w1");
		assertTrue(testItem.getClass().getSimpleName().equals("W1"));
		
		testItem = ItemFactory.createItem("w2");
		assertNull(testItem);
		
		
		
	}

}
