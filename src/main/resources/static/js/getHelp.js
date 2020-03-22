var endHelp = 0;
function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",
        success: function(result){
            if(result.status === "Done"){
                $('#getResultHelp').empty();

                $.each(result.data, function(i, customer){
                    $('#getResultHelp').append("<div class='review'>" +
                        "<h4 class='reviewName'>" + customer.sender + "</h4>" +
                        "<span class='time_date'>" + customer.date + "</span>" +
                        "<div class='reviewText'><p>" + customer.message + "</p></div></div>");
                });

                if(endHelp === 0)
                {
                    var div = $("#getResultHelp");
                    div.scrollTop(div.prop('scrollHeight'));
                    endHelp = 1;
                }

                document.getElementById("reviewEnter").style.display = "block";
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