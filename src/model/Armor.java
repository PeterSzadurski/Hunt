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
	
	public Armor(String armor) {
		super();
		String[] delim = armor.split(",");
		String name = delim[0];
		char icon = delim[1].charAt(0);
		super.setName(name);
		super.setIcon(icon);
		this.hpBonus = Integer.parseInt(delim[2]);
	}

	public int getHpBonus() {
		return hpBonus;
	}

	public void setHpBonus(int hpBonus) {
		this.hpBonus = hpBonus;
	}
	
	public String toString() {
		return this.getName() + "," + this.getIcon() + "," + this.getHpBonus();
	}
	

}
