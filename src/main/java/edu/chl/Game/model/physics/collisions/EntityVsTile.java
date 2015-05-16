package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.enemy.EnemyUnit;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.tile.Tile;
/*
 * Author: Alexander Sopov
 */
import edu.chl.Game.model.physics.collisions.CollisionStrategy.GotHitOnThe;

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
	}


}
