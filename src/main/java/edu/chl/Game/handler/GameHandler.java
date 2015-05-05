package edu.chl.Game.handler;

import edu.chl.Game.Physics.CollisionStrategy;
import edu.chl.Game.entity.GameCursor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.chl.Game.Main;
import edu.chl.Game.entity.Entity;
import edu.chl.Game.entity.MonsterA;
import edu.chl.Game.entity.Player;
import edu.chl.Game.graphics.SpriteSheet;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;
import edu.chl.Game.tile.Wall;
import edu.chl.Game.view.Frame;

public class GameHandler{
	private Thread thread;
	private Frame frame;
	private LinkedList<Entity> entity = new LinkedList<Entity>();
	private LinkedList<Tile> tile = new LinkedList<Tile>();
	
	private SpriteSheet sheetPlayer;
	private SpriteSheet sheetMonster;
	private SpriteSheet sheetTexture;
	private BufferedImage mapImage;
	private SpriteSheet sheetEnemyUnit0;
	private Entity player;
	
	private Camera camera;
    private GameCursor c;
	
	public GameHandler(Thread thread, Frame frame){
		this.thread = thread;
		this.frame = frame;		
		camera = new Camera();
                createSheet();
        createMap();
        player = getPlayer();
		frame.addKeyListener(new KeyInput(this, player));
        frame.addMouseListener(new MouseInput(c));
        frame.addMouseMotionListener(new MouseInput(c));
		
	}
	
	private Entity getPlayer() {
		for (Entity en : getEntityList())
			if (en.getUnitState().getId() == Id.player)
				return en;
		return null;
	}

	public void render(Graphics g){
		for(Entity e: getEntityList()){
			if(e.getUnitState().getId() == Id.player){
				camera.update(e);
			}
		}
	}
	
	public void update(){
		CollisionStrategy strategy;
		int listSize = entity.size();
		for(int i=0; i < listSize; i++){			
			for(Tile t: getTileList()){
				strategy = new CollisionStrategy(entity.get(i), t);
				if(strategy.areObjectsColliding()){
					if(Math.abs(entity.get(i).getUnitProperties().getVelocity().getY()) < 0.5)
						entity.get(i).setTouchesGround(true); 
					strategy.resolveCollision();
				}
			}
			strategy = new CollisionStrategy(entity.get(i), entity.get((i+1)%listSize));
			if(strategy.areObjectsColliding())
				strategy.resolveCollision();
		}
	}
	
	public void createSheet(){
		//28x41
		sheetPlayer = new SpriteSheet("/SpriteSheet_Player.png");
		//32x32
		sheetMonster = new SpriteSheet("/spriteSheetMonster.png");
		//16x16
		sheetTexture = new SpriteSheet("/spriteSheetTexture.png");
		//64x64
		sheetEnemyUnit0 = new SpriteSheet("/EnemyUnitGrid0.png");
	}
	

	public void createMap(){
		try {
			mapImage = ImageIO.read(getClass().getResource("/unitTestingMap0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int width = mapImage.getWidth();
		int height = mapImage.getHeight();
		
		for(int y=0; y<height; y++){
			for(int x = 0; x < width; x++){
				int pixel = mapImage.getRGB(x,y);
				
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 0 && blue == 0){
					addTile(new Wall(x*64, y*64, 64, 64, true, Id.wall, this, 1));
				}else if(red == 0 && green == 255 && blue == 0){
					addTile(new Wall(x*64, y*64, 64, 64, true, Id.wall, this, 3));
				}else if(red == 0 && green == 0 && blue == 255){
					addEntity(new Player(x*64,y*55, 64, 64, true, Id.player, this));
				}else if(red == 255 && green == 0 && blue == 0){
					addEntity(new MonsterA(x*64,y*64, 64, 64, true, Id.monster, this, 1));
				}else if(red == 255 && green == 0 && blue == 100){
					addEntity(new MonsterA(x*64,y*64, 64, 64, true, Id.monster, this, 2));
				}
			}
                        
                        for(Entity e: getEntityList()){
                            if(e.getUnitState().getId() == Id.player){
                                c = new GameCursor(e,this);
                                addEntity(c);
                                break;
                            }
                        }
                        
                        
		}
	}
	
	public Camera getCamera(){
		return camera;
	}
	
	public LinkedList<Entity> getEntityList(){
		return entity;
	}
	
	
	public void addEntity(Entity e){
		entity.add(e);
	}
	
	public void removeEntity(Entity e){
		entity.remove(e);
	}
	
	public void addTile(Tile t){
		tile.add(t);
	}
	
	public void removeTile(Tile t){
		tile.remove(t);
	}
	
	public LinkedList<Tile> getTileList(){
		return tile;
	}
	
	public SpriteSheet getSheetPlayer(){
		return sheetPlayer;
	}
	
	public SpriteSheet getSheetMonster(){
		return sheetMonster;
	}
	
	public SpriteSheet getSheetTexture(){
		return sheetTexture;
	}
	
	public SpriteSheet getSheetEnemyUnitGrid0(){
		return sheetEnemyUnit0;
	}

}
