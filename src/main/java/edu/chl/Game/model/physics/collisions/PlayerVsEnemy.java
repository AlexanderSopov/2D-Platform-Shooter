package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.entity.enemy.EnemyUnit;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.physics.collisions.CollisionStrategy.GotHitOnThe;

public class PlayerVsEnemy extends CollisionStrategy {
	private Player p;
	public PlayerVsEnemy(Player p, EnemyUnit go) {
		super(p, go);
		this.p=p;
	}

	@Override
	protected void specialTrick(GotHitOnThe sideGotHit) {
		if(sideGotHit == GotHitOnThe.Bottom)
			p.isBumpingGround(true);
	}
}
