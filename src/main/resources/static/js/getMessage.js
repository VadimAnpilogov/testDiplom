function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",
        success: function(result){
            if(result.status == "Done"){
                $('#getResultMessage').empty();

                $.each(result.data, function(i, customer){
                    $('#getResultMessage').append(
                        "<div class='received_msg'>" +
                        "<div class='received_withd_msg'>" +
                        "<p>" + customer.message + "</p>" +
                        "<span class='time_dateInMes'>" + customer.date +
                        "</span></div></div>");
                });

// нужно сделать нормально
                // $.each(result.data, function(i, customer){
                //     $('#getResultMessage').append(
                //         "<div th:if='${"+ customer.sender + " != " + customer.recipient + "}' class='received_msg'>" +
                //         "<div class='received_withd_msg'>" +
                //         "<p>" + customer.message + "</p>" +
                //         "<span class='time_dateInMes'>" + customer.date +
                //         "</span></div></div>"+
                //
                //         "<div th:if='${"+ customer.sender+ " == " + customer.recipient + "}' class='outgoing_msg'>" +
                //         "<div class='sent_msg'>" +
                //         "<p>" + customer.message + "</p>" +
                //         "<span class='time_dateOutMes'>" + customer.date +
                //         "</span></div></div>"
                //
                //     );
                // });

                // var custList = "";
                // $.each(result.data, function(i, customer){
                //     var customer = "Id = " + i + ", Отзыв = " + customer.message +"<br>";
                //     // var customer = "<div class=\"review\">\n" +
                //     //     "            <h4 class=\"reviewName\">customer.authorReviews</h4>\n" +
                //     //     "            <div class=\"reviewText\">\n" +
                //     //     "                <p>customer.reviewsOp</p>\n" +
                //     //     "            </div>\n" +
                //     //     "        </div>";
                //     $('#getResultMessage').append(customer)
                // });
                console.log("Success: ", result);
            }else{
                $("#getResultMessage").html("<strong>Error</strong>");
                console.log("Fail: ", result);
            }
        },
        error : function(e) {
            $("#getResultMessage").html("<strong>Error</strong>");
            console.log("ERROR: ", e);
        }
    });
}
setInterval('ajaxGet()', 1000);
