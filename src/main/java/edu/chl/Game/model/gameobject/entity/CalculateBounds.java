package edu.chl.Game.model.gameobject.entity;

import edu.chl.Game.model.gameobject.GameObject;

import java.awt.Rectangle;

public class CalculateBounds {
	private UnitProperties unitProperties;
        private GameObject go;

	public CalculateBounds(GameObject go) {
		this.unitProperties = go.getUnitProperties();
                this.go = go;
	}

	public Rectangle getBounds() {
		return new Rectangle(go.getX(), go.getY(),
				go.getWidth(), go.getHeight());
	}

	public Rectangle getBoundsTop() {
		return new Rectangle(go.getX() + 10, go.getY(),
				go.getWidth() - 20, 5);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle(go.getX() + 10, go.getY() + go.getHeight() - 5, go.getWidth() - 20, 5);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle(go.getX(), go.getY() + 10,
				5, unitProperties.getHeight() - 20);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle(go.getX() + go.getWidth()
				- 5, go.getY() + 10, 5,
				go.getHeight() - 20);
	}

}
