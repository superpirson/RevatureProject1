var submitForm = function () {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'TRform');
    var formData = new FormData(document.getElementsByClassName("input-form")[0]);
    xhr.send(formData);
}

var submitLogin = function () {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'login');
    document.get
    var formData = new FormData(document.getElementsByClassName("login-form")[0]);
    xhr.send(formData);
}