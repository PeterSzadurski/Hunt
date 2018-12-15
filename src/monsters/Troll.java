package monsters;

import model.Armor;
import model.Game;
import model.Monster;
import model.Weapon;
import model.Items;
public class Troll  extends Monster{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String name = "Troll";
	private static int expOnKill = 15;
	private static int str = 3;
	private static int agi = 1;
	private static int vit = 4;
	private static Weapon weapon = Items.club;
	private static Armor armor = null;
	private static char icon = 'T';
	private static String color ="#006400";

	public Troll(int x, int y, int floor) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y, floor);

		//this.name = name;
		
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
