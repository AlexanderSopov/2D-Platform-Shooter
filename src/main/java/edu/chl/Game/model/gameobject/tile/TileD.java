package edu.chl.Game.model.gameobject.tile;

import java.awt.Graphics;
import java.util.Observer;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.view.graphics.SpriteSheet;

public class TileD extends Tile implements Observer {

	public TileD(int x, int y, boolean solid, Id id, GameHandler handler) {
		super(x, y, 384, 384, solid, id, handler);
		setTileState(TileState.floor);
	}

	@Override
	public void initiateSpriteSheets() {
		switch (RefreshTimer.selectedMap) {
		case "level_1":
			setSpriteSheet(new SpriteSheet("/t03.png"));
			break;
		case "level_2":
			setSpriteSheet(new SpriteSheet("/level2_tiles/t13.png"));
			break;
		case "level_3":
			setSpriteSheet(new SpriteSheet("/level3_tiles/t23.png"));
			break;
		case "level_4":
			setSpriteSheet(new SpriteSheet("/level4_tiles/t33.png"));
			break;
		case "level_5":
			setSpriteSheet(new SpriteSheet("/level5_tiles/t43.png"));
			break;
		}
	}
	
	@Override
	public void initiateSprite() {
		setSprite(getLoad().loadSingleSprite(getSpriteSheet(), getSprite(), 384, 384)); 
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getSprite().getBufferedImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	@Override
	public void update() {
		
	}

}
