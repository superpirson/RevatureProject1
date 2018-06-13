var submitForm = function () {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'TRform');
    var formData = new FormData(document.getElementsByClassName("input-form")[0]);
    xhr.send(formData);
}

var submitLogin = function () {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'login');
    var formData = new FormData(document.getElementsByClassName("login-form")[0]);
    xhr.send(formData);
}
var loadLogin = function () {//window.location.href
	var url = new URL(window.location.href);
	var formID = url.searchParams.get("formID");
	console.log(formID);
	
}
var getForm = function (formID, func) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'FormInfo?formID=' + formID);
    xhr.onload = func;
    xhr.send()
}

var updateFormsTable = function () {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'FormInfo?formID=');
    xhr.onload = function () {
        var data = JSON.parse(xhr.response);
        console.log(data);
        for (var i = 0; i < data.forms.length; i++) {
        	//create the form div
            document.getElementsByClassName("js-display-table")[0].appendChild(document.createElement("div")).classList.add("form-" + (i + 1));
            for (var prop in data.forms[i]) {
            	if (data.forms[i][prop]) {
            		var propName;
            	switch(prop) {
            	case "FORM_ID":
            		propName = "form-ID";
            		break;
            	case "FNAME":
            		propName = "first-name";
            		break;
            	case "LNAME":
            		propName = "last-name";
            		break;
            	case "GRADE":
            		propName = "grade";
        			break;
            	case "DATE_COMPLETED":
            		propName = "date";
            		break;
            	case "EMPLOYEE_APPROVAL":
            		propName = "employee-approval";
            		break;
            	case "BENCO_APPROVAL":
            		propName = "benco-approval";
            		break;
            	case "DHA_APPROVAL":
            		propName = "dha-approval";
            		break;
            	case "DSA_APPROVAL":
            		propName = "dsa-approval";
            		break;
            	case "GRADES_APPROVAL":
            		propName = "grade-approval";
            		break;
            	case "FORM_STATUS":
            		propName = "form-status";
            		break;
            	case "DESCRIPTION":
            		propName = "description";
            		break;
            	case "LOCATION":
            		propName = "location";
            		break;
            	case "COST":
            		propName = "cost";
            		break;
            	case "REASON_REIMBURSE":
            		propName = "reimburse-reason";
            		break;
            	case "EVENT_TYPE":
            		propName = "event-type";
            		break;
            	case "SUBMITTED_BY":
            		propName = "submitted-by";
            		break;
            	}
            	//create the prop div
            	document.getElementsByClassName("form-" + (i + 1))[0].appendChild(document.createElement("div")).classList.add(propName);
            	
            	//fill in the data
            	document.querySelector("." + propName).innerHTML = '<p>' + propName + '</p><p>' + data.forms[i][prop] + '</p>';
            	}
            }
            //the edit button
            var edit = document.createElement('a');
            document.getElementsByClassName("form-" + (i + 1))[0].appendChild(edit).innerHTML = "edit";
            edit.classList.add("button")
            edit.href = "/TRForm?formID=" + data.forms[i].FORM_ID;
        }
    }
    xhr.send();
}