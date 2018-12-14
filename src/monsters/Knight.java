package monsters;

import model.Armor;
import model.Game;
import model.Monster;
import model.Weapon;

public class Knight  extends Monster{

	/**
	 * 
	 */
	static int[] location = Game.getDungeon()[Game.floor].getLocation();
	private static final long serialVersionUID = 1L;
	private static String name = "Knight";
	private static int expOnKill = 20;
	private static int str = 7;
	private static int agi = 2;
	private static int vit = 10;
	private static Weapon weapon = Game.ironLongblade;
	private static Armor armor = null;
	private static char icon = 'K';
	private static String color ="#FFFAF0";
	private static int x = location[1];
	private static int y = location[0];



	public Knight() {
		super(name, expOnKill, str, agi, vit, weapon, armor, icon, color, x, y);

		//this.name = name;
		
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}
	
}
