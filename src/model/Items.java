
package model;

public class Items {
	
	// Nothing items
	public static Weapon nothingWep = new Weapon("nothing weapon", '!', 0, 8);
	public static Armor nothingArm = new Armor("nothing armor", '+', 0, 0, 0, 8);

	// Generate weapons
	public static Weapon fists = new Weapon ("No Weapon", '!', 0, 1);
	public static Weapon club = new Weapon("Club", '!', 5,2);
	public static Weapon rustyDagger = new Weapon("Rusty Dagger", '!', 2,3);
	public static Weapon ironLongblade = new Weapon("Iron Longblade", '!', 8,4);
	public static Weapon steelLongblade = new Weapon("Steel Longblade", '!', 10,5);
	public static Weapon royalLance = new Weapon("Royal Lance", '!', 15,6);
	public static Weapon holyLance = new Weapon("Holy Lance", '!', 25,7);
	
	public static Weapon[] weapons = {nothingWep, fists, rustyDagger, ironLongblade, steelLongblade, royalLance, holyLance};
	

	// Generate armors

	public static Armor[] getArmors() {
		return armors;
	}

	public static void setArmors(Armor[] armors) {
		Items.armors = armors;
	}

	public static Armor clothes = new Armor("Clothes", '+', 1, 0, 0, 0);
	public static Armor lightLeather = new Armor("Light Leather", '+', 5, 1, 0, 1);
	public static Armor chainmail = new Armor("Chainmail", '+', 10, 0, 0, 2);
	public static Armor steelPlate = new Armor("Steel Plate", '+', 20, -1, 0, 3);
	public static Armor ironPlate = new Armor("Iron Plate", '+', 15, -1, 0, 4);
	public static Armor blessedArmor = new Armor("Blessed Armor", '+', 25, -2, 3, 5);
	public static Armor immortalArmor = new Armor("Immortal Armor", '+', 50, -1, 6, 6);
	public static Armor naked = new Armor("No Armor", '#', 0, 0, 0, 7);
	
	public static Armor[] armors = {clothes, lightLeather, chainmail, steelPlate, ironPlate, blessedArmor, immortalArmor, naked, nothingArm};
	
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
	
	public static Item winItem = new Item("Patriots Game Ticket",'$',"A <span style = \"color:yellow\">Prized Treasure</span>", 0, Effect.WIN_ITEM, null);

	public static Weapon getClub() {
		return club;
	}

	public static void setClub(Weapon club) {
		Items.club = club;
	}

	public static Weapon getRustyDagger() {
		return rustyDagger;
	}

	public static void setRustyDagger(Weapon rustyDagger) {
		Items.rustyDagger = rustyDagger;
	}

	public static Weapon getIronLongblade() {
		return ironLongblade;
	}

	public static void setIronLongblade(Weapon ironLongblade) {
		Items.ironLongblade = ironLongblade;
	}

	public static Weapon getSteelLongblade() {
		return steelLongblade;
	}

	public static void setSteelLongblade(Weapon steelLongblade) {
		Items.steelLongblade = steelLongblade;
	}

	public static Weapon getRoyalLance() {
		return royalLance;
	}

	public static void setRoyalLance(Weapon royalLance) {
		Items.royalLance = royalLance;
	}

	public static Weapon getHolyLance() {
		return holyLance;
	}

	public static void setHolyLance(Weapon holyLance) {
		Items.holyLance = holyLance;
	}

	public static Armor getClothes() {
		return clothes;
	}

	public static void setClothes(Armor clothes) {
		Items.clothes = clothes;
	}

	public static Armor getLightLeather() {
		return lightLeather;
	}

	public static void setLightLeather(Armor lightLeather) {
		Items.lightLeather = lightLeather;
	}

	public static Armor getChainmail() {
		return chainmail;
	}

	public static void setChainmail(Armor chainmail) {
		Items.chainmail = chainmail;
	}

	public static Armor getSteelPlate() {
		return steelPlate;
	}

	public static void setSteelPlate(Armor steelPlate) {
		Items.steelPlate = steelPlate;
	}

	public static Armor getIronPlate() {
		return ironPlate;
	}

	public static void setIronPlate(Armor ironPlate) {
		Items.ironPlate = ironPlate;
	}

	public static Armor getBlessedArmor() {
		return blessedArmor;
	}

	public static void setBlessedArmor(Armor blessedArmor) {
		Items.blessedArmor = blessedArmor;
	}

	public static Armor getImmortalArmor() {
		return immortalArmor;
	}

	public static void setImmortalArmor(Armor immortalArmor) {
		Items.immortalArmor = immortalArmor;
	}

	public static Item getSmallPotion() {
		return smallPotion;
	}

	public static void setSmallPotion(Item smallPotion) {
		Items.smallPotion = smallPotion;
	}

	public static Item getLargePotion() {
		return largePotion;
	}

	public static void setLargePotion(Item largePotion) {
		Items.largePotion = largePotion;
	}

	public static Item getSmallPoison() {
		return smallPoison;
	}

	public static void setSmallPoison(Item smallPoison) {
		Items.smallPoison = smallPoison;
	}

	public static Item getScrollTeleportation() {
		return scrollTeleportation;
	}

	public static void setScrollTeleportation(Item scrollTeleportation) {
		Items.scrollTeleportation = scrollTeleportation;
	}

	public static Item getScrollFireball() {
		return scrollFireball;
	}

	public static void setScrollFireball(Item scrollFireball) {
		Items.scrollFireball = scrollFireball;
	}

	public static Item getScrollGreaterFireball() {
		return scrollGreaterFireball;
	}

	public static void setScrollGreaterFireball(Item scrollGreaterFireball) {
		Items.scrollGreaterFireball = scrollGreaterFireball;
	}

	public static Item getScrollMinorFrozenTime() {
		return scrollMinorFrozenTime;
	}

	public static void setScrollMinorFrozenTime(Item scrollMinorFrozenTime) {
		Items.scrollMinorFrozenTime = scrollMinorFrozenTime;
	}

	public static Item getScrollFrozenTime() {
		return scrollFrozenTime;
	}

	public static void setScrollFrozenTime(Item scrollFrozenTime) {
		Items.scrollFrozenTime = scrollFrozenTime;
	}

	public static Item getScrollGreaterFrozenTime() {
		return scrollGreaterFrozenTime;
	}

	public static void setScrollGreaterFrozenTime(Item scrollGreaterFrozenTime) {
		Items.scrollGreaterFrozenTime = scrollGreaterFrozenTime;
	}

	public static Item getScrollMasterFrozenTime() {
		return scrollMasterFrozenTime;
	}

	public static void setScrollMasterFrozenTime(Item scrollMasterFrozenTime) {
		Items.scrollMasterFrozenTime = scrollMasterFrozenTime;
	}

	public static Item getPotionMinorOfImprovement() {
		return potionMinorOfImprovement;
	}

	public static void setPotionMinorOfImprovement(Item potionMinorOfImprovement) {
		Items.potionMinorOfImprovement = potionMinorOfImprovement;
	}

	public static Item getWinItem() {
		return winItem;
	}

	public static void setWinItem(Item winItem) {
		Items.winItem = winItem;
	}
	
	
}

