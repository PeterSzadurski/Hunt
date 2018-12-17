package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Armor;
import model.Effect;
import model.Item;
import model.Items;
import model.Player;
import model.User;
import model.Weapon;
import util.DBUtil;

public class PlayerDAO {
	
	
	// create inital player instance in database
	public void addPlayer(Player player, String username) {
		Connection conn = null;
		System.out.println("username: " + username);
		System.out.println("playername: " + player.getName());
		System.out.println("strength: " + player.getStrength());
		System.out.println("agility: " + player.getAgility());
		System.out.println("vitality: " + player.getVitality());
		System.out.println("damage: " + player.getDamage());
		System.out.println("speed: " + player.getMoveSpeed());
		System.out.println("healthpoints: " + player.getHp());
		System.out.println("curhp: " + player.getCurHp());
		System.out.println("weapon: " + player.getWeapon());
		System.out.println("armor: " + player.getArmor());
		System.out.println("issolid: " + player.isSolid());
		System.out.println("posx: " + player.getX());
		System.out.println("posy: " + player.getY());
		System.out.println("level: " + player.getLevel());
		System.out.println("expfornextlvl: " + player.getExpForNextLevel());
		System.out.println("exp: " + player.getExp());
		System.out.println("hunger: " + player.getHunger());
		System.out.println("inventory: " + player.backpackStorageString());
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(""
					+ "INSERT Into player ("
					+ "playername, "
					+ "username, "
					+ "playerstrength, "
					+ "playeragility, "
					+ "playervitality, "
					+ "playerdamage, "
					+ "playerspeed, "
					+ "playerhealthpoints, "
					+ "playercurrenthealthpoints, "
					+ "playerweapon, "
					+ "playerarmor, "
					+ "playerposx, "
					+ "playerposy, "
					+ "playerlevel, "
					+ "playerexpfornextlevel, "
					+ "playerexp, "
					+ "playerhunger, "
					+ "playerinventory) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");			
			pStmt.setString(1, player.getName());
			pStmt.setString(2, username);
			pStmt.setInt(3, player.getStrength());
			pStmt.setInt(4, player.getAgility());
			pStmt.setInt(5, player.getVitality());
			pStmt.setInt(6, player.getDamage());
			pStmt.setInt(7, player.getMoveSpeed());
			pStmt.setInt(8, player.getHp());
			pStmt.setInt(9, player.getCurHp());
			pStmt.setInt(10, player.getWeapon().getId());
			pStmt.setInt(11, player.getArmor().getId());
			pStmt.setInt(12, player.getX()); // x
			pStmt.setInt(13, player.getY()); // y
			pStmt.setInt(14, player.getLevel());
			pStmt.setInt(15, player.getExpForNextLevel());
			pStmt.setInt(16, player.getExp());
			pStmt.setInt(17, player.getHunger());
			pStmt.setString(18, player.backpackStorageString());
			
			

			int result = pStmt.executeUpdate();
			//pStmt.e
			
			if(result > 0) {
				System.out.println("Successful player add");
			} else {
				System.out.println("Unsuccessful player add");
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public void deletePlayer(Player player) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"delete from player where PlayerId = ?");
			pStmt.setInt(1, player.getPlayerID());
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public void updatePlayer(Player player) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"update player set PlayerName = ? where PlayerId = ?");
			pStmt.setString(1, player.getName());
			pStmt.setInt(2, player.getPlayerID());
			pStmt.executeQuery();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public List<Player> getAllPlayers() {
		Connection conn = null;
		List<Player> playerList = new ArrayList<Player>();
		try {
			conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("select * from player");
			
			while(result.next()) {
				Player player = new Player();
				player.setName(result.getString("PlayerName"));
				playerList.add(player);
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
		return playerList;
	}
	
	public boolean playerExists(String playerName) {
		boolean playerExists = false;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"select * from Player where username = ?");
			pStmt.setString(1, playerName);
			ResultSet result = pStmt.executeQuery();
			if (result.next()) {
				if(playerName.equals(result.getString("username"))) {
					playerExists = true;
				}
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
		return playerExists;
	}
	
	public Player getPlayerByID(int playerId) {
		Player player = new Player();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"select * from Player where PlayerId = ?");
			pStmt.setInt(1, playerId);
			ResultSet result = pStmt.executeQuery();
			while(result.next()) {
				player.setName(result.getString("playerName"));
				player.setPlayerID(result.getInt("playerId"));
				player.setCurrentDungeonID(result.getInt("currentDungeonLevelID"));
				player.setStrength(result.getInt("playerStrength"));
				player.setAgility(result.getInt("playerAgility"));
				player.setVitality(result.getInt("playerVitality"));
				player.setMoveSpeed(result.getInt("PlayerSpeed"));
				player.setHp(result.getInt("PlayerHealthPoints"));
				//player.setHp(result.getInt("PlayerHealthPoints"));
				player.setCurHp(result.getInt("PlayerHealthPoints"));
				//player.setWeapon(Items.holyLance);
				player.setArmorId(result.getInt("PlayerArmor"));
				//Armor work = new Armor(result.getString("PlayerArmor"));
				//player.setName(work.getName());
				//player.setArmor(Items.armors[1]);
				//player.setArmor(othertest);
				//Weapon weapon = new Weapon(result.getString("PlayerWeapon"));
				//player.setWeapon(weapon);
				
				//System.out.println("Current hp stter: " + result.getInt("PlayerCurrentHealthPoints"));
				//player.setCurHp(result.getInt("PlayerCurrentHealthPoints"));
				player.setLevel(result.getInt("PlayerLevel"));
				player.setExpForNextLevel(result.getInt("PlayerExpForNextLevel"));
				player.setExp(result.getInt("PlayerExp"));
				player.setHunger(result.getInt("PlayerHunger"));
				//String[] packString = result.getString("PlayerBackpack").split(",");
				//Item item;
				//ArrayList<Item> pack = new ArrayList<>();
		/*		for (int i = 0; i < packString.length; i++) {
					String[] itemString = packString[i].split("|");
					item = new Item();
					item.setName(itemString[0]);
					item.setEffectText(itemString[1]);
					item.setCount(Integer.parseInt(itemString[4]));
					
					pack.add(item);
				}
				player.setBackpack(pack); */
				//player.setArmor(othertest);

			}
			//player.setBackpack(pack);
			//player.setCurHp(400);
			//player.setCurHp(result.getInt("PlayerHealthPoints"));
			player.calcDamage();
			player.calcHP();
			player.calcMoveSpeed();
			player.setArmorId(5);


		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		}
		return player;
	}
	
