package edu.chl.Game.model.gameobject.tile;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.view.graphics.Sprite;

public class Wall extends Tile implements Observer {

	private Sprite floor;

	public Wall(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler, int type) {
		super(x, y, width, height, solid, id, handler);
		if (type == 1) {
			// floor
			floor = new Sprite(handler.getSheetTile(), 0, 0, 16, 16);
		} else if (type == 2) {
			// upper-floor
			floor = new Sprite(handler.getSheetTile(), 5, 0, 16, 16);
		} else if (type == 3) {
			// cloud
			floor = new Sprite(handler.getSheetTile(), 5, 8, 16, 16);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(floor.getBufferedImage(), getX(),
				getY(), getWidth(),
				getHeight(), null);
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
}
