<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Crear productos</title>
	<link href="../css/estilos.css" rel="stylesheet"/>
</head>
<body>
	<header>
		<h1>La Tienda del Gatito</h1>
	</header>

	<article>
		<h2>Entrada a la tienda</h2>
		<form method="post" action="../entrada/procesar">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<table>
				<tr>
					<td><label>Nombre de usuario</label></td>
					<td><input type="text" name="login"></td>
				</tr>

				<tr>
					<td><label>Clave Secreta</label></td>
					<td><input type="text" name="clave"></td>
				</tr>
				
				<tr>
					<td colspan="2"><input type="submit" value="Entrar"></td>
				</tr>
			</table>
		</form>
		
	</article>
	
	<footer>
		<p>Javier Rodríguez &copy; 2024</p>
	</footer>
</body>
</html>