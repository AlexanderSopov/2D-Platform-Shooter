package edu.chl.Game.model.gameobject.entity.player;

public class PlayerLevel {
	
	private int level;
	private int experience;
	private int experienceLimit;
	
	private double limitRatio;
	
	public PlayerLevel(){
		this.level = 1;
		this.experience = 0;
		this.experienceLimit = 100;
		this.limitRatio = 1.8;
	}
	
	public void gainExperience(int value){
		experience = experience + value;
		checkLevelLimit();
		circulateExperience();
	}
	
	private void circulateExperience(){
		experience = experience % experienceLimit;
	}
	
	private void checkLevelLimit(){
		if(newLevelReached()){
			increaseLevel();
		}
	}
	
	private boolean newLevelReached(){
		return experienceLimit <= experience;
	}
	
	private void increaseLevel(){
		level++;
		checkLimitRatio();
		setNewExperienceLimit();
	}
	
	private void setNewExperienceLimit(){
		experienceLimit = (int)(experienceLimit * limitRatio);
	}
	
	private void checkLimitRatio(){
		if(level<=10){
			setNewLimitRatio();
		}
	}
	
	private void setNewLimitRatio(){
		limitRatio = 1.2;
	}
	
	
	
	
	
	
	public int getLevel(){
		return level;
	}
	
	public int getExperience(){
		return experience;
	}
	
	public int getExperienceLimit(){
		return experienceLimit;
	}
	

	

}
