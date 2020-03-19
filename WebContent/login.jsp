<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Centinela</title>
</head>
<body>
<main>
	<form method="post" action="Login">
		<label for="email">e-Mail:</label>
		<input type="text" id="email" name="email" required>
		<label for="passwd">Contraseña:</label>
		<input type="password" id="passwd" name="passwd" required>
		<input type="submit">
	</form>
</main>
</body>
</html>