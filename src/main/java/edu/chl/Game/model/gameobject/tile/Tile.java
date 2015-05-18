package edu.chl.Game.model.gameobject.tile;

import java.awt.Rectangle;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.view.graphics.*;

public abstract class Tile extends GameObject {
	
	private SpriteSheet spriteSheet;
	private Sprite sprite;
	private LoadingSprites load;
	
	private TileState tileState;

	public Tile(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		this.load = new LoadingSprites();
		initiateSpriteSheets();
		initiateSprite();
	}
        

	
	public abstract void initiateSpriteSheets();
	public abstract void initiateSprite();
	
	public void setSpriteSheet(SpriteSheet sp){
		this.spriteSheet = sp;
	}
	
	public SpriteSheet getSpriteSheet(){
		return spriteSheet;
	}
	
	public void setSprite(Sprite sp){
		this.sprite = sp;
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public LoadingSprites getLoad(){
		return load;
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
