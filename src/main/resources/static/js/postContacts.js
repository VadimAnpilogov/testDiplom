$( document ).ready(function() {

    // SUBMIT FORM
    $("#contactsForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });

    // ", lastName = " + customer.authorReviews +
    function ajaxPost(){

        // PREPARE FORM DATA
        var formData = {
            message : $("#message").val()
        };

        // DO POST
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : window.location + "/messageDev",
            data : JSON.stringify(formData),
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
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });

        // Reset FormData after Posting
        resetData();

    }

    function resetData(){
        $("#message").val("");
    }

    document.body.onkeydown = function(event) {
        var e = event || window.event;
        var code = e.keyCode || e.which;
        var activeEl = document.activeElement.id;
        if(code === 13 && activeEl === "contactsText") {
            var text_redirect = document.getElementById('contactsText').value;
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
            if(activeEl === "contactsText") {
                document.getElementById('contactsText').value += "\n";
            } else {
                return false;
            }
        } else if(code === 13 && activeEl === "contactsText") {
            var text_redirect = document.getElementById('contactsText').value;
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