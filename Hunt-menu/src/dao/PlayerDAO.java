package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PlayerBean;
import util.DBUtil;

public class PlayerDAO {
	
	public void addPlayer(PlayerBean player) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"insert into Player (PlayerName) values (?)");
			pStmt.setString(1, player.getPlayerName());
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public void deletePlayer(PlayerBean player) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"delete from player where PlayerId = ?");
			pStmt.setInt(1, player.getPlayerId());
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public void updatePlayer(PlayerBean player) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"update player set PlayerName = ? where PlayerId = ?");
			pStmt.setString(1, player.getPlayerName());
			pStmt.setInt(2, player.getPlayerId());
			pStmt.executeQuery();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public List<PlayerBean> getAllPlayers() {
		Connection conn = null;
		List<PlayerBean> playerList = new ArrayList<PlayerBean>();
		try {
			conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("select * from player");
			
			while(result.next()) {
				PlayerBean player = new PlayerBean();
				player.setPlayerName(result.getString("PlayerName"));
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
	
	public PlayerBean getPlayerById(int playerId) {
		PlayerBean player = new PlayerBean();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"select * from Player where PlayerId = ?");
			pStmt.setInt(1, playerId);
			ResultSet result = pStmt.executeQuery();
			
			while(result.next()) {
				player.setPlayerName(result.getString("playerName"));
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		}
		return player;
	}
	
}
