<% @page import="modelo.pojo.Usuario"%>
<% @ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Usuario usuario = ((Usuario)session.getAttribute("usuario")); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Centinela</title>
<%@ include file="bootstrap_header.html" %>
</head>
<body>
<%@ include file="header.jsp" %>
<strong>Usuario</strong>
<br>
<ul>
	<li>Id: <%= usuario.getId() %></li>
	<li>Nombre: <%= usuario.getNombre() %></li>
	<li>Email: <%= usuario.getEmail() %></li>
	<li>Contraseña: <%= usuario.getPasswd() %></li>
	<li>Es administrador: <%= usuario.isAdministrador() %></li>
</ul>
</body>
</html>