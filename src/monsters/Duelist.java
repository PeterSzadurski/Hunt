package monsters;

import model.Armor;
import model.Game;
import model.Monster;
import model.Weapon;
import model.Item;
import model.Monster;
import model.Weapon;
import model.Items;


public class Duelist  extends Monster{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String name = "Duelist";

	private static Item[] loot = {Items.smallPotion, Items.smallPoison, Items.smallPotion, Items.ironLongblade, Items.lightLeather};

	private static int expOnKill = 10;
	private static int str = 2;
	private static int agi = 3;
	private static int vit = 1;



	private static Weapon weapon = Items.ironLongblade;
	private static Armor armor = Items.lightLeather;
	private static char icon = 'D';
	private static String color ="#ffffff";





	public Duelist(int x, int y, int floor) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y, floor);
		
		//this.name = name;
		super.setLoot(loot);
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
