package edu.chl.Game.model.gameobject.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestUnitValues {
	
	private UnitValues uv;

	@Before
	public void initiateTest(){
		System.out.println("initiate");
		uv = new UnitValues(10, 10, 10, 10, 10);
	}
	
	@Test
	public void testA() {
		System.out.println("testA");
		assertTrue(uv.getMaxHealthPoints()==10);
	}
	

}
