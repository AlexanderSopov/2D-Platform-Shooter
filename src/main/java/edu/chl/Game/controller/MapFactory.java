package edu.chl.Game.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.enemy.*;
import edu.chl.Game.model.gameobject.entity.items.Item;
import edu.chl.Game.model.gameobject.entity.items.W1;
import edu.chl.Game.model.gameobject.entity.player.GameCursor;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.tile.Tile;
import edu.chl.Game.model.gameobject.tile.TileA;
import edu.chl.Game.model.gameobject.tile.TileB;
import edu.chl.Game.model.gameobject.tile.TileC;
import edu.chl.Game.model.gameobject.tile.TileD;
import edu.chl.Game.model.gameobject.tile.TileE;

/**
 * 
 * @author Marre
 *
 */

public class MapFactory {
	public static BufferedImage mapImage;
	
	public static void createMap(GameHandler handler, GameCursor c, LinkedList<Entity> entityList, LinkedList<Tile> tileList, LinkedList<Item> itemList) {
		try {
			mapImage = ImageIO.read(handler.getClass().getResource(selectMap()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		int width = mapImage.getWidth();
		int height = mapImage.getHeight();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = mapImage.getRGB(x, y);

				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				
				/*
				 *		Units
				 */
				
                //	( blue )
				if (red == 0 && green == 0 && blue == 255) {
					
					entityList.add(new Player(x*64, y*64, 62, 62, true, Id.player, handler));
					
				}    
				// ( green )
				if (red == 0 && green == 255 && blue == 0) {
					entityList.add(new InfectedStudent(x * 64, y * 60, 97, 90, true,
							Id.monster, handler));
				}
				// ( purple )
				if (red == 150 && green == 0 && blue == 150) {
					entityList.add(new OvergrownMonstrocity(x * 64, y * 60, 200, 200, true,
							Id.monster, handler));
					
				}
				
				/*
				 *		Tiles 
				 */

				// ( black )
				if (red == 0 && green == 0 && blue == 0) {
					tileList.add(new TileA(x * 64, y * 64, true, Id.wall, handler));
				}

				// ( teal )
				if (red == 0 && green == 255 && blue == 255) {
					tileList.add(new TileB(x * 64, y * 64, true, Id.wall, handler));
				}
                                
                                        
				// ( pink )
				if (red == 255 && green == 0 && blue == 255) {
					tileList.add(new TileC(x * 64, y * 64, true, Id.wall, handler));

				}

				// ( yellow )
				if (red == 255 && green == 255 && blue == 0) {
					
					tileList.add(new TileD(x * 64, y * 64, true, Id.wall, handler));
				}

				// ( brown )
				if (red == 125 && green == 125 && blue == 0) {
					tileList.add(new TileE(x * 64, y * 64, true, Id.wall, handler));
				}
				
				
				/*
				 *		Items
				 */
				

				// ( purple )
				if (red == 150 && green == 0 && blue == 150) {
					
					itemList.add(new W1((x-5) * 64,  (y+1) * 60, 64,64,null,handler));
				}
				
	
				
			}
		}
	}
	
	private static String selectMap(){
		switch(RefreshTimer.selectedMap){
			case "level_1":
				return "/mapLevel/level_1.png";
			case "level_2":
				return "/mapLevel/level_2.png";
			case "level_3":
				return "/mapLevel/level_3.png";
			case "level_4":
				return "/mapLevel/level_4.png";
			case "level_5":
				return "/mapLevel/level_5.png";
			default:
				return null;
		}
	}
}
