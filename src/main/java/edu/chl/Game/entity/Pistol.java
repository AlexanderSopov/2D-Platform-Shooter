/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.entity;

import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Rasmus
 */
public class Pistol extends Entity{
    int centerX = getWidth() / 2;
     int  centerY = getHeight() / 2;
     double angle;
     GameCursor gc;
     Entity en;
    
    public Pistol(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler, GameCursor gc, Entity en) {
        super(x, y, width, height, solid, id, handler);
        this.gc = gc;
        this.centerX = x + width/2;
        this.centerY = y + height/2;
        this.en = en;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        ((Graphics2D)g).rotate(angle, centerX, centerY);
        
        g.fillRect(centerX, centerY-5, 50, 10); 
        ((Graphics2D)g).rotate(-angle, centerX, centerY);
    }

    @Override
    public void update() {
        this.centerX = en.getX() + en.getWidth()/2;
        this.centerY = en.getY() + en.getHeight()/2;
        angle = Math.atan2(centerY - gc.getY(), centerX - gc.getX()) - Math.PI ;
    }
    
}
