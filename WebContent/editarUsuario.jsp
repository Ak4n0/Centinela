<%@page import="modelo.pojo.UsuarioFullInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	UsuarioFullInfo usuario = (UsuarioFullInfo) request.getAttribute("usuario");
%>

<h1>Modificar usuario</h1>

<form class="needs-validation" novalidate>
  <div class="form-group">
    <label for="nombre">Nombre:</label>
    <input type="text" class="form-control" id="nombre" maxlength="100" placeholder="Introduce tu nombre" name="nombre" required>
    <div class="valid-feedback">Correcto.</div>
    <div class="invalid-feedback">Debes rellenar este campo.</div>
  </div>
  <div class="form-group">
    <label for="email">Email:</label>
    <input type="email" class="form-control" id="email" maxlength="320" placeholder="Introduce tu email" name="email" required>
    <div class="valid-feedback">Correcto.</div>
    <div class="invalid-feedback">Debes rellenar este campo.</div>
  </div>
  <div class="form-group">
    <label for="passwd">Contraseña:</label>
    <input type="password" class="form-control" id="passwd" maxlength="25" placeholder="Introduce tu contraseña" name="passwd" required>
    <div class="valid-feedback">Correcto.</div>
    <div class="invalid-feedback">Debes rellenar este campo.</div>
  </div>
  <div class="form-check">
    <label class="form-check-label">
      <input type="checkbox" class="form-check-input" id="verPasswd" onclick="swapVerPassword()" value="">Ver contraseña
    </label>
  </div>
  <button type="submit" class="btn btn-primary">Modificar</button>
  <button type="button" class="btn btn-danger" onclick="resetear()">Resetear</button>
</form>

<script>
	function swapVerPassword() {
		if($("#verPasswd").is(":checked")) {
			$("#passwd").prop("type", "text");
		} else {
			$("#passwd").prop("type", "password");
		}
	}
	
	function resetear() {
		$("#nombre").val("<%= usuario.getNombre() %>");
		$("#passwd").val("<%= usuario.getPasswd() %>");
		$("#email").val("<%= usuario.getEmail() %>");
	}
	
    // Loop over them and prevent submission
    (function() {
    	resetear();
    	let forms = $(".needs-validation");
		Array.prototype.filter.call(forms, function(form) {
			form.addEventListener("submit", function(event) {
				event.preventDefault();
				if (form.checkValidity() === false) {
					event.stopPropagation();
				} else {
					let paramNombre = $("#nombre").val();
					let paramEmail = $("#email").val();
					let paramPasswd = $("#passwd").val();
		        	$.post("EditarUsuario",
		        	{
		        		id: <%= usuario.getId() %>,
		        		nombre: paramNombre,
		        		email: paramEmail,
		        		passwd: paramPasswd
		        	});
		        	$.get("ObtenerUsuarios", function(respuesta) {
		        		$("#contenido").html(respuesta);
		        	});
				}
				form.classList.add('was-validated');
			}, false);
		});
	})();
</script>