<%@page import="modelo.pojo.BlackboxAdminInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% List<BlackboxAdminInfo> listaBlackboxes = (List) request.getAttribute("listaBlackboxes"); %>

<h1>Lista de usuarios</h1>

<table class="table">
	<thead>
		<tr>
			<th scope="col">ID</th>
			<th scope="col">IDENTIFICADOR</th>
			<th scope="col">PROPIETARIO</th>
			<th scope="col">CONTRASEÑA</th>
			<th class="text-center" scope="col">EDITAR</th>
			<th class="text-center" scope="col">ELIMINAR</th>
		</tr>
	</thead>
	<tbody>
		<% for(BlackboxAdminInfo blackbox: listaBlackboxes)  { %>
		<tr>
			<th scope="row"><%= blackbox.getId() %></th>
			<td><%= blackbox.getIdentificador() %></td>
			<td><a href="EditarUsuario?id=<%= blackbox.getIdUsuario() %>"><%= blackbox.getNombreUsuario() %></a></td>
			<td><%= blackbox.getPasswd() %></td>
			<td class="text-center"><a href="#" onclick="editarBlackbox(<%= blackbox.getId() %>)"><i class="fas fa-pen-square"></i></a></td>
			<td class="text-center"><a href="#" onclick="borrarBlackbox(<%= blackbox.getId() %>)"><i class="fas fa-trash-alt"></i></a></td>
		</tr>
		<% } %>
	</tbody>
</table>
<p><a href="#" onclick="crearBlackbox()"><i class="fas fa-plus-square"></i> Añadir Blackbox</a></p>
<script>
	function crearBlackbox() {
		$.get("AddBlackbox", function(htmlExterno) {$("#content").html(htmlExterno)});
	}
	
	function editarBlackbox(id) {
		$.get("EditarBlackbox", {id: id}, function(htmlExterno) {$("#content").html(htmlExterno)});
	}
	
	function borrarBlackbox(id) {
		if(confirm("¿Estás seguro de querer borrar la Blackbox #" + id + "?")) {
			$.post("EliminarBlackbox", {id: id});
			$.get("ObtenerBlackboxes", function(respuesta) {
        		$("#content").html(respuesta);
        	});
		}
	}
</script>