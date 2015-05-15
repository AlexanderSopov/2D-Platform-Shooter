package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.entity.enemy.EnemyUnit;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.physics.collisions.CollisionStrategy.GotHitOnThe;

public class PlayerVsEnemy extends CollisionStrategy {

	public PlayerVsEnemy(Player p, EnemyUnit go) {
		super(p, go);
	}

	@Override
	protected void specialTrick(GotHitOnThe sideGotHit) {
		// TODO Auto-generated method stub
		
	}

}
