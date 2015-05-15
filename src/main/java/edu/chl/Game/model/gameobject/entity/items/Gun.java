package edu.chl.Game.model.gameobject.entity.items;

import java.awt.Graphics;
import java.util.Random;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.GravitationalProperties;
import edu.chl.Game.view.graphics.Sprite;

/**
 * Visible Guns on the map that can be used by the Player.
 */
public class Gun extends Items {
	
	private Random rand;
	private Sprite guns;
	private GravitationalProperties gravitationalProperties;
	private int direction;
	
	public Gun(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		
		guns = new Sprite(handler.getSheetGun(), 0, 0, 30, 19);
		gravitationalProperties =  new GravitationalProperties(this);
		rand = new Random();
		setDirection(rand.nextInt(2));
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(guns.getBufferedImage(), getX(), getY(), null); 
	}
	
	@Override
	public void update() {
		super.update();
		setVelX(2);
		getCollisionDetection().checkForCollision();
		getEntityProperties().setGravity(1.0);
		gravitationalProperties.fallingMechanics();
	}
	/**
	 * Set a random direction for the object
	 */
	private void setRandomDirection() {
		
		switch(getDirection()) {
			case 0:
				setVelX(0);
				break;
			case 1:
				setVelX(-2);
				break;
			case 2:
				setVelX(2);
				break;
		}
	}

	/**
	 * Set a direction for the object. 
	 * 0 - Sets the object at still
	 * 1 - Sets the object at direction negative x axis
	 * 2 - Sets the object at direction positive x axis
	 * @param direction - Parameter takes an integer between 0-2
	 * @throws IllegalArgumentException
	 */
	public void setDirection(int direction) throws IllegalArgumentException {
		
		if(direction > 2) {
			throw new IllegalArgumentException();
		} else {
			this.direction = direction;
		}
	}
	/**
	 * Getting a random direction.
	 * 
	 * @return this.direction - Returns an integer from 0-2.
	 */
	public int getDirection() {
		return this.direction;
	}
}
