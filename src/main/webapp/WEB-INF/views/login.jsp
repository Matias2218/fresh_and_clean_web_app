<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="\semantic\out\semantic.min.css">
<link rel="stylesheet" href="\stylesheets\styles.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script src="\semantic\out\semantic.min.js"></script>
<meta charset="UTF-8" />
<title>Intranet</title>
<script type="text/javascript">
	$(window).on('scroll', function() {
		if ($(window).scrollTop()) {
			$('#divblack').addClass('greysi fixed');
		} else {
			$('#divblack').removeClass('greysi fixed');
		}
	})

	$('.special.cards .image').dimmer({
		on : 'hover'
	});
</script>
</head>
<body>

	<!-- HEADER -->
	<div class="pusher">
		<div class="ui vertical sc-main-intranet center aligned segment">
			<!-- NAV -->
			<div class="ui container">
				<div id="divblack" class="following bar ">
					<div class="ui large secondary inverted pointed fixed menu">
						<div class="right item">
							<a class="item" href="/">Fresh & Clean</a> 
							<a class="item" href="#">Anuncios</a>
							<div class="dropdown">
	                        	<a class="item" href="#">Nosotros<i class="dropdown icon"></i></a>
								<div class="dropdown-content">
								   <a href="#">Servicios</a>
								   <a href="#">Peluqueros</a>
								   <a href="#">Preguntas Frecuentes</a>
								</div>	  
							</div>
							<a class="item" href="#footer">Contactanos</a> 
						</div>
						
						</div>
					</div>
				</div>
			</div>
			
			<!-- IMG TEXT -->
			<div class="ui text center aligned  sc-header-content container">
				<h1 class="ui inverted header titulo-index">FRESH &
					CLEAN</h1>
				<p class="ui inverted header" style="padding-bottom: 10px;">Bienvenido
					a la Intranet de Fresh&Clean</p>
				<a class="ui huge olive right labeled icon button sc-button" href="#iniciosesion">Iniciar
					Sesion<i class="sign-in icon"></i>
				</a>
			</div>
		</div>
	<!-- END HEADER -->



	<div style="height: 50px;"></div>

	<div
		class="ui three column center aligned very relaxed stackable grid container">
		<div class="row">
			<div class="column">
			</div>

			<div class="column">
			</div>

			<div class="column">
				<div class="ui placeholder segment center aligned" id="iniciosesion">
					<form action="/intranet/" method="post">
						<div class="column">
							<div class="ui form">
								<div class="field">
									<label>Usuario</label>
									<div class="ui left icon input">
										<input type="text" name="username" placeholder="Usuario">
										<i class="user icon"></i>
									</div>
								</div>
								<div class="field">
									<label>Contraseña</label>
									<div class="ui left icon input">
										<input type="password" name="password" placeholder="******">
										<i class="lock icon"></i>
									</div>
								</div>

								<div class="field">
									<!-- Mensajes de Alerta -->
									<c:if test = "${error!=null}">
										<div class="ui red message" style="font-size: xx-small"><c:out value = "${error}"/></div>
									</c:if>

									<c:if test = "${logout!=null}">
										<div class="ui yellow message" style="font-size: xx-small"><c:out value = "${logout}"/></div>
									</c:if>

									<c:if test = "${errorServidor!=null}">
										<div class="ui red message" style="font-size: xx-small"><c:out value = "${errorServidor}"/></div>
									</c:if>
									<!-- Mensajes de Alerta -->
								</div>

								<input type="submit" value="Iniciar Sesion"
									class="ui inverted olive submit button" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
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
            <a class="item" href="#">Contactanos</a>
            <a class="item" href="#">Nosotros</a> 
            <a class="item" href="#">Privacy Policy</a>
        </div>
    </div>
</footer>
<!-- END FOOTER -->
</body>
</html>