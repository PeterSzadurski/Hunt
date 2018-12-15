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
			
			PreparedStatement pStmt = conn.prepareStatement("insert into dungeon (dungeonlevelid) values (?)");
			pStmt.setInt(1, dungeonLevel);
			
			int result1 = pStmt.executeUpdate();
			
			if (result1 > 0) {
				System.out.println("Successful Add");
			} else {
				System.out.println("Unsuccessful Add");
			}
			
			for(int i = 0; i < dungeonLayout.size(); i++) {
				String row = dungeonLayout.get(i);
				
				try {
					PreparedStatement pStmt2 = conn.prepareStatement("insert into dungeonlayoutrow (dungeonlayoutrowid, dungeonlayoutrow, dungeonlevelid) values (?, ?, ?)");
					pStmt2.setInt(1, i + 1);
					pStmt2.setString(2, row);
					pStmt2 .setInt(3, dungeonLevel);
					
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
	
	public void removeDungeon(int id) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("delete from dungeon where dungeonlevelid = ?");
			pStmt.setInt(1, id);
			
			int result = pStmt.executeUpdate();
			if (result == 1) {
				// dungeon removal is successfull
			} else {
				// dungeon removal is unsuccessfull
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	// do we need this? idk what we would need to update in a dungeon
	public void updateDungeon(Dungeon dungeon) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("insert into dungeon (DungeonLayout) values (?)");
			pStmt.setString(1, dungeon.getLayoutAsStr());
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
	
	// change return type to dungeon 
	public Dungeon getDungeonByID(int id) {
		Dungeon dungeon = null;
		Connection conn = null;
		ArrayList<String> dLayout = new ArrayList<String>();
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("select * from dungeonlayoutrow dlr join dungeon d on dlr.dungeonlevelid = d.dungeonlevelid where dlr.dungeonlevelid = ?");
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
		
			while(rs.next()) {
				// add each row 
				dLayout.add(rs.getString("dungeonlayoutrow"));
			}
			
			dungeon = new Dungeon(dLayout);
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
