package edu.chl.Game.view.graphics;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Animator for WorldMapView
 * @author Martin Tran
 *
 */
public class WorldMapAnimator {	
	
	//Private variables
	private int frame;
	private int delay;
	private int maxRows;
	private int maxDelay;
	private SpriteSheet spriteSheet;
	private Sprite sprites[] = new Sprite[20];
	
	/**
	 * Constructor for the WorldMapAnimator
	 * @param path File URL
	 * @param maxRows Number of rows in the SpriteSheet
	 * @param maxCols Number of cols in the SpriteSheet
	 * @param collumWidth The width of one collum
	 * @param collumHeight The hieght of one collum
	 * @param maxDelay The delay time for each image iteration 
	 */
	public WorldMapAnimator(String path, int maxRows,int maxCols, int collumWidth, int collumHeight, int maxDelay){
		spriteSheet = new SpriteSheet(path);
		this.maxDelay = maxDelay;
		this.maxRows = maxRows;
		for(int i = 0; i < maxRows; i++){
			sprites[i] = new Sprite(spriteSheet, i, maxCols, collumWidth, collumHeight);
		}
	}
	
	/**
	 * Draws the animation. Draws the current image and then iterate to the next one
	 * @param g The graphic context
	 * @param x The x-cordinate where it draws
	 * @param y The y-codinate where it draws
	 * @param width The width of the image that gets drawn
	 * @param height The height of the image that gets drawn
	 */
	public void renderAnimation(Graphics g, int x, int y, int width, int height){
		delay();
		
		//Draw the current Image
		g.drawImage(sprites[frame].getBufferedImage(), x, y, width, height, null);
	}
	
	private void delay(){
		delay++;
		if(delay == maxDelay){
			frame++;
			delay = 0;
		}
		if(frame > maxRows-1){
			frame = 0;
		}
	}
	
	/**
	 * Get the currentframe as an image.
	 * @return Current image
	 */
	public Image getCurrentImage(){
		return sprites[frame].getBufferedImage();
	}
	
	/**
	 * Get the sprites images in from the SpriteSheet.
	 * @return sprites arraylist
	 */
	public Sprite[] getSprite(){
		return sprites;
	}
	
	/**
	 * Get the specefic sprite in the Sprite arraw of the SpriteSheet.
	 * @param i the index for the specefic sprite in the array.
	 * @return a specefic sprite.
	 */
	public Sprite getSpeceficSprite(int i){
		if(i < sprites.length){
			return sprites[i];
		}else{
			return null;
		}
	}
	
	/**
	 * Get the current frame number.
	 * @return frame
	 */
	public int getFrame(){
		return frame;
	}
}
