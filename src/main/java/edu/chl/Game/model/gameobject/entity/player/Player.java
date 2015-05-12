package edu.chl.Game.model.gameobject.entity.player;

import java.awt.Graphics;
import java.util.LinkedList;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.FacingDirection;
import edu.chl.Game.model.gameobject.entity.FrameCounter;
import edu.chl.Game.model.gameobject.entity.GravitationalProperties;
import edu.chl.Game.model.gameobject.tile.Tile;
import edu.chl.Game.model.physics.ContactWithEnemy;
import edu.chl.Game.model.physics.ProjectileDetection;
import edu.chl.Game.view.graphics.Sprite;

public class Player extends Entity {

	private Sprite player[] = new Sprite[40];
	private Sprite recieveDamage[] = new Sprite[10];
	private ContactWithEnemy contactWithEnemy;
	private GravitationalProperties gravitationalProperties;
	private FrameCounter frameCounter;
	private boolean isRecievingDamage;
	private ProjectileDetection pd;
	private boolean pdInit;

	public Player(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);

		// facing right
		for (int i = 0; i < 20; i++) {
			player[i] = new Sprite(handler.getSheetPlayer(), i, 0, 62, 62);
		}

		// facing left
		for (int i = 0; i < 20; i++) {
			player[i + 20] = new Sprite(handler.getSheetPlayer(), i, 1, 62, 62);
		}

		for (int i = 0; i < 5; i++) {
			recieveDamage[i] = new Sprite(
					handler.getSheetPlayer_RecieveDamage(), i, 0, 62, 62);
		}

		for (int i = 0; i < 5; i++) {
			recieveDamage[i + 5] = new Sprite(
					handler.getSheetPlayer_RecieveDamage(), i, 1, 62, 62);
		}

		this.frameCounter = new FrameCounter(3, 5);
		this.contactWithEnemy = new ContactWithEnemy(this);
		this.gravitationalProperties = new GravitationalProperties(this);
	}

	public void initiateProjectileDetection() {
		this.pd = new ProjectileDetection(getHandler(), getHandler().getGameCursor().getPistol());
	}

	@Override
	public void render(Graphics g) {
		if (!isRecievingDamage) {
			if (getUnitState().isAnimate()) {
				if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
					getRenderClass().renderAnimateRight(g, player,
							getEntityProperties().getFrame(), getX(), getY(),
							getHeight(), getWidth());
				} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
					getRenderClass().renderAnimateLeft(g, player,
							getEntityProperties().getFrame(), getX(), getY(),
							getHeight(), getWidth(), 20);
				}
			}

			else if (!getUnitState().isAnimate()) {
				if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
					getRenderClass().renderNotAnimateRight(g, player,
							getEntityProperties().getFrame(), getX(), getY(),
							getHeight(), getWidth());
				}

				else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
					getRenderClass().renderNotAnimateLeft(g, player,
							getEntityProperties().getFrame(), getX(), getY(),
							getHeight(), getWidth(), 20);
				}
			}
		} else {

			if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
				getRenderClass1().renderAnimateRight(g, recieveDamage,
						frameCounter.getCount(), getX(), getY(), getWidth(),
						getHeight());
			} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
				getRenderClass1().renderAnimateLeft(g, recieveDamage,
						frameCounter.getCount(), getX(), getY(), getWidth(),
						getHeight(), 8);
			}
		}

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
			iterateThroughFrames();
		}
		if (isRecievingDamage) {
			frameCounter.updateCount();
			if (frameCounter.isComplete()) {
				isRecievingDamage = false;
			}
		}
		engageInitiation();
		//hitTarget();

		// System.out.println(getEntityState().isInAir());

	}

	public void iterateThroughFrames() {
		increaseFrameDelay();
	}

	public void increaseFrameDelay() {
		getEntityProperties().setFrameDelay(
				getEntityProperties().getFrameDelay() + 1);
		checkFrameDelayLimit();
	}

	public void checkFrameDelayLimit() {
		if (2 <= getEntityProperties().getFrameDelay()) {
			increaseFrame();
			setFrameDelayToZero();
		}
	}

	public void increaseFrame() {
		getEntityProperties().setFrame(getEntityProperties().getFrame() + 1);
		checkFrameLimit();
	}

	public void checkFrameLimit() {
		if (20 <= getEntityProperties().getFrame()) {
			setFrameToZero();
		}
	}

	public void setFrameDelayToZero() {
		getEntityProperties().setFrameDelay(0);
	}

	public void setFrameToZero() {
		getEntityProperties().setFrame(0);
	}

	public void engageInitiation() {
		if (pdInit) {
			pdInit = false;
			initiateProjectileDetection();
		}
	}

	public void hitTarget() {
		if (!pdInit) {
			pd.hitTarget();
		}
	}

	public void recieveDamage(double damage) {
		if (!isRecievingDamage) {
			isRecievingDamage = true;
		}
		setHealthPoints(getHealthPoints() - damage);
	}

}
