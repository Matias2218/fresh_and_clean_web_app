<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="\semantic\out\semantic.min.css">
    <link rel="stylesheet" href="\stylesheets\styles.css">
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/js/funciones.js"></script>
    <script src="\semantic\out\semantic.min.js"></script>

    <script type="text/javascript">
        $(window).on('scroll', function() {
            if ($(window).scrollTop()) {
                $('#divblack').addClass('greysi fixed');
            } else {
                $('#divblack').removeClass('greysi fixed');
            }
        })
         $(document).ready(function() {

		$('#formCrear')
		    .form({
                fields: {
                    nombre: {
                        identifier: 'nombre',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Por favor ingrese el nombre'
                            }
                        ]
                    },
                    descripcion: {
                        identifier: 'descripcion',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Por favor ingrese una descripción'
                            }
                        ]
                    },
                    'categoria.id': {
                        identifier: 'categoria.id',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Seleccione categoria'
                            }
                        ]
                    },
                    'marca.id': {
                        identifier: 'marca.id',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Seleccione marca'
                            }
                        ]
                    },
                    file: {
                        identifier: 'file',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Seleccione una foto'
                            }
                        ]
                    },
                    stock: {
                        identifier: 'stock',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Ingrese stock'
                            },
                            {
                                type: 'not[0]',
                                prompt: 'Debe ser mayor a 0'
                            },
                            {
                                type: 'number',
                                prompt: 'Solo se permiten valores numericos'
                            }
                        ]
                    }
                },

                inline: true,
                on: 'blur',

                onSuccess: function () {
                    $("#btnCrear").prop("disabled", true).addClass("disabled");
                    return true;
                },
                onFailure: function () {
                    return false;
                }
            });
    });
        
       
    </script>
    <meta charset="UTF-8"/>
    <title>Crear nuevo producto</title>
    <link rel="shortcut icon" type="image/png" href="/img/fyclogo.png"/>
</head>
<body>
<!-- HEADER -->
<div class="pusher card">
	<div class="ui vertical sc-main-intranet-perfiles2 center aligned segment">
		<div class="ui container">
			<div id="divblack" class="following bar">
				<div class="ui large secondary inverted pointed fixed menu">
					<a class="item sin-hover" href="/intranet/inventario"><img src="/img/logo-blanco.png" class="ui tiny image"> </a>
					<div class="right item">
						<a class="item" href="/intranet/inventario">Perfil</a> 
						<a class="item" href="/intranet/inventario">Ver Productos</a> 
						<a href="/intranet/inventario/crearProducto" class="item">Crear Producto</a>
						<button type="button" onclick="location.href='/logout'" class="ui inverted olive button">Cerrar Sesion</button>
					</div>
				</div>
			</div>
		</div>
		<div class="bottomleft">
					<p class="ui grey inverted left aligned header perfiles">Perfil inventario</p>
				</div>
	</div>
</div>
<!-- END HEADER -->

<form:form method="post" id="formCrear" action="/intranet/inventario/crearProducto" enctype="multipart/form-data" modelAttribute="producto">

    <div class="column margen-arriba margen-abajo">
		<h2 class="ui center aligned header">Crear producto<div class="sub header">Aquí podrá crear un nuevo producto dentro del inventario<br>Para guardar los cambios presione el botón “Crear”</div></h2>
	</div>
	
	<div class="ui bor form two column stackable grid text container">
	    <div class="column">
	        <div class="ui segment sin-margenes"
	             style="border: 1px solid transparent; box-shadow: none;">
	            <div class="field">
	                <form:label path="nombre">Nombre</form:label>
	                <form:input path="nombre"/>
	            </div>
	
	            <div class="field">
	                <form:label path="descripcion">Descripción</form:label>
	                <form:textarea path="descripcion"></form:textarea>
	            </div>
	
	            <div class="two fields">
	                <div class="field">
	                    <label>Categoría</label>
	                    <form:select  path="categoria.id" size="1">
	                        <form:option  value=""  label="--Seleccione Categoría--" />
	                        <form:options items="${categorias}" itemValue="id" itemLabel="nombre"/>
	                    </form:select>
	                </div>
	
	                <div class="field">
	                    <label>Marca</label>
	                    <form:select path="marca.id" size="1">
	                        <form:option  value=""  label="--Seleccione Marca--" />
	                        <form:options items="${marcas}" itemValue="id" itemLabel="nombre"/>
	                    </form:select>
	                </div>
	            </div>
	
	            <div class="field">
	                <form:label path="stock">Stock</form:label>
	                    <form:input path="stock"/>
	            </div>
	        </div>
	    </div>

    <div class="column">
        <div class="ui segment sin-margenes"
             style="border: 1px solid transparent; box-shadow: none;">
            <div class="field">
                <label>Foto</label>
                <img id="imagenSalida" name="imagenSalida" src=""  class="ui medium bordered rounded image imagen-espacio">
                <div>
                    <label for="foto" class="ui icon button" style="margin-top:10px;"> <i
                            class="file icon"></i> Seleccionar Archivo
                    </label>
                    <input type="file" name="file" id="foto" onchange="readURL(this)" style="display: none" accept=".png, .jpg, .jpeg"/>
                </div>
                <div id="preview"></div>

            </div>
        </div>
    </div>
</div>
    <div class="column">
        <div class="ui horizontal section divider">
            <input type="submit" id="btnCrear" class="ui olive button centered" value="Crear" style="margin-top:10px;"/>
        </div>
    </div>
</form:form>

<div style="height: 25px;"></div>
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
            <a class="item" href="#">Contáctanos</a>
            <a class="item" href="#">Nosotros</a> 
            <a class="item" href="#">Privacy Policy</a>
        </div>
    </div>
</footer>
<!-- END FOOTER -->
</body>
</html>