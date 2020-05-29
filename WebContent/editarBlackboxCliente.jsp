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
		<input type="text" class="form-control" id="nombre" maxlength="32" placeholder="Introduzca el nombre de la blackbox">
	</div>
	<div class="form-group">
		<label for="descr">Descripción:</label>
		<textarea class="form-control" id="descr" maxlength="255" placeholder="Introduzca una descripción para la blackbox"></textarea>
	</div>
	</fieldset>
	<fieldset>
	<legend>Entradas</legend>
	<div class="form-group">
		<label for="I0">Nombre entrada 1:</label>
		<input type="text" class="form-control" id="I0" maxlength="32" placeholder="Introduzca el identificador para la entrada">
	</div>
	<div class="form-group">
		<label for="I1">Nombre entrada 2:</label>
		<input type="text" class="form-control" id="I1" maxlength="32" placeholder="Introduzca el identificador para la entrada">
	</div>
	<div class="form-group">
		<label for="I2">Nombre entrada 3:</label>
		<input type="text" class="form-control" id="I2" maxlength="32" placeholder="Introduzca el identificador para la entrada">
	</div>
	<div class="form-group">
		<label for="I3">Nombre entrada 4:</label>
		<input type="text" class="form-control" id="I3" maxlength="32" placeholder="Introduzca el identificador para la entrada">
	</div>
	</fieldset>
	<fieldset>
	<legend>Salidas</legend>
	<div class="form-group">
		<label for="O0">Nombre salida 1:</label>
		<input type="text" class="form-control" id="O0" maxlength="32" placeholder="Introduzca el identificador para la salida">
	</div>
	<div class="form-group">
		<label for="O1">Nombre salida 2:</label>
		<input type="text" class="form-control" id="O1" maxlength="32" placeholder="Introduzca el identificador para la salida">
	</div>
	<div class="form-group">
		<label for="O2">Nombre salida 3:</label>
		<input type="text" class="form-control" id="O2" maxlength="32" placeholder="Introduzca el identificador para la salida">
	</div>
	<div class="form-group">
		<label for="O3">Nombre salida 4:</label>
		<input type="text" class="form-control" id="O3" maxlength="32" placeholder="Introduzca el identificador para la salida">
	</div>
	</fieldset>
	<fieldset>
	<legend>Umbrales de entrada</legend>
	<div class="form-group">
		<label for="I0sup">Umbral superior para entrada 1:</label>
		<input type="number" class="form-control" id="I0sup" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I0inf">Umbral inferior para entrada 1:</label>
		<input type="number" class="form-control" id="I0inf" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I1sup">Umbral superior para entrada 2:</label>
		<input type="number" class="form-control" id="I1sup" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I1inf">Umbral inferior para entrada 2:</label>
		<input type="number" class="form-control" id="I1inf" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I2sup">Umbral superior para entrada 3:</label>
		<input type="number" class="form-control" id="I2sup" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I2inf">Umbral inferior para entrada 3:</label>
		<input type="number" class="form-control" id="I2inf" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I3sup">Umbral superior para entrada 4:</label>
		<input type="number" class="form-control" id="I3sup" placeholder="Introduzca el valor umbral">
	</div>
	<div class="form-group">
		<label for="I3inf">Umbral inferior para entrada 4:</label>
		<input type="number" class="form-control" id="I3inf" placeholder="Introduzca el valor umbral">
	</div>
	</fieldset>
	<button type="button" class="btn btn-primary" onclick="enviar()">Modificar</button>
	<button type="button" class="btn btn-danger" onclick="resetear()">Resetear</button>
</form>

