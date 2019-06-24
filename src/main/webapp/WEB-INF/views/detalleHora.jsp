<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="\stylesheets\styles.css">
<link rel="stylesheet" href="\semantic\out\semantic.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script src="\semantic\out\semantic.min.js"></script>
<meta charset="ISO-8859-1">
<title>Detalle de su pedido</title>
<script type="text/javascript">
        $(window).on('scroll', function () {
            if ($(window).scrollTop()) {
                $('#divblack').addClass('greysi fixed');
            } else {
                $('#divblack').removeClass('greysi fixed');
            }
        })

        
      ;
    </script>
	<link rel="shortcut icon" type="image/png" href="/img/fyclogo.png"/>
</head>
<body>
	<!-- HEADER -->
<div class="pusher">
    <div class="ui vertical sc-main-paginas center aligned segment">
    	<!-- NAV -->
        <div class="ui container">
            <div id="divblack" class="following bar ">
                <div class="ui large secondary inverted pointed fixed menu">
                	<a class="item sin-hover" href="/"><img src="../img/logo-blanco.png" class="ui tiny image"> </a>
                	
                    <div class="right item">
                        <a class="item" href="/pedirHora">Agendar Hora</a> 
                        <div class="dropdown">
                        <a class="item" href="#">Nosotros<i class="dropdown icon"></i></a>
							<div class="dropdown-content">
							   <a href="#">Servicios</a>
							   <a href="#">Peluqueros</a>
							   <a href="#">Preguntas Frecuentes</a>
							</div>	  
						</div>
						<a class="item" href="#footer">Contáctanos</a> 
					</div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END HEADER -->


	<h4 class="ui center aligned icon header">
		<i class="calendar check outline icon"></i>
	</h4>
	<h3 class="ui center aligned header">¡Su solicitud ha sido
		enviada!</h3>
	<div class="ui center aligned container">
		<p>
			Su solicitud de hora ha sido enviada y se encuentra en proceso de
			evaluacion<br> Una vez esta sea aceptada/rechazada, se le
			enviara un correo a ${peticionHora.cliente.emailCliente}<br>
			Para mas informacion, no dude en contactarnos por medio de nuestras
			redes sociales o por el correo <b>freshandclean@gmail.cl</b>
		</p>
	</div>
	
	<div class="ui text stackable container">
	<h4 class="ui h4margen header">Detalle solicitud</h4>
		<table class="ui celled olive table">
			<tbody>
				<tr>
					<td>Nombre</td>
					<td>${peticionHora.cliente.persona.nombre} ${peticionHora.cliente.persona.apellido}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>${peticionHora.cliente.emailCliente}</td>
				</tr>
				<tr>
					<td>Telefono</td>
					<td>${peticionHora.cliente.telefonoCliente}</td>
				</tr>
				<tr>
					<td>Barbero</td>
					<td>${peticionHora.empleado.persona.nombre}</td>
				</tr>
				<tr>
					<td>Servicios</td>
					<td>
					<c:forEach items="${peticionHora.servicios}" var="servicio">
					$ ${servicio.precioServicio}:  ${servicio.nombreServicio} <br>
					</c:forEach>
					</td>
				</tr>
				<tr>
					<td>Fecha de atencion</td>
					<td>${fecha}</td>
				</tr>
				<tr>
					<td>Hora solicitada</td>
					<td>${hora} hrs</td>
				</tr>
				<tr class="warning">
					<td>Estado actual</td>
					<td>En ${peticionHora.estado}</td>
				</tr>
			</tbody>
		</table>
	</div>



	<div style="height: 50px;"></div>
<!-- FOOTER -->
<footer class="ui inverted vertical footer segment" id="footer">
    <div class="ui center aligned container">
        <div class="ui stackable inverted divided grid pad-footer">
            <div class="eleven wide column">
                <h4 class="ui inverted header">Fresh & Clean</h4>
                <div class="ui inverted link list">
                    <a href="#" class="item">Barbería Fresh & Clean</a> 
                    <a href="#" class="item">Servicios de barbería y belleza</a> 
                    <a href="#" class="item">Teléfono: 225050050</a> 
                    <a href="#" class="item">freshandclean@gmail.cl</a>
                </div>
            </div>
            <div class="five wide column">
                <h4 class="ui inverted header">Redes Sociales</h4>
                <div class="ui inverted link list">
                    <a href="#" class="item"><i class="facebook outline icon"></i>Facebook</a> 
                    <a href="#" class="item"><i class="twitter outline icon"></i>Twitter</a> 
                    <a href="#" class="item"><i class="instagram outline icon"></i>Instagram</a>
                    <a href="#" class="item"><i class="pinterest outline icon"></i>Pinterest</a>
                </div>
            </div>
        </div>
        <div class="ui inverted section divider"></div>
        <img src="../img/logo-blanco.png" class="ui small centered image">
        <div class="ui horizontal inverted small divided link list">
            <a class="item" href="#">Fresh&Clean</a> 
            <a class="item" href="#">Contáctanos</a>
            <a class="item" href="#">Nosotros</a> 
            <a class="item" href="#">Privacy Policy</a>
        </div>
    </div>
</footer>
<!-- END FOOTER -->
</body>
</html>