package monsters;

import model.Armor;
import model.Game;
import model.Monster;
import model.Weapon;

public class Troll  extends Monster{

	/**
	 * 
	 */
	static int[] location = Game.getDungeon()[Game.floor].getLocation();
	private static final long serialVersionUID = 1L;
	private static String name = "Troll";
	private static int expOnKill = 15;
	private static int str = 3;
	private static int agi = 1;
	private static int vit = 4;
	private static Weapon weapon = Game.club;
	private static Armor armor = null;
	private static char icon = 'T';
	private static String color ="#006400";
	private static int x = location[1];
	private static int y = location[0];


	public Troll() {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y);

		//this.name = name;
		
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
