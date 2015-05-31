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
	private TextureRegion[] spriteImages;
	private TextureRegion currentFrame;
	
	float frameSpeed, stateTime;
	
	/**
	 * Constructor for Animator
	 * @param spriteBatch Used to store images
	 */
	public Animator(SpriteBatch spriteBatch){			
		this.spriteBatch = spriteBatch;
	}
	
	/**
	 * Sets the SpriteSheets images into the SpriteBatch 
	 * @param path Filepath URL
	 * @param collumWidth With of one image
	 * @param collumHeight Height of one image 
	 * @param rows number of images on the rowns in SpriteSheet
	 * @param cols number of images collums in the SpriteSheet
	 * @param frameSpeed The speed of how fast the animation will iterate through the images
	 */
	public void setSprite(String path, int collumWidth, int collumHeight, int rows, int cols, float frameSpeed){
		spriteSheet = new Texture(Gdx.files.internal(path));
		this.frameSpeed = frameSpeed;
		
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, collumWidth, collumHeight);
		spriteImages = new TextureRegion[rows*cols];
		int index = 0;
		for(int i = 0; i < rows; i++ ){
			for(int k = 0; k < cols; k++){
				spriteImages[index++] = tmp[i][k];
			}
		}
		
		animation = new Animation(frameSpeed, spriteImages);
	}
	
	/**
	 * Draws the animation on the screen
	 */
	public void renderAnimation(){
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = animation.getKeyFrame(stateTime, true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, Frame.WIDTH - Frame.WIDTH/3, Frame.HEIGHT/3, 150, 150);
		spriteBatch.end();
	}
	
	/**
	 * Returns the animation with its speed and Sprite Images
	 * @return
	 */
	public Animation getAnimation() {
		return animation;
	}
	
	/**
	 * The SpriteSheet that contains all the Sprite Images
	 * @return spriteSheet
	 */
	public Texture getSpriteSheet() {
		return spriteSheet;
	}
	
	/**
	 * The texture region that has all the Sprites in an array
	 * @return spriteImages
	 */
	public TextureRegion[] getSpriteImages() {
		return spriteImages;
	}
	
	/**
	 * Get the current Sprite image texture region
	 * @return currentFrame
	 */
	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}
	
	/**
	 * Get the speed on how fast the Sprite Images iterates
	 * @return frameSpeed
	 */
	public float getFrameSpeed() {
		return frameSpeed;
	}

}
