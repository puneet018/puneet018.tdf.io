window.onload = initAll;

var email,eml,password,pass;
function initAll(){
	 getAllElements();

	 setVallidation(); 
 }

 function getAllElements(){
	email = document.getElementById("email");
	eml = document.getElementById("eml");
	password = document.getElementById("password");
	pass = document.getElementById("pass");
 }

 function setVallidation(){
	email.onblur = emailOnBlur;
	email.onfocus = emailOnFocus;

	password.onblur = passwordOnBlur;
	password.onfocus = passwordOnFocus;
}



function emailOnBlur(){
	if(!email.value){
		eml.innerHTML = 'Invalid Email Id';
		eml.style.visibility = 'visible';
		return false;
	}else{
		return true;
	}
}

function emailOnFocus(){
	eml.innerHTML = '';
	eml.style.visibility = 'hidden';
}

function passwordOnBlur(){
	var flag = true;
	if(!password.value){
		pass.innerHTML = 'Password is required!';
		pass.style.visibility = 'visible';
		flag = false;
	}

	if(password.value.length<6){
		pass.innerHTML = 'password should be > 6 character';
		pass.style.visibility = 'visible';
		flag = false;
	
	}

	if(password.value.length>15){
		pass.innerHTML = 'password should be < 15 character';
		pass.style.visibility = 'visible';
		flag = false;
	
	}
	return flag;
}
function passwordOnFocus(){
	pass.innerHTML = '';
	pass.style.visibility = 'hidden';
}