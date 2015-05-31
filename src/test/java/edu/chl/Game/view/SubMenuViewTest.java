package edu.chl.Game.view;

import static org.junit.Assert.*;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.junit.Before;
import org.junit.Test;

import edu.chl.Game.controller.State;

public class SubMenuViewTest {
	
	SubMenuView sbw;
	
	@Before
	public void SubMenuViewTestInit(){
		sbw = new SubMenuView();
	}

	@Test
	public void SubMenuViewTestA() {
		sbw.setFont(1, "bold");
		assertTrue(sbw.getFont1().equals(new Font("arial", Font.BOLD, 25)));
	}
	
	@Test
	public void SubMenuViewTestB(){	
		sbw.setFont(1, "italic");
		assertTrue(sbw.getFont1().equals(new Font("arial", Font.ITALIC, 25)));
	}
	
	@Test
	public void SubMenuViewTestC() {
		sbw.setFont(2, "bold");
		assertTrue(sbw.getFont2().equals(new Font("arial", Font.BOLD, 25)));
	}
	
	@Test
	public void SubMenuViewTestD(){	
		sbw.setFont(2, "italic");
		assertTrue(sbw.getFont2().equals(new Font("arial", Font.ITALIC, 25)));
	}
	
	@Test
	public void SubMenuViewTestE(){
		sbw.setState(State.SUB_MENU);
		assertTrue(sbw.getState() == State.SUB_MENU);
	}
	
	@Test
	public void SubMenuViewTestF(){
		sbw.setSoundState(false);
		assertFalse(sbw.getSoundState());
	}
}
