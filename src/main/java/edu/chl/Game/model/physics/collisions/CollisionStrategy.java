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
	
	
	protected abstract void specialTrick(GotHitOnThe sideGotHit);
	
	
	
	
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
			return distanceOnX() < sumOfHalfWidths();
	}
	
	private int distanceOnX() {
		return Math.abs(go2.getCenterX() - go1.getCenterX());
	}
	
	private int sumOfHalfWidths() {
		return (go1.getWidth() + go2.getWidth())
				/2;
	}
	
	

	
	private boolean intersectOnY() {
		return distanceOnY() < sumOfHalfHeights();
	}

	private int sumOfHalfHeights() {
		return (go1.getHeight() + go2.getHeight())
				/2;
	}

	private int distanceOnY() {
		return Math.abs(go2.getCenterY() - go1.getCenterY());
	}

	
	
	
	
	
	
	
	
	
	
	private void solveCollision() {
		GotHitOnThe side = whichSideGotHit();
		correctPosition(side);
		
		System.out.println("Got hiiit on the " + side + ", mothafacka!");
		specialTrick(side);
	}

	private GotHitOnThe whichSideGotHit() {
		if(collidedFromTop())
			return GotHitOnThe.Bottom;
		if(collidedFromBottom())
			return GotHitOnThe.Top;
		if(collidedFromLeft())
			return GotHitOnThe.Right;
		else
			return GotHitOnThe.Left;
	}

	private void correctPosition(GotHitOnThe side) {
		// TODO Auto-generated method stub
		
	}


	




}
