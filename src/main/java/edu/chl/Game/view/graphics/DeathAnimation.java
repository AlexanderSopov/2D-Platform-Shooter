package edu.chl.Game.view.graphics;
import java.awt.Graphics;

import edu.chl.Game.model.gameobject.entity.FacingDirection;
import edu.chl.Game.model.gameobject.entity.entityTools.FrameIterator;

public class DeathAnimation {
	
	private SpriteSheet sheetDeathAnimation;
	private Sprite[] arrayDeathAnimation;
	private LoadingSprites load;
	private FrameIterator fi;
	private EntityRender er;
	
	public DeathAnimation(){
		
		this.fi = new FrameIterator(3, 5);
		this.er = new EntityRender();
		this.load = new LoadingSprites();
		this.arrayDeathAnimation = new Sprite[10];
		this.sheetDeathAnimation = new SpriteSheet("/infectedstudent/isd00.png");
		this.arrayDeathAnimation = load.loadSprites(sheetDeathAnimation, arrayDeathAnimation, 10, 145, 110);
		
	}
	
	public void renderDeath(Graphics g, int x, int y, FacingDirection fd){
		try {

			er.render(g, arrayDeathAnimation, fi.getFrame(), x, y, 145, 110, fd, 0, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrameIterator getFrameIterator(){
		return fi;
	}

}
