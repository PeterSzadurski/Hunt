package controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dungeon;
import model.Game;
import model.ItemFloor;
import model.Monster;
import model.Player;
import model.Projectile;
import monsters.*;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
    	
        super();
       // Game.Game();
        Game.menu = 0; // default menu
     // player will always be actor 0
        
        Game.setItemsFloor(Game.getDungeon()[0].getDungeonItems());
		Game.setActors(Game.getDungeon()[0].getActors());
		Game.addPlayer(player);
		//Game.addActors(monster);
		//Game.addActors(monster2);
		//Game.addActors(monster3);
		/*player.pickUp(Game.smallPotion);
		player.pickUp(Game.smallPotion);
		player.pickUp(Game.largePotion);
		player.pickUp(Game.smallPoison);
		player.pickUp(Game.smallPoison);
		player.pickUp(Game.smallPoison);
		player.pickUp(Game.smallPoison);
		player.pickUp(Game.largePotion);
		player.pickUp(Game.scrollTeleportation);
		player.pickUp(Game.scrollFireball);
		player.pickUp(Game.scrollGreaterFireball);
		player.pickUp(Game.scrollGreaterFireball);
		player.pickUp(Game.scrollGreaterFireball);
		System.out.println("Item 2: " + Game.smallPotion.getName());
		*/
		//Game.itemsFloor.add(new ItemFloor(Game.scrollMinorFrozenTime, 8, 9));
		
		
		// TODO Auto-generated constructor stub

    }
    
    
    /*Dungeon dungeon0 = new Dungeon(0);
    Dungeon dungeon1 = new Dungeon(1);
    Dungeon dungeon2 = new Dungeon(2);
    Dungeon dungeon3 = new Dungeon(3);
    Dungeon dungeon4 = new Dungeon(4);
    Dungeon dungeon5 = new Dungeon(5);
    Dungeon dungeon6 = new Dungeon(6);
    Dungeon dungeon7 = new Dungeon(7);
    Dungeon dungeon8 = new Dungeon(8);
    Dungeon dungeon9 = new Dungeon(9)
    */
	int[] location = Game.getDungeon()[Game.floor].getLocation();

  //  System.out.println("");
    
    // int[] location = Game.getDungeon()[0].getLocation();
    //int[] location = Game.getDungeon()[1].getLocation();
    
    //for (int i = 0; i < 10; i++) {
    	//Game.dungeon[i] = new Dungeon(i);
    //}
    
    
    
 //   int[] location = Game.getDungeon()[Game.floor].getLocation();
    
	int doublePress = 0;
    int counter = 0;
    boolean firstPrint = true;
   // Dungeon dungeon = new Dungeon();
    Player player = new Player("Dave", 10, 2, 10, '@', "#FFFF00"
			, this.location[1], location[0]);
    
	
    
	//Monster monster = new Monster("Goblin", 10, 2, 2, 2, Game.club, null, 'G', "#006400"
		//	, 0, 2);

	
	//Monster monster2 = new Monster("Bat", 5, 1, 3, 1, Game.club, null, 'B', "#ffffff "
		//	, 1, 2);

	
