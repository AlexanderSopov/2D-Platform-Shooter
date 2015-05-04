package edu.chl.Game.Physics;

import edu.chl.Game.GameObject.Box;
import edu.chl.Game.GameObject.Circle;

public class CollisionStrategy {
	CollisionDetective detective;

	public CollisionStrategy(Circle a, Circle b){
		detective = new CircleVsCircle(a,b);
	}
	
	public CollisionStrategy(Box a, Box b){
		detective = new BoxVsBox(a,b);
	}
	
	public Boolean areObjectsColliding(){
		return detective.areObjectsColliding();
	}
	
	public void resolveCollision(){
		detective.resolveCollision();
	}
	
}
