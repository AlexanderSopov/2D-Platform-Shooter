package edu.chl.Game.handler;

import edu.chl.Game.entity.Entity;
import edu.chl.Game.view.Frame;

public class Camera {

	public int x, y;

	public void update(Entity player) {
		setX(-player.getUnitProperties().getX() + Frame.WIDTH / 2);
		setY(-player.getUnitProperties().getY() + Frame.HEIGHT / 2 + 100);
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
