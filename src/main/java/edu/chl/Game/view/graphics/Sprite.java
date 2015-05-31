package edu.chl.Game.view.graphics;

import java.awt.image.BufferedImage;

/**
 * 
 * Sprite models an image taken from a spritesheet.
 * 
 * @author Oliver Tunberg
 */

public class Sprite {
	
	public SpriteSheet sheet;
	public BufferedImage image;
	
	
	public Sprite(SpriteSheet sheet, int x, int y, int width, int height){
		image = sheet.getSprite(x, y, width, height);
	}
	
	public BufferedImage getBufferedImage(){
		return image;
	}
	
}
