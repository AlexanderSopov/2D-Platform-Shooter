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

public class InfectedStudent extends EnemyUnit {
	
	public InfectedStudent(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler) {
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
		setUnitTitle("Infected Student");
	}
	
	@Override
	public void initiateUnitMeasurement() {
		getUnitMeasurement().setNumberOfSprites_move(24);
		getUnitMeasurement().setNumberOfSprites_attack(32);
		getUnitMeasurement().setStrikeFrame(2);
		getUnitMeasurement().setAltWidth(96);
		getUnitMeasurement().setAltHeight(90);
		getUnitMeasurement().setDelay_move(3);
		getUnitMeasurement().setLimit_move(12);
		getUnitMeasurement().setDelay_attack(1);
		getUnitMeasurement().setLimit_attack(16);
		getUnitMeasurement().setStrikeFrame(8);
		getUnitMeasurement().setOffsetRight(-5);
		getUnitMeasurement().setOffsetLeft(10);
		setAltWidth(100);
		setAltHeight(100);
		
	}

	@Override
	public void initiateSpriteSheets() {
		setSheetMovingAnimation(new SpriteSheet("/is00.png"));
		setSheetAttackAnimation(new SpriteSheet("/is01.png"));
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
		setUnitValues(40, 0, 0, 1, 60);

	}










}
