package edu.chl.Game.model.gameobject.item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.Id;



public abstract class Item extends GameObject implements Character{
	
	private State state;
	private GameHandler handler;
	
	public enum State {
		equipped,
		inventory,
		wating;
	}
	
	
	public enum Type {
		WEAPON,
		HAT,
		SHOES,
		LIFE,
		SPECIAL
	}
	
	public Item(int x, int y, int width, int height, Id id, GameHandler handeler){
		super(x,y,width,height,true ,id ,handeler);
		this.state = State.wating;
		this.handler = handeler;
	}
	
	public Item(){
		super(0,0,0,0,true ,null ,null);
		this.state = State.inventory;
	}
	
	
	
	public abstract String getInfo();
	
	public abstract String getPath();
	
	
	public  BufferedImage getBufferedImage(){
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResource(getPath()));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	
	@Override
	public  void render(Graphics g){
		
		
			//System.out.println("State:"+ this.state);
			if(this.state == State.wating){
				g.setColor(Color.ORANGE);
				g.fillRect(getX(), getY(), 64, 64);
			}else if(this.state == State.equipped){
				//g.drawImage(getBufferedImage(), getX(), getY(), getBufferedImage().getWidth(), getBufferedImage().getHeight(), null);
				equippedRender(g);
			}
		
	}
	
	@Override
	public  void update(){
		if(this.state == State.equipped){
			
			equippedUpdate();
		}
	}
	
	public abstract void equippedRender(Graphics g);
	public abstract void equippedUpdate();
	
	public void switchState(State state){
		this.state  = state;
		//System.out.println("State:"+ this.state);
		
		if(this.state == State.equipped){
			
		}else if(this.state == State.inventory){
			
		}else{
			
		}
	}
	


	
	@Override
	public abstract void effect();
	
	public abstract Type getType();
	
	public State getState(){
		return this.state;
	}
	
	@Override
	public abstract double getHealth();
	
	@Override
	public abstract double getArmor();
	
	
	/**
	 * @return the NAME
	 */
	public String getNAME(){
		
		return this.getClass().getSimpleName();
	}
	
	@Override
	public void remove() {
		
		if(this.getHandler() != null){
			getHandler().removeItem(this);
		}
		
	}

	public void setHandler(GameHandler handler) {
		
		this.handler = handler;
		
	}
	
	@Override
	public GameHandler getHandler() {
		
		return this.handler;
		
	}
	
}
