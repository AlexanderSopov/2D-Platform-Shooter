package edu.chl.Game.model.gameobject;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import edu.chl.Game.model.physics.CalculateBounds;
import edu.chl.Game.view.graphics.SpriteSheet;
import edu.chl.Game.model.gameobject.entity.player.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.Main;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.physics.Vector2D;
import edu.chl.Game.model.physics.collisions.BoundingBoxes;

/**
 * The GameObject is the highest superclass in the hierarchy of the interacting
 * elements in the game. This class models a squareshaped unit and enables it
 * physical properties aswell as connecting it to the central handler that keeps
 * track of the units of the game.
 * 
 * @author Alexander Sopov
 * @auhor Rasmus Andersson
 * @auhor Oliver Tunberg
 */

public abstract class GameObject implements Observer, GameInterface {
	public static RefreshTimer gt = Main.game;
	private UnitState unitState;
	private PhysicalProperties physicalProperties;
	private GameHandler handler;
	private Id id;
	private WeaponProperties wp;

	public GameObject(int x, int y, int width, int height, boolean solid,
			Id id, GameHandler handler) {

		physicalProperties = new PhysicalProperties(new Vector2D(x, y), // Position
				new Vector2D(0, 0), // Velocity
				width, height); // Size

		this.id = id;
		this.handler = handler;
		this.unitState = new UnitState(id, solid);
	}

	/**
	 * Overrided from the Observer interface. Calls the position update and the
	 * graphical render method to model the game objects.
	 * 
	 * @param arg Graphics component
	 * @param o not used
	 */
	@Override
	public void update(Observable o, Object arg) {
		try {
			Graphics g = (Graphics) arg;
			render(g);
			update();
		} catch (IllegalArgumentException e) {
			System.out.println("Something's wrong with GameObjects Update!");
		}
	}

	/**
	 * Abstract graphical render method. Draws the graphics of the characters, tiles and information bars.
	 * 
	 * @param g Graphics component to draw with.
	 */
	public abstract void render(Graphics g);

	/**
	 * Abstract position and motion update method. Decides the behaviour of this object during each frame.
	 */
	public abstract void update();

	/**
	 * Abstract method to remove this object from the official list of units on the playingfield.
	 */
	public abstract void remove();

	/**
	 * Getter method for the UnitState instancevariable.
	 * 
	 * @return the class managing the states of this unit.
	 */
	public UnitState getUnitState() {
		return unitState;
	}

	/**
	 * @return a reference to the handler which this object is connected with. 
	 * 
	 * All objects should be registered 
	 * at one and the same handler.
	 */
	public GameHandler getHandler() {
		return this.handler;
	}

	/**
	 * Getter method for the enum Id.
	 * 
	 * @return the id of this object.
	 */
	public Id getId() {
		return this.id;
	}

	/**
	 * Getter method for the integer x coordinate
	 * 
	 * @return the x coordinate of this object
	 */
	public int getX() {
		return physicalProperties.getX();
	}

	/**
	 * Getter method for the integer y coordinate
	 * 
	 * @return the y coordinate of this object
	 */
	public int getY() {
		return physicalProperties.getY();
	}

	/**
	 * Getter method for the vector located at center of this object.
	 * 
	 * @return the vector to the center of the object square.
	 */
	public Vector2D getCenter() {
		return physicalProperties.getCenter();
	}

	/**
	 * Getter method for the integer value of the x coordinate in the center of this object.
	 * 
	 * @return x coordinate for the center of the square.
	 */
	public int getCenterX() {
		return getCenter().getX();
	}

	/**
	 * Getter method for the integer value of the y coordinate in the center of this object.
	 * 
	 * @return y coordinate for the center of the square.
	 */
	public int getCenterY() {
		return getCenter().getY();
	}

	/**
	 * Setter method for the x coordinate in the vector of this objects position. 
	 * 
	 * @param x sets x coordinate.
	 */
	public void setX(int x) {
		physicalProperties.setX(x);
	}

	/**
	 * Setter method for the y coordinate in the vector of this objects position. 
	 * 
	 * @param y sets y coordinate.
	 */
	public void setY(int y) {
		physicalProperties.setY(y);
	}

	/**
	 * Setter method for both the x and y coordinates in the vector of this objects position.
	 * 
	 * @param x sets the x coordinate.
	 * @param y sets the y coordinate.
	 */
	public void setPosition(int x, int y) {
		setPosition(new Vector2D(x, y));
	}

	/**
	 * Setter method for both the x and y coordinates in the vector of this objects position.
	 * 
	 * @param v sets the positon vector.
	 */
	public void setPosition(Vector2D v) {
		physicalProperties.setPosition(v);
	}

	/**
	 * Getter method for the vector of the position of this object.
	 * 
	 * @return the position vector.
	 */
	public Vector2D getPosition() {
		return physicalProperties.getPosition();
	}

	/**
	 * Getter method for the width of this object.
	 * 
	 * @return the width.
	 */
	public int getWidth() {
		return physicalProperties.getWidth();
	}

	/**
	 * Getter method for the height of this object.
	 * 
	 * @return the height.
	 */
	public int getHeight() {
		return physicalProperties.getHeight();
	}

	/**
	 * Getter method for the integer value of the velocity of this object on the x axis.
	 * 
	 * @return the velocity on the x axis.
	 */
	public int getVelX() {
		return physicalProperties.getVelX();
	}

	/**
	 * Setter method for the integer value of the velocity of this object on the x axis.
	 * 
	 * @param velX sets the velocity on the x axis.
	 */
	public void setVelX(int velX) {
		physicalProperties.setVelX(velX);
	}

	/**
	 * Getter method for the integer value of the velocity of this object on the y axis.
	 * 
	 * @return the velocity on the y axis.
	 */
	public int getVelY() {
		return physicalProperties.getVelY();
	}

	/**
	 * Setter method for the integer value of the velocity of this object on the y axis.
	 * 
	 * @param vely sets the velocity on the y axis.
	 */
	public void setVelY(int vely) {

		physicalProperties.setVelY(vely);
	}

	/**
	 * Getter method for the vector of the velocity of this object.
	 * 
	 * @return integer value of velocity
	 */
	public Vector2D getVelocity() {
		return physicalProperties.getVelocity();
	}

	/**
	 * Setter method for the vector of the velocity of this object.
	 * 
	 * @param x sets the integer x velocity.
	 * @param y sets the integer y velocity.
	 */
	public void setVelocity(int x, int y) {
		setVelocity(new Vector2D(x, y));
	}

	/**
	 * Setter method for the vector of the velocity of this object.
	 * 
	 * @param v vector to set the velocity.
	 */
	public void setVelocity(Vector2D v) {
		physicalProperties.setVelocity(v);
	}

	/**
	 * Method concerning the cooldown of attacking. Sets the entity to be affected and the frameiterator that
	 * controlles the delay.
	 * 
	 * @param en entity apply cooldown to.
	 * @param fi frameIterator that handles delay.
	 */
	public void setWeaponProperties(Entity en, FrameIterator fi) {
		this.wp = new WeaponProperties(en, fi);
	}

	/**
	 * Getter method for the WeaponProperties instance variable.
	 * 
	 * @return WeaponProperties reference.
	 */
	public WeaponProperties getWeaponProperties() {
		return wp;
	}

	/**
	 * Updates the cooldown of the attack. Moves the cooldown one tick through the frameIterator it used. 
	 */
	public void iterateCooldown() {
		wp.updateCooldown();
	}

}
