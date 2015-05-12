package edu.chl.Game.view.graphics;
import edu.chl.Game.handler.*;
import edu.chl.Game.view.graphics.*;

public class LoadingSprites {
	
	
	public Sprite[] loadSprites(SpriteSheet spriteSheet, Sprite[] spriteArray, int numberOfSprites, int width, int height){	
		
		for (int i = 0; i < (numberOfSprites/2); i++) {	
			spriteArray[i] = new Sprite(spriteSheet, i, 0, width, height);
		}
		
		for (int i = 0; i < (numberOfSprites/2); i++) { 
			spriteArray[i + (numberOfSprites/2)] = new Sprite(spriteSheet, i, 1, width, height);
		}
		
		return spriteArray;
		
	}

}
