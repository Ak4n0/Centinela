<%@page import="modelo.pojo.UsuarioAdminInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% List<UsuarioAdminInfo> listaUsuarios = (List<UsuarioAdminInfo>) request.getAttribute("listaUsuarios"); %>

<h1>Añadir una nueva Blackbox</h1>

<form action="AddBlackBox" method="post" class="needs-validation" novalidate>
  <div class="form-group">
    <label for="id">Identificador Blackbox:</label>
    <input type="text" class="form-control" id="id" placeholder="Introduce el identificador para la Blackbox" name="id" required>
    <div class="valid-feedback">Correcto.</div>
    <div class="invalid-feedback">Debes rellenar este campo.</div>
  </div>
  <div class="form-group">
    <label for="passwd">Password Blackbox:</label>
    <input type="password" class="form-control" id="passwd" placeholder="Intorduce la contraseña para la Blackbox" name="passwd" required>
    <div class="valid-feedback">Correcto.</div>
    <div class="invalid-feedback">Debes rellenar este campo.</div>
  </div>
  <div class="form-group">
    <label for="usuario">Selecciona un cliente:</label>
      <select class="form-control" id="usuario" required>
      <option value="" selected disabled>Selecciona al propietario</option>
      <% for(UsuarioAdminInfo cliente: listaUsuarios) { %>
      	<option value="<%= cliente.getId() %>">(<%= cliente.getId() %>) <%= cliente.getNombre() %> [ <%= cliente.getEmail() %> ]</option>
      <% } %>
      </select>
      <div class="valid-feedback">Correcto.</div>
      <div class="invalid-feedback">Debes seleccionar un cliente.</div>
  </div>
  <button type="submit" class="btn btn-primary">Crear</button>
</form>

<script>
	// Disable form submissions if there are invalid fields
    var forms = $(".needs-validation");
    // Loop over them and prevent submission
    (function() {
		Array.prototype.filter.call(forms, function(form) {
			form.addEventListener("submit", function(event) {
				event.preventDefault();
				if (form.checkValidity() === false) {
					event.stopPropagation();
				} else {
					let paramId = $("#id").val();
					let paramPasswd = $("#passwd").val();
					let paramUsuario = $("#usuario").val();
		        	$.post("AddBlackbox",
		        	{
		        		id: paramId,
		        		passwd: paramPasswd,
		        		usuario: paramUsuario
		        	});
		        	$.get("ObtenerBlackboxes", function(respuesta) {
		        		$("#content").html(respuesta);
		        	});
				}
				form.classList.add('was-validated');
			}, false);
		});
	})();
</script>