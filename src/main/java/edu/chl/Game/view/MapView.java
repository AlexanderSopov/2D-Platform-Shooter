package edu.chl.Game.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

/**
 * Temporary class for the map
 * @author Martin Tran
 * @version 1.0
 */

public class MapView {
	
	public Rectangle level1 = new Rectangle(Frame.WIDTH / 8, Frame.HEIGHT/4, 130, 60);
	public Rectangle level2 = new Rectangle(Frame.WIDTH / 4, Frame.HEIGHT/2, 130, 60);
	public Rectangle level3 = new Rectangle(Frame.WIDTH / 3+50 + 80, Frame.HEIGHT/4 + 140, 130, 60);
	public Rectangle level4 = new Rectangle(Frame.WIDTH / 2 + 150, Frame.HEIGHT/4, 130, 60);
	public Rectangle level5 = new Rectangle(Frame.WIDTH / 2 + 250, Frame.HEIGHT/2, 130, 60);
	
	public Rectangle shop = new Rectangle(Frame.WIDTH - 100, Frame.HEIGHT-60, 70, 50);
	public Rectangle character = new Rectangle(Frame.WIDTH - 170, Frame.HEIGHT-60, 70, 50);
	
	private Font fnt;
	
	public MapView(){
		
	}
	
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		//Set background
		g.setColor(new Color(135, 206, 235));
		g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		
		//Set title;
		fnt = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("WorldMap", Frame.WIDTH/3 + 50, Frame.HEIGHT/7);
		
		//Set the rectangles
		fnt = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt);
		
		drawRect(g2, level1, "Level 1", "Stage");
		drawRect(g2, level2, "Level 2", "Stage");
		drawRect(g2, level3, "Level 3", "Stage");
		drawRect(g2, level4, "Level 4", "Stage");
		drawRect(g2, level5, "Level 5", "Stage");
		drawRect(g2, shop, "Shop", "Button");
		drawRect(g2, character, "Char", "Button");
		
		drawArrow(g2, level1, level2, "DownToUp");
		drawArrow(g2, level2, level3, "SideToSide");
		drawArrow(g2, level3, level4, "UpToDown");
		drawArrow(g2, level4, level5, "DownToUp");
		
	}
	
	private void drawRect(Graphics2D g, Rectangle r, String Name, String type){
		if(type.equals("Stage")){
			g.draw(r);
			g.drawString(Name, r.x +30, r.y+35);
		}else if(type.equals("Button")){
			g.draw(r);
			g.drawString(Name, r.x+10, r.y+30);
		}
	}
	
    private void drawArrow(Graphics2D g, Rectangle r1, Rectangle r2, String type) {
    	if(type.equals("DownToUp")){
    		g.drawLine(r1.x+r1.width/2, r1.y+r1.height, r2.x+r2.width/2, r2.y);
    	}else if(type.equals("UpToDown")){
    		g.drawLine(r1.x+r1.width/2, r1.y, r2.x+r2.width/2, r2.y+r2.height);
    	}else if(type.equals("SideToSide")){
    		g.drawLine(r1.x+r1.width, r1.y+r1.height/2, r2.x, r2.y+r2.height/2);
    	}
    }
	
}
