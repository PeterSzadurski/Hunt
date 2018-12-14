package model;
public class Tile {
	
	private char icon;
	private boolean solid;
	private String color;
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setIcon(char icon) {
		this.icon = icon;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public void setColor(String color) {
		this.color = color;
	}

	Tile (char icon, boolean solid, String color, String type) {
		this.icon = icon;
		this.solid = solid;
		this.color = color;
		this.type = type;
	}
	Tile (char icon, boolean solid, String color) {
		this.icon = icon;
		this.solid = solid;
		this.color = color;
	}
	
	public boolean isSolid(){
		return solid;	
	}

	public char getIcon() {
		return icon;
	}
	public String getColor() {
		return color;
	}

}
