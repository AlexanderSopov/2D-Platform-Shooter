package edu.chl.Game.model.physics;

import java.awt.*;
import java.util.LinkedList;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.*;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.player.Bullet;
import edu.chl.Game.model.gameobject.entity.player.Pistol;

public class ProjectileDetection {

	private LinkedList<Entity> entityList;
	private GameHandler handler;

	public ProjectileDetection(GameHandler handler) {
		entityList = handler.getEntityList();
		this.handler = handler;
	}

	public void hitTarget() {
		int damageValue = handler.getPlayer().getUnitValues().getAttackDamage();
		for (Entity bullet : this.entityList) {
			if (bullet.getId() == Id.bullet) {
				for (Entity enemy : this.entityList) {
					if (bullet.getId() == Id.monster) {
						//if (checkIfHit(bullet, enemy)) {
						////	enemy.takeDamage(damageValue);
						//	break;
						}
					}
				}
			}
		}
	

	public void checkIfHit(Entity e1, Entity e2) {
		System.out.println("hit");
		//return e1.getCalcBounds().getBounds().intersects(e2.getCalcBounds().getBounds());
	}

}
