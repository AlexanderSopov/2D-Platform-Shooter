package edu.chl.Game.model.gameobject.entity.player;

import java.awt.Color;
import java.awt.Graphics;

import edu.chl.Game.model.gameobject.*;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.MouseInput;
import edu.chl.Game.view.*;

import java.awt.MouseInfo;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class GameCursor extends Entity {

	private CursorState state;
	private Camera cam;
	private int counter;
	private BufferedImage image;
	
        Pistol p;

	

	public enum CursorState {
		AIM, DEFULT, RELODING;
	}

	public GameCursor(Camera cam, GameHandler handler) {

		super(MouseInfo.getPointerInfo().getLocation().x, MouseInfo
				.getPointerInfo().getLocation().y, 0, 0, false, Id.cursor,
				handler);
		this.state = CursorState.DEFULT;
		this.cam = cam;
		this.counter = 0;
		
		try {
			image = ImageIO.read(getClass().getResource("/cursor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
                
              
                
	}

	@Override
	public void render(Graphics g) {
		switch (this.state) {
		case AIM:

			g.setColor(Color.red);
			g.drawOval(getX() - 15, getY() - 15, 30, 30);
			g.fillRect(getX(), getY(), 1, 1);
			break;
		case DEFULT:
			
			g.drawImage(image, getX()-image.getWidth()/40, getY(), image.getWidth()/10, image.getHeight()/10, null);
			
			break;

		}
                
		
	}

	@Override
	public void update() {
                if(cam != null){
		setX(MouseInput.getMousePosX() - cam.getX());
		setY(MouseInput.getMousePosY() - cam.getY());
                }
		//p.update();

	}
	
	public void changeState(CursorState state){
		this.state = state;
	}



}
