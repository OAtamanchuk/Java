$(document).ready(function() {
    $(".delete-form").submit(function(event) {
        event.preventDefault(); 
        
        let form = $(this);
        let url = form.attr("action");
        
        $.ajax({
            type: "POST",
            url: url,
            data: form.serialize(),
            success: function(response) {
                location.reload(); 
            },
            error: function(xhr) {
                alert("Error" + xhr.responseText);
            }
        });
    });
});