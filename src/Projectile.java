
public class Projectile  {
	char icon = 'o';
	String color;
	int x;
	int y;
	int agility;
	Projectile(){}
	Projectile (String color, int x, int y, int agility) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.agility = agility;
	}
	public char getIcon() {
		return icon;
	}
	public void setIcon(char icon) {
		this.icon = icon;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getAgility() {
		return agility;
	}
	public void setAgility(int agility) {
		this.agility = agility;
	}
	
	
}
