package edu.chl.Game.view.graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import edu.chl.Game.model.gameobject.entity.*;

public class UnitStatsGraphic {
	
	private Unit u;
	
	public UnitStatsGraphic(Unit u){
		this.u = u;
	}
	
	public void displayHealthGraphics(Graphics g){
		int hp = u.getUnitValues().getHealthPoints();
		if(hp < 0){
			hp = 0;
		}
		int maxHp = u.getUnitValues().getMaxHealthPoints();
		String str0 = String.valueOf(hp);
		String str1 = String.valueOf(maxHp);
		str0 = str0 + " / " + str1;
		
		g.drawString(str0, u.getX()+30, u.getY()-30);
	}

}
