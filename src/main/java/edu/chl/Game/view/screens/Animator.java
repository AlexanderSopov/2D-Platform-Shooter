package edu.chl.Game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.chl.Game.view.Frame;

/**
 * Animatior for LibGDX screens
 * @author Marre
 *
 */
public class Animator {
	
	private Animation animation;
	private Texture spriteSheet;
	private SpriteBatch spriteBatch;
	private TextureRegion[] spriteFrames;
	private TextureRegion currentFrame;
	
	private int rows, cols, collumWidth, collumHeight;
	float stateTime;
	
	public Animator(SpriteBatch spriteBatch){	
		//Spritesheet got 1 row and 20 cols
		rows = 1;
		cols = 20;
		//One collom is 62x62 in the spriteSheet
		collumWidth = 62;
		collumHeight = 62;
		
		setSprite();
		this.spriteBatch = spriteBatch;
	}
	
	public void setSprite(){
		spriteSheet = new Texture(Gdx.files.internal("img/SH_Player.png"));
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, collumWidth, collumHeight);
		spriteFrames = new TextureRegion[rows*cols];
		int index = 0;
		for(int i = 0; i < rows; i++ ){
			for(int k = 0; k < cols; k++){
				spriteFrames[index++] = tmp[i][k];
			}
		}
		
		animation = new Animation(0.050f, spriteFrames);
	}
	
	//Draws sprite animation
	public void renderAnimation(){
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = animation.getKeyFrame(stateTime, true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, Frame.WIDTH - Frame.WIDTH/3, Frame.HEIGHT/3, 150, 150);
		spriteBatch.end();
	}
}
