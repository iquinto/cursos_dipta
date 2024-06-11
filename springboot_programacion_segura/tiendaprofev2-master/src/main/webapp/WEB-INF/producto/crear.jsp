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
	
	<%@include file="/WEB-INF/varios/menu.jsp" %>
	
	<article>
		<h2>Crear productos</h2>
		<p>Donec sit amet justo vitae tellus pharetra volutpat ac a est. Duis volutpat tellus in neque eleifend ullamcorper. Proin diam nunc, scelerisque non tempus at, posuere ut libero. Vestibulum lobortis lobortis urna sed ultricies. Praesent arcu erat, tincidunt nec arcu eget, venenatis vulputate turpis. Sed elementum tellus lacinia, vehicula ipsum eget, pretium nunc. In ut pharetra tortor. In faucibus porta libero, eu eleifend nulla scelerisque et. Duis neque arcu, varius sodales euismod vitae, sagittis at lacus. Maecenas dictum, nisl id pulvinar fringilla, ipsum lectus sagittis enim, nec consectetur nisl erat rutrum leo. Pellentesque eu nibh porttitor, fringilla quam vel, ultricies nunc. Vestibulum commodo lorem a elit placerat, nec blandit tellus dapibus. Maecenas consequat dui magna, nec imperdiet erat pharetra eu.</p>
		
		<form method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<table>
				<tr>
					<td><label>Referencia</label></td>
					<td><input type="text" name="referencia" value="${productoDTO.referencia}"></td>
				</tr>

				<tr>
					<td><label>Nombre de producto</label></td>
					<td><input type="text" name="nombre" value="${productoDTO.nombre}"></td>
				</tr>

				<tr>
					<td><label>precio de venta</label></td>
					<td><input type="text" name="precio" value="${productoDTO.precio}"></td>
				</tr>

				<tr>
					<td><label>Tipo de producto</label></td>
					<td><input type="text" name="tipoId" value="${productoDTO.tipoId}"></td>
				</tr>

				
				<tr>
					<td colspan="2"><input type="submit" value="Crear Producto nuevo"></td>
				</tr>
			</table>
		</form>
		
		<c:if test="${! empty bien}">
			<p>El tipo se ha creado correctamente</p>
		</c:if>		
		
		<c:if test="${! empty mal}">
			<p>La operación ha fallado. Es posible que la refetencia ya exista, o el tipo te lo hayas inventado</p>
		</c:if>	
			
		<%-- 
		<c:if test="${! empty error}">
			<p>Deja de tocar las narices</p>
		</c:if>
		--%>	
		
		<p>Net est scelerisque, nunc molestie mauris, venenatis egestas justo dui non enim. Vivamus eu ex ut quam dictum scelerisque quis vel neque. Suspendisse vel nisi vel neque tempus viverra ut non sem. Vestibulum finibus elit sem, id egestas dolor dignissim non. Mauris lacinia, felis eu pharetra euismod, urna odio dapibus elit, quis efficitur turpis ligula ac eros. Vivamus lobortis, velit sed fringilla varius, enim sem maximus nulla, eu viverra diam neque nec nibh. Vivamus rhoncus eget tortor eu suscipit. Mauris tempor, neque vel dictum luctus, quam quam vestibulum massa, fringilla facilisis lectus odio eu arcu. Aenean vestibulum ultricies quam, id dignissim lorem varius et. Sed sit amet egestas neque, non dapibus lacus. Nunc venenatis sit amet ante ut porta. Maecenas at lorem lorem.</p>
	</article>
	
	<footer>
		<p>Javier Rodríguez &copy; 2024</p>
	</footer>
</body>
</html>