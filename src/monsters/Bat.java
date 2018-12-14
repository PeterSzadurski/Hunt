package monsters;

import model.Armor;
import model.Game;
import model.Monster;
import model.Weapon;

public class Bat  extends Monster{

	/**
	 * 
	 */
	static int[] location = Game.getDungeon()[Game.floor].getLocation();
	private static final long serialVersionUID = 1L;
	private static String name = "Bat";
	private static int expOnKill = 5;
	private static int str = 1;
	private static int agi = 3;
	private static int vit = 1;
	private static Weapon weapon = null;
	private static Armor armor = null;
	private static char icon = 'B';
	private static String color ="#ffffff";
	private static int x = location[1];
	private static int y = location[0];


	public Bat() {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y);

		//this.name = name;
		
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
