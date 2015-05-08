package edu.chl.Game.Units;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.UnitAI.*;
import edu.chl.Game.Main;
import edu.chl.Game.entity.*;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.view.GameThread;
import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;
import edu.chl.Game.UnitTools.*;

public class EnemyUnit extends Entity {

	private Sprite monster[] = new Sprite[12];
	private Sprite attackAnimation[] = new Sprite[12];
	private Random rand = new Random();
	private int playerXCoordinate;
	private AttackTimer attackTimer;
	private double attackDamage;
	private boolean performingAttack = false;
	private AI aI;
	private LoadingSprites loadingSprites;
	private OpponentUnitProperties op;
	private FrameIterator frameIterator;

	public EnemyUnit(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler, int type, OpponentUnitProperties op,
			FrameValues frameValues) {
		
		super(x, y, width, height, solid, id, handler);

		// set the unit properties

		this.op = op;
		this.attackTimer = op.getAttackTimer();
		this.attackDamage = op.getAttackDamage();

		// loads the array with the sprites

		this.loadingSprites = new LoadingSprites(this.monster, getHandler());
		initiate();

		// set the AI

		this.aI = new AI(getHandler(), getUnitProperties());

		// set the frameIterator

		this.frameIterator = new FrameIterator(getEntityProperties(),
				frameValues.getFrameDelayLimit(), frameValues.getFrameLimit());
	}

	// loads the sprites

	public void initiate() {
		loadingSprites.loadSprites();
	}

	// draws the animation

	@Override
	public void render(Graphics g) {
		if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
			getRenderClass().renderAnimateRight(g, monster,
					getEntityProperties().getFrame(),
					getUnitProperties().getX(), getUnitProperties().getY(),
					getUnitProperties().getWidth(),
					getUnitProperties().getHeight());
		} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
			getRenderClass().renderAnimateLeft(g, monster,
					getEntityProperties().getFrame(),
					getUnitProperties().getX(), getUnitProperties().getY(),
					getUnitProperties().getWidth(),
					getUnitProperties().getHeight());
		}
	}

	// updates the mechanics of the unit

	@Override
	public void update() {

		getUpdateMovement().updateCoordinates();
		getUpdateMovement().toggleAnimate();
		getCollisionDetection().checkForCollision();
		if (getUnitState().isAnimate()) {
			frameIteration();
		}
		UnitAI();
		attack();

	}
	
	// iterates through the frames
	
	public void frameIteration() {
		frameIterator.iterateThroughFrames();
	}

	public void UnitAI() {
		findPlayer();
		followBehaviour();
	}

	public void findPlayer() {
		for (int i = 0; i < getHandler().getEntityList().size(); i++) {
			if (getHandler().getEntityList().get(i).getUnitState().getId() == Id.player) {
				playerXCoordinate = getHandler().getEntityList().get(i).getX();
			}
		}
	}

	public void followBehaviour() {
		aI.followPlayer();
	}

	public void attack() {
		attackTimer.updateAttackTimer();
		if (attackTimer.isReadyToAttack()) {
			if (playerXCoordinate <= getX()) {
				if ((getX() - playerXCoordinate) <= 50) {
					dealDamage();
				}
			} else {
				if ((playerXCoordinate - getX()) <= 50) {
					dealDamage();
				}
			}
		}
	}

	public void dealDamage() {
		getHandler().getPlayer().recieveDamage(attackDamage);
	}

}
