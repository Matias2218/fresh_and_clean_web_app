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

<button type="button"
        onclick="location.href='/logout'">Cerrar Sesion</button>
${empleado.persona.nombre}
<form method="post" action="/intranet/gerente/informes/finanzas?format=pdf">
        <select name="cmbAñoBoleta">
            <c:forEach var="añoBoleta" items="${añosBoletas}">
            <option value="${añoBoleta}">${añoBoleta}</option>
            </c:forEach>
        </select>

         <select name="cmbMesBoleta">
             <c:forEach var="mesBoleta" items="${mesesBoletas}">
             <option value="${mesBoleta.key}">${mesBoleta.value}</option>
             </c:forEach>
         </select>
        <br/>
        <input type="submit" value="Generar PDF"/>
</form>
</body>
</html>