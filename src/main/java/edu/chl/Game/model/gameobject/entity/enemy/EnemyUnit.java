package edu.chl.Game.model.gameobject.entity.enemy;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import com.sun.prism.impl.BaseMesh.FaceMembers;
import edu.chl.Game.Main;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.model.gameobject.tile.Tile;
import edu.chl.Game.view.graphics.*;

public abstract class EnemyUnit extends Entity {

	private SpriteSheet sheetMovingAnimation;
	private SpriteSheet sheetAttackAnimation;
	private Sprite[] arrayMovingAnimation;
	private Sprite[] arrayAttackAnimation;
	private LoadingSprites load;

	
	private int playerXCoordinate;
	private AttackTimer attackTimer;
	private double attackDamage;
	private boolean performingAttack = false;
	private AI aI;
	private LoadingSprites loadingSprites;
	private OpponentUnitProperties op;
	private FrameIterator frameIterator;
	private boolean isAttacking = false;

	public EnemyUnit(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler, OpponentUnitProperties op,
			FrameValues frameValues) {

		super(x, y, width, height, solid, id, handler);
		
		
		this.op = op;
		this.attackTimer = op.getAttackTimer();
		this.attackDamage = op.getAttackDamage();
		this.aI = new AI(this, attackTimer, op);
		this.frameIterator = new FrameIterator(getEntityProperties(),
				frameValues.getFrameDelayLimit(), frameValues.getFrameLimit());
		
		
		this.load = new LoadingSprites();
		initiateSpriteSheets();
		initiateSpriteArrays();
		loadSprites();
	}


	@Override
	public void render(Graphics g) {
		if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {	
					getRenderClass().renderAnimateRight(g, arrayMovingAnimation,
					getEntityProperties().getFrame(),
					getX(), getY(),
					getWidth(),
					getHeight());
		} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
					getRenderClass().renderAnimateLeft(g, arrayMovingAnimation,
					getEntityProperties().getFrame(),
					getX(), getY(),
					getWidth(),
					getHeight(), 8);
		}
	}

	@Override
	public void update() {
		getUpdateMovement().updateCoordinates();
		getUpdateMovement().toggleAnimate();
		getUpdateMovement().updateFacing();
		getCollisionDetection().checkForCollision();
		if (getUnitState().isAnimate()) {
			frameIteration();
		}
		UnitAI();
		aI.attack();
	}
	
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
	
	public boolean isAttacking(){
		return isAttacking;
	}
	
	public void setAttacking(boolean b){
		isAttacking = b;
	}
	
	public SpriteSheet getSheetMovingAnimation(){
		return sheetMovingAnimation;
	}
	
	public SpriteSheet getSheetAttackAnimation(){
		return sheetAttackAnimation;
	}
	
	public void setSheetMovingAnimation(SpriteSheet ss){
		this.sheetMovingAnimation = ss;
	}
	
	public void setSheetAttackAnimation(SpriteSheet ss){
		this.sheetAttackAnimation = ss;
	}
	
	public Sprite[] getArrayMovingAnimation(){
		return arrayMovingAnimation;
	}
	
	public Sprite[] getArrayAttackAnimation(){
		return arrayAttackAnimation;
	}
	
	public void setArrayMovingAnimation(Sprite[] sa){
		this.arrayMovingAnimation = sa;
	}
	
	public void setArrayAttackAnimation(Sprite[] sa){
		this.arrayAttackAnimation = sa;
	}
	
	public LoadingSprites getLoad(){
		return load;
	}
	
	public void setLoad(LoadingSprites sp){
		this.load = sp;
	}
	
	public abstract void initiateSpriteSheets();
	public abstract void initiateSpriteArrays();
	public abstract void loadSprites();
	
	
	
	
}
