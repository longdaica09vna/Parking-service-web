function checkSubmitOffice() {
	let check1 = checkText("name", "invalid-name");
	let check2 = checkTelephone();
	let check3 = checkNumber();
	let check4 = checkText("dateFrom", "invalid-datefrom");
	let check5 = checkText("dateTo", "invalid-dateto");
	if (check1 && check2 && check3 && check4 && check5) {
		let button = document.getElementById(button).value;
		if(button == "add") return confirm("Do you want to add Booking office?");
		else if (button == "edit") return confirm("Do you want to edit Booking office?");
	} else {
		return false;
	}
}

function checkSubmitEmployee() {
	let check1 = checkText("employeename", "invalid-employeename");
	let check2 = checkTelephone();
	let check3 = checkText("dob", "invalid-dob");
	let check4 = checkSex();
	let check5 = checkText("account", "invalid-account");
	let check6 = checkText("password", "invalid-password");
	if (check1 && check2 && check3 && check4 && check5 && check6) {
		let button = document.getElementById(button).value;
		if(button == "add") return confirm("Do you want to add Employee?");
		else if (button == "edit") return confirm("Do you want to edit Employee?");
	} else {
		return false;
	}
}

function checkSubmitTicket() {
	let check1 = checkText("customerName", "invalid-customerName");
	let check2 = checkText("booktime", "invalid-booktime");
	if (check1 && check2) {
		let button = document.getElementById(button).value;
		if(button == "add") return confirm("Do you want to add Ticket?");
		else if (button == "edit") return confirm("Do you want to edit Ticket?");
	} else {
		return false;
	}
}

function checkSubmitCar() {
	let check1 = checkText("license", "invalid-license");
	if (check1) {
		let button = document.getElementById(button).value;
		if(button == "add") return confirm("Do you want to add Car?");
		else if (button == "edit") return confirm("Do you want to edit Car?");
	} else {
		return false;
	}
}

function checkLogin() {
	let check1 = checkText("account", "invalid-account");
	let check2 = checkText("password", "invalid-password");
	if (check1 && check2) {
		return confirm("Do you want to Login?");
	} else {
		return false;
	}
}

function checkSubmitTrip() {
	let check1 = checkText("destination", "invalid-destination");
	let check2 = checkText("departureTime", "invalid-time");
	let check3 = checkText("driver", "invalid-driver");
	let check4 = checkText("carType", "invalid-carType");
	let check5 = checkNumber();
	let check6 = checkText("departureDate", "invalid-date");
	if (check1 && check2 && check3 && check4 && check5 && check6) {
		let button = document.getElementById(button).value;
		if(button == "add") return confirm("Do you want to add Trip?");
		else if (button == "edit") return confirm("Do you want to edit Trip?");
	} else {
		return false;
	}
}

function checkText(id, idFeedback) {
	let name = document.getElementById(id).value;
	if (name == "") {
		document.getElementById(idFeedback).style.display = "block";
		return false;
	} else {
		document.getElementById(idFeedback).style.display = "none";
		return true;
	}
}

function checkTelephone() {
	let phone = document.getElementById("phone").value;
	let regex = /^\d{10,10}$/i;
	if (!regex.test(phone)) {
		document.getElementById("invalid-phone").style.display = "block";
		return false;
	} else {
		document.getElementById("invalid-phone").style.display = "none";
		return true;
	}
}

function checkNumber() {
	let number = document.getElementById("number").value;
	let regex = /^\d+$/i;
	if (!regex.test(number)) {
		document.getElementById("invalid-number").style.display = "block";
		return false;
	} else {
		document.getElementById("invalid-number").style.display = "none";
		return true;
	}
}

function checkSex() {
	if (document.getElementById('male').checked || document.getElementById('female').checked) {
		document.getElementById("invalid-sex").style.display = "none";
		return true;
	} else {
		document.getElementById("invalid-sex").style.display = "block";
		return false;
	}
}


