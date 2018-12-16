package monsters;

import model.Armor;
import model.Game;
import model.Item;
import model.Monster;
import model.Weapon;

import model.Items;


public class Knight  extends Monster{

	
	  
	private static Item[] loot = {Items.smallPotion, Items.ironPlate, Items.largePotion,Items.scrollFireball, Items.ironLongblade, Items.scrollFireball, Items.scrollFrozenTime};

	private static final long serialVersionUID = 1L;
	private static String name = "Knight";
	private static int expOnKill = 20;
	private static int str = 4;
	private static int agi = 4;
	private static int vit = 5;

	private static Weapon weapon = Items.ironLongblade;

	private static Armor armor = Items.steelPlate;
	private static char icon = 'K';
	private static String color ="#FFFAF0";




	public Knight(int x, int y, int floor) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y, floor);


		//this.name = name;
		super.setLoot(loot);
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
