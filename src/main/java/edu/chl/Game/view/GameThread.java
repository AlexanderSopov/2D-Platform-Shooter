package edu.chl.Game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Observable;

import edu.chl.Game.entity.Entity;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.handler.State;
import edu.chl.Game.tile.Tile;

/**
 * 
 * @author Mansoor, Alexander
 * @version 1.0
 */
public class GameThread extends Observable implements Runnable {

	private Thread thread;
	private Frame frame;
	private StartMenu startMenu;
	private GameHandler gameHandler;
	private boolean running = false;
	
	//The state of the game
	public static State state = State.MENU;
	
	private double delta = 0.0;
	private int Frame=1;
	private int second=1;
	
	public GameThread(){
		thread = new Thread(this);
		frame = new Frame();
		startMenu = new StartMenu(frame);
		gameHandler = new GameHandler(thread, frame);
		start();
		
		for (Entity e: gameHandler.getEntityList())
			addObserver(e);
		for (Tile t: gameHandler.getTileList())
			addObserver(t);
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
		if(state == State.GAME){
			BufferStrategy bs = frame.getBufferStrategy();
			if(bs == null){
				frame.createBufferStrategy(3);
				return;
				}
			renderGraphics(bs);
		}else if(state == State.MENU && !startMenu.inMenu()){
			startMenu.setMenu();
			frame.setVisible(true);
		}else if(state == State.OPTION && !startMenu.inOption()){
			startMenu.setOption();
			frame.setVisible(true);
		}else if(state == State.CREDIT && !startMenu.inCredit()){
			startMenu.setCredit();
			frame.setVisible(true);
		}
	}
	
	/**
	 * Rendering the Graphics to the screen.
	 * @param b - BufferStrategy to access the methods in BufferStrategy class.
	 */
	public void renderGraphics(BufferStrategy b) {
		Graphics g = b.getDrawGraphics();
			frame.requestFocus();
			g.setColor(new Color(135, 206, 235));
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
			g.translate(gameHandler.getCamera().getX(), gameHandler.getCamera().getY());
			notifyObservers((Object)g);
			gameHandler.render(g);
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
		Frame++;
		if(isFrame()){
			System.out.println(Frame + " updates/frames in the " + second + "th second");
			second++;
			Frame=1;
		}
	}
	
	/**
	 * Checking if Frame is equal to 60.
	 * @return Frame - If frame is not 60 then return false otherwise true.
	 */
	private boolean isFrame() {
		return Frame==60;
	}
}
