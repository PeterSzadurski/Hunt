package model;
public class Tile {

	private char icon;
	private boolean solid;
	private String color;
	private String type;
<<<<<<< HEAD
	
=======

>>>>>>> test_branch
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

<<<<<<< HEAD

	public Tile (char icon, boolean solid, String color, String type) {

//	Tile (char icon, boolean solid, String color, String type) {
=======
	Tile (char icon, boolean solid, String color, String type) {
>>>>>>> test_branch
		this.icon = icon;
		this.solid = solid;
		this.color = color;
		this.type = type;
	}
	Tile (char icon, boolean solid, String color) {

		this.icon = icon;
		this.solid = solid;
		this.color = color;
		this.type = type;
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
<<<<<<< HEAD
	
	public String toString() {
		return this.type;
	}
}
=======

	public String toString() {
		return this.type;
	}
}
>>>>>>> test_branch
