package edu.chl.Game.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.chl.Game.model.sound.Sound;
import edu.chl.Game.view.screens.AbstractMenuScreen;
import edu.chl.Game.view.screens.OptionView;

public class MouseInputGDX extends ClickListener{
	
	private AbstractMenuScreen menuScreen; 
	
	public MouseInputGDX(AbstractMenuScreen menuScreen){
		this.menuScreen = menuScreen;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y){
		CharSequence cs = ((TextButton)event.getListenerActor()).getText();
		String e = cs.toString();
				
		switch(e){
		case "Sound":
			((OptionView)menuScreen).getTableSound().setVisible(true);
			((OptionView)menuScreen).getTableGraphic().setVisible(false);
			break;
		case "Graphic":
			((OptionView)menuScreen).getTableSound().setVisible(false);
			((OptionView)menuScreen).getTableGraphic().setVisible(true);
			break;
		case "On":
			System.out.println("Sound: On");
			break;
		case "Off":
			System.out.println("Sound: off");
			break;
		case "+":
			Sound.increaseGlobalVol();
			break;
		case "-":
			Sound.decreaseGlobalVol();
			break;
		}
	}
	
}
