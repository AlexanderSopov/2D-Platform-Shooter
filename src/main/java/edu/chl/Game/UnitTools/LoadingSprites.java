package edu.chl.Game.UnitTools;
import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.*;

public class LoadingSprites {

	private GameHandler handler;
	private Sprite[] spriteArray;
	
	public LoadingSprites(Sprite[] spriteArray, GameHandler handler){
		this.handler = handler;
		this.spriteArray = spriteArray;
	}
	
	public void loadSprites(){

		for (int i = 0; i < 6; i++) {	// facing right
			spriteArray[i] = new Sprite(handler.getSheetDerangedBeast(), i, 0, 64, 64);
		}

		for (int i = 0; i < 6; i++) { 	// facing left
			spriteArray[i + 6] = new Sprite(handler.getSheetDerangedBeast(), i, 1, 64, 64);
		}

	}

}
