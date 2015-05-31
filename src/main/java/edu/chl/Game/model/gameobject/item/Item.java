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


/**
 * 
 * Item is a GameObject and u can pick 
 * them up in the inventory and later use them
 * 
 * @author Rasmus
 *
 */
public abstract class Item extends GameObject implements Character {

	private State state;
	private GameHandler handler;
	
	/**
	 * 
	 *Items have a state 
	 *
	 *equipped - in use
	 *
	 *inventory - stored in inventory
	 *
	 *wating - wating to be picked up
	 *
	 */
	public enum State {
		equipped, inventory, wating;
	}
	
	/**
	 * 
	 * Type defines what kind of item 
	 *
	 */
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
		//rendering a orange box
		if (this.state == State.wating) {
			g.setColor(Color.ORANGE);
			g.fillRect(getX(), getY(), 64, 64);
			//rendering the specific item 
		} else if (this.state == State.equipped) {
			equippedRender(g);
		}

	}
	
	//rendering the specific item when equipped
	public abstract void equippedRender(Graphics g);

	@Override
	public void update() {
		
		if (this.state == State.equipped) {
			//updating the equipped item
			equippedUpdate();
		}
		
	}
	
	//updating the equipped item
	public abstract void equippedUpdate();



	@Override
	public void remove() {

		if (this.getHandler() != null) {
			//demoving from handler itemlist
			getHandler().removeItem(this);
		}

	}
	
	@Override
	public abstract void effect();
	
	// --- Setters And Getters ---
	
	
	public void switchState(State state) {

		if (state != null) {
			this.state = state;
		}
		
	}
	
	
	//set the handler if missing
	public void setHandler(GameHandler handler) {
		if (handler != null) {
			this.handler = handler;
		}

	}
	
	public abstract Type getType();

	public State getState() {
		return this.state;
	}
	
	//get info about Item
	public abstract String getInfo();
	
	//get path to Image of item
	public abstract String getPath();
	
	@Override
	public abstract double getHealth();

	@Override
	public abstract double getArmor();


	public String getNAME() {

		return this.getClass().getSimpleName();
	}
	

	@Override
	public GameHandler getHandler() {

		return this.handler;

	}
	
	
	// Creates a Bufferedimage from path and return it 
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
	
	//Flipp the Bufferedimage and return it
	public BufferedImage getFlippedBufferedImage() {
		
		// get the image
		BufferedImage image = getBufferedImage();
		BufferedImage flippedImage = new BufferedImage(image.getWidth(),
				image.getHeight(), image.getType());
		
		// create Affine transformation for the flipping
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
