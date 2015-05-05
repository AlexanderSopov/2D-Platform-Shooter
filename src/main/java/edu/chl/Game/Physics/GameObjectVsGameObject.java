package edu.chl.Game.Physics;

import java.awt.Graphics2D;

import edu.chl.Game.Vector.Vector2D;
import edu.chl.Game.object.GameObject;
import edu.chl.Test.GameObject.Box;

/**
*
* @author Alexander Sopov
*/
public class GameObjectVsGameObject implements CollisionDetective {
	private final GameObject a;
	private final GameObject b;
	private Vector2D normal;
	private Vector2D velocityNormal;
	private double invasionOnX;
	private double invasionOnY;
	private double aHalfWidth;
	private double bHalfWidth;
	private double aHalfHeight;
	private double bHalfHeight;
	private double halfHeights;
	private double halfWidths;
	
	public GameObjectVsGameObject(GameObject a, GameObject b){
		this.a=a;
		this.b=b;
		aHalfWidth = (double)(a.getWidth())/2;
		bHalfWidth = (double)(b.getWidth())/2;
		aHalfHeight = (double)(a.getHeight())/2;
		bHalfHeight =(double)( b.getHeight())/2;
		halfHeights = aHalfHeight + bHalfHeight;
		halfWidths = aHalfWidth + bHalfWidth;
	}
	
	@Override
	public Boolean areObjectsColliding() {
		normal = subtract(b.getUnitProperties().getCenter(), a.getUnitProperties().getCenter());
		invasionOnX = halfWidths - abs(normal.getX());
		invasionOnY = halfHeights - abs(normal.getY());
		return isTherePenetration();
	}
	
	@Override
	public void resolveCollision() {
		velocityNormal = b.getUnitProperties().getVelocity().addWith(
				a.getUnitProperties().getVelocity());
		double penetration = getPenetration();
		setNormal();
		double rVelocityLength = velocityNormal.dotProduct(normal);
		if (-0.3<rVelocityLength && rVelocityLength <0.3)
			return;
		setVelocityNormal();
		correctBoxes(rVelocityLength, penetration);
		
	}



	private double getPenetration() {
		if(xInvasionIsSmaller())
			return invasionOnX;
		return invasionOnY;
	}
	
	private void setNormal(){
		if (xInvasionIsSmaller()){
			if(normal.getX() < 0)
				normal = new Vector2D(-1,0);
			else
				normal = new Vector2D(1,0);
		}else{
			if(normal.getY() < 0)
				normal = new Vector2D(0,-1);
			else
				normal = new Vector2D(0,1);
		}
	}
	
	private void setVelocityNormal(){
		if(xInvasionIsSmaller())
			velocityNormal = new Vector2D(velocityNormal.getX(), -velocityNormal.getY());
		else
			velocityNormal = new Vector2D(-velocityNormal.getX(), velocityNormal.getY());
			
	}
	private boolean xInvasionIsSmaller() {
		return invasionOnX < invasionOnY;
	}

	private Boolean isTherePenetration() {
		return invasionOnX > 0 && invasionOnY > 0;
	}


	private double abs(double x) {
		if(x < 0)
			return -x;
		return x;
	}



	private void correctBoxes(double velocityLength, double penetration) {
		correctPositions(penetration);
		Vector2D impulse = calculateImpulse(velocityNormal.makeUnitVector(), velocityLength);
		setVelocityToRatio(impulse);
	}
	

	private void correctPositions(double penetration) {
		double percent = 0.2;
		double slop = 0.01;
		double corr;
		double sumMass = a.mass + b.mass;
		double ratio = a.mass / sumMass;
		if (penetration - slop < 0.0)
			corr = 0;
		else
			corr = penetration - slop;
		
		Vector2D correction = normal.scale(percent*corr*ratio);
		a.setPosition((correction.subtractWith(a.getUnitProperties().getPosition())).returnNegative());
		ratio = b.mass / sumMass;
		correction = normal.scale(percent*corr*ratio);
		b.setPosition(correction.addWith(b.getUnitProperties().getPosition()));

		
	}
	
	


	private void setVelocityToRatio(Vector2D impulse) {
		Vector2D scaledImpulse = impulse.scale(a.invMass);
		//System.out.println("scaledImpulse.Y = " + scaledImpulse.getY());
		Vector2D newVelocity = a.getUnitProperties().getVelocity().addWith(scaledImpulse);
		System.out.println("newVelocity: " + newVelocity.toString());
		a.getUnitProperties().setVelocity(
				a.getUnitProperties().getVelocity().addWith(
				new Vector2D(0,scaledImpulse.getY()))
										);

		scaledImpulse = impulse.scale(b.invMass);
		newVelocity = b.getUnitProperties().getVelocity().subtractWith(scaledImpulse);
		b.getUnitProperties().setVelocity(newVelocity);
	}
	
	
	private Vector2D calculateImpulse(Vector2D velocityNormal, double velocityLength) {
		double e = min(a.restitution, b.restitution); // the object with less "bounciness" wins
		double j = -(1 + e) * (velocityLength/2); // calculate an impulse scalar
		
		j /= (a.invMass + b.invMass);
		
		return velocityNormal.scale(j);
	}
	
	private static double min(double a, double b){
		if(a>b)
			return b;
		else
			return a;
	}
	
	
	private static Vector2D subtract(Vector2D a, Vector2D b){
		return  a.subtractWith(b);
	}




}
