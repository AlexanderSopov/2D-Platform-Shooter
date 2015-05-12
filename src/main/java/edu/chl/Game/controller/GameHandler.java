package edu.chl.Game.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.chl.Game.Main;
import edu.chl.Game.handler.Camera;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.enemy.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.model.gameobject.entity.player.GameCursor;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.tile.*;
import edu.chl.Game.view.Frame;
import edu.chl.Game.view.graphics.SpriteSheet;

public class GameHandler {
	private Thread thread;
	private Frame frame;
	private LinkedList<Entity> entity = new LinkedList<Entity>();
	private LinkedList<Tile> tile = new LinkedList<Tile>();
	private Player player;
	
	private SpriteSheet sheetTile;
	private SpriteSheet sheetPlayer;
	private SpriteSheet sheetMonster;
	private SpriteSheet sheetTexture;
	private BufferedImage mapImage;
	private SpriteSheet sheetEnemyUnit0;
	private SpriteSheet sheetDerangedBeast;
	private SpriteSheet sheetDerangedBeast_AttackAnimation;
	private SpriteSheet sheetPlayer_RecieveDamage;

	private SpriteSheet sheetRoaringBrute;
	//private FrameValues frameValues;
	private boolean changeHasHappened;
        
        private int ref;

	private Camera camera;
	private GameCursor c;
        
        private RefreshTimer rfr;

	public GameHandler(RefreshTimer rfr,Thread thread, Frame frame) {
		this.thread = thread;
		this.frame = frame;
		camera = new Camera();
                this.rfr = rfr;
                this.ref = 0;
		createSheet();
		//frameValues = new FrameValues(6, 3);
		//this.op_db = new OpponentUnitProperties(10.0, 60, 6, 64, 64);
		//this.op_rb = new OpponentUnitProperties(25.0, 120, 16, 120, 115);
		createMap();
		frame.addKeyListener(new KeyInput(this));
		frame.addMouseListener(new MouseInput(c));
		frame.addMouseMotionListener(new MouseInput(c));
		
	}

	public void render(Graphics g) {
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
	}

	public void createMap() {
		try {
			mapImage = ImageIO.read(getClass().getResource(
					"/level_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		int width = mapImage.getWidth();
		int height = mapImage.getHeight();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = mapImage.getRGB(x, y);

				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				
				//	( black )
				if (red == 0 && green == 0 && blue == 0) {
					addTile(new FloorTile(x*64, y*64, 64, 64, true, Id.wall, this));
				}
				
				//	( green )
				else if (red == 0 && green == 255 && blue == 0) {
					addEntity(new RoaringBrute(x * 64, y * 64, 120, 115, true,
							Id.monster, this));
				}
				
				//	( blue )
				else if (red == 0 && green == 0 && blue == 255) {
					addEntity(new Player(x*64, y*64, 62, 62, true, Id.player, this));
				}
				
				
			}

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

		}
		
		

	}
        
        public void resetRef(){
           this.ref = 0;
        }
        
        public int getRef(){
            return ref;
        }

	public Camera getCamera() {
		return camera;
	}

	public  LinkedList<Entity> getEntityList() {
		return entity;
	}

	public  void addEntity(Entity e) {
      
            entity.add(e);
            rfr.addObserver(e);    
               
	}

	public  void removeEntity(Entity e) {

		rfr.deleteObserver(e);
                entity.remove(e);
              
	}

	public void addTile(Tile t) {
            
		tile.add(t);
                rfr.addObserver(t);
                
	}

	public void removeTile(Tile t) {
            
		tile.remove(t);
                rfr.deleteObserver(t);
                
	}

	public  LinkedList<Tile> getTileList() {
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
	
	public GameCursor getGameCursor(){
		return c;
	}

}
