package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Player;
import model.User;
import util.DBUtil;

public class PlayerDAO {
	
	public void addPlayer(Player player) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"insert into Player (PlayerName) values (?)");
			pStmt.setString(1, player.getName());
			pStmt.executeUpdate();
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
					"select * from Player where playerName = ?");
			ResultSet result = pStmt.executeQuery();
			if (result.next()) {
				if(playerName.equals(result.getString("playerName"))) {
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
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		}
		return player;
	}
	
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
		String pWeapon = p.getWeapon().toString();
		String pArmor = p.getArmor().toString();
		int pX = p.getX();
		int pY = p.getY();
		int pLevel = p.getLevel();
		int pExpForNextLevel = p.getExpForNextLevel();
		int pExp = p.getExp();
		int pHunger = p.getHunger();
		String pBackpack = p.getBackpack().toString();
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
		
			PreparedStatement pStmt = conn.prepareStatement("insert into "
					+ "Player ("
					+ "CurrentDungeonLevelID," // 1
					+ "PlayerStrenth," // 2 
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
					+ ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) where PlayerID = ? and UserID = ?"); 
			pStmt.setInt(1, currentDungeonLevelID);
			pStmt.setInt(2, pStrength);
			pStmt.setInt(3, pAgility);
			pStmt.setInt(4, pVitality);
			pStmt.setInt(5, pDamage);
			pStmt.setInt(6, pSpeed);
			pStmt.setInt(7, pHealth);
			pStmt.setInt(8, pCurrentHealth);
			pStmt.setString(9, pWeapon);
			pStmt.setString(10, pArmor);
			pStmt.setInt(11, pX);
			pStmt.setInt(12, pY);
			pStmt.setInt(13, pLevel);
			pStmt.setInt(14, pExpForNextLevel);
			pStmt.setInt(15, pExp);
			pStmt.setInt(16, pHunger);
			pStmt.setString(17, pBackpack);
			pStmt.setInt(18, pID);
			pStmt.setInt(19, uID);
			
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		}	
	}

}
