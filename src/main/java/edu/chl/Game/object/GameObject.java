package edu.chl.Game.object;
import java.awt.Graphics;

public abstract class GameObject {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int vX;
	private int vY;
	private boolean isSolid;
	private Id id;
	
	public GameObject(int x, int y, int width, int height, int vX, int vY, boolean isSolid, Id id){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isSolid = isSolid;
		this.id = id;
	}
	
	public abstract void render(Graphics g);
	
	public abstract void update();
	
	// Getter Methods
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public boolean getIsSolid(){
		return isSolid;
	}
	
	public Id getId(){
		return id;
	}
	
	// Setter Methods
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setvX(int vX){
		this.vX = vX;
	}
	
	public void setvY(int vY){
		this.vY = vY;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public void setWidth(int height){
		this.height = height;
	}
	
	
	
	

}
