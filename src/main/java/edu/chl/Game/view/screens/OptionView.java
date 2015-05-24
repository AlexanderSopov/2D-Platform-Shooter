package edu.chl.Game.view.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.chl.Game.view.Frame;
import edu.chl.Game.view.screens.tween.ActorAccessor;


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
		
		//GraphicButton
		TextButton buttonGraphic = new TextButton("Graphic", skin);
		buttonGraphic.addListener(new ClickListener());
		buttonGraphic.pad(10);
		
		//SoundButton
		TextButton buttonSound = new TextButton("Sound", skin);
		buttonSound.addListener(new ClickListener());
		buttonSound.pad(10);
		
		//BackButton
		TextButton buttonBack = new TextButton("Back", skin);
		buttonBack.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				//Animation: Moves up
				stage.addAction(sequence(moveTo(0, stage.getHeight() -50, .5f), run(new Runnable(){
					@Override
					public void run() {
						((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
					}	
				})));
			}
		});;
		buttonBack.pad(10);
	
		table.add(title).padTop(-Frame.HEIGHT/6).spaceBottom(50).row();
		table.add(buttonGraphic).width(180).spaceBottom(15).row();
		table.add(buttonSound).width(180).spaceBottom(15).row();
		table.add(buttonBack).width(180);
		
		stage.addActor(table);
		
		//Animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class,  new ActorAccessor());
		
		//table fade-in
		Tween.from(table, ActorAccessor.ALPHA, .75f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight()/8).start(tweenManager);
		
		tweenManager.update(Gdx.graphics.getDeltaTime());
	}
}