<script>
	function funcTransI0(val) {
		<%= blackbox.getFuncionTransferencia_I0() %>
	}

	function funcTransI1(val) {
		<%= blackbox.getFuncionTransferencia_I1() %>
	}

	function funcTransI2(val) {
		<%= blackbox.getFuncionTransferencia_I2() %>
	}

	function funcTransI3(val) {
		<%= blackbox.getFuncionTransferencia_I3() %>
	}
	
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
		
		// límites para I0
		<% if(blackbox.getUmbralSuperiorI0() == null) { %>
		$("#I0sup").val("");
		<% } else { %>
		$("#I0sup").val(funcTransI0(<%= blackbox.getUmbralSuperiorI0() %>));
		<% } %>
		
		<% if(blackbox.getUmbralInferiorI0() == null) { %>
		$("#I0inf").val("");
		<% } else { %>
		$("#I0inf").val(funcTransI0(<%= blackbox.getUmbralInferiorI0() %>));
		<% } %>
		
		// límites para I1
		<% if(blackbox.getUmbralSuperiorI1() == null) { %>
		$("#I1sup").val("");
		<% } else { %>
		$("#I1sup").val(funcTransI1(<%= blackbox.getUmbralSuperiorI1() %>));
		<% } %>
		<% if(blackbox.getUmbralInferiorI1() == null) { %>
		$("#I1inf").val("");
		<% } else { %>
		$("#I1inf").val(funcTransI1(<%= blackbox.getUmbralInferiorI1() %>));
		<% } %>
		
		// límites para I2
		<% if(blackbox.getUmbralSuperiorI2() == null) { %>
		$("#I2sup").val("");
		<% } else { %>
		$("#I2sup").val(funcTransI2(<%= blackbox.getUmbralSuperiorI2() %>));
		<% } %>
		<% if(blackbox.getUmbralInferiorI2() == null) { %>
		$("#I2inf").val("");
		<% } else { %>
		$("#I2inf").val(funcTransI3(<%= blackbox.getUmbralInferiorI2() %>));
		<% } %>
		
		// límites para I3
		<% if(blackbox.getUmbralSuperiorI3() == null) { %>
		$("#I3sup").val("");
		<% } else { %>
		$("#I3sup").val(funcTransI3(<%= blackbox.getUmbralSuperiorI3() %>));
		<% } %>
		<% if(blackbox.getUmbralInferiorI3() == null) { %>
		$("#I3inf").val("");
		<% } else { %>
		$("#I3inf").val(funcTransI3(<%= blackbox.getUmbralInferiorI3() %>));
		<% } %>
	}
	
    (function() {
    	resetear();
	})();
    
    function funcTransInvI0(val) {
    	<%= blackbox.getFuncionTransferenciaInversa_I0() %>
    }
    
    function funcTransInvI1(val) {
    	<%= blackbox.getFuncionTransferenciaInversa_I1() %>
    }
    
    function funcTransInvI2(val) {
    	<%= blackbox.getFuncionTransferenciaInversa_I2() %>
    }
    
    function funcTransInvI3(val) {
    	<%= blackbox.getFuncionTransferenciaInversa_I3() %>
    }
    
    function enviar() {
    	// Impedir que se mande la información con umbrales discordantes
    	if($("#I0sup").val() != "" && $("#I0inf").val() != "") {
    		if(parseInt($("#I0sup").val()) < parseInt($("#I0inf").val())) {
    			alert("El umbral superior de Entrada 1 no puede ser inferior al umbral inferior: " + $("#I0sup").val() + " y " + $("#I0inf").val() + " respectivamente.");
    			return;
    		}
    	}
    	if($("#I1sup").val() != "" && $("#I1inf").val() != "") {
    		if(parseInt($("#I1sup").val()) < parseInt($("#I1inf").val())) {
    			alert("El umbral superior de Entrada 2 no puede ser inferior al umbral inferior: " + $("#I1sup").val() + " y " + $("#I1inf").val() + " respectivamente.");
    			return;
    		}
    	}
    	if($("#I2sup").val() != "" && $("#I2inf").val() != "") {
    		if(parseInt($("#I2sup").val()) < parseInt($("#I2inf").val())) {
    			alert("El umbral superior de Entrada 3 no puede ser inferior al umbral inferior: " + $("#I2sup").val() + " y " + $("#I2inf").val() + " respectivamente.");
    			return;
    		}
    	}
    	if($("#I3sup").val() != "" && $("#I3inf").val() != "") {
    		if(parseInt($("#I3sup").val()) < parseInt($("#I3inf").val())) {
    			alert("El umbral superior de Entrada 3 no puede ser inferior al umbral inferior: " + $("#I3sup").val() + " y " + $("#I3inf").val() + " respectivamente.");
    			return;
    		}
    	}
    	
    	$.post("EditarBlackboxCliente", {
        		uid: "<%= blackbox.getIdentificador() %>",
        		nombre: $("#nombre").val(),
        		descr: $("#descr").val(),
        		O0: $("#O0").val(),
        		O1: $("#O1").val(),
        		O2: $("#O2").val(),
        		O3: $("#O3").val(),
        		I0: $("#I0").val(),
        		I1: $("#I1").val(),
        		I2: $("#I2").val(),
        		I3: $("#I3").val(),
        		I0sup: funcTransInvI0($("#I0sup").val()),
        		I1sup: funcTransInvI0($("#I1sup").val()),
        		I2sup: funcTransInvI0($("#I2sup").val()),
        		I3sup: funcTransInvI0($("#I3sup").val()),
        		I0inf: funcTransInvI0($("#I0inf").val()),
        		I1inf: funcTransInvI0($("#I1inf").val()),
        		I2inf: funcTransInvI0($("#I2inf").val()),
        		I3inf: funcTransInvI0($("#I3inf").val())
        });
       	$.get("ObtenerDatosBlackbox?id=<%= blackbox.getIdentificador() %>", function(respuesta) {
       		$("#contenido").html(respuesta);
       	});
    }
</script>