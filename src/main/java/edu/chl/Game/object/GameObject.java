package edu.chl.Game.object;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.Main;
import edu.chl.Game.Vector.Vector2D;
import edu.chl.Game.entity.*;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.view.GameThread;

public abstract class GameObject implements Observer {
	public static GameThread gt = Main.game;
	private UnitProperties unitProperties;
	private UnitState unitState;
	public final double mass;
	public final double invMass;
	public final double restitution = 0.0;

	public GameObject(double d, double e, int width, int height, boolean solid,
			Id id, GameHandler handler, double mass) {
		this.unitProperties = new UnitProperties(handler, d, e, width, height);
		this.unitState = new UnitState(id, solid);
		this.mass = mass;
		invMass = getInvMass();
	}

	private double getInvMass() {
		if (mass == 0)
			return 0;
		return 1/mass;
	}

	public abstract void render(Graphics g);

	public void update(Observable o, Object arg) {
		try {
			Graphics g = (Graphics) arg;
			render(g);
			update();
		} catch (IllegalArgumentException e) {
			System.out.println("oops!");
		}
	}

	public abstract void update();

	public abstract void remove();

	public UnitProperties getUnitProperties() {
		return unitProperties;
	}

	public UnitState getUnitState() {
		return unitState;
	}

        
	public GameHandler getHandler(){
            return unitProperties.getHandler();
        }

	public double getX() {
		return unitProperties.getPosition().getX();
	}

	public double getY() {
		return unitProperties.getPosition().getY();
	}

	public void setX(double d) {
		unitProperties.setPosition(d,unitProperties.getPosition().getY());
	}

	public void setY(double y) {
		unitProperties.setPosition(unitProperties.getPosition().getX(), y);
	}
	
	public void setPosition(Vector2D v){
		unitProperties.setPosition(v);
	}

	public double getWidth() {
		return unitProperties.getWidth();
	}

	public double getHeight() {
		return unitProperties.getHeight();
	}

}
