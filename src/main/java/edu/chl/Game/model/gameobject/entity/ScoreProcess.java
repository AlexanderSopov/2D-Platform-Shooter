package edu.chl.Game.model.gameobject.entity;

import java.awt.Graphics;
import java.util.ArrayList;

import edu.chl.Game.view.graphics.ScoreInterface;

public class ScoreProcess {
	
	private ArrayList<ScoreInterface> scoreList;
	
	public ScoreProcess(){
		scoreList = new ArrayList<ScoreInterface>();
	}
	
	public void addScoreInterface(Entity en, double value) {
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
