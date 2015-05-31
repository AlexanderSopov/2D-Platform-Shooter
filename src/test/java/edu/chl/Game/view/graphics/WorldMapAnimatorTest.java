package edu.chl.Game.view.graphics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WorldMapAnimatorTest {
	
	private WorldMapAnimator wpa;
	
	@Before
	public void WorldMapAnimatorTestInit(){
		wpa = new WorldMapAnimator("/SH_Player.png", 20, 0, 62, 62, 5);
	}
	
	@Test
	public void WorldMapAnimatorTestA() {
		assertFalse(wpa.getSprite() == null);
		
		for(int i = 0; i < wpa.getSprite().length; i++){
			assertFalse(wpa.getSpeceficSprite(i) == null);
		}
	}

	@Test
	public void WorldMapAnimatorTestB() {
		assertTrue(wpa.getCurrentImage() == wpa.getSpeceficSprite(wpa.getFrame()).getBufferedImage());
	}
}
