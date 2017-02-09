window.onload = initAll;

var username,un,email,em,password,pas,confirm_password,cpas;
function initAll(){
	 getAllElements();

	 setVallidation(); 
 }

 function getAllElements(){
	username = document.getElementById("username");
	un = document.getElementById("un");
	email = document.getElementById("email");
	em = document.getElementById("em");
	password = document.getElementById("password");
	pas = document.getElementById("pas");
	confirm_password = document.getElementById("confirm_password");
	cpas = document.getElementById("cpas"); 
 }

 function setVallidation(){
	username.onblur = usernameOnBlur;
	username.onfocus = usernameOnFocus;

	email.onblur = emailOnBlur;
	email.onfocus = emailOnFocus;

	password.onblur = passwordOnBlur;
	password.onfocus = passwordOnFocus;

	confirm_password.onblur = confirm_passwordOnBlur;
	confirm_password.onfocus = confirm_passwordOnFocus;
}


function usernameOnBlur(){
	if(!username.value){
		un.innerHTML = 'User Name must be Given';
		un.style.visibility = 'visible';
		return false;
	}else{
		return true;
	}
}

function usernameOnFocus(){
	un.innerHTML = '';
	un.style.visibility = 'hidden';
}


function emailOnBlur(){
	if(!email.value){
		em.innerHTML = 'Invalid Email Id';
		em.style.visibility = 'visible';
		return false;
	}else{
		return true;
	}
}

function emailOnFocus(){
	em.innerHTML = '';
	em.style.visibility = 'hidden';
}

function passwordOnBlur(){
	var flag = true;
	if(!password.value){
		pas.innerHTML = 'Password is required!';
		pas.style.visibility = 'visible';
		flag = false;
	}

	if(password.value.length<6){
		pas.innerHTML = 'password should be > 6 character';
		pas.style.visibility = 'visible';
		flag = false;
	
	}

	if(password.value.length>15){
		pas.innerHTML = 'password should be < 15 character';
		pas.style.visibility = 'visible';
		flag = false;
	
	}
	return flag;
}
function passwordOnFocus(){
	pas.innerHTML = '';
	pas.style.visibility = 'hidden';
}


function confirm_passwordOnBlur(){
	var flag = true;

	if(!confirm_password.value){
		cpas.innerHTML = 'Confirm Password should be given';
		cpas.style.visibility = 'visible';
		flag = false;
	}

	if(confirm_password.value!=password.value){
		cpas.innerHTML = 'Confirm Password and Password are not equal';
		cpas.style.visibility = 'visible';
		flag = false;
	}

	return flag;
}

function confirm_passwordOnFocus(){
	cpas.innerHTML = '';
	cpas.style.visibility = 'hidden';
}
