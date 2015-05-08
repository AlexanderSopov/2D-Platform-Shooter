package edu.chl.Game.handler;

import edu.chl.Game.entity.GameCursor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import edu.chl.Game.UnitTools.*;
import edu.chl.Game.Main;
import edu.chl.Game.entity.*;
import edu.chl.Game.UnitTools.OpponentUnitProperties;
import edu.chl.Game.Units.*;
import edu.chl.Game.entity.Player;
import edu.chl.Game.graphics.SpriteSheet;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.*;
import edu.chl.Game.view.Frame;

public class GameHandler {
	private Thread thread;
	private Frame frame;
	private LinkedList<Entity> entity = new LinkedList<Entity>();
	private LinkedList<Tile> tile = new LinkedList<Tile>();
	private Player player;

	private SpriteSheet sheetPlayer;
	private SpriteSheet sheetMonster;
	private SpriteSheet sheetTexture;
	private BufferedImage mapImage;
	private SpriteSheet sheetEnemyUnit0;
	private SpriteSheet sheetDerangedBeast;
	private SpriteSheet sheetDerangedBeast_AttackAnimation;
	private SpriteSheet sheetPlayer_RecieveDamage;
	private OpponentUnitProperties op_db;
	private OpponentUnitProperties op_rb;
	private FrameValues frameValues;

	private Camera camera;
	private GameCursor c;

	public GameHandler(Thread thread, Frame frame) {
		this.thread = thread;
		this.frame = frame;
		camera = new Camera();
		createSheet();
		frameValues = new FrameValues(6, 3);
		this.op_db = new OpponentUnitProperties(10.0, 60);
		this.op_rb = new OpponentUnitProperties(25.0, 120);
		createMap();
		frame.addKeyListener(new KeyInput(this));
		frame.addMouseListener(new MouseInput(c));
		frame.addMouseMotionListener(new MouseInput(c));
	}

	public void render(Graphics g) {
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
		// 28x41
		sheetPlayer = new SpriteSheet("/SH_Player.png");
		// 32x32
		sheetMonster = new SpriteSheet("/spriteSheetMonster.png");
		// 16x16
		sheetTexture = new SpriteSheet("/spriteSheetTexture.png");
		// 64x64
		sheetEnemyUnit0 = new SpriteSheet("/EnemyUnitGrid0.png");
		// 64x64
		sheetDerangedBeast = new SpriteSheet("/SpriteSheet_DerangedBeast.png");
		// 64x64
		sheetDerangedBeast_AttackAnimation = new SpriteSheet("/db_aa.png");
		// 64x64
		sheetPlayer_RecieveDamage = new SpriteSheet("/SH_RD_Player.png");
	}

	public void createMap() {
		try {
			mapImage = ImageIO.read(getClass().getResource(
					"/unitTestingMap0.png"));
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

				if (red == 0 && green == 0 && blue == 0) {
					addTile(new FloorTile(x * 64, y * 64, 64, 64, true,
							Id.wall, this, 1));
				} else if (red == 0 && green == 255 && blue == 0) {
					addTile(new FloorTile(x * 64, y * 64, 64, 64, true,
							Id.wall, this, 3));
				} else if (red == 0 && green == 0 && blue == 255) {
					addEntity(new Player(x * 62, y * 62, 62, 62, true,
							Id.player, this));
				} else if (red == 255 && green == 0 && blue == 0) {
					addEntity(new DerangedBeast(x * 64, y * 64, 64, 64, true,
							Id.monster, this, 1, op_db, frameValues));
				} else if (red == 255 && green == 0 && blue == 100) {
					addEntity(new DerangedBeast(x * 64, y * 64, 64, 64, true,
							Id.monster, this, 2, op_db, frameValues));
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

	public SpriteSheet getSheetTexture() {
		return sheetTexture;
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

}
