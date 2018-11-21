import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Character implements Serializable {
	
	private String name;
	private int level;
	private int exp;
	private int expForNextLevel;
	private int hunger;
	private ArrayList<Item> backpack;
	
	private char icon = '@';
	private String color = "#FFFF00";
	
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
	public Player(String name, int str, int agi, int vit, char icon, String color,
			int x, int y) {
		
		super(name,str, agi, vit, new Weapon("dagger", '!', 5), new Armor("clothes", '#', 1),
				icon, color, x, y);
		
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
			int curHP, int exp, int hunger, ArrayList<Item> backpack,
			char icon, String color, int x, int y) {
		
		super(name, str, agi, vit, weapon, armor, icon, color, x, y);
		
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
		pack.append("<b>" + getWeapon().getName() + " |<span style=\"color: #ff0000\"> Power: " + getWeapon().getDamage() + "</span></b><br>");
		pack.append("<b>" + getArmor().getName() + " |<span style=\"color: #00baff\"> Rating: " + getArmor().getHpBonus() + "</span></b><br>");
		for(int i = 0; i < backpack.size(); i++) {
			pack.append(backpack.get(i).getName() + "<br>");
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
		playerString.append("Name: " + name + "<br>");
		playerString.append("Strength: " + getStrength() + "<br>");
		playerString.append("Agility: " + getAgility() + "<br>");
		playerString.append("Vitality: " + getVitality() + "<br>");
		playerString.append("Current HP: " + getCurHp() + "<br>");
		playerString.append("Hunger: " + hunger + "<br>");
		playerString.append("Weapon: " + getWeapon().getName() + "<br>");
		playerString.append("Armor: " + getArmor().getName() + "<br>");
		
		return playerString.toString();
	}
	@Override
	public void move(int x, int y, Dungeon d) {
		int collideActor = -1;
		for (int i = 0; i < Game.actors.size(); i++) {
			if (((this.getX() + x) == Game.actors.get(i).getX()) && ((this.getY() + y) == Game.actors.get(i).getY())) {
				collideActor = i;
			}

		}
		
		// if there isn't an actor in the way
		if (collideActor == -1) {

			// if inside the Dungeon bounds
			if ((this.getX() + x) < d.getWidth() && (this.getX() + x > -1) && (this.getY() + y) < d.getHeight() && (this.getY() + y > -1)) {
				// check if not solid
				if (d.getTile(this.getY() + y, this.getX() + x).isSolid() == false) {
					d.changeEntities(this.getY(), this.getX(), d.getTile(this.getY(), this.getX()).getIcon(),
							d.getTile(this.getY(), this.getX()).getColor());
					this.setX(this.getX() + x);
					this.setY(this.getY() + y);
					d.changeEntities(this.getY(), this.getX(), this.icon, this.color);
					Game.log = ("Move Freely");

				}

				else {
					//System.out.println("solid");
					Game.log = ("Cannot Move");
				}
			}
			// Game.update(d);
			//System.out.println("X: " + this.x + " Y: " + this.y);
		}
		
		else {
			Game.log = (Game.actors.get(collideActor).getName() + ": HP: " + Game.actors.get(collideActor).getCurHp() + 
					"/" + Game.actors.get(collideActor).getHp());
		}
	}

}
