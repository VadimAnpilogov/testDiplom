function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",

        success: function(result){
            if(result.status === "Done"){
                $('#getResultUsers').empty();
                document.getElementById("loading").style.display = "none";
                var Username = [];
                var Fio = [];
                var Status = [];
                var AllData = "";

                $.each(result.data, function(i, customer){
                    Username.push(customer.username);
                    Fio.push(customer.user.fio);
                    Status.push(customer.user.rl);

                });

                for(var a = 0; a < Status.length; a++)
                {
                    if(Status[a] === 0)
                        Status[a] = "Преподаватель";
                    else
                        Status[a] = "Студент";
                }

                for (var q = 0; q < Username.length; q++)
                {
                    if(Username[q] === document.getElementById("username").textContent)
                    {
                        Username.splice(q, 1);
                        Fio.splice(q, 1);
                        Status.splice(q, 1);
                    }
                }

                AllData += '<div class="usersTiles" id="usersTilesID">' + //начало таблицы
                    '<table id="usersTest">';

                while (Username.length !== 0) {
                    AllData +="<tr>"; //строка из 4 пользователей
                    for (var i = 0; i < 4; i++){
                        if(Username[i] !== undefined){
                            AllData +=  //ячейка для пользователя
                                '<th>' +
                                '<div class="usersImg">' +
                                '<a href="/userPers/' + Username[i] + '">' +
                                '<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">' +
                                '</a>' +
                                '</div>' +
                                '<h1>' + Username[i] + '</h1>' +
                                '<h2>' + Fio[i] + '</h2>' +
                                '<h2>' + Status[i] + '</h2>' +
                                '</th>';
                        }
                    }
                    AllData += '</tr>';
                    for (var j = 0; j < 4; j++) //удаление первых 4 пользователей из массива
                    {
                        Username.shift();
                        Fio.shift();
                        Status.shift();
                    }
                }


                AllData += '</table>' + //конец таблицы
                    '</div>';

                $('#getResultUsers').append(AllData);

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