<%@ page contentType="text/html;charset=UTF-8"%>
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
hola barbero
<button type="button"
        onclick="location.href='/logout'">Cerrar Sesion</button>
${empleado.persona.nombre}
<a href="/intranet/barbero/productos/1">Ver productos</a>
</body>
</html>