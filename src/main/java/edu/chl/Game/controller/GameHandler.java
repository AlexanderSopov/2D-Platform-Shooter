package edu.chl.Game.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observer;

import javax.imageio.ImageIO;

import edu.chl.Game.Main;
import edu.chl.Game.handler.Camera;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.Item;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.enemy.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.model.gameobject.entity.items.CharacterDecorator;
import edu.chl.Game.model.gameobject.entity.items.CharacterFactory;
import edu.chl.Game.model.gameobject.entity.items.P2;

import edu.chl.Game.model.gameobject.entity.player.GameCursor;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.tile.TileA;
import edu.chl.Game.model.gameobject.tile.*;
import edu.chl.Game.view.Frame;
import edu.chl.Game.model.gameobject.entity.items.Character;
import edu.chl.Game.view.graphics.SpriteSheet;

public class GameHandler {
	private Thread thread;
	private Frame frame;
	private LinkedList<Entity> entity = new LinkedList<Entity>();
	private LinkedList<Tile> tile = new LinkedList<Tile>();
	private Player player;

	private BufferedImage mapImage;
	private boolean changeHasHappened;
	private int ref;
	private Camera camera;
	private GameCursor c;
	private RefreshTimer rfr;
	private MouseInput mi;

	public GameHandler(RefreshTimer rfr, Thread thread, Frame frame) {
		this.thread = thread;
		this.frame = frame;
		camera = new Camera();

		
        this.rfr = rfr;
        this.ref = 0;
        c = new GameCursor(this.camera, this);
		addEntity(c);
		createMap();
		this.mi = new MouseInput(c);
		frame.addKeyListener(new KeyInput(this));
		frame.addMouseListener(mi);
		frame.addMouseMotionListener(mi);
		
		 

	}

	public void render(Graphics g) {
		g.setColor(new Color(135, 206, 235));
		g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		g.translate(camera.getX(), camera.getY());

		camera.update(getPlayer());

	}



	public void createMap() {
		try {
			mapImage = ImageIO.read(getClass().getResource("/level_1.png"));
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

				

				// ( green )
				if (red == 0 && green == 255 && blue == 0) {
					addEntity(new InfectedStudent(x * 64, y * 64, 97, 90, true,
							Id.monster, this));
				}

				// ( black )
				if (red == 0 && green == 0 && blue == 0) {
					addTile(new TileA(x * 64, y * 64, true, Id.wall, this));
				}

				// ( teal )
				if (red == 0 && green == 255 && blue == 255) {
					addTile(new TileB(x * 64, y * 64, true, Id.wall, this));
				}
                                
                                //	( blue )
				else if (red == 0 && green == 0 && blue == 255) {
					
                    this.player = new Player(x*64, y*64, 62, 62, true, Id.player, this);
					addEntity(this.player);
				}                    
                                        
				// ( pink )
				if (red == 255 && green == 0 && blue == 255) {
					addTile(new TileC(x * 64, y * 64, true, Id.wall, this));

				}

				// ( yellow )
				if (red == 255 && green == 255 && blue == 0) {
					addTile(new TileD(x * 64, y * 64, true, Id.wall, this));
				}

				// ( brown )
				if (red == 125 && green == 125 && blue == 0) {
					addTile(new TileE(x * 64, y * 64, true, Id.wall, this));
				}

				
				/* Create a character with Items*/
	            //Character armedPlayer = CharacterFactory.createCharacter(new P2(this.player,this.player, c));
	            //rfr.addObserver((Observer) armedPlayer);
	            


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
		rfr.addObserver(e);

	}

	public void removeEntity(Entity e) {

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
