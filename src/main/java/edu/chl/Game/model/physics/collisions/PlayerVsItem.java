package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.entity.items.Item;
import edu.chl.Game.model.gameobject.entity.player.Player;

/*
 * Author: Alexander Sopov
 */


public class PlayerVsItem extends CollisionStrategy {

	public PlayerVsItem(Player p, Item go) {
		super(p, go);
	}

	@Override
	protected void specialTrick() {
		// TODO Auto-generated method stub
		
	}

}
