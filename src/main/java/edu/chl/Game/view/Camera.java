package edu.chl.Game.view;

import java.awt.Rectangle;

import edu.chl.Game.model.gameobject.entity.Entity;

public class Camera {

	private int x = 0, y=0;
	Rectangle moveArea;
	Entity pl;
	
	
	public void update(Entity player) {
		pl = player;
		setX(Frame.WIDTH  / 2 		- player.getX());
		setY(Frame.HEIGHT / 2 + 100 - player.getY());
		
		moveArea = new Rectangle(x+200, y+200, 600, 400);
		//if (playerOutOfBounds())
			//correct();
	}
	
	private boolean playerOutOfBounds() {
		return !moveArea.intersects(new Rectangle(
				pl.getX(), pl.getY(), pl.getWidth(), pl. getHeight()));
	}
	
	private void correct() {
		x = correctX();
		y = correctY();
	}
	


	private int correctX() {
		return pl.getCenterX()-getCenterX() + halfWidths();
	}

	private int halfWidths() {
		return (int)(moveArea.getWidth()+pl.getWidth())/2;
	}

	private int correctY() {
		return pl.getCenterY()-getCenterY() + halfHeights();
	}

	private int halfHeights() {
		return (int)(moveArea.getHeight()+pl.getHeight())/2;
	}

	private int getCenterX(){
		return (int)(moveArea.getX() + moveArea.getWidth()/2);
	}
	
	private int getCenterY(){
		return (int)(moveArea.getY() + moveArea.getHeight()/2);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
