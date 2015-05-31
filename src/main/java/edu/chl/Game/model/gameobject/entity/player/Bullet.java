package edu.chl.Game.model.gameobject.entity.player;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;

import edu.chl.Game.view.Frame;

import java.awt.Color;
import java.awt.Graphics;


/**
 *
 * @author Rasmus
 */
public class Bullet extends Entity {

	private int speed;
	private int centerX, centerY;
	private double angle;
	private int rotatedX, rotatedY;
	private int motionX, motionY;

	public Bullet(int x, int y, GameHandler handler, double angle, int offX, int offY) {
		super(x, y, 10, 10, true, Id.bullet, handler);

		this.angle = angle;
		this.speed = 10;
		
		
		this.centerX = getX();
		this.centerY = getY();
		this.motionX = getX() + offX;
		this.motionY = getY() + offY;
		rotatedX = (int) (Math.cos(angle) * (this.motionX - centerX)
				- Math.sin(angle) * (this.motionY - centerY) + centerX);
		rotatedY = (int) (Math.sin(angle) * (this.motionX - centerX)
				+ Math.cos(angle) * (this.motionY - centerY) + centerY);

	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillOval(rotatedX - (getWidth() / 2), rotatedY - (getHeight() / 2),
				getWidth(), getHeight());

	}

	@Override
	public void update() {
		super.update();
		this.motionX = this.motionX + this.speed;
		rotatedX = (int) (Math.cos(angle) * (this.motionX - centerX)
				- Math.sin(angle) * (this.motionY - centerY) + centerX);
		rotatedY = (int) (Math.sin(angle) * (this.motionX - centerX)
				+ Math.cos(angle) * (this.motionY - centerY) + centerY);

		setX((int) rotatedX);
		setY((int) rotatedY);

		if (Math.abs(getHandler().getPlayer().getCenterY() - getY()) > Frame.HEIGHT
				|| Math.abs(getHandler().getPlayer().getCenterX() - getX()) > Frame.WIDTH) {

			this.die();
		}

	}

}
