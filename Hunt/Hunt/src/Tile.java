public class Tile {
	
	private char icon;
	private boolean solid;
	private String color;
	
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
