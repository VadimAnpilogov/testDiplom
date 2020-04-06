function ajaxGet(){
    $.ajax({
        type : "GET",
        url : window.location + "/all",

        success: function(result){
            if(result.status === "Done"){

                if(result.data1 !== 0)
                {
                    $('#getResultCount').append(
                        '<svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" ' +
                        'width="64" height="64" ' +
                        'viewBox="0 0 172 172" ' +
                        'style=" fill:#000000;"><g fill="none" fill-rule="nonzero" stroke="none" stroke-width="1" stroke-linecap="butt" stroke-linejoin="miter" stroke-miterlimit="10" stroke-dasharray="" stroke-dashoffset="0" font-family="none" font-weight="none" font-size="none" text-anchor="none" style="mix-blend-mode: normal"><path d="M0,172v-172h172v172z" fill="none"></path><g fill="#ffffff"><path d="M151.30625,25.53125h-130.6125c-6.9875,0 -12.63125,5.64375 -12.63125,12.63125c0,3.49375 1.47813,6.85312 3.89687,9.1375l61.94688,58.85625c3.35938,3.225 7.65937,4.8375 12.09375,4.8375c4.43438,0 8.6,-1.6125 12.09375,-4.8375l57.78125,-54.95937v76.19063c0,4.56875 -3.7625,8.33125 -8.33125,8.33125h-123.0875c-4.56875,0 -8.33125,-3.7625 -8.33125,-8.33125v-49.45c0,-2.28437 -1.74688,-4.03125 -4.03125,-4.03125c-2.28437,0 -4.03125,1.74688 -4.03125,4.03125v49.45c0,9.00312 7.39063,16.39375 16.39375,16.39375h123.22188c9.00313,0 16.39375,-7.39062 16.39375,-16.39375v-89.7625c0,-0.13437 0,-0.40313 0,-0.5375c-0.80625,-6.45 -6.18125,-11.55625 -12.76562,-11.55625zM154.39688,41.52188l-61.94687,58.85625c-3.62813,3.49375 -9.27188,3.49375 -12.9,0l-61.94687,-58.85625c-0.94062,-0.80625 -1.47812,-2.01562 -1.47812,-3.35937c0,-2.55313 2.01563,-4.56875 4.56875,-4.56875h130.6125c2.55313,0 4.56875,2.01563 4.56875,4.56875c0,1.20938 -0.5375,2.41875 -1.47813,3.35938z"></path></g></g></svg>' +
                        '<p>' + result.data1 + '</p>');
                }

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
                                '<a href="/userPers=' + Username[i] + '">' +
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