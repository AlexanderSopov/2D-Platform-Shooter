package edu.chl.Game.view.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import com.sun.xml.internal.bind.v2.model.core.ID;
import edu.chl.Game.model.gameobject.*;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;

public class ScoreInterface {

	private Entity en;
	private FrameIterator fi;
	private Font f;
	private int display_y;
	private int value;
	private ScoreType type;
	
	public ScoreInterface(Entity en, int value, ScoreType type) {
		this.en = en;
		this.fi = new FrameIterator(3, 40);
		this.value = value;
		this.display_y = en.getY() - 30;
		this.f = new Font("serif", Font.BOLD, 25);
		this.type = type;
	}
	
	public void updateSettings(Graphics g){
		updateFrameCounter();
		checkDisplayLimit();
		animateDisplay();
		setFont(g);
	}
	
	public void setFont(Graphics g){
		g.setFont(f);
		processType(g);
	}
	
	private void processType(Graphics g){
		if(type==ScoreType.experience){
			setColorExperience(g);
		} else {
			processID(g);
		}
	}
	
	public void processID(Graphics g){
		if(en.getId()==Id.player){
			setColorPlayer(g);
		} else if (en.getId()==Id.monster){
			setColorEnemy(g);
		}
	}
	

	
	public void setColorPlayer(Graphics g){
		g.setColor(Color.YELLOW);
		renderScore(g);
	}
	

	
	public void setColorEnemy(Graphics g){
		g.setColor(Color.RED);
		renderScore(g);
	}
	
	private void setColorExperience(Graphics g){
		g.setColor(Color.BLUE);
		renderExperienceScore(g);
	}
	
	public void renderScore(Graphics g) {
		g.drawString(String.valueOf(value), en.getX(), display_y);
	}
	
	public void renderExperienceScore(Graphics g) {
		String str = "+" + String.valueOf(value);
		g.drawString(str, en.getX(), display_y);
	}

	public void animateDisplay() {
		display_y = display_y - 2;
	}
	public void checkDisplayLimit(){
		if(display_y < 0){
			resetDisplay();
		}
	}
	public void resetDisplay(){
		display_y = 240;
	}
	public String convertDoubleToString(double value){
		String str = String.valueOf(value);
		return str;
	}
	public void updateFrameCounter(){
		fi.updateFrameCounter();
	}
	public FrameIterator getFrameIterator(){
		return fi;
	}
}
