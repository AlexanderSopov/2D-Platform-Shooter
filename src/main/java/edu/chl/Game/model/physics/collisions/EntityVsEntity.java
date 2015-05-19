package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.entity.enemy.EnemyUnit;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.physics.collisions.CollisionStrategy.GotHitOnThe;
import edu.chl.Game.model.gameobject.entity.*;


/*

 */

public class EntityVsEntity extends CollisionStrategy {
	
	private Entity bullet;
	private Entity enemy;
	
	public EntityVsEntity(Entity enA, Entity enB) {
		super(enA, enB);
		this.bullet = enA;
		this.enemy = enB;
	}

	

	@Override
	protected void specialTrick(GotHitOnThe side) {
		System.out.println("hit");
		if(side == GotHitOnThe.Left){
			//p.setVelocity(-9,-12);
		}
		if(side == GotHitOnThe.Right){
			//p.setVelocity(9,-12);
		}
	}



	@Override
	protected void noHit() {
		// TODO Auto-generated method stub
		
	}

}
