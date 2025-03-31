$(document).ready(function() {
    $(".add-form").submit(function(event) {
        event.preventDefault(); 

        let form = $(this); 
        let link = form.attr("action");  
        let inputField = document.getElementById("url").value; 
		let requestData = { url: inputField };

		$("#create").prop("disabled", true).text("Processing...");

		 
        $.ajax({
            type: "POST",  
            url: link,      
            contentType: "application/json",  
            data: JSON.stringify(requestData),  
            success: function(response) {
				$("#create").prop("disabled", false).text("Додати");
                alert("Товари додані!");
				location.reload();
            },
            error: function(xhr, status, error) {
                alert("Помилка: " + xhr.responseText);
            }
        });
    });
});