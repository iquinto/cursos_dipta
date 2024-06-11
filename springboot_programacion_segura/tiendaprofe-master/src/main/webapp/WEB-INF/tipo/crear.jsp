<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Nuevos tipos de producto</title>
</head>
<body>
	<header>
		<h1>La tienda de los gatitos</h1>
	</header>
	<nav></nav>
	
	<article>
		<h2>Nuevos tipos de producto</h2>
		
		<!-- La validación sintáctixa SIEMPRE la haces con JS  -->
	
		<form method="post">
			<table>
				<tr>
					<td><label>Código</label></td>
					<td><input type="text" name="id" value="${tipo.id}"></td>
				</tr>
				
				<tr>
					<td><label>Nombre de tipo</label></td>
					<td><input type="text" name="nombre" value="${tipo.nombre}"></td>
				</tr>
				
				<tr>
					<td><input type="submit" value="Crear"></td>
				</tr>
			</table>
		</form>
		
		<c:if test="${! empty bien}">
			<p>El tipo se ha creado correctamente</p>
		</c:if>		
		
		<c:if test="${! empty mal}">
			<p>La vida es dura, inténtelo más tarde</p>
		</c:if>	
			
		<c:if test="${! empty error}">
			<p>Hay algo mal escrityo en algún sitio, busca a Wally</p>
		</c:if>
		
	</article>
	
	<footer>
		<p>Javier Rodríguez &copy; 2024</p>
	</footer>
</body>
</html>