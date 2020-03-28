function filterUsername() {
    var input, filter, ul, li, i;
    input = document.getElementById("usernameID");
    filter = input.value.toUpperCase();
    div = document.getElementById("usersTilesID");
    a = div.getElementsByTagName("th");
    for (i = 0; i < a.length; i++) {
        if (a[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
            a[i].style.display = "";
        } else {
            a[i].style.display = "none";
        }
    }
}

function openSearch() {
    var Search = document.getElementById("searchCourses");
    Search.style.display = "block";
    document.getElementById("CourseNameID").focus();
}
function closeSearch() {
    var Search = document.getElementById("searchCourses");
    Search.style.display = "none";
}