package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;
import util.ServletUtil;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int formNum = Integer.parseInt(request.getParameter("formNumber"));
		
		HttpSession session = request.getSession();
		
		UserDAO userDAO = new UserDAO();
		
		// create account is formNum == 1
		if (formNum == 1) {
			try {
				// check if user doesnt exist
				if (!userDAO.userExists(username)) {
					
					// create a new user with form params 
					User user = new User(username, password);
					
					// add user to database
					userDAO.addUser(user);
					
					// put username in a session attribute
					session.setAttribute("username", username);
					
					// redirect to game 
					ServletUtil.redirect("Index.jsp", response);
				} else if (userDAO.userExists(username)){ // if user does exist
					
					// create a user exists session attribute
					request.setAttribute("userExists", "User already exists.");
					
					// redirect to create an account 
					ServletUtil.forward("create-account.jsp", request, response);
				}	
			} catch(SQLException ex) {
				ex.getMessage();
			} catch(Exception ex) {
				ex.getMessage();
			}
			
		// login is formNum == 2
		} else if (formNum == 2) { 
			try {
				// check if user exists
				if (userDAO.userExists(username)) {
					
					// check if password matches 
					if(userDAO.passwordMatches(password, username)) { // might have to relook at lower case / upper case problem
						
						// put username in a session attribute
						request.setAttribute("username", username);
						ServletUtil.forward("characterCreation.html", request, response);
					} else {
						// password doesnt match
						request.setAttribute("passwordWrong", "Password is wrong.");
						ServletUtil.forward("login.jsp", request, response);
					}
				} else { // username doesnt exist
					
					// create a user exists session attribute
					request.setAttribute("userExists", "User does not exist.");
					ServletUtil.forward("login.jsp", request, response);
				}
			} catch (Exception ex) {
				ex.getMessage();
			}
		} else {
			// how did user get here
		}	
	}

}
