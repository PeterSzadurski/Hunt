import java.util.ArrayList;

public class Game {

	private Player player;
	private static ArrayList<Character> actors = new ArrayList<Character>();
	
	public Game (Player player) {
		this.player = player;
		actors.add(player);
	}
	
	// Generate weapons
	
	static Weapon club = new Weapon("club", '!', 5);
	
	
	public static void update(Dungeon d) {
		for (int i = 0; i < actors.size(); i++) {
		d.changeEntities(actors.get(0).getY(), actors.get(i).getX(),
				actors.get(i).geticon());
		}
		System.out.println("UPDATE!");
	};
	

	ArrayList<Character> getActors () {
		return actors;
	}
	
	public static void addActors (Character c) {
		actors.add(c);
	}
}
