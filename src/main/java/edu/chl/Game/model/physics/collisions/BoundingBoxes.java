package edu.chl.Game.model.physics.collisions;

import edu.chl.Game.model.gameobject.GameObject;
import java.awt.Rectangle;



public class BoundingBoxes {


	public static Rectangle getBounds(GameObject go) {
		return new Rectangle(go.getX(), go.getY(),
				go.getWidth(), go.getHeight());
	}

	public static Rectangle getBoundsTop(GameObject go) {
		return new Rectangle(go.getX() + 10, go.getY(),
				go.getWidth() - 20, 5);
	}

	public static Rectangle getBoundsBottom(GameObject go) {
		return new Rectangle(go.getX() + 10, go.getY() + go.getHeight() - 5, go.getWidth() - 20, 5);
	}

	public static Rectangle getBoundsLeft(GameObject go) {
		return new Rectangle(go.getX(), go.getY() + 10,
				5, go.getHeight() - 20);
	}

	public static Rectangle getBoundsRight(GameObject go) {
		return new Rectangle(go.getX() + go.getWidth()
				- 5, go.getY() + 10, 5,
				go.getHeight() - 20);
	}

}
