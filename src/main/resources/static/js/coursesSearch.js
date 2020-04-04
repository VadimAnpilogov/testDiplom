function filterCourseName() {
    var input, filter, ul, li, i;
    input = document.getElementById("CourseNameID");
    filter = input.value.toUpperCase();
    div = document.getElementById("coursesTilesID");
    a = div.getElementsByTagName("tr");
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

function openTooltip() {
    var tooltip = document.getElementById("tooltipDiv");

    if(tooltip.style.opacity === "1")
    {
        tooltip.style.opacity = "0";
        setTimeout(tooltip.style.display = "none", 500);
    }
    else
    {
        tooltip.style.display = "block";
        tooltip.style.opacity = "1";
    }
}