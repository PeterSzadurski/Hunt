package monsters;

import model.Armor;
import model.Game;
import model.Item;
import model.Monster;
import model.Weapon;

import model.Items;


public class Emperor  extends Monster{

	
	  
	private static Item[] loot = {Items.scrollTeleportation, Items.scrollGreaterFireball, Items.scrollMasterFrozenTime};

	private static final long serialVersionUID = 1L;
	private static String name = "Emperor";
	private static int expOnKill = 50;
	private static int str = 8;
	private static int agi = 25;
	private static int vit = 15;

	private static Weapon weapon = Items.royalLance;

	private static Armor armor = Items.blessedArmor;
	private static char icon = 'E';
	private static String color = Game.magicEffectColor;




	public Emperor(int x, int y, int floor) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y, floor);


		//this.name = name;
		super.setLoot(loot);
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
