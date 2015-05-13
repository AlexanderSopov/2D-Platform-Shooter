package edu.chl.Game.model.physics;

public class PhysicalProperties {
    private Vector2D position;
	private Vector2D velocity;
	private int width, height;
	
	public PhysicalProperties(Vector2D position, Vector2D velocity, int width, int height){
		this.position = position;
		this.velocity = velocity;
		this.width = width;
		this.height = height;
	}
	public int getX() {
		return (int)position.getX();
	}

	public int getY() {
		return (int)position.getY();
	}

	public void setX(int x) {
		position.setX(x);
	}

	public void setY(int y) {
		position.setY(y);;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
	    
	public int getVelX(){
		return (int) velocity.getX();
	}

	public void setVelX(int velX){
		velocity.setX(velX);
	}

	public int getVelY(){
		return (int) velocity.getY();
	}

	public void setVelY(int velY){
		velocity.setY(velY);
	}

}


