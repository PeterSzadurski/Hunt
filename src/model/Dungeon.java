package model;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Dungeon {
	
	private int dungeonID;
	
	Tile wall = new Tile(' ', true, "#878787", "wall");
	Tile floor = new Tile('.', false, "#878787", "floor");
	Tile path = new Tile('#', false, "#878787", "path");
	Tile layout[][] = {
			{wall, floor, floor, floor, floor, wall, floor, floor, floor, wall},
			{floor, floor, floor, floor, wall, wall, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{wall, wall, wall, wall, path, path, wall, wall, wall, wall},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor},
			{floor, floor, floor, floor, floor, floor, floor, floor, floor, floor}
	};
	
	Entity entities[][] = new Entity [10][10];
	
	public Dungeon() {

		for(int i = 0; i < entities[0].length; i++) {
			for (int n = 0; n < entities[1].length; n++) {
				//entities[i][n].setIcon(layout[i][n].getIcon());
				
				entities[i][n] = new Entity (layout[i][n].getIcon(), false, layout[i][n].getColor(), layout[i][n].getType()); 				
				//entities[i][n].setColor(layout[i][n].getColor());
				//entities[i][n].setSolid(false);
			}
		}
	} // end of no param constructor
	
	// constructor for using layout from database
	public Dungeon(ArrayList<String> layout) {
		
		ArrayList<ArrayList<Tile>> dungeon = new ArrayList<ArrayList<Tile>>();
		
		// loop through layout
		for(int i = 0; i < layout.size(); i++) {
			// get row from passed arraylist
			String row = (String) layout.get(i);
			
			// split row into array on ","
			String[] array = row.split(",");
			
			// tile arraylist to hold row values changed to respective tile type (wall or floor)
			ArrayList<Tile> rowTiles = new ArrayList<Tile>();
			
			// loop through strings in array
			for(String str: array) {
				
				// check if string equals wall
				if (str.equals("wall")) {
					
					// create a wall tile
					Tile wall = new Tile('#', true, "#878787", "wall");
					
					// add wall tile to tile arraylist
					rowTiles.add(wall);
					
				// check if string equals floor 
				} else if(str.equals("floor")) {
					
					// create a floor tile
					Tile floor = new Tile('.', false, "#878787", "floor");
					
					// add floor tile to tile arraylist
					rowTiles.add(floor);
					
				}
			}
			
			dungeon.add(rowTiles);
		}
		
		for(int i = 0; i < dungeon.size(); i++) {
			System.out.println("");
			for(int j = 0; j < dungeon.get(i).size(); j++) {
				System.out.print(dungeon.get(i).get(j).toString());
			}
		}
		
	} // end of dungeon(ArrayList layout) constructor
	
	
	public int getDungeonID() {
		return dungeonID;
	}

	public void setDungeonID(int dungeonID) {
		this.dungeonID = dungeonID;
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
				if ((Game.aiming.getX() == column) && (Game.aiming.getY() == row) && (Game.aiming.isShow())) {
					writer.print("<div>" + Game.aiming.geticon() + "</div>");
				}
				else {
					writer.print("<div style=\"color:"  + 
						entities[row][column].getColor() + "\">" + entities[row][column].getIcon() + "</div>");
					}
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

	public String getLayoutAsStr() {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
}
