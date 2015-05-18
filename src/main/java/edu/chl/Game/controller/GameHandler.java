package edu.chl.Game.controller;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Observer;

import edu.chl.Game.model.gameobject.Id;

import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.model.gameobject.entity.items.CharacterDecorator;
import edu.chl.Game.model.gameobject.entity.items.CharacterFactory;
import edu.chl.Game.model.gameobject.entity.items.P2;

import edu.chl.Game.model.gameobject.entity.player.GameCursor;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.tile.TileA;
import edu.chl.Game.model.gameobject.tile.*;

import edu.chl.Game.sound.Music;
import edu.chl.Game.sound.Sound;
import edu.chl.Game.sound.SFX;
import edu.chl.Game.view.Camera;

import edu.chl.Game.view.Frame;
import edu.chl.Game.model.gameobject.entity.items.Character;
import edu.chl.Game.view.graphics.SpriteSheet;

public class GameHandler {
	private RefreshTimer refreshTimer;
	private Frame frame;
	private LinkedList<Entity> entity = new LinkedList<Entity>();
	private LinkedList<Tile> tile = new LinkedList<Tile>();
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
		

	}
	
	private void init(){
		
		c = new GameCursor(this.camera, this);
		
		addEntity(c);
		
		for (int i = 0; i < entity.size(); i++) {
			if (entity.get(i).getUnitState().getId() == Id.player) {
				this.player = (Player) entity.get(i);
			}
		}
		
		for (Entity e: getEntityList())
			refreshTimer.addObserver(e);
		for (Tile t: getTileList())
			refreshTimer.addObserver(t);
		
		refreshTimer.getMouseInput().setCursor(c);

	}

	public void render(Graphics g) {
		if(MapFactory.mapImage == null){
			MapFactory.createMap(this, c, entity, tile);
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



	public Camera getCamera() {
		return camera;
	}

	public LinkedList<Entity> getEntityList() {
		return entity;
	}

	public void addEntity(Entity e) {

		entity.add(e);
		

	}

	public void removeEntity(Entity e) {

		
		entity.remove(e);

	}

	public void addTile(Tile t) {

		tile.add(t);

	}

	public void removeTile(Tile t) {

		tile.remove(t);

	}

	public LinkedList<Tile> getTileList() {
		return tile;
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
