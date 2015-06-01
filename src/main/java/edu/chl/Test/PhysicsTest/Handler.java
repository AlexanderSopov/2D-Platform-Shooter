package edu.chl.Test.PhysicsTest;

import java.util.Observable;
import java.util.Observer;

import edu.chl.Test.GameObject.Box;
import edu.chl.Test.GameObject.Circle;
import edu.chl.Test.Physics.CollisionStrategy;
import edu.chl.Test.Vector.Vector2D;

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
		c1.setVelocity(5.5,-1.25);
		c3.setVelocity(-1.25, -12.5);
		b1.setVelocity(new Vector2D(-10,5));
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
		/**/		
		
		
	}
}
