<%@page import="modelo.pojo.Usuario"%>
<header>
<div class="d-flex flex-row justify-content-between">
	<h1>Centinela</h1>
	<div class="d-block">
	<% if(session == null) { %>
		<a href="Login">Login</a> - 
		<a href="Signin">Signin</a>
	<% } else { %>
		<%= ((Usuario)session.getAttribute("usuario")).getNombre() %><br>
		<a href="Logout">Logout</a>
	<% } %>
	</div>
</div>
</header>