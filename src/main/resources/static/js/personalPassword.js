function showPassword(svg) {
    var input = svg.previousElementSibling;
    input.type = "text";
    svg.style.display = "none";
    svg.nextElementSibling.style.display = "block";
}
function hiddenPassword(svg) {
    var input = svg.previousElementSibling.previousElementSibling;
    input.type = "password";
    svg.style.display = "none";
    svg.previousElementSibling.style.display = "block";
}