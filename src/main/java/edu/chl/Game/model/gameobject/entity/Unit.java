package edu.chl.Game.model.gameobject.entity;

import java.awt.Graphics;
import edu.chl.Game.view.graphics.*;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;

public abstract class Unit extends Entity {
	
	private UnitTitleGraphic uTG;
	private UnitStatsGraphic uSG;
	
	public Unit(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		
		this.uTG = new UnitTitleGraphic(this);
		this.uSG = new UnitStatsGraphic(this);
		
	}
	@Override
	public void render(Graphics g) {
		uTG.displayUnitTitle(g);
		uSG.displayHealthGraphics(g);
	}
	@Override
	public void update() {
		super.update();
	}
	
	public abstract void initiateUnitTitle();
	public abstract void initiateSpriteSheets();
	public abstract void initiateSpriteArrays();
	public abstract void loadSprites();
	public abstract void initiateProperties();
	public abstract void initiateUnit();
	public abstract void setAlternativeMeasurement();
	public abstract void initiateUnitMeasurement();
}
