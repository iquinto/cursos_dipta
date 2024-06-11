<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Nuevos tipos de producto</title>
	<link href="../css/estilos.css" rel="stylesheet"/>
	<sec:csrfMetaTags/>
	
	<script type="text/javascript" src="../js/jquery-3.7.1.js"></script>
	<script type="text/javascript" src="../js/creartipo.js"></script>
</head>
<body>
	<header>
		<h1>La Tienda de los gatitos</h1>
	</header>
	
	<%@include file="/WEB-INF/varios/menu.jsp" %>
	
	<article>
		<h2>Nuevos tipos de producto</h2>
		
		<!-- La validación sintáctixa SIEMPRE la haces con JS  -->
	
		<form method="post" id="formulario">
		<%--
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		 --%>
			<sec:csrfInput/>
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
					<td colspan="2"><input type="submit" value="Crear"></td>
				</tr>
			</table>
		</form>

		<p id="bien">El tipo se ha creado correctamente</p>
		<p id="mal">La vida es dura, inténtelo más tarde</p>


		
	</article>
	
	<footer>
		<p>Javier Rodríguez &copy; 2024</p>
	</footer>
</body>
</html>