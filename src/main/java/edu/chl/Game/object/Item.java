package edu.chl.Game.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.chl.Game.handler.GameHandler;


public abstract class Item extends GameObject{
	
	private final String NAME;
	private String info;
	private BufferedImage buffImage;
	
	//Temporary
	private int x,y, width,height; 
	
	Item(int x, int y, int width, int height, Id id, GameHandler handeler,String name, String info, BufferedImage buffImage){
		super(/*x,y,width,height,id,handeler*/);
		this.x = x;
		this.y = y;
		this.buffImage = buffImage;
		this.width = width;
		this.height = height;
		this.info = info;
		this.NAME = name;
		
		/* suggestions we can have two constructors one if there is an 
		 * bufferd image and another if there is an grafical-object 
		 */
	}
	
	//Temporary
	public abstract void render(Graphics g);
	
	//Temporary
	public abstract void update();
	
	public abstract void effect();
	
	public String getInfo(){
		return info;
	}
	
	public BufferedImage getBuffImage(){
		return buffImage;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	protected void setX(int x) {
		this.x = x;
	}

	protected void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the nAME
	 */
	public String getNAME() {
		return NAME;
	}
	
	
}
