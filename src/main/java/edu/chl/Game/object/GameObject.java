package edu.chl.Game.object;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.Main;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.view.GameThread;

public abstract class GameObject implements Observer {
	public int x, y;
	public int velX, velY;
	public int width, height;
	public boolean solid;
	
	public Id id;
	public GameHandler handler;
	public static GameThread gt = Main.game;
	private boolean animate = false;
	
	public GameObject(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.id = id;
		this.handler = handler;
	}
	
	public abstract void render(Graphics g);
	public void update(Observable o, Object arg){
		try {
			Graphics g = (Graphics)arg;
			render(g);
			update();
		}catch (IllegalArgumentException e){
			System.out.println("oops!");
		}
	}
	public abstract void update();
	public abstract void remove();
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public Id getId(){
		return id;
	}
	
	public GameHandler getHandler(){
		return handler;
	}
	
	public int getVelX(){
		return velX;
	}
	
	public int getVelY(){
		return velY;
	}
	
	public boolean isAnimate(){
		return animate;
	}
	
	public void setAnimate(boolean animate){
		this.animate = animate;
	}
	
}
