package edu.chl.Game.entity;

import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class CollisionDetection {
	/*
	@Override
	public void update() {
		x += velX;
		y += velY;

		if (velX != 0) {
			animate = true;
		} else {
			animate = false;
		}

		// checks if objects collides
		for (Tile t : handler.getTileList()) {
			if (t.solid) {
				if (t.getId() == Id.wall) {
					if (getBoundsTop().intersects(t.getBounds())) {
						setVelY(0);
						if (jumping) {
							jumping = false;
							gravity = 0.8;
							falling = true;
						}
					} else if (getBoundsBottom().intersects(t.getBounds())) {
						setVelY(0);
						if (falling) {
							falling = false;
						}

						if (!falling && !jumping) {
							gravity = 0.8;
							falling = true;
						}
					} else if (getBoundsLeft().intersects(t.getBounds())) {
						setVelX(0);
						x = t.getX() + t.width;
					} else if (getBoundsRight().intersects(t.getBounds())) {
						setVelX(0);
						x = t.getX() - t.width;
					}
				}
			}
		}
		
		*/

}
