package edu.chl.Game.handler;

import edu.chl.Game.entity.Entity;
import edu.chl.Game.view.Frame;

public class Camera {

	public double x, y;

	public void update(Entity player) {
		setX(-player.getUnitProperties().getX() + Frame.WIDTH / 2);
		setY(-player.getUnitProperties().getY() + Frame.HEIGHT / 2 + 100);
	}

	public double getX() {
		return x;
	}

	public void setX(double d) {
		this.x = d;
	}

	public double getY() {
		return y;
	}

	public void setY(double d) {
		this.y = d;
	}

}
