package edu.chl.Game.UnitAI;
import edu.chl.Game.*;
import edu.chl.Game.handler.*;
import edu.chl.Game.object.Id;
import edu.chl.Game.entity.*;
import edu.chl.Game.Units.*;
import edu.chl.Game.UnitTools.*;

public class AI {
	
	private GameHandler handler;
	private UnitProperties uP;
	private int playerXCoordinate;
	private AttackTimer attackTimer;
	private OpponentUnitProperties op;
	
	public AI(GameHandler handler, UnitProperties uP, AttackTimer attackTimer, OpponentUnitProperties op){
		this.uP = uP;
		this.handler = handler;
		this.attackTimer = attackTimer;
		this.op = op;
	}
	
	public void followPlayer(){
		if(handler.getPlayer().getX() < uP.getX()){
			uP.setVelX(-2);
		} else {
			uP.setVelX(2);
		}
	}
	
	public void findPlayer() {
		playerXCoordinate = handler.getPlayer().getX();
	}
	
	public void attack() {
		attackTimer.updateAttackTimer();
		if (attackTimer.isReadyToAttack()) {
			if (playerXCoordinate <= uP.getX()) {
				if ((uP.getX() - playerXCoordinate) <= 50) {
					dealDamage();
				}
			} else {
				if ((playerXCoordinate - uP.getX()) <= 50) {
					dealDamage();
				}
			}
		}
	}

	public void dealDamage() {
		handler.getPlayer().recieveDamage(op.getAttackDamage());
	}
	



}
