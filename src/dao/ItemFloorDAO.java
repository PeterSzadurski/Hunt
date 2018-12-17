package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Item;
import model.ItemFloor;
import model.Items;
import util.DBUtil;

public class ItemFloorDAO {
	
	public ArrayList<ItemFloor> loadAllItemFloor() {
		//Dungeon dungeon = null;
		Connection conn = null;
		ArrayList<ItemFloor> iflr = new ArrayList<ItemFloor>();
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM ItemFloor");
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				// add each row
				Item item = null ; 
				switch(rs.getString("item")) {
					case "smallPotion":
						item = Items.getSmallPotion();
						break;
					case "largePotion":
						item = Items.getLargePotion();
						break;
					case "smallPoison":
						item = Items.getSmallPoison();
						break;
					case "scrollTeleportation":
						item = Items.getScrollTeleportation();
						break;
					case "scrollFireball":
						item = Items.getScrollFireball();
						break;
					case "scrollGreaterFireball":
						item = Items.getScrollGreaterFireball();
						break;
					case "scrollMinorFrozenTime":
						item = Items.getScrollMinorFrozenTime();
						break;
					case "scrollFrozenTime":
						item = Items.getScrollFrozenTime();
						break;
					case "scrollGreaterFrozenTime":
						item = Items.getScrollGreaterFrozenTime();
						break;
					case "scrollMasterFrozenTime":
						item = Items.getScrollMasterFrozenTime();
						break;
					case "potionMinorOfImprovement":
						item = Items.getPotionMinorOfImprovement();
						break;
					case "winItem":
						item = Items.getWinItem();
						break;
				}
				ItemFloor i = new ItemFloor(item, rs.getInt("x"), rs.getInt("y"));
				iflr.add(i);
			}
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
		return iflr;
	}

	public void saveItemFloor(ArrayList<ItemFloor> iflr) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			for (int i = 0; i < iflr.size(); i++) {
				PreparedStatement pStmt = conn.prepareStatement("insert into ItemFloor (item, x, y)");
				pStmt.setString(1, iflr.get(i).getItem().getName());
				pStmt.setInt(2, iflr.get(i).getX());
				pStmt.setInt(3, iflr.get(i).getY());
				
				int result = pStmt.executeUpdate();
				if (result == 1) {
					System.out.println("successfully saved projectile");
				} else {
					System.out.println("unsuccessfully saved projectile");
				}
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

