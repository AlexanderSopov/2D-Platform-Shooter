package edu.chl.Game.view.graphics.HUD;

import edu.chl.Game.model.gameobject.entity.*;
import java.awt.Graphics;

public class StatusBar {

	private Entity en;

	public StatusBar(Entity en) {
		this.en = en;
	}

	public void displayStatusBar(Graphics g) {
		int x = en.getX() - 100;
		int y = en.getY() - 300;
		g.drawRect(x, y, 200, 30);
		displayHealth(g);
	}

	public void displayHealth(Graphics g){
		int x = en.getX() - 100;
		int y = en.getY() - 300;
		g.fillRect(x, y, en.getUnitValues().getHealthPoints(), 30);
	}
	
	

	
	
	
}
