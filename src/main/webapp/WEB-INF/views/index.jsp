<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="\semantic\out\semantic.min.css">
    <link rel="stylesheet" href="\stylesheets\styles.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>
    <script src="\semantic\out\semantic.min.js"></script>
    <meta charset="UTF-8"/>
    <title>Fresh & Clean</title>
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
</head>

<body>
<!-- HEADER -->
<div class="pusher">
    <div class="ui vertical sc-main center aligned segment">
    	<!-- NAV -->
        <div class="ui container">
            <div id="divblack" class="following bar ">
                <div class="ui large secondary inverted pointed fixed menu">
                    <div class="right item">
                        <a class="item" href="/">Fresh & Clean</a> 
                        <a class="item" href="/pedirHora">Pedir Hora</a> 
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
        
        <!-- IMG TEXT -->
        <div class="ui text sc-header-content container">
        <p class="ui inverted header">Bienvenido a</p>
            <h1 class="ui inverted huge header titulo-index">FRESH & CLEAN</h1>
            <a class="ui huge primary right labeled icon button sc-button" href="/pedirHora" style="background-color: rgb(230, 184, 0)">Pedir Hora <i class="calendar alternate outline icon"></i>
            </a>
        </div>
    </div>
</div>
<!-- END HEADER -->

<div style="height: 50px;"></div>

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