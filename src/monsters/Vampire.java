package monsters;

import model.Armor;
import model.Game;
import model.Item;
import model.Monster;
import model.Weapon;

import model.Items;


public class Vampire  extends Monster{

	
	  
	private static Item[] loot = {Items.scrollFireball, Items.steelLongblade, Items.scrollTeleportation,  Items.ironPlate,Items.scrollFireball, Items.scrollFireball, Items.scrollFrozenTime};

	private static final long serialVersionUID = 1L;
	private static String name = "Vampire";
	private static int expOnKill = 25;
	private static int str = 1;
	private static int agi = 4;
	private static int vit = 6;

	private static Weapon weapon = Items.steelLongblade;

	private static Armor armor = Items.steelPlate;
	private static char icon = 'V';
	private static String color ="#ff0000";




	public Vampire(int x, int y, int floor) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y, floor);


		//this.name = name;
		super.setLoot(loot);
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
