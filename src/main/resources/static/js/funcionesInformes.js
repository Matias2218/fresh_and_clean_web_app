$(window).on('scroll', function() {
    if ($(window).scrollTop()) {
        $('#divblack').addClass('greysi fixed');
    } else {
        $('#divblack').removeClass('greysi fixed');
    }
})
$('select.dropdown')
    .dropdown()
;
$(document).ready(function() {
    $("#btnPdf").click(function(){
        window.location.href = "/intranet/gerente/informes/atencionBarberos?format=pdf";
    });
});