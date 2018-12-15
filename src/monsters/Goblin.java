package monsters;

import model.Armor;
import model.Game;
import model.Monster;
import model.Weapon;
import model.Items;

public class Goblin  extends Monster{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String name = "Goblin";
	private static int expOnKill = 10;
	private static int str = 2;
	private static int agi = 2;
	private static int vit = 2;
	private static Weapon weapon = Items.club;
	private static Armor armor = null;
	private static char icon = 'G';
	private static String color ="#006400";



	public Goblin(int x, int y, int floor) {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y, floor);
		
		//this.name = name;
		
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
