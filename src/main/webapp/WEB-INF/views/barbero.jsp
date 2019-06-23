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
                    "      <p><b>Fecha: "+datos[1]+"<br>" +
                    "      Nombre: "+datos[2]+" "+datos[3]+"</p></b>\n" +
                    "      <p>Seleccione la accion que quiere realizar correspondiente a la hora:<br>" +
                    "      <b>Rechazada:</b> Si el cliente no se presento a la cita o ocurre un imprevisto y no se podrá realizar la hora, presionar “Rechazar”, para este campo debe escribir el motivo del rechazo.<br>" +
                    "      <b>Realizada:</b> Si la cita fue realizada, presionar “Realizada”, ya que con esto se guardará y generará la boleta, para este campo no se necesita escribir el motivo.</p>" +
                    "<div class=\"ui form\">\n" +
                    "<div class=\"field\">\n" +
                    "<label style='color:grey;'>Si es rechazo, por favor escriba al motivo aquí</label>"+
                    "<textarea id='areaRechazo'>\n" +
                    "\n" +
                    "</textarea>\n" +
                    "</div>\n" +
                    "</div>"+
                    "    </div>\n" +
                    "  <div class=\"actions\">\n" +
                    "    <div class=\"ui red button\" id='btnRechazar' >Rechazada</div>\n" +
                    "    <div class=\"ui green button\" id='btnRealizada' >Realizada</div>\n" +
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

                $('#btnRealizada').click(function () {
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
                        url : "/intranet/barbero/horaRealizada",
                        data : JSON.stringify({
                             idHora:datos[0],
                             totalServicio:datos[4]
                        }),
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
	<div class="pusher card">
		<div
			class="ui vertical sc-main-intranet-perfiles center aligned segment">
			<div class="ui container">
				<div id="divblack" class="following bar">
					<div class="ui large secondary inverted pointed fixed menu">
						<a class="item sin-hover" href="/intranet/barbero/horas"><img src="/img/logo-blanco.png" class="ui tiny image"> </a>
						<div class="right item">
							<a class="item" href="/intranet/barbero/horas">Perfil</a>
							<a class="item" href="/intranet/barbero/productos/1">Ver Productos</a>
							<div class="dropdown">
								<a class="item" href="#">Horas<i class="dropdown icon"></i></a>
								<div class="dropdown-content">
									<a href="/intranet/barbero/horas">Horas Aceptadas</a> 
									<a href="/intranet/barbero/horasEspera">Horas en Espera</a>
								</div>
							</div>

							<button type="button" onclick="location.href='/logout'"
								class="ui inverted olive button">Cerrar Sesion</button>
						</div>
					</div>
				</div>
			</div>
	<div class="bottomleft">
					<h1 class="titulos-perfiles margenes-abajo">${persona.nombre} ${persona.apellido}</h1>
					<p class="ui grey inverted left aligned header perfiles">Bienvenido al perfil barbero</p>
				</div>
		</div>
	</div>
<!-- END HEADER -->

<div class="column margen-arriba margen-abajo">
		<h2 class="ui center aligned header">Agenda<div class="sub header">Aquí podrá ver las horas que ha aceptado para ser agendadas<br>Al presionar “Administrar” podrá editar el estado de estas horas<br>También puede filtrar estas mismas por fecha si así lo desea</div></h2>
	</div>
<div class="ui container">
<c:choose>
    <c:when test="${fn:length(peticionHoras)>0}">


        <form method="get" action="/intranet/barbero/horas" class="ui form">
        <h4 class="margen-abajo2">Buscar por fecha</h4>
        <div class="fields">
    		<div class="three wide field">
	            <input type="date" name="fechaBuscar">
            </div>
            <div class="two wide field">
            	<input type="submit" value="Buscar" class="ui olive button">
            </div>
        </div>
        
        </form>
        <table class="ui celled structured olive table">
            <thead>
            <tr>
                <th rowspan="2" class="center aligned">Nombre</th>
                <th rowspan="2" class="center aligned">Email</th>
                <th rowspan="2" class="center aligned">Telefono</th>
                <th rowspan="2" class="center aligned">Fecha Hora</th>
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
                    <td>${fechasFormateadas[i]}</td>
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
                        <button type="button" class="ui yellow button" value="${peticionHoras[i].idPeticion}.${localDateTimeFormat.format(peticionHoras[i].horaAtencion)}.${peticionHoras[i].cliente.persona.nombre}.${peticionHoras[i].cliente.persona.apellido}.${servicios}" name="btnAdministrar">Administrar</button>
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
</div>



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