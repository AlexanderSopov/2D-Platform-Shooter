package edu.chl.Game.Units;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.Main;
import edu.chl.Game.entity.*;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.view.GameThread;
import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class EnemyUnit extends Entity {

	private Sprite monster[] = new Sprite[12];
	private Sprite attackAnimation[] = new Sprite[12];
	private Random rand = new Random();
	private int playerXCoordinate;
	private AttackTimer attackTimer;
	private double attackDamage;
	private boolean performingAttack = false;



	public EnemyUnit(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler, int type) {
		super(x, y, width, height, solid, id, handler);

		int r = rand.nextInt(2);

		if (true) {
			getUnitProperties().setVelX(2);
			getEntityProperties().setFrame(0);
		}

		if (type == 1) {
			// facing right
			for (int i = 0; i < 6; i++) {
				monster[i] = new Sprite(handler.getSheetDerangedBeast(), i, 0,
						64, 64);
			}
			// facing left
			for (int i = 0; i < 6; i++) {
				monster[i + 6] = new Sprite(handler.getSheetDerangedBeast(),
						i, 1, 64, 64);
			}
		} else if (type == 2) {
			// facing right
			for (int i = 0; i < 6; i++) {
				monster[i] = new Sprite(handler.getSheetDerangedBeast(), i, 0,
						64, 64);
			}
			// facing left
			for (int i = 0; i < 6; i++) {
				monster[i + 6] = new Sprite(handler.getSheetDerangedBeast(),
						i, 1, 64, 64);
			}
		}
		
		for(int i=0; i<6; i++){
			attackAnimation[i] = new Sprite(handler.getSheetDerangedBeast_AttackAnimation(), i, 0, 64, 64);
		}
		
		for(int i=0; i<6; i++){
			attackAnimation[i+6] = new Sprite(handler.getSheetDerangedBeast_AttackAnimation(), i, 1, 64, 64);
		}
		
		this.attackTimer = new AttackTimer(60);
		this.attackDamage = 10.0;
	}

	@Override
	public void render(Graphics g) {

			if(getEntityState().getFacingDirection()==FacingDirection.FacingRight){
				getRenderClass().renderAnimateRight(g, monster, getEntityProperties().getFrame(), getUnitProperties().getX(), getUnitProperties().getY(), getUnitProperties().getWidth(), getUnitProperties().getHeight());
			} else if (getEntityState().getFacingDirection()==FacingDirection.FacingLeft){
				getRenderClass().renderAnimateLeft(g, monster, getEntityProperties().getFrame(), getUnitProperties().getX(), getUnitProperties().getY(), getUnitProperties().getWidth(), getUnitProperties().getHeight());
			}
		
		
	}

	@Override
	public void update() {
		
		getUpdateMovement().updateCoordinates();
		getUpdateMovement().toggleAnimate();
		getCollisionDetection().checkForCollision();
		if (getUnitState().isAnimate()) {
			iterateThroughFrames();
		}
		UnitAI();
		attack();

	}
	
	public void iterateThroughFrames(){
		increaseFrameDelay();
	}

	public void increaseFrameDelay(){
		getEntityProperties().setFrameDelay(getEntityProperties().getFrameDelay() + 1);
		checkFrameDelayLimit();
	}
	
	public void checkFrameDelayLimit(){
		if (3 <= getEntityProperties().getFrameDelay()){
			increaseFrame();
			setFrameDelayToZero();
		}
	}
	
	public void increaseFrame(){
		getEntityProperties().setFrame(getEntityProperties().getFrame() + 1);
		checkFrameLimit();
	}
	
	public void checkFrameLimit(){
		if (6 <= getEntityProperties().getFrame()) {
			setFrameToZero();
		}
	}
	
	public void setFrameDelayToZero(){
		getEntityProperties().setFrameDelay(0);
	}
	
	public void setFrameToZero(){
		getEntityProperties().setFrame(0);
	}
	
	public void UnitAI(){
		findPlayer();
		followPlayer();
	}
	
	public void findPlayer(){
		for(int i=0; i<getHandler().getEntityList().size(); i++){
			if(getHandler().getEntityList().get(i).getUnitState().getId()==Id.player){
				playerXCoordinate = getHandler().getEntityList().get(i).getX();
			}
		}
	}
	
	public void followPlayer(){
		if(playerXCoordinate < getX()){
			getUnitProperties().setVelX(-2);
		} else {
			getUnitProperties().setVelX(2);
		}
	}
	
	public void attack(){
		attackTimer.updateAttackTimer();
		if(attackTimer.isReadyToAttack()){
			if(playerXCoordinate <= getX()){
				if( (getX() - playerXCoordinate) <= 50 ){
					dealDamage();
				}
			} else {
				if( (playerXCoordinate - getX()) <= 50 ){
					dealDamage();
				}
			}
		}
	}
	
	public void dealDamage(){
		getHandler().getPlayer().recieveDamage(attackDamage);
	}

	
	
	
	
	
}
