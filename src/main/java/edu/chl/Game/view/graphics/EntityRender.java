package edu.chl.Game.view.graphics;
import java.awt.Graphics;

public class EntityRender {

	public void renderAnimateRight(Graphics g, Sprite[] unit, int frame, int x, int y, int width, int height){
		g.drawImage(unit[frame].getBufferedImage(), x, y, width, height, null);
	}
	
	public void renderAnimateLeft(Graphics g, Sprite[] unit, int frame, int x, int y, int width, int height, int frameAmount){
		g.drawImage(unit[frame+frameAmount].getBufferedImage(), x, y, width, height, null);
	}
	
	public void renderNotAnimateRight(Graphics g, Sprite[] unit, int frame, int x, int y, int width, int height){
		g.drawImage(unit[0].getBufferedImage(), x, y, width, height, null);
	}
	
	public void renderNotAnimateLeft(Graphics g, Sprite[] unit, int frame, int x, int y, int width, int height, int frameAmount){
		g.drawImage(unit[frameAmount].getBufferedImage(), x, y, width, height, null);
	}

}
