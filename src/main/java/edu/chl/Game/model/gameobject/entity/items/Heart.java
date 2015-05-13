package edu.chl.Game.model.gameobject.entity.items;

import java.awt.Graphics;	
import java.awt.image.BufferedImage;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;

/**
 * Life item that can be captured by Player. 
 * Resulting giving the Player more life.
 * 
 */
public class Heart extends Items {
		
	
	//private Sprite heart;
	
	public Heart(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		
		//heart = new Sprite(handler.getSheetHeart(), 0, 0, 32, 32);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
