<%@page import="modelo.pojo.UsuarioFullInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<title>Centinela - administración</title>
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
					<li><a href="#" id="linkUsuarios">Usuarios</a></li>
					<li><a href="#" id="linkBlackboxes">Blackboxes</a></li>
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

			<nav class="navbar navbar-expand-lg navbar-light bg-light" id="navbar-horizontal">
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
							<li class="nav-item"><a class="nav-link" id="menu-blackbox" href="#">Adm. Blackbox</a>
							</li>
							<li class="nav-item"><a class="nav-link" id="menu-usuarios" href="#">Adm. Usuarios</a>
							</li>
						</ul>
					</div>
				</div>
			</nav>
			<div id="contenido">
				<h1 class="mb-4">Administración</h1>
				<h5>Ten en cuenta lo siguiente</h5>
				<ol>
					<li>Puede editar y eliminar usuarios ya registrados.</li>
					<li>Puedes crear, editar y eliminar blackbox y reasignarlas a los diferentes usuarios.</li>
					<li>Tenga en cuenta que si modifica los datos de un usuario deberá
						comunicarselo pues no se le realiza ningún aviso automático de ello.</li>
					<li>Tenga cuidado al modificar una blackbox pues un cambio en el identificador único
						o la contraseña sin cambiar los datos internos en el aparato producirá que este
						no se pueda conectar con Centinela</li>
					<li>Igualmente revise la asignación de las blackbox para no darle la propiedad
						al usuario erróneo.</li>
					<li>En la lista de propietarios de la blackbox únicamente aparecen
						los usuarios que han validado su cuenta de email. Si un usuario
						acaba de hacerse una cuenta pero no está validada no se le puede
						asignar un equipo.</li>
				</ol>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$("#administrarUsuario").click(function() {
				$.get("EditarUsuario", {id: <%= ((UsuarioFullInfo) session.getAttribute("usuario")).getId() %>}, function(htmlExterno) {
					$("#contenido").html(htmlExterno);
				});
				document.getElementById("btn-menu").style.visibility = "hidden";
			});
			
			$("#linkUsuarios").click(function() {
				obtenerUsuarios();
			});
			
			$("#linkBlackboxes").click(function() {
				obtenerBlackbox();
			});
			
			$("#menu-usuarios").click(function() {
				obtenerUsuarios();
			});
			
			$("#menu-blackbox").click(function() {
				obtenerBlackbox();
			});
			
			function obtenerUsuarios() {
				$.get("ObtenerUsuarios", function(htmlExterno) {
					$("#contenido").html(htmlExterno)
				});
			}
			
			function obtenerBlackbox() {
				$.get("ObtenerBlackboxes", function(htmlExterno) {
					$("#contenido").html(htmlExterno)
				});
			}
		});
	</script>
</body>
</html>