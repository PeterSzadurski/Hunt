

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
     // player will always be actor 0
		Game.addActors(player);
		
		Game.addActors(monster);
		Game.addActors(monster2);
        // TODO Auto-generated constructor stub
    }
    int counter = 0;
    boolean firstPrint = true;
    Dungeon dungeon = new Dungeon();
    Player player = new Player("Dave", 10, 2, 10, '@', "#FFFF00"
			, 0, 1);
    
	
    
	Monster monster = new Monster("Goblin", 10, 1, 1, 1, Game.club, null, 'G', "#006400"
			, 0, 2);
	
	
	Monster monster2 = new Monster("Bat", 10, 2, 2, 1, Game.club, null, 'B', "#ffffff "
			, 1, 2);
	
    
	
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
		
		

		
		
		//out.println("key press " + key + " Counter: " + counter);
		
		switch (key) {
			case 38: // up
				player.move(0, -1, dungeon);
				break;
			case 37: // left
				player.move(-1, 0, dungeon);
				break;
			case 39: // right
				player.move(1, 0, dungeon);
				break;
			case 40: // down
				player.move( 0, 1, dungeon);
				break;
			case 75: // k
				//player.setY(2);
				Game.menu = 1;
				//player.setX(0);
				break;
		}
		Game.update(dungeon);
		Game.display(out, dungeon);
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
