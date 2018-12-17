package model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import dao.DungeonDAO;
import dao.MonsterDAO;
import monsters.*;
import model.Game;

public class Dungeon {
	
	//Dungeon[] dungeon = new Dungeon[10];// array of dungeons
	
	
	Tile path = new Tile('#', false, "#878787", "path");
	Tile floor = new Tile('.', false, "#878787", "floor");
	Tile wall = new Tile(' ',true, "#878787", "wall");
	Tile upFloor = new Tile('%',false, "#009900", "upFloor");//green
	Tile downFloor = new Tile('%',false, "#000099", "downFloor");//blue
	Tile layout[][] = new Tile[50][50];

	Entity entities[][] = new Entity [50][50];
	
	ArrayList<Character> actors = new ArrayList<Character>();
	ArrayList <ItemFloor> dungeonItems = new ArrayList<ItemFloor>();
	
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
				
				entities[i][n] = new Entity (layout[i][n].getIcon(), layout[i][n].isSolid() , layout[i][n].getColor(), null); 				
				//entities[i][n].setColor(layout[i][n].getColor());
				//entities[i][n].setSolid(false);
			}
		}
		
		/*
		 * Adds dungeon layout to database
		 */
		DungeonDAO dao = new DungeonDAO();
		ArrayList<String> dLayout = new ArrayList<String>();
		
		for(int i = 0; i < layout.length; i++) {
			String row = "";
			for(int j = 0; j < layout[i].length; j++) {
				if(j == layout[i].length - 1) {
					row += layout[i][j].toString();
				} else {
					row += layout[i][j].toString() + ",";
				}
			}
			dLayout.add(row);
		}
		dao.addDungeon(dLayout, index);
		
		
		// adds monsters to dungeon 
		populate(index);
		
		// add monsters to database
		MonsterDAO mDao = new MonsterDAO();
		for (int i = 0; i < this.actors.size(); i++) {
			System.out.println(i + ": " + this.actors.get(i).toString());
			//if(this.actors.get(i) instanceof Bat) {
			mDao.addMonster((Monster) this.actors.get(i));
				
//			} else if (this.actors.get(i) instanceof Troll) {
//				mDao.addMonster((Monster) this.actors.get(i));
//				
//			} else if (this.actors.get(i) instanceof Assassin) {
//				mDao.addMonster((Monster) this.actors.get(i));
//				
//			} else if (this.actors.get(i) instanceof Duelist) {
//				mDao.addMonster((Monster) this.actors.get(i));
//				
//			} else if (this.actors.get(i) instanceof Emperor) {
//				mDao.addMonster((Monster) this.actors.get(i));
//				
//			} else if (this.actors.get(i) instanceof Goblin) {
//				mDao.addMonster((Monster) this.actors.get(i));
//				
//			} else if (this.actors.get(i) instanceof Knight) {
//				mDao.addMonster((Monster) this.actors.get(i));
//				
//			} else if (this.actors.get(i) instanceof Ninja) {
//				mDao.addMonster((Monster) this.actors.get(i));
//				
//			} else if (this.actors.get(i) instanceof RoyalGuard) {
//				mDao.addMonster((Monster) this.actors.get(i));
//				
//			} else if (this.actors.get(i) instanceof Vampire) {
//				mDao.addMonster((Monster) this.actors.get(i));
//				
//			} 
		}
		
	}
	
	// constructor for using layout from database
	public Dungeon(ArrayList<String> lo, boolean is) {
			
		Tile dungeonLayout[][] = new Tile[50][50];
			
		// loop through layout
		for(int i = 0; i < lo.size(); i++) {
			// get row from passed arraylist
			String row = (String) lo.get(i);
					
			// split row into array on ","
			String[] array = row.split(",");
					
			// tile arraylist to hold row values changed to respective tile type (wall or floor)
			Tile[] rowTiles = new Tile[50];
					
			// loop through 'Tile' strings in array
			for(int j = 0; j < array.length; j++) {
						
				// check if string equals wall
				if (array[j].equals("wall")) {
							
					// create a wall tile
					Tile wall = new Tile(' ', true, "#878787", "wall");
								
					// add wall tile to tile arraylist
					rowTiles[j] = wall;
							
				// check if string equals floor 
				} else if(array[j].equals("floor")) {
							
					// create a floor tile
					Tile floor = new Tile('.', false, "#878787", "floor");
								
					// add floor tile to tile arraylist
					rowTiles[j] = floor;
							
				} else if(array[j].equals("path")) {
					// create a path tile
					Tile path = new Tile('#', false, "#878787", "path");
								
					// add path tile to tile arraylist
					rowTiles[j] = path;
							
				} else if(array[j].equals("upFloor")) {
					// create a upFloor tile
					Tile upFloor = new Tile('%',false, "#009900", "upFloor");//green
							
					// add upFloor tile to tile arraylist
					rowTiles[j] = upFloor;
							
				} else if(array[j].equals("downFloor")) {
					// create a upFloor tile
					Tile downFloor = new Tile('%',false, "#000099", "downFloor");//blue
								
					// add downFloor tile to tile arraylist
					rowTiles[j] = downFloor;
				}
			} // end of inside for loop
				
			// add rowTiles array to dungeon array
			dungeonLayout[i] = rowTiles;
				
		} // end of outside for loop
			
	} // end of dungeon(ArrayList layout) constructor
	
	
	public ArrayList<Character> getActors() {
		return actors;
	}


	public void setActors(ArrayList<Character> actors) {
		this.actors = actors;
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
	
	public void populate(int index) {
		int rand;
		int itemRange;
		int[] location = getLocation();
		switch (index) {
		// place monsters and in the dungeon
		case 0:
			for (int i = 0; i < 10; i++) {
				rand = (int) (Math.random() * 6) + 0;
				switch (rand) {
				default:
					location = getLocation();
					actors.add(new Bat(location[1], location[0], index));
					break;
				case 0:
					location = getLocation();
					actors.add(new Goblin(location[1], location[0], index));
					break;
				case 2:
					location = getLocation();
					actors.add(new Goblin(location[1], location[0], index));
					break;
				case 3: 
					location = getLocation();
					actors.add(new Bat(location[1], location[0], index));
					break;
				case 4:
					location = getLocation();
					actors.add(new Bat(location[1], location[0], index));
					break;
				case 5:
					location = getLocation();
					actors.add(new Troll(location[1], location[0], index));
					break;
				}
			}
			
			itemRange = (int) (Math.random() * 5) + 2;
			System.out.println("Setting up items");
			//System.out.println("Item: " +
			//Items.smallPotion.getName());
			//Item[] itemTable = Game.itemTable(0);
			Item[] itemTable = {Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.club, Items.club, Items.lightLeather, Items.lightLeather, Items.lightLeather,
					Items.smallPoison, Items.chainmail, Items.chainmail, Items.ironPlate, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.potionMinorOfImprovement, Items.scrollGreaterFireball, Items.scrollGreaterFireball,
					Items.rustyDagger, Items.rustyDagger, Items.rustyDagger, Items.rustyDagger, Items.rustyDagger, Items.scrollTeleportation};
					
			//System.out.println("Length: " + itemTable.length);
			for (int i = 0; i < itemRange; i++) {
				System.out.println("Placing itemes");

				int pick = (int) (Math.random() * (itemTable.length -1)) + 0;
				System.out.println("Pick: " + pick);
				location = getLocation();
				//System.out.println(Game.itemTable(0)[0].getName());
				dungeonItems.add(new ItemFloor(itemTable[pick], location[1], location[0]));
				//System.out.println("Added: " + dungeonItems.get(0).getItem().getName());
				//Game.itemsFloor.add(new ItemFloor Game.smallPotion ,location[1], location[0] );
			}
			
			
			
			break;
		case 1:
			for (int i = 0; i < 10; i++) {
				rand = (int) (Math.random() * 6) + 0;
				switch (rand) {
				default:
					location = getLocation();
					actors.add(new Bat(location[1], location[0], index));
					break;
				case 0:
					location = getLocation();
					actors.add(new Goblin(location[1], location[0], index));
					break;
				case 2:
					location = getLocation();
					actors.add(new Goblin(location[1], location[0], index));//
					break;
				case 3: 
					location = getLocation();
					actors.add(new Bat(location[1], location[0], index));
					break;
				case 4:
					location = getLocation();
					actors.add(new Bat(location[1], location[0], index));
					break;
				case 5:
					location = getLocation();
					actors.add(new Troll(location[1], location[0], index));
					break;
				}
			}
			
			itemRange = (int) (Math.random() * 5) + 2;
		//	System.out.println("Setting up items");
			Item[] itemTable1 = {Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.club, Items.club, Items.lightLeather, Items.lightLeather, Items.lightLeather,
					Items.smallPoison, Items.chainmail, Items.chainmail, Items.ironPlate, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.potionMinorOfImprovement, Items.potionMinorOfImprovement, Items.rustyDagger, Items.rustyDagger, Items.scrollTeleportation};
					
			//System.out.println("Length: " + itemTable.length);
			for (int i = 0; i < itemRange; i++) {
				System.out.println("Placing itemes");

				int pick = (int) (Math.random() * (itemTable1.length )) + 0;
				
				System.out.println("Pick: " + pick);
				location = getLocation();
				//System.out.println(Game.itemTable(0)[0].getName());
				dungeonItems.add(new ItemFloor(itemTable1[pick], location[1], location[0]));
				//System.out.println("Added: " + dungeonItems.get(0).getItem().getName());
				//Game.itemsFloor.add(new ItemFloor Game.smallPotion ,location[1], location[0] );
			}
			
			
			
			break;
			
		case 2:
			for (int i = 0; i < 10; i++) {
				rand = (int) (Math.random() * 6) + 0;
				switch (rand) {
				default:
					location = getLocation();
					actors.add(new Bat(location[1], location[0], index));
					break;
				case 0:
					location = getLocation();
					actors.add(new Goblin(location[1], location[0], index));
					break;
				case 2:
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));//
					break;
				case 3: 
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));
					break;
				case 4:
					location = getLocation();
					actors.add(new Bat(location[1], location[0], index));
					break;
				case 5:
					location = getLocation();
					actors.add(new Troll(location[1], location[0], index));
					break;
				}
			}
			
			itemRange = (int) (Math.random() * 5) + 2;
		//	System.out.println("Setting up items");
			Item[] itemTable2 = {Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.club, Items.club, Items.lightLeather, Items.lightLeather, Items.lightLeather,
					Items.smallPoison, Items.chainmail, Items.chainmail, Items.ironPlate, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.potionMinorOfImprovement, Items.potionMinorOfImprovement, Items.scrollTeleportation};
					
			//System.out.println("Length: " + itemTable.length);
			for (int i = 0; i < itemRange; i++) {
				System.out.println("Placing itemes");

				int pick = (int) (Math.random() * (itemTable2.length )) + 0;
				
				System.out.println("Pick: " + pick);
				location = getLocation();
				//System.out.println(Game.itemTable(0)[0].getName());
				dungeonItems.add(new ItemFloor(itemTable2[pick], location[1], location[0]));
				//System.out.println("Added: " + dungeonItems.get(0).getItem().getName());
				//Game.itemsFloor.add(new ItemFloor Game.smallPotion ,location[1], location[0] );
			}
			
			
			
			break;
			
		case 3:
			for (int i = 0; i < 10; i++) {
				rand = (int) (Math.random() * 7) + 0;
				switch (rand) {
				default:
					location = getLocation();
					actors.add(new Bat(location[1], location[0], index));
					break;
				case 0:
					location = getLocation();
					actors.add(new Goblin(location[1], location[0], index));
					break;
				case 2:
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));//
					break;
				case 3: 
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));
					break;
				case 4:
					location = getLocation();
					actors.add(new Bat(location[1], location[0], index));
					break;
				case 5:
					location = getLocation();
					actors.add(new Troll(location[1], location[0], index));
					break;
				case 6:
					location = getLocation();
					actors.add(new Ninja(location[1], location[0], index));
					break;
				}
			}
			
			itemRange = (int) (Math.random() * 5) + 2;
		//	System.out.println("Setting up items");
			Item[] itemTable3 = {Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.scrollGreaterFrozenTime, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.club, Items.ironLongblade, Items.lightLeather, Items.lightLeather, Items.lightLeather,
					 Items.chainmail, Items.chainmail, Items.ironPlate, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.potionMinorOfImprovement, Items.potionMinorOfImprovement, Items.scrollTeleportation};
					
			//System.out.println("Length: " + itemTable.length);
			for (int i = 0; i < itemRange; i++) {
				System.out.println("Placing itemes");

				int pick = (int) (Math.random() * (itemTable3.length )) + 0;
				
				System.out.println("Pick: " + pick);
				location = getLocation();
				//System.out.println(Game.itemTable(0)[0].getName());
				dungeonItems.add(new ItemFloor(itemTable3[pick], location[1], location[0]));
				//System.out.println("Added: " + dungeonItems.get(0).getItem().getName());
				//Game.itemsFloor.add(new ItemFloor Game.smallPotion ,location[1], location[0] );
			}
			break;
			
		case 4:
			for (int i = 0; i < 10; i++) {
				rand = (int) (Math.random() * 7) + 0;
				switch (rand) {
				default:
					location = getLocation();
					actors.add(new Ninja(location[1], location[0], index));
					break;
				case 0:
					location = getLocation();
					actors.add(new Goblin(location[1], location[0], index));
					break;
				case 2:
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));//
					break;
				case 3: 
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));
					break;
				case 4:
					location = getLocation();
					actors.add(new Ninja(location[1], location[0], index));
					break;
				case 5:
					location = getLocation();
					actors.add(new Troll(location[1], location[0], index));
					break;
				case 6:
					location = getLocation();
					actors.add(new Ninja(location[1], location[0], index));
					break;
				}
			}
			
			itemRange = (int) (Math.random() * 5) + 2;
		//	System.out.println("Setting up items");
			Item[] itemTable4 = {Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.largePotion, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.scrollGreaterFrozenTime, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.ironLongblade, Items.ironLongblade, Items.lightLeather, Items.chainmail, Items.chainmail,
					 Items.chainmail, Items.chainmail, Items.ironPlate, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.potionMinorOfImprovement, Items.potionMinorOfImprovement, Items.scrollTeleportation};
					
			//System.out.println("Length: " + itemTable.length);
			for (int i = 0; i < itemRange; i++) {
				System.out.println("Placing itemes");

				int pick = (int) (Math.random() * (itemTable4.length )) + 0;
				
				System.out.println("Pick: " + pick);
				location = getLocation();
				//System.out.println(Game.itemTable(0)[0].getName());
				dungeonItems.add(new ItemFloor(itemTable4[pick], location[1], location[0]));
				//System.out.println("Added: " + dungeonItems.get(0).getItem().getName());
				//Game.itemsFloor.add(new ItemFloor Game.smallPotion ,location[1], location[0] );
			}
			break;
			
		case 5:
			for (int i = 0; i < 10; i++) {
				rand = (int) (Math.random() * 9) + 0;
				switch (rand) {
				default:
					location = getLocation();
					actors.add(new Ninja(location[1], location[0], index));
					break;
				case 0:
					location = getLocation();
					actors.add(new Goblin(location[1], location[0], index));
					break;
				case 2:
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));//
					break;
				case 3: 
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));
					break;
					
				case 4:
					location = getLocation();
					actors.add(new Ninja(location[1], location[0], index));
					break;
				case 5:
					location = getLocation();
					actors.add(new Troll(location[1], location[0], index));
					break;
				case 6:
					location = getLocation();
					actors.add(new Ninja(location[1], location[0], index));
					break;
				case 7: 
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));
					break;
				case 8: 
					location = getLocation();
					actors.add(new Knight(location[1], location[0], index));
					break;
				}
			}
			
			itemRange = (int) (Math.random() * 5) + 2;
		//	System.out.println("Setting up items");
			Item[] itemTable5 = {Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.largePotion, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.scrollGreaterFrozenTime, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.ironLongblade, Items.ironLongblade, Items.lightLeather, Items.chainmail, Items.chainmail,
					 Items.chainmail, Items.chainmail, Items.ironPlate, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.potionMinorOfImprovement, Items.potionMinorOfImprovement, Items.scrollTeleportation};
					
			//System.out.println("Length: " + itemTable.length);
			for (int i = 0; i < itemRange; i++) {
				System.out.println("Placing itemes");

				int pick = (int) (Math.random() * (itemTable5.length )) + 0;
				
				System.out.println("Pick: " + pick);
				location = getLocation();
				//System.out.println(Game.itemTable(0)[0].getName());
				dungeonItems.add(new ItemFloor(itemTable5[pick], location[1], location[0]));
				//System.out.println("Added: " + dungeonItems.get(0).getItem().getName());
				//Game.itemsFloor.add(new ItemFloor Game.smallPotion ,location[1], location[0] );
			}
			break;
			
		case 6:
			for (int i = 0; i < 10; i++) {
				rand = (int) (Math.random() * 11) + 0;
				switch (rand) {
				default:
					location = getLocation();
					actors.add(new Ninja(location[1], location[0], index));
					break;
				case 0:
					location = getLocation();
					actors.add(new Goblin(location[1], location[0], index));
					break;
				case 2:
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));//
					break;
				case 3: 
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));
					break;
					
				case 4:
					location = getLocation();
					actors.add(new Ninja(location[1], location[0], index));
					break;
				case 5:
					location = getLocation();
					actors.add(new Troll(location[1], location[0], index));
					break;
				case 6:
					location = getLocation();
					actors.add(new Ninja(location[1], location[0], index));
					break;
				case 7: 
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));
					break;
				case 8: 
					location = getLocation();
					actors.add(new Knight(location[1], location[0], index));
					break;
				case 9: 
					location = getLocation();
					actors.add(new Knight(location[1], location[0], index));
					break;
				case 10: 
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				}
			}
			
			itemRange = (int) (Math.random() * 5) + 2;
		//	System.out.println("Setting up items");
			Item[] itemTable6 = {Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.largePotion, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.scrollGreaterFrozenTime, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.ironLongblade, Items.ironLongblade, Items.lightLeather, Items.chainmail, Items.chainmail,
					 Items.chainmail, Items.chainmail, Items.ironPlate, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.potionMinorOfImprovement, Items.potionMinorOfImprovement, Items.scrollTeleportation};
					
			//System.out.println("Length: " + itemTable.length);
			for (int i = 0; i < itemRange; i++) {
				System.out.println("Placing itemes");

				int pick = (int) (Math.random() * (itemTable6.length )) + 0;
				
				System.out.println("Pick: " + pick);
				location = getLocation();
				//System.out.println(Game.itemTable(0)[0].getName());
				dungeonItems.add(new ItemFloor(itemTable6[pick], location[1], location[0]));
				//System.out.println("Added: " + dungeonItems.get(0).getItem().getName());
				//Game.itemsFloor.add(new ItemFloor Game.smallPotion ,location[1], location[0] );
			}
			break;
			
		case 7:
			for (int i = 0; i < 10; i++) {
				rand = (int) (Math.random() * 13) + 0;
				switch (rand) {
				default:
					location = getLocation();
					actors.add(new Ninja(location[1], location[0], index));
					break;
				case 0:
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				case 2:
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));//
					break;
				case 3: 
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));
					break;
					
				case 4:
					location = getLocation();
					actors.add(new Ninja(location[1], location[0], index));
					break;
				case 5:
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				case 6:
					location = getLocation();
					actors.add(new Assassin(location[1], location[0], index));
					break;
				case 7: 
					location = getLocation();
					actors.add(new Duelist(location[1], location[0], index));
					break;
				case 8: 
					location = getLocation();
					actors.add(new Knight(location[1], location[0], index));
					break;
				case 9: 
					location = getLocation();
					actors.add(new Knight(location[1], location[0], index));
					break;
				case 10: 
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				case 11: 
					location = getLocation();
					actors.add(new Assassin(location[1], location[0], index));
					break;
				case 12: 
					location = getLocation();
					actors.add(new Assassin(location[1], location[0], index));
					break;
				}
			}
			
			itemRange = (int) (Math.random() * 5) + 2;
		//	System.out.println("Setting up items");
			Item[] itemTable7 = {Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.largePotion, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.scrollGreaterFrozenTime, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.ironLongblade, Items.ironLongblade, Items.lightLeather, Items.chainmail, Items.chainmail,
					 Items.chainmail, Items.scrollTeleportation, Items.ironPlate, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.potionMinorOfImprovement, Items.potionMinorOfImprovement, Items.scrollTeleportation};
					
			//System.out.println("Length: " + itemTable.length);
			for (int i = 0; i < itemRange; i++) {
				System.out.println("Placing itemes");

				int pick = (int) (Math.random() * (itemTable7.length )) + 0;
				
				System.out.println("Pick: " + pick);
				location = getLocation();
				//System.out.println(Game.itemTable(0)[0].getName());
				dungeonItems.add(new ItemFloor(itemTable7[pick], location[1], location[0]));
				//System.out.println("Added: " + dungeonItems.get(0).getItem().getName());
				//Game.itemsFloor.add(new ItemFloor Game.smallPotion ,location[1], location[0] );
			}
			break;
			
		case 8:
			for (int i = 0; i < 10; i++) {
				rand = (int) (Math.random() * 13) + 0;
				switch (rand) {
				default:
					location = getLocation();
					actors.add(new Assassin(location[1], location[0], index));
					break;
				case 0:
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				case 2:
					location = getLocation();
					actors.add(new RoyalGuard(location[1], location[0], index));//
					break;
				case 3: 
					location = getLocation();
					actors.add(new RoyalGuard(location[1], location[0], index));
					break;
					
				case 4:
					location = getLocation();
					actors.add(new Assassin(location[1], location[0], index));
					break;
				case 5:
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				case 6:
					location = getLocation();
					actors.add(new Assassin(location[1], location[0], index));
					break;
				case 7: 
					location = getLocation();
					actors.add(new RoyalGuard(location[1], location[0], index));
					break;
				case 8: 
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				case 9: 
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				case 10: 
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				case 11: 
					location = getLocation();
					actors.add(new Assassin(location[1], location[0], index));
					break;
				case 12: 
					location = getLocation();
					actors.add(new Assassin(location[1], location[0], index));
					break;
				}
			}
			
			itemRange = (int) (Math.random() * 5) + 2;
		//	System.out.println("Setting up items");
			Item[] itemTable8 = {Items.smallPotion, Items.scrollTeleportation, Items.scrollTeleportation, Items.smallPotion, Items.largePotion, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.scrollGreaterFrozenTime, Items.potionMinorOfImprovement, Items.smallPotion, Items.smallPotion,
					Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.royalLance, Items.royalLance, Items.blessedArmor, Items.blessedArmor, Items.chainmail,
					 Items.blessedArmor, Items.scrollTeleportation, Items.blessedArmor, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.potionMinorOfImprovement, Items.potionMinorOfImprovement, Items.scrollTeleportation};
					
			//System.out.println("Length: " + itemTable.length);
			for (int i = 0; i < itemRange; i++) {
				System.out.println("Placing itemes");

				int pick = (int) (Math.random() * (itemTable8.length )) + 0;
				
				System.out.println("Pick: " + pick);
				location = getLocation();
				//System.out.println(Game.itemTable(0)[0].getName());
				dungeonItems.add(new ItemFloor(itemTable8[pick], location[1], location[0]));
				//System.out.println("Added: " + dungeonItems.get(0).getItem().getName());
				//Game.itemsFloor.add(new ItemFloor Game.smallPotion ,location[1], location[0] );
			}
			break;
			
		case 9:
			location = getLocation();
			dungeonItems.add(new ItemFloor(Items.winItem, location[1], location[0]));
			location = getLocation();
			actors.add(new Emperor(location[1], location[0], index));
			for (int i = 0; i < 19; i++) {
				rand = (int) (Math.random() * 13) + 0;
				switch (rand) {
				default:
					location = getLocation();
					actors.add(new RoyalGuard(location[1], location[0], index));
					break;
				case 0:
					location = getLocation();
					actors.add(new RoyalGuard(location[1], location[0], index));
					break;
				case 2:
					location = getLocation();
					actors.add(new RoyalGuard(location[1], location[0], index));//
					break;
				case 3: 
					location = getLocation();
					actors.add(new RoyalGuard(location[1], location[0], index));
					break;
					
				case 4:
					location = getLocation();
					actors.add(new RoyalGuard(location[1], location[0], index));
					break;
				case 5:
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				case 6:
					location = getLocation();
					actors.add(new Assassin(location[1], location[0], index));
					break;
				case 7: 
					location = getLocation();
					actors.add(new RoyalGuard(location[1], location[0], index));
					break;
				case 8: 
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				case 9: 
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				case 10: 
					location = getLocation();
					actors.add(new Vampire(location[1], location[0], index));
					break;
				case 11: 
					location = getLocation();
					actors.add(new Assassin(location[1], location[0], index));
					break;
				case 12: 
					location = getLocation();
					actors.add(new Assassin(location[1], location[0], index));
					break;
				}
			}
			
			itemRange = (int) (Math.random() * 5) + 2;
		//	System.out.println("Setting up items");
			Item[] itemTable9 = {Items.smallPotion, Items.scrollTeleportation, Items.scrollTeleportation, Items.smallPotion, Items.largePotion, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.smallPotion, Items.smallPotion, Items.smallPotion,
					Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.smallPotion, Items.scrollGreaterFrozenTime, Items.potionMinorOfImprovement, Items.smallPotion, Items.smallPotion,
					Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.scrollFireball, Items.royalLance, Items.royalLance, Items.blessedArmor, Items.blessedArmor, Items.chainmail, Items.immortalArmor, Items.holyLance,
					 Items.blessedArmor, Items.scrollTeleportation, Items.blessedArmor, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.scrollFrozenTime, Items.potionMinorOfImprovement, Items.potionMinorOfImprovement, Items.scrollTeleportation};
					
			//System.out.println("Length: " + itemTable.length);
			for (int i = 0; i < itemRange; i++) {
				System.out.println("Placing itemes");

				int pick = (int) (Math.random() * (itemTable9.length )) + 0;
				
				System.out.println("Pick: " + pick);
				location = getLocation();
				//System.out.println(Game.itemTable(0)[0].getName());
				dungeonItems.add(new ItemFloor(itemTable9[pick], location[1], location[0]));
				//System.out.println("Added: " + dungeonItems.get(0).getItem().getName());
				//Game.itemsFloor.add(new ItemFloor Game.smallPotion ,location[1], location[0] );
			}
			break;
		default:
			break;
		}
	}


	public ArrayList<ItemFloor> getDungeonItems() {
		return dungeonItems;
	}


	public void setDungeonItems(ArrayList<ItemFloor> dungeonItems) {
		this.dungeonItems = dungeonItems;
	}
}
