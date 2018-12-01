public class Tile {
	
	private char icon;
	private boolean solid;
	private String color;
	
	public void setIcon(char icon) {
		this.icon = icon;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public void setColor(String color) {
		this.color = color;
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
