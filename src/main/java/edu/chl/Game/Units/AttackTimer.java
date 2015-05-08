package edu.chl.Game.Units;

public class AttackTimer {
	
	private int strikeTimer;
	private int strikeLimit;
	private boolean readyToAttack = false;
	
	public AttackTimer(int strikeLimit){
		this.strikeLimit = strikeLimit;
		this.strikeTimer = 0;
	}
	
	public void updateAttackTimer(){
		strikeTimer++;
		
		if(strikeTimer == strikeLimit){
			readyToAttack = true;
		} else {
			readyToAttack = false;
		}
		
		strikeTimer = strikeTimer % strikeLimit;
	}
	
	public boolean isReadyToAttack(){
		return readyToAttack;
	}

}
