function openNav() {
    HideMyName();
    document.getElementById("mySidenav").style.width = "250px";
}

// /* Set the width of the side navigation to 0 */
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
function HideMyName() {
    var Username = document.getElementById("username").textContent;
    var Dialogs = document.getElementById("dialogUL").getElementsByTagName("li");
    for(var i = 0; i < Dialogs.length; i++)
    {
        if(Username === Dialogs.item(i).innerText)
        {
            Dialogs.item(i).style.display = "none";
        }
    }
}

function filterFunction() {
    var input, filter, ul, li, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    div = document.getElementById("mySidenav");
    a = div.getElementsByTagName("div");
    for (i = 0; i < a.length; i++) {
        if (a[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
            a[i].style.display = "";
        } else {
            a[i].style.display = "none";
        }
    }
    HideMyName();
}