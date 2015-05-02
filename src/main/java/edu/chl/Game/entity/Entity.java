package edu.chl.Game.entity;

import java.awt.Rectangle;

import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.GameObject;
import edu.chl.Game.object.Id;

public abstract class Entity extends GameObject {

	private int facing = 0;
	private int frame = 0;
	private int frameDelay = 0;
	private double gravity = 0.0;

	private boolean jumping = false;
	private boolean falling = true;
	private RenderClass renderClass;
	private FacingDirection facingDirection;
	private CalculateBounds calculateBounds;

	public Entity(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		
		renderClass = new RenderClass();
		facingDirection = facingDirection.FacingRight;
		calculateBounds = new CalculateBounds(getUnitProperties());

	}
	
	public CalculateBounds getCalculateBounds(){
		return calculateBounds;
	}

	public void remove() {
		getHandler().removeEntity(this);
	}

	public RenderClass getRenderClass() {
		return renderClass;
	}

	public void setFacingDirection(FacingDirection d) {
		facingDirection = d;
	}

	public FacingDirection getFacingDirection() {
		return facingDirection;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		this.facing = facing;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public int getFrameDelay() {
		return frameDelay;
	}

	public void setFrameDelay(int frameDelay) {
		this.frameDelay = frameDelay;
	}
	

}
