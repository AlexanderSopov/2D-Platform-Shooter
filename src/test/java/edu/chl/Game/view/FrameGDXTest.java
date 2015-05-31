package edu.chl.Game.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FrameGDXTest {
	
	private FrameGDX f;
	
	@Before
	public void FrameGDXTestInit(){
		f = new FrameGDX(new Frame());
	}

	@Test
	public void FrameGDXTestA() {
		f.dispose();
		assertTrue(f.getCanvas() == null);
	}

}
