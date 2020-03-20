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
					<h3>Login</h3>
					<!-- Íconos innecesarios por ahora -->
<!-- 					<div class="d-flex justify-content-end social_icon"> -->
<!-- 						<span><i class="fab fa-facebook-square"></i></span> -->
<!-- 						<span><i class="fab fa-google-plus-square"></i></span> -->
<!-- 						<span><i class="fab fa-twitter-square"></i></span> -->
<!-- 					</div> -->
				</div>
				<div class="card-body">
					<form>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="email" class="form-control" placeholder="e-mail">
							
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" class="form-control" placeholder="password">
						</div>
						<!-- No usado, por no haber cookies -->
<!-- 						<div class="row align-items-center remember"> -->
<!-- 							<input type="checkbox">Remember Me -->
<!-- 						</div> -->
						<!-- Sustitución del código anterior -->
						<br>
						<div class="form-group">
							<input type="submit" value="Login" class="btn float-right login_btn">
						</div>
					</form>
				</div>
				<div class="card-footer">
						<!-- El usuario no se puede registrar -->
<!-- 					<div class="d-flex justify-content-center links"> -->
<!-- 						Don't have an account?<a href="#">Sign Up</a> -->
<!-- 					</div> -->
					<div class="d-flex justify-content-center">
						<a href="#">Forgot your password?</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>