package monsters;

import model.Armor;
import model.Game;
import model.Monster;
import model.Weapon;
import model.Item;
import model.Monster;
import model.Weapon;
import model.Items;


public class RoyalGuard  extends Monster{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String name = "Royal Guard";

	private static Item[] loot = {Items.royalLance, Items.blessedArmor, Items.scrollTeleportation, Items.scrollFrozenTime, Items.scrollFrozenTime};

	private static int expOnKill = 30;
	private static int str = 4;
	private static int agi = 15;
	private static int vit = 8;



	private static Weapon weapon = Items.royalLance;
	private static Armor armor = Items.blessedArmor;
	private static char icon = 'R';
	private static String color = "#FF00FF";





	public RoyalGuard(int x, int y, int floor) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y, floor);
		
		//this.name = name;
		super.setLoot(loot);
		super.calcStrength();
		super.calcAgility();
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
