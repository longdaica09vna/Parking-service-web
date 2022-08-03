



function checkName(id, idFeedback) {
	let name = document.getElementById(id).value;
	let regex = /^[A-Za-z0-9\s]+{4,20}$/i;
	if (!regex.test(name)) {
		document.getElementById(idFeedback).style.display = "block";
		return false;
	} else {
		document.getElementById(idFeedback).style.display = "none";
		return true;
	}
}

function checkPrice(id, idFeedback) {
	let phone = document.getElementById(id).value;
	let regex = /^\d+{6,11}$/i;
	if (!regex.test(phone)) {
		document.getElementById(idFeedback).style.display = "block";
		return false;
	} else {
		document.getElementById(idFeedback).style.display = "none";
		return true;
	}
}
function checkArea(id, idFeedback) {
	let phone = document.getElementById(id).value;
	let regex = /^\d+{2,11}$/i;
	if (!regex.test(phone)) {
		document.getElementById(idFeedback).style.display = "block";
		return false;
	} else {
		document.getElementById(idFeedback).style.display = "none";
		return true;
	}
}

function checkInputAdd() {
	let check1 = checkName("name", "invalid-name");
	let check2 = checkPrice("price", "invalid-price");
	let check3 = checkArea("area", "invalid-area");
	if (check1 && check2 && check3) {
		return confirm("Do you want to add parking lot?")
	} else {
		return false;
	}
}

function checkInputEdit() {
	let check1 = checkName("name", "invalid-name");
	let check2 = checkPrice("price", "invalid-price");
	let check3 = checkArea("area", "invalid-area");
	if (check1 && check2 && check3) {
		return confirm("Do you want to edit parking lot?")
	} else {
		return false;
	}
}


