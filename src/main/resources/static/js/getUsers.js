function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",

        success: function(result){
            if(result.status == "Done"){
                $('#getResultUsers').empty();
                document.getElementById("loading").style.display = "none";
                var Username = new Array();
                var AllData = "";

                $.each(result.data, function(i, customer){
                    Username.push(customer.username);
                });

                AllData += '<div class="usersTiles">' + //начало таблицы
                    '<table id="usersTest">';

                while (Username.length !== 0) {
                    AllData +="<tr>"; //строка из 4 пользователей
                    for (var i = 0; i < 4; i++){
                        AllData +=  //ячейка для пользователя
                            '<th>' +
                            '<div class="usersImg">' +
                            '<a href="/userPers/' + Username[i] + '">' +
                            '<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">' +
                            '</a>' +
                            '</div>' +
                            '<h1>' + Username[i] + '</h1>' +
                            '<h2>Статус</h2>' +
                            '<h2>Звание</h2>' +
                            '</th>';
                    }
                    AllData += '</tr>';
                    for (var j = 0; j < 4; j++) //удаление первых 4 пользователей из массива
                        Username.shift();
                }


                AllData += '</table>' + //конец таблицы
                    '</div>';

                $('#getResultUsers').append(AllData);


                // $.each(result.data, function(i, customer){
                //     $('#getResultUsers').append(
                //         "<div class='usersTiles'>" +
                //             "<table id='usersTest'>" +
                //                 "<tr>" +
                //                     "<th>" +
                //                         "<div class='usersImg'>" +
                //                             "<a href='/userPers/" + customer.username + "'>" +
                //                                 "<img src='https://ptetutorials.com/images/user-profile.png' alt='sunil'>" +
                //                             "</a>" +
                //                         "</div>" +
                //                         "<h1>" + customer.username + "</h1>" +
                //                         "<h2>Статус</h2>" +
                //                         "<h2>Звание</h2>" +
                //                     "</th>" +
                //                 "</tr>" +
                //             "</table>" +
                //         "</div>"
                //     );
                // });


                // $.each(result.data, function(i, customer){
                //     $('#getResultUsers').append("<div class='usersImg'>" +
                //         "<a href='/userPers/" + customer.username +">" +
                //         "<img src='https://ptetutorials.com/images/user-profile.png' alt='sunil'>" +
                //         "</a>" +
                //         "</div>" +
                //         "<h1>" + customer.username + "</h1>" +
                //         "<h2>Статус</h2>" +
                //         "<h2>Звание</h2>");
                // });
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
ajaxGet();