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
	public static GameThread gt = Main.game;
	private UnitProperties unitProperties;
	private UnitState unitState;
	private UpdateMovement updateMovement;
	private CalculateBounds calculateBounds;

	public GameObject(int x, int y, int width, int height, boolean solid,
			Id id, GameHandler handler) {
		this.unitProperties = new UnitProperties(handler, x, y, width, height);
		this.unitState = new UnitState(id, solid);
		this.updateMovement = new UpdateMovement(this.unitProperties, this.unitState);
		this.calculateBounds = new CalculateBounds(unitProperties);
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

	public UpdateMovement getUpdateMovement() {
		return this.updateMovement;
	}

	public CalculateBounds getCalculateBounds() {
		return calculateBounds;
	}
        
        public GameHandler getHandler(){
            return unitProperties.getHandler();
        }

	public int getX() {
		return unitProperties.getX();
	}

	public int getY() {
		return unitProperties.getY();
	}

	public void setX(int x) {
		unitProperties.setX(x);
	}

	public void setY(int y) {
		unitProperties.setY(y);
	}

	public int getWidth() {
		return unitProperties.getWidth();
	}

	public int getHeight() {
		return unitProperties.getHeight();
	}

}
