package edu.chl.Game.model.gameobject.item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.entity.player.PlayerOutfit;
import edu.chl.Game.view.Frame;

public class TestInventory {
	
	private RefreshTimer rf;
	private Inventory testInventory;

	
	@Before
	public void initInventory(){
		
		rf = new RefreshTimer();	
		testInventory = new Inventory();
		
	}
	


	@Test
	public void InventoryMapTest() {
		
		
		testInventory.addItem(new Nothing(0,0,0,0, null, rf.getHandler()));
		testInventory.addItem(new W1(0,0,0,0, null, rf.getHandler()));
		testInventory.addItem(new Hat(0,0,0,0, null, rf.getHandler()));
		
		assertTrue(testInventory.getInventoryList().size() == 3);
		
		testInventory.addDelete(new Nothing(0,0,0,0, null, rf.getHandler()));
		assertTrue(testInventory.getInventoryList().size() == 2);
		
		testInventory.addDelete(new Nothing(0,0,0,0, null, rf.getHandler()));
		assertTrue(testInventory.getInventoryList().size() == 2);
		
	}
	
 
	
	


}
