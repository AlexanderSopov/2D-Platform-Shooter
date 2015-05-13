package edu.chl.Game.model.physics;


public class Vector2D{

	private double x, y;

	
	public Vector2D(double d, double e) {
		this.x=x;
		this.y=y;
	}


	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	

	public void setVector(double x, double y) {
		this.x=x;
		this.y=y;
	}

	public void setX(double x){
		x=x;
	}
	
	public void setY(double y){
		y=y;
	}
	
	
	
	//Vector Algebra
	
	public Vector2D scale(double s){
		return new Vector2D(x*s, y*s);
	}
	
	public double length(){
		return Math.sqrt(lengthSquared());
	}
	
	public double lengthSquared(){
		return x*x+y*y;
	}

	public double dotProduct(Vector2D v){
		return x * v.getX() + y * v.getY();
	}
	
	public Vector2D addWith(Vector2D v){
		return new Vector2D(x+v.getX(), y+v.getY());
	}
	
	public Vector2D subtractWith(Vector2D p){
		return new Vector2D(x-p.getX(), y-p.getY());
	}
	
	public Vector2D returnNegative(){
		return new Vector2D(-x,-y);
	}
	
	public String toString(){
		return "x = " + x + ". y = " + y;
	}
	
	public Vector2D makeUnitVector(){
		double length = length();
		return new Vector2D(x/length, y/length);
	}
	
	
}
