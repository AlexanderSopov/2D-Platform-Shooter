package edu.chl.Game.model.physics.collisions;

import java.awt.Rectangle;

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


	
	
	protected boolean areColliding() {
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
		
		//System.out.println("Got hiiit on the " + side + ", mothafacka!");
	
		specialTrick(side);
	}

	
	private GotHitOnThe whichSideGotHit() {
		Rectangle entity = BoundingBoxes.getBounds(go1);
		if(collidedOnBottom(entity))
			return GotHitOnThe.Bottom;
		if(collidedOnTop(entity))
			return GotHitOnThe.Top;
		if(collidedOnRight(entity))
			return GotHitOnThe.Right;
		else
			return GotHitOnThe.Left;
	}

	private boolean collidedOnRight(Rectangle entity) {
		return entity.intersects(BoundingBoxes.getBoundsLeft(go2));
	}


	private boolean collidedOnTop(Rectangle entity) {
		return entity.intersects(BoundingBoxes.getBoundsBottom(go2));
	}


	private boolean collidedOnBottom(Rectangle entity) {
		
		return entity.intersects(BoundingBoxes.getBoundsTop(go2));
	}


	private void correctPosition(GotHitOnThe side) {
		if (side == GotHitOnThe.Bottom){
			go1.setY(go2.getY()-go1.getHeight());
			go1.setVelY(-1);
		}else if (side == GotHitOnThe.Top)
			go1.setY(go2.getY()+go2.getHeight());
		else if (side == GotHitOnThe.Right)
			go1.setX(go2.getX()-go1.getWidth());
		else if (side == GotHitOnThe.Left)
			go1.setX(go2.getX()+go2.getWidth());

	}


	




}
