package edu.chl.Game.model.gameobject.entity.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.Id;



public abstract class Item extends GameObject implements Character{
	
	private State state;
	
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
		
		if(getBufferedImage() != null){
			
			g.drawImage(getBufferedImage(), getX(), getY(), getBufferedImage().getWidth(), getBufferedImage().getHeight(), null);
		}
	}
	
	public void switchState(State state){
		this.state  = state;
		if(this.state == State.equipped){
			
		}else if(this.state == State.inventory){
			
		}else{
			
		}
	}
	
	@Override
	public abstract void update();
	
	@Override
	public abstract void effect();
	
	public abstract Type getType();
	
	@Override
	public abstract double getHealth();
	
	@Override
	public abstract double getArmor();
	
	
	/**
	 * @return the NAME
	 */
	public String getNAME(){
		
		return this.getClass().getName();
	}
	
}
