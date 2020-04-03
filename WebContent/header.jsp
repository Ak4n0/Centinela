<%@ page import="modelo.pojo.UsuarioFullInfo" %>
<header>
<div class="d-flex flex-row justify-content-between">
	<h1>Centinela</h1>
	<div class="d-block">
	<%
		UsuarioFullInfo u = null;
			if(session != null) { 
		u = (UsuarioFullInfo)session.getAttribute("usuario");
			}
			if(u == null) {
	%>
			<a href="Login">Login</a> - 
			<a href="Signin">Signin</a>
		<% } else { %>
			<%= u.getNombre() %><br>
			<a href="Logout">Logout</a>
	<% } %>
	</div>
</div>
</header>