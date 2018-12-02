package model;
import java.io.Serializable;

public class Food extends Item implements Serializable {
	private static final long serialVersionUID = 8174808095852826297L;
	
	private int hungerRestore;
	
	public Food() {
		super();
	}
	
	public Food(String name, char icon, int hungerRestore) {
		super(name, icon);
		this.hungerRestore = hungerRestore;
	}

	public int getHungerRestore() {
		return hungerRestore;
	}

	public void setHungerRestore(int hungerRestore) {
		this.hungerRestore = hungerRestore;
	}
	
}
