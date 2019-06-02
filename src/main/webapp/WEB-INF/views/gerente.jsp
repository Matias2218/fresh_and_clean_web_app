<%@ page contentType="text/html;charset=UTF-8"%>
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
    </script>
</head>
<body>

<!-- HEADER -->
<div classs="pusher">
    <div
            class="ui vertical sc-main-intranet-perfiles center aligned segment">
        <div class="ui container">
            <div id="divblack" class="following bar ">
                <div class="ui large secondary inverted pointed fixed menu">
                    <a class="toc item"><i class="sidebar icon"></i></a>

                    <div class="right item">
                        <a class="item" href="#">Fresh & Clean</a>
                        <a type="button"
                           onclick="location.href='/intranet/gerente/informes'"
                           class="item">Informes</a>
                        <a class="item" href="#">Servicios</a>
                        <a class="item" href="#">Nosotros</a>
                        <button type="button" onclick="location.href='/logout'"
                                class="ui inverted olive button">Cerrar Sesion</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui text sc-header-content container">
            <h1 class="ui inverted header" style="font-size: 50px;">${persona.nombre}</h1>
            <p class="ui inverted header" style="padding-bottom: 10px;">Bienvenido
                al perfil de Gerente</p>
        </div>
    </div>
</div>
<!-- END HEADER -->


<div style="height: 50px;"></div>
<div class="column">
    <div class="ui horizontal section divider">
        <button type="button"
                onclick="location.href='/intranet/gerente/informes'"
                class="ui olive button centered">Informes</button>
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