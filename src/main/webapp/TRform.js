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

var getForm = function () {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'FormInfo?formID=90');
    xhr.onload = function () {
        var data = JSON.parse(xhr.response);
        console.log(data);
        for (var i = 0; i < data.forms.length; i++) {
            document.getElementsByClassName("js-display-table")[0].appendChild(document.createElement("div")).classList.add("form-" + (i + 1));
            for (var prop in data.forms[i]) {
            	if (data.forms[i][prop]) {
            	//create the prop div
            	document.getElementsByClassName("form-" + (i + 1))[0].appendChild(document.createElement("div")).classList.add(prop);
            	
            	//fill in the data
            	document.querySelector("." + [prop]).innerHTML = [prop] + " " + data.forms[i][prop];
            	}
            }
        }
    }
    xhr.send();
}