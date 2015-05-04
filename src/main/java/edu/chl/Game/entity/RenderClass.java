package edu.chl.Game.entity;
import java.awt.Graphics;
import edu.chl.Game.graphics.Sprite;

public class RenderClass {

	public void renderAnimateRight(Graphics g, Sprite[] unit, int frame, double x, double y, double width, double height){
		g.drawImage(unit[frame].getBufferedImage(), round(x), round(y), round(width), round(height), null);
	}
	
	public void renderAnimateLeft(Graphics g, Sprite[] unit, int frame, double x, double y, double width, double height){
		g.drawImage(unit[frame+6].getBufferedImage(), round(x), round(y), round(width), round(height), null);
	}
	
	public void renderNotAnimateRight(Graphics g, Sprite[] unit, int frame, double x, double y, double width, double height){
		g.drawImage(unit[0].getBufferedImage(), round(x), round(y), round(width), round(height), null);
	}
	
	public void renderNotAnimateLeft(Graphics g, Sprite[] unit, int frame, double x, double y, double width, double height){
		g.drawImage(unit[6].getBufferedImage(), round(x), round(y), round(width), round(height), null);
	}

	private int round(double x) {
		double slop = x-(int)x;
		slop -= 0.5;
		if (slop < 0)
			return (int)x;
		return (int)x+1;
	}

}
