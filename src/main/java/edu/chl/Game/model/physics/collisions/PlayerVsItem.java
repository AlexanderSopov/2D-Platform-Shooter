package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.item.Item;
import edu.chl.Game.model.gameobject.item.Item.State;
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
		
			if(!p.getInventory().isItemExsisting(it.getNAME())){
				p.getInventory().addItem(it);
				if(p.getInventory().isItemequipped(it.getNAME())){
					p.getInventory().eqipeItem(it.getNAME());
				}
			}else{
				it.remove();
			}
			
			
		
		
	}

	@Override
	protected void noHit() {
		// Do nothing
		
	}

}
