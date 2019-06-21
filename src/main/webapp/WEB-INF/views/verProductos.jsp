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
<title>Bienvenido Gerente</title>
<script type="text/javascript">
	$(window).on('scroll', function() {
		if ($(window).scrollTop()) {
			$('#divblack').addClass('greysi fixed');
		} else {
			$('#divblack').removeClass('greysi fixed');
		}
	})
</script>
</head>
<body>

	<div style="height: 50px;"></div>

	<div class="ui container">
		
		<div class="ui segments">
			<div class="ui segment">

				<div class="ui horizontal section divider">Productos en Inventario</div>
				<div class="ui three column grid container">
					
				<c:forEach begin="0" var="i" end="${fn:length(productos)-1}">
						<div class="column">
							<script>
								$('.special.cards .image').dimmer({
									on : 'hover'
								});
							</script>
							<div class="ui special cards">
								<div class="card" STYLE="height: 410px;">
										<img src="http://localhost:8081/api/uploads/img/${productos[i].imagen}">
									<div class="content">
										<a class="header">${productos[i].nombre}</a>
										<div class="meta">
											<span class="date">${productos[i].marca.nombre}</span>
										</div>
									</div>
									<div class="extra content">

											<i class="clipboard list icon"></i> Stock: ${productos[i].stock}
									</div>
								</div>
							</div>
						</div>
				</c:forEach>
				</div>

					<div class="ui horizontal section divider">
				<div class="ui pagination menu margenes">
						<c:if test="${page.pageNumber != 0}">
							<a href="/intranet/barbero/productos/1" class="item">Primera</a>
						</c:if>
						<c:if test="${page.pageNumber != 0}">
							<a href="/intranet/barbero/productos/${(page.pageNumber+1) - 1}" class="item">&laquo;</a>
						</c:if>
						<c:forEach begin="1" end="${totalPaginas}" var="i">
							<c:choose>
								<c:when test="${page.pageNumber+1 eq i}">
									<a class="item">${i}</a>
								</c:when>
								<c:otherwise>
									<a href="/intranet/barbero/productos/${i}" class="item">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${page.pageNumber+1 < totalPaginas}">
							<a href="/intranet/barbero/productos/${(page.pageNumber+1) + 1}" class="item">&raquo;</a>
						</c:if>
						<c:if test="${page.pageNumber+1 < totalPaginas}">
							<a href="/intranet/barbero/productos/${totalPaginas}" class="item">Ultima</a>
						</c:if>
					</tr>
					</div>
				</div>
			</div>
		</div>
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