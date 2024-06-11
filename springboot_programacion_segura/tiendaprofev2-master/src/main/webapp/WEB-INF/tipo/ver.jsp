<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ver tipos de producto</title>
	<link href="../css/estilos.css" rel="stylesheet"/>
</head>
<body>
	<header>
		<h1>La Tienda de los gatitos</h1>
	</header>
	
	<%@include file="/WEB-INF/varios/menu.jsp" %>
	
	<article>
		<h2>Ver tipos de producto</h2>
		
		<table class="pequeña">
			<thead>
				<tr>
					<td>Código de tipo de producto</td>
					<td>Nombre de tipo de producto</td>
				</tr>
			</thead>
			
			
			<c:forEach items="${tipos}" var="t">
				<tbody>
					<tr>
						<td>${t.id}</td>
						<td>${t.nombre}</td>
						<%--Esto ya no existe: ${t.cosasSecretas} --%>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		
		
		
	</article>
	
	<footer>
		<p>Javier Rodríguez &copy; 2024</p>
	</footer>
</body>
</html>