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
	private GameHandler handler;
	public static GameThread gt = Main.game;
	private UnitProperties unitProperties;
	private UnitState unitState;
	private UpdateMovement updateMovement;
	
	public GameObject(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler){
		this.handler = handler;
		this.unitProperties = new UnitProperties(x, y, width, height);
		this.unitState = new UnitState(id, solid);
		this.updateMovement = new UpdateMovement(this.unitProperties, this.unitState);
		
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
	
	public GameHandler getHandler(){
		return handler;
	}
	
	public UnitProperties getUnitProperties(){
		return unitProperties;
	}
	
	public UnitState getUnitState(){
		return unitState;
	}
	
	public UpdateMovement getUpdateMovement(){
		return this.updateMovement;
	}

	

	
}
