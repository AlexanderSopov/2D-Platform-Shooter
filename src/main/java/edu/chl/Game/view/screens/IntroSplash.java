package edu.chl.Game.view.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.chl.Game.view.screens.tween.SpriteAccessor;

/**
 * LibGDX
 * Splash animation at Intro
 * @author Martin Tran
 *
 */
public class IntroSplash implements Screen {
	
	private SpriteBatch batch;
	private Sprite splash;
	private TweenManager tweenManager;

	@Override
	public void dispose() {
		batch.dispose();
		splash.getTexture().dispose();
	}

	@Override
	public void hide() {
		dispose();	
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void render(float delta) {
		//Clear the screen with the given color, in this case Black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Starts the drawing of the splash
		batch.begin();
		splash.draw(batch);
		batch.end();
		
		tweenManager.update(delta);
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		
		//Sets the pictrue for the splashscreen
		splash = new Sprite(new Texture(Gdx.files.internal("img/splash1.png")));
		
		//Makes an animation, after it's done it goes to the next Screen
		Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(splash,  SpriteAccessor.ALPHA, 1.5f).target(1).repeatYoyo(1, .5f).setCallback(new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		}).start(tweenManager);
		
		tweenManager.update(Float.MIN_VALUE);	
	}

}
