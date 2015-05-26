package edu.chl.Game.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.player.GameCursor;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.item.Item;
import edu.chl.Game.model.gameobject.tile.*;
//import edu.chl.Game.sound.Music;
import edu.chl.Game.view.Camera;
//import edu.chl.Game.model.sound.SFX;
import edu.chl.Game.view.Frame;
//import edu.chl.Game.sound.Sound;
import edu.chl.Game.view.Camera;
//import edu.chl.Game.model.sound.SFX;
import edu.chl.Game.view.Frame;


public class GameHandler {
	private RefreshTimer refreshTimer;
	private Frame frame;
	private LinkedList<Entity> entity = new LinkedList<Entity>();
	private LinkedList<Tile> tile = new LinkedList<Tile>();
	private LinkedList<Item> item = new LinkedList<Item>();
	private Player player;

	private BufferedImage mapImage;
	private boolean changeHasHappened;
	private int ref;
	private Camera camera;
	private GameCursor c;
	private MouseInput mi;



	public GameHandler(RefreshTimer refreshTimer, Frame frame) {
		this.refreshTimer = refreshTimer;
		this.frame = frame;
		camera = new Camera();
		c = new GameCursor(this.camera, this);
		//addEntity(c);
		
		

	}
	
	private void init(){
		
		
		for (int i = 0; i < entity.size(); i++) {
			if (entity.get(i).getUnitState().getId() == Id.player) {
				this.player = (Player) entity.get(i);
			}
		}
		
		
		for (Entity e: getEntityList())
			refreshTimer.addObserver(e);
		for (Tile t: getTileList())
			refreshTimer.addObserver(t);
		for (Item it: getItemList())
			refreshTimer.addObserver(it);
		
		//refreshTimer.getMouseInput().setCursor(c);

	}

	public void render(Graphics g) {
		if(MapFactory.mapImage == null){
			MapFactory.createMap(this, c, entity, tile, item);
			init();
		}
		g.setColor(new Color(135, 206, 235));
		g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		g.translate(camera.getX(), camera.getY());

		
		for (Entity e : getEntityList()) {
			if (e.getUnitState().getId() == Id.player) {
				camera.update(e);
			}
		}
		
		
		
	}
	
	public void restart(){
		refreshTimer.deleteObservers();
		item = new LinkedList<Item>();
		entity = new LinkedList<Entity>();
		tile = new LinkedList<Tile>();
		c = new GameCursor(this.camera, this);
		addEntity(c);
		MapFactory.mapImage = null;
		init();
	}



	public Camera getCamera() {
		return camera;
	}

	public LinkedList<Entity> getEntityList() {
		return entity;
	}

	public void addEntity(Entity e) {

		entity.add(e);
		refreshTimer.addObserver(e);

	}

	public void removeEntity(Entity e) {

		refreshTimer.deleteObserver(e);
		entity.remove(e);
		

	}

	public void addTile(Tile t) {

		tile.add(t);
		refreshTimer.addObserver(t);
	}

	public void removeTile(Tile t) {
		
		refreshTimer.deleteObserver(t);
		tile.remove(t);

	}
	
	public void addItem(Item it) {

		item.add(it);
		refreshTimer.addObserver(it);
	}

	public void removeItem(Item it) {
		
		refreshTimer.deleteObserver(it);
		item.remove(it);

	}

	public LinkedList<Tile> getTileList() {
		return tile;
	}
	
	public LinkedList<Item> getItemList() {
		return item;
	}

	public Player getPlayer() {
		return player;
	}
	
	public GameCursor getGameCursor() {
		return this.c;
	}
	
	public MouseInput getMouseInput(){
		return mi;
	}

}
