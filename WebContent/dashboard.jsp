<%@page import="modelo.pojo.UsuarioFullInfo"%>
<%@page import="modelo.pojo.BlackboxAdminInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% List<BlackboxAdminInfo> listaBlackboxes = (List<BlackboxAdminInfo>) request.getAttribute("listaBlackboxes"); %>

<!DOCTYPE html>
<html lang="es">
<head>
<title>Centinela - Dashboard</title>
<%@ include file="bootstrap_header.html" %>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/main.css">
<script src="js/main.js" defer></script>
</head>
<body>

	<div class="wrapper d-flex align-items-stretch">
		<nav id="sidebar">
			<div class="p-4 pt-5">
				<a href="#" class="img logo mb-5"
					style="background-image: url(images/logo-celeste.png);"></a>
				<ul class="list-unstyled components mb-5">
					<li class="active"><a href="#cuentaMenu"
						data-toggle="collapse" aria-expanded="false"
						class="dropdown-toggle">Mi cuenta</a>
						<ul class="collapse list-unstyled" id="cuentaMenu">
							<li><a href="#" id="administrarUsuario">Administrar</a></li>
							<li><a href="Logout">Cerrar sesión</a></li>
						</ul>
					</li>
					<li class="active"><a href="#blackboxesMenu"
						data-toggle="collapse" aria-expanded="false"
						class="dropdown-toggle">Mis blackboxes</a>
						<ul class="collapse list-unstyled" id="blackboxesMenu">
						<% if(listaBlackboxes.isEmpty()) { %>
							<li>No tiene equipos afiliados</li>
						<% } else { %>
							<% for(BlackboxAdminInfo blackbox: listaBlackboxes) { %>
								<li><a href="#">
									<% if(blackbox.getNombre() != null) {%>
										<%= blackbox.getNombre() %>
									<% } %>
								[ <%= blackbox.getIdentificador() %> ]</a>
							<% } %>
						<% } %>
						</ul>
					</li>
					<li class="active"><a href="#IOMenu"
						data-toggle="collapse" aria-expanded="false"
						class="dropdown-toggle">Visualición</a>
						<ul class="collapse list-unstyled" id="IOMenu">
							<li><input type="checkbox" id="verSalida1"> Entradas</li>
							<li><input type="checkbox" id="verSalida1"> Salida 1</li>
							<li><input type="checkbox" id="verSalida1"> Salida 2</li>
							<li><input type="checkbox" id="verSalida1"> Salida 3</li>
							<li><input type="checkbox" id="verSalida1"> Salida 4</li>
						</ul>
					</li>
				</ul>

				<div class="footer">
					<p>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;
						<script>
							document.write(new Date().getFullYear());
						</script>
						All rights reserved | This template is made with <i
							class="icon-heart" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">Colorlib.com</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</p>
				</div>

			</div>
		</nav>

		<!-- Page Content  -->
		<div id="content" class="p-4 p-md-5">

			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">

					<button type="button" id="sidebarCollapse" class="btn btn-primary">
						<i class="fa fa-bars"></i> <span class="sr-only">Toggle
							Menu</span>
					</button>
					<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
						type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<i class="fa fa-bars"></i>
					</button>

					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="nav navbar-nav ml-auto">
							<li class="nav-item active"><a class="nav-link" href="#">Home</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="#">About</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="#">Portfolio</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="#">Contact</a>
							</li>
						</ul>
					</div>
				</div>
			</nav>

			<h1 class="mb-4">Control</h1>
			<h5>Visualiza los datos enviados por tu blackbox</h5>
			<p>Las gráficas informan de los datos recogidos por sus sensores a través del tiempo</p>
			<h5>Modifica sus salidas</h5>
			<p>Activa y desactiva remotamente los dispositivos que estén conectados a la blackbox</p>
			<h5>Personaliza la visualización</h5>
			<p>Nombra los puertos I/O de forma representativa</p>
			
		</div>
	</div>
	<script>
		$(function() {
			$("#administrarUsuario").click(function() {
				$.get("EditarUsuario", {id: <%= ((UsuarioFullInfo) session.getAttribute("usuario")).getId() %>}, function(htmlExterno) {
					$("#content").html(htmlExterno);
				});
			});
			$("#linkUsuarios").click(function() {
				$.get("ObtenerUsuarios", function(htmlExterno) {
					$("#content").html(htmlExterno)
				});
			});
			
			$("#linkBlackboxes").click(function() {
				$.get("ObtenerBlackboxes", function(htmlExterno) {
					$("#content").html(htmlExterno)
				});
			});
		});
	</script>
</body>
</html>