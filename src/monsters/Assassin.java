package monsters;

import model.Armor;
import model.Game;
import model.Monster;
import model.Weapon;
import model.Item;
import model.Monster;
import model.Weapon;
import model.Items;


public class Assassin  extends Monster{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String name = "Assassin";

	private static Item[] loot = {Items.smallPoison, Items.smallPoison, Items.smallPoison, Items.scrollFrozenTime, Items.scrollFrozenTime};

	private static int expOnKill = 20;
	private static int str = 3;
	private static int agi = 13;
	private static int vit = 2;



	private static Weapon weapon = Items.steelLongblade;
	private static Armor armor = Items.lightLeather;
	private static char icon = 'A';
	private static String color ="#ffffff";





	public Assassin(int x, int y, int floor) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y, floor);
		
		//this.name = name;
		super.setLoot(loot);
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
