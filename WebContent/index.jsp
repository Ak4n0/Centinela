<%@ page import="modelo.pojo.UsuarioFullInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
	UsuarioFullInfo usuario = ((UsuarioFullInfo)session.getAttribute("usuario"));
	if(usuario != null) {
		response.sendRedirect("Principal");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Centinela</title>
<%@ include file="bootstrap_header.html" %>
<style>
	.carousel-item {
		height: 65vh;
		min-height: 350px;
		background: no-repeat center center scroll;
		-webkit-background-size: cover;
		-moz-background-size: cover;
		-o-background-size: cover;
		background-size: cover;
	}
	
	.carousel-caption {
		background-color: rgba(0,0,0,0.75);
	}
	
	.display-4 {
		font-weight: 500;
	}
	
</style>
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  <div class="container">
    <a class="navbar-brand" style="display: block-inline" href="#"><img src="images/logo-solo.png" height="50px" alt="Centinela"/><span style="font-size: 25;">Centinela</span></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link" href="Signin">Sign-in</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Login">Login</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="mailto:centinela.soluciones@gmail.com">Contacta</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<header>
  <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
      <!-- Slide One - Set the background image for this slide in the line below -->
      <div class="carousel-item active" style="background-image: url('images/interior-casa.jpg')">
        <div class="carousel-caption d-none d-md-block">
          <h3 class="display-4">Su casa siempre bajo su supervisión</h3>
          <p class="lead">Controle aparatos eléctricos desde cualquier lugar.</p>
        </div>
      </div>
      <!-- Slide Two - Set the background image for this slide in the line below -->
      <div class="carousel-item" style="background-image: url('images/jardin.jpg')">
        <div class="carousel-caption d-none d-md-block">
          <h3 class="display-4">Invernaderos y granjas</h3>
          <p class="lead">Sepa sus condiciones las 24 horas del día.</p>
        </div>
      </div>
      <!-- Slide Three - Set the background image for this slide in the line below -->
      <div class="carousel-item" style="background-image: url('images/multimetro.png')">
        <div class="carousel-caption d-none d-md-block">
          <h3 class="display-4">Tenga todo bajo su control</h3>
          <p class="lead">Póngase en contacto con nosotros y le haremos un estudio personalizado sobre sus necesidades.</p>
        </div>
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
  </div>
</header>

<!-- Page Content -->
<section class="py-5">
  <div class="container">
    <h1 class="font-weight-light">¿Qué és Centinela?</h1>
    <p class="lead">Centinela es la forma más rápida y barata que tiene para controlar sus dispositivos de forma remota.</p>
    <p>24 horas al día, 7 días a la semana todo está bajo su control.</p>
    <p>Podrá activar y desactivar dispositivos que estén conectados al sistema desde internet y con un simple click.</p>
    <p>Mediante la información recogida por los sensores podrá ver que ocurre en el entorno controlado por Centinela.</p>
    <p>Quien va a realizar la mágina son las blackbox. Estos dispositivos se conectan a los sensores y a los sistemas que quiera gobernar. Serán ellos quienes enviarán los datos a Centinela y recogerán las órdenes para dárselas a los aparatos que estén conectados.
    <p>Una blackbox es cualquier dispositivo que se entienda con Centinela. Puede ser un programa de ordenador, un microcomputador Raspberry Pi, Arduino u otro dispositovo programable que pueda conectarse a internet. También disponemos de hardware que se lo configuraremos a medida si que deba preocuparse de obtenerlo a parte.
    <p><a href="mailto:centinela.soluciones@gmail.com?subject=Información%20sobre%20Centinela">Póngase en contacto con nosotros</a>, así podremos ofrecerle la solución que más se ajuste a sus necesidades.</p>
    <hr>
    <h1 class="font-weight-light">¿Qué puedo hacer con Centinela?</h1>
    <p class="lead">Cualquier dispositivo eléctrico puede gobernarse con Centinela.</p>
    <p>Algunas ideas dónde aplicar Centinela:</p>
    <ul>
    	<li>Conocer el consumo de una instalación eléctrica.</li>
    	<li>Encender y apagar las luces de su casa, abrir y cerrar las persianas, activar la calefacción.</li>
    	<li>Controlar humedad, temperatura y la luminosidad de su invernadero o jardín personal.</li>
    	<li>Cerrar las ventanillas de su coche de forma remota, impedir que su motor siga funcionando.</li>
    </ul>
    <p>Y éstas son solo una pequeña muestra del potencial de Centinela.</p>
    <br>
    
  </div>
</section>

<footer class="page-footer font-small bg-dark pt-4">
	
	<!-- Copyright -->
	<div class="footer-copyright text-center py-3 text-light">© 2020 Copyright:
		Centinela
	</div>
	<!-- Copyright -->

</footer>
</body>
</html>