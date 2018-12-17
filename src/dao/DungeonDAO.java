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
	
	public void addDungeon(ArrayList<String> dungeonLayout, int dungeonLevel) {
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			
			// create dungeon in database with given dungeonLevel param
			PreparedStatement pStmt = conn.prepareStatement("insert into dungeonlevel (dungeonlevelid) values (?)");
			pStmt.setInt(1, dungeonLevel);
			
			int result1 = pStmt.executeUpdate();
			
			if (result1 > 0) {
				System.out.println("Successful dungeon Add");
			} else {
				System.out.println("Unsuccessful dungeon Add");
			}
			
			// loop through given dungeonLayout param and add dungeon rows to database for given dungeonLevel param
			for(int i = 0; i < dungeonLayout.size(); i++) {
				String row = dungeonLayout.get(i);
				
				try {
					PreparedStatement pStmt2 = conn.prepareStatement("insert into dungeonrow (dungeonrow, rowtiles, dungeonlevelid) values (?, ?, ?)");
					pStmt2.setInt(1, i);
					pStmt2.setString(2, row);
					pStmt2.setInt(3, dungeonLevel);
					
					int result2 = pStmt2.executeUpdate();
					
					if (result2 > 0) {
						System.out.println("Successful Row " + i + " Add");
						
					} else {
						System.out.println("Unsuccessful Row " + i + " Add");
						
					}
					
				} catch (SQLException ex) {
					ex.printStackTrace();
					
				} catch (Exception ex) {
					ex.printStackTrace();
					
				}
			} // end of row loop
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public ArrayList<String> getDungeonByLevel(int id) {
		//Dungeon dungeon = null;
		Connection conn = null;
		ArrayList<String> dLayout = new ArrayList<String>();
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM DungeonRow WHERE dungeonLevelId = ?");
			pStmt.setInt(1, id);
				
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				// add each row 
				dLayout.add(rs.getString("rowTiles"));
			}
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
		return dLayout;
	}

	public void updateDungeon(Dungeon dungeon) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("insert into dungeonlevel (DungeonLayout) values (?)");
			int result = pStmt.executeUpdate();
			if (result == 1) {
				// dungeon update is successfull
			} else {
				// dungeon update is unsuccessfull
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
}
