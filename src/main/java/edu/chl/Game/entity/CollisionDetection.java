package edu.chl.Game.entity;

import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class CollisionDetection {
/*	
	public void checkForCollision(){
		for (Tile t : getHandler().getTileList()) {
			if (t.isSolid()) {
				if (t.getId() == Id.wall) {
					if (getBoundsTop().intersects(t.getBounds())) {
						getUnitProperties().setVelY(0);
						if (isJumping()) {
							setJumping(false);
							setGravity(0.8);
							setFalling(true);
						}
					} else if (getBoundsBottom().intersects(t.getBounds())) {
						getUnitProperties().setVelY(0);
						if (isFalling()) {
							setFalling(false);
						}

						if (!isFalling() && !isJumping()) {
							setGravity(0.8);
							setFalling(true);
						}
					} else if (getBoundsLeft().intersects(t.getBounds())) {
						getUnitProperties().setVelX(0);
						getUnitProperties().setX((t.getUnitProperties().getX() + t.getUnitProperties().getWidth()));
					} else if (getBoundsRight().intersects(t.getBounds())) {
						getUnitProperties().setVelX(0);
						getUnitProperties().setX((t.getUnitProperties().getX() - t.getUnitProperties().getWidth()));
					}
				}
			}
		}
	}
*/
}
