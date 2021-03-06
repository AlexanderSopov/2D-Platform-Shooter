package edu.chl.Game.view.screens.tween;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * A standard SpriteBatch Accessor
 * @author Martin Tran
 *
 */
public class SpriteBatchAccessor implements TweenAccessor<SpriteBatch> {

	public static final int ALPHA = 0;

	@Override
	public int getValues(SpriteBatch target, int tweenType, float[] returnValues) {
		switch(tweenType){
			case ALPHA:
				returnValues[0] = target.getColor().a;
				return 1;
			default:
				assert false;
				return -1;
		}
	}

	@Override
	public void setValues(SpriteBatch target, int tweenType, float[] newValues) {
		switch(tweenType){
			case ALPHA:
				target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]);
				break;
			default:
				assert false;
		}
		
	}

}
