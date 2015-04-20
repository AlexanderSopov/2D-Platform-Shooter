package edu.chl.Game.entity;

import java.awt.Graphics;
import java.util.Random;

import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class MonsterA extends Entity {
	
	private Sprite monster[] = new Sprite[4];
	private Random rand = new Random();

	public MonsterA(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler, int type) {
		super(x, y, width, height, solid, id, handler);
		
		int r = rand.nextInt(2);

		if(r == 0){
			velX = 2;
			facing = 0;
		}else if(r == 1){
			velX = -2;
			facing = 1;
		}
		
		if(type == 1){
			//facing right
			for(int i = 0; i < 2; i++){
				monster[i] = new Sprite(handler.getSheetMonster(),i, 0, 32, 32);
			}
			//facing left
			for(int i = 0; i < 2; i++){
				monster[i+2] = new Sprite(handler.getSheetMonster(),i, 2, 32, 32);
			}
		}else if(type == 2){
			//facing right
			for(int i = 0; i < 2; i++){
				monster[i] = new Sprite(handler.getSheetMonster(),i+2, 0, 32, 32);
			}
			//facing left
			for(int i = 0; i < 2; i++){
				monster[i+2] = new Sprite(handler.getSheetMonster(),i+2, 2, 32, 32);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(facing ==0){
			g.drawImage(monster[frame].getBufferedImage(), x, y, width, height, null);
		}else if(facing == 1){
			g.drawImage(monster[frame+2].getBufferedImage(), x, y, width, height, null);
		}
	}

	@Override
	public void update() {
		x+= velX;
		y+= velY;
		
		for(Tile t: handler.getTileList()){
			if(t.solid){
				if(t.getId() == Id.wall){
					if(getBoundsBottom().intersects(t.getBounds())){
						setVelY(0);
						
						if(falling){
							falling = false;
						}
				
						if(!falling){
							gravity = 0.8;
							falling = true;
						}
						
					}else if(getBoundsLeft().intersects(t.getBounds())){
						setVelX(2);
						facing = 0;
					}else if(getBoundsRight().intersects(t.getBounds())){
						setVelX(-2);
						facing = 1;
					}
				}
			}
		}
		
		if(falling){
			gravity+=0.1;
			setVelY((int)gravity);
		}
		
		if(velX != 0){
			frameDelay++;
			if(frameDelay >= 4){
				frame++;
				if(frame >= 2){
					frame = 0;
				}
				frameDelay = 0;
			}
		}
		
	}

}
