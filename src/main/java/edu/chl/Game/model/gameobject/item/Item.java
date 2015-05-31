package edu.chl.Game.model.gameobject.item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.Id;

public abstract class Item extends GameObject implements Character {

	private State state;
	private GameHandler handler;

	public enum State {
		equipped, inventory, wating;
	}

	public enum Type {
		WEAPON, HAT, SHOES, LIFE, SPECIAL
	}

	public Item(int x, int y, int width, int height, Id id, GameHandler handeler) {
		super(x, y, width, height, true, id, handeler);
		this.state = State.wating;
		this.handler = handeler;
	}

	public Item() {
		super(0, 0, 0, 0, true, null, null);
		this.state = State.inventory;
	}


	@Override
	public void render(Graphics g) {

		if (this.state == State.wating) {
			g.setColor(Color.ORANGE);
			g.fillRect(getX(), getY(), 32, 32);
		} else if (this.state == State.equipped) {
			equippedRender(g);
		}

	}

	@Override
	public void update() {
		
		if (this.state == State.equipped) {
			equippedUpdate();
		}
		
	}

	public abstract void equippedRender(Graphics g);

	public abstract void equippedUpdate();



	@Override
	public void remove() {

		if (this.getHandler() != null) {
			getHandler().removeItem(this);
		}

	}
	
	@Override
	public abstract void effect();
	
	
	
	public void switchState(State state) {

		if (state != null) {
			this.state = state;
		}
		
	}
	

	public void setHandler(GameHandler handler) {
		if (handler != null) {
			this.handler = handler;
		}

	}
	
	public abstract Type getType();

	public State getState() {
		return this.state;
	}
	
	public abstract String getInfo();

	public abstract String getPath();
	
	@Override
	public abstract double getHealth();

	@Override
	public abstract double getArmor();

	/**
	 * @return the NAME
	 */
	public String getNAME() {

		return this.getClass().getSimpleName();
	}
	

	@Override
	public GameHandler getHandler() {

		return this.handler;

	}
	
	public BufferedImage getBufferedImage() {
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResource(getPath()));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public BufferedImage getFlippedBufferedImage() {
		BufferedImage image = getBufferedImage();
		BufferedImage flippedImage = new BufferedImage(image.getWidth(),
				image.getHeight(), image.getType());

		AffineTransform transformation = AffineTransform.getTranslateInstance(
				image.getWidth(), 0);
		AffineTransform fliping = AffineTransform.getScaleInstance(-1d, 1d);
		transformation.concatenate(fliping);

		Graphics2D g = flippedImage.createGraphics();
		g.setTransform(transformation);
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return flippedImage;
	}

}
