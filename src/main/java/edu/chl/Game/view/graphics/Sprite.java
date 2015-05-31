package edu.chl.Game.view.graphics;

import java.awt.image.BufferedImage;

/**
 * The Sprite Images of each collum in a SpriteSheet
 * @author Martin Tran
 *
 */
public class Sprite {
	
	public SpriteSheet sheet;
	public BufferedImage image;
	
	/**
	 * Constructor for Sprite
	 * @param sheet The SpriteSheet that will be used to create the sprite
	 * @param x The starting x-cordinates in the SpriteSheet
	 * @param y The starting y-cordinates in the SpriteSheet
	 * @param width The width of one image
	 * @param height The height of one image
	 */
	public Sprite(SpriteSheet sheet, int x, int y, int width, int height){
		image = sheet.getSprite(x, y, width, height);
	}
	
	/**
	 * Get the Sprite image
	 * @return A Sprite image in the SpriteSheet
	 */
	public BufferedImage getBufferedImage(){
		return image;
	}
	
}
