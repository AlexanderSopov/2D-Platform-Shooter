package edu.chl.Game.model.gameobject.entity;

public class UnitMeasurement {
	
	private int numberOfSprites_move;
	private int numberOfSprites_attack;
	private int numberOfSprites_hurt;
	
	private int strikeFrame;
	
	private int altWidth;
	private int altHeight;
	
	private int delay_move;
	private int limit_move;
	
	private int delay_attack;
	private int limit_attack;
	
	private int delay_hurt;
	private int limit_hurt;
	
	public int getNumberOfSprite_move(){
		return numberOfSprites_move;
	}
	
	public int getNumberOfSprite_attack(){
		return numberOfSprites_attack;
	}
	
	public int getNumberOfSprite_hurt(){
		return numberOfSprites_hurt;
	}
	
	public int getStrikeFrame(){
		return strikeFrame;
	}
	
	public int getAltWidth(){
		return altWidth;
	}
	
	public int getAltHeight(){
		return altHeight;
	}
	
	public int getDelay_move(){
		return delay_move;
	}
	
	public int getLimit_move(){
		return limit_move;
	}
	
	public int getDelay_attack(){
		return delay_attack;
	}
	
	public int getLimit_attack(){
		return limit_attack;
	}
	
	public int getDelay_hurt(){
		return delay_hurt;
	}
	
	public int getLimit_hurt(){
		return limit_hurt;
	}
	
	public void setNumberOfSprites_move(int value){
		this.numberOfSprites_move = value;
	}
	
	public void setNumberOfSprites_attack(int value){
		this.numberOfSprites_attack = value;
	}
	
	public void setStrikeFrame(int value){
		this.strikeFrame = value;
	}
	
	public void setNumberOfSprites_hurt(int value){
		this.numberOfSprites_hurt = value;
	}
	
	public void setAltWidth(int value){
		this.altWidth = value;
	}
	
	public void setAltHeight(int value){
		this.altHeight = value;
	}
	
	public void setDelay_move(int value){
		this.delay_move = value;
	}

	public void setLimit_move(int value){
		this.limit_move = value;
	}
	
	public void setDelay_attack(int value){
		this.delay_attack = value;
	}
	
	public void setLimit_attack(int value){
		this.limit_attack = value;
	}
	
	public void setDelay_hurt(int value){
		this.delay_hurt = value;
	}
	
	public void setLimit_hurt(int value){
		this.limit_hurt = value;
	}

}
