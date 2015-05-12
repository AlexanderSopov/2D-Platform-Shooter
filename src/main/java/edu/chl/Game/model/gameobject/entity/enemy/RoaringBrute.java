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
import edu.chl.Game.view.graphics.Sprite;
import edu.chl.Game.view.graphics.SpriteSheet;

public class RoaringBrute extends EnemyUnit {
	
	public RoaringBrute(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	@Override
	public void initiateUnit() {
		initiateSpriteSheets();
		initiateSpriteArrays();
		initiateProperties();
		loadSprites();
	}
	
	@Override
	public void initiateSpriteSheets() {
		setSheetMovingAnimation(new SpriteSheet("/b00.png"));
		setSheetAttackAnimation(new SpriteSheet("/b03.png"));
	}

	@Override
	public void initiateSpriteArrays() {
		setArrayMovingAnimation(new Sprite[20]);
		setArrayAttackAnimation(new Sprite[16]);
	}
	
	@Override
	public void loadSprites() {
		setArrayMovingAnimation( getLoad().loadSprites(getSheetMovingAnimation(), getArrayMovingAnimation(), 16, getWidth(), getHeight()) );
		setArrayAttackAnimation( getLoad().loadSprites(getSheetAttackAnimation(), getArrayAttackAnimation(), 8, 148, 126) );
	}

	@Override
	public void initiateProperties() {
		setAttackDamage(25.0);
		setAttackTimer(new AttackTimer(120));
		setFrameDelayLimit(3);
		setFrameLimit(8);
		setFrameIterator(new FrameIterator(this, getFrameDelayLimit(), getFrameLimit()));
		
		
	}







}
