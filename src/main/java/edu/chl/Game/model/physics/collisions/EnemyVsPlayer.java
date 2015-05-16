package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.entity.enemy.EnemyUnit;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.physics.collisions.CollisionStrategy.GotHitOnThe;


/*
 * Author: Alexander Sopov
 */

public class EnemyVsPlayer extends CollisionStrategy {
	private EnemyUnit e;
	private Player p;
	public EnemyVsPlayer(EnemyUnit en, Player pl) {
		super(en, pl);
		e = en;
		p = pl;
		
	}

	

	@Override
	protected void specialTrick(GotHitOnThe side) {
		/*if(side == GotHitOnThe.Left)
			p.setVelocity(-9,-12);
		if(side == GotHitOnThe.Right)
			p.setVelocity(9,-12);
		*/
	}

}
