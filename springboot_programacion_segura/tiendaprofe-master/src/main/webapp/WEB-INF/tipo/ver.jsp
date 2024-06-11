<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ver tipos de producto</title>
</head>
<body>
	<header>
		<h1>La tienda de los gatitos</h1>
	</header>
	<nav></nav>
	
	<article>
		<h2>Ver tipos de producto</h2>
		<c:forEach items="${tipos}" var="t">
			<p>${t.id} ${t.nombre} ${t.cosasSecretas}</p>
		</c:forEach>
		
	</article>
	
	<footer>
		<p>Javier Rodríguez &copy; 2024</p>
	</footer>
</body>
</html>