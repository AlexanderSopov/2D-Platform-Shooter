package edu.chl.Game.entity;
import java.awt.Graphics;
import edu.chl.Game.graphics.Sprite;

public class RenderClass {

	public void renderAnimateRight(Graphics g, Sprite[] unit, int frame, int x, int y, int width, int height){
		g.drawImage(unit[frame].getBufferedImage(), x, y, width, height, null);
	}
	
	public void renderAnimateLeft(Graphics g, Sprite[] unit, int frame, int x, int y, int width, int height){
		g.drawImage(unit[frame+6].getBufferedImage(), x, y, width, height, null);
	}
	
	public void renderNotAnimateRight(Graphics g, Sprite[] unit, int frame, int x, int y, int width, int height){
		g.drawImage(unit[0].getBufferedImage(), x, y, width, height, null);
	}
	
	public void renderNotAnimateLeft(Graphics g, Sprite[] unit, int frame, int x, int y, int width, int height){
		g.drawImage(unit[6].getBufferedImage(), x, y, width, height, null);
	}

}
