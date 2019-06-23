<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="\stylesheets\styles.css">
    <link rel="stylesheet" href="\semantic\out\semantic.min.css">
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/js/funcionesEditarEmpleado.js">
    </script>
    <meta charset="UTF-8"/>
    <title>Editar Empleado</title>
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
	<div class="pusher card">
		<div
			class="ui vertical sc-main-intranet-perfiles2 center aligned segment">
			<div class="ui container">
				<div id="divblack" class="following bar">
					<div class="ui large secondary inverted pointed fixed menu">
						<a class="item sin-hover" href="/intranet/administrador"><img src="/img/logo-blanco.png" class="ui tiny image"> </a>
						<div class="right item">
							<a class="item" href="/intranet/administrador">Perfil</a> 
							<a href="/intranet/administrador/crearEmpleado" class="item">Crear perfil</a>
							<button type="button" onclick="location.href='/logout'" class="ui inverted olive button">Cerrar Sesion</button>
						</div>
					</div>
				</div>
			</div>


				<div class="bottomleft">
					<p class="ui grey inverted left aligned header perfiles">Perfil Administrador</p>
				</div>
		</div>
	</div>
	<!-- END HEADER -->

<div class="column margen-arriba margen-abajo">
		<h2 class="ui center aligned header"> Editar empleado <div class="sub header">Aquí podrá editar campos del empleado anteriormente seleccionado<br>Para guardar los cambios presione el botón “Editar”</div></h2>
	</div>
<div class="margenes-tabla">
<form:form method="post" action="/intranet/administrador/editarEmpleado" class="ui form" modelAttribute="empleado">
    	
    	<div class="ui text container">
        <form:label path="persona.nombre">Nombre</form:label>
        <div class="two fields">
            <div class="field">
                <form:input path="persona.nombre" placeholder="Nombre"/>
            </div>
            <div class="field">
                <form:input path="persona.apellido" placeholder="Apellido"/>
            </div>
        </div>

        <div class="two fields">
            <div class="field">
                <form:label path="persona.genero">Género</form:label>
                <form:select path="persona.genero">
                    <form:option value="N">Seleccione su sexo</form:option>
                    <form:options items="${generos}"></form:options>
                </form:select>
            </div>
            <div class="field">
                <form:label path="persona.fechaNacimiento">Fecha de Nacimiento</form:label>
                <form:input type="date" path="persona.fechaNacimiento"></form:input>
            </div>
        </div>
        <div class="field">
            <form:label path="usernameEmpleado">Nombre de Usuario</form:label>
            <form:input path="usernameEmpleado"/>
        </div>
        <div class="two fields">
            <div class="field">
                <form:label path="passwordEmpleado">Contraseña</form:label>
                <form:password path="passwordEmpleado" placeholder="***********"/>
            </div>
            <div class="field">
                <form:label path="passwordConfirmEmpleado">Confirmar Constraseña</form:label>
                <form:password path="passwordConfirmEmpleado" placeholder="***********"/>
            </div>
        </div>
        <div class="field">
            <form:label path="emailEmpleado">Email</form:label>
            <form:input path="emailEmpleado"/>
        </div>
        <div class="two fields">
            <div class="field">
                <form:label path="telefonoEmpleado">Telefono</form:label>
                <form:input path="telefonoEmpleado"/>
            </div>

            <div class="field">
                <form:label path="sueldoEmpleado">Sueldo</form:label>
                <form:input path="sueldoEmpleado"/>
            </div>
        </div>
        <div>
            <label>Tipo de empleado</label>
            <form:select path="tipoEmpleado.idTipo" size="1"
                         class="ui fluid dropdown">
                <form:option value="0" label="--Seleccione Categoria--" />
                <form:options items="${tipoEmpleados}" itemValue="idTipo"
                              itemLabel="descripcion" />
            </form:select>
        </div>
    	 <div class="field">
        <div class="ui horizontal section divider">
            <input type="submit" class="ui olive button centered" value="Editar" style="margin-top:10px;"/>
        </div>
    </div>
    </div>
   
</form:form>
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