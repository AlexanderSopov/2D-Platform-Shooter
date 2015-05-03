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
			if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
				getRenderClass().renderAnimateRight(g, player,
						getEntityProperties().getFrame(),
						getUnitProperties().getX(), getUnitProperties().getY(),
						getUnitProperties().getHeight(),
						getUnitProperties().getWidth());
			} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
				getRenderClass().renderAnimateLeft(g, player,
						getEntityProperties().getFrame(),
						getUnitProperties().getX(), getUnitProperties().getY(),
						getUnitProperties().getHeight(),
						getUnitProperties().getWidth());
			}
		}

		else if (!getUnitState().isAnimate()) {
			if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
				getRenderClass().renderNotAnimateRight(g, player,
						getEntityProperties().getFrame(),
						getUnitProperties().getX(), getUnitProperties().getY(),
						getUnitProperties().getHeight(),
						getUnitProperties().getWidth());
			}

			else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
				getRenderClass().renderNotAnimateLeft(g, player,
						getEntityProperties().getFrame(),
						getUnitProperties().getX(), getUnitProperties().getY(),
						getUnitProperties().getHeight(),
						getUnitProperties().getWidth());
			}
		}
	}

	@Override
	public void update() {

		getUpdateMovement().updateCoordinates();
		getUpdateMovement().toggleAnimate();

		getCollisionDetection().checkForCollision();

		LinkedList<Entity> e = getUnitProperties().getHandler().getEntityList();
		for (int i = 0; i < e.size(); i++) {
			if (e.get(i).getUnitState().getId() == Id.monster) {
				if (getCalculateBounds().getBounds().intersects(
						e.get(i).getCalculateBounds().getBoundsTop())) {
					e.remove();
				} else if (getCalculateBounds().getBounds().intersects(
						e.get(i).getCalculateBounds().getBounds())) {
					remove();
				}
			}
		}

		if (getEntityState().isJumping()) {
			getEntityProperties().setGravity(
					getEntityProperties().getGravity() - 0.1);
			getUnitProperties().setVelY(
					((int) -getEntityProperties().getGravity()));
			if (getEntityProperties().getGravity() <= 0.0) {
				getEntityState().setFalling(true);
				getEntityState().setJumping(false);
			}
		}

		if (getEntityState().isFalling()) {
			getEntityProperties().setGravity(
					getEntityProperties().getGravity() + 0.1);
			getUnitProperties().setVelY(
					((int) getEntityProperties().getGravity()));
		}

		if (getUnitState().isAnimate()) {
			getEntityProperties().setFrameDelay(
					getEntityProperties().getFrameDelay() + 1);
			if (getEntityProperties().getFrameDelay() >= 3) {
				getEntityProperties().setFrame(
						getEntityProperties().getFrame() + 1);
				if (6 <= getEntityProperties().getFrame()) {
					getEntityProperties().setFrame(0);
				}
				getEntityProperties().setFrameDelay(0);
			}
		}
	}

}
