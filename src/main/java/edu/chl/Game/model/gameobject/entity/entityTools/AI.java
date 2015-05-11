package edu.chl.Game.model.gameobject.entity.entityTools;
import edu.chl.Game.*;
import edu.chl.Game.handler.*;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.enemy.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.controller.GameHandler;

public class AI {
	
	private GameHandler handler;
	private int playerXCoordinate;
	private AttackTimer attackTimer;
	private OpponentUnitProperties op;

	private EntityState es;
	
    private Entity en;

	public AI(Entity en, AttackTimer attackTimer, OpponentUnitProperties op){
        this.en = en;
		this.handler = en.getHandler();
		this.attackTimer = attackTimer;
		this.op = op;
		this.es = en.getEntityState();
	}
	
	public void followPlayer(){

		if(handler.getPlayer().getX() < en.getX()){
			en.setVelX(-1);
		} else {
			en.setVelX(1);

		}
		
		if( (handler.getPlayer().getX()-50) < en.getX() && en.getX() < (handler.getPlayer().getX()+50) ){
			en.setVelX(0);
		}
		updateFacingDirection();
	}
	
	public void updateFacingDirection(){
		if(en.getVelX() < 0){
			es.setFacingDirection(FacingDirection.FacingLeft);
		} else if(0 < en.getVelX()) {
			es.setFacingDirection(FacingDirection.FacingRight);
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
