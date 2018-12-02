package model;
import java.io.Serializable;

public class Armor extends Item implements Serializable {
	private static final long serialVersionUID = 1097799878020673882L;
	
	private int hpBonus;
	
	public Armor() {
		super();
	}
	
	public Armor(String name, char icon, int hpBonus) {
		super(name, icon);
		
		this.hpBonus = hpBonus;
	}

	public int getHpBonus() {
		return hpBonus;
	}

	public void setHpBonus(int hpBonus) {
		this.hpBonus = hpBonus;
	}
	
	public String toString() {
		return this.getName();
	}
	

}
