<%@page import="modelo.pojo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Usuario usuario = (Usuario) request.getSession().getAttribute("usuario"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<strong>Usuario</strong>
<br>
<ul>
	<li>Id: <%= usuario.getId() %></li>
	<li>Nombre: <%= usuario.getNombre() %></li>
	<li>Email: <%= usuario.getEmail() %></li>
	<li>Contraseña: <%= usuario.getPasswd() %></li>
	<li>Es administrador: <%= usuario.isAdministrador() %>
</ul>
</body>
</html>