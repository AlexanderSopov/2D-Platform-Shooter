package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.tile.Tile;
/*
 * Author: Alexander Sopov
 */

public class EntityVsTile extends CollisionStrategy {
	Entity en;
	public EntityVsTile(Entity en, Tile go) {
		super(en, go);
		this.en = en;
	}

	@Override
	protected void specialTrick(GotHitOnThe sideGotHit) {
		if(sideGotHit == GotHitOnThe.Bottom)
			en.isBumpingGround(true);
		else if(sideGotHit == GotHitOnThe.Top)
			en.setVelY(0);
	}
	
	protected void noHit(){
	}


}
