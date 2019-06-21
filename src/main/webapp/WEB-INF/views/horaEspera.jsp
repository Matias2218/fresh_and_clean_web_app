<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="\stylesheets\styles.css">
    <link rel="stylesheet" href="\semantic\out\semantic.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"
          integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
          crossorigin="anonymous"></script>
        <script src="\semantic\out\semantic.min.js"></script>
    <script type="text/javascript">
        $(window).on('scroll', function() {
            if ($(window).scrollTop()) {
                $('#divblack').addClass('greysi fixed');
            } else {
                $('#divblack').removeClass('greysi fixed');
            }
        });


        $(document).ready(function() {

            $('button[name=btnAdministrar]').click(function () {
                $("div[class='ui dimmer modals page transition hidden']").remove();
                var datos = $(this).attr('value').split(".");
                console.log(datos[4]);
                $('#body').append("<div id='modalAccion' class=\"ui modal\">\n" +
                    "  <i class=\"close icon\"></i>\n" +
                    "  <div class=\"header\">\n" +
                    "     Administrar hora\n" +
                    "  </div>\n" +
                    "    <div class=\"content\">\n" +
                    "      <p>Seleccione la accion que quiere realizar correspondiente a la hora: <br>" +
                    "     Fecha: "+datos[1]+"<br>" +
                    "     Nombre: "+datos[2]+" "+datos[3]+"</p>\n" +
                    "<div class=\"ui form\">\n" +
                    "<div class=\"field\">\n" +
                    "<label>Si el motivo es rechazo, por favor escriba al motivo aqui</label>"+
                    "<textarea id='areaRechazo'>\n" +
                    "\n" +
                    "</textarea>\n" +
                    "</div>\n" +
                    "</div>"+
                    "    </div>\n" +
                    "  <div class=\"actions\">\n" +
                    "    <div class=\"ui button\" id='btnAceptarHora' >Aceptar Hora</div>\n" +
                    "    <div class=\"ui button\" id='btnRechazar' >Rechazar</div>\n" +
                    "</div>");
                    $('#modalAccion')
                    .modal('show')
                     ;

                    $('#btnRechazar').click(function () {
                        $('#modalAccion')
                            .modal('hide')
                        ;
                        $('#cargarAjax')
                        .modal({closable: false}).modal('show')
                        ;
                        $('#body').click(false);

                        $.ajax({
                            type : 'POST',
                            contentType : 'application/json; charset=utf-8',
                            dataType : 'json',
                            url : "/intranet/barbero/rechazarHora",
                            data : JSON.stringify({
                                    idHora:datos[0],
                                    motivoRechazo:$('#areaRechazo').val().trim()
                                }),
                            success : function (response) {
                                location.reload();
                            },
                            error : function (e) {
                                console.log("Error",e);
                            }
                        })
                    });

                $('#btnAceptarHora').click(function () {
                    $('#modalAccion')
                        .modal('hide')
                    ;
                    $('#cargarAjax')
                        .modal({closable: false}).modal('show')
                    ;
                    $.ajax({
                        type : 'POST',
                        contentType : 'application/json; charset=utf-8',
                        dataType : 'json',
                        url : "/intranet/barbero/horaAceptada",
                        data : JSON.stringify(datos[0]),
                        success : function (response) {
                            location.reload();
                        },
                        error : function (e) {
                            console.log("Error",e);
                        }
                    })
                });
            });
        });
    </script>
    <meta charset="UTF-8"/>
    <title>Perfil barbero</title>
</head>
<body id="body">
<!-- HEADER -->
<div id="cargarAjax" class="ui basic modal">
        <div class="ui medium text loader">Cargando</div>
</div>
<div class="pusher">
    <div class="ui vertical sc-main-intranet-perfiles center aligned segment">
        <div class="ui container">
            <div id="divblack" class="following bar ">
                <div class="ui large secondary inverted pointed fixed menu">
                    <a class="toc item"><i class="sidebar icon"></i></a>

                    <div class="right item">
                        <a class="item" href="#">Fresh & Clean</a>
                        <a class="item" href="/intranet/barbero/productos/1">Ver Productos</a>
                        <a class="item" href="/intranet/barbero/horasEspera">Horas en espera</a>
                        <a class="item" href="#">Servicios</a>
                        <a class="item" href="#">Nosotros</a>
                        <button type="button" onclick="location.href='/logout'" class="ui inverted olive button">Cerrar Sesion</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui text sc-header-content container">
            <h1 class="ui inverted header" style="font-size: 50px;">${persona.nombre}</h1>
            <p class="ui inverted header" style="padding-bottom: 10px;">Bienvenido al perfil de barbero</p>
        </div>
    </div>
