var submitForm = function (url, form) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', url);
    var formData = new FormData(document.getElementsByClassName(form)[0]);
    xhr.send(formData);
}
var getForm = function (formID, func) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'FormInfo?formID=' + formID);
    xhr.onload = function() {
    	var data = JSON.parse(xhr.response);
    	func(data);
    }
    xhr.send()
}
var loadLogin = function () {
	var url = new URL(window.location.href);
	var formID = url.searchParams.get("formID");
	if (formID) {
		getForm(formID, function(data) {
			var form = data.forms[0]
			document.getElementsByName("first-name")[0].value = form.FNAME;
			document.getElementsByName("date")[0].value = form.DATE_COMPLETED;
			document.getElementsByName("cost")[0].value = form.COST;
			document.getElementsByName("reason-change")[0].value = form.REASONCHANGE;
			document.getElementsByName("location")[0].value = form.LOCATION;
			document.getElementsByName("description")[0].value = form.DESCRIPTION;
			document.getElementsByName("last-name")[0].value = form.LNAME;
			document.getElementsByName("grade")[0].value = form.GRADES_APPROVAL;
			document.getElementsByName("event-type")[0].value = form.EVENT_TYPE;
			document.getElementsByName("reason")[0].value = form.REASON_REIMBURSE;
			document.getElementsByName("employee-approve")[0].value = form.EMPLOYEE_APPROVAL;
			document.getElementsByName("reason-denial")[0].value = form.REASON_DENIAL;
		});
	}
}
var updateFormsTable = function () {
    getForm('', function(data) {
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
            	document.querySelector("." + "form-" + (i + 1) + " ." + propName).innerHTML = '<p>' + propName + '</p><p>' + data.forms[i][prop] + '</p>';
            	}
            }
            //the edit button
            var edit = document.createElement('a');
            document.getElementsByClassName("form-" + (i + 1))[0].appendChild(edit).innerHTML = "edit";
            edit.classList.add("button");

            edit.href = "TRForm?formID=" + data.forms[i].FORM_ID;

        }
    });
}