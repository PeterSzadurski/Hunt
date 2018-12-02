package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Dungeon;
import model.Player;
import util.DBUtil;

public class DungeonDAO {
	
	public void addDungeon(Dungeon dungeon) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("");
			
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public void removeDungeon(Dungeon dungeon) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("");
			
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public void updateDungeon(Dungeon dungeon) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("");
			
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public Dungeon getDungeonByID(int id) {
		Dungeon dungeon = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("");
			
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
		return dungeon;
	}

}
