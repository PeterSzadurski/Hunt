import java.io.PrintWriter;
import java.util.ArrayList;

public class Game {

	public static ArrayList<Character> actors = new ArrayList<Character>();

	public Game() {
		// this.player = player;
		// actors.add(player);
	}

	// static Player player = new Player("Dave", 10, 10, 10, '@', "#FFFF00"
	// , 0, 1);
	static String log = ("");
	static int menu = 0;

	// Generate weapons

	static Weapon club = new Weapon("club", '!', 5);

	public void createPlayer() {
		// Player player = new Player("Dave", 10, 10, 10, '@', "#FFFF00"
		// , 0, 1);
		// actors.add(player);
	}

	public static double turnRate(int index) {

		return (double) actors.get(index).getAgility() / (double) actors.get(0).getAgility();
	}

	public static void update(Dungeon d) {

		int turn = 1;

		for (int i = 0; i < actors.size(); i++) {
			d.changeEntities(actors.get(i).getY(), actors.get(i).getX(), actors.get(i).geticon(),
					actors.get(i).getColor());
		}
		System.out.println("UPDATE! " + actors.size());

		for (int n = 1; n < actors.size(); n++) {
			System.out.println("First: " + turnRate(n) + " " + actors.get(n).getName());
			System.out.println(actors.get(n).getName() + " AGI = " + actors.get(n).getAgility());

			// find how close the monster is to move in a turn
			((Monster) actors.get(n)).setTurnCount(((Monster) actors.get(n)).getTurnCount() + turnRate(n));

			if (((Monster) actors.get(n)).getTurnCount() >= turn) {
				for (int t = 0; t < turnRate(n); t++) {
					System.out.println(turnRate(n) + " " + actors.get(n).getName());
					int rand = (int) (Math.random() * 4) + 0;
					// System.out.println("choice: " + rand );

					switch (rand) {
					case 0:
						actors.get(n).move(0, 1, d);
						break;
					case 1:
						actors.get(n).move(0, -1, d);
						break;
					case 2:
						actors.get(n).move(1, 0, d);
						break;
					case 3:
						actors.get(n).move(-1, 0, d);
						break;
					default:
						// stand still
						break;
					}
				}
				((Monster) actors.get(n)).setTurnCount(0);
			}
			
	
			

		}
		// }

	};

	ArrayList<Character> getActors() {
		return actors;
	}

	public static void addActors(Character c) {
		actors.add(c);
	}

	public static void display(PrintWriter writer, Dungeon d) {

		switch (menu) {
		case 0:
			break;
		case 1:
			writer.print("<tr><td> menu </td>");
			break;
		default:
			writer.print("<tr>");
			break;
		}
		d.firstPrint(writer);
		// draw player health
		writer.println("<tr><td></td><td>&thinsp;&thinsp;" + actors.get(0).getName() + " | HP: "
				+ actors.get(0).getCurHp() + "/" + actors.get(0).getHp() + " | EXP: 0/10 " + "</td></tr>");
		// draw game log
		writer.println("<tr><td></td><td>&thinsp;&thinsp;" + log + "</td></tr>");
	}
}
