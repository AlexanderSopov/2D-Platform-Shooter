package edu.chl.Game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.handler.GameHandler;

/**
 * 
 * @author Mansoor
 * @version 1.0
 */
public class GameThread implements Runnable, Observer {
	
	private Thread thread;
	private Frame frame;
	private GameHandler handler;
	
	private boolean running = false;
	
	private double delta = 0.0;
	private int Frame=1;
	private int second=1;
	
	public GameThread(){
		thread = new Thread(this);
		frame = new Frame();
		handler = new GameHandler(thread, frame);
	}
	
	/**
	 * If Game is running return true otherwise false.
	 * @return running 
	 */
	private boolean isRunning() {
		return this.running;
	}
	
	/**
	 * 
	 */
	public synchronized void start(){
		if(isRunning()) {
			running = true;
			thread.start();
		}
	}
	
	public synchronized void interrupt(){
		if(isRunning()){
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
		handler.update();
		createBuffer();
		printTimer();
	}

	/**
	 * Create a Buffer with maximum number of 3 and start rendering.
	 */
	public void createBuffer(){
		BufferStrategy bs = frame.getBufferStrategy();
		if(bs == null){
			frame.createBufferStrategy(3);
			return;
		}
		renderGraphics(bs);
	}
	
	/**
	 * Rendering the Graphics to the screen.
	 * @param b - BufferStrategy to access the methods in BufferStrategy class.
	 */
	public void renderGraphics(BufferStrategy b) {
		Graphics g = b.getDrawGraphics();
		g.setColor(new Color(135, 206, 235));
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.translate(handler.getCamera().getX(), handler.getCamera().getY());
		handler.render(g);
		g.dispose();
		showGraphics(b);
	}
	
	/**
	 * Show the Graphics.
	 */
	public void showGraphics(BufferStrategy b) {
		b.show();
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
	
	/**
	 * If observer have been change then update.
	 * @see java.util.Observer;
	 */
	public void update(Observable o, Object arg) {
		if(o.hasChanged()) {
			update();
		}
	}

}
