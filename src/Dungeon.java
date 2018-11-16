
import java.io.PrintWriter;
import java.util.ArrayList;

public class Dungeon {
	
	
	
	Tile wall = new Tile('#', true, "#878787");
	Tile floor = new Tile('.', false, "#878787");
	Tile layout[][] = {
			{wall, floor, floor, floor, floor, wall, floor, floor, floor, wall},
			{floor, floor, floor, floor, wall, wall, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			
	};
	

	
	
	Entity entities[][] = new Entity [10][10];
	
	
	
	Dungeon() {

		for(int i = 0; i < entities[0].length; i++) {
			for (int n = 0; n < entities[1].length; n++) {
				//entities[i][n].setIcon(layout[i][n].getIcon());
				
				entities[i][n] = new Entity (layout[i][n].getIcon(), false, layout[i][n].getColor()); 				
				//entities[i][n].setColor(layout[i][n].getColor());
				//entities[i][n].setSolid(false);
			}
		}
	}
	
	
	public int getWidth() {
		return layout[0].length;
	}
	
	public int getHeight () {
		return layout[1].length;
	}
	
	public Tile getTile (int x, int y) {
		return layout[x][y];
	}

	public Tile[][] getLayout() {
		return this.layout;
	}
	
	public void changeEntities (int x, int y, char icon, String color) {
		entities[x][y].setIcon(icon);
		entities[x][y].setColor(color);
	}
	
	public void printLayout(PrintWriter writer, Player p) {
		int x = p.getX();
		int y = p.getY();
		
		for (int row = 0; row < layout[0].length; row++) {
			for (int column = 0; column < layout[1].length; column++) {
				if ((row == y) && (column == x)) {
				writer.print("<div>@</div>");
				//System.out.print("@");
				}
				else {
				writer.println("<div>" + layout[row][column].getIcon() +  "</div>");
				//System.out.print("<div>" + layout[row][column].letter +  "</div>");
				}
				//System.out.println();
			}
			
		}
		System.out.println("END");
		writer.print("x: " + p.getX() + "y: " + p.getY());
	}
	
	public void firstPrint(PrintWriter writer) {
		writer.print("<td>");
		writer.print("<div id =\"screen\" class=\"grid\">");
		for (int row = 0; row < entities[0].length; row++) {
			for (int column = 0; column < entities[1].length; column++) {
				writer.print("<div style=\"color:"  + 
						entities[row][column].getColor() + "\">" + entities[row][column].getIcon() + "</div>");
				//System.out.print(entities[row][column]);

					

			//	if (occupyCount == 0) {
				//writer.println("<div>" + layout[row][column].getIcon() +  "</div>");
				//System.out.print(layout[row][column].getIcon());
				//System.out.print("<div>" + layout[row][column].letter +  "</div>");
				//}
				
			}
			//System.out.println();
		}
		//System.out.println("END");
		
		//writer.print("x: " + actors.get(0).getX() + "y: " + actors.get(0).getY());
		//System.out.println("x: " + actors.get(0).getX() + "y: " + actors.get(0).getY());
		writer.println("</div>");
		writer.print("</td></tr>");
	}
	

	
	
}
