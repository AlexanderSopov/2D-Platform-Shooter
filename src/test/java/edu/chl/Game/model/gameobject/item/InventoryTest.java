package edu.chl.Game.model.gameobject.item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.chl.Game.controller.RefreshTimer;


public class InventoryTest {
	
	private RefreshTimer rf;
	private Inventory testInventory;
	private Item item1, item2;

	
	@Before
	public void initInventory(){
		
		rf = new RefreshTimer();	
		item1 = new W1(0,0,0,0, null, rf.getHandler());
		item2 = new Hat(0,0,0,0, null, rf.getHandler());
		
	}
	


	@Test
	public void InventoryMapTest() {
		
		testInventory = new Inventory();
		
		testInventory.addItem(item1);
		testInventory.addItem(item2);
		testInventory.addItem(null);
		
		assertTrue(testInventory.getInventoryList().size() == 2);
		
		testInventory.addDelete(item1);
		assertTrue(testInventory.getInventoryList().size() == 1);
		
		testInventory.addDelete(item1);
		testInventory.addDelete(null);
		assertTrue(testInventory.getInventoryList().size() == 1);
		
	}
	
	@Test
	public void equipeAndDiscardTest() {
		
		testInventory = new Inventory();
		
		testInventory.addItem(item1);
		testInventory.addItem(item2);

		
		testInventory.equipItem(item1);
		
		assertTrue(testInventory.isItemequipped(item1.getNAME()));
		
		testInventory.discardItem(item1);
		
		assertFalse(testInventory.isItemequipped(item1.getNAME()));
		
		assertFalse(testInventory.isItemequipped(null));
		
	}
	
	@Test
	public  void saveandLoadTest(){
		
		testInventory = new Inventory();
		
		testInventory.addItem(new W1(0,0,0,0, null, rf.getHandler()));
		testInventory.addItem(new Hat(0,0,0,0, null, rf.getHandler()));
		
		testInventory.load();
		
	}
	


}
