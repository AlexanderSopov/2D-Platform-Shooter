package edu.chl.Game.view.screens;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.chl.Game.controller.MouseInputGDX;

/**
 * Abstract class for standard Screens
 * @author Martin Tran
 *
 */

public abstract class AbstractMenuScreen implements Screen {
	
	protected Stage stage;
	protected Skin skin;
	protected Table table;
	protected TweenManager tweenManager;
	
	protected Animator animation;
	protected SpriteBatch spriteBatch;
	protected MouseInputGDX mouseInput;

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		//Clear the screen with the given color, in this case Black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
		table.invalidateHierarchy();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), new TextureAtlas("ui/atlas.pack"));
		
		table = new Table(skin);
		table.setFillParent(true);
		
		//Sprite & animations
		spriteBatch = new SpriteBatch();
		animation = new Animator(spriteBatch);
		
		mouseInput = new MouseInputGDX(this);
	}

}
