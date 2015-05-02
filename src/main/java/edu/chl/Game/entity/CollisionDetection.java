package edu.chl.Game.entity;

import java.util.LinkedList;

import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class CollisionDetection {
	
	public void update() {
		setX(getX() + getVelX());
		setY(getY() + getVelY());

		if (getVelX() != 0) {
			setAnimate(true);
		} else {
			setAnimate(false);
		}

		// checks if objects collides
		for (Tile t : getHandler().getTileList()) {
			if (t.isSolid()) {
				if (t.getId() == Id.wall) {
					if (getBoundsTop().intersects(t.getBounds())) {
						setVelY(0);
						if (isJumping()) {
							setJumping(false);
							setGravity(0.8);
							setFalling(true);
						}
					} else if (getBoundsBottom().intersects(t.getBounds())) {
						setVelY(0);
						if (isFalling()) {
							setFalling(false);
						}

						if (!isFalling() && !isJumping()) {
							setGravity(0.8);
							setFalling(true);
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

		LinkedList<Entity> e = handler.getEntityList();
		for (int i = 0; i < e.size(); i++) {
			if (e.get(i).getId() == Id.monster) {
				if (getBounds().intersects(e.get(i).getBoundsTop())) {
					e.remove();
				} else if (getBounds().intersects(e.get(i).getBounds())) {
					remove();
				}
			}
		}

		if (isJumping()) {
			setGravity(getGravity() - 0.1);
			setVelY((int) -getGravity());
			if (getGravity() <= 0.0) {
				setFalling(true);
				setJumping(false);
			}
		}

		if (isFalling()) {
			setGravity(getGravity() + 0.1);
			setVelY((int) getGravity());
		}

		if (isAnimate()) {
			setFrameDelay(getFrameDelay()+1);
			if (getFrameDelay()>= 3) {
				setFrame(getFrame()+1);
				if (6 <= getFrame()) {
					setFrame(0);
				}
				setFrameDelay(0);
			}
		}
	}
	
	
	

}
