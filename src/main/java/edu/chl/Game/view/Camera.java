package edu.chl.Game.view;


import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.physics.Vector2D;

public class Camera {
	private static final int width = 400, height = 350;
	Vector2D origo = new Vector2D();
	Vector2D maMax = new Vector2D();
	Vector2D maMin = new Vector2D(width,height);
	Vector2D plMax;
	Vector2D plMin;
	
	
	public void update(Entity player) {
		plMax = player.getPosition();
		plMin = plMax.addWith(player.getWidth(), player.getHeight());
		correctMoveArea();
		setOrigo();
	}
	


	private void setOrigo() {
		int x = (Frame.WIDTH - width)/2;
		int y = (Frame.HEIGHT - height)/2;
		origo = maMax.addWith(-x, -100);
		origo = origo.returnNegative();
	}

	
	
	private void correctMoveArea() {
		correctXDependingOnSide();
		correctYDependingOnSide();
	}

	private void correctXDependingOnSide(){
		if (outOfBoundsLeft()){
			maMax.setX(plMax.getX());
			maMin.setX(maMax.getX()+width);
		}else if (outOfBoundsRight()){
			maMin.setX(plMin.getX());
			maMax.setX(maMin.getX()-width);
		}
	}


	private boolean outOfBoundsLeft() {
		return plMax.getX() < maMax.getX();
	}
	
	private boolean outOfBoundsRight(){
		return maMin.getX() < plMin.getX();
	}


	private void correctYDependingOnSide(){
		if (outOfBoundsTop()){
			maMax.setY(plMax.getY());
			maMin.setY(maMax.getY()+height);
		}else if (outOfBoundsBottom()){
			maMin.setY(plMin.getY());
			maMax.setY(maMin.getY()-height);
		}
	}

	private boolean outOfBoundsTop(){
		return plMax.getY() < maMax.getY();
	}
	
	private boolean outOfBoundsBottom(){
		return maMin.getY() < plMin.getY();
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


}
