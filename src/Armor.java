import java.io.Serializable;

public class Armor extends Item implements Serializable {
	
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
	
	

}
