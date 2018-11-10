import java.io.Serializable;
public class Weapon extends Item implements Serializable{
	
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

}
