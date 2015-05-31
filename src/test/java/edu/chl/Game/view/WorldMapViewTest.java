package edu.chl.Game.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.chl.Game.controller.RefreshTimer;

public class WorldMapViewTest {
	
	private WorldMapView wt;
	
	@Before
	public void WorldMapViewTestInit(){
		wt = new WorldMapView(new SubMenuView());
	}
	
	@Test
	public void WorldMapViewTestA() {
		for(int i = 0; i < (RefreshTimer.levels).length; i++){
			wt.setPos(i);
		}
		assertTrue(wt.getPos() == (RefreshTimer.levels).length - 1);
	}
	
	@Test
	public void WorldMapViewTestB() {
		wt.setIsMoving();
		assertTrue(wt.ifMoving());
	}

}
