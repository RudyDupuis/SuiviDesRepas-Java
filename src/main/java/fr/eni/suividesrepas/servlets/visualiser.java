package fr.eni.suividesrepas.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.suividesrepas.bll.RepasManager;
import fr.eni.suividesrepas.bo.Repas;

/**
 * Servlet implementation class visualiser
 */
@WebServlet("/visualiser")
public class visualiser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RepasManager rmng = new RepasManager();
		List<Repas> repas = null;
		
		if(request.getParameter("idRepas") != null) {
			request.setAttribute("idRepas", request.getParameter("idRepas"));
		}
		
		String erreur = null;
		
		try {
			repas = rmng.getRepas();
		} catch (Exception e) {
			e.printStackTrace();
			erreur = e.getMessage();
		}
		
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/WEB-INF/Visuel.jsp");
		
		request.setAttribute("repas", repas);
		
		if(erreur != null) {
			request.setAttribute("message", erreur);
		}
		
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
