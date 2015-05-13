package edu.chl.Game.view.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;

public class ScoreInterface {

	private int display_y;

	public ScoreInterface(Entity en) {
		this.display_y = en.getY() - 80;
	}

	public void renderScore(Entity en, Graphics g, double value) {
		
		System.out.println(display_y);
		
		String str = convertDoubleToString(value);
		checkDisplayLimit();
		animateDisplay();
		g.setColor(Color.RED);
		Font f = new Font("serif", Font.BOLD, 25);
		g.setFont(f);
		g.drawString(str, en.getX() - 60, display_y);

	}

	public void animateDisplay() {
		display_y = display_y - 1;
	}
	
	public String convertDoubleToString(double value){
		String str = String.valueOf(value);
		return str;
		
	}
	
	public void checkDisplayLimit(){
		if(display_y < 100){
			resetDisplay();
		}
	}
	
	public void resetDisplay(){
		display_y = 240;
	}

}
