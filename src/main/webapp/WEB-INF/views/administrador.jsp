<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="\semantic\out\semantic.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="\semantic\out\semantic.min.js"></script>
    <!--<script type="text/javascript" src="/js/funcionesAdministrador.js">
    </script>
    -->
    <script>
        $(document).ready(function() {
            $('button[name=btnEliminar]').click(function() {
                $("div[class='ui dimmer modals page transition hidden']").remove();
                var datos = $(this).attr('value').split(".");
                var estaActivo = datos[4];
                var mensaje;
                var mensaje2;
                if(estaActivo=="true")
                {
                    mensaje="Desactivar";
                    mensaje2="Desactivado";
                }
                else
                {
                    mensaje="Activar";
                    mensaje2="Desactivar";
                }

                $('#body').append('<div class="ui basic modal"></div>');
                $("div[class='ui basic modal']").append('<div class="ui icon header"><i class="archive icon"></i>\n' +
                    '¿'+mensaje+' a '+datos[1]+" "+datos[2]+'? </div>');
                $("div[class='ui basic modal']").append('<div class="content">\n' +
                    '        <p>Usted esta a punto de '+mensaje+' al '+datos[3]+" "+datos[1]+" "+datos[2]+', si presiona '+mensaje+' ' +
                    'este empleado será '+mensaje2+' en el sistema </p>\n' +
                    '    </div>');
                $("div[class='ui basic modal']").append('<div class="actions">\n' +
                    '        <div id="iVolverAtras" class="ui red basic cancel inverted button">\n' +
                    '            <i class="remove icon"></i>\n' +
                    '            No\n' +
                    '        </div>\n' +
                    '        <div id="iEliminar" class="ui green ok inverted button">\n' +
                    '            <i class="checkmark icon"></i>\n' +
                    '            '+mensaje+'\n' +
                    '        </div>\n' +
                    '    </div>');
                $('.ui.basic.modal')
                    .modal('show')
                ;
                $("#iEliminar").click(function(){

                    if(estaActivo=="true")
                    {
                        $.ajax({
                            type : 'POST',
                            contentType : 'application/json; charset=utf-8',
                            dataType : 'json',
                            url : "/intranet/administrador/desactivarEmpleado",
                            data : JSON.stringify(datos[0]),
                            success : function (response) {
                                location.reload();
                            },
                            error : function (e) {
                                console.log("Error",e);
                            }
                        })
                    }
                    else
                    {
                        $.ajax({
                            type : 'POST',
                            contentType : 'application/json; charset=utf-8',
                            dataType : 'json',
                            url : "/intranet/administrador/activarEmpleado",
                            data : JSON.stringify(datos[0]),
                            success : function (response) {
                                console.log(response);
                                location.reload();
                            },
                            error : function (e) {
                                console.log("Error",e);
                            }
                        })
                    }
                });
                $("#iVolverAtras").click(function(e){
                    $('.ui.basic.modal')
                        .modal('hide');
                });
            });
        });
    </script>
    <meta charset="UTF-8"/>
    <title>Login</title>
</head>
<body id="body">
hola ${persona.nombre}
<button type="button"
        onclick="location.href='/logout'">Cerrar Sesion</button>
<button type="button"
        onclick="location.href='/intranet/administrador/crearEmpleado'">Crear Empleado</button>
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
        <c:choose>
        <c:when test="${empleados[i].estaActivo==true}">
           <td><a href="/intranet/administrador/editarEmpleado/${empleados[i].idEmpleado}">Editar</a></td>
        </c:when>
            <c:otherwise>
            <td></td>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${empleados[i].estaActivo==true}">
                <td><button name="btnEliminar" value="${empleados[i].idEmpleado}.${empleados[i].persona.nombre}.${empleados[i].persona.apellido}.${empleados[i].tipoEmpleado.descripcion}.${empleados[i].estaActivo}">Desactivar</button></td>
            </c:when>
            <c:otherwise>
                <td><button name="btnEliminar" value="${empleados[i].idEmpleado}.${empleados[i].persona.nombre}.${empleados[i].persona.apellido}.${empleados[i].tipoEmpleado.descripcion}.${empleados[i].estaActivo}">Activar</button></td>
            </c:otherwise>
        </c:choose>

    </tr>
    </c:forEach>
    </tbody>
</table>



</body>
</html>