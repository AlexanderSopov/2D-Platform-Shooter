package edu.chl.Game.model.gameobject.entity.enemy;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import edu.chl.Game.Main;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.view.graphics.*;

public class DerangedBeast extends EnemyUnit {
	
	private SpriteSheet attackAnimationSheet;
	private Sprite[] attackAnimationArray;
	private LoadingSprites load;

	public DerangedBeast(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		
		
	}


	@Override
	public void loadSprites() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initiateSpriteSheets() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initiateSpriteArrays() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void initiateProperties() {
		
                this.setHealthPoints(50);

	}


	@Override
	public void initiateUnit() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setAlternativeMeasurement() {
		// TODO Auto-generated method stub
		
	}


}
