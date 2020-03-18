function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",
        success: function(result){
            if (result.status !== "Done") {
                $("#getResultMessage").html("<strong>Error!</strong>");
                console.log("Fail: ", result);
            } else {
                var Enter = document.getElementById("enterID");
                Enter.style.visibility = "visible";

                $('#getResultDialog').empty();
                $.each(result.data, function (i, customer1) {
                    $('#getResultDialog').append("<div class='message_list'>" +
                        "<div class='chat_people'>" +
                        "<div class='chat_img'>" +
                        "<a href='/userPers/{" + customer1.recipient + "}'>" +
                        "<img src='https://ptetutorials.com/images/user-profile.png' alt='sunil'></a></div>" +
                        "<div class=\"chat_ib\">" +
                        "<a href='/messageU/" + customer1.recipient + "' class='username_msg'>" +
                        "<h5 id='recipient' class='username_msg'>" + customer1.recipient + "</h5>" +
                        "</a></div></div></div>");
                });
                $('#getResultMessage').empty();
                var Username = document.getElementById("username").textContent;
                $.each(result.data1, function (i, customer) {
                    if (customer.sender !== Username) {
                        $('#getResultMessage').append(
                            "<div class='received_msg'>" +
                            "<div class='received_withd_msg'>" +
                            "<p>" + customer.message + "</p>" +
                            "<span class='time_dateInMes'>" + customer.date +
                            "</span></div></div>");
                    }
                    if (customer.sender === Username) {
                        $('#getResultMessage').append(
                            "<div class='outgoing_msg'>" +
                            "<div class='sent_msg'>" +
                            "<p>" + customer.message + "</p>" +
                            "<span class='time_dateOutMes'>" + customer.date +
                            "</span></div></div>");
                    }
                });

                console.log("Success: ", result);
            }
        },
        error : function(e) {
            $("#getResultMessage").html("<div class='getEnterMessage2'>" +
                "<div class='getEnterMessage'>" +
                "<img src='https://img.icons8.com/color/96/000000/chat.png'>" +
                "<a>Выберите диалог</a></div></div>");
            console.log("ERROR: ", e);
        }
    });
}
setInterval('ajaxGet()', 1000);
