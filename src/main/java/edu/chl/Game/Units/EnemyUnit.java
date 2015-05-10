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
import edu.chl.Game.graphics.*;

public class EnemyUnit extends Entity {

	private SpriteSheet spriteSheet_walking;
	private Sprite[] spriteArray_walking = new Sprite[16];
	private int playerXCoordinate;
	private AttackTimer attackTimer;
	private double attackDamage;
	private boolean performingAttack = false;
	private AI aI;
	private LoadingSprites loadingSprites;
	private OpponentUnitProperties op;
	private FrameIterator frameIterator;

	public EnemyUnit(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler, OpponentUnitProperties op,
			FrameValues frameValues, SpriteSheet spriteSheet_walking) {

		super(x, y, width, height, solid, id, handler);

		// set the SpriteSheet

		this.spriteSheet_walking = spriteSheet_walking;

		// set the unit properties

		this.op = op;
		this.attackTimer = op.getAttackTimer();
		this.attackDamage = op.getAttackDamage();

		// loads the array with the sprites

		this.loadingSprites = new LoadingSprites(this.spriteSheet_walking,
				this.spriteArray_walking, op.getNumberOfSprites(),
				op.getWidth(), op.getHeight());
		initiate();

		// set the AI

		this.aI = new AI(this, attackTimer, op);

		// set the frameIterator

		this.frameIterator = new FrameIterator(getEntityProperties(),
				frameValues.getFrameDelayLimit(), frameValues.getFrameLimit());
	}

	// loads the sprites

	public void initiate() {
		loadingSprites.loadSprites();
		getRenderClass().setFrameAmount(8);
	}

	// draws the animation

	@Override
	public void render(Graphics g) {
		if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
					getRenderClass().renderAnimateRight(g, spriteArray_walking,
					getEntityProperties().getFrame(),
					getX(), getY(),
					getWidth(),
					getHeight());
		} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
					getRenderClass().renderAnimateLeft(g, spriteArray_walking,
					getEntityProperties().getFrame(),
					getX(), getY(),
					getWidth(),
					getHeight());
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
		aI.attack();

	}

	// iterates through the frames

	public void frameIteration() {
		frameIterator.iterateThroughFrames();
	}

	public void UnitAI() {
		locatePlayer();
		followBehaviour();
	}

	public void locatePlayer() {
		aI.findPlayer();
	}

	public void followBehaviour() {
		aI.followPlayer();
	}

}
