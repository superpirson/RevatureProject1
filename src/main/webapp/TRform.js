var submitForm = function () {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/TRform');
    var formData = new FormData(document.getElementByClass("input-form"));
    xhr.send(formData);
}

var submitLogin = function () {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/login');
    var formData = new FormData(document.getElementByClass("login-form"));
    xhr.send(formData);
}