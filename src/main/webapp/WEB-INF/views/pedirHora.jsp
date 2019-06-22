<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="\stylesheets\styles.css">
    <link rel="stylesheet" href="\semantic\out\semantic.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="\semantic\out\semantic.min.js"></script>
    <meta charset="UTF-8"/>
    <script type="text/javascript">
        $(window).on('scroll', function () {
            if ($(window).scrollTop()) {
                $('#divblack').addClass('greysi fixed');
            } else {
                $('#divblack').removeClass('greysi fixed');
            }
        })

        $('.special.cards .image').dimmer({
            on: 'hover'
        });
        $(document).ready(function()
        {
            $('#divDatosBarbero :input').attr('disabled', true);
            $('#divDatosHora :input').attr('disabled', true);
            function today() {
                var today = new Date();
                var dd = today.getDate();
                var mm = today.getMonth()+1; //January is 0!
                var yyyy = today.getFullYear();
                if(dd<10){dd='0'+dd}
                if(mm<10){mm='0'+mm}
                today = yyyy+'-'+mm+'-'+dd;
                return today;
            }
            function DiasMaximo()
            {
                var today = new Date();
                var dd = today.getDate();
                var mm = today.getMonth()+2; //January is 0!
                var yyyy = today.getFullYear();
                if(dd<10){dd='0'+dd}
                if(mm<10){mm='0'+mm}

                today = yyyy+'-'+mm+'-'+dd;
                return today;
            }

            $('#btnGenerarHora').click(function () {
                $('#divDatosBarbero :input').attr('disabled', false);
                $('#divDatosHora :input').attr('disabled', false);
                $('#divDatosCliente :input').attr('disabled', false);
            });

            $('#dateHoraAtencion').attr('min',today());
            $('#dateHoraAtencion').attr('max',DiasMaximo());

            $('#btnDatosCliente').click(function () {

                $('#divDatosCliente :input').attr('disabled', true);
                $('#divDatosBarbero :input').attr('disabled', false);
            });
            $('#btnIrIzquierda').click(function () {

                $('#divDatosCliente :input').attr('disabled', false);
                $('#divDatosBarbero :input').attr('disabled', true);
            });
            $('#btnIrDerecha').click(function () {

                $('#divDatosCliente :input').attr('disabled', true);
                $('#divDatosBarbero :input').attr('disabled', true);
                $('#divDatosHora :input').attr('disabled',false);
            });
            $('#btnVolver').click(function () {

                $('#divDatosCliente :input').attr('disabled', true);
                $('#divDatosBarbero :input').attr('disabled', false);
                $('#divDatosHora :input').attr('disabled',true);
            });
            $("#dateHoraAtencion").change(function(){
                var empleadoId = $('#cmbEmpleado').val();
                var fecha = $('#dateHoraAtencion').val();
                $('#divCheckBox :input').attr('disabled',false);
                $.ajax({
                    type : 'POST',
                    contentType : 'application/json; charset=utf-8',
                    dataType : 'json',
                    url : "/horasDisponibles",
                    data : JSON.stringify({
                        idEmpleado:empleadoId,
                        fecha:fecha
                    }),
                    success : function (response) {
                        var i;
                        var array = response.datos
                        for (i=0; i < array.length; i++) {
                                var chk = $('#divCheckBox :input').filter(function(){return this.value==array[i]});
                                chk.attr('disabled',true);
                        }
                    },
                    error : function (e) {
                    }
                });
            });
        });
    </script>
    <title>Solicitar hora</title>
</head>
<body>
<!-- HEADER -->
<div class="pusher">
    <div class="ui vertical sc-main-paginas center aligned segment">
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
    </div>
</div>
<!-- END HEADER -->

