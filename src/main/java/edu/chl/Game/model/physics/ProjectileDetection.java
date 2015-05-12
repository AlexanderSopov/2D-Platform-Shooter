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
        
        private Bullet bullet;
	private LinkedList<Entity> entityList;

	public ProjectileDetection(Bullet bullet) {
		
		this.bullet = bullet;
		entityList = bullet.getHandler().getEntityList();
	}

	public void hitTarget() {
                            System.out.println("lenth:"+ this.entityList.size());
                           for(Entity en : this.entityList) {
					if (en.getId() == Id.monster) {
                                                if (bullet.getCalculateBounds().getBounds().intersects(en.getCalculateBounds().getBounds())) {
                                                            
                                                    //System.err.println("Im hit!!!");
                                                    en.takeDamage(10);
                                                    bullet.remove();
                                                    break;
                                                 }
                                        }
                            }
	}

}
		


		
