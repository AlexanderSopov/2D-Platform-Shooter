package edu.chl.Game.model.gameobject;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.Main;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.physics.CalculateBounds;
import edu.chl.Game.view.graphics.SpriteSheet;

public abstract class GameObject implements Observer, GameInterface {
	public static RefreshTimer gt = Main.game;
	private UnitState unitState;
	private CalculateBounds calculateBounds;
	private int x, y;
	private int velX, velY;
	private int width, height;
	private double healthPoints;
	private GameHandler handler;
	private Id id;
	private boolean solid;

	public GameObject(int x, int y, int width, int height, boolean solid,
			Id id, GameHandler handler) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.solid = solid;
		this.handler = handler;
		this.unitState = new UnitState(id, solid);
		this.healthPoints = 100.0;
		this.calculateBounds = new CalculateBounds(this);
	}

	public void update(Observable o, Object arg) {
		try {
			Graphics g = (Graphics) arg;
			render(g);
			update();
		} catch (IllegalArgumentException e) {
			System.out.println("oops!");
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

	public GameHandler getHandler() {
		return this.handler;
	}

	public Id getId() {
		return this.id;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public double getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(double healthPoints) {
		this.healthPoints = healthPoints;
	}

	public void takeDamage(double damage) {
		System.out.println(getHealthPoints());
		setHealthPoints(getHealthPoints() - damage);
		if (getHealthPoints() <= 0.0) {
			this.remove();
		}

	}

	public int getVelX() {

		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int vely) {
		this.velY = vely;
	}

}
