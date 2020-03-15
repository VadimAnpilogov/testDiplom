// $( document ).ready(function() {

    // GET REQUEST



    // $("#getAllCustomerId").click(function(event){
    //     event.preventDefault();
    //     ajaxGet();
    // });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url : window.location + "/all",
            success: function(result){
                if(result.status == "Done"){
                    $('#getResultDiv').empty();

                    $.each(result.data, function(i, customer){
                        $('#getResultDiv').append("<div class='review'>" +
                            "<h4 class='reviewName'>" + customer.sender + "</h4>" +
                            "<span class='time_date'>" + customer.date + "</span>" +
                            "<div class='reviewText'><p>" + customer.message + "</p></div></div>");
                    });



                    // $("#getResultDiv").html("<div class='review'>\n" +
                    //     "        <h4 class='reviewName'>" + customer.sender + "</h4>\n" +
                    //     "        <span class='time_date'>" + customer.date + "</span>\n" +
                    //     "        <div class='reviewText'>\n" +
                    //     "            <p>" + customer.message + "</p>\n" +
                    //     "        </div>\n" +
                    //     "    </div>");


                    // var custList = "";
                    // $.each(result.data, function(i, customer){
                    //     var customer = "Id = " + i + ", Отзыв = " + customer.message +", Автор = " + customer.sender + ", дата = " + customer.date +"<br>";
                    //     // var customer = "<div class=\"review\">\n" +
                    //     //     "            <h4 class=\"reviewName\">customer.authorReviews</h4>\n" +
                    //     //     "            <div class=\"reviewText\">\n" +
                    //     //     "                <p>customer.reviewsOp</p>\n" +
                    //     //     "            </div>\n" +
                    //     //     "        </div>";
                    //     $('#getResultDiv .list-group').append(customer)
                    // });
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
// })