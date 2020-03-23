function SentMessage() {
    $('#contactsFormGeneral').empty();
    $('#contactsFormGeneral').append(
        "<div>" +
            "<h4>Спасибо!</h4>" +
            "<h5>Мы свяжемся с вами для ответа.</h5>" +
        "</div>");
}

//скрипты для переноса строки на ctrl+enter и отправки на enter
document.body.onkeydown = function(event) {
    var e = event || window.event;
    var code = e.keyCode || e.which;
    var activeEl = document.activeElement.id;
    if(code === 13 && activeEl === "contactsText") {
        activeEl.style.readonly = 1;
        var text_redirect = document.getElementById('contactsText').value;
        text_redirect = text_redirect.replace(/^\s+/, "");
        if(text_redirect !== "") {
            SentMessage();
        } else {
            alert("ЗАПОЛНИТЕ ФОРМУ!");
        }
    }
};
document.body.onkeydown = function(event) {
    var e = event || window.event;
    var code = e.keyCode || e.which;
    var activeEl = document.activeElement.id;
    if(code === 13 && e.ctrlKey) {
        if(activeEl === "contactsText") {
            document.getElementById('contactsText').value += "\n";
        } else {
            return false;
        }
    } else if(code === 13 && activeEl === "contactsText") {
        var text_redirect = document.getElementById('contactsText').value;
        text_redirect = text_redirect.replace(/^\s+/, "");
        if(text_redirect !== "") {
            SentMessage();
        } else {
            return false;
        }
    }
};