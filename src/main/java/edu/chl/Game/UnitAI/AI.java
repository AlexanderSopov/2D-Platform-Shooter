package edu.chl.Game.UnitAI;
import edu.chl.Game.*;
import edu.chl.Game.handler.*;
import edu.chl.Game.entity.*;

public class AI {
	
	private GameHandler handler;
	private UnitProperties uP;
	private int playerXCoordinate;
	
	public AI(GameHandler handler, UnitProperties uP){
		
		this.uP = uP;
		this.handler = handler;
		
	}
	
	public void followPlayer(){
		if(handler.getPlayer().getX() < uP.getX()){
			uP.setVelX(-2);
		} else {
			uP.setVelX(2);
		}
	}


}
