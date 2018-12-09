package model;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Game {

	public static ArrayList<Character> actors = new ArrayList<Character>();
	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public Game() {
		// this.player = player;
		// actors.add(player);
	}

	// static Player player = new Player("Dave", 10, 10, 10, '@', "#FFFF00"
	// , 0, 1);
	public static String log = ("");
	public static int storeMagnitude;
	public static int menu = 0;
	public static int select = 0;
	public static int innerSelect = 0;
	static boolean ifUsed = false;
	static final String buffColor = "#00baff";
	static final String debuffColor = "#ff0000";
	public static final String magicEffectColor = "#9e00d8";

	// Generate weapons

	public static Weapon club = new Weapon("club", '!', 5);
	static Weapon rustyDagger = new Weapon("Rusty Dagger", '!', 2);

	public static Aiming aiming = new Aiming(0, 0);

	// Generate armors

	static Armor clothes = new Armor("Clothes", '+', 1);
	static Armor steelPlate = new Armor("Steel Plate", '+', 20);

	// Generate items
	public static Item smallPotion = new Item("Small Potion", 'i',
			"<span style = \"color:" + buffColor + "\">Restores</span> 10HP", 10, Effect.RESTORE_HEALTH, null);
	public static Item largePotion = new Item("Large Potion", 'i',
			"<span style = \"color:" + buffColor + "\">Restores</span> 50HP", 50, Effect.RESTORE_HEALTH, null);
	public static Item smallPoison = new Item("Small Poison", 'i',
			"<span style = \"color:" + debuffColor + "\">Damages</span> 15HP", 15, Effect.DAMAGE_HEALTH, null);
	public static Item scrollTeleportation = new Item("Scroll of Teleportation", 'i',
			"<span style = \"color: " + magicEffectColor + "\">Teleports</span> the user", 0, Effect.TELEPORT, null);
	public static Item scrollFireball = new Item("Scroll of Fireball", 'i',
			"Scorches the enemy for <span style=\"color:" + debuffColor + "\">25 damage</span>", -25, Effect.RANGED,
			Effect.DAMAGE_HEALTH);
	public static Item scrollGreaterFireball = new Item("Scroll of Greater Fireball", 'i',
			"Scorches the enemy for <span style=\"color:" + debuffColor + "\">100 damage</span>", -100, Effect.RANGED,
			Effect.DAMAGE_HEALTH);

	// effects methods
	public static void effects(int target, int magnitude, Effect effect) {
		// boolean ifUsed = false;
		switch (effect) {
		case RESTORE_HEALTH:
			if (Game.actors.get(target).getHp() == Game.actors.get(target).getCurHp()) {

				// show log if player
				if (Game.actors.get(target) instanceof Player) {
					Game.log = ("You are already fully healed.");
					ifUsed = false;
				}

				System.out.println("invalid");
			} else {
				// heal actor

				// show log if player
				if (Game.actors.get(target) instanceof Player) {
					Game.log = ("You restore " + (magnitude) + "HP");
				}
				System.out.println("heal");
				Game.actors.get(target).setCurHp(Game.actors.get(target).getCurHp() + magnitude);
				// makes sure the hp does not overflow
				if (Game.actors.get(target).getCurHp() > Game.actors.get(target).getHp()) {
					Game.actors.get(target).setCurHp(Game.actors.get(target).getHp());
				}
				ifUsed = true;
			}
			break;
		case DAMAGE_HEALTH:
			Game.actors.get(target).setCurHp(Game.actors.get(target).getCurHp() - magnitude);
			System.out.println("Cur hp " + actors.get(target).getCurHp());
			System.out.println("damage");
			ifUsed = true;
			break;
		case TELEPORT:
			Game.aiming.setRestrict(false);
			Game.menu = 3;
			Game.log = "[AIMING!]";

			Game.aiming.setX(Game.actors.get(target).getX());
			Game.aiming.setY(Game.actors.get(target).getY());
			Game.aiming.setShow(true);
			Game.aiming.setEffect(Effect.TELEPORT);
			ifUsed = true;
			break;
		case RANGED:
			Game.aiming.setRestrict(true);
			Game.menu = 3;
			Game.log = "[TARGETING]";
			Game.aiming.setX(Game.actors.get(target).getX());
			Game.aiming.setY(Game.actors.get(target).getY());
			Game.aiming.setShow(true);
			Game.aiming.setEffect(Effect.RANGED);
			storeMagnitude = magnitude;
			ifUsed = true;
			break;
		default:
			ifUsed = false;
			System.out.println("PLACEHOLDER");
			break;
		}
	}

	public void createPlayer() {
		// Player player = new Player("Dave", 10, 10, 10, '@', "#FFFF00"
		// , 0, 1);
		// actors.add(player);
	}

	public static double turnRate(int agi) {

		return (double) agi / (double) actors.get(0).getAgility();
	}

	public static void update(Dungeon d) {

		int turn = 1;

		for (int i = 0; i < actors.size(); i++) {
			d.changeEntities(actors.get(i).getY(), actors.get(i).getX(), actors.get(i).geticon(),
					actors.get(i).getColor());
		}
		System.out.println("UPDATE! " + actors.size());

		if (!projectiles.isEmpty()) {
			for (int p = 0; p < projectiles.size(); p++) {
				// move projectiles
				projectiles.get(p)
						.setTurnCount((projectiles.get(p)).getTurnCount() + turnRate(projectiles.get(p).agility));
				if (projectiles.get(p).getTurnCount() >= turn) {
					for (int t = 0; t < turnRate(projectiles.get(p).getAgility()); t++) {
						projectiles.get(p).move(d);
						System.out.println("Move Ball");
						// if (projectiles.get(p).isSetDestroy()) {
						// projectiles.remove(p);
					}
				}
				if (projectiles.get(p).isSetDestroy()) {
					projectiles.remove(p);
				}

			}
		}

		// check for dead monsters
		for (int n = 1; n < actors.size(); n++) {
			if (actors.get(n).getCurHp() <= 0) {
				d.changeEntities(actors.get(n).getY(), actors.get(n).getX(),
						d.getTile(actors.get(n).getY(), actors.get(n).getX()).getIcon(),
						d.getTile(actors.get(n).getY(), actors.get(n).getX()).getColor());
				((Player) actors.get(0)).gainExp(((Monster) actors.get(n)).getExpOnKill());
				actors.remove(n);
			}

		}

		for (int n = 1; n < actors.size(); n++) {
			// System.out.println("First: " + turnRate(n) + " " + actors.get(n).getName());
			// System.out.println(actors.get(n).getName() + " AGI = " +
			// actors.get(n).getAgility());

			// find how close the monster is to move in a turn
			((Monster) actors.get(n))
					.setTurnCount(((Monster) actors.get(n)).getTurnCount() + turnRate(actors.get(n).getAgility()));

			if (((Monster) actors.get(n)).getTurnCount() >= turn) {
				for (int t = 0; t < turnRate(actors.get(n).getAgility()); t++) {
					// System.out.println(turnRate(n) + " " + actors.get(n).getName());
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

	public static void start(Dungeon d) {

		for (int i = 0; i < actors.size(); i++) {
			d.changeEntities(actors.get(i).getY(), actors.get(i).getX(), actors.get(i).geticon(),
					actors.get(i).getColor());
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

		String insert = "";

		switch (menu) {
		case 0: // no menu
			writer.print("<tr>");
			insert = "";
			break;
		case 1:
			insert = "<td></td>";
			switch (select) {
			case 0:
				// inventory selected
				writer.print(
						"<tr><td>[Inventory]<br>&nbsp;Magic&nbsp;<br>&nbsp;Stats&nbsp;<br>&nbsp;Sign Out&nbsp;</td>");
				break;
			case 1:
				// magic selected
				writer.print(
						"<tr><td>&nbsp;Inventory&nbsp;<br>[Magic]<br>&nbsp;Stats&nbsp;<br>&nbsp;Sign Out&nbsp;</td>");
				break;
			case 2:
				// Stats selected
				writer.print(
						"<tr><td>&nbsp;Inventory&nbsp;<br>&nbsp;Magic&nbsp;<br>[Stats]<br>&nbsp;Sign Out&nbsp;</td>");
				break;
			case 3:
				// sign out selected
				writer.print(
						"<tr><td>&nbsp;Inventory&nbsp;<br>&nbsp;Magic&nbsp;<br>&nbsp;Stats&nbsp;<br>[Sign Out]</td>");
				break;
			}
			break;
		case 2:
			insert = "<td></td>";
			switch (select) {

			case 0: // inventory screen
				writer.print("<tr><td>" + ((Player) actors.get(0)).displayBackpack() + "</td>");
				break;
			case 2:
				writer.print("<tr><td>" + actors.get(0).toString() + "</td>");
				break;
			default:
				writer.print("<tr><td>PLACEHOLDER</td>");
				break;
			}
			break;
		default:
			writer.print("<tr>");
			insert = "";
			break;
		}
		d.firstPrint(writer);
		// draw game hud
		writer.println("<tr>" + insert + "<td>&thinsp;&thinsp;" + actors.get(0).getName() + " | LV: "
				+ ((Player) actors.get(0)).getLevel() + " | HP: " + actors.get(0).getCurHp() + "/"
				+ actors.get(0).getHp() + " | EXP: " + ((Player) actors.get(0)).getExp() + "/"
				+ ((Player) actors.get(0)).getExpForNextLevel() + "</td></tr>");
		// draw game log
		writer.println("<tr>" + insert + "<td>&thinsp;&thinsp;" + log + "</td></tr>");
	}
}
