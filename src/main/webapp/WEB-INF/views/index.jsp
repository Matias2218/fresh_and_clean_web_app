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
<title>Fresh & Clean</title>
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
	<div classs="pusher">
		<div class="ui vertical sc-main center aligned segment">
			<div class="ui container">
				<div id="divblack" class="following bar ">
					<div class="ui large secondary inverted pointed fixed menu">
						<a class="toc item"><i class="sidebar icon"></i></a>

						<div class="right item">
							<a class="item" href="#">Fresh & Clean</a> <a class="item"
								href="#">Pedir Hora</a> <a class="item" href="#">Servicios</a> <a
								class="item" href="#">Peluqueros</a> <a class="item" href="#">Nosotros</a>
						</div>
					</div>
				</div>
			</div>
			<div class="ui text sc-header-content container">
				<h1 class="ui inverted header" style="font-size: 50px;">FRESH &
					CLEAN</h1>
				<p class="ui inverted header" style="padding-bottom: 10px;">dasdja
					hfc adfhask fcas fasf a fldhsa hfhd fdas fa fas fsa</p>
				<a class="ui huge primary right labeled icon button sc-button"
					style="background-color: rgb(230, 184, 0)">Pedir Hora <i
					class="calendar alternate outline icon"></i>
				</a>
			</div>
		</div>
	</div>
	<!-- END HEADER -->

	<div style="height: 500px;"></div>


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
							class="item">Tel�fono: 225050050</a> <a href="#" class="item">freshandclean@gmail.cl</a>
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
			<img src="../img/logo-blanco.png" class="ui centered image"
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