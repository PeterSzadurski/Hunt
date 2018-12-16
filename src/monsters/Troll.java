package monsters;

import model.Armor;
import model.Game;
<<<<<<< HEAD
import model.Monster;
import model.Weapon;

=======
import model.Item;
import model.Monster;
import model.Weapon;
import model.Items;
>>>>>>> test_branch
public class Troll  extends Monster{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String name = "Troll";
<<<<<<< HEAD
=======
	private static Item[] loot = {Items.smallPoison, Items.smallPotion, Items.smallPoison, Items.smallPotion, Items.scrollMinorFrozenTime, Items.smallPotion, Items.largePotion};
>>>>>>> test_branch
	private static int expOnKill = 15;
	private static int str = 3;
	private static int agi = 1;
	private static int vit = 4;
<<<<<<< HEAD
	private static Weapon weapon = Game.club;
=======
	private static Weapon weapon = Items.club;
>>>>>>> test_branch
	private static Armor armor = null;
	private static char icon = 'T';
	private static String color ="#006400";

<<<<<<< HEAD
	public Troll(int x, int y) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y);

		//this.name = name;
		
=======
	public Troll(int x, int y, int floor) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y, floor);

		//this.name = name;
		super.setLoot(loot);
>>>>>>> test_branch
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
