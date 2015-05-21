package edu.chl.Game.view.graphics.window;

import java.awt.Graphics;

import edu.chl.Game.view.graphics.LoadingSprites;
import edu.chl.Game.view.graphics.Sprite;
import edu.chl.Game.view.graphics.SpriteSheet;

public class TalentButton {
	
	private SpriteSheet sheetButton;
	private Sprite spriteButton;
	private LoadingSprites load;
	private int width;
	private int height;
	private int buttonX;
	private int buttonY;
	private boolean activated;

	public TalentButton(String path){
		load = new LoadingSprites();
		System.out.println(path);
		sheetButton = new SpriteSheet(path);
		this.width = 100;
		this.height = 100;
		spriteButton = load.loadSingleSprite(sheetButton, spriteButton, width, height);

	}
	
	public void displayButton(Graphics g, int X, int Y){
		buttonX = X;
		buttonY = Y;
		g.drawImage(spriteButton.getBufferedImage(), buttonX, buttonY, width, height, null);
	}
	
	public void hitArea(int x, int y){
		if((buttonX <= x) && (x <= (buttonX+width)) && (buttonY <= y) && (y <= buttonY+height)){
			System.out.println("hit");
			activated = true;
		}
	}

	
}
