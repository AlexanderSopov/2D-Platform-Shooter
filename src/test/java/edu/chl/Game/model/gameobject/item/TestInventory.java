package edu.chl.Game.model.gameobject.item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.chl.Game.controller.RefreshTimer;


public class TestInventory {
	
	private RefreshTimer rf;
	private Inventory testInventory;
	private Item item1, item2, item3;

	
	@Before
	public void initInventory(){
		
		rf = new RefreshTimer();	
		item1 = new Nothing(0,0,0,0, null, rf.getHandler());
		item2 = new W1(0,0,0,0, null, rf.getHandler());
		item3 = new Hat(0,0,0,0, null, rf.getHandler());
		
	}
	


	@Test
	public void InventoryMapTest() {
		
		testInventory = new Inventory();
		
		testInventory.addItem(item1);
		testInventory.addItem(item2);
		testInventory.addItem(item3);
		testInventory.addItem(null);
		
		assertTrue(testInventory.getInventoryList().size() == 3);
		
		testInventory.addDelete(item1);
		assertTrue(testInventory.getInventoryList().size() == 2);
		
		testInventory.addDelete(item1);
		testInventory.addDelete(null);
		assertTrue(testInventory.getInventoryList().size() == 2);
		
	}
	
	@Test
	public void equipeAndDiscardTest() {
		
		testInventory = new Inventory();
		
		testInventory.addItem(new Nothing(0,0,0,0, null, rf.getHandler()));
		testInventory.addItem(new W1(0,0,0,0, null, rf.getHandler()));
		testInventory.addItem(new Hat(0,0,0,0, null, rf.getHandler()));
		
		testInventory.eqipeItem(item1);
		
		assertTrue(testInventory.isItemequipped(item1.getNAME()));
		
		testInventory.discardItem(item1);
		
		assertFalse(testInventory.isItemequipped(item1.getNAME()));
		
		assertFalse(testInventory.isItemequipped(null));
		
	}
	


}
