package model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import monsters.*;

public class Dungeon {
	
	//Dungeon[] dungeon = new Dungeon[10];// array of dungeons
	
	
	Tile path = new Tile('#', false, "#878787", "path");
	Tile floor = new Tile('.', false, "#878787", "floor");
	Tile wall = new Tile(' ',true, "#878787", "wall");
	Tile upFloor = new Tile('%',false, "#009900", "upFloor");//green
	Tile downFloor = new Tile('%',false, "#000099", "downFloor");//blue
	Tile layout[][] = new Tile[50][50];

	Entity entities[][] = new Entity [50][50];
	
	public Dungeon(int index) {
	
		for(int i = 0; i < layout[0].length; i++) {
			for (int n = 0; n < layout[1].length; n++) {
			
					layout[i][n]=wall;
				
			}
		}
		int rooms=0;
		int width;
		int length;
		int startRow;
		int startCol;
		do{
//			width=0;
//			length=0;
//			startRow=0;
//			startCol=0;
			boolean emptyRoom = true;
			do {
				emptyRoom = true;
				Random rnd = new Random();
				width = rnd.nextInt(11-4+1)+4;
				length = rnd.nextInt(11-4+1)+4;
				startCol = rnd.nextInt(50)+1;
				startRow = rnd.nextInt(50)+1;
				if(startCol+width>=50||startRow+length>=50) {
					emptyRoom=false;
				}else {
					for(int i = startCol; i < (startCol+width); i++) {
						for (int n = startRow; n < (startRow+length); n++) {
							
							if(layout[i][n]==floor) {
								emptyRoom=false;
							}
						}
					}
				}
				
				
			}while(emptyRoom==false);
			//System.out.println(width);
			//System.out.println(length);
			//System.out.println(startCol);
			//System.out.println(startRow);
			
			//create room
			//for(int i = startCol; i <= (startCol+width); i++) {
			//	layout[i][startCol]=wall;
			//	layout[i][startCol+width]=wall;
			//}
			//for(int i = startRow; i <= (startRow+length); i++) {
			//	layout[startRow][i]=wall;
			//	layout[startRow+length][i]=wall;
			//}
			if(startCol+width<50&&startRow+length<50) {
				for(int i = startCol; i <= (startCol+width); i++) {
					for (int n = startRow; n <= (startRow+length); n++) {
					
						
						if(layout[i][n]!=wall||layout[i][n]!=floor) {
							layout[i][n]=floor;
						}
						
						
						
					}
				}
			}
			else if(startCol+width>=50||startRow+length>=50) {
				rooms--;
			}
			rooms++;
		}while(rooms<25);
		rooms=0;
		//create vertical paths
		int paths=0;
		do {
			boolean room = true;
			do {
				room=true;
				Random rnd = new Random();
				
				length = 5;
				startCol = rnd.nextInt(50)+1;
				startRow = rnd.nextInt(50)+1;
				
				if(startCol+length>=50||startRow+length>=50) {
					room=false;
				}
//				else {
//					for(int i = startCol; i < (startCol+length); i++) {
//						for (int n = startRow; n < (startRow+length); n++) {
//							
//							if(layout[i][n]!=floor) {
//								room=false;
//							}
//						}
//					}
//				}
			}while(room==false);
			
			
			int row=startRow;
			for(int n = startCol; n < 50; n++) {
				if(layout[n][row]==floor) {
					for(int i = startCol; i < 50; i++) {
						if(layout[i][row]==wall) {
						
							for(int x = i; x < 50; x++) {
								if(layout[x][row]==floor) {
									
									for(int col = i; col < x; col++) {
										layout[col][row]=path;
									}
									break;
								}
								else if(x==49){
									paths--;
								}
							}
							break;
						}
						else if(i==49){
							paths--;
						}
					}
					break;
				}
				else if(n==49){
					paths--;
				}
			}
			paths++;
		}while(paths<30);
		
		//horizontal paths
		paths=0;
		do {
			boolean room = true;
			do {
				room=true;
				Random rnd = new Random();
				
				length = 5;
				startCol = rnd.nextInt(50)+1;
				startRow = rnd.nextInt(50)+1;
				
				if(startCol+length>=50||startRow+length>=50) {
					room=false;
				}
//				else {
//					for(int i = startCol; i < (startCol+length); i++) {
//						for (int n = startRow; n < (startRow+length); n++) {
//							
//							if(layout[i][n]!=floor) {
//								room=false;
//							}
//						}
//					}
//				}
			}while(room==false);
			
			
			int col=startCol;
			for(int n = startRow; n < 50; n++) {
				if(layout[col][n]==floor) {
					for(int i = startRow; i < 50; i++) {
						if(layout[col][i]==wall) {
						
							for(int x = i; x < 50; x++) {
								if(layout[col][x]==floor) {
									
									for(int row = i; row < x; row++) {
										layout[col][row]=path;
									}
									break;
								}
								else if(x==49){
									paths--;
								}
							}
							break;
						}
						else if(i==49){
							paths--;
						}
					}
					break;
				}
				else if(n==49){
					paths--;
				}
			}
			paths++;
		}while(paths<30);
		int[] up = getLocation();
		int[] down = getLocation();
		layout[up[0]][up[1]] = upFloor;
		
		if (index != 9) {
			layout[down[0]][down[1]] = downFloor;
		}
		else {
			layout[down[0]][down[1]] = floor;
		}
		
		
		
		
		for(int i = 0; i < entities[0].length; i++) {
			for (int n = 0; n < entities[1].length; n++) {
				//entities[i][n].setIcon(layout[i][n].getIcon());
				
				entities[i][n] = new Entity (layout[i][n].getIcon(), layout[i][n].isSolid() , layout[i][n].getColor()); 				
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
				System.out.print("<div>" + layout[row][column].getIcon() +  "</div>");
				}
				//System.out.println();
			}
			
		}
		System.out.println("END");
		writer.print("x: " + p.getX() + "y: " + p.getY());
	}
	
	public void firstPrint(PrintWriter writer) {
		writer.print("<tr><td>");
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
	

	public int[] getLocation() {
		int location[] = new int[2];
		boolean room=true;
		int col=0;
		int row=0;
		do {
			room=true;
			Random rnd = new Random();
			col = rnd.nextInt(48)+1;
			row = rnd.nextInt(48)+1;
			if(layout[row][col]==floor&&(layout[row][col]!=wall||layout[row][col]!=path)) {
				room=true;
			}else {
				room=false;
			}
		}while(room==false);
		location[1]=col;
		location[0]=row;
		
		return location;
	}
	
	
	//method to get floor up location
	public int[] getUpFloor() {
		int[] up= {0,0};
		for(int i = 0; i < 50; i++) {
			for (int n = 0; n < 50; n++) {
				if(layout[i][n]==upFloor) {
					up[0]=i;
					up[1]=n;
				}
			}
		}
		return up;
	}
	//method to get floor down location
	public int[] getDownFloor() {
		int[] down= {0,0};
		for(int i = 0; i < 50; i++) {
			for (int n = 0; n < 50; n++) {
				if(layout[i][n]==downFloor) {
					down[0]=i;
					down[1]=n;
				}
			}
		}
		return down;
	}
	
	public void populate () {
		int rand;
		int itemRange;
		int[] location = Game.getDungeon()[Game.floor].getLocation();
		switch (Game.floor) {
		// place monsters and in the dungeon
		case 0:
			for (int i = 0; i < 10; i++) {
				rand = (int) (Math.random() * 6) + 0;
				switch (rand) {
				default:
					location = Game.getDungeon()[Game.floor].getLocation();
					Game.addActors(new Bat(location[1], location[0]));
					break;
				case 0:
					location = Game.getDungeon()[Game.floor].getLocation();
					Game.addActors(new Goblin(location[1], location[0]));
					break;
				case 2:
					location = Game.getDungeon()[Game.floor].getLocation();
					Game.addActors(new Goblin(location[1], location[0]));
					break;
				case 3: 
					location = Game.getDungeon()[Game.floor].getLocation();
					Game.addActors(new Bat(location[1], location[0]));
					break;
				case 4:
					location = Game.getDungeon()[Game.floor].getLocation();
					Game.addActors(new Bat(location[1], location[0]));
					break;
				case 5:
					location = Game.getDungeon()[Game.floor].getLocation();
					Game.addActors(new Troll(location[1], location[0]));
					break;
				}
			}
			
			itemRange = (int) (Math.random() * 5) + 2;
			System.out.println("Setting up items");
			for (int i = 0; i < itemRange; i++) {
				System.out.println("Placing itemes");
				int pick = (int) (Math.random() * (Game.itemTable().length -1)) + 0;
				location = Game.getDungeon()[Game.floor].getLocation();
				Game.itemsFloor.add(new ItemFloor(Game.itemTable()[pick], location[1], location[0]));
				//Game.itemsFloor.add(new ItemFloor Game.smallPotion ,location[1], location[0] );
			}
			
			break;
		default:
			break;
		}
	}
}
