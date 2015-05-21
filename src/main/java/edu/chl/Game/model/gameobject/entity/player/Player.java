package edu.chl.Game.model.gameobject.entity.player;

import java.awt.Frame;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.view.graphics.HUD.*;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.entityTools.FrameIterator;
import edu.chl.Game.model.gameobject.tile.Tile;
import edu.chl.Game.model.physics.ProjectileDetection;
import edu.chl.Game.view.graphics.*;
import edu.chl.Game.model.gameobject.entity.items.*;
import edu.chl.Game.model.sound.SFX;
import edu.chl.Game.view.graphics.Sprite;
import edu.chl.Game.view.graphics.window.*;

public class Player extends Unit {

	private SpriteSheet sheetMovingAnimation;
	private SpriteSheet sheetHurtingAnimation;
	private Sprite[] arrayMovingAnimation;
	private Sprite[] arrayHurtAnimation;
	private LoadingSprites load;
	private PlayerLevel level;
	private Inventory inventory;
	private Pistol p;
	private StatusBar bar;
	private ArrayList<Talent> talentList;


	public Player(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);

		this.load = new LoadingSprites();
		this.level = new PlayerLevel();
		initiateUnit();
		p = new Pistol(getX(), getY(), getWidth(), getHeight(), false, null,
				handler, handler.getGameCursor(), this);
		this.setWeaponProperties(this, new FrameIterator(1, 20));

		this.inventory = new Inventory(new PlayerOutfit(this));
		this.bar = new StatusBar(this);
		talentList = new ArrayList<Talent>();

	}

	@Override
	public void render(Graphics g) {

		if (!isRecievingDamage()) {
			if (getUnitState().isAnimate()) {
				if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
					getRenderClass().renderAnimateRight(g,
							arrayMovingAnimation,
							getFrameIterator_moving().getFrame(), getX(),
							getY(), getHeight(), getWidth());
				} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
					getRenderClass().renderAnimateLeft(g, arrayMovingAnimation,
							getFrameIterator_moving().getFrame(), getX(),
							getY(), getHeight(), getWidth(), 20);
				}
			}

			else if (!getUnitState().isAnimate()) {
				if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
					getRenderClass().renderNotAnimateRight(g,
							arrayMovingAnimation, getX(), getY(), getHeight(),
							getWidth());
				}

				else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
					getRenderClass()
							.renderNotAnimateLeft(
									g,
									arrayMovingAnimation,
									getX(),
									getY(),
									getHeight(),
									getWidth(),
									(getUnitMeasurement()
											.getNumberOfSprite_move() / 2));
				}
			}
		} else {

			if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
				getRenderClass1().renderAnimateRight(g, arrayHurtAnimation,
						getFrameIterator_hurting().getFrame(), getX(), getY(),
						getWidth(), getHeight());
			} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
				getRenderClass1().renderAnimateLeft(g, arrayHurtAnimation,
						getFrameIterator_hurting().getFrame(), getX(), getY(),
						getWidth(), getHeight(),
						(getUnitMeasurement().getNumberOfSprite_hurt() / 2));
			}
		}

		runScoreDisplay(g);
		p.render(g);
		
		
	}

	@Override
	public void update() {
		super.update();
		iterateCooldown();
		if (getUnitState().isAnimate()) {
			iterateMoving();
		}
		if (isRecievingDamage()) {
			iterateTakingDamage();
			processDamageTaking();
		}
		p.update();
	}

	public void processDamageTaking() {
		if (!getFrameIterator_hurting().isActive()) {
			setRecievingDamage(false);
		}
	}

	public void iterateMoving() {
		getFrameIterator_moving().updateFrameCounter();
	}

	public void iterateTakingDamage() {
		getFrameIterator_hurting().updateFrameCounter();
	}

	public void recieveDamage(int value) {
		if (!isRecievingDamage()) {
			setRecievingDamage(true);
		}
		getUnitValues().setHealthPoints(calculateNewHealthPoints(value));
	}

	public int calculateNewHealthPoints(int value) {
		return getUnitValues().getHealthPoints() - value;
	}

	public void shoot() {
		p.shoot();
	}

	public double getHealth() {
		return getUnitValues().getHealthPoints();
	}

	public double getArmor() {
		return 0;
	}

	@Override
	public void initiateUnit() {
		initiateUnitTitle();
		initiateUnitMeasurement();
		initiateSpriteSheets();
		initiateSpriteArrays();
		initiateProperties();
		loadSprites();
	}

	@Override
	public void initiateUnitTitle() {
		setUnitTitle("Bit");
	}

	@Override
	public void initiateUnitMeasurement() {
		getUnitMeasurement().setNumberOfSprites_move(40);
		getUnitMeasurement().setNumberOfSprites_hurt(8);
		getUnitMeasurement().setDelay_move(2);
		getUnitMeasurement().setLimit_move(20);
		getUnitMeasurement().setDelay_hurt(2);
		getUnitMeasurement().setLimit_hurt(4);
	}

	@Override
	public void initiateSpriteSheets() {
		this.sheetMovingAnimation = new SpriteSheet("/p00.png");
		this.sheetHurtingAnimation = new SpriteSheet("/p01.png");
	}

	@Override
	public void initiateSpriteArrays() {
		int valueA = getUnitMeasurement().getNumberOfSprite_move();
		int valueB = getUnitMeasurement().getNumberOfSprite_hurt();
		this.arrayMovingAnimation = new Sprite[valueA];
		this.arrayHurtAnimation = new Sprite[valueB];
	}

	@Override
	public void initiateProperties() {
		int valueA = getUnitMeasurement().getDelay_move();
		int valueB = getUnitMeasurement().getLimit_move();
		int valueC = getUnitMeasurement().getDelay_hurt();
		int valueD = getUnitMeasurement().getLimit_hurt();
		setFrameIterator_moving(new FrameIterator(valueA, valueB));
		setFrameIterator_hurt(new FrameIterator(valueC, valueD));
		// level: _ / healthPoints:_ / energyPoints:_ / armor:_ / attackDamage:_
		// / attackRate:_ /
		setUnitValues(0, 200, 100, 0, 7, 0);
	}

	@Override
	public void loadSprites() {
		this.arrayMovingAnimation = load.loadSprites(sheetMovingAnimation,
				arrayMovingAnimation, getUnitMeasurement()
						.getNumberOfSprite_move(), getWidth(), getHeight());
		this.arrayHurtAnimation = load.loadSprites(sheetHurtingAnimation,
				arrayHurtAnimation, getUnitMeasurement()
						.getNumberOfSprite_hurt(), getWidth(), getHeight());
	}

	@Override
	public void setAlternativeMeasurement() {}

	/**
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}

	public void gainExperience(int value) {
		level.gainExperience(value);
		addScoreInterface(value, ScoreType.experience);
	}
	
	public void addTalent(Talent tal){
		talentList.add(tal);	
	}
	
	private void gainTalent(Talent tal){
		switch (tal.getType()) {
		
		case activation:
			gainBonus(tal);
		break;
		
		}
	}
	
	private void gainBonus(Talent tal){
		//getUnitValues().setAttackDamage();
	}
	
	
	
	
	
	
	

}
