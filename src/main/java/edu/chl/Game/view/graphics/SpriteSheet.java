package edu.chl.Game.view.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * SpriteSheet 
 * @author Martin Tran
 *
 */
public class SpriteSheet {
	
	//The file that will contains the SpriteSheet
	private BufferedImage sheet;
	
	/**
	 * Constructor for SpriteSheet
	 * @param path The filepath URL for the SpriteSheet
	 */
	public SpriteSheet(String path){
		try {
			sheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates images for each collum
	 * @param x The starting x-cordinate.
	 * @param y The starting y-cordinate.
	 * @param width Width of the collum.
	 * @param height Height of the collum.
	 * @return The specefic Sprite image that was requested
	 */
	public BufferedImage getSprite(int x, int y, int width, int height){
		return sheet.getSubimage(x*width, y*height, width, height);
	}

}
