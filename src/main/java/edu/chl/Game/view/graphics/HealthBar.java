package edu.chl.Game.view.graphics;
import edu.chl.Game.model.gameobject.entity.*;
import java.awt.Graphics;

public class HealthBar {
	
	private LoadingSprites load;
	private SpriteSheet sheetHealthBar;
	private Sprite[] arrayHealthBars;
	private Entity en;

	public HealthBar(Entity en){
		this.load = new LoadingSprites();
		arrayHealthBars = new Sprite[10];
		this.sheetHealthBar = new SpriteSheet("/hb00.png");
		this.arrayHealthBars = load.loadHealthBars(sheetHealthBar, arrayHealthBars, 160, 10);
		this.en = en;
	}
	
	public void renderHealthBar(Sprite hb, Graphics g){
		g.drawImage(hb.getBufferedImage(), en.getX() - 30, en.getY()-60, 160, 10, null);
	}
	
	public void calculateHealthBar(Graphics g){
		
		double maxHp = en.getUnitValues().getMaxHealthPoints();
		double hp = en.getUnitValues().getHealthPoints();
		double value = ( hp / maxHp );
		
		if( (0 < value) && (value <= 0.1) ){
			renderHealthBar(arrayHealthBars[9], g);
		} else if( (0.1 < value) && (value <= 0.2) ){
			renderHealthBar(arrayHealthBars[8], g);
		} else if( (0.2 < value) && (value <= 0.3) ){
			renderHealthBar(arrayHealthBars[7], g);
		} else if( (0.3 < value) && (value <= 0.4) ){
			renderHealthBar(arrayHealthBars[6], g);
		} else if( (0.4 < value) && (value <= 0.5) ){
			renderHealthBar(arrayHealthBars[5], g);
		} else if( (0.5 < value) && (value <= 0.6) ){
			renderHealthBar(arrayHealthBars[4], g);
		} else if( (0.6 < value) && (value <= 0.7) ){
			renderHealthBar(arrayHealthBars[3], g);
		} else if( (0.7 < value) && (value <= 0.8) ){
			renderHealthBar(arrayHealthBars[2], g);
		} else if( (0.8 < value) && (value <= 0.9) ){
			renderHealthBar(arrayHealthBars[1], g);
		} else if( (0.9 < value) && (value <= 1) ){
			renderHealthBar(arrayHealthBars[0], g);
		}
	}
	
	public void displayHealthBar(Graphics g){
		calculateHealthBar(g);
	}

}
