package edu.chl.Game.model.gameobject.entity;

import java.awt.Graphics;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;

public abstract class Unit extends Entity {

	public Unit(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void render(Graphics g) {}
	@Override
	public void update() {}
	public abstract void initiateSpriteSheets();
	public abstract void initiateSpriteArrays();
	public abstract void loadSprites();
	public abstract void initiateProperties();
	public abstract void initiateUnit();
	public abstract void setAlternativeMeasurement();
	public abstract void initiateUnitMeasurement();

}
