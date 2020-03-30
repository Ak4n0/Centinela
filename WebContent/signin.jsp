<%@ page import="modelo.enumeracion.TipoError" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

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
					<h3>Nuevo usuario</h3>
					<!-- Íconos innecesarios por ahora -->
					<!-- 					<div class="d-flex justify-content-end social_icon"> -->
					<!-- 						<span><i class="fab fa-facebook-square"></i></span> -->
					<!-- 						<span><i class="fab fa-google-plus-square"></i></span> -->
					<!-- 						<span><i class="fab fa-twitter-square"></i></span> -->
					<!-- 					</div> -->
				</div>
				<div class="card-body">
					<form action="Signin" id="formulario" method="post">
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" name="nombre" class="form-control"
								placeholder="nombre" required>
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-envelope"></i></span>
							</div>
							<input type="email" name="email" class="form-control"
								placeholder="e-mail" required>
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" name="passwd" class="form-control"
								placeholder="contraseña" required>
						</div>
						<div class="form-group">
							<input type="submit" value="Registrar"
								class="btn float-right btn-yellow">
						</div>
					</form>
				</div>
				<div class="card-footer">
				</div>
			</div>
		</div>
	</div>
</body>
</html>