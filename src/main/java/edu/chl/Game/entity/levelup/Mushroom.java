package edu.chl.Game.entity.levelup;

import java.awt.Graphics;	
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;

import edu.chl.Game.entity.Entity;
import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;


/**
 * Visible Mushroom on the map that can be used by the Player.
 * 
 * @author Mansoor
 * @version 1.0
 */

public class Mushroom extends Items {


	/*
	 * initialize mushroom with a mushroom image. The position of mushroom
	 * is (1,1) and have width of 32 and height 32.
	 */
	private Sprite mushroom = new Sprite(handler.getSheetMushroom(), 1, 1, 32, 32);
	
	public Mushroom(int x, int y, int width, int height, boolean solid,
			Id id, GameHandler handler) {
		
		super(x, y, width, height, solid, id, handler);
		
	}
	@Override
	public BufferedImage itemsGetBufferedImage() {
		
		return mushroom.getBufferedImage();
	}
}

