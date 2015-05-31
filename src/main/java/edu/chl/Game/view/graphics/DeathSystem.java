package edu.chl.Game.view.graphics;

import java.awt.Graphics;

import edu.chl.Game.model.gameobject.entity.FacingDirection;
import edu.chl.Game.model.gameobject.entity.entityTools.FrameIterator;

public class DeathSystem {
	
	private int x, y;
	private boolean active;
	private DeathAnimation da;
	private FacingDirection fd;
	
	public DeathSystem(){
		this.da = new DeathAnimation();
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setFacing(FacingDirection fd){
		this.fd = fd;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public void setActive(boolean b){
		active = b;
	}
	
	public void render(Graphics g){
		da.renderDeath(g, x, y, fd);
	}
	
	public FrameIterator getFrameIterator(){
		return da.getFrameIterator();
	}

}
