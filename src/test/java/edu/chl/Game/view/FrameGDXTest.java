package edu.chl.Game.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.chl.Game.controller.RefreshTimer;

public class FrameGDXTest {
	
	private FrameGDX f;
	
	@Before
	public void FrameGDXTestInit(){
		f = new FrameGDX(new RefreshTimer(), new Frame());
	}

	@Test
	public void FrameGDXTestA() {
		f.dispose();
		assertTrue(f.getCanvas() == null);
	}

}
