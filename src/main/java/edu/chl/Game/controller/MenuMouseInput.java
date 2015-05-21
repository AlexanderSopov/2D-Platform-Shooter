package edu.chl.Game.controller;

import java.awt.event.MouseEvent;

import edu.chl.Game.view.Frame;
import edu.chl.Game.view.WorldMapView;

public class MenuMouseInput extends MouseInput{
		private WorldMapView mapView;
		private RefreshTimer refreshTimer;
		
	public MenuMouseInput(RefreshTimer refreshTimer,WorldMapView mapView, GameHandler handler) {
		super(handler);
		
		this.mapView = mapView;
		this.refreshTimer = refreshTimer;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
			if(RefreshTimer.state == State.MAP){
			int mx = e.getX();
			int my = e.getY();
			
			if(mx > mapView.mapLevels[0].getX() && mx < mapView.mapLevels[0].getMaxX() && my > mapView.mapLevels[0].getY() && my < mapView.mapLevels[0].getMaxY()){
				mapView.setIsMoving();
				mapView.setPos(0);
				setLevel(e, 0);
			}else if(mx > mapView.mapLevels[1].getX() && mx < mapView.mapLevels[1].getMaxX() && my > mapView.mapLevels[1].getY() && my < mapView.mapLevels[1].getMaxY()){
				mapView.setIsMoving();
				mapView.setPos(1);
				setLevel(e, 1);
			}else if(mx > mapView.mapLevels[2].getX() && mx < mapView.mapLevels[2].getMaxX() && my > mapView.mapLevels[2].getY() && my < mapView.mapLevels[2].getMaxY()){
				mapView.setIsMoving();
				mapView.setPos(2);
				setLevel(e, 2);
			}else if(mx > mapView.mapLevels[3].getX() && mx < mapView.mapLevels[3].getMaxX() && my > mapView.mapLevels[3].getY() && my < mapView.mapLevels[3].getMaxY()){
				mapView.setIsMoving();
				mapView.setPos(3);
				setLevel(e, 3);
			}else if(mx > mapView.mapLevels[4].getX() && mx < mapView.mapLevels[4].getMaxX() && my > mapView.mapLevels[4].getY() && my < mapView.mapLevels[4].getMaxY()){
				mapView.setIsMoving();
				mapView.setPos(4);
				setLevel(e, 4);
			}else if(mx > mapView.shop.getX() && mx < mapView.shop.getMaxX() && my > mapView.shop.getY() && my < mapView.shop.getMaxY()){
				System.out.println("Shop");
			}else if(mx > mapView.character.getX() && mx < mapView.character.getMaxX() && my > mapView.character.getY() && my < mapView.character.getMaxY()){
				System.out.println("Character");
			}
		}
	}
	
	private void setLevel(MouseEvent e, int i){
		if(e.getClickCount() == 2 && !e.isConsumed()){
			e.consume();
			System.out.println(RefreshTimer.levels[i]);
			RefreshTimer.selectedMap = RefreshTimer.levels[i];
			refreshTimer.changeGameState(State.GAME);
		}
	}

}
