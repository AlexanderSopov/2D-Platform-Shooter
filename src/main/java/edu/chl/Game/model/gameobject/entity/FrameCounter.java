package edu.chl.Game.model.gameobject.entity;

public class FrameCounter {
	
	private boolean countingComplete = false;
	private int frameCountDelay;
	private int frameCountDelayLimit;
	private int frameCount;
	private int frameLimit;
	
	public FrameCounter(int frameCountDelayLimit, int frameLimit){
		this.frameLimit = frameLimit;
		this.frameCountDelay = 0;
		this.frameCount = 0;
		this.frameCountDelayLimit = frameCountDelayLimit;
	}
	
	public void updateCount(){
		countingComplete = false;
		
		frameCountDelay++;
		if(frameCountDelay == frameCountDelayLimit){
			frameCount++;
			frameCountDelay = 0;
			if(frameCount == frameLimit){
				countingComplete = true;
				frameCount = 0;
			}
		}
		
	}
	
	public boolean isComplete(){
		return countingComplete;
	}
	
	public int getCount(){
		return frameCount;
	}
	

}
