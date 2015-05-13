package edu.chl.Game.model.physics;

import java.util.LinkedList;

import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.player.*;

public class ContactWithEnemy {
	
	private CalculateBounds calculateBounds;
	private Player player;
	
	public ContactWithEnemy(Player player){
		this.calculateBounds = player.getCalculateBounds();
		this.player = player;
	}
	
	public void checkForContact(){
		LinkedList<Entity> entityList = player.getHandler().getEntityList();
		for (Entity en : entityList) {												//looks through the entitylist
			if (en.getId() == Id.monster) {											//finds monsters
				if (calculateBounds.getBounds().intersects(en.getCalculateBounds().getBoundsTop())) {			//checks if the playable unit is ontop of a monster	
					// -add code here- ;
				} else if (calculateBounds.getBounds().intersects(en.getCalculateBounds().getBounds())) {		//checks if the playable unit touches the monster
					// -add code here- ;
				}
			}
		}
	}

}
