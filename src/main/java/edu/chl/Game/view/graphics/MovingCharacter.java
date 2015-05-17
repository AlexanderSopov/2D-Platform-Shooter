package edu.chl.Game.view.graphics;

import java.awt.Graphics;

/**
 * 
 * @author Marre
 *
 */
public class MovingCharacter {	
	private int frame;
	private int delay;
	private SpriteSheet sheetPlayer;
	private Sprite character[] = new Sprite[20];
	
	public MovingCharacter(){
		sheetPlayer = new SpriteSheet("/SH_Player.png");
		
		for (int i = 0; i < 20; i++) {
			character[i] = new Sprite(sheetPlayer, i, 0, 62, 62);
		}
	}
	
	public void renderAnimate(Graphics g, int x, int y, int width, int height){
		//Delaying the animation
		delay++;
		if(delay == 5){
			frame++;
			delay = 0;
		}else if(frame == 19){
			frame = 0;
		}
		
		//Draw the current Image
		g.drawImage(character[frame].getBufferedImage(), x, y, width, height, null);
	}
	
}
