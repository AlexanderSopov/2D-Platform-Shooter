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
	private EntityState es;
	private EnemyUnit eu;
	private UnitAttraction unitAttraction;

	public AI(EnemyUnit eu, AttackTimer attackTimer) {
		this.eu = eu;
		this.handler = eu.getHandler();
		this.attackTimer = attackTimer;
		this.es = eu.getEntityState();
		unitAttraction = new UnitAttraction(400);
	}

	public void exerciseBehaviour() {
		searchPremesis();
	}

	public void searchPremesis() {
		if (!unitAttraction.targetIsFound()) {
			unitAttraction.searchAttractionArea(eu.getX(), handler.getPlayer().getX());
		}
		unitIsAttracted();
	}

	public void unitIsAttracted() {
		if (unitAttraction.targetIsFound()) {
			react();
		}
	}

	public void react() {
		findPlayer();
		followPlayer();
	}

	public void followPlayer() {
		if (handler.getPlayer().getX() < eu.getX()) {
			eu.setVelX(-1);
			updateFacingDirectionLeft();
		} else {
			eu.setVelX(1);
			updateFacingDirectionRight();
		}

		if ((handler.getPlayer().getX() - 100) < eu.getX()
				&& eu.getX() < (handler.getPlayer().getX() + 100)) {
			eu.setVelX(0);
		}
	}

	public void updateFacingDirectionRight() {
		es.setFacingDirection(FacingDirection.FacingRight);
	}

	public void updateFacingDirectionLeft() {
		es.setFacingDirection(FacingDirection.FacingLeft);
	}

	public void findPlayer() {
		playerXCoordinate = handler.getPlayer().getX();
	}

	public void attack() {
		attackTimer.updateAttackTimer();
		if (attackTimer.isReadyToAttack()) {
			if (playerXCoordinate <= eu.getX()) {
				if ((eu.getX() - playerXCoordinate) <= 100) {
					dealDamage();
				}
			} else {
				if ((playerXCoordinate - eu.getX()) <= 100) {
					dealDamage();
				}
			}
		}
	}

	public void dealDamage() {
		eu.setAttacking(true);
		handler.getPlayer().takeDamage((eu.getAttackDamage()));
	}

}
