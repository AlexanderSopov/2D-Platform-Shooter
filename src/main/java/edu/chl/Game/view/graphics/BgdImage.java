package edu.chl.Game.view.graphics;

import java.awt.image.BufferedImage;

public class BgdImage extends BufferedImage {
	private int x, y;
	public BgdImage(int width, int height, int imageType) {
		super(width, height, imageType);
		x=0;
		y=0;
	}
	
	
	public int getY(){ return y;}
	public int getX(){ return x;}
	public void setX(int x){ this.x=x;}
	public void setY(int y){ this.y=y;}
}
