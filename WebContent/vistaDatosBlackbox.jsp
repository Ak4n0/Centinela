<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.pojo.IOPort"%>
<%@page import="java.util.List"%>
<%@page import="modelo.pojo.BlackboxFullInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	BlackboxFullInfo blackbox = (BlackboxFullInfo) request.getAttribute("blackbox");
	List<IOPort> puertos = (List<IOPort>) request.getAttribute("io");
%>

<h1><%=blackbox.getNombre()%><small>[<%=blackbox.getIdentificador()%>]
	<a href="#" id="modificar" title="Modificar Blackbox"><i class="fas fa-pencil-ruler"></i></a></small>
</h1>
<% if(blackbox.getInformacionExtra() != null) {%>
<p><%= blackbox.getInformacionExtra() %></p>
<% } %>

<hr>
<fieldset>
	<legend>Salidas</legend>
	<ul class="list-group list-group-flush">
		<li class="list-group-item">
			<%= blackbox.getNombre_O0() %><label class="switch"><input type="checkbox" class="warning" id="O0" /><span class="slider"></span></label>
		</li>
		<li class="list-group-item">
			<%= blackbox.getNombre_O1() %><label class="switch"><input type="checkbox" class="warning" id="O1" /><span class="slider"></span></label>
		</li>
		<li class="list-group-item">
			<%= blackbox.getNombre_O2() %><label class="switch"><input type="checkbox" class="warning" id="O2" /><span class="slider"></span></label>
		</li>
		<li class="list-group-item">
			<%= blackbox.getNombre_O3() %><label class="switch"><input type="checkbox" class="warning" id="O3" /><span class="slider"></span></label>
		</li>
	</ul>
</fieldset>
<hr>
<fieldset>
	<legend>Entradas</legend>
	<h4><%=blackbox.getNombre_I0()%></h4>
	<div id="I0" class="grafica" style="width:auto"></div>
	<h4><%=blackbox.getNombre_I1()%></h4>
	<div id="I1" class="grafica" style="width:auto"></div>
	<h4><%=blackbox.getNombre_I2()%></h4>
	<div id="I2" class="grafica" style="width:auto"></div>
	<h4><%=blackbox.getNombre_I3()%></h4>
	<div id="I3" class="grafica" style="width:auto"></div>
</fieldset>
<script>
	document.getElementById("modificar").addEventListener("click", function(e) {
		$.get("EditarBlackboxCliente", {"uid": "<%= blackbox.getIdentificador() %>"}, function(htmlExterno) {
			$("#contenido").html(htmlExterno);
		});
	});
	
	document.addEventListener("mousewheel", function() {
    	lastClickedGraph = null;
  	}, false);
  	document.addEventListener("click", function() { lastClickedGraph = null; }, false);
  	
  	// Preparar dato de las gráficas
  	let dataI0 = [];
  	let dataI1 = [];
  	let dataI2 = [];
  	let dataI3 = [];
  	

	let date;
	
	<%for (IOPort entrada : puertos) {%>
		date = new Date(<%= entrada.getFechaHora().getTime() %>);
		dataI0.push([date, <%= entrada.getI0() %>]);
		dataI1.push([date, <%= entrada.getI1() %>]);
		dataI2.push([date, <%= entrada.getI2() %>]);
		dataI3.push([date, <%= entrada.getI3() %>]);
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
	
</script>
