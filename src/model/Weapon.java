package model;
import java.io.Serializable;
public class Weapon extends Item implements Serializable{
	private static final long serialVersionUID = 1673858350622172505L;
	private int damage;
	
	public Weapon() {
		super();
		// makes it a bean
	}
	
	public Weapon(String name, char icon, int damage) {
		super(name, icon);
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public String toString() {
		return this.getName();
	}

}
