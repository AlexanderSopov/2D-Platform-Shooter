package edu.chl.Game.view.graphics;

import static org.junit.Assert.*;

import java.awt.Graphics;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.player.Player;

public class EntityRenderTest {
	
	private RefreshTimer rt;
	private Entity testEntity;
	private Sprite[] spriteArray;
	

	@Before
	public void setUpBeforeClass() {
		rt = new RefreshTimer();
		testEntity = new Player(0,0,64,64,true, Id.player, rt.getHandler());
	}

	@Test(expected = NullPointerException.class)
	public void nullPoniterTest() {
		Graphics g = null;
		
		testEntity.render(g);
	}

}
