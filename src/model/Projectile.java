package model;


public class Projectile {
	char icon = 'o';
	String color;
	int x;
	int y;
	int agility;
	int movX;
	int movY;
	int damage;
	boolean setDestroy = false;
	private double turnCount = 0; 
	Projectile(){}
	public Projectile (String color, int x, int y, int movX, int movY, int agility, int damage) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.movY = movY;
		this.movX = movX;
		this.agility = agility;
		this.damage = damage;
	}
	public boolean isSetDestroy() {
		return setDestroy;
	}
	public void setSetDestroy(boolean setDestroy) {
		this.setDestroy = setDestroy;
	}
	public char getIcon() {
		return icon;
	}
	public void setIcon(char icon) {
		this.icon = icon;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getAgility() {
		return agility;
	}
	public void setAgility(int agility) {
		this.agility = agility;
	}
	
	public void setTurnCount (double turnCount) {
		this.turnCount = turnCount;
	}
	
	public double getTurnCount () {
		return turnCount;
	}
	
	public void move() {
		int collideActor = -1;
		for (int i = 0; i < Game.actors.size(); i++) {
			if (((this.x + movX) == Game.actors.get(i).getX()) && ((this.y + movY) == Game.actors.get(i).getY())) {
				collideActor = i;
			}

		}
		
		// if there isn't an actor in the way
		if (collideActor == -1) {

			// if inside the Dungeon bounds
			if ((this.x + movX) < Game.getDungeon()[Game.floor].getWidth() && (this.x + movX > -1) && (this.y + movY) < Game.getDungeon()[Game.floor].getHeight() && (this.y + movY > -1)) {
				// check if not solid
				if (Game.getDungeon()[Game.floor].getTile(this.y + movY, this.x + movX).isSolid() == false) {
					Game.getDungeon()[Game.floor].changeEntities(this.y, this.x, Game.getDungeon()[Game.floor].getTile(this.y, this.x).getIcon(),
							Game.getDungeon()[Game.floor].getTile(this.y, this.x).getColor());
					this.x += movX;
					this.y += movY;
					System.out.println("Ball x=" + this.x + " | Ball Y=" + this.y);
					Game.getDungeon()[Game.floor].changeEntities(this.y, this.x, this.icon, this.color);
					//Game.log = ("Move Freely");

				}

				else {
					Game.getDungeon()[Game.floor].changeEntities(this.y, this.x, Game.getDungeon()[Game.floor].getTile(this.y, this.x).getIcon(),
							Game.getDungeon()[Game.floor].getTile(this.y, this.x).getColor());
					setDestroy = true;
					//System.out.println("solid");
					//Game.log = ("Cannot Move");
				}
			}
			else {
				Game.getDungeon()[Game.floor].changeEntities(this.y, this.x, Game.getDungeon()[Game.floor].getTile(this.y, this.x).getIcon(),
				Game.getDungeon()[Game.floor].getTile(this.y, this.x).getColor());
				setDestroy = true;
			}
			// Game.update(d);
			//System.out.println("X: " + this.x + " Y: " + this.y);
		}
		
		else if (!setDestroy) {
				Game.actors.get(collideActor).setCurHp(Game.actors.get(collideActor).getCurHp() + damage);
				setDestroy = true;
				Game.getDungeon()[Game.floor].changeEntities(this.y, this.x, Game.getDungeon()[Game.floor].getTile(this.y, this.x).getIcon(),
						Game.getDungeon()[Game.floor].getTile(this.y, this.x).getColor());
			}
			//Game.log = (Game.actors.get(collideActor).getName() + ": HP: " + Game.actors.get(collideActor).getCurHp() + 
				//	"/" + Game.actors.get(collideActor).getHp());
		}
	}
	

