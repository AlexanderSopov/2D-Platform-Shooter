package edu.chl.Game.model.gameobject.entity.enemy;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.entityTools.FrameValues;
import edu.chl.Game.model.gameobject.entity.entityTools.OpponentUnitProperties;
import edu.chl.Game.view.graphics.SpriteSheet;

public class RoaringBrute extends EnemyUnit {

	public RoaringBrute(int x, int y, int width, int height, boolean solid,
			Id id, GameHandler handler, OpponentUnitProperties op, FrameValues frameValues, SpriteSheet spriteSheet_walking) {
		super(x, y, width, height, solid, id, handler, op, frameValues, spriteSheet_walking);
		
		getRenderClass().setFrameAmount(8);
		
	}

}
