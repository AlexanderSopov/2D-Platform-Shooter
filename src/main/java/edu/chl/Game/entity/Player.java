package edu.chl.Game.entity;

import java.awt.Graphics;
import java.util.LinkedList;

import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class Player extends Entity {

	private Sprite player[] = new Sprite[12];


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
		if (getUnitState().isAnimate()) {
			if (getFacingDirection() == FacingDirection.FacingRight) {
				getRenderClass().renderAnimateRight(g, player, getFrame(), getUnitProperties().getX(), getUnitProperties().getY(), getUnitProperties().getHeight(), getUnitProperties().getWidth());
			} else if (getFacingDirection() == FacingDirection.FacingLeft) {
				getRenderClass().renderAnimateLeft(g, player, getFrame(), getUnitProperties().getX(), getUnitProperties().getY(),
						getUnitProperties().getHeight(), getUnitProperties().getWidth());
			}
		}

		else if (!getUnitState().isAnimate()) {
			if (getFacingDirection() == FacingDirection.FacingRight) {
				getRenderClass().renderNotAnimateRight(g, player, getFrame(), getUnitProperties().getX(), getUnitProperties().getY(),
						getUnitProperties().getHeight(), getUnitProperties().getWidth());
			}

			else if (getFacingDirection() == FacingDirection.FacingLeft) {
				getRenderClass().renderNotAnimateLeft(g, player, getFrame(), getUnitProperties().getX(), getUnitProperties().getY(),
						getUnitProperties().getHeight(), getUnitProperties().getWidth());
			}
		}
	}

	@Override
	public void update() {
		
		getUpdateMovement().updateCoordinates();
		getUpdateMovement().toggleAnimate();

		// checks if objects collides
		for (Tile t : getHandler().getTileList()) {
			if (t.getUnitState().isSolid()) {
				if (t.getUnitState().getId() == Id.wall) {
					if (getCalculateBounds().getBoundsTop().intersects(t.getBounds())) {
						getUnitProperties().setVelY(0);
						if (isJumping()) {
							setJumping(false);
							setGravity(0.8);
							setFalling(true);
						}
					} else if (getCalculateBounds().getBoundsBottom().intersects(t.getBounds())) {
						getUnitProperties().setVelY(0);
						if (isFalling()) {
							setFalling(false);
						}

						if (!isFalling() && !isJumping()) {
							setGravity(0.8);
							setFalling(true);
						}
					} else if (getCalculateBounds().getBoundsLeft().intersects(t.getBounds())) {
						getUnitProperties().setVelX(0);
						getUnitProperties().setX((t.getUnitProperties().getX() + t.getUnitProperties().getWidth()));
					} else if (getCalculateBounds().getBoundsRight().intersects(t.getBounds())) {
						getUnitProperties().setVelX(0);
						getUnitProperties().setX((t.getUnitProperties().getX() - t.getUnitProperties().getWidth()));
					}
				}
			}
		}

		LinkedList<Entity> e = getHandler().getEntityList();
		for (int i = 0; i < e.size(); i++) {
			if (e.get(i).getUnitState().getId() == Id.monster) {
				if (getCalculateBounds().getBounds().intersects(e.get(i).getCalculateBounds().getBoundsTop())) {
					e.remove();
				} else if (getCalculateBounds().getBounds().intersects(e.get(i).getCalculateBounds().getBounds())) {
					remove();
				}
			}
		}

		if (isJumping()) {
			setGravity(getGravity() - 0.1);
			getUnitProperties().setVelY(((int) -getGravity()));
			if (getGravity() <= 0.0) {
				setFalling(true);
				setJumping(false);
			}
		}

		if (isFalling()) {
			setGravity(getGravity() + 0.1);
			getUnitProperties().setVelY(((int) getGravity()));
		}

		if (getUnitState().isAnimate()) {
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
