<%@page import="fr.eni.suividesrepas.bo.Aliment"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.suividesrepas.bo.Repas"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Suivi des repas - Visualiser</title>
</head>
<body>
	<h1>Visualiser les repas</h1>
	
		<% if(request.getAttribute("message") != null) { %>
	<p><% out.print(request.getAttribute("message")); %>
	<%} %>

	<table>
		<tr>
			<th>Date</th>
			<th>Heure</th>
			<th>Actions</th>
		</tr>

		<%
		List<Repas> repas = (List<Repas>) request.getAttribute("repas");
		for (Repas r : repas) {
		%>
		<tr>
			<td>
				<%
				out.print(r.getrDate());
				%>
			</td>
			<td>
				<%
				out.print(r.getrTime());
				%>
			</td>
			<td><a
				href="<%=request.getContextPath()%>/visualiser?repasId=<%=r.getId()%>">Détails</a></td>
		</tr>

		<%
		if (request.getParameter("repasId") != null){
			
			int repasIdFromParameter = Integer.parseInt(request.getParameter("repasId"));
		    
		    if (repasIdFromParameter == r.getId()) {
		%>
		<tr>
			<%
			for (Aliment a : r.getAliments()) {
			%>
			<td>
				<%
				out.print(a.getAliment());
				%>
			</td>
			<%
			}
			%>
		</tr>
		<%
		}
		%>
		<%
		}
		%>
		<%
		}
		%>
	</table>

	<a href="<%=request.getContextPath()%>/ajouter">Ajouter un
		nouveau repas</a>
	<a href="<%=request.getContextPath()%>/">Retour à l'accueil</a>

</body>
</html>