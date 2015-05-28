package edu.chl.Game.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import edu.chl.Game.view.graphics.WorldMapAnimator;

/**
 * 
 * @author Marre
 *
 */
public class CharacterSelectionView {
	
	public Rectangle male = new Rectangle(Frame.WIDTH / 6, Frame.HEIGHT/4, 250, 350);
	public Rectangle female = new Rectangle(Frame.WIDTH/2 + 100, Frame.HEIGHT/4, 250, 350);
	
	private Font fnt;
	private WorldMapAnimator movingChar;
	
	public CharacterSelectionView(WorldMapAnimator movingChar){
		this.movingChar = movingChar;
	}
	
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		//Set background
		g.setColor(new Color(135, 206, 235));
		g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		
		//Set title
		fnt = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Character Selection", Frame.WIDTH/4, Frame.HEIGHT/7);
		
		//Set the rectangles
		fnt = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt);
		
		drawRect(g2, male, "Male");
		drawRect(g2, female, "Female");
		
		movingChar.renderAnimation(g, (int)male.getCenterX() - 50, (int)male.getCenterY(), 128, 128);
		movingChar.renderAnimation(g, (int)female.getCenterX() - 50, (int)female.getCenterY(), 128, 128);
	}
	
	private void drawRect(Graphics2D g, Rectangle r, String Name){
			g.draw(r);
			g.drawString(Name, (int)r.getCenterX() - 25, r.y+35);
	}
}
