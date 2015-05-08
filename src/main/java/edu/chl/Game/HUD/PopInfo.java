package edu.chl.Game.HUD;
import java.awt.*;
import edu.chl.Game.handler.*;

public class PopInfo {
	
	private GameHandler gameHandler;
	
	public PopInfo(GameHandler gameHandler){
		this.gameHandler = gameHandler;
	}
	
	public void renderPopInfo(Graphics g){
		g.drawRect(gameHandler.getPlayer().getX(), gameHandler.getPlayer().getY() - 20, 30, 30);
	}

}
