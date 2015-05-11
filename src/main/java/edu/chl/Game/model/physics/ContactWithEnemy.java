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
		LinkedList<Entity> e = player.getHandler().getEntityList();
		for (int i = 0; i < e.size(); i++) {																		//looks through the entitylist
			if (e.get(i).getUnitState().getId() == Id.monster) {													//finds monsters
				if (calculateBounds.getBounds().intersects(e.get(i).getCalculateBounds().getBoundsTop())) {			//checks if the playable unit is ontop of a monster	
					// -add code here- ;
				} else if (calculateBounds.getBounds().intersects(e.get(i).getCalculateBounds().getBounds())) {		//checks if the playable unit touches the monster
					// -add code here- ;
				}
			}
		}
	}

}
