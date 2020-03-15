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