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

		if (r == 0) {
			velX = 2;
			facing = 0;
		} else if (r == 1) {
			velX = -2;
			facing = 1;
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
		if(facing==0){
			getRenderClass().renderAnimateRight(g, monster, frame, x, y, width, height);
		} else if (facing == 1){
			getRenderClass().renderAnimateLeft(g, monster, frame, x, y, width, height);
		}
		
	}

	@Override
	public void update() {
		x += velX;
		y += velY;

		for (Tile t : handler.getTileList()) {
			if (t.solid) {
				if (t.getId() == Id.wall) {
					if (getBoundsBottom().intersects(t.getBounds())) {
						setVelY(0);

						if (falling) {
							falling = false;
						}

						if (!falling) {
							gravity = 0.8;
							falling = true;
						}

					} else if (getBoundsLeft().intersects(t.getBounds())) {
						setVelX(2);
						facing = 0;
					} else if (getBoundsRight().intersects(t.getBounds())) {
						setVelX(-2);
						facing = 1;
					}
				}
			}
		}

		if (falling) {
			gravity += 0.1;
			setVelY((int) gravity);
		}

		if (velX != 0) {
			frameDelay++;
			if (frameDelay >= 15) {
				frame++;
				if (6 <= frame) {
					frame = 0;
				}
				frameDelay = 0;
			}
		}

	}

}
