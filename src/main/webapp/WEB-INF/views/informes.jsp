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
    <title>Informes</title>
    <script  src="/js/funcionesInformes.js">
    </script>
</head>
<body>
<!-- HEADER -->
	<div class="pusher card">
		<div
			class="ui vertical sc-main-intranet-perfiles2 center aligned segment">
			<div class="ui container">
				<div id="divblack" class="following bar">
					<div class="ui large secondary inverted pointed fixed menu">
						<a class="item sin-hover" href="/intranet/gerente"><img src="/img/logo-blanco.png" class="ui tiny image"> </a>
						<div class="right item">
							<a class="item" href="/intranet/gerente">Perfil</a>
							<div class="dropdown">
								<a class="item" href="#">Informes<i class="dropdown icon"></i></a>
								<div class="dropdown-content">
									<a href="/intranet/gerente/informes">Informes según mes</a> 
									<a href="/intranet/gerente/informes">Atención de barberos</a>
								</div>
							</div>

							<button type="button" onclick="location.href='/logout'"
								class="ui inverted olive button">Cerrar Sesion</button>
						</div>
					</div>
				</div>
			</div>


				<div class="bottomleft">
					<p class="ui grey inverted left aligned header perfiles">Perfil gerencial</p>
				</div>
		</div>
	</div>
	<!-- END HEADER -->
<div class="column margen-arriba margen-abajo">
		<h2 class="ui center aligned header">Informes<div class="sub header">Aquí podrá generar un archivo PDF del informe que desee<br>Recuerde ingresar los parámetros si este lo requiere</div></h2>
	</div>
	
<div class="ui horizontal section divider">Informes según mes</div>

<form method="post" target="_blank" action="/intranet/gerente/informes/finanzas?format=pdf" >
    <div class="ui text container">
    <div class="ui form">
        <div class="ui two column grid container">
            <div class="column">
            <select name="cmbAñoBoleta" class="field">
                <c:forEach var="añoBoleta" items="${añosBoletas}">
                <option value="${añoBoleta}">${añoBoleta}</option>
                </c:forEach>
            </select>
            </div>
            <div class="column">
             <select name="cmbMesBoleta">
                 <c:forEach var="mesBoleta" items="${mesesBoletas}">
                 <option value="${mesBoleta.key}">${mesBoleta.value}</option>
                 </c:forEach>
             </select>
            </div>
        </div>
    </div>

    <div class="column">
        <div class="ui horizontal section divider">
            <input type="submit" value="Generar PDF" class="ui olive button centered" style="margin-top:10px;"/>
        </div>
    </div>
    </div>
</form>
<div style="height: 50px;"></div>
<div class="ui horizontal section divider">Atención de barberos</div>

    <div class="ui text container">
        <div class="ui form">
            <div class="ui two column grid container">

            </div>
        </div>
        <div class="column">
            <div class="ui horizontal section divider">
                <button type="button" id="btnPdf" class="ui olive button centered" style="margin-top:10px;">Generar PDF</button>
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