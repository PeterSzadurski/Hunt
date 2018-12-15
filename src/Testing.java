import java.util.ArrayList;
import java.util.Arrays;

import dao.DungeonDAO;
import model.Dungeon;
import model.Tile;

public class Testing {

	public static void main(String[] args) {
		
		DungeonDAO dao = new DungeonDAO();
		
		Tile wall = new Tile('#', true, "#878787", "wall");
		Tile floor = new Tile('.', false, "#878787", "floor");
		Tile layout[][] = {
				{wall, floor, floor, floor, floor, wall, floor, floor, floor, wall},
				{floor, floor, floor, floor, wall, wall, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
				{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor}
		};
		
		// arraylist to hold rows of 2d array as strings
		ArrayList<String> dLayout = new ArrayList<String>();
		
		for(int i = 0; i < layout.length; i++) {
			String row = "";
			for(int j = 0; j < layout[i].length; j++) {
				if(j == layout[i].length - 1) {
					row += layout[i][j].toString();
				} else {
					row += layout[i][j].toString() + ",";
				}
			}
			dLayout.add(row);
		}
		
		Dungeon dungeon = new Dungeon(dLayout);
		
		dao.addDungeon(dLayout, 1);
		
	}

}
