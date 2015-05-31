package edu.chl.Game.model.gameobject.tile;

import java.awt.Graphics;
import java.util.Observer;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.view.graphics.SpriteSheet;

public class TileC extends Tile implements Observer {

	public TileC(int x, int y, boolean solid, Id id, GameHandler handler) {
		super(x, y, 1600, 64, solid, id, handler);
		setTileState(TileState.floor);
	}

	@Override
	public void initiateSpriteSheets() {
		switch (RefreshTimer.selectedMap) {
		case "level_1":
			setSpriteSheet(new SpriteSheet("/t02.png"));
			break;
		case "level_2":
			setSpriteSheet(new SpriteSheet("/level2_tiles/t12.png"));
			break;
		case "level_3":
			setSpriteSheet(new SpriteSheet("/level3_tiles/t22.png"));
			break;
		case "level_4":
			setSpriteSheet(new SpriteSheet("/level4_tiles/t32.png"));
			break;
		case "level_5":
			setSpriteSheet(new SpriteSheet("/level5_tiles/t42.png"));
			break;
		}
	}
	
	@Override
	public void initiateSprite() {
		setSprite(getLoad().loadSingleSprite(getSpriteSheet(), getSprite(), 1600, 64)); 
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getSprite().getBufferedImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	@Override
	public void update() {
		
	}

}
