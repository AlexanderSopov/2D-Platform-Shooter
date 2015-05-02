/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.entity;

import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.view.Frame;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Rasmus
 */
public class Bullet extends Entity {

	private int targetPosX, targetPosY, speed;
	private double angle;

	public Bullet(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler, int targetPosX, int targetPosY, int speed) {
		super(x, y, width, height, solid, id, handler);

		this.setTargetPosX(targetPosX);
		this.setTargetPosY(targetPosY);
		this.speed = speed;
		System.out.println("");

		angle = Math.toDegrees(Math.atan2(targetPosY - y, targetPosX - x));
		angle = (int) angle;
		if (angle < 0) {
			// angle += 360;
		}
		// System.out.println("a:"+ angle);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(getUnitProperties().getX()
				- (getUnitProperties().getWidth() / 2), getUnitProperties()
				.getY() - (getUnitProperties().getHeight() / 2),
				getUnitProperties().getWidth(), getUnitProperties().getHeight());
		// g.drawString(""+ angle, x, y);

	}

	@Override
	public void update() {

		int varX = getUnitProperties().getX();
		int varY = getUnitProperties().getY();

		varX += (float) (Math.cos(Math.toRadians(angle))) * speed;
		varY += (float) (Math.sin(Math.toRadians(angle))) * speed;

		getUnitProperties().setX(varX);
		getUnitProperties().setY(varY);

		if (getUnitProperties().getX() <= 0 || getUnitProperties().getY() <= 0 || getUnitProperties().getX() > Frame.WIDTH
				|| getUnitProperties().getY() > Frame.HEIGHT) {
			// this.remove();

		}

	}

	public void moveToTarget() {

	}

	public int getTargetPosX() {
		return targetPosX;
	}

	private void setTargetPosX(int targetPosX) {
		this.targetPosX = targetPosX;
	}

	public int getTargetPosY() {
		return targetPosY;
	}

	private void setTargetPosY(int targetPosY) {
		this.targetPosY = targetPosY;
	}

	public int getSpeed() {
		return speed;
	}

}
