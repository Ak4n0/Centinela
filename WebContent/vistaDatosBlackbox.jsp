<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.pojo.IOPort"%>
<%@page import="java.util.List"%>
<%@page import="modelo.pojo.BlackboxFullInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	BlackboxFullInfo blackbox = (BlackboxFullInfo) request.getAttribute("blackbox");
	List<IOPort> puertos = (List<IOPort>) request.getAttribute("io");
	IOPort ultimaIO = (IOPort) request.getAttribute("ultimaIO");
%>

<h2>
	<% if(blackbox.getNombre() != null) { %>
	<%=blackbox.getNombre()%>
	<% } %>
	<small>[<%=blackbox.getIdentificador()%>]</small>
</h2>
<% if(blackbox.getInformacionExtra() != null) {%>
<p><%= blackbox.getInformacionExtra() %></p>
<% } %>

<hr>
<fieldset>
	<legend>Salidas</legend>
	<ul class="list-group list-group-flush">
		<li class="list-group-item custom-control custom-switch" style="padding-left: 2.5rem">
			<input type="checkbox" class="custom-control-input" id="O0"
			<% if(ultimaIO != null && ultimaIO.getO0() != null && ultimaIO.getO0() == true) { %>
			checked
			<% } %>
			/><label class="custom-control-label" for="O0"><%= blackbox.getNombre_O0() %></label>
		</li>
		<li class="list-group-item custom-control custom-switch" style="padding-left: 2.5rem">
			<input type="checkbox" class="custom-control-input" id="O1" 
			<% if(ultimaIO != null && ultimaIO.getO1() != null && ultimaIO.getO1() == true) { %>
			checked
			<% } %>
			/><label class="custom-control-label" for="O1"><%= blackbox.getNombre_O1() %></label>
		</li>
		<li class="list-group-item custom-control custom-switch" style="padding-left: 2.5rem">
			<input type="checkbox" class="custom-control-input" id="O2" 
			<% if(ultimaIO != null && ultimaIO.getO2() != null && ultimaIO.getO2() == true) { %>
			checked
			<% } %>
			/><label class="custom-control-label" for="O2"><%= blackbox.getNombre_O2() %></label>
		</li>
		<li class="list-group-item custom-control custom-switch" style="padding-left: 2.5rem">
			<input type="checkbox" class="custom-control-input" id="O3" 
			<% if(ultimaIO != null && ultimaIO.getO3() != null && ultimaIO.getO3() == true) { %>
			checked
			<% } %>
			/><label class="custom-control-label" for="O3"><%= blackbox.getNombre_O3() %></label>
		</li>
	</ul>
</fieldset>
<hr>
<fieldset>
	<legend>Entradas</legend>
	<h4><%=blackbox.getNombre_I0()%></h4>
	<% if(blackbox.getUnidades_I0() != null) { %>
		<%= blackbox.getUnidades_I0() %>
	<% } %>
	<div id="I0" class="grafica" style="width:auto"></div>
	<h4><%=blackbox.getNombre_I1()%></h4>
	<% if(blackbox.getUnidades_I1() != null) { %>
		<%= blackbox.getUnidades_I1() %>
	<% } %>
	<div id="I1" class="grafica" style="width:auto"></div>
	<h4><%=blackbox.getNombre_I2()%></h4>
	<% if(blackbox.getUnidades_I2() != null) { %>
		<%= blackbox.getUnidades_I2() %>
	<% } %>
	<div id="I2" class="grafica" style="width:auto"></div>
	<h4><%=blackbox.getNombre_I3()%></h4>
	<% if(blackbox.getUnidades_I3() != null) { %>
		<%= blackbox.getUnidades_I3() %>
	<% } %>
	<div id="I3" class="grafica" style="width:auto"></div>
