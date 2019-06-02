<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="\semantic\out\semantic.min.css">
        <script
          src="https://code.jquery.com/jquery-3.1.1.min.js"
          integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
          crossorigin="anonymous"></script>
        <script src="\semantic\out\semantic.min.js"></script>
    <meta charset="UTF-8"/>
    <title>Login</title>
</head>
<body>
hola ${persona.nombre}
<button type="button"
        onclick="location.href='/logout'">Cerrar Sesion</button>

<table>
    <thead>
    <tr>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Email</th>
        <th>Sueldo</th>
        <th>Cargo</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach begin="0" var="i" end="${fn:length(empleados)-1}">
    <tr>
        <td>${empleados[i].persona.nombre}</td>
        <td>${empleados[i].persona.apellido}</td>
        <td>${empleados[i].emailEmpleado}</td>
        <td>$${sueldos[i]}</td>
        <td>${empleados[i].tipoEmpleado.descripcion}</td>
        <td><a href="/intranet/administrador/editarEmpleado/${empleados[i].idEmpleado}">Editar</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>