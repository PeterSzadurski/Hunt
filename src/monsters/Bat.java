package monsters;

import model.Armor;
import model.Game;
<<<<<<< HEAD
=======
import model.Item;
import model.Items;
>>>>>>> test_branch
import model.Monster;
import model.Weapon;

public class Bat  extends Monster{

	/**
	 * 
	 */
<<<<<<< HEAD
	 
	private static final long serialVersionUID = 1L;
=======
	private static final long serialVersionUID = 1L;
	private static  Item[] loot = {Items.smallPoison, Items.smallPotion, Items.smallPoison, Items.smallPotion, Items.smallPoison, Items.smallPotion, Items.largePotion};
>>>>>>> test_branch
	private static String name = "Bat";
	private static int expOnKill = 5;
	private static int str = 1;
	private static int agi = 3;
	private static int vit = 1;
	private static Weapon weapon = null;
	private static Armor armor = null;
	private static char icon = 'B';
	private static String color ="#ffffff";
<<<<<<< HEAD
	private static int y;


	public Bat(int x, int y) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y);

		//this.name = name;
		
=======
	//private static int y;
	//private static int floor;


	public Bat(int x, int y, int floor) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y, floor);

		//this.name = name;
		super.setLoot(loot);

>>>>>>> test_branch
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
