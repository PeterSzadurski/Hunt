import java.io.Serializable;

public class Item implements Serializable {
	
	private String name;
	private char icon;
	
	public Item() {
		
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
	
	

}
