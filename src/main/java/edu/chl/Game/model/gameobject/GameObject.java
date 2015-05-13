package edu.chl.Game.model.gameobject;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.Main;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.physics.CalculateBounds;
import edu.chl.Game.model.physics.PhysicalProperties;
import edu.chl.Game.model.physics.Vector2D;

/*
 * Author: Alexander Sopov, Oliver Tunberg, Rasmus Andersson
 */

public abstract class GameObject implements Observer, GameInterface {
	public static RefreshTimer gt = Main.game;
	private UnitState unitState;
	private CalculateBounds calculateBounds;
	private PhysicalProperties physicalProperties;
	private double healthPoints;
	private GameHandler handler;
	private Id id;
	private boolean solid; 

	public GameObject(int x, int y, int width, int height, boolean solid,
			Id id, GameHandler handler) {
		
		physicalProperties = new PhysicalProperties(
                		 new Vector2D(x,y), new Vector2D(0,0), width, height);
		this.id = id;
		this.solid = solid;
		this.handler = handler;
		this.unitState = new UnitState(id, solid);
		this.calculateBounds = new CalculateBounds(this);
	}

	public void update(Observable o, Object arg) {
		try {
			Graphics g = (Graphics) arg;
			render(g);
			update();
		} catch (IllegalArgumentException e) {
			System.out.println("Something's wrong with GameObjects Update!");
		}
	}
	
	public abstract void render(Graphics g);

	public abstract void update();

	public abstract void remove();


	public UnitState getUnitState() {
		return unitState;
	}

	

	public CalculateBounds getCalculateBounds() {
		return calculateBounds;
	}
        
        public GameHandler getHandler(){
            return this.handler;
        }

	public int getX() {
		return physicalProperties.getX();
	}

	public int getY() {
		return physicalProperties.getY();
	}

	public void setX(int x) {
		physicalProperties.setX(x);
	}

	public void setY(int y) {
		physicalProperties.setY(y);
	}

	public int getWidth() {
		return physicalProperties.getWidth();
	}

	public int getHeight() {
		return physicalProperties.getHeight();
	}
        
        public double getHealthPoints(){
		return healthPoints;
	}
	
	public void setHealthPoints(double healthPoints){
		this.healthPoints = healthPoints;
	}
        
    public int getVelX(){
		return physicalProperties.getVelX();
	}
	
	public void setVelX(int velX){
		physicalProperties.setVelX(velX);
	}
	
	public int getVelY(){
		return physicalProperties.getVelY();
	}
	
	public void setVelY(int vely){
		physicalProperties.setVelY(vely);
	}

}
