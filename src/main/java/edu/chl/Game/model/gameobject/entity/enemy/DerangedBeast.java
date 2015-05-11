package edu.chl.Game.model.gameobject.entity.enemy;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import edu.chl.Game.UnitTools.*;
import edu.chl.Game.Main;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.GameThread;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.view.graphics.SpriteSheet;

public class DerangedBeast extends EnemyUnit {

	public DerangedBeast(int x, int y, int width, int height, boolean solid,
			Id id, GameHandler handler, OpponentUnitProperties op, FrameValues frameValues, SpriteSheet spriteSheet_walking) {
		super(x, y, width, height, solid, id, handler, op, frameValues, spriteSheet_walking);
	}

}
