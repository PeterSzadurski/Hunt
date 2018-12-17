import java.util.ArrayList;
import java.util.Arrays;

import dao.DungeonDAO;
import dao.MonsterDAO;
import model.Dungeon;
import model.Tile;
import monsters.Bat;

public class Testing {

	public static void main(String[] args) {
		
System.out.println("Hey");
System.out.println("test");
MonsterDAO mDao = new MonsterDAO();
Bat bat = new Bat(0, 0, 0);
	mDao.addMonster(bat);
	
	}
}
