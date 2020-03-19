function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",

        success: function(result){
            if(result.status == "Done"){
                $('#getResultUsers').empty();

                $.each(result.data, function(i, customer){
                    $('#getResultUsers').append("<div class='usersImg'>" +
                        "<a href='/userPers/" + customer.username +">" +
                        "<img src='https://ptetutorials.com/images/user-profile.png' alt='sunil'>" +
                        "</a>" +
                        "</div>" +
                        "<h1>" + customer.username + "</h1>" +
                        "<h2>Статус</h2>" +
                        "<h2>Звание</h2>");
                });
                console.log("Success: ", result);
            }else{
                $("#getResultUsers").html("<strong>Error</strong>");
                console.log("Fail: ", result);
            }
        },
        error : function(e) {
            $("#getResultUsers").html("<strong>Error</strong>");
            console.log("ERROR: ", e);
        }
    });
}
setInterval('ajaxGet()', 2000);