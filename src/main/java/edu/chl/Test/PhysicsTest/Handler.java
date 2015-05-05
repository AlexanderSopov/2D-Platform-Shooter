package edu.chl.Test.PhysicsTest;

import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.Physics.CollisionStrategy;
import edu.chl.Game.Vector.Vector2D;
import edu.chl.Test.GameObject.Box;
import edu.chl.Test.GameObject.Circle;

/**
*
* @author Alexander Sopov
*/
public class Handler implements Observer {
	
	private static Circle c1 = Main.c1;
	private static Circle c2 = Main.c2;
	private static Circle c3 = Main.c3;

	private static Box b1 = Main.b1;
	private static Box b2 = Main.b2;
	
	public Handler(){
		c1.setVelocity(22,-5);
		c3.setVelocity(-2, -45);
		b1.setVelocity(new Vector2D(-5,-5));
	}
	
	
	
	public void update(Observable arg0, Object arg1) {
		CollisionStrategy strategy = new CollisionStrategy(c1,c2);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();
		
		strategy = new CollisionStrategy(c2,c3);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();

		strategy = new CollisionStrategy(c1,c3);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();

		strategy = new CollisionStrategy(b1,b2);
		if (strategy.areObjectsColliding()){
			strategy.resolveCollision();
		}
			
		
		
	}
}
