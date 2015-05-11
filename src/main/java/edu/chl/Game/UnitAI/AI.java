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
<<<<<<< HEAD
	private EntityState es;
	
	public AI(GameHandler handler, UnitProperties uP, AttackTimer attackTimer, OpponentUnitProperties op, EntityState es){
		this.uP = uP;
		this.handler = handler;
=======
        private Entity en;
	//public AI(GameHandler handler, UnitProperties uP, AttackTimer attackTimer, OpponentUnitProperties op){
	public AI(Entity en,AttackTimer attackTimer, OpponentUnitProperties op){
                this.en = en;
		this.uP = en.getUnitProperties();
		this.handler = en.getHandler();
>>>>>>> a977b293f066842a1fa73ade1c78d5956e775cff
		this.attackTimer = attackTimer;
		this.op = op;
		this.es = es;
	}
	
	public void followPlayer(){
<<<<<<< HEAD
		if(handler.getPlayer().getX() <= uP.getX()){
			uP.setVelX(-1);
		} else if (uP.getX() < handler.getPlayer().getX()) {
			uP.setVelX(1);
=======
		if(handler.getPlayer().getX() < en.getX()){
			en.setVelX(-1);
		} else {
			en.setVelX(1);
>>>>>>> a977b293f066842a1fa73ade1c78d5956e775cff
		}
		
		if( (handler.getPlayer().getX()-50) < uP.getX() && uP.getX() < (handler.getPlayer().getX()+50) ){
			uP.setVelX(0);
		}
		updateFacingDirection();
	}
	
	public void updateFacingDirection(){
		if(uP.getVelX() < 0){
			es.setFacingDirection(FacingDirection.FacingLeft);
		} else if(0 < uP.getVelX()) {
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
