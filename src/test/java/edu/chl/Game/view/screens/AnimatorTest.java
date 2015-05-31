package edu.chl.Game.view.screens;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnimatorTest {
	
	Animator a;
	
	@Before
	public void AnimatorTestInit(){
		a = new Animator(new SpriteBatch());
	}

	@Test
	public void AnimatorTestA() {
		a.setSprite("img/cogwheel.png", 200, 200, 1, 10, 0.2f);
	}

}