//	Monster monster3 = new Monster("Troll", 15, 3, 1, 4, Game.club, null, 'T', "#006400 "
	//		, 5, 5);
	
    
	
   // dungeon.firstPrint(out);
   //dungeon.addActor(player);
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	//	if (firstPrint) {
		//	dungeon.addActor(player);
	//		firstPrint = false;
		//}
		response.setContentType("text/html");
		int key = Integer.parseInt(request.getParameter("key"));
		PrintWriter out = response.getWriter();
		counter++;
		
		

		// check up/down
		int[] up = Game.getDungeon()[Game.floor].getUpFloor();
		int[] down = Game.getDungeon()[Game.floor].getDownFloor();
		
		
		//out.println("key press " + key + " Counter: " + counter);
		//Game.display(out, dungeon);
		
		
		
	//	if(player.getX()==up[1]&&player.getY()==up[0]) {
		//	Game.log = "Press [Enter] to go up.";
		//	System.out.println("go up man");
		//}
		Game.start();
		
		
		// controls
		switch (key) {
			case 38: // up
				switch (Game.menu) {
				case 0: // no menu open
					player.move(0, -1);
					Game.update();
					break;
				case 1: // menu navigation
					if (Game.select == 0) {
						Game.select = 3;
					}
					else {
						Game.select--;
					}
					break;
				case 2: // inner menu
					switch (Game.select) {
					case 0: // inventory navigation
						if (Game.innerSelect == 0) {
							Game.innerSelect = player.getBackpack().size() - 1;
						}
						else {
							Game.innerSelect--;
						}
						break;
					}
					break;
				case 3: // aiming
					if (Game.aiming.isRestrict()) {
						Game.aiming.restrictedMove(0, -1);
					}
					else {
						Game.aiming.move(0, -1);
					}
					break;
				}

				break;
			case 37: // left
				switch (Game.menu) {
				case 0: // no menu open
					player.move(-1, 0);
					Game.update();
					break;
				case 3: // aiming					
					if (Game.aiming.isRestrict()) {
						Game.aiming.restrictedMove(-1, 0);
					}
					else {
						Game.aiming.move(-1, 0);
					}
					break;
				}

				break;
			case 39: // right
				switch (Game.menu) {
				case 0: // no menu open
					player.move(1, 0);
					Game.update();
					break;
				case 3: // aiming
					if (Game.aiming.isRestrict()) {
						Game.aiming.restrictedMove(1, 0);
					}
					else {
						Game.aiming.move(1, 0);
					}
					break;
				}

				break;
			case 40: // down
				switch (Game.menu) {
				case 0: // no menu open
					player.move( 0, 1);
					Game.update();
					break;
				case 1: // menu navigation
					if (Game.select == 3) {
						Game.select = 0;
					}
					else {
						Game.select++;
					}
					break;
					
				case 2: // inner menu
					switch (Game.select) {
					case 0: //inventory controls
						if (Game.innerSelect == player.getBackpack().size() - 1) {
							Game.innerSelect = 0;
						}
						else {
							Game.innerSelect++;
						}
						break;
					}
					break;
					
				case 3: // aiming
					if (Game.aiming.isRestrict()) {
						Game.aiming.restrictedMove(0, 1);
					}
					else {
						Game.aiming.move(0, 1);
					}
					break;
				}

				break;
			case 73: // i
				
				switch (Game.menu) {
				
				case 0:
					// open menu
					Game.innerSelect = 0;
					Game.menu = 1;
					break;
				case 1:
					// close menu
					Game.menu = 0;
					Game.select = 0;
					Game.innerSelect = 0;
					break;
				default:
					Game.menu = 1;
					break;
				case 3:
				}
				break;
				
			case 13: // enter
				switch (Game.menu) {
				case 0:
					if (Game.onDown(player)) {
						//System.out.println("Current Floor " + Game.floor);
						Game.floor++;
						System.out.println("You descended a floor");
						Game.log  = Game.actors.get(0).getName() + " descended to floor " + (Game.getDungeon().length - Game.floor);
						player.setX(up[0]);
						player.setY(up[1]);
						Game.setItemsFloor(Game.getDungeon()[Game.floor].getDungeonItems());
						Game.setActors(Game.getDungeon()[Game.floor].getActors());
						Game.addPlayer(player);
						Game.update();						//Game.display(out);
					}
					else if (Game.onUp(player)) {
						Game.floor--;
						System.out.println("You descended a floor");
						Game.log  = Game.actors.get(0).getName() + " ascended to floor " + (Game.getDungeon().length - Game.floor);
						player.setX(down[0]);
						player.setY(down[1]);
				        Game.setItemsFloor(Game.getDungeon()[Game.floor].getDungeonItems());
						Game.setActors(Game.getDungeon()[Game.floor].getActors());
						Game.addPlayer(player);
						out.print("");
						Game.update();
					}
					break;
				
				case 1: // menu open
					Game.menu = 2;
					break;
				case 2:
					switch (Game.select) {
						default:
							Game.menu = 1;
							break;
						case 0:
							if (player.getBackpack().isEmpty()){
								
							}
							else {
							player.useFromBackpack(Game.innerSelect);
							}
							break;
					}
					break;
					
				case 3: // aiming menu
					switch (Game.aiming.getEffect()) {
						// effect on aiming teleportation
						case TELEPORT:
							// used to prevent the effect from triggering right when it is selected
							doublePress++;
								if (doublePress == 2) {
									int collideActor = -1;
									for (int i = 0; i < Game.actors.size(); i++) {
										if (((Game.aiming.x) == Game.actors.get(i).getX()) && ((Game.aiming.y)) == Game.actors.get(i).getY()) {
											collideActor = i;
										}

									}
									
									Game.aiming.setShow(false);
									if (Game.getDungeon()[Game.floor].getTile(Game.aiming.getY(), Game.aiming.getX()).isSolid()) {
										Game.log = "You have <span style=\"color:" + Game.magicEffectColor + "\">teleported</span>.. inside a wall";
										Game.actors.get(0).setCurHp(0);
									}
									else if (collideActor != -1) {
										((Player)Game.actors.get(0)).fuseWithMonster(Game.actors.get(collideActor));
										Game.actors.remove(collideActor);
									}
									else {
										Game.log = "You have <span style=\"color:" + Game.magicEffectColor + "\">teleported</span>!";
									}
									doublePress = 0;
									Game.menu = 0;
									
									Game.getDungeon()[Game.floor].changeEntities(player.getY(), player.getX(), Game.getDungeon()[Game.floor].getTile(player.getY(), player.getX()).getIcon(),
											Game.getDungeon()[Game.floor].getTile(player.getY(), player.getX()).getColor());
									
									player.setX(Game.aiming.x);
									player.setY(Game.aiming.y);
									Game.getDungeon()[Game.floor].changeEntities(player.getY(), player.getX(), player.geticon(), player.getColor());
									Game.update();
									
									
								}
							break;
						case RANGED:
							doublePress++;
							if (doublePress == 2) {
								Game.aiming.setShow(false);
								Game.log = "BURN!";
								doublePress = 0;
								Game.menu = 0;
								if ((Game.aiming.getY() > player.getY()) && Game.aiming.getX() == player.getX()) { // if direction of aim is down
									Game.projectiles.add(new Projectile("red", Game.aiming.getX(), Game.aiming.getY(), 0, 1, 20, Game.storeMagnitude));
								}
								else if ((Game.aiming.getY() < player.getY()) && (Game.aiming.getX() == Game.aiming.getX())) { // is direction of aim is up 
									Game.projectiles.add(new Projectile("red", Game.aiming.getX(), Game.aiming.getY(), 0, -1, 20, Game.storeMagnitude));
								}
								else if ((Game.aiming.getY() == player.getY()) && Game.aiming.getX() > player.getX()) { // if direction of aim is right
									Game.projectiles.add(new Projectile("red", Game.aiming.getX(), Game.aiming.getY(), 1, 0, 20, Game.storeMagnitude));
								}
								else if ((Game.aiming.getY() == player.getY()) && Game.aiming.getX() < player.getX()) { // if direction of aim is left
									Game.projectiles.add(new Projectile("red", Game.aiming.getX(), Game.aiming.getY(), -1, 0, 20, Game.storeMagnitude));
								}
								Game.getDungeon()[Game.floor].changeEntities(Game.projectiles.get(Game.projectiles.size() - 1).getY(), Game.projectiles.get(Game.projectiles.size() - 1).getX(), Game.projectiles.get(Game.projectiles.size() - 1).getIcon(), Game.projectiles.get(Game.projectiles.size() - 1).getColor());
								//Game.actors.add(projectile);
								Game.menu = 0;
							}
							break;
					default:
						break;
						
							
					}
					break; 
				}
				break;
			case 80: // p
				if (!Game.itemsFloor.isEmpty()) {
					for (int i = 0; i < Game.itemsFloor.size(); i++) {
						if(Game.itemsFloor.get(i).getX() == player.getX() && Game.itemsFloor.get(i).getY() == player.getY()) {
							player.pickUp(Game.itemsFloor.get(i).getItem());
							Game.log = "You pick up the \"" + Game.itemsFloor.get(i).getItem().getName() + "\"";
							Game.itemsFloor.remove(i);
						}
					}
				}
				break;
			// these are for debugging, will be removed
			 case 220:
			//	Game.smallPoison.use(0);;
				break;
			case 221:
			//	Game.largePotion.use(0);
				break;
			case 192:
				if (player.getBackpack().isEmpty()) {
					System.out.println("Empty");
				}
				else {
					System.out.println("it ain't");
				}
				break;
			case 16: // shift, aim test
				if (Game.menu == 0) {
					Game.menu = 3;
					Game.log = "[AIMING!]";
					
					Game.aiming.setX(player.getX());
					Game.aiming.setY(player.getY());
					Game.aiming.setShow(true);

				}
				else if (Game.menu == 3) {

					
				}
				break;
			case 17:
			//	player.getBackpack().clear();
				break;
		}
		Game.display(out);
		
		
		//dungeon.firstPrint(out);
		
		//out.print(counter);
		/*if (firstPrint) {
			dungeon.firstPrint(out, player);
			firstPrint = false;
			System.out.println("First");
		}
		else {
			dungeon.printLayout(out, player);
			System.out.println("another ");
		
		}
		//out.println(dungeon.getLayout(0,0).letter);
		//out.println("<br>");
		//out.println("<b> test </b>");
		out.close();
		*/
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
