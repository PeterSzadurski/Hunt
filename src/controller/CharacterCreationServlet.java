package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PlayerDAO;
import model.Item;
import model.Player;
import model.User;
import util.ServletUtil;

/**
 * Servlet implementation class CharacterCreationServlet
 */
@WebServlet("/CharacterCreationServlet")
public class CharacterCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CharacterCreationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get parameters
		String name = request.getParameter("name");
		int str = Integer.parseInt(request.getParameter("str"));
		int agi = Integer.parseInt(request.getParameter("agi"));
		int vit = Integer.parseInt(request.getParameter("vit"));
		
		Player player = new Player(name, str, agi, vit, '@', "#FFFF00", 0, 0);
		player.setIsSolid(true);
		
		// Store player in session
		HttpSession session = request.getSession();
		session.setAttribute("player", player);
		
		// Add player to database
		//PlayerDAO pDAO = new PlayerDAO();
		User user = (User)session.getAttribute("user");
	//	pDAO.addPlayer(player, user.getUserID());
		//pDAO.addPlayer(player, user.getUsername());
		//User user = (User)session.getAttribute("user");
		//pDAO.savePlayerAttributes(player, user);
		System.out.println("In character creation. Player is: " + player.getName());
		
		ServletUtil.forward("Index.jsp", request, response);
	}

}
