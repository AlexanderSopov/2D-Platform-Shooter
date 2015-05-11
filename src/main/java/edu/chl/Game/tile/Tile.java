package edu.chl.Game.tile;

import java.awt.Rectangle;

import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.GameObject;
import edu.chl.Game.object.Id;

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
