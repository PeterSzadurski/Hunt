
package model;

public class Items {

	// Generate weapons

	public static Weapon club = new Weapon("club", '!', 5);
	public static Weapon rustyDagger = new Weapon("Rusty Dagger", '!', 2);
	public static Weapon ironLongblade = new Weapon("Iron Longblade", '!', 8);
	public static Weapon steelLongblade = new Weapon("Steel Longblade", '!', 10);
	public static Weapon royalLance = new Weapon("Royal Lance", '!', 15);




	// Generate armors

	public static Armor clothes = new Armor("Clothes", '+', 1, 0, 0);
	public static Armor lightLeather = new Armor("Light Leather", '+', 5, 0, 0);
	public static Armor chainmail = new Armor("Chainmail", '+', 10, 0, 0);
	public static Armor steelPlate = new Armor("Steel Plate", '+', 20, -1, 0);
	public static Armor ironPlate = new Armor("Iron Plate", '+', 15, -1, 0);
	public static Armor blessedArmor = new Armor("Blessed Armor", '+', 25, -2, 3);

	// Generate items
	public static Item smallPotion = new Item("Small Potion", 'i',
			"<span style = \"color:" + Game.buffColor + "\">Restores</span> 10HP", 10, Effect.RESTORE_HEALTH, null);
	public static Item largePotion = new Item("Large Potion", 'i',
			"<span style = \"color:" + Game.buffColor + "\">Restores</span> 50HP", 50, Effect.RESTORE_HEALTH, null);
	public static Item smallPoison = new Item("Small Poison", 'i',
			"<span style = \"color:" + Game.debuffColor + "\">Damages</span> 15HP", 15, Effect.DAMAGE_HEALTH, null);
	public static Item scrollTeleportation = new Item("Scroll of Teleportation", 'i',
			"<span style = \"color: " + Game.magicEffectColor + "\">Teleports</span> the user", 0, Effect.TELEPORT, null);
	public static Item scrollFireball = new Item("Scroll of Fireball", 'i',
			"Scorches the enemy for <span style=\"color:" + Game.debuffColor + "\">25 damage</span>", -25, Effect.RANGED,
			Effect.DAMAGE_HEALTH);
	public static Item scrollGreaterFireball = new Item("Scroll of Greater Fireball", 'i',
			"Scorches the enemy for <span style=\"color:" + Game.debuffColor + "\">100 damage</span>", -100, Effect.RANGED,
			Effect.DAMAGE_HEALTH);
	public static Item scrollMinorFrozenTime = new Item("Scroll of Minor Frozen Time", 'i',
			"<span style = \"color: " + Game.magicEffectColor + "\">Freezes time</span> for 5 turns", 5, Effect.STOP_TIME,
			null);
	public static Item scrollFrozenTime = new Item("Scroll of Frozen Time", 'i',
			"<span style = \"color: " + Game.magicEffectColor + "\">Freezes time</span> for 8 turns", 8, Effect.STOP_TIME,
			null);
	public static Item scrollGreaterFrozenTime = new Item("Scroll of Greater Frozen Time", 'i',
			"<span style = \"color: " + Game.magicEffectColor + "\">Freezes time</span> for 14 turns", 14, Effect.STOP_TIME,
			null);
	public static Item scrollMasterFrozenTime = new Item("Scroll of Master Frozen Time", 'i',
			"<span style = \"color: " + Game.magicEffectColor + "\">Freezes time</span> for 50 turns", 50, Effect.STOP_TIME,
			null);
	public static Item potionMinorOfImprovement = new Item("Minor Potion of Improvement", 'i',
			"<span style = \"color: " + Game.buffColor + "\">Raises a random stat</span> by 1 point", 1, Effect.RANDOM_RAISE,
			null);
	
	public static Item winItem = new Item("Patriots Game Ticket",'$',"A <span style = \"color:yellow\">Prized Treasure</span>", 0, null, null);
}

