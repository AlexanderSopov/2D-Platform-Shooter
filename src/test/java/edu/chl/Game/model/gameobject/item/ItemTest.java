package edu.chl.Game.model.gameobject.item;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.item.Item.State;

public class ItemTest {
	
	private Item itemTest;
	private RefreshTimer rt;
	
	
	
	@Before
	public void initItem(){
		
		rt = new RefreshTimer();	
		
		
	}

	@Test
	public void itemStateTest() {
		
		
		itemTest = new W1(0,0, 0,0,null, rt.getHandler());
		
		itemTest.switchState(null);
		
		assertNotNull(itemTest.getState());
		
		
		
		itemTest.switchState(State.equipped);
		
		assertTrue(itemTest.getState() == State.equipped);
		
		itemTest.switchState(State.inventory);
		
		assertTrue(itemTest.getState() == State.inventory);
		
		itemTest.switchState(State.wating);
		
		assertTrue(itemTest.getState() == State.wating);
		
		
		
	}
	
	@Test
	public void AddAndRemoveTest() {
		
		
		itemTest = new W1(0,0, 0,0,null, rt.getHandler());
		
		
		rt.getHandler().addItem(itemTest);
		boolean itemIsExisting = false;
		for(Item it : rt.getHandler().getItemList()){
			if(it.getNAME().equals(itemTest.getNAME())){
				itemIsExisting = true;
			}
		}
		
		assertTrue(itemIsExisting);
		
		itemTest.remove();
		itemIsExisting = false;
		for(Item it : rt.getHandler().getItemList()){
			if(it.getNAME().equals(itemTest.getNAME())){
				itemIsExisting = true;
			}
		}
		assertFalse(itemIsExisting);
		
	}

}
