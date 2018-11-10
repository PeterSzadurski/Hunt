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
	private boolean solid = true;
	private int x;
	private int y;
	
	private char icon;
	
	private String color;
	
	public Character() {
		// this makes it a bean
	}
	
	public Character(int str, int agi, int vit, Weapon w, Armor a, char icon, 
			String color, int x, int y) {
		strength = str;
		agility = agi;
		vitality = vit;
		weapon = w;
		armor = a;
		
		this.icon = icon;
		
		this.color = color;
		
		this.x = x;
		this.y = y;
		
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

	public void move(int x, int y, Dungeon d) {
		if ((this.x + x) < d.getWidth() && (this.x + x > -1)
			&& (this.y + y) < d.getHeight() && (this.y + y > -1)) {
				if (d.getTile(this.x + x, this.y + y).isSolid() == false) {
					d.changeEntities(this.y, this.x, d.getTile(this.x, this.y).getIcon());
					this.x += x;
					this.y += y;
					d.changeEntities(this.y, this.x, this.icon);
				}
		}
		System.out.println("X: " + this.x + " Y: " + this.y);
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
	
	public boolean isSolid(){
		return solid;	
	}

	public char geticon() {
		return icon;
	}
	public String getColor() {
		return color;
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
