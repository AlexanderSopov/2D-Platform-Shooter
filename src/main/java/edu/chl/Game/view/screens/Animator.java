package edu.chl.Game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.chl.Game.view.Frame;

/**
 * Animatior for LibGDX screens
 * @author Martin Tran
 *
 */
public class Animator {
	
	private Animation animation;
	private Texture spriteSheet;
	private SpriteBatch spriteBatch;
	private TextureRegion[] spriteFrames;
	private TextureRegion currentFrame;
	
	float frameSpeed, stateTime;
	
	public Animator(SpriteBatch spriteBatch){			
		this.spriteBatch = spriteBatch;
	}
	
	public void setSprite(String path, int collumWidth, int collumHeight, int rows, int cols, float frameSpeed){
		spriteSheet = new Texture(Gdx.files.internal(path));
		this.frameSpeed = frameSpeed;
		
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, collumWidth, collumHeight);
		spriteFrames = new TextureRegion[rows*cols];
		int index = 0;
		for(int i = 0; i < rows; i++ ){
			for(int k = 0; k < cols; k++){
				spriteFrames[index++] = tmp[i][k];
			}
		}
		
		animation = new Animation(frameSpeed, spriteFrames);
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
