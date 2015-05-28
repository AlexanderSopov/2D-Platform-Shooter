package edu.chl.Game.model.gameobject.entity.entityTools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestFrameIterator {
	
	private FrameIterator fi0;
	private FrameIterator fi1;
	
	@Before
	public void initiateTest(){
		this.fi0 = new FrameIterator(1, 10);
		this.fi1 = new FrameIterator(-1, -2);
	}

	@Test
	public void testDeActivated() {
		assertFalse(fi0.isActive());
	}
	
	@Test
	public void testFrameCounterA() {
		for(int i=1; i<=15; i++){
			fi0.updateFrameCounter();
		}
		assertTrue(fi0.getFrame()==5);
		testActivated();
	}
	

	public void testActivated() {
		assertTrue(fi0.isActive());
	}
	
	@Test
	public void testFrameCounterB() {
		for(int i=1; i<=15; i++){
			fi1.updateFrameCounter();
		}
		assertTrue(fi1.getFrame()==5);
	}

}
