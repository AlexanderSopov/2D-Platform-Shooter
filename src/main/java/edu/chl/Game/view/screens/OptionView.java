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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.chl.Game.model.sound.Sound;
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
	
	private Table tableGraphics;
	private Table tableSound;
	
	private Animator animationGraphics;
	private SpriteBatch spriteBatchGraphics;
	
	@Override
	public void show() {
		super.show();
		showSubmenu();
		
		//Set Title
		Label title = new Label("Options", skin);
		title.setFontScale(2);
		
		//Set up Sprite cogwheel. Spritesheet 62x62: 1 row and 20 cols
		animation.setSprite("img/cogwheel.png", 200, 200, 1, 10, 0.2f);
		
		//Sprite & animations for submenu Graphics. Spritesheet 225x182: 1 row and 3 cols
		spriteBatchGraphics = new SpriteBatch();
		animationGraphics = new Animator(spriteBatchGraphics);
		animationGraphics.setSprite("img/GraphicSpriteSheet.png", 225, 182, 1, 3, 2f);
		
		//GraphicButton
		TextButton buttonGraphic = new TextButton("Graphic", skin);
		buttonGraphic.addListener(mouseInput);
		buttonGraphic.pad(10);
		
		//SoundButton
		TextButton buttonSound = new TextButton("Sound", skin);
		buttonSound.addListener(mouseInput);
		buttonSound.pad(10);
		
		//BackButton
		TextButton buttonBack = new TextButton("Back", skin);
		buttonBack.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				//Animation: Moves up
				Timeline.createParallel().beginParallel()
				.push(Tween.to(spriteBatch, SpriteBatchAccessor.ALPHA, .50f).target(0))
				.push(Tween.to(spriteBatchGraphics, SpriteBatchAccessor.ALPHA, .50f).target(0))
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
		table.add(buttonGraphic).padRight(Frame.WIDTH/3).width(200).spaceBottom(15).row();
		table.add(buttonSound).padRight(Frame.WIDTH/3).width(200).spaceBottom(15).row();
		table.add(buttonBack).padRight(Frame.WIDTH/3).width(200);
		
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
	
	//Set the table for the submenus
	private void showSubmenu(){
		tableGraphics = new Table(skin);
		tableSound = new Table(skin);
		tableGraphics.setFillParent(true);
		tableSound.setFillParent(true);
		
		//Buttons for submenu Graphics
		TextButton buttonLowGraphic = new TextButton("Low", skin);
		buttonLowGraphic.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				System.out.println("Low Graphics");
				resize(1000, 600);
			}
		});
		buttonLowGraphic.pad(10);
		
		TextButton buttonHighGraphic = new TextButton("High", skin);
		buttonHighGraphic.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				System.out.println("High Graphics");
				resize(1100, 700);
			}
		});
		buttonHighGraphic.pad(10);
		
		//Buttons and Labels for submenu Sound
		Label soundLabel = new Label("Sound:", skin);
		Label soundStatusLabel = new Label(Integer.toString(Sound.getCurrentVolume()), skin);
		
		TextButton soundIncrease = new TextButton("+", skin);
		soundIncrease.addListener(mouseInput);
		soundIncrease.pad(10);
		
		TextButton soundDecrease = new TextButton("-", skin);
		soundDecrease.addListener(mouseInput);
		soundDecrease.pad(10);
		
		TextButton soundOn = new TextButton("On", skin);
		soundOn.addListener(mouseInput);
		soundOn.pad(10);

		TextButton soundOff = new TextButton("Off", skin);
		soundOff.addListener(mouseInput);
		soundOff.pad(10);
		
		// Set-up and Adds buttons to the Graphic table.
		tableGraphics.add(buttonLowGraphic).padTop(250).padLeft(480).width(120);
		tableGraphics.add(buttonHighGraphic).padTop(250).width(120);
		tableGraphics.setVisible(false);
		stage.addActor(tableGraphics);
		
		// Set-up and Adds buttons to the Sound table.
		tableSound.add(soundLabel).padTop(150).padLeft(400);
		tableSound.add(soundStatusLabel).padTop(150);
		tableSound.add(soundDecrease).padTop(150);
		tableSound.add(soundIncrease).padTop(150).row();
		tableSound.add(soundOn).padTop(75).padLeft(480).width(70);
		tableSound.add(soundOff).padTop(75).width(70);
		tableSound.setVisible(false);
		stage.addActor(tableSound);
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		
		if(tableGraphics.isVisible()){
			animationGraphics.renderAnimation();
		}else if(!tableGraphics.isVisible() && !tableSound.isVisible()){
			animation.renderAnimation();
		}
		
		tweenManager.update(delta);
	}
	
	/**
	 * Get the table of Graphic option
	 * @return Table: Graphic
	 */
	public Table getTableGraphic(){
		return tableGraphics;
	}
	
	/**
	 * Get the table of Sound options
	 * @return Table: Sound
	 */
	public Table getTableSound(){
		return tableSound;
	}
}
