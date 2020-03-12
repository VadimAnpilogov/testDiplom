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
            reviewsOp : $("#reviewsOp").val(),
            authorReviews :  $("#authorReviews").val()
        }

        // DO POST
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : window.location + "/api/customer/save",
            data : JSON.stringify(formData),
            dataType : 'json',
            success : function(result) {
                if(result.status == "Done"){
                    $("#postResultDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" +
                        "Post Successfully! <br>" +
                        "---> Customer's Info: FirstName = " +
                        result.data.reviewsOp + "</p>");
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
        $("#reviewsOp").val("");
        $("#authorReviews").val("");
    }
})