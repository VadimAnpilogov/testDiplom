var status = document.getElementById("statusPers").textContent;
var reviews = document.getElementById("reviews");
if(status === "Студент")
    reviews.style.display = "none";

var str = document.getElementById("lastTR");
if(str.nextElementSibling === null)
{
    $('#reviews').empty();
    $('#reviews').append("<h1>Пока отзывов нет</h1>");
}