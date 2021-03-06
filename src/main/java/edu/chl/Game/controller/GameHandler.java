package edu.chl.Game.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.player.GameCursor;
import edu.chl.Game.model.gameobject.entity.player.GameCursor.CursorState;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.item.Item;
import edu.chl.Game.model.gameobject.tile.*;
import edu.chl.Game.view.Camera;
import edu.chl.Game.view.SubMenuView;
import edu.chl.Game.view.ParallaxBackground;
import edu.chl.Game.view.graphics.DeathSystem;

public class GameHandler {
	private RefreshTimer refreshTimer;

	private SubMenuView subMenuView;

	private LinkedList<Entity> entity = new LinkedList<Entity>();
	private LinkedList<Tile> tile = new LinkedList<Tile>();
	private LinkedList<Item> item = new LinkedList<Item>();

	private Player player;
	private Camera camera;
	private GameCursor c;
	private MouseInput mi;
	private ParallaxBackground bgd = new ParallaxBackground();
	private DeathSystem ds;


	public GameHandler(RefreshTimer refreshTimer, SubMenuView subMenuView) {

		this.refreshTimer = refreshTimer;
		this.subMenuView = subMenuView;
		camera = new Camera();
		c = new GameCursor(this);

		this.ds = new DeathSystem();

	}
	
	//Adds observers to the objects
	private void init() {
		for (int i = 0; i < entity.size(); i++) {
			if (entity.get(i).getUnitState().getId() == Id.player) {
				this.player = (Player) entity.get(i);
			}
		}

		refreshTimer.addObserver(bgd);

		for (Entity e: getEntityList())

			refreshTimer.addObserver(e);
		for (Tile t : getTileList())
			refreshTimer.addObserver(t);
		for (Item it : getItemList())
			refreshTimer.addObserver(it);

	}
	
	//Remove all observers and clear all the linked lists of objects
	private void removeAllObjects(){
		for (Entity e: getEntityList())
			refreshTimer.deleteObserver(e);
		for (Tile t : getTileList())
			refreshTimer.deleteObserver(t);
		for (Item it : getItemList())
			refreshTimer.deleteObserver(it);
		
		entity.clear();
		tile.clear();
		item.clear();
	}

	public void render(Graphics g) {
		g.fillRect(0, 0, 1000, 600);

		if(MapFactory.levelImage == null){
			removeAllObjects();
			MapFactory.createMap(this, c, entity, tile, item);
			init();
		}
		g.drawImage(bgd.background, bgd.backgroundX, bgd.backgroundY, 1000, 600, null);
		g.drawImage(bgd.foreground, bgd.foregroundX, bgd.foregroundY, 1000, 600, null);
		g.drawImage(bgd.foreground1, bgd.foreground1X, bgd.foreground1Y, 1000, 600, null);
		
		subMenuView.render((Graphics2D)g);
		
		g.translate(Camera.getX(), Camera.getY());
		

		renderDeath(g);

		for (Entity e : getEntityList()) {
			if (e.getUnitState().getId() == Id.player) {
				Camera.update(e);
			}
		}
	}

	public void restart() {
		refreshTimer.deleteObservers();
		item = new LinkedList<Item>();
		entity = new LinkedList<Entity>();
		tile = new LinkedList<Tile>();
		camera = new Camera();
		c = new GameCursor(this);
		c.changeState(CursorState.AIM);
		addEntity(c);
		MapFactory.levelImage = null;
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

	public MouseInput getMouseInput() {
		return mi;
	}

	public RefreshTimer getRefreshTimer() {
		return refreshTimer;
	}

	public void registerDead(int x, int y, FacingDirection fd) {
		ds.setX(x);
		ds.setY(y);
		ds.setFacing(fd);
		ds.setActive(true);
	}

	public void renderDeath(Graphics g) {
		if (ds.isActive()) {
			ds.render(g);
			ds.getFrameIterator().updateFrameCounter();
			if (!ds.getFrameIterator().isActive()) {
				ds.setActive(false);
			}
		}
	}

}
