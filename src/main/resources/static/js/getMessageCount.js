var endReviews = 0;
function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/count",
        success: function(result){
            if(result.status === "DoneYes"){
                $('#getResultCount').empty();


                $('#getResultCount').append("<p style='color: #e5e7ed'>" + result.data + "</p>");


                if(endReviews === 0)
                {
                    var div = $("#getResultCount");
                    div.scrollTop(div.prop('scrollHeight'));
                    endReviews = 1;
                }

                console.log("Success: ", result);
            }else{
                $("#getResultCount").html("<strong>Error</strong>");
                console.log("Fail: ", result);
            }
        },
        error : function(e) {
            $("#getResultCount").html("<strong>Error</strong>");
            console.log("ERROR: ", e);
        }
    });
}
setInterval('ajaxGet()', 1000);