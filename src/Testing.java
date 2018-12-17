import java.util.ArrayList;
import java.util.Arrays;

import dao.DungeonDAO;
import dao.MonsterDAO;
import model.Armor;
import model.Dungeon;
import model.Monster;
import model.Tile;
import model.Weapon;
import monsters.Bat;

public class Testing {

	public static void main(String[] args) {
		
		DungeonDAO dao = new DungeonDAO();
		
		Tile path = new Tile('#', false, "#878787", "path");
		Tile floor = new Tile('.', false, "#878787", "floor");
		Tile wall = new Tile(' ',true, "#878787", "wall");
		Tile upFloor = new Tile('%',false, "#009900", "upFloor");//green
		Tile downFloor = new Tile('%',false, "#000099", "downFloor");//blue
		
		Tile layout[][] = {
				{wall, floor, floor, floor, floor, wall, floor, floor, floor, wall},
				{downFloor, floor, floor, floor, wall, wall, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
				{floor, floor, wall, wall, wall, floor, floor, floor, floor, floor},
				{floor, floor, wall, floor, path, floor, floor, floor, floor, floor},
				{floor, floor, wall, floor, wall, floor, floor, floor, floor, upFloor}
		};
		
		// arraylist to hold rows of 2d array as strings
		ArrayList<String> dLayout = new ArrayList<String>();
		
//		for(int i = 0; i < layout.length; i++) {
//			String row = "";
//			for(int j = 0; j < layout[i].length; j++) {
//				if(j == layout[i].length - 1) {
//					row += layout[i][j].toString();
//				} else {
//					row += layout[i][j].toString() + ",";
//				}
//			}
//			dLayout.add(row);
//		}
//		
//		Dungeon d = new Dungeon(dLayout);
		
		//ArrayList<String> d = dao.getDungeonByLevel(0);
		
		//Dungeon dung = new Dungeon(d, true);
		
		MonsterDAO mDao = new MonsterDAO();
		Bat bat = new Bat(0, 0, 0);
		mDao.addMonster(bat);
		
	}

}
