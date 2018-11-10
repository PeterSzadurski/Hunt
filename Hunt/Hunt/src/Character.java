import java.io.Serializable;
import java.util.Random;

public class Character implements Serializable {
	
	private int strength;
	private int agility;
	private int vitality;
	private int damage;
	private int moveSpeed;
	private int hp;
	private int curHp;
	private Weapon weapon;
	private Armor armor;
	
	public Character() {
		// this makes it a bean
	}
	
	public Character(int str, int agi, int vit, Weapon w, Armor a) {
		strength = str;
		agility = agi;
		vitality = vit;
		weapon = w;
		armor = a;
		
		calcDamage();
		calcMoveSpeed();
		calcHP();
		curHp = hp;
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

	public void calcDamage() {
		damage = strength;
		
		if(weapon!=null) {
			damage+=weapon.getDamage();
		}
	}
	
	public void calcMoveSpeed() {
		moveSpeed = agility * 3;
	}
	
	public void calcHP() {
		hp = 10 + (vitality * 5);
		
		if(armor!=null) {
			hp+=armor.getHpBonus();
		}
	}
	
	/**
	 * The character attacks a target character.
	 * @param targetAGI
	 * @return How much damage the character dealt.
	 */
	public int attack(int targetAGI) {
		// Calculate the hit chance
		int hitChance = 0;
		if(agility < targetAGI) {
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
	 * @param damage The damage they will take.
	 * @return true if the character is dead.
	 */
	public boolean takeDamage(int damage) {
		curHp-=damage;
		boolean dead = false;
		if (curHp < 0) {
			curHp = 0;
		}
		
		if (curHp == 0) {
			dead = true;
		}
		
		return dead;
	}

}
