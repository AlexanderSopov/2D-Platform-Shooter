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
	private SpriteSheet spriteSheet;
	private Sprite character[] = new Sprite[20];
	private Sprite buildings[][] = new Sprite[20][20];
	
	public WorldMapAnimator(String type){
		if(type.equals("character")){
			spriteSheet = new SpriteSheet("/SH_Player.png");
			for (int i = 0; i < 20; i++) {
				character[i] = new Sprite(spriteSheet, i, 0, 62, 62);
			}
		}else if(type.equals("buildings")){
			spriteSheet = new SpriteSheet("/worldMap/buildings.png");
			for(int i = 0; i < 8; i++ ){
				for(int k = 0; k < 5; k++){
					//SpriteSheet: 20x38
					buildings[i][k] = new Sprite(spriteSheet, i, k, 22, 38);
				}
			}
		}
	}
	
	public void renderCharacter(Graphics g, int x, int y, int width, int height){
		//Delaying the animation by 5 and got 19 frames
		delay(5, 19);
		
		//Draw the current Image
		g.drawImage(character[frame].getBufferedImage(), x, y, width, height, null);
	}
	
	public void renderBuilding(Graphics g, int x, int y, int width, int height, int type){
		delay(250, 2);
		
		g.drawImage(buildings[frame+type*2][0].getBufferedImage(), x, y, width, height, null);
	}
	
	public void delay(int maxDelay, int maxFrame){
		delay++;
		if(delay == maxDelay){
			frame++;
			delay = 0;
		}else if(frame == maxFrame){
			frame = 0;
		}
	}
	
	public Image getCharacter(){
		return character[frame].getBufferedImage();
	}
}
