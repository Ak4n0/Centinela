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
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/dygraph/2.1.0/dygraph.min.css" />
<link rel="stylesheet" href="css/graficas.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/dygraph/2.1.0/dygraph.min.js"></script>
<script src="js/interaction.js"></script>
<script src="js/main.js" defer></script>
</head>
<body>
	<!-- Menú side izquierda -->
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
								<li><a href="#" class="linkBlackbox" id="<%= blackbox.getIdentificador() %>">
									<% if(blackbox.getNombre() != null) {%>
										<%= blackbox.getNombre() %>
									<% } %>
								[ <%= blackbox.getIdentificador() %> ]</a>
							<% } %>
						<% } %>
						</ul>
					</li>
				</ul>

				<div class="footer">
					<p>
						<!-- Link de contacto con la administración de Centinela -->
						Contacta con nosotros:<br>
						<a href="mailto:centinela.soluciones@gmail.com"><i class="fas fa-envelope"></i> Administración Centinela</a>
					</p>
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
			<!-- Barra superior de la navegación -->
			<nav class="navbar navbar-expand-lg navbar-light bg-light" id="navbar-horizontal">
				<div id="container-bar" class="container-fluid">
					<!-- Botón para esconder/mostrar menú de la izquierda -->
					<button type="button" id="sidebarCollapse" class="btn btn-primary">
						<i class="fa fa-bars"></i> <span class="sr-only">Toggle
							Menu</span>
					</button>
					
					<!-- Botón para mostrar menú selección página blackbox -->
					<button id="btn-menu" class="btn btn-dark d-inline-block d-lg-none ml-auto"
						type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation" style="visibility: hidden">
						<i class="fa fa-bars"></i>
					</button>

					<div class="collapse navbar-collapse" id="navbarSupportedContent" style="visibility: hidden">
						<ul class="nav navbar-nav ml-auto">
							<li class="nav-item"><a id="btn-dashboard" class="nav-link" href="#">Dashboard</a></li>
							<li class="nav-item"><a id="btn-editar" class="nav-link" href="#">Editar blackbox</a></li>
						</ul>
					</div>
				</div>
			</nav>
			<div id="contenido">
				<h1 class="mb-4">Control</h1>
				<h5>Visualiza los datos enviados por tu blackbox</h5>
				<p>Las gráficas informan de los datos recogidos por sus sensores a través del tiempo</p>
				<h5>Modifica sus salidas</h5>
				<p>Activa y desactiva remotamente los dispositivos que estén conectados a la blackbox</p>
				<h5>Personaliza la visualización</h5>
				<p>Nombra los puertos I/O de forma representativa</p>
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
			
			$(".linkBlackbox").click(function() {
				// Obtener el nombre de la blackbox
				let uid = $(this).attr("id");
				$.get("ObtenerDatosBlackbox", {id: uid}, function(htmlExterno) {
					$("#contenido").html(htmlExterno)
				});
			});
		});
	</script>
</body>
</html>