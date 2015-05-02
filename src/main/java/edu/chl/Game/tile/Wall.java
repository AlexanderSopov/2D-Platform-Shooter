package edu.chl.Game.tile;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;

public class Wall extends Tile implements Observer{
	
	private Sprite floor;

	public Wall(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler, int type) {
		super(x, y, width, height, solid, id, handler);
		if(type == 1){
			//floor
			floor = new Sprite(handler.getSheetTexture(), 0, 0, 16, 16);
		}else if(type == 2){
			//upper-floor
			floor = new Sprite(handler.getSheetTexture(), 5, 0, 16, 16);
		}else if(type == 3){
			//cloud
			floor = new Sprite(handler.getSheetTexture(), 5, 8, 16, 16);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(floor.getBufferedImage(), getX(), getY(), width, height, null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public void update(Observable o, Object arg){
		try {
			Graphics g = (Graphics)arg;
			render(g);
			update();
		}catch (IllegalArgumentException e){
			System.out.println("oops!");
		}
	}
}
