
public class Effect {

	// effect methods for Items and Spells
	
	public static void restoreHealth (int heal, int index) {
		if (Game.actors.get(index).getHp() == Game.actors.get(index).getCurHp()) {
			
		}
		else {
			// heal actor
			Game.actors.get(index).setCurHp(Game.actors.get(index).getCurHp() + heal);
			// makes sure the hp does not overflow
			if (Game.actors.get(index).getCurHp() > Game.actors.get(index).getHp()) {
				Game.actors.get(index).setCurHp(Game.actors.get(index).getHp());
			}
		}
		
	}
	
}
