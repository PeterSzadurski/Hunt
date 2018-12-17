package monsters;

import model.Armor;
import model.Game;
import model.Monster;
import model.Weapon;
import model.Item;
import model.Monster;
import model.Weapon;
import model.Items;


public class Ninja  extends Monster{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String name = "Ninja";

	private static Item[] loot = {Items.smallPotion, Items.smallPoison, Items.smallPotion, Items.ironLongblade, Items.ironLongblade, Items.scrollGreaterFrozenTime,};

	private static int expOnKill = 12;
	private static int str = 1;
	private static int agi = 4;
	private static int vit = 1;



	private static Weapon weapon = Items.ironLongblade;
	private static Armor armor = Items.nothingArm;
	private static char icon = 'N';
	private static String color ="##C0C0C0";





	public Ninja(int x, int y, int floor) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y, floor);
		
		//this.name = name;
		super.setLoot(loot);
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
