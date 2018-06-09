var submitForm = function () {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'https://example.com/cors.php');
    var formData = new FormData(document.getElementByClass("input-form"));
    xhr.send(formData);
}