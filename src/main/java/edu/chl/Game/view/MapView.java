package edu.chl.Game.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MapView {
	
	public Rectangle startButton = new Rectangle(Frame.WIDTH / 3 + 80, Frame.HEIGHT/4, 160, 60);
	public Rectangle newButton = new Rectangle(Frame.WIDTH / 3 + 80, Frame.HEIGHT/4 + 70, 160, 60);
	public Rectangle optionButton = new Rectangle(Frame.WIDTH / 3 + 80, Frame.HEIGHT/4 + 140, 160, 60);
	public Rectangle creditButton = new Rectangle(Frame.WIDTH / 3 + 80, Frame.HEIGHT/4 + 210, 160, 60);
	public Rectangle exitButton = new Rectangle(Frame.WIDTH / 3 + 80, Frame.HEIGHT/4 + 350, 160, 60);
	
	private Font fnt;
	
	public MapView(){
		
	}
	
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(new Color(135, 206, 235));
		g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		
		fnt = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("JumpShooter", Frame.WIDTH / 3, Frame.HEIGHT/5);
		
		fnt = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt);
		
		g2.draw(startButton);
		g2.draw(newButton);
		g2.draw(optionButton);
		g2.draw(creditButton);
		g2.draw(exitButton);
		
		g.drawString("Start Game", startButton.x + 30, startButton.y + 35);
		g.drawString("New Game", newButton.x + 30, newButton.y + 35);
		g.drawString("Option", optionButton.x + 45, optionButton.y + 35);
		g.drawString("Credits", creditButton.x + 45, creditButton.y + 35);
		g.drawString("Exit", exitButton.x + 60, exitButton.y + 35);
	}
	
}
