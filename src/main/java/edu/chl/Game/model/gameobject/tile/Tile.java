package edu.chl.Game.model.gameobject.tile;

import java.awt.Rectangle;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.view.graphics.SpriteSheet;

public abstract class Tile extends GameObject {
	
	private TileState tileState;

	public Tile(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
	}
        


	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public void remove() {
		getHandler().removeTile(this);
	}
	
	public void setTileState(TileState tileState){
		this.tileState = tileState;
	}
	
	public TileState getTileState(){
		return this.tileState;
	}

}
