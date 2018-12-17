package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Projectile;
import util.DBUtil;

public class ProjectileDAO {
	
	public ArrayList<Projectile> loadAllProjectiles() {
		//Dungeon dungeon = null;
		Connection conn = null;
		ArrayList<Projectile> p = new ArrayList<Projectile>();
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM Projectile");
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				// add each row
				Projectile proj = new Projectile();
				proj.setColor(rs.getString("color"));
				proj.setX(rs.getInt("x"));
				proj.setY(rs.getInt("y"));
				proj.setMovX(rs.getInt("movx"));
				proj.setMovY(rs.getInt("movY"));
				proj.setDamage(rs.getInt("damge"));
				proj.setAgility(rs.getInt("agility"));
			}
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
		return p;
	}

	public void saveProjectiles(ArrayList<Projectile> p) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			for (int i = 0; i < p.size(); i++) {
				PreparedStatement pStmt = conn.prepareStatement("insert into Projectile (color, x, y, movx, movy, agility, damage) values (?, ?, ?, ?, ?, ?, ?)");
				pStmt.setString(1, String.valueOf(p.get(i).getIcon()));
				pStmt.setInt(2, p.get(i).getX());
				pStmt.setInt(3, p.get(i).getY());
				pStmt.setInt(4, p.get(i).getMovX());
				pStmt.setInt(5, p.get(i).getMovY());
				pStmt.setInt(6, p.get(i).getAgility());
				pStmt.setInt(7, p.get(i).getDamage());
				
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

