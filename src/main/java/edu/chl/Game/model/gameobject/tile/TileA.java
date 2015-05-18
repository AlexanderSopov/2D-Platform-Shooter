package edu.chl.Game.model.gameobject.tile;

import java.awt.Graphics;
import java.util.Observer;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.view.graphics.SpriteSheet;

public class TileA extends Tile implements Observer {

	public TileA(int x, int y, boolean solid, Id id, GameHandler handler) {
		super(x, y, 64, 64, solid, id, handler);
		setTileState(TileState.floor);
	}

	@Override
	public void initiateSpriteSheets() {
		setSpriteSheet(new SpriteSheet("/t00.png"));
	}
	
	@Override
	public void initiateSprite() {
		setSprite(getLoad().loadSingleSprite(getSpriteSheet(), getSprite(), 64, 64)); 
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getSprite().getBufferedImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	@Override
	public void update() {
		
	}

}