	public Player getPlayerActual (String user) {
		Player player = new Player();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement pStmt = conn.prepareStatement(
					"select * from Player where Username = ?" );
			pStmt.setString(1, user);
			ResultSet result = pStmt.executeQuery();
			while(result.next()) {
				player.setName(result.getString("UserName"));
				player.setAgility(result.getInt("PlayerAgility"));
				player.setStrength(result.getInt("PlayerStrength"));
				player.setVitality(result.getInt("PlayerVitality"));
				player.setCurHp(result.getInt("PlayerCurrentHealthPoints"));
				player.setArmor(Items.armors[result.getInt("PlayerArmor")]);
				player.setWeapon(Items.weapons[result.getInt("PlayerWeapon")]);
				player.setLevel(result.getInt("playerlevel"));
				player.setExp(result.getInt("playerexp"));
				player.setExpForNextLevel(result.getInt("Playerexpfornextlevel"));
				player.setBackpack(new ArrayList<>());

				//String[] packString = result.getString("PlayerBackpack").split(",");
				//Item item;
				//ArrayList<Item> pack = new ArrayList<>();
		/*		for (int i = 0; i < packString.length; i++) {
					String[] itemString = packString[i].split("|");
					item = new Item();
					item.setName(itemString[0]);
					item.setEffectText(itemString[1]);
					item.setCount(Integer.parseInt(itemString[4]));
					
					pack.add(item);
				}
				player.setBackpack(pack); */
				//player.setArmor(othertest);

			}
			//player.setBackpack(pack);
				
			
			
		}
	 catch (Exception ex) {
		ex.getMessage();
	}
		player.calcHP();
		player.calcMoveSpeed();
		player.calcDamage();
		
