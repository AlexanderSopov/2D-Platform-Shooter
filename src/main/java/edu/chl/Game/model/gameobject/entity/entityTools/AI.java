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
	
    private EnemyUnit eu;

	public AI(EnemyUnit eu, AttackTimer attackTimer, OpponentUnitProperties op){
        this.eu = eu;
		this.handler = eu.getHandler();
		this.attackTimer = attackTimer;
		this.op = op;
		this.es = eu.getEntityState();
	}
	
	public void followPlayer(){

		if(handler.getPlayer().getX() < eu.getX()){
			eu.setVelX(-1);
		} else {
			eu.setVelX(1);

		}
		
		if( (handler.getPlayer().getX()-50) < eu.getX() && eu.getX() < (handler.getPlayer().getX()+50) ){
			eu.setVelX(0);
		}
		updateFacingDirection();
	}
	
	public void updateFacingDirection(){
		if(eu.getVelX() < 0){
			es.setFacingDirection(FacingDirection.FacingLeft);
		} else if(0 < eu.getVelX()) {
			es.setFacingDirection(FacingDirection.FacingRight);
		}
	}
	
	public void findPlayer() {
		playerXCoordinate = handler.getPlayer().getX();
	}
	
	public void attack() {
		attackTimer.updateAttackTimer();
		if (attackTimer.isReadyToAttack()) {
			if (playerXCoordinate <= eu.getX()) {
				if ((eu.getX() - playerXCoordinate) <= 50) {
					dealDamage();
				}
			} else {
				if ((playerXCoordinate - eu.getX()) <= 50) {
					dealDamage();
				}
			}
		}
	}

	public void dealDamage() {
		eu.setAttacking(true);
		handler.getPlayer().recieveDamage(op.getAttackDamage());
	}
	



}
