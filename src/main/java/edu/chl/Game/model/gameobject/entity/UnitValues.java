package edu.chl.Game.model.gameobject.entity;

public class UnitValues {
	
	private int maxHealthPoints;
	private int healthPoints;
	private int maxEnergyPoints;
	private int energyPoints;
	private int armor;
	private int attackDamage;
	private int attackRate;
	
	public UnitValues(int maxHealthPoints, int maxEnergyPoints, int armor, int attackDamage, int attackRate){
		this.maxHealthPoints = maxHealthPoints;
		this.healthPoints = maxHealthPoints;
		this.maxEnergyPoints = maxEnergyPoints;
		this.energyPoints = maxEnergyPoints; 
		this.armor = armor;
		this.attackDamage = attackDamage;
		this.attackRate = attackRate;
	}
	
	public int getMaxHealthPoints(){
		return maxHealthPoints;
	}
	
	public int getHealthPoints(){
		return healthPoints;
	}
	
	public int getMaxEnergyPoints(){
		return maxEnergyPoints;
	}
	
	public int getEnergyPoints(){
		return energyPoints;
	}
	
	public int getArmor(){
		return armor;
	}
	
	public int getAttackDamage(){
		return attackDamage;
	}
	
	public int getAttackRate(){
		return attackRate;
	}
	
	public void setMaxHealthPoints(int hp){
		this.maxHealthPoints = hp;
	}
	
	public void setHealthPoints(int hp){
		this.healthPoints = hp;
	}
	
	public void setMaxEnergyPoints(int ep){
		this.maxEnergyPoints = ep;
	}
	
	public void setEnergyPoints(int ep){
		this.energyPoints = ep;
	}
	
	public void setArmor(int arm){
		this.armor = arm;
	}
	
	public void setAttackDamage(int ad){
		this.attackDamage = ad;
	}
	
	public void setAttackRate(int ar){
		this.attackRate = ar;
	}
	
	

}
