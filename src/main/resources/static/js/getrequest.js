var endReviews = 0;
function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",
        success: function(result){
            if(result.status === "Done"){
                $('#getResultReviews').empty();

                $.each(result.data, function(i, customer){
                    $('#getResultReviews').append("<div class='review'>" +
                        "<h4 class='reviewName'>" + customer.authorReviews + "</h4>" +
                        "<span class='time_date'>" + customer.date + "</span>" +
                        "<div class='reviewText'><p>" + customer.reviewsOp + "</p></div></div>");
                });

                if(endReviews === 0)
                {
                    var div = $("#getResultReviews");
                    div.scrollTop(div.prop('scrollHeight'));
                    endReviews = 1;
                }

                document.getElementById("reviewEnter").style.display = "block";
                console.log("Success: ", result);
            }else{
                $("#getResultReviews").html("<strong>Error</strong>");
                console.log("Fail: ", result);
            }
        },
        error : function(e) {
            $("#getResultReviews").html("<strong>Error</strong>");
            console.log("ERROR: ", e);
        }
    });
}
setInterval('ajaxGet()', 1000);