package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.DBUtil;

public class UserDAO {
	
	public void addUser(User user) throws SQLException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("insert into User (Username, Password) values (?, ?)");
			pStmt.setString(1, user.getUsername());
			pStmt.setString(2, user.getPassword());
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public void deleteUser(User user) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("delete from User where UserId = ?");
			pStmt.setInt(1, user.getUserID());
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	public void updateUser(User user) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("update User set Username = ? where UserId = ?");
			pStmt.setString(1, user.getUsername());
			pStmt.setInt(2, user.getUserID());
			pStmt.executeQuery();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	/*
	 * 
	 * If we want to create a scoreboard with all users who have signed up and played
	 */
	public List<User> getAllUsers() {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		try {
			conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("select * from user");
			
			while(result.next()) {
				User user = new User();
				user.setUsername(result.getString("Username"));
				userList.add(user);
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
		return userList;
	}
	
	public boolean userExists(String username) {
		boolean userExists = false;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement pStmt = conn.prepareStatement("select * from User where username = ?");
			pStmt.setString(1, username);
			
			ResultSet result = pStmt.executeQuery();
			
			if (result.next()) {
				if(username.equalsIgnoreCase(result.getString("username"))) {
					userExists = true;
				}
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
		return userExists;
	}
	
	public boolean passwordMatches(String passwordToCheck, String username) {
		boolean passwordMatches = false;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("select * from User where username = ?");
			pStmt.setString(1, username);
			ResultSet result = pStmt.executeQuery();
			
			while(result.next()) {
				if (passwordToCheck.equals(result.getString("password"))) {
					passwordMatches = true;
				}
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			DBUtil.closeConnection();
		}
		return passwordMatches;
	}
	
	public User getUserById(int id) {
		User user = new User();
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement pStmt = conn.prepareStatement("select * from User where UserId = ?");
			pStmt.setInt(1, id);
			
			ResultSet result = pStmt.executeQuery();
			
			while(result.next()) {
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
			}
			
		} catch(SQLException ex) {
			ex.getMessage();
			
		} catch(Exception ex) {
			ex.getMessage();
			
		} finally {
			DBUtil.closeConnection();
			
		}
		return user;
	}

}
