package edu.chl.Game.view.graphics;
import edu.chl.Game.model.gameobject.entity.*;

import java.awt.Graphics;

public class HealthBar {
	
	private LoadingSprites load;
	private SpriteSheet sheetHealthBar;
	private Sprite sprite_HealthBar;
	private Entity en;

	
	public HealthBar(Entity en){
		this.load = new LoadingSprites();
		this.sheetHealthBar = new SpriteSheet("/b00.png");
		this.sprite_HealthBar = load.loadSingleSprite(sheetHealthBar, this.sprite_HealthBar, 160, 10);
		this.en = en;
	}
	
	public void displayBar(Graphics g){
		g.drawImage(sprite_HealthBar.getBufferedImage(), en.getX(), en.getY()-60, 160, 10, null);
	}

}
