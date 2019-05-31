function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            $('#imagenSalida').attr('src', this.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
$(document).ready(function() {
    $("#stock").attr({
        "type" : "number",
    });
});
