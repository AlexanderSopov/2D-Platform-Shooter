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

	public EntityVsTile(Entity en, Tile go) {
		super(en, go);
		
	}

	@Override
	protected void specialTrick(GotHitOnThe sideGotHit) {
		// TODO Auto-generated method stub
		
	}


}
