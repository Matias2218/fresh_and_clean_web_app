<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-2.1.4.js"></script>
    <link rel="stylesheet" href="\semantic\out\semantic.min.css">
    <script src="https://cdn.rawgit.com/mdehoog/Semantic-UI/6e6d051d47b598ebab05857545f242caf2b4b48c/dist/semantic.min.js"></script>
    <script type="text/javascript" src="/js/funcionesEditarEmpleado.js">
    </script>
    <meta charset="UTF-8"/>
    <title>Editar Empleado</title>
</head>
<body>
Editar Empleado
<form:form method="post" action="/intranet/inventario/crearProducto" modelAttribute="empleado">
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
            <div class="ui calendar" id="calendario">
                <div class="ui input left icon">
                    <i class="calendar icon"></i>
                    <form:input path="persona.fechaNacimiento"></form:input>
                </div>
            </div>
        </td>
    </tr>
</table>
</form:form>

</body>
</html>