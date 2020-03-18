$( document ).ready(function() {

    // SUBMIT FORM
    $("#customerForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });

    // ", lastName = " + customer.authorReviews +
     function ajaxPost(){

        // PREPARE FORM DATA
        var formData = {
            message : $("#message").val(),
            recipient: $("#recipient").val(),
            sender :  $("#sender").val(),
            date: $("#date").val()
        };

        // DO POST
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : window.location + "/save",
            data : JSON.stringify(formData),
            data1 : JSON.stringify(formData),
            dataType : 'json',
            success : function(result) {
                if(result.status === "Done"){
                    $("#postResultDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" +
                        "Post Successfully! <br>" +
                        "---> Customer's Info: FirstName = " +
                        result.data.message + "</p>");
                }else{
                    $("#postResultDiv").html("<strong>Error</strong>");
                }
                console.log(result);
            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });

        // Reset FormData after Posting
        resetData();

    }

    function resetData(){
        $("#message").val("");
        $("#sender").val("");
        $("#date").val("");
        $("#recipient").val("");
    }

    document.body.onkeydown = function(event) {
        var e = event || window.event;
        var code = e.keyCode || e.which;
        var activeEl = document.activeElement.id;
        if(code === 13 && activeEl === "message") {
            var text_redirect = document.getElementById('message').value;
            text_redirect = text_redirect.replace(/^\s+/, "");
            if(text_redirect !== "") {
                event.preventDefault();
                ajaxPost();
            } else {
                alert("ЗАПОЛНИТЕ ФОРМУ!");
            }
        }
    };
    document.body.onkeydown = function(event) {
        var e = event || window.event;
        var code = e.keyCode || e.which;
        var activeEl = document.activeElement.id;
        if(code === 13 && e.ctrlKey) {
            if(activeEl === "message") {
                document.getElementById('message').value += "\n";
            } else {
                return false;
            }
        } else if(code === 13 && activeEl === "message") {
            var text_redirect = document.getElementById('message').value;
            text_redirect = text_redirect.replace(/^\s+/, "");
            if(text_redirect !== "") {
                event.preventDefault();
                ajaxPost();
            } else {
                return false;
            }
        }
    }
});