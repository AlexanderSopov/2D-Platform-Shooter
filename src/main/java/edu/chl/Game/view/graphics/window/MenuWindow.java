package edu.chl.Game.view.graphics.window;

import java.awt.Graphics;
import edu.chl.Game.view.graphics.*;
import edu.chl.Game.controller.*;
import edu.chl.Game.model.gameobject.entity.player.*;
import java.awt.Font;


public class MenuWindow {

	private int menuX;
	private int menuY;
	private GameHandler handler;
	private Player player;
	private MouseInput mi;
	private boolean showMenu;
	private SpriteSheet sheetMenuWindow;
	private Sprite spriteMenuWindow;
	private LoadingSprites load;
	private TalentButton[] tb;
	

	public MenuWindow(GameHandler h, MouseInput mi) {
		this.mi = mi;
		this.handler = h;
		this.player = handler.getPlayer();
		//this.showMenu = true;
		load = new LoadingSprites();
		sheetMenuWindow = new SpriteSheet("/mw00.png");
		spriteMenuWindow = load.loadSingleSprite(sheetMenuWindow, spriteMenuWindow, 900, 500);
		tb = new TalentButton[7];
		for(int i=0; i<tb.length; i++){
			tb[i] = new TalentButton("/ta" + "0" + i + ".png");
		}
	}

	public void displayWindow(Graphics g) {
		if (showMenu) {
			menuX = player.getX() - 450;
			menuY = player.getY() - 350;	
			g.drawImage(spriteMenuWindow.getBufferedImage(), menuX, menuY, 900, 500, null);
			tb[0].displayButton(g, menuX+100, menuY+250);
			tb[1].displayButton(g, menuX+300, menuY+150);
			tb[2].displayButton(g, menuX+300, menuY+350);
			tb[3].displayButton(g, menuX+500, menuY+150);
			tb[4].displayButton(g, menuX+500, menuY+350);
			tb[5].displayButton(g, menuX+700, menuY+150);
			tb[6].displayButton(g, menuX+700, menuY+350);
		}
	}
	
	public void pressButton(int x, int y){
		for(int i=0; i<tb.length; i++){
			tb[i].hitArea( (x+player.getX() - 500) , ((y+player.getY() - 400)) );
		}
	}
	
	public void toggleMenu(){
		if(showMenu){
			showMenu = false;
		} else {
			showMenu = true;
		}
	}

}
