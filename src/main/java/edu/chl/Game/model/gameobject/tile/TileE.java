package edu.chl.Game.model.gameobject.tile;

import java.awt.Graphics;
import java.util.Observer;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.view.graphics.SpriteSheet;

public class TileE extends Tile implements Observer {

	public TileE(int x, int y, boolean solid, Id id, GameHandler handler) {
		super(x, y, 64, 384, solid, id, handler);
		setTileState(TileState.floor);
	}

	@Override
	public void initiateSpriteSheets() {
		switch (RefreshTimer.selectedMap) {
		case "level_1":
			setSpriteSheet(new SpriteSheet("/t04.png"));
			break;
		case "level_2":
			setSpriteSheet(new SpriteSheet("/level2_tiles/t14.png"));
			break;
		case "level_3":
			setSpriteSheet(new SpriteSheet("/level3_tiles/t24.png"));
			break;
		case "level_4":
			setSpriteSheet(new SpriteSheet("/level4_tiles/t34.png"));
			break;
		case "level_5":
			setSpriteSheet(new SpriteSheet("/level5_tiles/t44.png"));
			break;
		}
	}
	
	@Override
	public void initiateSprite() {
		setSprite(getLoad().loadSingleSprite(getSpriteSheet(), getSprite(), 64, 384)); 
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getSprite().getBufferedImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	@Override
	public void update() {
		
	}

}
