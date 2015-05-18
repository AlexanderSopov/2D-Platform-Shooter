package edu.chl.Game.view.graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.chl.Game.model.gameobject.entity.*;

public class UnitTitleGraphic {
	
	private Unit u;
	private Font f;

	public UnitTitleGraphic(Unit u){
		this.u = u;
		this.f = new Font("serif", Font.ROMAN_BASELINE, 18);
	}
	
	public void displayUnitTitle(Graphics g){
		String str = u.getUnitTitle();
		g.setFont(f);
		g.setColor(Color.BLACK);
		g.drawString(str, (u.getX()-10), (u.getY()-80));
	}

}
