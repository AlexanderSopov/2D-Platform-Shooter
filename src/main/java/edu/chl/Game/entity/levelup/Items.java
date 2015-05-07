package edu.chl.Game.entity.levelup;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import edu.chl.Game.entity.Entity;
import edu.chl.Game.entity.Player;
import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

/**
 * All items share few common properties, therefore use delegation
 * for a clean and sustainable code.
 * 
 * NOTE THIS CLASS IS NOT COMPLETE.
 * 
 * @author Mansoor
 * @version 1.0
 */
public abstract class Items extends Entity {
	

	private Random rand = new Random();
	private Graphics g;
	private static boolean animateItem = true;
	
	public Items(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		
		int randomDirection = rand.nextInt(2);
		
		switch(randomDirection) {
			case 0:
				setVelX(-2);
				break;
			case 1:
				setVelX(2);
				break;
		}
	}
	public static void setAnimateItem(boolean i) {
		animateItem = i;
	}
	public static boolean getAnimateItem() {
		return animateItem;
	}
	public static void showInventory() {
		g.drawRect(x, y, width, height);
	}
	@Override
	public void render(Graphics g) {
		
		if(getAnimateItem()) {	
			/**
			 * This was used to find the problem, analyze it,
			 *  find a efficient method and solve it.
			 */
			try {
				g.drawImage(itemsGetBufferedImage(), getX(), getY(), getWidth(), getHeight(), null);
				
			} catch(NullPointerException e) {
				StackTraceElement[] trace = e.getStackTrace();
				System.out.println(trace[0].toString());
			}
		}
	}
	
	@Override
	public void update() {
		
		setX(getX()+getVelX());
		setY(getY()+getVelY());
		LinkedList<Tile> tile = handler.getTileList();
		for(int i=0; i<tile.size(); i++){	
			
			Tile t = tile.get(i);
			if(t.isSolid()){
				if(getBoundsBottom().intersects(t.getBounds())){
					
					setVelY(0);
					if(isFalling()){ 
						setFalling(false); 
					}	
					if(!isFalling()) { 
						setGravity(0.8); 
						setFalling(true); 
					}	
				}	
				if(getBoundsLeft().intersects(t.getBounds())){
					setVelX(3);
				}
				if(getBoundsRight().intersects(t.getBounds())){
					setVelX(-3);
				}
			}
		}
		
		
		if(isFalling()){
			//gravity+=0.05;
			setGravity(getGravity()+0.05);
			setVelY((int)getGravity());
		}
	}
	public abstract BufferedImage itemsGetBufferedImage();
}
