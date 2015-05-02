package edu.chl.Game.object;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.Main;
import edu.chl.Game.entity.*;
import edu.chl.Game.entity.UnitProperties;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.view.GameThread;

public abstract class GameObject implements Observer {
	private boolean solid;
	private Id id;
	private GameHandler handler;
	public static GameThread gt = Main.game;
	private boolean animate = false;
	private UnitProperties unitProperties;
	
	public GameObject(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler){
		unitProperties = new UnitProperties(x, y, width, height);
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
	


	public boolean isSolid() {
		return solid;
	}

	
	public Id getId(){
		return id;
	}
	
	public GameHandler getHandler(){
		return handler;
	}
	
	public boolean isAnimate(){
		return animate;
	}
	
	public void setAnimate(boolean animate){
		this.animate = animate;
	}
	
	public UnitProperties getUnitProperties(){
		return unitProperties;
	}
	

	

	
}
