package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.GameObject;

public abstract class CollisionStrategy {
	private GameObject go1;
	private GameObject go2;
	
	protected enum GotHitOnThe{
		Top, Bottom, Left, Right;
	}
	
	public CollisionStrategy(GameObject obj1, GameObject obj2){
		go1 = obj1;
		go2 = obj2;
	}
	
	public void solve(){
		if (areColliding())
			solveCollision();
	}


	
	
	private boolean areColliding() {
		if(intersectOnX())
			if(intersectOnY)
				return true;
		return false;
	}
	


	private boolean intersectOnX() {
			return distanceOnX() > sumOfWidths();
	}

	private int distanceOnX() {
		return go1.getCenter().getX();
	}

	private void solveCollision() {
		GotHitOnThe sideGotHit;
	}




}
