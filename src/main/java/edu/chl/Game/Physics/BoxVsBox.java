package edu.chl.Game.Physics;

import edu.chl.Game.GameObject.Box;
import edu.chl.Game.Vector.Vector2D;

public class BoxVsBox implements CollisionDetective {
	private final Box a;
	private final Box b;
	private Vector2D normal;
	private double invasionOnX;
	private double invasionOnY;
	private double aHalfWidth;
	private double bHalfWidth;
	private double aHalfHeight;
	private double bHalfHeight;
	private double halfHeights;
	private double halfWidths;
	
	public BoxVsBox(Box a, Box b){
		this.a=a;
		this.b=b;
		aHalfWidth = (double)(a.getShape().getWidth())/2;
		bHalfWidth = (double)(b.getShape().getWidth())/2;
		aHalfHeight = (double)(a.getShape().getHeight())/2;
		bHalfHeight =(double)( b.getShape().getHeight())/2;
		halfHeights = aHalfHeight + bHalfHeight;
		halfWidths = aHalfWidth + bHalfWidth;
	}
	
	@Override
	public Boolean areObjectsColliding() {
		normal = subtract(b.getCenter(), a.getCenter());
		invasionOnX = halfWidths - abs(normal.getX());
		invasionOnY = halfHeights - abs(normal.getY());
		return isTherePenetration();
	}
	
	@Override
	public void resolveCollision() {
		Vector2D relativeVelocity = b.getVelocity().addWith(a.getVelocity());
		double rVelocityLength = relativeVelocity.length();
		normal = relativeVelocity.scale(1/rVelocityLength);
		correctBoxes(rVelocityLength);
		
	}



	private Boolean isTherePenetration() {
		return invasionOnX > 0 && invasionOnY > 0;
	}


	private double abs(double x) {
		if(x < 0)
			return -x;
		return x;
	}



	private void correctBoxes(double velNormal) {
		correctPositions();
		Vector2D impulse = calculateImpulse(normal, velNormal);
		setVelocityToRatio(impulse);
	}
	

	private void correctPositions() {
		double sumMass = a.mass + b.mass;
		double ratio = a.mass / sumMass;
		double move = (getPenetration()+1) / normal.getY();
		System.out.println("move = " + move);
		Vector2D correction = normal.scale(move*ratio);
		System.out.println("correction.Y = " + correction.getY());

		
		a.setLocation(a.getLocation().subtractWith(correction));
		
		ratio = b.mass / sumMass;
		correction = normal.scale(move*ratio);
		b.setLocation(correction.addWith(b.getLocation()));
	}
	
	
	private double getPenetration() {
		if(invasionOnY > 50)
			return invasionOnX;
		else
			return invasionOnY;
		
	}

	private void setVelocityToRatio(Vector2D impulse) {
		Vector2D scaledImpulse = impulse.scale(a.invMass);
		//System.out.println("scaledImpulse.Y = " + scaledImpulse.getY());
		Vector2D newVelocity = a.getVelocity().addWith(scaledImpulse);
		//System.out.println("a.Velocity.Y = " + newVelocity.getY());
		a.setVelocity(newVelocity);

		scaledImpulse = impulse.scale(b.invMass);
		newVelocity = b.getVelocity().subtractWith(scaledImpulse);
		b.setVelocity(newVelocity);
	}
	
	
	private Vector2D calculateImpulse(Vector2D normal, double velNormal) {
		double e = min(a.restitution, b.restitution); // the object with less "bounciness" wins
		double j = -(1 + e) * velNormal; // calculate an impulse scalar
		
		j /= (a.invMass + b.invMass);
		System.out.println("VelNormal = " + velNormal);
		return normal.scale(j);
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

	private void setNormal() {
		if(invasionOnX < invasionOnY){
			if (normal.getX() > 0)
				normal = new Vector2D( -1, 0 );
			else
				normal = new Vector2D( 1, 0 );
		}else{
			if (normal.getY() > 0 )
				normal = new Vector2D(0,-1);
			else
				normal = new Vector2D(0,1);
		}
	}



}
