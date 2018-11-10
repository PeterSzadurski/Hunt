import java.util.Scanner;
public class CombatTester {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		// Character creation (yes I know there's no error handling here)
		int totalPoints = 5;
		System.out.println("5 points total");
		System.out.print("Points to Strength: ");
		int str = in.nextInt();
		totalPoints-=str;
		System.out.println(totalPoints + " points left" );
		System.out.print("Points to Agility: ");
		int agi = in.nextInt();
		totalPoints-=agi;
		System.out.println(totalPoints + " points left" );
		System.out.print("Points to Vitality: ");
		int vit = in.nextInt();
		totalPoints-=str;
		
		
		Player player = new Player("Stabby McStabberson", str, agi, vit);
		
		// Make a monster
		Weapon club = new Weapon("club", '!', 5);
		Monster monster = new Monster("goblin", 10, 1, 1, 1, club, null);
		
		// print starting stats
		System.out.println("--------------------------------------------------");
		System.out.println("Pre-Battle Stats");
		System.out.println("\n" + player.toString());
		System.out.println("\n" + monster.toString() + "\n");
		System.out.println("--------------------------------------------------");
		
		/*
		 * In actual combat, movement would need to be taken into account.
		 * The character would attack a square, and the attack method would only be called
		 * if there was a character standing on that square.
		 * This tester is assuming that the monster and player are standing next to each other
		 * beating on each other until one of them dies.
		 */
		int damage = 0;
		boolean dead = false;
		while(!dead) {
			// monster turn
			System.out.println("The monster attacks.");
			damage = monster.attack(player.getAgility());
			if(damage!=0) {
				System.out.println("The monster dealt " + damage + " damage.");
				dead = player.takeDamage(damage);
				System.out.println("Player HP: " + player.getCurHp() + "/" + player.getHp());
				damage = 0;
			} else {
				System.out.println("The monster missed.");
			}
			
			System.out.println();
			
			// player turn
			if (!dead) {
				System.out.println(player.getName() + " attacks.");
				damage = player.attack(monster.getAgility());
				if(damage!=0) {
					System.out.println(player.getName() + " dealt " + damage + " damage.");
					dead = monster.takeDamage(damage);
					System.out.println("Monster HP: " + monster.getCurHp() + "/" + monster.getHp());
					damage = 0;
				} else {
					System.out.println(player.getName() + " missed.");
				}
			}
			System.out.println();
		}
		
		System.out.println("---------------------------------------------------");
		System.out.println("Post-Battle Stats:");
		System.out.println(player.toString());
		System.out.println(monster.toString());
		
		System.out.println();
		boolean levelUp = false;
		if (player.getCurHp() == 0) {
			System.out.println("You died.");
		} else if (monster.getCurHp() == 0) {
			int exp = monster.getExpOnKill();
			levelUp = player.gainExp(exp);
			System.out.println("You killed the monster! You gain " + exp + " experience points.");
		}
		
		// Level up
		if(levelUp) {
			System.out.println("---------------------------------------------------");
			System.out.println("You leveled up! Choose a stat to increase (s, a, v): ");
			String stat = in.next().toLowerCase();
			if(stat.equals("s")) {
				player.levelUp("strength");
			} else if (stat.equals("a")) {
				player.levelUp("agility");
			} else if (stat.equals("v")) {
				player.levelUp("vitality");
			}
			
			System.out.println(player.toString());
		}
		
	}

}
