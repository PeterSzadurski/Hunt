package model;
import java.io.Serializable;

public class Armor extends Item implements Serializable {
	private static final long serialVersionUID = 1097799878020673882L;
	
	private int hpBonus;
	private int agiAlter;
	private int strAlter;
	
	public int getAgiAlter() {
		return agiAlter;
	}

	public void setAgiAlter(int agiAlter) {
		this.agiAlter = agiAlter;
	}

	public int getStrAlter() {
		return strAlter;
	}

	public void setStrAlter(int strAlter) {
		this.strAlter = strAlter;
	}

	public Armor() {
		super();
	}
	
	public Armor(String name, char icon, int hpBonus, int agiAlter, int strAlter) {
		super(name, icon);
		
		this.hpBonus = hpBonus;
		this.agiAlter = agiAlter;
		this.strAlter = strAlter;
	}
	
	public Armor(String armor) {
		super();
		String[] delim = armor.split(",");
		String name = delim[0];
		super.setName(name);
		super.setIcon('#');
		this.hpBonus = Integer.parseInt(delim[1]);
	}

	public int getHpBonus() {
		return hpBonus;
	}

	public void setHpBonus(int hpBonus) {
		this.hpBonus = hpBonus;
	}
	
	public String toString() {
		StringBuilder armorString = new StringBuilder();
		armorString.append(this.getName() + " | <span style=\"color:" + Game.buffColor + "\"> Rating: "
				+ this.getHpBonus() + "</span>");
		if (this.strAlter > 0) {
			armorString.append(" | <span style=\"color:" + Game.buffColor + "\">+" + this.getStrAlter() + " Strength</span>");
		}
		else if (this.strAlter < 0) {
			armorString.append(" | <span style=\"color:" + Game.debuffColor + "\">" + this.getStrAlter() + " Strength</span>");
		}
		if (this.agiAlter > 0) {
			armorString.append(" | <span style=\"color:" + Game.buffColor + "\">+" + this.getAgiAlter() + " Agility</span>");
		}
		else if (this.agiAlter < 0) {
			armorString.append(" | <span style=\"color:" + Game.debuffColor + "\">" + this.getAgiAlter() + " Agility</span>");
		}
		//armorString.append("<br>");
		return armorString.toString();
				// this.getName() + " |<span style=\"color:" + Game.buffColor + "\"> Rating: "
				//+ this.getHpBonus() + "</span>";
	}
	
	public String getArmorStorageString() {
		return getName() + "," + getHpBonus();
	}

}
