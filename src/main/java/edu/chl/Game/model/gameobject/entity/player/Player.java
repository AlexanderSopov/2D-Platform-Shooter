package edu.chl.Game.model.gameobject.entity.player;

import java.awt.Graphics;
import java.util.LinkedList;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.FacingDirection;
import edu.chl.Game.model.gameobject.entity.GravitationalProperties;
import edu.chl.Game.model.gameobject.entity.entityTools.FrameIterator;
import edu.chl.Game.model.gameobject.tile.Tile;
import edu.chl.Game.model.physics.ContactWithEnemy;
import edu.chl.Game.model.physics.ProjectileDetection;
import edu.chl.Game.view.graphics.Sprite;
import edu.chl.Game.model.gameobject.entity.items.Character;
import edu.chl.Game.model.gameobject.Item;
import edu.chl.Game.model.gameobject.entity.items.ItemMap;

public class Player extends Entity implements Character{

	private Sprite player[] = new Sprite[40];
	private Sprite recieveDamage[] = new Sprite[10];
	private ContactWithEnemy contactWithEnemy;
	private GravitationalProperties gravitationalProperties;

	private FrameIterator frameIterator_moving;
	private FrameIterator frameIterator_takeDamage;
	

	//private FrameCounter frameCounter;
	private boolean isRecievingDamage;
    private ItemMap itemMap;


	public Player(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
                
                itemMap = new ItemMap();

		// facing right

		for (int i = 0; i < 20; i++) {
			// facing right
                        player[i] = new Sprite(handler.getSheetPlayer(), i, 0, 62, 62);
                        // facing left
                        player[i + 20] = new Sprite(handler.getSheetPlayer(), i, 1, 62, 62);
		}

		

		for (int i = 0; i < 5; i++) {
			recieveDamage[i] = new Sprite(
					handler.getSheetPlayer_RecieveDamage(), i, 0, 62, 62);
                        recieveDamage[i + 5] = new Sprite(
					handler.getSheetPlayer_RecieveDamage(), i, 1, 62, 62);
		}
		
		this.frameIterator_moving = new FrameIterator(this, 2, 20);
		this.frameIterator_takeDamage = new FrameIterator(this, 3, 5);
		this.contactWithEnemy = new ContactWithEnemy(this);
		this.gravitationalProperties = new GravitationalProperties(this);
                
               
	}



	@Override
	public void render(Graphics g) {
		if (!isRecievingDamage()) {
			if (getUnitState().isAnimate()) {
				if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
					getRenderClass().renderAnimateRight(g, player,
							frameIterator_moving.getFrame(), getX(), getY(),
							getHeight(), getWidth());
				} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
					getRenderClass().renderAnimateLeft(g, player,
							frameIterator_moving.getFrame(), getX(), getY(),
							getHeight(), getWidth(), 20);
				}
			}

			else if (!getUnitState().isAnimate()) {
				if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
					getRenderClass().renderNotAnimateRight(g, player,
							frameIterator_moving.getFrame(), getX(), getY(),
							getHeight(), getWidth());
				}

				else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
					getRenderClass().renderNotAnimateLeft(g, player,
							frameIterator_moving.getFrame(), getX(), getY(),
							getHeight(), getWidth(), 20);
				}
			}
		} else {

			if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
				getRenderClass1().renderAnimateRight(g, recieveDamage,
						frameIterator_takeDamage.getFrame(), getX(), getY(), getWidth(),
						getHeight());
			} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
				getRenderClass1().renderAnimateLeft(g, recieveDamage,
						frameIterator_takeDamage.getFrame(), getX(), getY(), getWidth(),
						getHeight(), 4);
			}
		}
		
		//displayScore(g);

	}

	@Override
	public void update() {
		getUpdateMovement().updateCoordinates();
		getUpdateMovement().toggleAnimate();
		getCollisionDetection().checkForCollision();
		contactWithEnemy.checkForContact();
		gravitationalProperties.jumpingMechanics();
		gravitationalProperties.fallingMechanics();
		if (getUnitState().isAnimate()) {
			iterateMoving();
		}
		if (isRecievingDamage()) {
			iterateTakingDamage();
			processDamageTaking();
		}

	}
	
	public void processDamageTaking(){
		if (!frameIterator_takeDamage.isActive()) {
			setRecievingDamage(false);
		}
	}

	public void iterateMoving() {
		frameIterator_moving.updateFrameCounter();
	}
	
	public void iterateTakingDamage() {
		frameIterator_takeDamage.updateFrameCounter();
	}
	
	public void recieveDamage(double damage) {
		if (!isRecievingDamage()) {
			setRecievingDamage(true);
		}
		setHealthPoints(getHealthPoints() - damage);
	}

        
    // this is a part of Inventory system, will soon be refactorated :)
  
    @Override
    public void eqipeItem(Item item) {
        this.itemMap.put("Item.getName()", item);
    }

    @Override
    public void discardItem(Item item) {
        this.itemMap.remove("Item.getName()");
    }

    @Override
    public double getHealth() {
       return this.getHealthPoints();
    }

    @Override
    public double getArmor() {
        return 0;
    }

}
