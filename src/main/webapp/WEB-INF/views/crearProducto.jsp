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
    <script type="text/javascript" src="/js/funciones.js">
    </script>

    <meta charset="UTF-8"/>
    <title>Login</title>
</head>
<body>

crear Producto
<form:form method="post" action="/intranet/inventario/crearProducto" enctype="multipart/form-data" modelAttribute="producto">
<table>
    <tr>
        <td><form:label path="nombre">Nombre</form:label></td>
        <td><form:input path="nombre"/></td>
    </tr>
    <tr>
        <td><form:label path="descripcion">Descrpcion</form:label></td>
        <td><form:textarea path="descripcion"></form:textarea></td>
    </tr>
    <tr>
    <td>Categoria</td>
        <td>
            <form:select  path="categoria.id" size="1">
                <form:option  value="0"  label="--Seleccione Categoria--" />
                <form:options items="${categorias}" itemValue="id" itemLabel="nombre"/>
            </form:select>
        </td>
    </tr>
    <tr>
        <td>Marca</td>
        <td>
            <form:select path="marca.id" size="1">
                <form:option  value="0"  label="--Seleccione Marca--" />
                <form:options items="${marcas}" itemValue="id" itemLabel="nombre"/>
            </form:select>
        </td>
    </tr>
    <tr>
        <td><form:label path="stock">Stock</form:label></td>
        <td><form:input path="stock"/></td>
    </tr>
    <tr>
        <td>Foto</td>
        <td><img style="width:80px;" id="imagenSalida" name="imagenSalida" src=""></td>
        <td><input type="file" name="file" id="foto" onchange="readURL(this)" accept=".png, .jpg, .jpeg"/></td>
    </tr>
    <tr>
        <td></td>
        <td><input type="submit" value="Crear"/></td>
    </tr>
</table>
</form:form>



</body>
</html>