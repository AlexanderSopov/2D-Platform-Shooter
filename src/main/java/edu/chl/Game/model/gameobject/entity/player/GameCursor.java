package edu.chl.Game.model.gameobject.entity.player;

import java.awt.Color;
import java.awt.Graphics;

import edu.chl.Game.model.gameobject.*;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.MouseInput;
import edu.chl.Game.view.Frame;
import edu.chl.Game.view.*;

import java.awt.MouseInfo;
import java.util.LinkedList;

public class GameCursor extends Entity {

	private State state;
	private Camera cam;
	private int counter;
	
        Pistol p;

	

	private enum State {
		AIM, DEFULT, RELODING;
	}

	public GameCursor(Camera cam, GameHandler handler) {

		super(MouseInfo.getPointerInfo().getLocation().x, MouseInfo
				.getPointerInfo().getLocation().y, 0, 0, false, Id.cursor,
				handler);
		this.state = State.AIM;
		this.cam = cam;
		this.counter = 0;
                
               // p = new Pistol(en.getX(), en.getY(), en.getWidth(),en.getHeight(), false, null, handler, this,en);
                
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
                //p.render(g);
		
	}

	@Override
	public void update() {
                if(cam != null){
		setX(MouseInput.getMousePosX() - cam.getX());
		setY(MouseInput.getMousePosY() - cam.getY());
                }
		//p.update();

	}

	public void shoot() {
		//p.shoot();
	}
	
	public Pistol getPistol(){
		return p;
	}



}
