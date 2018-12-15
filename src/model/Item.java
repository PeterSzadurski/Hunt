package model;
import java.io.Serializable;

public class Item implements Serializable {
	private static final long serialVersionUID = -7964186490924305132L;
	
	private int count = 1;
	private String name;
	private char icon;
	private String effectText;
	private int magnitude = 0;
	private Effect effect;
	private String color = "#C5B358";
	public Item() {
		
	}
	
	public Item(String name, char icon, String effectText, int magnitude, Effect effect, Effect subEffect) {
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

	public String getColor() {
		return color;
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
	public void setCount(int count) {
		this.count = count;
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
	
	public void setEffectText(String text) {
		effectText = text;
	}
	
	/*
	 * use the item effect
	 * @param target
	 */
	
	public void use (int target) {
		
		Game.effects(target, this.magnitude, this.effect);
	}
	
	@Override
	public String toString () {
		return getName() + " | " + getEffectText() + " (" + getCount() + ")";
	}
	
	public String getStorageString() {
		return getName() + "|" + getEffectText() + "|" + this.magnitude + "|" + this.effect + "|" + getCount();
	}

}