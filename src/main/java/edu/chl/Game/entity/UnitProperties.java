package edu.chl.Game.entity;

import edu.chl.Game.Vector.Vector2D;
import edu.chl.Game.handler.GameHandler;

public class UnitProperties {
	
	private Vector2D position;
	private Vector2D velocity;
	private double width, height;
	private GameHandler handler;
	
	public UnitProperties(GameHandler handler, double d, double e, int width, int height){
		setPosition(d, e);
		setVelocity(0,0);
		this.width = width;
		this.height = height;
		this.handler = handler;
	}
	
	public Vector2D getVelocity(){
		return velocity;
	}

	
	public void setVelocity(double x, double y){
		setVelocity(new Vector2D(x,y));
	}

	public void setVelocity(Vector2D vector2d) {
		velocity = vector2d;
	}

	public Vector2D getPosition(){
		return position;
	}

	public double getX(){
		return position.getX();
	}
	
	public double getY(){
		return position.getY();
	}
	
	public void setPosition(double x, double y){
		setPosition(new Vector2D(x,y));
	}

	public void setPosition(Vector2D vector2d) {
		position = vector2d;
	}
	
	public double getWidth(){
		return width;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public double getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public GameHandler getHandler(){
		return handler;
	}

	public Vector2D getCenter() {
		double x = getX() + (width/2);
		double y = getY() + (height/2);
		return new Vector2D(x,y);
	}
	
}
