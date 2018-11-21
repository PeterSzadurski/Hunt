import java.io.Serializable;

public class Item implements Serializable {
	
	private int count = 0;
	private String name;
	private char icon;
	private String effectText;
	private int magnitude = 0;
	private Effect effect;
	
	public Item() {
		
	}
	
	public Item(String name, char icon, String effectText, int magnitude, Effect effect) {
		this.name = name;
		this.icon = icon;
		this.effectText = effectText;
		this.magnitude = magnitude;
		this.effect = effect;
	}
	
	public Item(String name, char icon) {
		this.name = name;
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getIcon() {
		return icon;
	}

	public void setIcon(char icon) {
		this.icon = icon;
	}
	
	public int getCount () {
		return count;
	}
	
	public void addCount (int add) {
		this.count += count;
	}
	
	public void subCount () {
		this.count--;
	}
	
	public String getEffectText () {
		return effectText;
	}
	
	public void use (int target) {
		Game.effects(target, this.magnitude, this.effect);
	}
	
	@Override
	public String toString () {
		return getName() + " | " + getEffectText() + " (" + getCount() + ")";
	}
	
	

}
