package edu.chl.Game.Units;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.UnitTools.*;
import edu.chl.Game.Main;
import edu.chl.Game.entity.*;
import edu.chl.Game.graphics.SpriteSheet;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.view.GameThread;

public class DerangedBeast extends EnemyUnit {

	public DerangedBeast(int x, int y, int width, int height, boolean solid,
			Id id, GameHandler handler, OpponentUnitProperties op, FrameValues frameValues, SpriteSheet spriteSheet_walking) {
		super(x, y, width, height, solid, id, handler, op, frameValues, spriteSheet_walking);
	}

}
