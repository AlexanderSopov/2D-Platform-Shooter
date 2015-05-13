package edu.chl.Game.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import edu.chl.Game.handler.Camera;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.model.gameobject.entity.items.Gun;
import edu.chl.Game.model.gameobject.entity.player.GameCursor;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.tile.*;
import edu.chl.Game.sound.Music;
import edu.chl.Game.sound.Sound;
import edu.chl.Game.sound.SFX;
import edu.chl.Game.view.Frame;
import edu.chl.Game.view.graphics.SpriteSheet;

public class GameHandler {
	private RefreshTimer refreshTimer;
	private Frame frame;
	private LinkedList<Entity> entity = new LinkedList<Entity>();
	private LinkedList<Tile> tile = new LinkedList<Tile>();
	private Player player;
	
	private SpriteSheet sheetTile;
	private SpriteSheet sheetPlayer;
	private SpriteSheet sheetMonster;
	private SpriteSheet sheetTexture;
	private SpriteSheet sheetGun;
	
	private SpriteSheet sheetEnemyUnit0;
	private SpriteSheet sheetDerangedBeast;
	private SpriteSheet sheetDerangedBeast_AttackAnimation;
	private SpriteSheet sheetPlayer_RecieveDamage;
	private OpponentUnitProperties op_db;
	private OpponentUnitProperties op_rb;
	private SpriteSheet sheetRoaringBrute;
	private FrameValues frameValues;
	private boolean changeHasHappened;

	private Camera camera;
	private GameCursor c;


	public GameHandler(RefreshTimer refreshTimer, Frame frame) {
		this.refreshTimer = refreshTimer;
		this.frame = frame;
		camera = new Camera();
		createSheet();
		frameValues = new FrameValues(6, 3);

		this.op_db = new OpponentUnitProperties(10.0, 60, 6, 64, 64);
		this.op_rb = new OpponentUnitProperties(25.0, 120, 16, 120, 115);
	}
	
	private void init(){
		for (Entity e : getEntityList()) {
			if (e.getUnitState().getId() == Id.player) {
				c = new GameCursor(e, this);
				addEntity(c);
				break;
			}
		}
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

	public void update() {
		for (Entity e : getEntityList()) {
			e.update();
		}

		for (Tile t : getTileList()) {
			t.update();
		}


	}

	public void createSheet() {
		
		sheetTile = new SpriteSheet("/floorTile.png");

		sheetPlayer = new SpriteSheet("/SH_Player.png");
		
		sheetDerangedBeast = new SpriteSheet("/SpriteSheet_DerangedBeast.png");

		sheetDerangedBeast_AttackAnimation = new SpriteSheet("/db_aa.png");

		sheetPlayer_RecieveDamage = new SpriteSheet("/SH_RD_Player.png");
		
		sheetRoaringBrute = new SpriteSheet("/SH_RB.png");
		
		sheetGun = new SpriteSheet("/SH_Gun.png");
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

	public SpriteSheet getSheetPlayer() {
		return sheetPlayer;
	}

	public SpriteSheet getSheetMonster() {
		return sheetMonster;
	}

	public SpriteSheet getSheetTile() {
		return sheetTile;
	}

	public SpriteSheet getSheetEnemyUnitGrid0() {
		return sheetEnemyUnit0;
	}

	public SpriteSheet getSheetDerangedBeast() {
		return sheetDerangedBeast;
	}
	
	public SpriteSheet getSheetGun() {
		return this.sheetGun;
	}
	public Player getPlayer() {
		return player;
	}

	public SpriteSheet getSheetDerangedBeast_AttackAnimation() {
		return sheetDerangedBeast_AttackAnimation;
	}

	public SpriteSheet getSheetPlayer_RecieveDamage() {
		return sheetPlayer_RecieveDamage;
	}
	
	public SpriteSheet getSheetRoaringBrute() {
		return sheetRoaringBrute;
	}
	
	public OpponentUnitProperties getOp_db(){
		return op_db;
	}
	
	public OpponentUnitProperties getOp_rb(){
		return op_rb;
	}
	
	public FrameValues getFrameValues(){
		return frameValues;
	}
	
	public GameCursor getGameCursor(){
		return c;
	}
	
	public void removeUnit(int j){
		entity.remove(j);
	}
	
	public void unitChanges(boolean b){
		changeHasHappened = b;
	}
	
	public boolean checkForUpdate(){
		if(changeHasHappened){
			changeHasHappened = false;
			return true;
		} else {
			return false;
		}
	}

}
