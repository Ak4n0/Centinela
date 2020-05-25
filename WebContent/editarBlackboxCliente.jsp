<%@page import="modelo.pojo.BlackboxFullInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	BlackboxFullInfo blackbox = (BlackboxFullInfo) request.getAttribute("blackbox");
%>

<h1>Editar blackbox <%= blackbox.getIdentificador() %></h1>

<form method="post">
	<fieldset>
	<legend>Información general</legend>
	<div class="form-group">
		<label for="nombre">Nombre de la blackbox:</label>
		<input type="text" class="form-control" id="nombre" placeholder="Introduzca el nombre de la blackbox">
	</div>
	<div class="form-group">
		<label for="descr">Descripción:</label>
		<textarea class="form-control" id="descr" placeholder="Introduzca una descripción para la blackbox"></textarea>
	</div>
	</fieldset>
	<fieldset>
	<legend>Entradas</legend>
	<div class="form-group">
		<label for="I0">Nombre entrada 1:</label>
		<input type="text" class="form-control" id="I0" placeholder="Introduzca el identificador para la entrada">
	</div>
	<div class="form-group">
		<label for="I1">Nombre entrada 2:</label>
		<input type="text" class="form-control" id="I1" placeholder="Introduzca el identificador para la entrada">
	</div>
	<div class="form-group">
		<label for="I2">Nombre entrada 3:</label>
		<input type="text" class="form-control" id="I2" placeholder="Introduzca el identificador para la entrada">
	</div>
	<div class="form-group">
		<label for="I3">Nombre entrada 4:</label>
		<input type="text" class="form-control" id="I3" placeholder="Introduzca el identificador para la entrada">
	</div>
	</fieldset>
	<fieldset>
	<legend>Salidas</legend>
	<div class="form-group">
		<label for="O0">Nombre salida 1:</label>
		<input type="text" class="form-control" id="O0" placeholder="Introduzca el identificador para la salida">
	</div>
	<div class="form-group">
		<label for="O1">Nombre salida 2:</label>
		<input type="text" class="form-control" id="O1" placeholder="Introduzca el identificador para la salida">
	</div>
	<div class="form-group">
		<label for="O2">Nombre salida 3:</label>
		<input type="text" class="form-control" id="O2" placeholder="Introduzca el identificador para la salida">
	</div>
	<div class="form-group">
		<label for="O3">Nombre salida 4:</label>
		<input type="text" class="form-control" id="O3" placeholder="Introduzca el identificador para la salida">
	</div>
	</fieldset>
	<fieldset>
	<legend>Umbrales de entrada</legend>
	<div class="form-group">
		<label for="I0sup">Umbral superior para entrada 1:</label>
		<input type="text" class="form-control" id="I0sup" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I0inf">Umbral inferior para entrada 1:</label>
		<input type="text" class="form-control" id="I0inf" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I1sup">Umbral superior de para entrada 2:</label>
		<input type="text" class="form-control" id="I1sup" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I1inf">Umbral inferior para entrada 2:</label>
		<input type="text" class="form-control" id="I1inf" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I2sup">Umbral superior para entrada 3:</label>
		<input type="text" class="form-control" id="I2sup" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I2inf">Umbral inferior para entrada 3:</label>
		<input type="text" class="form-control" id="I2inf" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I3sup">Umbral superior para entrada 4:</label>
		<input type="text" class="form-control" id="I3sup" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I3inf">Umbral inferior para entrada 4:</label>
		<input type="text" class="form-control" id="I3inf" placeholder="Introduzca el valor umbral">
	</div>
	</fieldset>
	<button type="button" class="btn btn-primary" onclick="enviar()">Modificar</button>
	<button type="button" class="btn btn-danger" onclick="resetear()">Resetear</button>
</form>

<script>
	function resetear() {
		<% if(blackbox.getNombre() != null) { %>
			$("#nombre").val("<%= blackbox.getNombre() %>");
		<% } %>
		<% if(blackbox.getInformacionExtra() != null) { %>
			$("#descr").val("<%= blackbox.getInformacionExtra() %>");
		<% } %>
		$("#I0").val("<%= blackbox.getNombre_I0() %>");
		$("#I1").val("<%= blackbox.getNombre_I1() %>");
		$("#I2").val("<%= blackbox.getNombre_I2() %>");
		$("#I3").val("<%= blackbox.getNombre_I3() %>");
		$("#O0").val("<%= blackbox.getNombre_O0() %>");
		$("#O1").val("<%= blackbox.getNombre_O1() %>");
		$("#O2").val("<%= blackbox.getNombre_O2() %>");
		$("#O3").val("<%= blackbox.getNombre_O3() %>");
		$("#I0sup").val("<%= blackbox.getUmbralSuperiorI0() %>");
		$("#I0inf").val("<%= blackbox.getUmbralInferiorI0() %>");
		$("#I1sup").val("<%= blackbox.getUmbralSuperiorI1() %>");
		$("#I1inf").val("<%= blackbox.getUmbralInferiorI1() %>");
		$("#I2sup").val("<%= blackbox.getUmbralSuperiorI2() %>");
		$("#I2inf").val("<%= blackbox.getUmbralInferiorI2() %>");
		$("#I3sup").val("<%= blackbox.getUmbralSuperiorI3() %>");
		$("#I3inf").val("<%= blackbox.getUmbralInferiorI3() %>");
	}
	
    (function() {
    	resetear();
	})();
    
    function enviar() {
    	$.post("EditarBlackboxCliente", {
        		uid: "<%= blackbox.getIdentificador() %>",
        		nombre: $("#nombre").val(),
        		descr: $("#descr").val(),
        		<% for(int n = 0; n < 4; ++n) { %>
        		O<%= n %>: $("#O<%= n %>").val(),
        		I<%= n %>: $("#I<%= n %>").val(),
        		I<%= n %>sup: $("#I<%= n %>sup").val(),
        		I<%= n %>inf: $("#I<%= n %>inf").val(),
        		<% } %>
        		dummy: null
        });
       	$.get("ObtenerDatosBlackbox?id=<%= blackbox.getIdentificador() %>", function(respuesta) {
       		$("#contenido").html(respuesta);
       	});
    }
</script>