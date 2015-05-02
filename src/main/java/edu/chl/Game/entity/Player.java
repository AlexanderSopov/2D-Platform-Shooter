package edu.chl.Game.entity;

import java.awt.Graphics;
import java.util.LinkedList;

import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class Player extends Entity {

	private Sprite player[] = new Sprite[12];

	private CollisionDetection collisionDetection = new CollisionDetection();

	public Player(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);

		for (int i = 0; i < 6; i++) {
			player[i] = new Sprite(handler.getSheetPlayer(), i, 0, 64, 64);
		}

		// facing left
		for (int i = 0; i < 6; i++) {
			player[i + 6] = new Sprite(handler.getSheetPlayer(), i, 1, 64, 64);
		}

	}

	@Override
	public void render(Graphics g) {
		if (isAnimate()) {
			if (getFacingDirection() == FacingDirection.FacingRight) {
				getRenderClass().renderAnimateRight(g, player, getFrame(), getX(), getY(), height, width);
			} else if (getFacingDirection() == FacingDirection.FacingLeft) {
				getRenderClass().renderAnimateLeft(g, player, getFrame(), x, y,
						height, width);
			}
		}

		else if (!isAnimate()) {
			if (getFacingDirection() == FacingDirection.FacingRight) {
				getRenderClass().renderNotAnimateRight(g, player, getFrame(), x, y,
						height, width);
			}

			else if (getFacingDirection() == FacingDirection.FacingLeft) {
				getRenderClass().renderNotAnimateLeft(g, player, getFrame(), x, y,
						height, width);
			}
		}
	}

	@Override
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
