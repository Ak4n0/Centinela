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
	<a href="#" title="Modificar Blackbox"><i class="fas fa-pencil-ruler"></i></a></small>
</h1>
<hr>

<h4><%=blackbox.getNombre_I0()%></h4>
<div id="I0" class="grafica" style="width:auto"></div>
<h4><%=blackbox.getNombre_I1()%></h4>
<div id="I1" class="grafica" style="width:auto"></div>
<h4><%=blackbox.getNombre_I2()%></h4>
<div id="I2" class="grafica" style="width:auto"></div>
<h4><%=blackbox.getNombre_I3()%></h4>
<div id="I3" class="grafica" style="width:auto"></div>
<script>
	document.addEventListener("mousewheel", function() {
    	lastClickedGraph = null;
  	}, false);
  	document.addEventListener("click", function() { lastClickedGraph = null; }, false);
  	
	i0 = new Dygraph(
		document.getElementById("I0"),
		"Date,<%=blackbox.getNombre_I0()%>\n" + 
		<%for (IOPort entrada : puertos) {%>
			"<%=(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")).format(entrada.getFechaHora())%>,<%=entrada.getI0()%>\n" + 
		<%}%>"",
		{
			errorBars : true
		}        
	);
	
	i1 = new Dygraph(
		document.getElementById("I1"),
		"Date,<%=blackbox.getNombre_I1()%>\n" + 
		<%for (IOPort entrada : puertos) {%>
			"<%=(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")).format(entrada.getFechaHora())%>,<%=entrada.getI1()%>\n" + 
		<%}%>"",
		{
			errorBars : true
		} 
	);
	
	i2 = new Dygraph(
		document.getElementById("I2"),
		"Date,<%=blackbox.getNombre_I2()%>\n" + 
		<%for (IOPort entrada : puertos) {%>
			"<%=(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")).format(entrada.getFechaHora())%>,<%=entrada.getI2()%>\n" + 
		<%}%>"",
		{
			errorBars : true
		} 
	);
	
	i3 = new Dygraph(
		document.getElementById("I3"),
		"Date,<%=blackbox.getNombre_I3()%>\n" + 
		<%for (IOPort entrada : puertos) {%>
			"<%=(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")).format(entrada.getFechaHora())%>,<%=entrada.getI3()%>\n" + 
		<%}%>"",
		{
			errorBars : true
		} 
	);

</script>
