package edu.chl.Game.utilities;

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
	private LinkedList<Bullet> bulletList = new LinkedList<Bullet>();
	private LinkedList<Entity> entityList = new LinkedList<Entity>();

	public ProjectileDetection(GameHandler handler, Pistol pistol) {
		this.handler = handler;
		this.pistol = pistol;
		bulletList = pistol.getBulletList();
		entityList = handler.getEntityList();
	}

	public void hitTarget() {
		if (!(bulletList.size() == 0)) {
			for (int i = 0; i < bulletList.size(); i++) {
				for (int j = 0; j < entityList.size(); j++) {
					if (entityList.get(j).getUnitState().getId() == Id.monster) {
						if (getBulletArea(i).intersects(getUnitArea(j))) {
							System.out.println("Hit!");
							removeBullet(i);
							removeUnit(j);
						}
					}
				}
			}
		}
		
	}

	public Rectangle getBulletArea(int i) {
		int x = (bulletList.get(i).getX() - (bulletList.get(i).getWidth() / 2) + 50);
		int y = (bulletList.get(i).getY() - (bulletList.get(i).getHeight() / 2));
		int w = bulletList.get(i).getWidth();
		int h = bulletList.get(i).getHeight();
		//System.out.println("bullet: " + " x: " + x + " y: " + y + " w: " + w + " h: " + h);
		return new Rectangle(x, y, w, h);
	}

	public Rectangle getUnitArea(int i) {
		int x = entityList.get(i).getX();
		int y = entityList.get(i).getY();
		int w = entityList.get(i).getWidth();
		int h = entityList.get(i).getHeight();
		//System.out.println("monster: " + " x: " + x + " y: " + y + " w: " + w + " h: " + h);
		return new Rectangle(x, y, w, h);
	}
	
	public void removeBullet(int i){
		bulletList.remove(i);
	}
	
	public void removeUnit(int j){
		handler.removeUnit(j);
		notifyChange();
	}
	
	public void notifyChange(){
		handler.unitChanges(true);
	}
	
}
