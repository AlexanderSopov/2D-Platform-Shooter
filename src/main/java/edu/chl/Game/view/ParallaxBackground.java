package edu.chl.Game.view;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.view.graphics.BgdImage;


public class ParallaxBackground implements Observer {

	
	private static BgdImage background;
	private static BgdImage foreground;
	//private Image clouds;
	
	

	@Override
	public void update(Observable arg0, Object arg1) {
		updateY();
		updateX();
	}
	
	private void updateY() {
		int y = - Camera.getY();
		background.setY(y);
		foreground.setY(y);
	}


	private void updateX() {
		int x = - Camera.getX();
		background.setX(x);
		foreground.setX(x);
	}



}
