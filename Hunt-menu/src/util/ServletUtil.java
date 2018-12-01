package util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtil {

	public static void forward(String url, HttpServletRequest req, HttpServletResponse resp) {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	public static void redirect(String url, HttpServletResponse resp) {
		try {
			resp.sendRedirect(url);
		} catch (IOException e) {
			e.getMessage();
		}
	}
}
