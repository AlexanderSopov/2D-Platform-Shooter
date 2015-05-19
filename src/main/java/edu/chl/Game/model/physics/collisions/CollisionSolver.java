package edu.chl.Game.model.physics.collisions;

import java.util.LinkedList;

import com.sun.javafx.css.CalculatedValue;

import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.EntityProperties;
import edu.chl.Game.model.gameobject.entity.EntityState;
import edu.chl.Game.model.gameobject.entity.UnitState;
import edu.chl.Game.model.gameobject.entity.enemy.EnemyUnit;
import edu.chl.Game.model.gameobject.entity.items.Item;
import edu.chl.Game.model.gameobject.entity.player.Bullet;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.tile.*;

public class CollisionSolver {
	private static CollisionStrategy strategy;
	public static void solveCollisions(Entity en, LinkedList<GameObject> list){

		if(en instanceof Player)
			for(GameObject go : list)
				solveCollision((Player)en, go);
		else if (en instanceof EnemyUnit)
			for(GameObject go : list)
				solveCollision((EnemyUnit)en, go);
		else if (en instanceof Bullet)
			for(GameObject go : list)
				solveCollision((Bullet)en, go);
	}
	

	
	/*
	 * Player Vs XXX
	 */
	
	//Check type of GameObject
	
	private static void solveCollision(Player p, GameObject go){
		if (go instanceof Tile){
			strategy = new EntityVsTile(p, (Tile)go);
			strategy.solve();
		}else if (go instanceof Item){
			strategy = new PlayerVsItem(p, (Item)go);
			strategy.solve();
		}else if (go instanceof EnemyUnit){
			strategy = new PlayerVsEnemy(p, (EnemyUnit)go);
			strategy.solve();
		}
	}
	
	
	/*
	 * Enemy Vs XXX
	 */
	
	// Check type of GameObject
	
	private static void solveCollision(EnemyUnit en, GameObject go){
		if (go instanceof Tile){
			strategy = new EntityVsTile(en, (Tile)go);
			strategy.solve();
		}else if (go instanceof Player){
			strategy = new EnemyVsPlayer(en, (Player)go);
			strategy.solve();
		}
		else
			;//Do nothing, enemies don't pick up items
		
	}
	
	
	/*
	 * Projectile Vs XXX
	 */
	
	// Check type of GameObject 
	
	private static void solveCollision(Bullet bu, GameObject go){
		if (go instanceof EnemyUnit){
			strategy = new ProjectileVsEnemy(bu, (EnemyUnit)go);
			strategy.solve();
		}else
			;
		
	}



}
