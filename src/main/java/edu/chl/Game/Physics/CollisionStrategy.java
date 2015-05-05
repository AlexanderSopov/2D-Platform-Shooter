package edu.chl.Game.Physics;

import edu.chl.Game.object.GameObject;
import edu.chl.Test.GameObject.Box;
import edu.chl.Test.GameObject.Circle;

/**
*
* @author Alexander Sopov
*/
public class CollisionStrategy {
	CollisionDetective detective;

	public CollisionStrategy(Circle a, Circle b){
		detective = new CircleVsCircle(a,b);
	}
	public CollisionStrategy(Box a, Box b){
		detective = new BoxVsBox(a,b);
	}
	
	public CollisionStrategy(GameObject a, GameObject b){
		detective = new GameObjectVsGameObject(a,b);
	}
	
	public Boolean areObjectsColliding(){
		return detective.areObjectsColliding();
	}
	
	public void resolveCollision(){
		detective.resolveCollision();
	}
	
}
