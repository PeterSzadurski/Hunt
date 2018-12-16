package model;
import java.io.Serializable;
import java.util.Random;

public class Monster extends Character implements Serializable {
	private static final long serialVersionUID = 6331686211289256850L;
	
	private String type;
	private int expOnKill;
	private double turnCount = 0;
<<<<<<< HEAD
	private int id;
	private int currentDungeonLevel;
=======
	int id;

	private int floor;
	
	private Item[] loot;

>>>>>>> test_branch
	
	MonsterStates state = MonsterStates.IDLE;
	
	
	
	
	public Monster() {
		// makes it a bean
	}
	
	public Monster(String name, int expOnKill, int str, int agi, int vit, Weapon weapon, 
			Armor armor, char icon, String color, int x, int y, int floor) {
		
		super(name, str, agi, vit, weapon, armor, icon, color, x, y);
		
		//this.name = name;
		this.expOnKill = expOnKill;
		this.floor =floor;
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
<<<<<<< HEAD
	
	
=======
	
	
	
	public Item[] getLoot() {
		return loot;
	}

	public void setLoot(Item[] loot) {
		this.loot = loot;
	}

>>>>>>> test_branch
	public MonsterStates getState() {
		return state;
	}

	public void setState(MonsterStates state) {
		this.state = state;
	}

	public int getExpOnKill() {
		return expOnKill;
	}
	
	public Item getLootItem() {
		// will only drop items half the time
			int choose = (int) (Math.random() * loot.length) + 0;
			return loot[choose];
	}

	/*public int getCurrentLevel() {
		return currentDungeonLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentDungeonLevel = currentLevel;
	}
*/
	public int getId() {
		return id;
	}

	public void setId(int monsterId) {
		this.id = monsterId;
	}

	public int getCurrentLevel() {
		return currentDungeonLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentDungeonLevel = currentLevel;
	}

	public int getId() {
		return id;
	}

	public void setId(int monsterId) {
		this.id = monsterId;
	}

	public void setExpOnKill(int expOnKill) {
		this.expOnKill = expOnKill;
	}

	public void setTurnCount (double turnCount) {
		this.turnCount = turnCount;
	}
	
	public double getTurnCount () {
		return turnCount;
	}
	
	public String toString() {
		StringBuilder monsterString = new StringBuilder();
		monsterString.append("Type: " + getName() + "\n");
		monsterString.append("Strength: " + getStrength() + "\n");
		monsterString.append("Agility: " + getAgility() + "\n");
		monsterString.append("Vitality: " + getVitality() + "\n");
		monsterString.append("Current HP: " + getCurHp() + "\n");
		
		return monsterString.toString();
	}
	
	public boolean withinRange (Character c, int range) {
		return ((this.getX() - c.getX() <= range) && (this.getX() - c.getX() >= -range) && (this.getY() - c.getY() <= range) && (this.getY() - c.getY() >= -range));
	}
	
	
	
}
