<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String id = (String) request.getAttribute("id");
	String passwd = (String) request.getAttribute("passwd");
	String usuario = (String) request.getAttribute("usuario");
%>
  
<h1>Blackbox</h1>

id: <%= id %><br>
passwd: <%= passwd %><br>
usuario: <%= usuario %><br>
