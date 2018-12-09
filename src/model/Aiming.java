package model;

public class Aiming {
	char icon = '*';
	public int x;
	public int y;
	boolean show = false;
	private Effect effect;
	 boolean restrict = false;
	Aiming() {};
	
	Aiming(int x, int y) {
		this.x = x;
		this.y = y;
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

	public boolean isShow() {
		return show;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public boolean isRestrict() {
		return restrict;
	}

	public void setRestrict(boolean restrict) {
		this.restrict = restrict;
	}

	public void setShow(boolean show) {
		this.show = show;
	}
	
	public char geticon() {
		return icon;
	}
	
	public void move(int x, int y, Dungeon d) {
		int collideActor = -1;
		for (int i = 0; i < Game.actors.size(); i++) {
			if (((this.x + x) == Game.actors.get(i).getX()) && ((this.y + y) == Game.actors.get(i).getY())) {
				collideActor = i;
			}

		}
		
		// if there isn't an actor in the way
		if (collideActor == -1) {

			// if inside the Dungeon bounds
			if ((this.x + x) < d.getWidth() && (this.x + x > -1) && (this.y + y) < d.getHeight() && (this.y + y > -1)) {
				// check if not solid
				if (d.getTile(this.y + y, this.x + x).isSolid() == false) {
					this.x += x;
					this.y += y;
					
					//Game.log = ("Move Freely");

				}

				else {
					//System.out.println("solid");
					//Game.log = ("Cannot Move");
				}
			}
			// Game.update(d);
			//System.out.println("X: " + this.x + " Y: " + this.y);
		}
		
		else {
			//Game.log = (Game.actors.get(collideActor).getName() + ": HP: " + Game.actors.get(collideActor).getCurHp() + 
				//	"/" + Game.actors.get(collideActor).getHp());
		}
	}
	public void restrictedMove(int x, int y, Dungeon d) {

		System.out.println("X: " + x);
		// limits the aiming to one tile to from the player, not including diagonals 
		if ((((Game.aiming.getX() + x  == Game.actors.get(0).getX() + x) ||
				(Game.aiming.getX() + x  == Game.actors.get(0).getX())) &&
				((Game.aiming.getY() + y  == Game.actors.get(0).getY() + y) ||
						(Game.aiming.getY() + y  == Game.actors.get(0).getY())))) {
			System.out.println("Player X:" + Game.actors.get(0).getX() + " Move X:" + x + " Combined:" + (Game.actors.get(0).getX() + x));
			// if inside the Dungeon bounds
			if ((this.x + x) < d.getWidth() && (this.x + x > -1) && (this.y + y) < d.getHeight() && (this.y + y > -1)) {
				// check if not solid
				if (d.getTile(this.y + y, this.x + x).isSolid() == false) {
					this.x += x;
					this.y += y;
					
					//Game.log = ("Move Freely");

				}

				else {
					//System.out.println("solid");
					//Game.log = ("Cannot Move");
				}
			}
			// Game.update(d);
			//System.out.println("X: " + this.x + " Y: " + this.y);
		}
		
		else {
			//Game.log = (Game.actors.get(collideActor).getName() + ": HP: " + Game.actors.get(collideActor).getCurHp() + 
				//	"/" + Game.actors.get(collideActor).getHp());
		}
	}
	
}
