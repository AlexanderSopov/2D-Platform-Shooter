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
import edu.chl.Game.view.graphics.SpriteSheet;

public class Player extends Entity {

	private Sprite player[] = new Sprite[40];
	private Sprite recieveDamage[] = new Sprite[10];
	private ContactWithEnemy contactWithEnemy;
	private GravitationalProperties gravitationalProperties;
	private FrameIterator frameIterator_moving;
	private FrameIterator frameIterator_takeDamage;
	
	
	

	public Player(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);


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
		
		runScoreDisplay(g);

	}

	@Override
	public void update() {
		getUpdateMovement().updateCoordinates_player();
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

}
