function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",
        success: function(result){
            if(result.status == "Done"){
                $('#getResultHelp').empty();

                $.each(result.data, function(i, customer){
                    $('#getResultHelp').append("<div class='review'>" +
                        "<h4 class='reviewName'>" + customer.username + "</h4>" +
                        "<span class='time_date'>" + customer.date + "</span>" +
                        "<div class='reviewText'><p>" + customer.message + "</p></div></div>");
                });
                console.log("Success: ", result);
            }else{
                $("#getResultHelp").html("<strong>Error</strong>");
                console.log("Fail: ", result);
            }
        },
        error : function(e) {
            $("#getResultHelp").html("<strong>Error</strong>");
            console.log("ERROR: ", e);
        }
    });
}
setInterval('ajaxGet()', 1000);