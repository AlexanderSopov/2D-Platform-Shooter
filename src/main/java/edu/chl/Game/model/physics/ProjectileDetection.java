package edu.chl.Game.model.physics;

import java.awt.*;
import java.util.LinkedList;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.*;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.player.Bullet;
import edu.chl.Game.model.gameobject.entity.player.Pistol;

public class ProjectileDetection {

	private Bullet bullet;
	private LinkedList<Entity> entityList;
 	private GameHandler handler;

	public ProjectileDetection(Bullet bullet, GameHandler handler) {
		this.bullet = bullet;
		entityList = bullet.getHandler().getEntityList();
        this.handler = handler;
	}
/*
	public void hitTarget() {
        int damageValue = handler.getPlayer().getUnitValues().getAttackDamage();
		for (Entity en : this.entityList) {
			if (en.getId() == Id.monster) {
				if (checkIfHit(bullet, en)) {
					en.takeDamage(damageValue);
					bullet.remove();
					break;
				}
			}
		}
	}
/*
	public boolean checkIfHit(Entity e1, Entity e2) {
		return e1.getCalculateBounds().getBounds()
				.intersects(e2.getCalculateBounds().getBounds());
	}*/

}
