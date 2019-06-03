<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="\semantic\out\semantic.min.css">
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/js/funcionesEditarEmpleado.js">
    </script>
    <meta charset="UTF-8"/>
    <title>Crear Empleado</title>
</head>
<body>
Crear Empleado
<form:form method="post" action="/intranet/administrador/crearEmpleado" modelAttribute="empleado">
<table>
    <tr>
        <td><form:label path="persona.nombre">Nombre</form:label></td>
        <td><form:input path="persona.nombre"/></td>
    </tr>
    <tr>
        <td><form:label path="persona.apellido">Apellido</form:label></td>
        <td><form:input path="persona.apellido"></form:input></td>
    </tr>
    <tr>
        <td><form:label path="persona.fechaNacimiento">Fecha de Nacimiento</form:label></td>
        <td>
            <form:input type="date" path="persona.fechaNacimiento"></form:input>
        </td>
    </tr>
    <tr>
        <td><form:label path="persona.genero">Genero</form:label></td>
        <td><form:radiobutton path="persona.genero" value="M" />Masculino<form:radiobutton
                path="persona.genero" value="F" />Femenino</td></td>
    </tr>
    <tr>
        <td><form:label path="usernameEmpleado">Nombre de Usuario</form:label></td>
        <td><form:input path="usernameEmpleado"/></td>
    </tr>
    <tr>
        <td><form:label path="passwordEmpleado">Contraseña</form:label></td>
        <td><form:password path="passwordEmpleado"/></td>
    </tr>
    <tr>
        <td><form:label path="passwordConfirmEmpleado">Confirmar Constraseña</form:label></td>
        <td><form:password path="passwordConfirmEmpleado"/></td>
    </tr>
    <tr>
        <td><form:label path="emailEmpleado">Email</form:label></td>
        <td><form:input path="emailEmpleado"/></td>
    </tr>
    <tr>
        <td><form:label path="telefonoEmpleado">Telefono</form:label></td>
        <td><form:input path="telefonoEmpleado"/></td>
    </tr>
    <tr>
        <td><form:label path="sueldoEmpleado">Sueldo</form:label></td>
        <td><form:input path="sueldoEmpleado"/></td>
    </tr>
    <tr>
        <td>Tipo de empleado</td>
        <td><form:select path="tipoEmpleado.idTipo" size="1"
                         class="ui fluid dropdown">
            <form:option value="0" label="--Seleccione Categoria--" />
            <form:options items="${tipoEmpleados}" itemValue="idTipo"
                          itemLabel="descripcion" />
        </form:select></td>
    </tr>
    <tr>
        <td></td>
        <td><input type="submit" value="Crear"/></td>
    </tr>




</table>
</form:form>

</body>
</html>