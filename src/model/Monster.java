package model;
import java.io.Serializable;
import java.util.Random;

public class Monster extends Character implements Serializable {
	private static final long serialVersionUID = 6331686211289256850L;
	
	private String type;
	private int expOnKill;
	private double turnCount = 0;
	int id;

	private int floor;
	
	private Item[] loot;

	
	MonsterStates state = MonsterStates.IDLE;
	
	
	
	
	public Monster() {
		// makes it a bean
	}
	
	public Monster(String name, int expOnKill, int str, int agi, int vit, Weapon weapon, 
			Armor armor, char icon, String color, int x, int y, int floor) {
		
		super(name, str, agi, vit, weapon, armor, icon, color, x, y);
		
		//this.name = name;
		this.expOnKill = expOnKill;
		this.floor =floor;
		super.calcDamage();
		super.calcHP();
		super.calcMoveSpeed();
	}

	public String getType() {
		return type;
	}
	
	
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	public Item[] getLoot() {
		return loot;
	}

	public void setLoot(Item[] loot) {
		this.loot = loot;
	}

	public MonsterStates getState() {
		return state;
	}

	public void setState(MonsterStates state) {
		this.state = state;
	}

	public int getExpOnKill() {
		return expOnKill;
	}
	
	public Item getLootItem() {
		// will only drop items half the time
			int choose = (int) (Math.random() * loot.length) + 0;
			return loot[choose];
	}

	/*public int getCurrentLevel() {
		return currentDungeonLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentDungeonLevel = currentLevel;
	}
*/
	public int getId() {
		return id;
	}

	public void setId(int monsterId) {
		this.id = monsterId;
	}

	public void setExpOnKill(int expOnKill) {
		this.expOnKill = expOnKill;
	}

	public void setTurnCount (double turnCount) {
		this.turnCount = turnCount;
	}
	
	public double getTurnCount () {
		return turnCount;
	}
	
	public String toString() {
		StringBuilder monsterString = new StringBuilder();
		monsterString.append("Type: " + getName() + "\n");
		monsterString.append("Strength: " + getStrength() + "\n");
		monsterString.append("Agility: " + getAgility() + "\n");
		monsterString.append("Vitality: " + getVitality() + "\n");
		monsterString.append("Current HP: " + getCurHp() + "\n");
		
		return monsterString.toString();
	}
	
	public boolean withinRange (Character c, int range) {
		return ((this.getX() - c.getX() <= range) && (this.getX() - c.getX() >= -range) && (this.getY() - c.getY() <= range) && (this.getY() - c.getY() >= -range));
	}
	
@Override	public void move(int x, int y, Dungeon d) {
		int collideActor = -1;
		for (int i = 0; i < Game.actors.size(); i++) {
			if (((this.getX() + x) == Game.actors.get(i).getX()) && ((this.getY() + y) == Game.actors.get(i).getY())) {
				collideActor = i;
			}

		}
		
		// if there isn't an actor in the way
		if (collideActor == -1) {

			// if inside the Dungeon bounds
			if ((this.getX() + x) < d.getWidth() && (this.getX() + x > -1) && (this.getY() + y) < d.getHeight() && (this.getY() + y > -1)) {
				// check if not solid
				if (d.getTile(this.getY() + y, this.getX() + x).isSolid() == false) {
					d.changeEntities(this.getY(), this.getX(), d.getTile(this.getY(), this.getX()).getIcon(),
							d.getTile(this.getY(), this.getX()).getColor());
					// check the floor for items
					if (!Game.itemsFloor.isEmpty()) {
						for (int o = 0; o < Game.itemsFloor.size(); o++) {
							if (Game.itemsFloor.get(o).getX() == this.getX() && Game.itemsFloor.get(o).getY() == this.getY()) {
							d.changeEntities(Game.itemsFloor.get(o).getY(), Game.itemsFloor.get(o).getX(),
									Game.itemsFloor.get(o).getIcon(), Game.itemsFloor.get(o).getColor());
							}
						}
					}
					this.setX(this.getX() + x);
					this.setY(this.getY() + y);
					Game.getDungeon()[Game.floor].changeEntities(this.getY(), this.getX(), this.geticon(), this.getColor());
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
		
		else if (Game.actors.get(collideActor) instanceof Player){
			int test = this.attack(Game.actors.get(collideActor));
			if (test != 0) {
			Game.log = "The " + this.getName() + " strikes " + Game.actors.get(collideActor).getName() + " for " + test + "damage!";
			}
			else {
				Game.log = "The " + this.getName() + " misses " + Game.actors.get(collideActor).getName() + "!";
			}
	}
		}

public int getCurrentLevel() {
	return floor;
}
	
}
