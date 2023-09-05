<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Suivi des repas - Ajouter</title>
</head>
<body>
	<h1>Ajouter un repas</h1>
	
	<% if(request.getAttribute("message") != null) { %>
	<p><% out.print(request.getAttribute("message")); %>
	<%} %>
	
	<form action="<%= request.getContextPath() %>/ajouter">
		<label for="date">Date :</label>
		<input type="date" required name="date" id="date">
		<label for="time">Heure :</label>
		<input type="time" required name="time" id="time">
		<label for="aliments">Aliments (séparés par une virgule et pas d'espaces) :</label>
		<input type="text" required name="aliments" id="aliments">
		<input type="submit">
	</form>
	
	<a href="<%= request.getContextPath() %>/">Annuler</a>
</body>
</html>