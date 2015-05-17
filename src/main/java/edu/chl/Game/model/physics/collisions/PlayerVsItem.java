package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.entity.items.Item;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.physics.collisions.CollisionStrategy.GotHitOnThe;

/*
 * Author: Alexander Sopov
 */


public class PlayerVsItem extends CollisionStrategy {

	public PlayerVsItem(Player p, Item go) {
		super(p, go);
	}

	@Override
	protected void specialTrick(GotHitOnThe sideGotHit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void noHit() {
		// Do nothing
		
	}

}
