package edu.chl.Game.model.gameobject.entity.player;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;

import edu.chl.Game.view.Frame;

import java.awt.Color;
import java.awt.Graphics;


/**
 * Bullet a projectile in the game which can go in any direction 
 *
 * @author Rasmus
 */
public class Bullet extends Entity {
	

	private int speed;
	private double angle;
	
	private int centerX, centerY;
	private int rotatedX, rotatedY;
	private int motionX, motionY;

	
	public Bullet(int x, int y, GameHandler handler, double angle, int offX, int offY) {
		super(x, y, 10, 10, true, Id.bullet, handler);

		//initialize the angle of the projectile and speed of 10
		this.angle = angle;
		this.speed = 10;
		
		
		//initialize the center coordinates
		this.centerX = getX();
		this.centerY = getY();
		
		//initialize the motion coordinates
		this.motionX = getX() + offX;
		this.motionY = getY() + offY;
		
		
		//Rotate coordinates with angle using Affintransformation 
		rotatedX = (int) (Math.cos(angle) * (this.motionX - centerX)
				- Math.sin(angle) * (this.motionY - centerY) + centerX);
		
		rotatedY = (int) (Math.sin(angle) * (this.motionX - centerX)
				+ Math.cos(angle) * (this.motionY - centerY) + centerY);

	}

	@Override
	public void render(Graphics g) {
		
		//Render a black bullet 
		g.setColor(Color.BLACK);
		g.fillOval(rotatedX - (getWidth() / 2), rotatedY - (getHeight() / 2),
				getWidth(), getHeight());

	}

	@Override
	public void update() {
		super.update();
		
		// Increase motion x coordinates with speed
		this.motionX = this.motionX + this.speed;
		
		//Rotate coordinates with angle using Affintransformation 
		rotatedX = (int) (Math.cos(angle) * (this.motionX - centerX)
				- Math.sin(angle) * (this.motionY - centerY) + centerX);
		rotatedY = (int) (Math.sin(angle) * (this.motionX - centerX)
				+ Math.cos(angle) * (this.motionY - centerY) + centerY);
		
		//set the resulting coordinates 
		setX((int) rotatedX);
		setY((int) rotatedY);
		
		// check if the bullet is outside the frame 
		if (Math.abs(getHandler().getPlayer().getCenterY() - getY()) > Frame.HEIGHT
				|| Math.abs(getHandler().getPlayer().getCenterX() - getX()) > Frame.WIDTH) {
			
			// remove bullet
			this.die();
		}

	}

}
