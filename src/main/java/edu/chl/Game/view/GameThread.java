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
		//init();
		frame.requestFocus();
		long previousTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0;
		double ns = 1000000000.0/60.0;
		int frames = 0;
		int updates = 0;
		while(running){
			long currentTime = System.nanoTime();
			delta+=(currentTime - previousTime)/ns;
			previousTime = currentTime;
			while(delta>=1){
				handler.update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis()-timer > 1000){
				timer += 1000;
				System.out.println(frames + " FPS, " + updates + " refreshrate");
				updates = 0;
				frames = 0;
			}
		}
		interrupt();	
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
	
	
	public void update(){
		handler.update();
	}

}
