package edu.chl.Game.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;



public class ParallaxBackground implements Observer {

	
	public BufferedImage background, foreground, foreground1;
	public int backgroundY, backgroundX, foregroundX, foregroundY, foreground1X, foreground1Y;
	//private static BufferedImage clouds;
	
	public ParallaxBackground(){
		try {
			background = ImageIO.read(getClass().getResource("/background.png"));
			foreground = ImageIO.read(getClass().getResource("/foreground.png"));
			foreground1 = ImageIO.read(getClass().getResource("/foreground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		//updateY();
		updateX();
	}
	
	private void updateY() {
		int y = Camera.getY();
		backgroundY = y/10;
		foregroundY = y/10;
	}


	private void updateX() {
		int x = Camera.getX();
		//backgroundX = x;
		setForeground(x);
	}

	private void setForeground(int x) {
		if (x - foregroundX == 1000)
			foregroundX = x;
		else
			foregroundX = x/3;
		foreground1X = foregroundX + 1000; 
	}
	




}
