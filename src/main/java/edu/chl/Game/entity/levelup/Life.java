package edu.chl.Game.entity.levelup;

import java.awt.image.BufferedImage;

import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;

/**
 * Life item that can be captured by Player. 
 * Resulting giving the Player more life.
 * 
 * MAKE ABSTRACT CLASS BECAUSE HEART ITEM ON MAP
 * AND POINTS ITEM GIVE LIFE.
 * 
 * @author Mansoor
 * @version 1.0
 */
public class Life extends Items {
	
	private Sprite life = new Sprite(handler.getHeart(), 1, 1, 32, 32);
	
	public Life(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	@Override
	public BufferedImage itemsGetBufferedImage() {
		return life.getBufferedImage();
	}
}
