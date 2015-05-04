package edu.chl.Game.entity;

import edu.chl.Game.Vector.Vector2D;
import edu.chl.Game.handler.GameHandler;

public class UnitProperties {
	
	private Vector2D position;
	private Vector2D velocity;
	private double width, height;
	private GameHandler handler;
	
	public UnitProperties(GameHandler handler, int x, int y, int width, int height){
		setVelocity(x,y);
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

	private void setVelocity(Vector2D vector2d) {
		velocity = vector2d;
	}

	public Vector2D getPosition(){
		return position;
	}

	
	public void setPosition(double x, double y){
		setPosition(new Vector2D(x,y));
	}

	private void setPosition(Vector2D vector2d) {
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
	
}
