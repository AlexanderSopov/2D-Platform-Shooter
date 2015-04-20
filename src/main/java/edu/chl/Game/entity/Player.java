package edu.chl.Game.entity;

import java.awt.Graphics;
import java.util.LinkedList;

import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class Player extends Entity {
	
	private Sprite player[] = new Sprite[6];
	private boolean animate = false;
	
	public Player(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		
		//facing right
		for(int i = 0; i < 3; i++){
			player[i] = new Sprite(handler.getSheetPlayer(),i, 2, 28, 41);
		}
		
		//facing left
		for(int i = 0; i < 3; i++){
			player[i+3] = new Sprite(handler.getSheetPlayer(),i, 1, 28, 41);
		}
		
	}

	@Override
	public void render(Graphics g) {
		// facing = 0 is right, facing 1 is left
		if(animate){
			if(facing ==0){
				g.drawImage(player[frame].getBufferedImage(), x, y, width, height, null);
			}else if(facing == 1){
				g.drawImage(player[frame+3].getBufferedImage(), x, y, width, height, null);
			}
		}else if(!animate){
			if(facing ==0){
				g.drawImage(player[1].getBufferedImage(), x, y, width, height, null);
			}else if(facing == 1){
				g.drawImage(player[4].getBufferedImage(), x, y, width, height, null);
			}
		}
		
	}

	@Override
	public void update() {
		x+= velX;
		y+= velY;
		
		if(velX!=0){
			animate = true;
		}else{
			animate = false;
		}
		
		// checks if objects collides
		for(Tile t: handler.getTileList()){
			if(t.solid){
				if(t.getId() == Id.wall){
					if(getBoundsTop().intersects(t.getBounds())){
						setVelY(0);
						if(jumping){
							jumping = false;
							gravity = 0.8;
							falling = true;
						}
					}else if(getBoundsBottom().intersects(t.getBounds())){
						setVelY(0);
						if(falling){
							falling = false;
						}
							
						if(!falling&&!jumping){
							gravity = 0.8;
							falling = true;
						}
					}else if(getBoundsLeft().intersects(t.getBounds())){
						setVelX(0);
						x = t.getX()+t.width;
					}else if(getBoundsRight().intersects(t.getBounds())){
						setVelX(0);
						x = t.getX()-t.width;
					}
				}
			}
		}
		
		LinkedList<Entity> e = handler.getEntityList();
		for(int i = 0; i < e.size(); i++){
			if(e.get(i).getId() == Id.monster){
				if(getBounds().intersects(e.get(i).getBoundsTop())){
					e.remove();
				}else if(getBounds().intersects(e.get(i).getBounds())){
					remove();
				}
			}
		}
		
		if(jumping){
			gravity-=0.1;
			setVelY((int)-gravity);
			if(gravity<= 0.0){
				falling = true;
				jumping = false;
			}
		}
		
		if(falling){
			gravity+=0.1;
			setVelY((int)gravity);
		}
		
		if(animate){
			frameDelay++;
			if(frameDelay >= 3){
				frame++;
				if(frame >= 3){
					frame = 0;
				}
				frameDelay = 0;
			}
		}
	}
}
