package edu.chl.Game.controller;

import java.awt.Rectangle;
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
			if(RefreshTimer.state == State.MAP && !mapView.ifMoving()){		
				if(ifClicked(mapView.mapLevels[0], e)){
					mapView.setIsMoving();
					mapView.setPos(0);
					setLevel(e, 0);
				}else if(ifClicked(mapView.mapLevels[1], e)){
					mapView.setIsMoving();
					mapView.setPos(1);
					setLevel(e, 1);
				}else if(ifClicked(mapView.mapLevels[2], e)){
					mapView.setIsMoving();
					mapView.setPos(2);
					setLevel(e, 2);
				}else if(ifClicked(mapView.mapLevels[3], e)){
					mapView.setIsMoving();
					mapView.setPos(3);
					setLevel(e, 3);
				}else if(ifClicked(mapView.mapLevels[4], e)){
					mapView.setIsMoving();
					mapView.setPos(4);
					setLevel(e, 4);
				}else if(ifClicked(mapView.shopButton, e)){
					WorldMapView.mapState = State.MAP_SHOP;
				}else if(ifClicked(mapView.characterButton, e)){
					WorldMapView.mapState = State.MAP_CHAR;
				}
				
				if(WorldMapView.mapState == State.MAP_SHOP){
					System.out.println("Shop");
				}else if(WorldMapView.mapState == State.MAP_CHAR){
					System.out.println("Character");
				}
				
				if(ifClicked(mapView.menuCloseButton, e)){
					WorldMapView.mapState = null;
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
	
	private boolean ifClicked(Rectangle r, MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		return mx > r.getX() && mx < r.getMaxX() && my > r.getY() && my < r.getMaxY();
	}

}
