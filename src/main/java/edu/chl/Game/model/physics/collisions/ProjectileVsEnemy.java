package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.entity.enemy.EnemyUnit;
import edu.chl.Game.model.gameobject.entity.player.Bullet;
import edu.chl.Game.model.physics.collisions.CollisionStrategy.GotHitOnThe;

public class ProjectileVsEnemy extends CollisionStrategy{
	private EnemyUnit e;
	private Bullet b;
	public ProjectileVsEnemy(Bullet bu,EnemyUnit en) {
		super(bu, en);
		e = en;
		b = bu;
		
	}

	

	@Override
	protected void specialTrick(GotHitOnThe side) {
		//what happens?
		b.die();
		e.die();
	}



	@Override
	protected void noHit() {
		// TODO Auto-generated method stub
		
	}
}
