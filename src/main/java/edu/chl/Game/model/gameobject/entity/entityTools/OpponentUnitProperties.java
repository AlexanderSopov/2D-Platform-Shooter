package edu.chl.Game.model.gameobject.entity.entityTools;


public class OpponentUnitProperties {
	
	private double attackDamage;
	private AttackTimer attackTimer;
	private boolean performingAttack;
	private int numberOfSprites;
	private int width;
	private int height;
	
	public OpponentUnitProperties(double attackDamage, int attackRate, int numberOfSprites, int width, int height){
		this.width = width;
		this.height = height;
		this.attackDamage = attackDamage;
		this.attackTimer = new AttackTimer(attackRate);
		this.performingAttack = false;
		this.numberOfSprites = numberOfSprites;
		
	}
	
	public double getAttackDamage(){
		return this.attackDamage;
	}
	
	public AttackTimer getAttackTimer(){
		return attackTimer;
	}
	
	public boolean getPerformingAttack(){
		return performingAttack;
	}
	
	public int getNumberOfSprites(){
		return numberOfSprites;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	


}
