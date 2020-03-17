function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",
        success: function(result){
            if(result.status == "Done"){
                $('#getResultDialog').empty();
                $.each(result.data, function(i, customer1){
                    $('#getResultDialog').append("<div class='message_list'>" +
                        "<div class='chat_people'>" +
                        "<div class='chat_img'>" +
                        "<a href='/userPers/{"+ customer1.recipient + "}'>" +
                        "<img src='https://ptetutorials.com/images/user-profile.png' alt='sunil'></a></div>" +
                        "<div class=\"chat_ib\">" +
                        "<a href='/messageU/" + customer1.recipient + "' class='username_msg'>" +
                        "<h5 id='recipient' class='username_msg'>" + customer1.recipient + "</h5>"+
                        "</a></div></div></div>");
                    $('#getResultMessage').empty();
                    $.each(result.data1, function(i, customer){
                        $('#getResultMessage').append(
                            "<div class='received_msg'>" +
                            "<div class='received_withd_msg'>" +
                            "<p>" + customer.message + "</p>" +
                            "<span class='time_dateInMes'>" + customer.date +
                            "</span></div></div>");
                    });
                });
// нужно сделать нормально
//                 $.each(result.data, function(i, customer){
//                     $('#getResultMessage').append(
//                         "<div th:if='${"+ customer.sender + " != " + customer.recipient + "}' class='received_msg'>" +
//                         "<div class='received_withd_msg'>" +
//                         "<p>" + customer.message + "</p>" +
//                         "<span class='time_dateInMes'>" + customer.date +
//                         "</span></div></div>"+
//
//                         "<div th:if='${"+ customer.sender+ " == " + customer.recipient + "}' class='outgoing_msg'>" +
//                         "<div class='sent_msg'>" +
//                         "<p>" + customer.message + "</p>" +
//                         "<span class='time_dateOutMes'>" + customer.date +
//                         "</span></div></div>"
//
//                     );
//                 });

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
