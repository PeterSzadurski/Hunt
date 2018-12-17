package monsters;

import model.Armor;
import model.Game;

import model.Item;
import model.Items;
import model.Monster;
import model.Weapon;

public class Bat  extends Monster{

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private static  Item[] loot = {Items.smallPoison, Items.smallPotion, Items.smallPoison, Items.smallPotion, Items.smallPoison, Items.smallPotion, Items.largePotion};
	private static String name = "Bat";
	private static int expOnKill = 3;
	private static int str = 1;
	private static int agi = 2;
	private static int vit = 1;
	private static Weapon weapon = Items.nothingWep;
	private static Armor armor = Items.nothingArm;
	private static char icon = 'B';
	private static String color ="#ffffff";

	private static int y;

	public Bat(int x, int y, int floor) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y, floor);

		//this.name = name;
		super.setLoot(loot);


		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
