package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.entity.items.Item;
import edu.chl.Game.model.gameobject.entity.items.Item.State;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.physics.collisions.CollisionStrategy.GotHitOnThe;

/*
 * Author: Alexander Sopov
 */


public class PlayerVsItem extends CollisionStrategy {
	
	private Player p;
	private Item it;

	public PlayerVsItem(Player p, Item it) {
		super(p, it);
		this.p = p;
		this.it = it;
		
	}

	@Override
	protected void specialTrick(GotHitOnThe sideGotHit) {
		
		//it.switchState(State.inventory);
		p.getInventory().addItem(it);
		it.remove();
		
	}

	@Override
	protected void noHit() {
		// Do nothing
		
	}

}
