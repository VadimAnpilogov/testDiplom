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
    var Dialog = document.getElementById("UsernameDialog").textContent;
    var Li = document.getElementById("liDialog");
    if(Username === Dialog)
    {
        Li.style.display = "none";
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
function deleteDialog() {
    alert("Заглушка)");
}