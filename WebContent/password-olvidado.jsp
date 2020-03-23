<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Centinela</title>
<%@include file="bootstrap_header.html"%>
<link rel="stylesheet" type="text/css" href="css/login.css" />
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					<h3>Restablecer contraseña</h3>
				</div>
				<div class="card-body">
					<form action="PasswordOlvidado" method="post">
						<br>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-envelope"></i></span>
							</div>
							<input type="email" class="form-control" placeholder="e-mail" required>
						</div>
						
						<br>
						<div class="form-group">
							<input type="submit" value="Enviar" class="btn float-right login_btn">
						</div>
					</form>
				</div>
				<div class="card-footer">
					<div class="d-flex justify-content-center text-white justify-content-center">
						Recibirá en su e-mail las instrucciones para restablecer la contraseña
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>