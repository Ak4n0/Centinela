<%@ page import="modelo.enumeracion.TipoError" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%
	TipoError error = (TipoError) request.getAttribute("error");
	String titulo = null;
	String mensaje = null;
	if(error != null) {
		switch(error) {
		case DATOS_INCOMPLETOS:
	titulo = "Información incompleta";
	mensaje = "Es obligatorio rellenar todos los campos del formulario.";
	break;
		case CREDENCIALES:
	titulo = "Usuario no reconocido";
	mensaje = "El e-mail y/o la contrasenya son incorrectos.";
	break;
		default:
	titulo = "Error inesperado";
	mensaje = "Ha ocurrido un error durante su proceso de registro.";
		}
	}
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
					<h3>Login</h3>
				</div>
				<div class="card-body">
					<form action="Login" method="post">
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-envelope"></i></span>
							</div>
							<input type="email" name="email" class="form-control" maxlength="320" 
								placeholder="e-mail" required>
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" name="passwd" class="form-control" maxlength="25" 
								placeholder="password" required>
						</div>
<!-- 						<div class="row align-items-center remember"> -->
<!-- 							<input type="checkbox">Recuérdame -->
<!-- 						</div> -->

<!-- 					En su lugar se usar <br> -->
						<br>
						<div class="form-group">
							<input type="submit" value="Login"
								class="btn float-right btn-yellow">
						</div>
					</form>
				</div>
				<div class="card-footer">
					<div class="d-flex justify-content-center links">
						¿No tiene cuenta?<a href="Signin">Regístrese</a>
					</div>
					<div class="d-flex justify-content-center">
						<a href="PasswordOlvidado">Olvidé mi contraseña</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<% if(error != null) {%>
	<div class="modal" id="modalError" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title"><%= titulo %></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p><%= mensaje %></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						aria-label="Entendido">Entendido</button>
				</div>
			</div>
		</div>
	</div>
 	<script>
		$('#modalError').modal('show');
	</script>
	<% } %>
</body>
</html>