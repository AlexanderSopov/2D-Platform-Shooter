package edu.chl.Game.view;


import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.physics.Vector2D;

public class Camera {
	private static final int width = 400, height = 350;
	private static Vector2D origo = new Vector2D();
	private static Vector2D maMax = new Vector2D();
	private static Vector2D maMin = new Vector2D(width,height);
	private static Vector2D plMax;
	private static Vector2D plMin;
	
	
	public static void update(Entity player) {
		plMax = player.getPosition();
		plMin = plMax.addWith(player.getWidth(), player.getHeight());
		correctMoveArea();
		setOrigo();
	}
	


	private static void setOrigo() {
		int x = (Frame.WIDTH - width)/2;
		int y = (Frame.HEIGHT - height)/2;
		origo = maMax.addWith(-x, -100);
		origo = origo.returnNegative();
	}

	
	
	private static void correctMoveArea() {
		correctXDependingOnSide();
		correctYDependingOnSide();
	}

	private static void correctXDependingOnSide(){
		if (outOfBoundsLeft()){
			maMax.setX(plMax.getX());
			maMin.setX(maMax.getX()+width);
		}else if (outOfBoundsRight()){
			maMin.setX(plMin.getX());
			maMax.setX(maMin.getX()-width);
		}
	}


	private static boolean outOfBoundsLeft() {
		return plMax.getX() < maMax.getX();
	}
	
	private static boolean outOfBoundsRight(){
		return maMin.getX() < plMin.getX();
	}


	private static void correctYDependingOnSide(){
		if (outOfBoundsTop()){
			maMax.setY(plMax.getY());
			maMin.setY(maMax.getY()+height);
		}else if (outOfBoundsBottom()){
			maMin.setY(plMin.getY());
			maMax.setY(maMin.getY()-height);
		}
	}

	private static boolean outOfBoundsTop(){
		return plMax.getY() < maMax.getY();
	}
	
	private static boolean outOfBoundsBottom(){
		return maMin.getY() < plMin.getY();
	}
	

	public static Vector2D getPosition(){
		return origo.addWith(0,0); // add with zero in order to return a copy of origo and not origo itself.
	}
	
	public static int getX() {
		return origo.getX();
	}


	public static int getY() {
		return origo.getY();
	}

	
	
	
	public static void setY(int y) {
		origo.setY(y);
	}
	
	public static void setX(int x) {
		origo.setX(x);
	}

}
