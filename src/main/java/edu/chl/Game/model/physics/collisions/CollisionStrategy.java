package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.GameObject;
/*
 * Author: Alexander Sopov
 */

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
	
	
	protected abstract void specialTrick();
	
	
	
	
	public void solve(){
		if (areColliding())
			solveCollision();
	}


	
	
	private boolean areColliding() {
		if(intersectOnX())
			if(intersectOnY())
				return true;
		return false;
	}
	


	private boolean intersectOnX() {
			return distanceOnX() > sumOfWidths();
	}
	
	private int distanceOnX() {
		return Math.abs(go2.getCenterX() - go1.getCenterX());
	}
	
	private int sumOfWidths() {
		return go1.getWidth() + go2.getWidth();
	}
	
	

	
	private boolean intersectOnY() {
		return distanceOnY() > sumOfHeights();
	}

	private int sumOfHeights() {
		return go1.getHeight() + go2.getHeight();
	}

	private int distanceOnY() {
		return Math.abs(go2.getCenterY() - go1.getCenterY());
	}

	private void solveCollision() {
		GotHitOnThe sideGotHit;
		System.out.println("Got hiiit mothafacka!");
		specialTrick();
	}




}
