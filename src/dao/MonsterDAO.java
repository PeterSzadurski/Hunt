package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Monster;
import util.DBUtil;

public class MonsterDAO {
	
	public void addMonster(Monster m) {
		Connection conn = null;
		System.out.println("inside addMonster()");
		System.out.println(m.getName());
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"insert into Monster ("
					+ "MonsterType, "
					+ "CurrentDungeonLevelId, "
					+ "Strength, "
					+ "Agility, "
					+ "Vitality, "
					+ "Damage, "
					+ "Speed, "
					+ "HealthPoints, "
					+ "CurrentHealthPoints, "
					+ "Weapon, "
					+ "Armor, "
					+ "PosX, "
					+ "PosY, "
					+ "ExpOnKill, "
					+ "TurnCount"
					+ ") values "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pStmt.setString(1, m.getName());
			pStmt.setInt(2, m.getCurrentLevel());
			pStmt.setInt(3, m.getStrength());
			pStmt.setInt(4, m.getAgility());
			pStmt.setInt(5, m.getVitality());
			pStmt.setInt(6, m.getDamage());
			pStmt.setInt(7, m.getMoveSpeed());
			pStmt.setInt(8, m.getHp());
			pStmt.setInt(9, m.getCurHp());
			pStmt.setString(10, m.getWeapon().toString());
			pStmt.setString(11, m.getArmor().toString());
			pStmt.setInt(12, m.getX());
			pStmt.setInt(13, m.getY());
			pStmt.setInt(14, m.getExpOnKill());
			pStmt.setDouble(15, m.getTurnCount());
			int result = pStmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("Successfully added monster");
			} else {
				System.out.println("Unsuccessfully added monster");
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
		System.out.println("end of addMonster()");
	}
	
	public void deleteMonster(Monster monster) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"delete from monster where monsterid = ?");
			pStmt.setInt(1, monster.getId());
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public void updateMonster(Monster monster) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("");
			pStmt.executeQuery();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public List<Monster> getAllMonsters() {
		Connection conn = null;
		List<Monster> monsterList = new ArrayList<Monster>();
		try {
			conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("select * from monster");
			
			while(result.next()) {
				Monster monster = new Monster();
				monster.setType(result.getString("MonsterType"));
				
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
		return monsterList;
	}
	
	public Monster getMonsterById(int monsterId) {
		Monster monster = new Monster();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"select * from monster where MonsterId = ?");
			pStmt.setInt(1, monsterId);
			ResultSet result = pStmt.executeQuery();
			
			while(result.next()) {
				
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		}
		return monster;
	}
	
	public void saveMonsterAttributes(Monster m) {
		int id = m.getId();
		int currentDungeonLevelID = m.getCurrentLevel();
		int pStrength = m.getStrength();
		int pAgility = m.getAgility();
		int pVitality = m.getVitality();
		int pDamage = m.getDamage();
		int pSpeed = m.getMoveSpeed();
		int pHealth = m.getHp();
		int pCurrentHealth = m.getCurHp();
		String pWeapon = m.getWeapon().toString();
		String pArmor = m.getArmor().toString();
		int x = m.getX();
		int y = m.getY();
		int expOnKill = m.getExpOnKill();
		double turnCount = m.getTurnCount();
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
		
			PreparedStatement pStmt = conn.prepareStatement("insert into "
					+ "Monster ("
					+ "CurrentDungeonLevelId," // 1
					+ "MonsterStrength," // 2 
					+ "MonsterAgility," // 3
					+ "MonsterVitality," // 4
					+ "MonsterDamage," // 5
					+ "MonsterSpeed," // 6
					+ "MonsterHealthPoints," // 7
					+ "MonsterCurrentHealth," // 8
					+ "MonsterWeapon," // 9
					+ "MonsterArmor," // 10
					+ "MonsterPosX," // 11
					+ "MonsterPosY," // 12
					+ "ExpOnKill,"
					+ "TurnCount" // 14
					+ ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) where MonsterId = ?"); 
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
			pStmt.setInt(11, x);
			pStmt.setInt(12, y);
			pStmt.setInt(13, expOnKill);
			pStmt.setDouble(14, turnCount);
			pStmt.setInt(15, id);
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		}	
	}

}