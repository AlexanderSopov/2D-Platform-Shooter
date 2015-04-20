package edu.chl.Game.entity;

import java.awt.Rectangle;

import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.GameObject;
import edu.chl.Game.object.Id;

public abstract class Entity extends GameObject{
	
	public int facing = 0;
	public int frame = 0;
	public int frameDelay = 0;
	public double gravity = 0.0;
	
	public boolean jumping = false;
	public boolean falling = true;

	public Entity(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle(x+10, y, width-20, 5);
	}
	
	public Rectangle getBoundsBottom(){
		return new Rectangle(x+10, y+height-5, width-20, 5);
	}
	
	public Rectangle getBoundsLeft(){
		return new Rectangle(x, y+10, 5, height-20);
	}
	
	public Rectangle getBoundsRight(){
		return new Rectangle(x+width-5, y+10, 5, height-20);
	}
	
	public void remove(){
		handler.removeEntity(this);
	}

}
