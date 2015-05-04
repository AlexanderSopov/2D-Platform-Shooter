package edu.chl.Game.entity;

public class EntityProperties {
	
	private int frame = 0;
	private int frameDelay = 0;
	private double gravity;
	
	public EntityProperties(){
		
	}
	
	public int getFrame(){
		return frame;
	}
	
	public void setFrame(int frame){
		this.frame = frame;
	}
	
	public int getFrameDelay(){
		return frameDelay;
	}
	
	public void setFrameDelay(int frameDelay){
		this.frameDelay = frameDelay;
	}
	
	public double getGravity(){
		return gravity;
	}
	
	public void setGravity(double gravity){
		this.gravity = gravity;
	}
	
}
