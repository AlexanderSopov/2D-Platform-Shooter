package edu.chl.Game.controller;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Observable;

import edu.chl.Game.model.gameobject.entity.player.GameCursor.CursorState;
import edu.chl.Game.view.CharacterSelectionView;
import edu.chl.Game.view.Frame;
import edu.chl.Game.view.FrameGDX;
import edu.chl.Game.view.SubMenuView;
import edu.chl.Game.view.WorldMapView;
import edu.chl.Game.model.sound.*;
import edu.chl.Game.view.graphics.WorldMapAnimator;

/**
 * 
 * @author Mansoor, Alexander
 * @version 1.0
 */
public class RefreshTimer extends Observable implements Runnable{
	
	//Private Variables
	private Thread thread;
	private SubMenuView subMenuView;
	private WorldMapView mapView;
	private CharacterSelectionView charSelectionView;
	private WorldMapAnimator movingChar;
	private GameHandler gameHandler;
	private MouseInput mouseInput;
	
	private int frameRate=1;
	private double delta = 0.0;
	private boolean running = false;
	private Boolean inMainMenu = false;
	
	// Swing frame
	private Frame frame;
	
	//The state of the game
	public static State state = State.MAIN_MENU;
	//Array of possible levels
	public static String[] levels = {"level_1","level_2", "level_3", "level_4", "level_5"};
	//The selected map/level
	public static String selectedMap = levels[0];
	
	public RefreshTimer(){
		Music.addToAccessMusic();
		SFX.addToAccessSFX();
		thread = new Thread(this);
		frame = new Frame();
		
		subMenuView = new SubMenuView();
		mapView = new WorldMapView(subMenuView);
		charSelectionView = new CharacterSelectionView(movingChar);

		gameHandler = new GameHandler(this, subMenuView);
		mouseInput = new MouseInput(mapView, gameHandler, subMenuView);
		
		frame.addKeyListener(new KeyInput(gameHandler));
		frame.addMouseListener(mouseInput);
		frame.addMouseMotionListener(mouseInput);
		
		if(state == State.GAME){
			gameHandler.getGameCursor().changeState(CursorState.AIM);
		}
		
		start();
	}

	/**
	 * Starts the thread
	 */
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread.start();
	}
	
	/**
	 * When the thread is interrupted
	 * @throws InerrutpedException
	 */
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
		Music.playMusic();
		if(state == State.GAME || state == State.MAP || state == State.CHARACTER_SELECTION){	
			BufferStrategy bs = frame.getBufferStrategy();
			if(bs == null){
				frame.createBufferStrategy(3);
                return;
			}
			renderGraphics(bs);
		}else if(state == State.MAIN_MENU){
			if(!inMainMenu){
				inMainMenu = true;
				
				new FrameGDX(this, frame);
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
	
	//Counter / Timer
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
	
	
	//When the time is there, it's goes to the next stage of the updating
	private void timeToUpdate(double d){
		if(d>1){
			delta=delta-1;
			update();
		}
	}
	
	
	//Printing frames per second. 
	private void printTimer(){
		frameRate++;
		if(isFrame()){
			frameRate=1;
		}
	}
	

	//Checking if Frame is equal to 60.
	//If frame is not 60 then return false otherwise true.
	private boolean isFrame() {
		return frameRate==60;
	}
        
	/**
	 * Get the MouseInput that are used Game and WorldMap
	 * @return mouseInput
	 */
	public MouseInput getMouseInput(){
		return mouseInput;
	}
	
	/**
	 * Get the Game Handler that controlls the whole rendering, tiles, entites and items. 
	 * @return gameHandler
	 */
	public GameHandler getHandler(){
		return gameHandler;
	}
	
	/**
	 * Get the varible to se if the game is in menu.
	 * @return True if in Main Menu, false if not.
	 */
	public boolean getInMainMenu(){
		return inMainMenu;
	}
	
	/**
	 * Calls only when entering or exiting the Main Menu.
	 * @param arg Sets the args for inMainMenu;
	 */
	public void setInMainMenu(Boolean arg){
		inMainMenu = arg;
	}
}
