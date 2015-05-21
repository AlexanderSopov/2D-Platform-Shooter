package edu.chl.Game.model.gameobject.entity.enemy;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.print.attribute.SetOfIntegerSyntax;

import edu.chl.Game.Main;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.view.graphics.Sprite;
import edu.chl.Game.view.graphics.SpriteSheet;

public class OvergrownMonstrocity extends EnemyUnit {
	
	public OvergrownMonstrocity(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		
	}

	@Override
	public void initiateUnit() {
		initiateUnitTitle();
		initiateUnitMeasurement();
		initiateSpriteSheets();
		initiateSpriteArrays();
		setAlternativeMeasurement();
		initiateProperties();
		loadSprites();
	}	
	
	@Override
	public void initiateUnitTitle() {
		setUnitTitle("Overgrown Monstrocity");
	}
	
	@Override
	public void initiateUnitMeasurement() {
		getUnitMeasurement().setNumberOfSprites_move(60);
		getUnitMeasurement().setNumberOfSprites_attack(60);
		getUnitMeasurement().setStrikeFrame(16);
		getUnitMeasurement().setAltWidth(200);
		getUnitMeasurement().setAltHeight(200);
		getUnitMeasurement().setDelay_move(2);
		getUnitMeasurement().setLimit_move(30);
		getUnitMeasurement().setDelay_attack(1);
		getUnitMeasurement().setLimit_attack(30);
		getUnitMeasurement().setOffsetRight(0);
		getUnitMeasurement().setOffsetLeft(0);
		setAltWidth(200);
		setAltHeight(200);
		
	}

	@Override
	public void initiateSpriteSheets() {
		setSheetMovingAnimation(new SpriteSheet("/om00.png"));
		setSheetAttackAnimation(new SpriteSheet("/om01.png"));
	}

	@Override
	public void initiateSpriteArrays() {
		int valueA = getUnitMeasurement().getNumberOfSprite_move();
		int valueB = getUnitMeasurement().getNumberOfSprite_attack();
		setArrayMovingAnimation(new Sprite[valueA]);
		setArrayAttackAnimation(new Sprite[valueB]);
	}
	
	@Override
	public void setAlternativeMeasurement(){ 
	}
	
	@Override
	public void loadSprites() {
		int valueA = getUnitMeasurement().getNumberOfSprite_move();
		int valueB = getUnitMeasurement().getNumberOfSprite_attack();
		int valueC = getAltWidth();
		int valueD = getAltHeight();
		setArrayMovingAnimation( getLoad().loadSprites(getSheetMovingAnimation(), getArrayMovingAnimation(), valueA, getWidth(), getHeight()) );
		setArrayAttackAnimation( getLoad().loadSprites(getSheetAttackAnimation(), getArrayAttackAnimation(), valueB, valueC, valueD) );
	}
	
	@Override
	public void initiateProperties() {
		int valueA = getUnitMeasurement().getDelay_move();
		int valueB = getUnitMeasurement().getLimit_move();
		int valueC = getUnitMeasurement().getDelay_attack();
		int valueD = getUnitMeasurement().getLimit_attack();
		setFrameIterator_moving(new FrameIterator(valueA, valueB));
		setFrameIterator_attack(new FrameIterator(valueC, valueD));
		// healthPoints:_ / energyPoints:_ / armor:_ / attackDamage:_ / attackRate:_ /
		setUnitValues(3, 20, 0, 0, 17, 60);

	}










}