</div>


<!-- END HEADER -->

<h3 class="ui center aligned header">Horas en espera</h3>

<c:choose>
    <c:when test="${fn:length(peticionHoras)>0}">
        <table class="ui celled structured olive table">
            <thead>
            <tr>
                <th rowspan="2" class="center aligned">Nombre</th>
                <th rowspan="2" class="center aligned">Email</th>
                <th rowspan="2" class="center aligned">Telefono</th>
                <th rowspan="2" class="center aligned">Genero</th>
                <th rowspan="2" class="center aligned">Servicios</th>
                <th rowspan="2" class="center aligned">Acciones</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach begin="0" var="i" end="${fn:length(peticionHoras)-1}">
                <tr>
                    <td>${peticionHoras[i].cliente.persona.nombre} ${peticionHoras[i].cliente.persona.apellido}</td>
                    <td>${peticionHoras[i].cliente.emailCliente}</td>
                    <td>${peticionHoras[i].cliente.telefonoCliente}</td>
                    <c:choose>
                        <c:when test="${peticionHoras[i].cliente.persona.genero eq 'F'.charAt(0)}">
                            <td>Femenino</td>
                        </c:when>
                        <c:otherwise>
                            <td>Masculino</td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <c:set var="servicios" value="0"/>
                        <c:forEach begin="0" var="j" end="${fn:length(peticionHoras[i].servicios)-1}">
                            ${peticionHoras[i].servicios[j].nombreServicio} $${peticionHoras[i].servicios[j].precioServicio}<br>
                            <c:set var="servicios" value="${servicios + peticionHoras[i].servicios[j].precioServicio}"/>
                        </c:forEach>
                    </td>
                    <td>
                        <button type="button" value="${peticionHoras[i].idPeticion}.${localDateTimeFormat.format(peticionHoras[i].horaAtencion)}.${peticionHoras[i].cliente.persona.nombre}.${peticionHoras[i].cliente.persona.apellido}.${servicios}" name="btnAdministrar">Administrar</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <div>No hay horas disponibles</div>
    </c:otherwise>
</c:choose>




<div style="height: 50px;"></div>
<!-- FOOTER -->
<div class="ui inverted vertical footer segment">
    <div class="ui center aligned container">
        <div class="ui stackable inverted divided grid"
             style="padding-top: 20px;">
            <div class="eleven wide column">
                <h4 class="ui inverted header">Fresh & Clean</h4>
                <div class="ui inverted link list">
                    <a href="#" class="item">Barberia Fresh & Clean</a> <a href="#"
                                                                           class="item">Servicios de barberia y belleza</a> <a href="#"
                                                                                                                               class="item">Teléfono: 225050050</a> <a href="#" class="item">freshandclean@gmail.cl</a>
                </div>
            </div>
            <div class="five wide column">
                <h4 class="ui inverted header">Redes Sociales</h4>
                <div class="ui inverted link list">
                    <a href="#" class="item"><i class="facebook outline icon"></i>
                        Facebook</a> <a href="#" class="item"><i
                        class="twitter outline icon"></i> Twitter</a> <a href="#"
                                                                         class="item"><i class="instagram outline icon"></i> Instagram</a>
                    <a href="#" class="item"><i class="pinterest outline icon"></i>
                        Pinterest</a>
                </div>
            </div>
        </div>
        <div class="ui inverted section divider"></div>
        <img src="/img/logo-blanco.png" class="ui centered image"
             style="height: 50px;">
        <div class="ui horizontal inverted small divided link list">
            <a class="item" href="#">Fresh&Clean</a> <a class="item" href="#">Contactanos</a>
            <a class="item" href="#">Nosotros</a> <a class="item" href="#">Privacy
            Policy</a>
        </div>
    </div>
</div>
<!-- END FOOTER -->
</body>
</html>