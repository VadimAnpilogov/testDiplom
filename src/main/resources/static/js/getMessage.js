function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",
        success: function(result){
            if (result.status === "Done" && result.data1 !== null) {
                var Enter = document.getElementById("enterID");
                Enter.style.visibility = "visible";

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
            } else {
                $("#getResultMessage").html("<div class='getEnterMessage2'>" +
                    "<div class='getEnterMessage'>" +
                    "<img src='https://img.icons8.com/color/96/000000/chat.png'>" +
                    "<a>Выберите диалог</a></div></div>");
                console.log("Fail: ", result);
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
