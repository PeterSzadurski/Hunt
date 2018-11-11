import java.util.ArrayList;

public class Game {

	
	private static ArrayList<Character> actors = new ArrayList<Character>();
	
	public Game () {
		//this.player = player;
		//actors.add(player);
	}
	
	//static Player player = new Player("Dave", 10, 10, 10, '@', "#FFFF00"
		//		, 0, 1);
	
	
	// Generate weapons
	
	static Weapon club = new Weapon("club", '!', 5);
	
	
	public void createPlayer () {
		// Player player = new Player("Dave", 10, 10, 10, '@', "#FFFF00"
			//	, 0, 1);
	//	 actors.add(player);
	}
	
	public static void update(Dungeon d) {
		for (int i = 0; i < actors.size(); i++) {
		d.changeEntities(actors.get(i).getY(), actors.get(i).getX(),
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
