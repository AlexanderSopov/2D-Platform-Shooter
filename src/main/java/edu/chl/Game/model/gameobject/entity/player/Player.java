package edu.chl.Game.model.gameobject.entity.player;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.entityTools.FrameIterator;
import edu.chl.Game.model.gameobject.tile.Tile;
import edu.chl.Game.model.physics.ProjectileDetection;
import edu.chl.Game.view.graphics.*;
import edu.chl.Game.model.gameobject.item.*;
import edu.chl.Game.model.sound.SFX;
import edu.chl.Game.view.graphics.Sprite;


public class Player extends Unit {

	private SpriteSheet sheetMovingAnimation;
	private SpriteSheet sheetHurtingAnimation;
	private Sprite[] arrayMovingAnimation;
	private Sprite[] arrayHurtAnimation;
	private LoadingSprites load;
	private Inventory inventory; 
    private PlayerOutfit outfit;
    private PlayerLevel level;
	private ArrayList<Talent> talentList;
    
    private Pistol p;
    private ProjectileDetection pd;
	private boolean isRecievingDamage;

	public Player(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
               
        
		this.load = new LoadingSprites();
		this.level = new PlayerLevel();
		initiateUnit();      

		this.setWeaponProperties(this, new FrameIterator(1, 20));

		this.outfit = new PlayerOutfit(this);
		this.inventory = new Inventory(outfit);


	}

	@Override
	public void render(Graphics g) {
                
		if (!isRecievingDamage()) {
			if (getUnitState().isAnimate()) {
				if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
					try {
						getRenderClass().render(g, arrayMovingAnimation,getFrameIterator_moving().getFrame(), getX(), getY(), getHeight(), getWidth(), FacingDirection.FacingRight, 0, true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
					try {
						getRenderClass().render(g, arrayMovingAnimation,getFrameIterator_moving().getFrame(), getX(), getY(), getHeight(), getWidth(), FacingDirection.FacingLeft, 20, true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			else if (!getUnitState().isAnimate()) {
				if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
					try {
						getRenderClass().render(g, arrayMovingAnimation, 0, getX(), getY(), getWidth(), getHeight(), FacingDirection.FacingRight, 0, false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
					try {
						getRenderClass().render(g, arrayMovingAnimation, 0, getX(), getY(), getWidth(), getHeight(), FacingDirection.FacingLeft, (getUnitMeasurement().getNumberOfSprite_move()/2), false);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {

			if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
				try {
					getRenderClass1().renderAnimateRight(g, arrayHurtAnimation,
							getFrameIterator_hurting().getFrame(), getX(), getY(),
							getWidth(), getHeight());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
				try {
					getRenderClass1().renderAnimateLeft(g, arrayHurtAnimation,
							getFrameIterator_hurting().getFrame(), getX(), getY(),
							getWidth(), getHeight(), (getUnitMeasurement().getNumberOfSprite_hurt()/2));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		runScoreDisplay(g);
              
		this.outfit.render(g);
		this.inventory.render(g, this);
	}

	@Override
	public void update() {
		super.update();
		iterateCooldown();
		
		this.outfit.update();
		
		if (getUnitState().isAnimate()) {
			iterateMoving();
		}
		
		if (isRecievingDamage()) {
			iterateTakingDamage();
			processDamageTaking();
		}

          
          
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
	
	public int calculateNewHealthPoints(int value){
		return getUnitValues().getHealthPoints() - value;
	}
	
	public void shoot(){
		this.outfit.effect();
		//p.shoot();
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
		// healthPoints:_ / energyPoints:_ / armor:_ / attackDamage:_ / attackRate:_ /
		setUnitValues(0, 200, 100, 0, 7, 0);
	}
	
	@Override
	public void loadSprites() {	
		this.arrayMovingAnimation = load.loadSprites(sheetMovingAnimation, arrayMovingAnimation, getUnitMeasurement().getNumberOfSprite_move(), getWidth(), getHeight());
		this.arrayHurtAnimation = load.loadSprites(sheetHurtingAnimation, arrayHurtAnimation, getUnitMeasurement().getNumberOfSprite_hurt(), getWidth(), getHeight());
	}



	@Override
	public void setAlternativeMeasurement() {	

	}

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
