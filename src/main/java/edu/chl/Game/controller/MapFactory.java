package edu.chl.Game.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.enemy.InfectedStudent;
import edu.chl.Game.model.gameobject.entity.enemy.RoaringBrute;
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
	
	public static void createMap(GameHandler handler, GameCursor c, LinkedList<Entity> entityList, LinkedList<Tile> tileList) {
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

				
				// ( green )
				if (red == 0 && green == 255 && blue == 0) {
					entityList.add(new InfectedStudent(x * 64, y * 60, 97, 90, true,
							Id.monster, handler));
				}

				// ( black )
				if (red == 0 && green == 0 && blue == 0) {
					tileList.add(new TileA(x * 64, y * 64, true, Id.wall, handler));
				}

				// ( teal )
				if (red == 0 && green == 255 && blue == 255) {
					tileList.add(new TileB(x * 64, y * 64, true, Id.wall, handler));
				}
                                
                //	( blue )
				else if (red == 0 && green == 0 && blue == 255) {
					
					entityList.add(new Player(x*64, y*64, 62, 62, true, Id.player, handler));
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
			}
		}
	}
	
	private static String selectMap(){
		switch(RefreshTimer.selectedMap){
			case "level_1":
				System.out.println("hej");
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
