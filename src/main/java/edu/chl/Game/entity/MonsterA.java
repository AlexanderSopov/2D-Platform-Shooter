package edu.chl.Game.entity;

import java.awt.Graphics;
import java.util.Random;

import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class MonsterA extends Entity {

	private Sprite monster[] = new Sprite[12];
	private Random rand = new Random();


	public MonsterA(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler, int type) {
		super(x, y, width, height, solid, id, handler);

		int r = rand.nextInt(2);

		if (true) {
			getUnitProperties().setVelX(2);
			getEntityProperties().setFrame(0);
		} else if (r == 1) {
			getUnitProperties().setVelX(-2);
			getEntityState().setFacingDirection(FacingDirection.FacingRight);
		}

		if (type == 1) {
			// facing right
			for (int i = 0; i < 6; i++) {
				monster[i] = new Sprite(handler.getSheetEnemyUnitGrid0(), i, 0,
						64, 64);
			}
			// facing left
			for (int i = 0; i < 6; i++) {
				monster[i + 6] = new Sprite(handler.getSheetEnemyUnitGrid0(),
						i, 1, 64, 64);
			}
		} else if (type == 2) {
			// facing right
			for (int i = 0; i < 6; i++) {
				monster[i] = new Sprite(handler.getSheetEnemyUnitGrid0(), i, 0,
						64, 64);
			}
			// facing left
			for (int i = 0; i < 6; i++) {
				monster[i + 6] = new Sprite(handler.getSheetEnemyUnitGrid0(),
						i, 1, 64, 64);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(getEntityState().getFacingDirection()==FacingDirection.FacingRight){
			getRenderClass().renderAnimateRight(g, monster, getEntityProperties().getFrame(), getUnitProperties().getX(), getUnitProperties().getY(), getUnitProperties().getWidth(), getUnitProperties().getHeight());
		} else if (getEntityState().getFacingDirection()==FacingDirection.FacingLeft){
			getRenderClass().renderAnimateLeft(g, monster, getEntityProperties().getFrame(), getUnitProperties().getX(), getUnitProperties().getY(), getUnitProperties().getWidth(), getUnitProperties().getHeight());
		}
		
	}

	@Override
	public void update() {
		getUpdateMovement().updateCoordinates();

		for (Tile t : getUnitProperties().getHandler().getTileList()) {
			if (t.getUnitState().isSolid()) {
				if (t.getUnitState().getId() == Id.wall) {
					if (getCalculateBounds().getBoundsBottom().intersects(t.getBounds())) {
						getUnitProperties().setVelY(0);

						if (getEntityState().isFalling()) {
							getEntityState().setFalling(false);
						}

						if (!getEntityState().isFalling()) {
							getEntityProperties().setGravity(0.8);
							getEntityState().setFalling(true);
						}

					} else if (getCalculateBounds().getBoundsLeft().intersects(t.getBounds())) {
						getUnitProperties().setVelX(2);
						getEntityState().setFacingDirection(FacingDirection.FacingRight);
					} else if (getCalculateBounds().getBoundsRight().intersects(t.getBounds())) {
						getUnitProperties().setVelX(-2);
						getEntityState().setFacingDirection(FacingDirection.FacingLeft);
					}
				}
			}
		}

		if (getEntityState().isFalling()) {
			//gravity += 0.1;
			getEntityProperties().setGravity(getEntityProperties().getGravity()+0.1);
			getUnitProperties().setVelY((int) getEntityProperties().getGravity());
		}

		if (!getUnitState().isAnimate()) {
			//frameDelay++;
			getEntityProperties().setFrameDelay(getEntityProperties().getFrameDelay()+1);
			if (getEntityProperties().getFrameDelay()>= 15) {
				getEntityProperties().setFrame(getEntityProperties().getFrame()+1);
				if (6 <= getEntityProperties().getFrame()) {
					getEntityProperties().setFrame(0);
				}
				getEntityProperties().setFrameDelay(0);
			}
		}

	}

}
