package edu.chl.Game.tile;

import java.awt.Rectangle;

import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.GameObject;
import edu.chl.Game.object.Id;

public abstract class Tile extends GameObject {

	public Tile(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	public Rectangle getBounds() {
		return new Rectangle(getUnitProperties().getX(), getUnitProperties()
				.getY(), getUnitProperties().getWidth(), getUnitProperties()
				.getHeight());
	}

	public void remove() {
		getHandler().removeTile(this);
	}

}