</fieldset>
<script>	
	document.addEventListener("mousewheel", function() {
    	lastClickedGraph = null;
  	}, false);
  	document.addEventListener("click", function() { lastClickedGraph = null; }, false);
  	
  	// Preparar dato de las gráficas
  	dataI0 = [];
  	dataI1 = [];
  	dataI2 = [];
  	dataI3 = [];
	
  	// Las siguientes líneas se transforman en las funciones necesarias para modificar los datos
  	//    devueltos por las blackbox a sus respectivos valores de magnitud real. La función de trans-
  	//    ferencia es guardada en la base de datos y es específica para cada blackbox. Por tanto
  	//    la base de datos y este javascript deben concordar en el nombre de la funicón.
  	function transFunc_I0(val) {
  		<%= blackbox.getFuncionTransferencia_I0() %>
  	}
  	 
  	function transFunc_I1(val) {
  		<%= blackbox.getFuncionTransferencia_I1() %>
  	}
  	
  	function transFunc_I2(val) {
  		<%= blackbox.getFuncionTransferencia_I2() %>
  	}
  	
  	function transFunc_I3(val) {
  		<%= blackbox.getFuncionTransferencia_I3() %>
  	}
  	
	<%for (IOPort entrada : puertos) {%>
		date = new Date(<%= entrada.getFechaHora().getTime() %>);
		val_I0 = transFunc_I0(<%= entrada.getI0() %>);
		val_I1 = transFunc_I1(<%= entrada.getI1() %>);
		val_I2 = transFunc_I2(<%= entrada.getI2() %>);
		val_I3 = transFunc_I3(<%= entrada.getI3() %>);
		dataI0.push([date, val_I0]);
		dataI1.push([date, val_I1]);
		dataI2.push([date, val_I2]);
		dataI3.push([date, val_I3]);
	<%}%>
	
	i0 = new Dygraph(
		document.getElementById("I0"),
		dataI0,
		{
			drawPoints: true,
			drawLabels: true,
            labels: ['Time', 'Valores']
		}        
	);
	
	i1 = new Dygraph(
		document.getElementById("I1"),
		dataI1,
		{
			drawPoints: true,
			drawLabels: true,
            labels: ['Time', 'Valores']
		} 
	);
	
	i2 = new Dygraph(
		document.getElementById("I2"),
		dataI2,
		{
			drawPoints: true,
			drawLabels: true,
            labels: ['Time', 'Valores']
		}  
	);
	
	i3 = new Dygraph(
		document.getElementById("I3"),
		dataI3,
		{
			drawPoints: true,
			drawLabels: true,
            labels: ['Time', 'Valores']
		} 
	);
	
	socket = new WebSocket("ws://" + "<%= request.getLocalAddr() %>" + ":8080/Centinela/ws");
	socket.onopen = function() {
		let obj = {
			"op": "conn",
			"uid": "<%= blackbox.getIdentificador() %>"
		};
		socket.send(JSON.stringify(obj));
	};
	
	socket.onmessage = function(event) {
		let obj = JSON.parse(event.data);
		fecha = new Date(obj.fecha);
		dataI0.push([fecha, obj.I0]);
		dataI1.push([fecha, obj.I1]);
		dataI2.push([fecha, obj.I2]);
		dataI3.push([fecha, obj.I3]);
		i0.updateOptions( {'file': dataI0});
		i1.updateOptions( {'file': dataI1});
		i2.updateOptions( {'file': dataI2});
		i3.updateOptions( {'file': dataI3});
	}
	
	document.getElementById("O0").addEventListener("click", function(e) {
		let obj = {
			"op": "outp",
			"port": "O0",
			"valor": this.checked
		};
		socket.send(JSON.stringify(obj));
	});
	
	document.getElementById("O1").addEventListener("click", function(e) {
		let obj = {
			"op": "outp",
			"port": "O1",
			"valor": this.checked
		};
		socket.send(JSON.stringify(obj));
	});
	
	document.getElementById("O2").addEventListener("click", function(e) {
		let obj = {
			"op": "outp",
			"port": "O2",
			"valor": this.checked
		};
		socket.send(JSON.stringify(obj));
	});

	document.getElementById("O3").addEventListener("click", function(e) {
		let obj = {
			"op": "outp",
			"port": "O3",
			"valor": this.checked
		};
		socket.send(JSON.stringify(obj))
	});
	
	// Mostrar los menús de la barra superior de la blackbox
	document.getElementById("btn-menu").style.visibility = "visible";
	document.getElementById("navbarSupportedContent").style.visibility = "visible";
	
	// Dar funcionalidad a los botones
	document.getElementById("btn-dashboard").addEventListener("click", function() {
		let uid = $(this).attr("id");
		$.get("ObtenerDatosBlackbox", {"id": "<%= blackbox.getIdentificador() %>"}, function(htmlExterno) {
			$("#contenido").html(htmlExterno)
		});
	});
	
	document.getElementById("btn-editar").addEventListener("click", function() {
		$.get("EditarBlackboxCliente", {"uid": "<%= blackbox.getIdentificador() %>"}, function(htmlExterno) {
			$("#contenido").html(htmlExterno);
		});
	});
	
</script>
