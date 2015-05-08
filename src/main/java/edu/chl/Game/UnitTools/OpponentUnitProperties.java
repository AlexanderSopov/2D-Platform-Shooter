package edu.chl.Game.UnitTools;

import edu.chl.Game.Units.AttackTimer;

public class OpponentUnitProperties {
	
	private double attackDamage;
	private AttackTimer attackTimer;
	private boolean performingAttack;
	
	public OpponentUnitProperties(double attackDamage, int attackRate){
		this.attackDamage = attackDamage;
		this.attackTimer = new AttackTimer(attackRate);
		this.performingAttack = false;
		
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
	


}
