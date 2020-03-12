function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",
        success: function(result){
            if(result.status == "Done"){
                $('#getResultDiv ul').empty();
                var custList = "";
                $.each(result.data, function(i, customer){
                    var customer = "Id = " + i + ", Отзыв = " + customer.message +"<br>";
                    // var customer = "<div class=\"review\">\n" +
                    //     "            <h4 class=\"reviewName\">customer.authorReviews</h4>\n" +
                    //     "            <div class=\"reviewText\">\n" +
                    //     "                <p>customer.reviewsOp</p>\n" +
                    //     "            </div>\n" +
                    //     "        </div>";
                    $('#getResultDiv .list-group').append(customer)
                });
                console.log("Success: ", result);
            }else{
                $("#getResultDiv").html("<strong>Error</strong>");
                console.log("Fail: ", result);
            }
        },
        error : function(e) {
            $("#getResultDiv").html("<strong>Error</strong>");
            console.log("ERROR: ", e);
        }
    });
}
setInterval('ajaxGet()', 1000);
