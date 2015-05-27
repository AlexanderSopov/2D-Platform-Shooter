package edu.chl.Game.view.graphics;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 
 * @author Marre
 *
 */
public class WorldMapAnimator {	
	private int frame;
	private int delay;
	private int maxRows;
	private int maxDelay;
	private SpriteSheet spriteSheet;
	private Sprite sprites[] = new Sprite[20];
	
	public WorldMapAnimator(String path, int maxRows,int maxCols, int collumWidth, int collumHeight, int maxDelay){
		spriteSheet = new SpriteSheet(path);
		this.maxDelay = maxDelay;
		this.maxRows = maxRows;
		for(int i = 0; i < maxRows; i++){
			sprites[i] = new Sprite(spriteSheet, i, maxCols, collumWidth, collumHeight);
		}
	}
	
	public void renderAnimation(Graphics g, int x, int y, int width, int height){
		delay();
		
		//Draw the current Image
		g.drawImage(sprites[frame].getBufferedImage(), x, y, width, height, null);
	}
	
	public void delay(){
		delay++;
		if(delay == maxDelay){
			frame++;
			delay = 0;
		}
		if(frame > maxRows-1){
			frame = 0;
		}
	}
	
	public Image getCharacter(){
		return sprites[frame].getBufferedImage();
	}
}
