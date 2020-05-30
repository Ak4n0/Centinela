<%@page import="modelo.pojo.BlackboxFullInfo"%>
<%@page import="modelo.pojo.UsuarioAdminInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<UsuarioAdminInfo> listaUsuarios = (List<UsuarioAdminInfo>) request.getAttribute("listaUsuarios");
	BlackboxFullInfo blackbox = (BlackboxFullInfo) request.getAttribute("blackbox");
%>

<h1>Editar blackbox</h1>

<form class="needs-validation" novalidate>
	<!-- UID blackbox -->
	<div class="form-group">
		<label for="uid">Identificador Blackbox:</label>
		<input type="text" class="form-control" id="uid" maxlength="20" placeholder="Introduzca el identificador para la Blackbox" name="uid" required>
		<div class="valid-feedback">Correcto.</div>
		<div class="invalid-feedback">Debes rellenar este campo.</div>
	</div>
	<!-- Password -->
	<div class="form-group">
		<label for="passwd">Password Blackbox:</label>
		<input type="text" class="form-control" id="passwd" maxlength="25" placeholder="Intorduzca la contraseña para la Blackbox" name="passwd" required>
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
		<input type="text" class="form-control" id="unidades_I<%= n %>"  maxlength="15" placeholder="Unidades: cm, ºC, lm..." name="unidades_I<%= n %>" required>
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
	
	<button type="submit" class="btn btn-primary">Modificar</button>
	<button type="button" class="btn btn-danger" onclick="resetear()">Resetear</button>
</form>

<script>
	// Disable form submissions if there are invalid fields
    var forms = $(".needs-validation");
	
	// Función de reset
	function resetear() {
		$("#uid").val("<%= blackbox.getIdentificador() %>");
		$("#passwd").val("<%= blackbox.getPasswd() %>");
		$("#usuario").val("<%= blackbox.getIdUsuario() %>");
		$("#unidades_I0").val("<%= blackbox.getUnidades_I0() %>");
		$("#func_trans_I0").val("<%= blackbox.getFuncionTransferencia_I0() %>");
		$("#func_trans_inv_I0").val("<%= blackbox.getFuncionTransferenciaInversa_I0() %>");
		$("#unidades_I1").val("<%= blackbox.getUnidades_I1() %>");
		$("#func_trans_I1").val("<%= blackbox.getFuncionTransferencia_I1() %>");
		$("#func_trans_inv_I1").val("<%= blackbox.getFuncionTransferenciaInversa_I1() %>");
		$("#unidades_I2").val("<%= blackbox.getUnidades_I2() %>");
		$("#func_trans_I2").val("<%= blackbox.getFuncionTransferencia_I2() %>");
		$("#func_trans_inv_I2").val("<%= blackbox.getFuncionTransferenciaInversa_I2() %>");
		$("#unidades_I3").val("<%= blackbox.getUnidades_I3() %>");
		$("#func_trans_I3").val("<%= blackbox.getFuncionTransferencia_I3() %>");
		$("#func_trans_inv_I3").val("<%= blackbox.getFuncionTransferenciaInversa_I3() %>");
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
					let paramId = $("#uid").val();
					let paramPasswd = $("#passwd").val();
					let paramUsuario = $("#usuario").val();
					<% for(int n = 0; n < 4; ++n) { %>
	        		let paramUnidadesI<%= n %> = $("#unidades_I<%= n %>").val();
	        		let paramFuncTransI<%= n %> = $("#func_trans_I<%= n %>").val();
	        		paramFuncTransInvI<%= n %> = $("#func_trans_inv_I<%= n %>").val();
	        		<% } %>
		        	$.post("EditarBlackbox",
		        	{
		        		id: <%= blackbox.getId() %>,
		        		idUnico: paramId,
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