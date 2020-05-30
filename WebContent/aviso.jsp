<%@ page import="modelo.enumeracion.TipoError" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%
	String titulo = (String) request.getAttribute("titulo");
	String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Centinela</title>
<%@ include file="bootstrap_header.html" %>
<link rel="stylesheet" type="text/css" href="css/aviso.css" />
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					<h3><%= titulo %></h3>
				</div>
				<div class="card-body">
					<%= mensaje %>
				</div>
				<div class="card-footer">
					<br>
					<div class="d-flex justify-content-center">
						<div class="form-group">
							<button type="button" class="btn float-right btn-yellow"
								onclick="location.href='Principal'">Entendido
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$("#navbar-horizontal").hide;
	</script>
</body>
</html>