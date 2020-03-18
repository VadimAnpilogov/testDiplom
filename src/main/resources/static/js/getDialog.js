function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",
        success: function(result){
            if(result.status == "Done"){
                $('#getResultDialog').empty();

                $.each(result.data, function(i, customer){
                    $('#getResultDialog').append("<div class='message_list'>" +
                        "<div class='chat_people'>" +
                        "<div class='chat_img'>" +
                        "<a href='/userPers/"+ customer.recipient + "'>" +
                        "<img src='https://ptetutorials.com/images/user-profile.png' alt='sunil'></a></div>" +
                        "<div class=\"chat_ib\">" +
                        "<a href='/messageU/" + customer.recipient + "' class='username_msg'>" +
                        "<h5 id='recipient' class='username_msg'>" + customer.recipient + "</h5>"+
                        "</a></div></div></div>");
                });
                console.log("Success: ", result);
            }else{
                $("#getResultDialog").html("<strong>Error</strong>");
                console.log("Fail: ", result);
            }
        },
        error : function(e) {
            $("#getResultDialog").html("<strong>Error</strong>");
            console.log("ERROR: ", e);
        }
    });
}
setInterval('ajaxGet()', 1000);