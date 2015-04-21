package edu.chl.Game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import edu.chl.Game.handler.GameHandler;

public class GameThread implements Runnable {
	
	private Thread thread;
	private Frame frame;
	private GameHandler handler;
	
	private boolean running = false;
	
	private double delta = 0.0;
	private int x=1;
	private int y=1;
	
	public GameThread(){
		thread = new Thread(this);
		frame = new Frame();
		handler = new GameHandler(thread, frame);
	
	}
	
	public synchronized void start(){
		if(!running){
			running = true;
			thread.start();
		}
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
		timer();	
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
	
	private void timeToUpdate(double d){
		if(d>1){
			delta=delta-1;
			update();
		}
	}
	
	public void update(){
		handler.update();
		render();
		printTimer();
	}
	
	public void render(){
		BufferStrategy bs = frame.getBufferStrategy();
		if(bs == null){
			frame.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(135, 206, 235));
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.translate(handler.getCamera().getX(), handler.getCamera().getY());
		handler.render(g);
		g.dispose();
		bs.show();
	}
	

	
	private void printTimer(){
		x++;
		if(x==60){
			System.out.println(y + "seconds");
			y++;
			x=1;
		}
	}

}
