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

/*
 * Author: Alexander Sopov, Oliver Tunberg, Rasmus Andersson
 */




public abstract class GameObject implements Observer, GameInterface {
	public static RefreshTimer gt = Main.game;
	private UnitState unitState;
	private PhysicalProperties physicalProperties;
	private double healthPoints;
	private GameHandler handler;
	private Id id;
	private boolean solid;
	private WeaponProperties wp;
	

	public GameObject(int x, int y, int width, int height, boolean solid,
			Id id, GameHandler handler) {
		
		physicalProperties = new PhysicalProperties(
                		 new Vector2D(x,y), //Position
                		 new Vector2D(0,0), //Velocity
                		 width, height);	//Size
		

		this.id = id;
		this.solid = solid;
		this.handler = handler;
		this.unitState = new UnitState(id, solid);
	}

	public void update(Observable o, Object arg) {
		try {
			Graphics g = (Graphics) arg;
			render(g);
			update();
		} catch (IllegalArgumentException e) {
			System.out.println("Something's wrong with GameObjects Update!");
		}
	}

	public abstract void render(Graphics g);

	public abstract void update();

	public abstract void remove();
	
	public UnitState getUnitState() {
		return unitState;
	}



	public GameHandler getHandler() {
		return this.handler;
	}

	public Id getId() {
		return this.id;
	}


	public int getX() {
		return physicalProperties.getX();
	}

	public int getY() {
		return physicalProperties.getY();
	}
	
	public Vector2D getCenter(){
		return physicalProperties.getCenter();
	}
	
	public int getCenterX(){
		return getCenter().getX();
	}
	
	public int getCenterY(){
		return getCenter().getY();
	}

	public void setX(int x) {
		physicalProperties.setX(x);
	}

	public void setY(int y) {
		physicalProperties.setY(y);
	}
	
	public void setPosition(int x, int y){
		setPosition(new Vector2D(x,y));
	}
	
	public void setPosition(Vector2D v){
		physicalProperties.setPosition(v);
	}
	
	public Vector2D getPosition(){
		return physicalProperties.getPosition();
	}
	public int getWidth() {
		return physicalProperties.getWidth();
	}

	public int getHeight() {
		return physicalProperties.getHeight();
	}

        
    public double getHealthPoints(){
		return healthPoints;
    }

        
    public int getVelX(){
		return physicalProperties.getVelX();
	}
	
	public void setVelX(int velX){
		physicalProperties.setVelX(velX);
	}
	
	public int getVelY(){
		return physicalProperties.getVelY();
	}
	
	public void setVelY(int vely){
		physicalProperties.setVelY(vely);
	}
	
	public Vector2D getVelocity(){
		return physicalProperties.getVelocity();
	}
	
	public void setVelocity(int x, int y){
		setVelocity(new Vector2D(x,y));
	}
	public void setVelocity(Vector2D v){
		physicalProperties.setVelocity(v);
	}
	
	public void setWeaponProperties(Entity en, FrameIterator fi){
		this.wp = new WeaponProperties(en, fi);
	}
	
	public WeaponProperties getWeaponProperties(){
		return wp;
	}
	
	public void iterateCooldown(){
		wp.updateCooldown();
	}
	

	
}
