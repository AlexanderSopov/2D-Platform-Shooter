package edu.chl.Game.entity;

import java.awt.Rectangle;

public class CalculateBounds {
	private UnitProperties unitProperties;

	public CalculateBounds(UnitProperties unitProperties) {
		this.unitProperties = unitProperties;

	}

	public Rectangle getBounds() {
		return new Rectangle(unitProperties.getX(), unitProperties.getY(),
				unitProperties.getWidth(), unitProperties.getHeight());
	}

	public Rectangle getBoundsTop() {
		return new Rectangle(unitProperties.getX() + 10, unitProperties.getY(),
				unitProperties.getWidth() - 20, 5);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle(unitProperties.getX() + 10, unitProperties.getY()
				+ unitProperties.getHeight() - 5,
				unitProperties.getWidth() - 20, 5);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle(unitProperties.getX(), unitProperties.getY() + 10,
				5, unitProperties.getHeight() - 20);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle(unitProperties.getX() + unitProperties.getWidth()
				- 5, unitProperties.getY() + 10, 5,
				unitProperties.getHeight() - 20);
	}

}
