package model;


public class ItemFloor {
	Item item;
	int x;
	int y;
	public ItemFloor (Item item, int x, int y) {
		this.item = item;
		this.x = x;
		this.y = y;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
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
	
	public char getIcon () {
		return item.getIcon();
		//return 'x';
	}
	
	public String getColor () {
		return item.getColor();
		//return "blue";
	}
	
	
	
}
