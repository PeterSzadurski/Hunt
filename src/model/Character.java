package model;
import java.io.Serializable;
import java.util.Random;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class Character implements Serializable {
	private static final long serialVersionUID = 63535810039368495L;
	
	private String name;
	private int strength;
	private int agility;
	private int vitality;
	private int damage;
	private int moveSpeed;
	private int hp;
	private int curHp;
	private Weapon weapon;
	private Armor armor;
	private boolean solid = true;
	private int x;
	private int y;
	int oldStrength;
	int oldAgi;
	

	private char icon;

	private String color;

	public Character() {
		// this makes it a bean
	}

	public Character(String name, int str, int agi, int vit, Weapon w, Armor a, char icon, String color, int x, int y) {
		this.name = name;
		
		strength = str;
		oldStrength = strength;
		agility = agi;
		oldAgi = agility;
		vitality = vit;
		weapon = w;
		armor = a;

		this.icon = icon;

		this.color = color;

		this.x = x;
		this.y = y;
		calcStrength();
		calcDamage();
		calcAgility();
		calcMoveSpeed();
		calcHP();
		curHp = hp;
	}

	public Character(String name, int str, int agi, int vit, Weapon w, Armor a, char icon, String color) {
		this.name = name;
		
		strength = str;
		agility = agi;
		vitality = vit;
		weapon = w;
		armor = a;

		this.icon = icon;

		this.color = color;


		calcDamage();
		calcMoveSpeed();
		calcHP();
		curHp = hp;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String getName() {
		return name;
	}
	
	public int attack(Character c) {
		// Calculate the hit chance
		int hitChance = 0;
		if (agility < c.getAgility()) {
			hitChance = 50;
		} else if (agility == c.getAgility()) {
			hitChance = 75;
		} else {
			hitChance = 90;
		}

		// Calculate if the attack hits
		Random rand = new Random();
		int hit = rand.nextInt(100);

		if (hit <= hitChance) {
			c.setCurHp(c.getCurHp() - damage);
			return damage;
		} else {
			return 0;
		}
	}

	public void move(int x, int y, Dungeon d) {
		int collideActor = -1;
		for (int i = 0; i < Game.actors.size(); i++) {
			if (((this.x + x) == Game.actors.get(i).getX()) && ((this.y + y) == Game.actors.get(i).getY())) {
				collideActor = i;
			}

		}
		
		// if there isn't an actor in the way
		if (collideActor == -1) {

			// if inside the Dungeon bounds
			if ((this.x + x) < d.getWidth() && (this.x + x > -1) && (this.y + y) < d.getHeight() && (this.y + y > -1)) {
				// check if not solid
				if (d.getTile(this.y + y, this.x + x).isSolid() == false) {
					d.changeEntities(this.y, this.x, d.getTile(this.y, this.x).getIcon(),
							d.getTile(this.y, this.x).getColor());
					// check the floor for items
					if (!Game.itemsFloor.isEmpty()) {
						for (int o = 0; o < Game.itemsFloor.size(); o++) {
							if (Game.itemsFloor.get(o).getX() == this.getX() && Game.itemsFloor.get(o).getY() == this.getY()) {
							d.changeEntities(Game.itemsFloor.get(o).getY(), Game.itemsFloor.get(o).getX(),
									Game.itemsFloor.get(o).getIcon(), Game.itemsFloor.get(o).getColor());
							}
						}
					}
					this.x += x;
					this.y += y;
					d.changeEntities(this.y, this.x, this.icon, this.color);
					//Game.log = ("Move Freely");

				}

				else {
					//System.out.println("solid");
					//Game.log = ("Cannot Move");
				}
			}
			// Game.update(d);
			//System.out.println("X: " + this.x + " Y: " + this.y);
		}
		
		else {
			//Game.log = (Game.actors.get(collideActor).getName() + ": HP: " + Game.actors.get(collideActor).getCurHp() + 
				//	"/" + Game.actors.get(collideActor).getHp());
		}
	}

	
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getVitality() {
		return vitality;
	}

	public void setVitality(int vitality) {
		this.vitality = vitality;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getCurHp() {
		return curHp;
	}

	public void setCurHp(int curHp) {
		this.curHp = curHp;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public boolean isSolid() {
		return solid;
	}
	public void setIsSolid(boolean s) {
		solid = s;
	}

	public char geticon() {
		return icon;
	}

	public String getColor() {
		return color;
	}
	
	public void calcStrength () {
		this.strength = this.oldStrength;
		if (armor != null) {
			this.strength += armor.getStrAlter();
			if (this.strength < 1) {
				this.strength = 1;
			} 
		}
	}
	
	public void calcAgility () {
		this.agility = this.oldAgi;
		if (armor != null) {
			this.agility += armor.getAgiAlter();
			if (this.agility < 1) {
				this.agility = 1;
			} 
		}
	}

	public int getOldStrength() {
		return oldStrength;
	}

	public void setOldStrength(int oldStrength) {
		this.oldStrength = oldStrength;
	}

	public int getOldAgi() {
		return oldAgi;
	}

	public void setOldAgi(int oldAgi) {
		this.oldAgi = oldAgi;
	}

	public void calcDamage() {
		this.damage = strength;

		if (weapon != null && !(weapon.getDamage() ==0)) {
			this.damage *= weapon.getDamage();
		}
	}

	public void calcMoveSpeed() {
		this.moveSpeed = agility * 3;
	}

	public void calcHP() {
		this.hp = 10 + (vitality * 5);

		if (armor != null) {
			this.hp += armor.getHpBonus();
		}
	}

	/**
	 * The character attacks a target character.
	 * 
	 * @param targetAGI
	 * @return How much damage the character dealt.
	 */
	public int attack(int targetAGI) {
		// Calculate the hit chance
		int hitChance = 0;
		if (agility < targetAGI) {
			hitChance = 50;
		} else if (agility == targetAGI) {
			hitChance = 75;
		} else {
			hitChance = 90;
		}

		// Calculate if the attack hits
		Random rand = new Random();
		int hit = rand.nextInt(100);

		if (hit <= hitChance) {
			return damage;
		} else {
			return 0;
		}
	}

	/**
	 * The character loses HP and potentially dies.
	 * 
	 * @param damage
	 *            The damage they will take.
	 * @return true if the character is dead.
	 */
	public boolean takeDamage(int damage) {
		curHp -= damage;
		boolean dead = false;
		if (curHp < 0) {
			curHp = 0;
		}

		if (curHp == 0) {
			dead = true;
		}

		return dead;
	}

	public void move(int x, int y) {
		
		int collideActor = -1;
		for (int i = 0; i < Game.actors.size(); i++) {
			if (((this.x + x) == Game.actors.get(i).getX()) && ((this.y + y) == Game.actors.get(i).getY())) {
				collideActor = i;
			}

		}
		
		// if there isn't an actor in the way
		if (collideActor == -1) {

			// if inside the Dungeon bounds
			if ((this.x + x) < Game.getDungeon()[Game.floor].getWidth() && (this.x + x > -1) && (this.y + y) < Game.getDungeon()[Game.floor].getHeight() && (this.y + y > -1)) {
				// check if not solid
				if (Game.getDungeon()[Game.floor].getTile(this.y + y, this.x + x).isSolid() == false) {
					Game.getDungeon()[Game.floor].changeEntities(this.y, this.x, Game.getDungeon()[Game.floor].getTile(this.y, this.x).getIcon(),
							Game.getDungeon()[Game.floor].getTile(this.y, this.x).getColor());
					// check the floor for items
					if (!Game.itemsFloor.isEmpty()) {
						for (int o = 0; o < Game.itemsFloor.size(); o++) {
							if (Game.itemsFloor.get(o).getX() == this.getX() && Game.itemsFloor.get(o).getY() == this.getY()) {
							Game.getDungeon()[Game.floor].changeEntities(Game.itemsFloor.get(o).getY(), Game.itemsFloor.get(o).getX(),
									Game.itemsFloor.get(o).getIcon(), Game.itemsFloor.get(o).getColor());
							}
						}
					}
					this.x += x;
					this.y += y;
					Game.getDungeon()[Game.floor].changeEntities(this.y, this.x, this.icon, this.color);
					//Game.log = ("Move Freely");

				}

				else {
					//System.out.println("solid");
					//Game.log = ("Cannot Move");
				}
			}
			// Game.update(d);
			//System.out.println("X: " + this.x + " Y: " + this.y);
		}
		
		else {
			//Game.log = (Game.actors.get(collideActor).getName() + ": HP: " + Game.actors.get(collideActor).getCurHp() + 
				//	"/" + Game.actors.get(collideActor).getHp());
		}
		
	}

}
