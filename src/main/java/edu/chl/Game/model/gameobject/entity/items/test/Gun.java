package edu.chl.Game.model.gameobject.entity.items.test;

import java.awt.Graphics;
import java.util.Random;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.items.Item;

import edu.chl.Game.view.graphics.Sprite;

/**
 * Visible Guns on the map that can be used by the Player.
 */
public class Gun extends Item {
	
	private Random rand;
	private Sprite guns;
	private int direction;
	
	public Gun(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, id, handler);
		

		//guns = new Sprite(handler.getSheetGun(), 0, 0, 30, 19);

		rand = new Random();
		setDirection(rand.nextInt(2));
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(guns.getBufferedImage(), getX(), getY(), null); 
	}
	
	@Override
	public void update() {

		
		setVelX(2);

		
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
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void effect() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getArmor() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void equippedRender(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void equippedUpdate() {
		// TODO Auto-generated method stub
		
	}
}
