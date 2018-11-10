import java.io.Serializable;
import java.util.ArrayList;
// test edit
public class Player extends Character implements Serializable {
	
	private String name;
	private int level;
	private int exp;
	private int expForNextLevel;
	private int hunger;
	private ArrayList<Item> backpack;
	
	public Player() {
		// just to make it a bean
	}
	
	/**
	 * Constructor for a new Player.
	 * @param name
	 * @param str Number of points to allocate to strength
	 * @param agi Number of points to allocate to agility
	 * @param vit Number of points to allocate to vitality
	 */
	public Player(String name, int str, int agi, int vit) {
		
		super(str, agi, vit, new Weapon("dagger", '!', 5), new Armor("clothes", '#', 1));
		
		this.name = name;
		level = 1;
		exp = 0;
		expForNextLevel = 10;
		hunger = 100;
		backpack = new ArrayList<Item>();
		
		// Calculate other stats
		super.calcDamage();
		super.calcMoveSpeed();
		super.calcHP();
		
	}
	
	/**
	 * For making a Player from a database record
	 * @param name
	 * @param level
	 * @param str
	 * @param agi
	 * @param vit
	 * @param armor
	 * @param weapon
	 * @param curHP
	 * @param exp
	 * @param hunger
	 * @param backpack
	 */
	public Player(String name, int level, int str, int agi, int vit, Armor armor, Weapon weapon, 
			int curHP, int exp, int hunger, ArrayList<Item> backpack) {
		
		super(str, agi, vit, weapon, armor);
		
		this.name = name;
		this.level = level;
		this.hunger = hunger;
		this.backpack = backpack;
		this.exp = exp;
		this.level = level;
		expForNextLevel = 10 + (level - 1) * 5;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getExpForNextLevel() {
		return expForNextLevel;
	}
	public void setExpForNextLevel(int expForNextLevel) {
		this.expForNextLevel = expForNextLevel;
	}
	public int getHunger() {
		return hunger;
	}
	public void setHunger(int hunger) {
		this.hunger = hunger;
	}
	public ArrayList<Item> getBackpack() {
		return backpack;
	}
	public void setBackpack(ArrayList<Item> backpack) {
		this.backpack = backpack;
	}
	
	public void pickUp(Item item) {
		backpack.add(item);
	}
	
	/**
	 * Stores and returns the contents of the backpack in a String.
	 * @return
	 */
	public String displayBackpack() {
		
		StringBuilder pack = new StringBuilder();
		
		for(int i = 0; i < backpack.size(); i++) {
			pack.append(backpack.get(i).getName());
		}
		
		return pack.toString();
	}
	
	/**
	 * Removes an Item from the backpack (can be used to drop, pick an item to equip, or
	 * pick a Food object to eat)
	 * @param index
	 * @return The selected Item from the backpack.
	 */
	public Item getItemFromPack(int index) {
		return backpack.remove(index);
	}
	
	/**
	 * Restores hunger.
	 * @param food The food the Player will eat (selected from backpack?)
	 */
	public void eat(Food food) {
		hunger+=food.getHungerRestore();
		if (hunger > 100) {
			hunger = 100;
		}
	}
	
	/**
	 * The player's hunger decrements when they attack.
	 * @param targetAGI The target's agility.
	 * @return How much damage they deal to the target.
	 */
	@Override
	public int attack(int targetAGI) {
		
		hunger-=2;
		
		return super.attack(targetAGI);
	}
	
	/**
	 * Increases exp points and checks if it is possible to level up
	 * @param exp
	 * @return If the player has leveled up
	 */
	public boolean gainExp(int exp) {
		this.exp+=exp;
		
		// Check if the player has leveled up
		if (this.exp >= expForNextLevel) {
			return true;  
			// the class that calls this method calls levelUp when this method returns true
		}
		
		return false;
	}
	
	/**
	 * Increases the level and one stat.
	 * @param stat The stat the player wants to increase
	 */
	public void levelUp(String stat) {
		if(exp >= expForNextLevel) {
			
			level++;
			
			// Calculate overflow exp
			int overflow = exp - expForNextLevel;
			expForNextLevel+=5;
			exp = overflow;
					
			// Increment chosen stat and recalculate dependent stat
			switch(stat) {
				case "strength":	setStrength(1 + getStrength());
									calcDamage();
									break;
				case "agility": 	setAgility(1 + getAgility());;
									calcMoveSpeed();
									break;
				case "vitality": 	setVitality(1 + getVitality());;
									calcHP();
									break;
			}
			
		}
	}
	
	@Override
	public String toString() {
		StringBuilder playerString = new StringBuilder();
		playerString.append("Name: " + name + "\n");
		playerString.append("Strength: " + getStrength() + "\n");
		playerString.append("Agility: " + getAgility() + "\n");
		playerString.append("Vitality: " + getVitality() + "\n");
		playerString.append("Current HP: " + getCurHp() + "\n");
		playerString.append("Hunger: " + hunger + "\n");
		playerString.append("Weapon: " + getWeapon().getName() + "\n");
		playerString.append("Armor: " + getArmor().getName() + "\n");
		
		return playerString.toString();
	}

}
