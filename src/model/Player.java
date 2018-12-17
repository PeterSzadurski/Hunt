package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Character implements Serializable {
	private static final long serialVersionUID = 4473093991835774643L;

	private int playerID;
	private int currentDungeonID;

	private String name;
	private int level;
	private int exp;
	private int expForNextLevel;
	private int hunger;
	private ArrayList<Item> backpack;
	private int armorId;
	private char icon = '@';
	private String color = "#FFFF00";

	public Player() {
		// just to make it a bean
	}

	/**
	 * Constructor for a new Player.
	 * 
	 * @param name
	 * @param str
	 *            Number of points to allocate to strength
	 * @param agi
	 *            Number of points to allocate to agility
	 * @param vit
	 *            Number of points to allocate to vitality
	 */
	public Player(String name, int str, int agi, int vit, char icon, String color, int x, int y) {

		super(name, str, agi, vit, Items.fists, Items.naked, icon, color, x, y);

		this.name = name;
		level = 1;
		exp = 0;
		expForNextLevel = 10;
		hunger = 100;
		backpack = new ArrayList<Item>();
		//backpack.add(Items.largePotion);

		// Calculate other stats
		//super.calcStrength();
		super.calcDamage();
		super.calcMoveSpeed();
		super.calcHP();
	}

	/**
	 * For making a Player from a database record
	 * 
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
	public Player(String name, int level, int str, int agi, int vit, Armor armor, Weapon weapon, int curHP, int exp,
			int hunger, ArrayList<Item> backpack, char icon, String color, int x, int y) {

		super(name, str, agi, vit, weapon, armor, icon, color, x, y);
		
		this.name = name;
		this.level = level;
		this.hunger = hunger;
		this.backpack = backpack;
		this.exp = exp;
		this.level = level;
		expForNextLevel = 10 + (level - 1) * 5;

	}

	public int getPlayerID() {
		return this.playerID;
	}

	public void setPlayerID(int id) {
		this.playerID = id;
	}

	public int getCurrentDungeonID() {
		return currentDungeonID;
	}

	public void setCurrentDungeonID(int currentDungeonID) {
		this.currentDungeonID = currentDungeonID;
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

	public void useFromBackpack(int index) {
		backpack.get(index).use(0);
		if (Game.ifUsed) {
			if (backpack.get(index).getCount() == 1) {
				backpack.remove(index);
				if (Game.innerSelect >= backpack.size()) {
					Game.innerSelect--;
				}
			} else {
				backpack.get(index).subCount();
			}
		}
	}
	
	/**
	 * For equipping things
	 * @param index
	 * @param type
	 */
	public void useFromBackpack(int index, String type) {
		if (type.equals("armor")) {
			Armor oldArmor = this.getArmor();
			Armor newArmor = (Armor)backpack.get(index);
			this.setArmor(newArmor);
			backpack.remove(index);
			if (oldArmor.getName().equals("No Armor")) {
				System.out.println("yes");
				backpack.add(0, oldArmor);
			}
			else {
				backpack.add(index, oldArmor);
				System.out.println("no");

				//backpack.remove(index);
			}
		} else if (type.equals("weapon")) {
			Weapon newWeapon = (Weapon)backpack.get(index);
			Weapon oldWeapon = this.getWeapon();
			this.setWeapon(newWeapon);
			backpack.remove(index);
			if (oldWeapon.getName().equals("No Weapon")) {
				if (backpack.get(0).getName().equals("No Armor")) {
				backpack.add(1, oldWeapon);
				}
				else {
					backpack.add(0, oldWeapon);
				}
			}
			else {
				backpack.add(index, oldWeapon);
			}
		}

		// this code doesn't make sense for equipping an item
		/*if (backpack.get(index).getCount() == 1) {
			backpack.remove(index);
			if (Game.innerSelect >= backpack.size()) {
				Game.innerSelect--;
			}
		} else {
			backpack.get(index).subCount();
		}*/
		
	}

	public void setBackpack(ArrayList<Item> backpack) {
		this.backpack = backpack;
	}

	public void pickUp(Item item) {
		if (backpack.contains(item)) {
			backpack.get(backpack.indexOf(item)).addCount(1);
		} else {
			backpack.add(item);
		}
	}

	/**
	 * Stores and returns the contents of the backpack in a String.
	 * 
	 * @return
	 */
	public String displayBackpack() {

		StringBuilder pack = new StringBuilder();
		pack.append("<i>&nbsp;" + getWeapon().toString() +"&nbsp;</i><br>");
		pack.append("<i>&nbsp;" + getArmor().toString()  +"&nbsp;</i><br>");
		for (int i = 0; i < backpack.size(); i++) {
			if (Game.innerSelect == i) {
				pack.append("[" + backpack.get(i).toString() + "]<br>");
			} else {
				pack.append("&nbsp;" + backpack.get(i).toString() + "&nbsp;<br>");
			}
		}

		return pack.toString();
	}
	
	public String backpackStorageString() {
		StringBuilder pack = new StringBuilder(200);
		for(int i = 0; i < backpack.size(); i ++) {
			pack.append(backpack.get(i).toString() + ",");
		}
		
		return pack.toString();
	}

	/**
	 * Removes an Item from the backpack (can be used to drop, pick an item to
	 * equip, or pick a Food object to eat)
	 * 
	 * @param index
	 * @return The selected Item from the backpack.
	 */

	public Item getItemFromPack(int index) {
		return backpack.remove(index);
	}

	/**
	 * Restores hunger.
	 * 
	 * @param food
	 *            The food the Player will eat (selected from backpack?)
	 */
	public void eat(Food food) {
		hunger += food.getHungerRestore();
		if (hunger > 100) {
			hunger = 100;
		}
	}

	/**
	 * The player's hunger decrements when they attack.
	 * 
	 * @param targetAGI
	 *            The target's agility.
	 * @return How much damage they deal to the target.
	 */
	@Override
	public int attack(int targetAGI) {

		hunger -= 2;

		return super.attack(targetAGI);
	}

	/**
	 * Increases exp points and checks if it is possible to level up
	 * 
	 * @param exp
	 * @return If the player has leveled up
	 */
	public boolean gainExp(int exp) {
		this.exp += exp;

		// Check if the player has leveled up
		if (this.exp >= expForNextLevel) {
			return true;
			// the class that calls this method calls levelUp when this method returns true
		}

		return false;
	}

	/**
	 * Increases the level and one stat.
	 * 
	 * @param stat
	 *            The stat the player wants to increase
	 */
	public void levelUp(String stat) {
		if (exp >= expForNextLevel) {
			level++;

			// Calculate overflow exp
			int overflow = exp - expForNextLevel;
			expForNextLevel += 5;
			exp = overflow;

			// Increment chosen stat and recalculate dependent stat
			switch (stat) {
			case "strength":
				setOldStrength(1 + getStrength());
				calcStrength();
				calcDamage();
				break;
			case "agility":
				setOldAgi(1 + getAgility());
				calcAgility();
				calcMoveSpeed();
				break;
			case "vitality":
				setVitality(1 + getVitality());
				calcHP();
				this.setCurHp(this.getHp());
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
	public void move(int x, int y) {
		int collideActor = -1;
		for (int i = 0; i < Game.actors.size(); i++) {
			if (((this.getX() + x) == Game.actors.get(i).getX()) && ((this.getY() + y) == Game.actors.get(i).getY())) {
				collideActor = i;
			}

		}

		// if there isn't an actor in the way
		if (collideActor == -1) {

			// if inside the Dungeon bounds
			if ((this.getX() + x) < Game.getDungeon()[Game.floor].getWidth() && (this.getX() + x > -1) && (this.getY() + y) < Game.getDungeon()[Game.floor].getHeight()
					&& (this.getY() + y > -1)) {
				// check if not solid
				if (Game.getDungeon()[Game.floor].getTile(this.getY() + y, this.getX() + x).isSolid() == false) {
					Game.getDungeon()[Game.floor].changeEntities(this.getY(), this.getX(), Game.getDungeon()[Game.floor].getTile(this.getY(), this.getX()).getIcon(),
							Game.getDungeon()[Game.floor].getTile(this.getY(), this.getX()).getColor());
					// check for floor items
					if (!Game.itemsFloor.isEmpty()) {
						for (int o = 0; o < Game.itemsFloor.size(); o++) {
							if (Game.itemsFloor.get(o).getX() == this.getX() && Game.itemsFloor.get(o).getY() == this.getY()) {
								Game.getDungeon()[Game.floor].changeEntities(Game.itemsFloor.get(o).getY(), Game.itemsFloor.get(o).getX(),
									Game.itemsFloor.get(o).getIcon(), Game.itemsFloor.get(o).getColor());
							}
						}
					}
					// check if on downtile
					
					this.setX(this.getX() + x);
					this.setY(this.getY() + y);
					Game.getDungeon()[Game.floor].changeEntities(this.getY(), this.getX(), this.icon, this.color);
					Game.log = ("Move Freely");
					if (Game.onDown(this)) {
						Game.log = "Press [Enter] to go down";
					}
					else if (Game.onUp(this)) {
						Game.log = "Press [Enter] to go up";
					}


				}

				else {
					// System.out.println("solid");
					Game.log = ("Cannot Move");
				}
			}
			// Game.update(d);
			// System.out.println("X: " + this.x + " Y: " + this.y);
		}

		else {
			int test = this.attack(Game.actors.get(collideActor));
			if (test != 0) {
			Game.log = this.name + " strikes the " + Game.actors.get(collideActor).getName() + " for " + test + " damage! | " + Game.actors.get(collideActor).getCurHp() + "/" + Game.actors.get(collideActor).getHp();
			}
			else {
				Game.log = this.name + " misses the " + Game.actors.get(collideActor).getName() + "! | " + Game.actors.get(collideActor).getCurHp() + "/" + Game.actors.get(collideActor).getHp();
			}
		}
	}
	
	public void fuseWithMonster (Character c) {
		int highestStat = c.getVitality();
		int lowestStat = c.getVitality();
		int lowestIndex = 0;
		int highestIndex = 0;
		int[] stats = {c.getVitality(), c.getOldStrength(), c.getOldAgi()};
		
		// find highest stat
		for (int i = 0; i < stats.length; i++) {
			if (highestStat < stats[i]) {
				highestStat = stats[i];
				highestIndex = i;
			}
		}
		// find lowest stat
		for (int i = 0; i < stats.length; i++) {
			if (lowestStat > stats[i]) {
				lowestStat = stats[i];
				lowestIndex = i;
			}
		}
		
		switch (highestIndex) {
		case 0:
			this.setVitality(this.getVitality() + c.getVitality());
			this.calcHP();
			this.setCurHp(this.getHp());
			break;
		case 1:
			this.setOldStrength(this.getOldStrength() + c.getOldStrength());
			this.calcStrength();
			break;
		case 2:
			this.setOldAgi(this.getOldAgi() + c.getOldAgi());
			calcAgility();
			break;
		}
		
		switch (lowestIndex) {
		case 0:
			this.setVitality((this.getVitality() + c.getVitality()) / 2);
			this.calcHP();
			this.setCurHp(this.getHp());
			break;
		case 1:
			this.setOldStrength((this.getOldStrength() + c.getOldStrength()) /2);
			this.calcStrength();
			break;
		case 2:
			this.setAgility((this.getAgility() + c.getAgility()) /2);
			break;
		}
		
		this.calcDamage();
		this.calcMoveSpeed();
		System.out.println("New hp = "+this.getCurHp() + " | " + this.getHp());
		
		Game.log = "You <span style =\"color: " + Game.magicEffectColor + "\">fused</span> with the " + c.getName() + "!";
		
		// check for highest stat
	/*	if (c.getVitality() < c.getStrength()) {
			if (c.getStrength() < c.getAgility()) {
				this.setAgility(this.getAgility() + c.getAgility());
				this.calcMoveSpeed();
			}
			else {}
			
		}
		else if (c.getVitality() < c.getAgility()) {} */
	}

	public void setArmorId(int id) {
		this.armorId = id;
		// TODO Auto-generated method stub
		
	}
	public int getArmorId () {
		return this.armorId;
	}
}
