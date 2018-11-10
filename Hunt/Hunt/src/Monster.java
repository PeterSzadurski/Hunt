import java.io.Serializable;
import java.util.Random;

public class Monster extends Character implements Serializable {
	
	private String type;
	private int expOnKill;
	
	public Monster() {
		// makes it a bean
	}
	
	public Monster(String type, int expOnKill, int str, int agi, int vit, Weapon weapon, 
			Armor armor) {
		
		super(str, agi, vit, weapon, armor);
		
		this.type = type;
		this.expOnKill = expOnKill;
		
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
	
	
	public int getExpOnKill() {
		return expOnKill;
	}

	public void setExpOnKill(int expOnKill) {
		this.expOnKill = expOnKill;
	}

	public String toString() {
		StringBuilder monsterString = new StringBuilder();
		monsterString.append("Type: " + type + "\n");
		monsterString.append("Strength: " + getStrength() + "\n");
		monsterString.append("Agility: " + getAgility() + "\n");
		monsterString.append("Vitality: " + getVitality() + "\n");
		monsterString.append("Current HP: " + getCurHp() + "\n");
		
		return monsterString.toString();
	}
	
}