		return player;
	}
	
	public Player getPlayerByUserID(int userId) {
		Player player = new Player();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"select * from Player where UserId = ?");
			pStmt.setInt(1, userId);
			ResultSet result = pStmt.executeQuery();
			
			while(result.next()) {
				player.setName(result.getString("playerName"));
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		}
		return player;
	}
	
	// save player attributes in game
	public void savePlayerAttributes(Player p, User u) {
		int uID = u.getUserID();
		int pID = p.getPlayerID();
		int currentDungeonLevelID = p.getCurrentDungeonID();
		int pStrength = p.getStrength();
		int pAgility = p.getAgility();
		int pVitality = p.getVitality();
		int pDamage = p.getDamage();
		int pSpeed = p.getMoveSpeed();
		int pHealth = p.getHp();
		int pCurrentHealth = p.getCurHp();
		int pWeapon = p.getWeapon().getId();
		int pArmor = p.getArmor().getId();
		int pX = p.getX();
		int pY = p.getY();
		int pLevel = p.getLevel();
		int pExpForNextLevel = p.getExpForNextLevel();
		int pExp = p.getExp();
		int pHunger = p.getHunger();
		//String pBackpack = p.getBackpack().toString();
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
		
			PreparedStatement pStmt = conn.prepareStatement("insert into "
					+ "Player ("
					+ "CurrentDungeonLevelID," // 1
					+ "PlayerStrength," // 2 
					+ "PlayerAgility," // 3
					+ "PlayerVitality," // 4
					+ "PlayerDamage," // 5
					+ "PlayerSpeed," // 6
					+ "PlayerHealthPoints," // 7
					+ "PlayerCurrentHealth," // 8
					+ "PlayerWeapon," // 9
					+ "PlayerArmor," // 10
					+ "PlayerPosX," // 11
					+ "PlayerPosY," // 12
					+ "PlayerLevel," // 13
					+ "PlayerExpForNextLevel," // 14
					+ "PlayerExp," // 15
					+ "PlayerHunger," //16
					+ "PlayerBackpack" // 17
					+ ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) where Playername = ? and Username = ?"); 
			pStmt.setInt(1, currentDungeonLevelID);
			pStmt.setInt(2, pStrength);
			pStmt.setInt(3, pAgility);
			pStmt.setInt(4, pVitality);
			pStmt.setInt(5, pDamage);
			pStmt.setInt(6, pSpeed);
			pStmt.setInt(7, pHealth);
			pStmt.setInt(8, pCurrentHealth);
			pStmt.setInt(9, pWeapon);
			pStmt.setInt(10, pArmor);
			pStmt.setInt(11, pX);
			pStmt.setInt(12, pY);
			pStmt.setInt(13, pLevel);
			pStmt.setInt(14, pExpForNextLevel);
			pStmt.setInt(15, pExp);
			pStmt.setInt(16, pHunger);
			//pStmt.setString(17, pBackpack);
			pStmt.setString(17, p.getName());
			pStmt.setString(18, u.getUsername());
			
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		}	
	}
	
	public boolean playerExists(int userId) {
		boolean playerExists = false;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement pStmt = conn.prepareStatement("select * from Player where userid = ?");
			pStmt.setInt(1, userId);
			
			ResultSet result = pStmt.executeQuery();
			
			if (result.next()) {
				if(userId == result.getInt("userid")) {
					playerExists = true;
				}
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
		return playerExists;
	}

}
