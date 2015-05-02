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
			velX = 2;
			setFrame(0);
		} else if (r == 1) {
			velX = -2;
			setFacing(1);
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
		if(getFacing()==0){
			getRenderClass().renderAnimateRight(g, monster, getFrame(), getX(), getY(), width, height);
		} else if (getFacing() == 1){
			getRenderClass().renderAnimateLeft(g, monster, getFrame(), getX(), getY(), width, height);
		}
		
	}

	@Override
	public void update() {
		setX(getX() + getVelX());
		setY(getY() + getVelY());

		for (Tile t : handler.getTileList()) {
			if (t.solid) {
				if (t.getId() == Id.wall) {
					if (getBoundsBottom().intersects(t.getBounds())) {
						setVelY(0);

						if (isFalling()) {
							setFalling(false);
						}

						if (!isFalling()) {
							setGravity(0.8);
							setFalling(true);
						}

					} else if (getBoundsLeft().intersects(t.getBounds())) {
						setVelX(2);
						setFacing(0);
					} else if (getBoundsRight().intersects(t.getBounds())) {
						setVelX(-2);
						setFacing(1);
					}
				}
			}
		}

		if (isFalling()) {
			//gravity += 0.1;
			setGravity(getGravity()+0.1);
			setVelY((int) getGravity());
		}

		if (!isAnimate()) {
			//frameDelay++;
			setFrameDelay(getFrameDelay()+1);
			if (getFrameDelay()>= 15) {
				setFrame(getFrame()+1);
				if (6 <= getFrame()) {
					setFrame(0);
				}
				setFrameDelay(0);
			}
		}

	}

}
