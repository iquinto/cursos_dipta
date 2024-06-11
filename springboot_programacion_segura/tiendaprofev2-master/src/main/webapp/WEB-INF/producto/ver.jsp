<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" prefix="e" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ver productos</title>
	<link href="../css/estilos.css" rel="stylesheet"/>
</head>
<body>
	<header>
		<h1>La Tienda del Gatito</h1>
	</header>
	
	<%@include file="/WEB-INF/varios/menu.jsp" %>
	
	<article>
		<h2>Ver Productos</h2>
		<table class="mediana">
			<thead>
				<tr>
					<td>Referencia</td>
					<td>Nombre de producto</td>
					<td>Precio</td>
					<td>Tipo</td>
				</tr>
			</thead>
			
			
			<c:forEach items="${productos}" var="p">
				<tbody>
					<tr>
						<td>${p.referencia}</td>
						<%--
						<td><spring:eval expression="p.nombre"/></td>
						 --%>
						<td>${e:forHtmlContent(p.nombre)}</td>
						<td>${p.precio}</td>
						<td>${p.tipoNombre}</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<p>Nunc iaculis consequat gravida. Praesent ullamcorper, libero cursus hendrerit fringilla, sem nunc molestie mauris, venenatis egestas justo dui non enim. Vivamus eu ex ut quam dictum scelerisque quis vel neque. Suspendisse vel nisi vel neque tempus viverra ut non sem. Vestibulum finibus elit sem, id egestas dolor dignissim non. Mauris lacinia, felis eu pharetra euismod, urna odio dapibus elit, quis efficitur turpis ligula ac eros. Vivamus lobortis, velit sed fringilla varius, enim sem maximus nulla, eu viverra diam neque nec nibh. Vivamus rhoncus eget tortor eu suscipit. Mauris tempor, neque vel dictum luctus, quam quam vestibulum massa, fringilla facilisis lectus odio eu arcu. Aenean vestibulum ultricies quam, id dignissim lorem varius et. Sed sit amet egestas neque, non dapibus lacus. Nunc venenatis sit amet ante ut porta. Maecenas at lorem lorem.</p>
	</article>
	
	<footer>
		<p>Javier Rodríguez &copy; 2024</p>
	</footer>
</body>
</html>