package edu.chl.Game.Units;

import edu.chl.Game.UnitTools.FrameValues;
import edu.chl.Game.UnitTools.OpponentUnitProperties;
import edu.chl.Game.graphics.SpriteSheet;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;

public class RoaringBrute extends EnemyUnit {

	public RoaringBrute(int x, int y, int width, int height, boolean solid,
			Id id, GameHandler handler, OpponentUnitProperties op, FrameValues frameValues, SpriteSheet spriteSheet_walking) {
		super(x, y, width, height, solid, id, handler, op, frameValues, spriteSheet_walking);
	}

}
