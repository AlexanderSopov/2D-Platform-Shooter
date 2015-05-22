package edu.chl.Game.view;


import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.physics.Vector2D;

public class Camera {

	Vector2D origo = new Vector2D();
	Vector2D maMax = new Vector2D();
	Vector2D maMin = new Vector2D(400,300);
	Vector2D plMax;
	Vector2D plMin;
	
	
	public void update(Entity player) {
		plMax = player.getPosition();
		plMin = plMax.addWith(player.getWidth(), player.getHeight());
		// printStatus(); // activate when troubleshooting!
		correctMoveArea();
		setOrigo();
	}
	


	private void setOrigo() {
		origo = maMax.addWith(-200, -100);
		origo = origo.returnNegative();
	}

	
	
	private void correctMoveArea() {
		if(outOfBoundsX())
			correctX();
		
		if(outOfBoundsY())
			correctY();
	}

	
	private boolean outOfBoundsX() {
		return plMax.getX() < maMax.getX() || maMin.getX() < plMin.getX();
	}

	private void correctX(){
		if (plMax.getX() < maMax.getX()){
			maMax.setX(plMax.getX());
			maMin.setX(maMax.getX()+600);
		}else{
			maMin.setX(plMin.getX());
			maMax.setX(maMin.getX()-600);
		}
	}
	
	private boolean outOfBoundsY() {
		return plMax.getY() < maMax.getY() || maMin.getY() < plMin.getY();
	}

	private void correctY(){
		if (plMax.getY() < maMax.getY()){
			maMax.setY(plMax.getY());
			maMin.setY(maMax.getY()+400);
		}else{
			maMin.setY(plMin.getY());
			maMax.setY(maMin.getY()-400);
		}
	}


	public int getX() {
		return origo.getX();
	}

	public void setX(int x) {
		origo.setX(x);
	}

	public int getY() {
		return origo.getY();
	}

	public void setY(int y) {
		origo.setY(y);
	}

	/*
	private void printStatus() {
		System.out.println("Player position: "
				+ "\nx = " + pl.getX()
				+ "\ny = " + pl.getY()
				+ "\nMove area: "
				+ "\nx = " + moveArea.x
				+ "\ny = " + moveArea.y);
	}
	

	private int halfWidths() {
		return (int)(moveArea.getWidth()+pl.getWidth())/2;
	}

	private int halfHeights() {
		return (int) ( moveArea.getHeight()+pl.getHeight() ) /2;
	}
	

	private int getCenterX(){
		return (int)(moveArea.getX() + moveArea.getWidth()/2 );
	}
	
	private int getCenterY(){
		return (int)(moveArea.getY() + moveArea.getHeight()/2 );
	}
	
		
	private int distanceOnY() {
		return  pl.getCenterY()-getCenterY();
	}

	private int distanceOnX() {
		return pl.getCenterX()-getCenterX();
	}
	
		private void correctX(int dDistance) {
		if(distanceOnX() < 0)
			moveArea.x =- dDistance;
		else
			moveArea.x =+ dDistance;
	}


	private void correctY(int dDistance) {
		System.out.println("dDistance on y = " + dDistance);
		if(distanceOnY() < 0)
			moveArea.y =- dDistance;
		else
			moveArea.y =+ dDistance;
	}
	*/
}
