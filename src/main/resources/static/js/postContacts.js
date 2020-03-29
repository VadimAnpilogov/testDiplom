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
                    SentMessage();
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
    };
    function SentMessage() {
        $('#contactsFormGeneral').empty();
        $('#contactsFormGeneral').append(
            "<div>" +
            "<h4>Спасибо!</h4>" +
            "<h5>Мы свяжемся с вами для ответа.</h5>" +
            "</div>");
    }
});