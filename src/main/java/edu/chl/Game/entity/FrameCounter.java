package edu.chl.Game.entity;

public class FrameCounter {
	
	private boolean countingComplete = false;
	private int frameCountDelay;
	private int frameCount;
	private int frameLimit;
	
	public FrameCounter(int frameLimit){
		this.frameLimit = frameLimit;
		this.frameCountDelay = 0;
		this.frameCount = 0;
	}
	
	public void updateCount(){
		countingComplete = false;
		
		frameCountDelay++;
		if(frameCountDelay == 3){
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