<h3 class="ui center aligned header">Agende su hora</h3>
<form:form class="ui three column doubling grid container" method="Post" action="/generarHora" modelAttribute="cliente">
    <!-- DATOS PERSONALES -->
    <div class="column" id="divDatosCliente">
        <div class="ui segment">
            <div class="ui form">
                <h4 class="ui dividing header">Información Personal</h4>
                <div class="field">
                    <form:label path="persona.nombre">Nombre</form:label>
                    <div class="two fields">
                        <div class="field">
                            <form:input path="persona.nombre" placeholder="Nombre"></form:input>
                        </div>
                        <div class="field">
                            <form:input path="persona.apellido" placeholder="Apellido"></form:input>
                        </div>
                    </div>
                </div>
                <div class="field">
                    <label>Correo Electronico</label>
                    <div class="field">
                        <form:input path="emailCliente" type="email" placeholder="ej: micorreo@gmail.com" ></form:input>
                    </div>
                </div>
                <div class="two fields">
                    <div class="field">
                        <form:label path="persona.fechaNacimiento">Fecha de Nacimiento</form:label>
                        <form:input type="date" path="persona.fechaNacimiento"></form:input>
                    </div>
                    <div class="field">
                        <form:label path="persona.genero">Seleccione género</form:label>
                        <form:select path="persona.genero">
                            <form:option value="N">Seleccione su sexo</form:option>
                            <form:options items="${generos}"></form:options>
                        </form:select>
                    </div>
                </div>
                <div class="field">
                    <form:label path="telefonoCliente">Número de contacto</form:label>
                    <div class="field">
                        <form:input path="telefonoCliente" placeholder="Teléfono de casa o celular"></form:input>
                    </div>
                </div>
                <div class="ui center aligned basic segment">
                    <button type="button" id="btnDatosCliente" class="icon right attached ui button"><i class="caret right icon"></i></button>
                </div>
            </div>
        </div>
    </div>

    <!-- SERVICIO Y BARBERO -->
    <div class="column" id="divDatosBarbero" >
        <div class="ui segment">
            <div class="ui form">
                <h4 class="ui dividing header">Barbero y Servicio</h4>
                <div class="ui center aligned basic segment">
                    <div class="ui input">
                        <select name="cmbEmpleado" id="cmbEmpleado">
                            <option value="NONE">Seleccione Barbero</option>
                            <c:forEach items="${barberos}" var="barbero">
                                <option value="${barbero.key}">${barbero.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="column">
                    <div class="ui segment">
							<table class="ui center aligned very basic table">
								<tbody>
									<c:forEach items="${servicios}" var="servicio">
									<tr>
									<td class="ui right aligned"><input type="checkbox" name="servicios[]" value="${servicio.key}"></td>
									<td>$ ${servicio.value[1]}</td>
									<td>${servicio.value[0]}</td>
                            		</tr>
                        			</c:forEach>
								</tbody>
							</table>

							
                    </div>
                </div>
                <div class="ui center aligned basic segment">
                    <button type="button" id="btnIrIzquierda" class="ui icon left attached button"><i class="caret left icon"></i></button>
                    <button type="button" id="btnIrDerecha" class="icon right attached ui button"><i class="caret right icon"></i></button>
                </div>
            </div>
        </div>
    </div>

    <!-- SELECCIONAR HORA -->
    <div class="column" id="divDatosHora">
        <div class="ui segment">
            <div class="ui form">
                <h4 class="ui dividing header">Seleccionar Hora</h4>
                <div class="ui center aligned basic segment">
                    <div class="ui input">
                        <input type="date" name="dateHoraAtencion" id="dateHoraAtencion" />
                    </div>
                </div>
                <div class="column" id="divCheckBox">
                    <div class="ui center aligned segment">
                        <div class="field">
                            <div class="two fields">
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]"  value="10:00">
                                        <label>10:00am</label>
                                    </div>
                                </div>
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="15:30">
                                        <label>15:00pm</label>
                                    </div>
                                </div>
                            </div>
                            <div class="two fields">
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="10:30">
                                        <label>10:30am</label>
                                    </div>
                                </div>
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="15:30">
                                        <label>15:30pm</label>
                                    </div>
                                </div>
                            </div>
                            <div class="two fields">
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="11:00">
                                        <label>11:00am</label>
                                    </div>
                                </div>
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="16:00">
                                        <label>16:00pm</label>
                                    </div>
                                </div>
                            </div>
                            <div class="two fields">
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="11:30">
                                        <label>11:30am</label>
                                    </div>
                                </div>
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="16:30">
                                        <label>16:30pm</label>
                                    </div>
                                </div>
                            </div>
                            <div class="two fields">
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="12:00">
                                        <label>12:00pm</label>
                                    </div>
                                </div>
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="17:00">
                                        <label>17:00pm</label>
                                    </div>
                                </div>
                            </div>
                            <div class="two fields">
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="12:30">
                                        <label>12:30pm</label>
                                    </div>
                                </div>
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="17:30">
                                        <label>17:30pm</label>
                                    </div>
                                </div>
                            </div>
                            <div class="two fields">
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="14:00">
                                        <label>14:00pm</label>
                                    </div>
                                </div>
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="18:00">
                                        <label>18:00pm</label>
                                    </div>
                                </div>
                            </div>
                            <div class="two fields">
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="14:30">
                                        <label>14:30pm</label>
                                    </div>
                                </div>
                                <div class="field">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="rdbHora[]" value="18:30">
                                        <label>18:30pm</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ui center aligned basic segment">
                    <button  type="button" id="btnVolver" class="ui icon left attached button"><i class="caret left icon"></i></button>
                    <button id="btnGenerarHora" class="right attached ui olive button">Ver detalle</button>
                </div>
            </div>
        </div>
    </div>

</form:form>

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