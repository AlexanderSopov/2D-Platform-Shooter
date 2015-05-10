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
        private Entity en;
	//public AI(GameHandler handler, UnitProperties uP, AttackTimer attackTimer, OpponentUnitProperties op){
	public AI(Entity en,AttackTimer attackTimer, OpponentUnitProperties op){
                this.en = en;
		this.uP = en.getUnitProperties();
		this.handler = en.getHandler();
		this.attackTimer = attackTimer;
		this.op = op;
	}
	
	public void followPlayer(){
		if(handler.getPlayer().getX() < en.getX()){
			en.setVelX(-1);
		} else {
			en.setVelX(1);
		}
	}
	
	public void findPlayer() {
		playerXCoordinate = handler.getPlayer().getX();
	}
	
	public void attack() {
		attackTimer.updateAttackTimer();
		if (attackTimer.isReadyToAttack()) {
			if (playerXCoordinate <= en.getX()) {
				if ((en.getX() - playerXCoordinate) <= 50) {
					dealDamage();
				}
			} else {
				if ((playerXCoordinate - en.getX()) <= 50) {
					dealDamage();
				}
			}
		}
	}

	public void dealDamage() {
		handler.getPlayer().recieveDamage(op.getAttackDamage());
	}
	



}
