package edu.chl.Game.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.enemy.DerangedBeast;
import edu.chl.Game.model.gameobject.entity.enemy.RoaringBrute;
import edu.chl.Game.model.gameobject.entity.player.GameCursor;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.tile.FloorTile;
import edu.chl.Game.model.gameobject.tile.Tile;

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

				
				//	( black )
				if (red == 0 && green == 0 && blue == 0) {
					tileList.add(new FloorTile(x*64, y*64, 64, 64, true, Id.wall, handler));
				}
				
				//	( green )
				else if (red == 0 && green == 255 && blue == 0) {
					entityList.add(new RoaringBrute(x * 64, y * 64, 120, 115, true,
							Id.monster, handler, handler.getOp_rb(), handler.getFrameValues(), handler.getSheetRoaringBrute()));
				}
				
				//	( blue )
				else if (red == 0 && green == 0 && blue == 255) {
					entityList.add(new Player(x*64, y*64, 62, 62, true, Id.player, handler));
				}
				
				
				//	( red )
				else if (red == 255 && green == 0 && blue == 0) {
					entityList.add(new DerangedBeast(x * 64, y * 64, 64, 64, true,
							Id.monster, handler, handler.getOp_db(), handler.getFrameValues(), handler.getSheetDerangedBeast()));
				}
				
				//	( ??? )
				else if (red == 022 && green == 0 && blue == 255) {
					System.out.println("<check_4>");
					entityList.add(new RoaringBrute(x * 120, y * 115, 120, 115, true,
							Id.monster, handler, handler.getOp_rb(), handler.getFrameValues(), handler.getSheetRoaringBrute()));
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
