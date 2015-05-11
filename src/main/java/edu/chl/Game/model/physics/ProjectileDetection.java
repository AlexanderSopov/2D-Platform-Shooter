package edu.chl.Game.model.physics;

import java.awt.*;
import java.util.LinkedList;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.handler.*;
import edu.chl.Game.model.gameobject.*;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.player.Bullet;
import edu.chl.Game.model.gameobject.entity.player.Pistol;

public class ProjectileDetection {

	private Pistol pistol;
	private GameHandler handler;
	private LinkedList<Bullet> bulletList;
	private LinkedList<Entity> entityList;

	public ProjectileDetection(Pistol pistol) {
		this.handler = pistol.getHandler();
		this.pistol = pistol;
		bulletList = pistol.getBulletList();
		entityList = handler.getEntityList();
	}

	public void hitTarget() {
		if (!(bulletList.size() == 0)) {
			for (Bullet bu : bulletList) {	
                                for(Entity en : this.entityList) {
					if (en.getId() == Id.monster) {
                                                if (bu.getCalculateBounds().getBounds().intersects(en.getCalculateBounds().getBounds())) {
							System.out.println("Hit!");
							en.remove();
                                                        bu.remove();
							
                                                        break;
						}
					}
				}
			}
		}
		
	}

}
