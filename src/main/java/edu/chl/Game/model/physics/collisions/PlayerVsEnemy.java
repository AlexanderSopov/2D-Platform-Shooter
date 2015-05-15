package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.entity.enemy.EnemyUnit;
import edu.chl.Game.model.gameobject.entity.player.Player;

public class PlayerVsEnemy extends CollisionStrategy {

	public PlayerVsEnemy(Player p, EnemyUnit go) {
		super(p, go);
	}

	@Override
	protected void specialTrick() {
		// TODO Auto-generated method stub
		
	}

}
