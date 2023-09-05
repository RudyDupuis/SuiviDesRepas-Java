package fr.eni.suividesrepas.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.suividesrepas.bll.RepasManager;
import fr.eni.suividesrepas.bo.Aliment;
import fr.eni.suividesrepas.bo.Repas;


/**
 * Servlet implementation class ajouter
 */
@WebServlet("/ajouter")
public class ajouter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/WEB-INF/Ajout.jsp");
		
		if(request.getParameter("date") == null || request.getParameter("time") == null || request.getParameter("aliments") == null) {
			rd.forward(request, response);
			return;
		}
		
		Repas repas = new Repas(Date.valueOf(request.getParameter("date")), Time.valueOf(LocalTime.parse(request.getParameter("time"))));
		
		String[] aliments = request.getParameter("aliments").split(",");
		
		for(String a : aliments) {
			repas.addAliments(new Aliment(a.trim()));
		}
		
		RepasManager rmng = new RepasManager();
		String erreur = null;
		
		try {
			rmng.addRepas(repas);
		} catch (Exception e) {
			e.printStackTrace();
			erreur = e.getMessage();
		}
		
		if(erreur != null) {
			request.setAttribute("message", erreur);
		} else {
			request.setAttribute("message", "Ajout réussi !");
		}
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
