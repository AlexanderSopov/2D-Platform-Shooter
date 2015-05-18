package edu.chl.Game.view.graphics;

import java.awt.Graphics;
import java.util.ArrayList;

import edu.chl.Game.model.gameobject.entity.Entity;

public class ScoreProcess {
	
	private ArrayList<ScoreInterface> scoreList;
	
	public ScoreProcess(){
		scoreList = new ArrayList<ScoreInterface>();
	}
	
	public void addScoreInterface(Entity en, int value) {
		scoreList.add(new ScoreInterface(en, value));
	}
	
	public void updateScoreDispay(Graphics g) {
		for (int i=0; i < scoreList.size(); i++) {
			ScoreInterface si = scoreList.get(i);
			si.updateSettings(g);
			isScoreComplete(si);
		}
	}
	
	public void isScoreComplete(ScoreInterface si){
		if( !( si.getFrameIterator().isActive() ) ){
			removeScoreInterface(si);
		}
	}
	
	public void removeScoreInterface(ScoreInterface si){
		scoreList.remove(si);
	}
	
	public boolean listIsEmpty(){
		return (scoreList.size()==0);
	}


}
