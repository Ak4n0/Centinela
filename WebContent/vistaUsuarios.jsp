<%@page import="modelo.pojo.Usuario"%>
<%@page import="modelo.pojo.UsuarioAdminInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% List<UsuarioAdminInfo> listaUsuarios = (List<UsuarioAdminInfo>) request.getAttribute("listaUsuarios"); %>

<h1>Lista de usuarios</h1>

<table class="table">
	<thead>
		<tr>
			<th scope="col">ID</th>
			<th scope="col">NOMBRE</th>
			<th scope="col">EMAIL</th>
			<th class="text-center" scope="col">N. BLACKBOXES</th>
			<th class="text-center" scope="col">EDITAR</th>
			<th class="text-center" scope="col">ELIMINAR</th>
		</tr>
	</thead>
	<tbody>
		<%
			for(UsuarioAdminInfo usuario: listaUsuarios)  {
				if(usuario.getId() == ((Usuario)session.getAttribute("usuario")).getId()) {
					continue;
				}
		%>
		<tr>
			<th scope="row"><%= usuario.getId() %></th>
			<td><%= usuario.getNombre() %></td>
			<td><%= usuario.getEmail() %></td>
			<td class="text-center"><%= usuario.getNumBlackboxes() %></td>
			<td class="text-center"><a href="#"><i class="fas fa-user-edit"></i></a></td>
			<td class="text-center"><a href="#"><i class="fas fa-user-slash"></i></a></td>
		</tr>
		<% } %>
	</tbody>
</table>