package edu.chl.Game.model.gameobject;

import edu.chl.Game.model.physics.Vector2D;

public class PhysicalProperties {
    private Vector2D position;
	private Vector2D velocity;
	private int width, height;
	
	protected PhysicalProperties(Vector2D position, Vector2D velocity, int width, int height){
		this.position = position;
		this.velocity = velocity;
		this.width = width;
		this.height = height;
	}
	
	
	/*
	 * GETTERS
	 */
	
	
	//Position
	
	protected Vector2D getPosition(){
		return position;
	}
	
	protected Vector2D getCenter(){
		return position.addWith(new Vector2D(
				width/2,
				height/2
				));
	}
	
	protected int getX() {
		return (int)position.getX();
	}

	protected int getY() {
		return (int)position.getY();
	}

	
	//Velocity
	
	protected Vector2D getVelocity(){
		return velocity;
	}
	
	protected int getVelX(){
		return (int) velocity.getX();
	}
	protected int getVelY(){
		return (int) velocity.getY();
	}
	
	
	//Size
	
	protected int getWidth() {
		return this.width;
	}

	protected int getHeight() {
		return this.height;
	}
	
	
	/*
	 * SETTERS
	 */
	
	
	//Position
	
	protected void setPosition(Vector2D v){
		position = v;
	}
	
	protected void setX(int x) {
		position.setX(x);
	}

	protected void setY(int y) {
		position.setY(y);;
	}

	
	//Velocity
	
	protected void setVelocity(Vector2D v){
		velocity = v;
	}

	protected void setVelX(int velX){
		velocity.setX(velX);
	}
	
	protected void setVelY(int velY){
		velocity.setY(velY);
	}
	



	

}


