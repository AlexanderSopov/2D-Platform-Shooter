package edu.chl.Game.controller;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Observable;

import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.player.GameCursor.CursorState;
import edu.chl.Game.model.gameobject.tile.Tile;
import edu.chl.Game.view.CharacterSelectionView;
import edu.chl.Game.view.Frame;
import edu.chl.Game.view.FrameGDX;
import edu.chl.Game.view.WorldMapView;
import edu.chl.Game.model.sound.*;
import edu.chl.Game.view.graphics.WorldMapAnimator;

/**
 * 
 * @author Mansoor, Alexander
 * @version 1.0
 */
public class RefreshTimer extends Observable implements Runnable{

	private Thread thread;
	private WorldMapView mapView;
	private CharacterSelectionView charSelectionView;
	private WorldMapAnimator movingChar;
	private GameHandler gameHandler;
	private boolean running = false;
	private MouseInput mouseInput;
	
	// Swing frame
	private Frame frame;
	
	//The state of the game
	public static State state = State.MAIN_MENU;
	//Array of possible levels
	public static String[] levels = {"level_1","level_2", "level_3", "level_4", "level_5"};
	//The selected map/level
	public static String selectedMap = levels[0];
	
	private double delta = 0.0;
	private int frameRate=1;
	private int second=1;
	private boolean inMenu = false;
	
	public RefreshTimer(){
		Music.addToAccessMusic();
		thread = new Thread(this);
		frame = new Frame();
		
		mapView = new WorldMapView();
		charSelectionView = new CharacterSelectionView(movingChar);

		gameHandler = new GameHandler(this, frame);
		mouseInput = new MenuMouseInput(this ,mapView, gameHandler);
		
		frame.addKeyListener(new KeyInput(gameHandler));
		frame.addMouseListener(mouseInput);
		frame.addMouseMotionListener(mouseInput);
		this.changeGameState(state);
		start();
		
		
	}

	/**
	 * 
	 */
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread.start();
	}
	
	public synchronized void interrupt(){
		if(running){
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		frame.requestFocus();
		timer();
	}
	
	/**
	 * Update Timer, create a Buffer and update the handler.
	 */
	public void update(){
			setChanged();
			render();
			printTimer();
	}

	/**
	 * Create a Buffer with maximum number of 3 and start rendering.
	 */
	public void render(){
		
		if(state == State.GAME || state == State.MAP || state == State.CHARACTER_SELECTION){
			BufferStrategy bs = frame.getBufferStrategy();
			Music.playWorldOneMapOne();
			if(bs == null){
				frame.createBufferStrategy(3);
                return;
			}
			renderGraphics(bs);
		}else if(state == State.MAIN_MENU){
			if(!inMenu){
				Music.playMenu();
				inMenu = true;
				new FrameGDX(frame);
			}else{
				//setScreen(mainMenu);
			}
		}
		frame.setVisible(true);
	}
	
	/**
	 * Rendering the Graphics to the screen.
	 * @param b - BufferStrategy to access the methods in BufferStrategy class.
	 */
	public void renderGraphics(BufferStrategy b) {
		Graphics g = b.getDrawGraphics();
		frame.requestFocus();
		
		if(state == State.GAME){
			gameHandler.render(g);
			notifyObservers((Object)g);
		}else if(state == State.MAP){
			mapView.render(g);
		}else if(state == State.CHARACTER_SELECTION){
			charSelectionView.render(g);
		}
		
		gameHandler.getGameCursor().update();
		gameHandler.getGameCursor().render(g);
		
		b.show();
		g.dispose();

	}
	
	private void timer(){
		long timeSnap1 = System.nanoTime();
		double nanosec = 1000000000.0;
		long timeSnap2 = System.nanoTime();
		delta = (timeSnap2 - timeSnap1)*60/nanosec;
		while(running){
			timeSnap1 = timeSnap2;
			timeSnap2 = System.nanoTime();
			delta += (timeSnap2 - timeSnap1 )*60/nanosec;
			timeToUpdate(delta);
		}
		interrupt();	
	}
	
	/**
	 * 
	 * @param d
	 */
	private void timeToUpdate(double d){
		if(d>1){
			delta=delta-1;
			update();
		}
	}
	
	/**
	 * Printing frames per second.
	 */
	private void printTimer(){
		frameRate++;
		if(isFrame()){
			second++;
			frameRate=1;
		}
	}
	
	/**
	 * Checking if Frame is equal to 60.
	 * @return Frame - If frame is not 60 then return false otherwise true.
	 */
	private boolean isFrame() {
		return frameRate==60;
	}
        
	public void updateObserverList(){
		deleteObservers();
		for(Entity e: gameHandler.getEntityList()){
			addObserver(e);
		}
		for (Tile t: gameHandler.getTileList()){
			addObserver(t);
		}
	}
	
	public MouseInput getMouseInput(){
		return mouseInput;
	}
	
	public  void changeGameState(State newState){
		state = newState;
		if(state == State.GAME){
			frame.removeMouseListener(mouseInput);
			frame.removeMouseMotionListener(mouseInput);
			mouseInput = new MouseInput( gameHandler);
			frame.addMouseListener(mouseInput);
			frame.addMouseMotionListener(mouseInput);
			gameHandler.getGameCursor().changeState(CursorState.AIM);
		}else{
			gameHandler.getGameCursor().changeState(CursorState.DEFULT);
		}
	}
}
