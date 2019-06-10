<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="\semantic\out\semantic.min.css">
<link rel="stylesheet" href="\stylesheets\styles.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/funciones.js">
	
</script>

<meta charset="UTF-8" />
<title>Editar Producto</title>

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
						<a class="toc item" href="/intranet/inventario/"><i
							class="reply icon"></i></a>

						<div class="right item">
							<a class="item" href="/intranet/inventario/">Perfil Inventario</a> <a type="button"
								onclick="location.href='/intranet/inventario/crearProducto'"
								class="item">Crear Producto</a> <a class="item" href="#">Servicios</a>
							<a class="item" href="#">Nosotros</a>
							<button type="button" onclick="location.href='/logout'"
								class="ui inverted olive button">Cerrar Sesion</button>
						</div>
					</div>
				</div>
			</div>
			<div class="ui text sc-header-content container">
				<h1 class="ui inverted header" style="font-size: 170%;">Edicion
					de Producto</h1>
				<p class="ui inverted header" style="padding-bottom: 10px;">Aqui
					puede editar el producto seleccionado</p>
				</a>
			</div>
		</div>
	</div>
	<!-- END HEADER -->


	<form:form method="post" action="/intranet/inventario/editarProducto"
		enctype="multipart/form-data" modelAttribute="producto"
		>
		<div class="column">
			<div class="ui horizontal section divider">Editar</div>
		</div>
	<div class="ui bor form two column stackable grid container">
		<div class="column">
			<div class="ui segment"
				style="border: 1px solid transparent; box-shadow: none;">
				<div class="field">
					<form:label path="nombre">Nombre</form:label>
					<form:input path="nombre" />
				</div>

				<div class="field">
					<form:label path="descripcion">Descripcion</form:label>
					<form:textarea path="descripcion" rows="2"></form:textarea>
				</div>

				<div class="two fields">
					<div class="field">
						<label>Categoria</label>
						<form:select path="categoria.id" size="1"
							class="ui fluid dropdown">
							<form:option value="0" label="--Seleccione Categoria--" />
							<form:options items="${categorias}" itemValue="id"
								itemLabel="nombre" />
						</form:select>
					</div>

					<div class="field">
						<label>Marca</label>
						<form:select path="marca.id" size="1"
							class="ui fluid dropdown selection">
							<form:option value="0" label="--Seleccione Marca--" />
							<form:options items="${marcas}" itemValue="id" itemLabel="nombre" />
						</form:select>
					</div>
				</div>


				<div class="field">
					<form:label path="stock">Stock</form:label>
					<form:input path="stock" />
				</div>
			</div>
		</div>


		<div class="column">
			<div class="ui segment"
				style="border: 1px solid transparent; box-shadow: none;">
				<div class="field">
					<label>Foto</label>
					<img id="imagenSalida" name="imagenSalida"
						src="http://localhost:8081/api/uploads/img/${producto.imagen}"
						class="ui medium  bordered rounded image">

					<!--<input type="file" name="file" id="foto" onchange="readURL(this)"
							accept=".png, .jpg, .jpeg" />-->

					<div>
						<label for="foto" class="ui icon button" style="margin-top:10px;"> <i
							class="file icon"></i> Seleccionar Archivo
						</label> <input type="file"  name="file" style="display: none" id="foto"
							onchange="readURL(this)"  accept=".png, .jpg, .jpeg">
					</div>


					<div id="preview"></div>

				</div>
			</div>
		</div>
	</div>
		<div class="column">
			<div class="ui horizontal section divider">
				<input type="submit" class="ui olive button centered" value="Editar" style="margin-top:10px;"/>
			</div>
		</div>
	</form:form>



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