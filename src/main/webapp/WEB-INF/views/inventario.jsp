<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="\semantic\out\semantic.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script src="\semantic\out\semantic.min.js"></script>
<meta charset="UTF-8" />
<link rel="stylesheet" href="\stylesheets\styles.css">
<title>Bienvenido ${persona.nombre}</title>
<script type="text/javascript">
	$(window).on('scroll', function() {
		if ($(window).scrollTop()) {
			$('#divblack').addClass('greysi fixed');
		} else {
			$('#divblack').removeClass('greysi fixed');
		}
	})

	$(document).ready(function () {
		$('.message .close')
				.on('click', function() {
					$(this)
							.closest('.message')
							.transition('fade')
					;
				})
		;
	});

</script>
</head>
<body>
	<!-- HEADER -->
	<div class="pusher card">
		<div
			class="ui vertical sc-main-intranet-perfiles center aligned segment">
			<div class="ui container">
				<div id="divblack" class="following bar">
					<div class="ui large secondary inverted pointed fixed menu">
						<a class="item sin-hover" href="/intranet/inventario"><img src="/img/logo-blanco.png" class="ui tiny image"> </a>
						<div class="right item">
							<a class="item" href="/intranet/inventario">Perfil</a> <a
								class="item" href="/intranet/inventario">Ver Productos</a> <a
								href="/intranet/inventario/crearProducto" class="item">Crear
								Producto</a>
							<button type="button" onclick="location.href='/logout'"
								class="ui inverted olive button">Cerrar Sesion</button>
						</div>
					</div>
				</div>
			</div>


				<div class="bottomleft">
					<h1 class="titulos-perfiles margenes-abajo">${persona.nombre} ${persona.apellido}</h1>
					<p class="ui grey inverted left aligned header perfiles">Bienvenido al perfil de Inventario</p>
				</div>
		</div>
	</div>
	<!-- END HEADER -->

	<c:if test="${mensaje != null}">
		<div class="ui success message">
			<i class="close icon"></i>
			<div class="header">
					${mensaje}
			</div>
		</div>
	</c:if>
	<div style="height: 30px;"></div>
	<div class="ui container">
				<div class="ui horizontal section divider">Productos en Inventario</div>
				<div class="ui three column centered grid">
				<c:forEach begin="0" var="i" end="${fn:length(productos)-1}">
						<div class="column">
							<div class="ui special centered cards">
								<div class="card alto-carta">
									<div class="blurring dimmable image">
										<div class="ui dimmer">
											<div class="content">
												<div class="center">
													<div class="ui inverted button" onclick="location.href='/intranet/inventario/editarProducto/${productos[i].id}'">Editar</div>
												</div>
											</div>
										</div>
										<img src="http://localhost:8081/api/uploads/img/${productos[i].imagen}">
									</div>
									<script>
										$('.special.cards .image').dimmer({
											on : 'hover'
										});
									</script>
									<div class="content">
										<a class="header">${productos[i].nombre}</a>
										<div class="meta">
											<span class="date">${productos[i].marca.nombre}</span>
										</div>
									</div>
									<div class="extra content">
										<a href="/intranet/inventario/editarProducto/${productos[i].id}">
											<i class="clipboard list icon"></i> Stock: ${productos[i].stock}
										</a>
									</div>
								</div>
							</div>
						</div>
				</c:forEach>
				</div>

					<div class="ui horizontal section divider">
				<div class="ui pagination menu margenes">
						<c:if test="${page.pageNumber != 0}">
							<a href="/intranet/inventario/1}" class="item">Primera</a>
						</c:if>
						<c:if test="${page.pageNumber != 0}">
							<a href="/intranet/inventario/${(page.pageNumber+1) - 1}" class="item">&laquo;</a>
						</c:if>
						<c:forEach begin="1" end="${totalPaginas}" var="i">
							<c:choose>
								<c:when test="${page.pageNumber+1 eq i}">
									<a class="item">${i}</a>
								</c:when>
								<c:otherwise>
									<a href="/intranet/inventario/${i}" class="item">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${page.pageNumber+1 < totalPaginas}">
							<a href="/intranet/inventario/${(page.pageNumber+1) + 1}" class="item">&raquo;</a>
						</c:if>
						<c:if test="${page.pageNumber+1 < totalPaginas}">
							<a href="/intranet/inventario/${totalPaginas}" class="item">Ultima</a>
						</c:if>
					
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
        <img src="/img/logo-blanco.png" class="ui small centered image">
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