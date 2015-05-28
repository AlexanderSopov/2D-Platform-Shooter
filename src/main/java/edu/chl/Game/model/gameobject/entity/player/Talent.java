package edu.chl.Game.model.gameobject.entity.player;

public class Talent {
	
	private TalentType type;
	private int bonus;
	
	public Talent(TalentType type, int bonus){
		this.type = type;
		this.bonus = bonus;
	}
	
	public TalentType getType(){
		return type;
	}
	
	public int getBonus(){
		return bonus;
	}

}
