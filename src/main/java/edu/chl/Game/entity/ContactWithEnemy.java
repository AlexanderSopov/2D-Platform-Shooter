package edu.chl.Game.entity;

import java.util.LinkedList;

import edu.chl.Game.object.Id;

public class ContactWithEnemy {
	
	private UnitProperties unitProperties;
	private CalculateBounds calculateBounds;
	
	public ContactWithEnemy(UnitProperties unitProperties, CalculateBounds calculateBounds){
		this.unitProperties = unitProperties;
		this.calculateBounds = calculateBounds;
	}
	
	public void checkForContact(){
		LinkedList<Entity> e = unitProperties.getHandler().getEntityList();
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
