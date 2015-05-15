package edu.chl.Game.model.gameobject.tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.view.graphics.Sprite;

public class FloorTile extends Tile implements Observer {

	private Sprite floor;

	public FloorTile(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler) {
		super(x, y, width, height, solid, id, handler);

		floor = new Sprite(handler.getSheetTile(), 0, 0, width, height);
		
		setTileState(TileState.floor);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(floor.getBufferedImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public void update(Observable o, Object arg) {
		try {
			Graphics g = (Graphics) arg;
			render(g);
			update();
		} catch (IllegalArgumentException e) {
			System.out.println("oops!");
		}
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void initiateSpriteSheets() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initiateSprite() {
		// TODO Auto-generated method stub
		
	}
	
	
}
