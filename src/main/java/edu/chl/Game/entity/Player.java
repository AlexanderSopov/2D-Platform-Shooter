package edu.chl.Game.entity;

import java.awt.Graphics;
import java.util.LinkedList;

import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class Player extends Entity {

	private Sprite player[] = new Sprite[40];
	private ContactWithEnemy contactWithEnemy;
	private GravitationalProperties gravitationalProperties;

	public Player(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		
		getRenderClass().setFrameAmount(20);

		// facing right
		for (int i = 0; i < 20; i++) {
			player[i] = new Sprite(handler.getSheetPlayer(), i, 0, 62, 62);
		}

		// facing left
		for (int i = 0; i < 20; i++) {
			player[i + 20] = new Sprite(handler.getSheetPlayer(), i, 1, 62, 62);
		}
		
		this.contactWithEnemy = new ContactWithEnemy(getUnitProperties(), getCalculateBounds());
		this.gravitationalProperties = new GravitationalProperties(getUnitProperties(), getEntityProperties(), getEntityState());
	}

	@Override
	public void render(Graphics g) {
		if (getUnitState().isAnimate()) {
			if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
				getRenderClass().renderAnimateRight(g, player,
						getEntityProperties().getFrame(),
						getUnitProperties().getX(), getUnitProperties().getY(),
						getUnitProperties().getHeight(),
						getUnitProperties().getWidth());
			} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
				getRenderClass().renderAnimateLeft(g, player,
						getEntityProperties().getFrame(),
						getUnitProperties().getX(), getUnitProperties().getY(),
						getUnitProperties().getHeight(),
						getUnitProperties().getWidth());
			}
		}

		else if (!getUnitState().isAnimate()) {
			if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
				getRenderClass().renderNotAnimateRight(g, player,
						getEntityProperties().getFrame(),
						getUnitProperties().getX(), getUnitProperties().getY(),
						getUnitProperties().getHeight(),
						getUnitProperties().getWidth());
			}

			else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
				getRenderClass().renderNotAnimateLeft(g, player,
						getEntityProperties().getFrame(),
						getUnitProperties().getX(), getUnitProperties().getY(),
						getUnitProperties().getHeight(),
						getUnitProperties().getWidth());
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
	}
	
	public void iterateThroughFrames(){
		increaseFrameDelay();
	}

	public void increaseFrameDelay(){
		getEntityProperties().setFrameDelay(getEntityProperties().getFrameDelay() + 1);
		checkFrameDelayLimit();
	}
	
	public void checkFrameDelayLimit(){
		if (2 <= getEntityProperties().getFrameDelay()){
			increaseFrame();
			setFrameDelayToZero();
		}
	}
	
	public void increaseFrame(){
		getEntityProperties().setFrame(getEntityProperties().getFrame() + 1);
		checkFrameLimit();
	}
	
	public void checkFrameLimit(){
		if (20 <= getEntityProperties().getFrame()) {
			setFrameToZero();
		}
	}
	
	public void setFrameDelayToZero(){
		getEntityProperties().setFrameDelay(0);
	}
	
	public void setFrameToZero(){
		getEntityProperties().setFrame(0);
	}
	
	public void recieveDamage(double damage){
		System.out.println(getUnitProperties().getHealthPoints());
		getUnitProperties().setHealthPoints(getUnitProperties().getHealthPoints() - damage);
	}
	
	


	
}


