<%@page import="modelo.pojo.BlackboxFullInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	BlackboxFullInfo blackbox = (BlackboxFullInfo) request.getAttribute("blackbox");
%>

<h1>Editar blackbox <%= blackbox.getIdentificador() %></h1>

<form>
  <input type="hidden" name="uid" value="<%= blackbox.getIdentificador() %>">
  <div class="form-group">
    <label for="nombre">Nombre de la blackbox:</label>
    <input type="text" class="form-control" id="nombre" placeholder="Introduzca el nombre de la blackbox" name="nombre">
  </div>
  <div class="form-group">
    <label for="descr">Descripción:</label>
    <textarea class="form-control" id="descripcion" placeholder="Introduzca una descripción para la blackbox" name="descr"></textarea>
  </div>
  <div class="form-group">
    <label for="I0">Nombre entrada 1:</label>
    <input type="text" class="form-control" id="I0" placeholder="Introduzca el identificador para la entrada" name="I0">
  </div>
  <div class="form-group">
    <label for="I1">Nombre entrada 2:</label>
    <input type="text" class="form-control" id="I1" placeholder="Introduzca el identificador para la entrada" name="I1">
  </div>
  <div class="form-group">
    <label for="I2">Nombre entrada 3:</label>
    <input type="text" class="form-control" id="I2" placeholder="Introduzca el identificador para la entrada" name="I2">
  </div>
  <div class="form-group">
    <label for="I3">Nombre entrada 4:</label>
    <input type="text" class="form-control" id="I3" placeholder="Introduzca el identificador para la entrada" name="I3">
  </div><div class="form-group">
    <label for="O0">Nombre entrada 1:</label>
    <input type="text" class="form-control" id="O0" placeholder="Introduzca el identificador para la salida" name="I0">
  </div>
  <div class="form-group">
    <label for="O1">Nombre entrada 2:</label>
    <input type="text" class="form-control" id="O1" placeholder="Introduzca el identificador para la salida" name="I1">
  </div>
  <div class="form-group">
    <label for="O2">Nombre entrada 3:</label>
    <input type="text" class="form-control" id="O2" placeholder="Introduzca el identificador para la salida" name="I2">
  </div>
  <div class="form-group">
    <label for="O3">Nombre entrada 4:</label>
    <input type="text" class="form-control" id="O3" placeholder="Introduzca el identificador para la salida" name="I3">
  </div>
  <button type="submit" class="btn btn-primary">Modificar</button>
  <button type="button" class="btn btn-danger" onclick="resetear()">Resetear</button>
</form>

<script>
	function resetear() {
		$("#nombre").val("<%= blackbox.getNombre() %>");
		$("#descr").text("<%= blackbox.getInformacionExtra() %>");
		$("#I0").val("<%= blackbox.getNombre_I0() %>");
		$("#I1").val("<%= blackbox.getNombre_I1() %>");
		$("#I2").val("<%= blackbox.getNombre_I2() %>");
		$("#I3").val("<%= blackbox.getNombre_I3() %>");
		$("#O0").val("<%= blackbox.getNombre_O0() %>");
		$("#O1").val("<%= blackbox.getNombre_O1() %>");
		$("#O2").val("<%= blackbox.getNombre_O2() %>");
		$("#O3").val("<%= blackbox.getNombre_O3() %>");
	}
	
    (function() {
    	resetear();
	})();
</script>