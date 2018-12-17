package model;
import java.io.Serializable;
public class Weapon extends Item implements Serializable{
	private static final long serialVersionUID = 1673858350622172505L;
	private int damage;
	private int id;
	
	public Weapon() {
		super();
		// makes it a bean
	}
	
	public Weapon(String name, char icon, int damage,int id) {
		super(name, icon);
		this.damage = damage;
		this.id =id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Weapon(String weapon) {
		super();
		String[] delim = weapon.split("|");
		String name = delim[0];
		super.setName(name);
		super.setIcon('!');
		this.damage = Integer.parseInt(delim[1]);
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public String toString() {
		return  this.getName() + " |<span style=\"color:" + Game.debuffColor + "\"> Power: "
					+ this.getDamage() + "&nbsp;</span></i>";

	}
	
	public String getWeaponStorageString() {
		return getName() + "," + getIcon() + "," + getDamage();
	}

}
