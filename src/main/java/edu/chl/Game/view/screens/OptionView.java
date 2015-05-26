package edu.chl.Game.view.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.chl.Game.view.Frame;
import edu.chl.Game.view.screens.tween.ActorAccessor;
import edu.chl.Game.view.screens.tween.SpriteBatchAccessor;


/**
 * LibGDX
 * Settings and Option menu
 * @author Martin Tran
 *
 */
public class OptionView extends AbstractMenuScreen {
	
	@Override
	public void show() {
		super.show();
		
		//Set Title
		Label title = new Label("Options", skin);
		title.setFontScale(2);
		
		//Set up Sprite cogwheel
		animation.setSprite("option");
		
		//GraphicButton
		TextButton buttonGraphic = new TextButton("Graphic", skin);
		buttonGraphic.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				System.out.println("Graphics");
			}
		});
		buttonGraphic.pad(10);
		
		//SoundButton
		TextButton buttonSound = new TextButton("Sound", skin);
		buttonSound.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				System.out.println("Sound");
			}
		});
		buttonSound.pad(10);
		
		//BackButton
		TextButton buttonBack = new TextButton("Back", skin);
		buttonBack.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				//Animation: Moves up
				Timeline.createParallel().beginParallel()
				.push(Tween.to(spriteBatch, SpriteBatchAccessor.ALPHA, .50f).target(0))
				.end().start(tweenManager);
				stage.addAction(sequence(moveTo(0, stage.getHeight() -50, .5f), run(new Runnable(){
					@Override
					public void run() {
						((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
					}	
				})));
			}
		});;
		buttonBack.pad(10);
		
		//Add and organizes the objects
		table.add(title).padRight(Frame.WIDTH/3).padTop(-Frame.HEIGHT/6).spaceBottom(50).row();
		table.add(buttonGraphic).padRight(Frame.WIDTH/3).width(180).spaceBottom(15).row();
		table.add(buttonSound).padRight(Frame.WIDTH/3).width(180).spaceBottom(15).row();
		table.add(buttonBack).padRight(Frame.WIDTH/3).width(180);
		
		stage.addActor(table);
		
		//Animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class,  new ActorAccessor());
		Tween.registerAccessor(SpriteBatch.class, new SpriteBatchAccessor());
		
		//table fade-in
		Tween.from(table, ActorAccessor.ALPHA, .75f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight()/8).start(tweenManager);
		
		tweenManager.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		animation.renderAnimation();
		tweenManager.update(delta);
	}
}
