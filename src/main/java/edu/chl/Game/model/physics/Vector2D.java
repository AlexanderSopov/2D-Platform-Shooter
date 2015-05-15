package edu.chl.Game.model.physics;

/*
 * Author: Alexander Sopov
 */

public class Vector2D{

	private int x, y;

	
	public Vector2D(int x, int y) {
		this.x=x;
		this.y=y;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	

	public void setVector(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	
	
	//Vector Algebra
	
	public Vector2D scale(int s){
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
	public Vector2D addWith(int x, int y){
		return addWith(new Vector2D(x, y));
	}
	
	public Vector2D subtractWith(Vector2D p){
		return new Vector2D(x-p.getX(), y-p.getY());
	}
	
	public Vector2D returnNegative(){
		return new Vector2D(-x,-y);
	}
	
	
	/*
	 * Reserved for Vectors of type double,
	 *
	 *	public Vector2D makeUnitVector(){
	 *		double length = length();
	 *		return new Vector2D(x/length, y/length);
	 *	}
	 *
	 */
	
	
}
