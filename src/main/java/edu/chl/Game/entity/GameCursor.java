package edu.chl.Game.entity;

import java.awt.Color;
import java.awt.Graphics;

import edu.chl.Game.object.*;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.handler.MouseInput;
import edu.chl.Game.view.Frame;
import java.awt.MouseInfo;
import java.util.LinkedList;

public class GameCursor extends Entity {

	private State state;
	private Entity en;
	private int counter;
        Pistol p;

	

	private enum State {
		AIM, DEFULT, RELODING;
	}

	public GameCursor(Entity en, GameHandler handler) {

		super(MouseInfo.getPointerInfo().getLocation().x, MouseInfo
				.getPointerInfo().getLocation().y, 0, 0, false, Id.cursor,
				handler);
		this.state = State.AIM;
		this.en = en;
		this.counter = 0;
                
                p = new Pistol(en.getX(), en.getY(), en.getWidth(),en.getHeight(), false, null, handler, this,en);
                
	}

	@Override
	public void render(Graphics g) {
		switch (this.state) {
		case AIM:

			g.setColor(Color.red);
			g.drawOval(getX() - 15, getY() - 15, 30, 30);
			g.fillRect(getX(), getY(), 1, 1);
                        

			break;

		}
                p.render(g);
		
	}

	@Override
	public void update() {

		setX(MouseInput.getMousePosX() - (-en.getX() + Frame.WIDTH / 2));
		setY(MouseInput.getMousePosY() - (-en.getY() + Frame.HEIGHT / 2 + 100));

		p.update();

	}

	public void shoot() {
		p.shoot();
	}



}
