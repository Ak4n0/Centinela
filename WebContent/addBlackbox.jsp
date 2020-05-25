<%@page import="modelo.pojo.UsuarioAdminInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% List<UsuarioAdminInfo> listaUsuarios = (List<UsuarioAdminInfo>) request.getAttribute("listaUsuarios"); %>

<h1>Añadir una nueva Blackbox</h1>

<form class="needs-validation" novalidate>
	<!-- UID blackbox -->
	<div class="form-group">
		<label for="id">Identificador Blackbox:</label>
		<input type="text" class="form-control" id="id" placeholder="Introduzca el identificador para la Blackbox" name="id" required>
		<div class="valid-feedback">Correcto.</div>
		<div class="invalid-feedback">Debes rellenar este campo.</div>
	</div>
	<!-- Password -->
	<div class="form-group">
		<label for="passwd">Password Blackbox:</label>
		<input type="text" class="form-control" id="passwd" placeholder="Intorduzca la contraseña para la Blackbox" name="passwd" required>
		<div class="valid-feedback">Correcto.</div>
		<div class="invalid-feedback">Debes rellenar este campo.</div>
	</div>
	<!-- Cliente -->
	<div class="form-group">
		<label for="usuario">Seleccione un cliente:</label>
		<select class="form-control" id="usuario" required>
		<option value="" selected disabled>Selecciona al propietario</option>
		<% for(UsuarioAdminInfo cliente: listaUsuarios) { %>
			<option value="<%= cliente.getId() %>">(<%= cliente.getId() %>) <%= cliente.getNombre() %> [ <%= cliente.getEmail() %> ]</option>
		<% } %>
		</select>
		<div class="valid-feedback">Correcto.</div>
		<div class="invalid-feedback">Debes seleccionar un cliente.</div>
	</div>
	
	<% for(int n = 0; n < 4; ++n) { %>
	<!-- Unidades I<%= n %> -->
	<fieldset>
	<legend>Entrada <%= n %></legend>
	<div class="form-group">
		<label for="unidades_I<%= n %>">Unidades de la entrada:</label>
		<input type="text" class="form-control" id="unidades_I<%= n %>"  placeholder="Unidades: cm, ºC, lm..." name="unidades_I<%= n %>" required>
		<div class="valid-feedback">Correcto.</div>
		<div class="invalid-feedback">Debes rellenar este campo.</div>
	</div>
	<!-- Funcion transferencia I<%= n %> -->
	<div class="form-group">
		<label for="func_trans_I<%= n %>">Función de transferencia:</label>
		<p style="font-family: monospace; color: black">function transFuncInv_I<%= n %>(val) {</p>
		<textarea style="font-family: monospace" class="form-control" id="func_trans_I<%= n %>" maxlength="1024" placeholder="Introduzca la función de transferencia para esta entrada." name="func_trans_I<%= n %>" required></textarea>
		<p style="font-family: monospace; color: black">}</p>
		<div class="valid-feedback">Correcto.</div>
		<div class="invalid-feedback">Debes rellenar este campo.</div>
	</div>
	<!-- Función inversa transferencia I<%= n %> -->
	<div class="form-group">
		<label for="func_trans_inv_I<%= n %>">Función de transferencia inversa:</label>
		<p style="font-family: monospace; color: black">function transFuncInv_I<%= n %>(val) {</p>
		<textarea style="font-family: monospace" class="form-control" id="func_trans_inv_I<%= n %>" maxlength="1024" placeholder="Introduzca la función de transferencia para esta entrada." name="func_trans_inv_I<%= n %>" required></textarea>
		<p style="font-family: monospace; color: black">}</p>
		<div class="valid-feedback">Correcto.</div>
		<div class="invalid-feedback">Debes rellenar este campo.</div>
	</div>
	</fieldset>
	<% } %>

	<button type="submit" class="btn btn-primary">Crear</button>
	<button type="button" class="btn btn-danger" onclick="resetear()">Resetear</button>
</form>

<script>
	// Disable form submissions if there are invalid fields
    var forms = $(".needs-validation");
	
	// Función de reset
	function resetear() {
		$("#id").val("");
		$("#passwd").val("");
		$("#usuario").val("");
		<% for(int n = 0; n < 4; ++n) { %>
		$("#func_trans_I<%= n %>").val("return val;");
		$("#func_trans_inv_I<%= n %>").val("return val;");
		<% } %>
	}
    // Loop over them and prevent submission
    (function() {
    	resetear();
		Array.prototype.filter.call(forms, function(form) {
			form.addEventListener("submit", function(event) {
				event.preventDefault();
				if (form.checkValidity() === false) {
					event.stopPropagation();
				} else {
					let paramId = $("#id").val();
					let paramPasswd = $("#passwd").val();
					let paramUsuario = $("#usuario").val();
					<% for(int n = 0; n < 4; ++n) { %>
					let paramUnidadesI<%= n %> = $("#unidades_I<%= n %>").val();
					let paramFuncTransI<%= n %> = $("#func_trans_I<%= n %>").val();
					let paramFuncTransInvI<%= n %> = $("#func_trans_inv_I<%= n %>").val();
					<% } %>
		        	$.post("AddBlackbox",
		        	{
		        		id: paramId,
		        		passwd: paramPasswd,
		        		usuario: paramUsuario,
		        		<% for(int n = 0; n < 4; ++n) { %>
		        		unidades_i<%= n %>: paramUnidadesI<%= n %>,
		        		func_trans_i<%= n %>: paramFuncTransI<%= n %>,
		        		func_trans_inv_i<%= n %>: paramFuncTransInvI<%= n %>,
		        		<% } %>
		        		dummy: null
		        	});
		        	$.get("ObtenerBlackboxes", function(respuesta) {
		        		$("#contenido").html(respuesta);
		        	});
				}
				form.classList.add('was-validated');
			}, false);
		});
	})();
</script>