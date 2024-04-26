$(document).ready(function() {
    $('#rating-threshold').show();
    $('#period-popular').hide();
    
    $('input[name="articleFilter"]').change(function() {
        if ($(this).val() === 'new') {
            $('#rating-threshold').show();
            $('#period-popular').hide();
        } else if ($(this).val() === 'popular') {
            $('#rating-threshold').hide();
            $('#period-popular').show();
        }
    });
});