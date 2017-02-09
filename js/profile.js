window.onload = initAll;

function initAll(){
	getAllElements();

	setAllAction();
}

function getAllElements(){
	getUnmElement();

	//getEmailElement();

}

var unm,save_unm,edit_unm,cancel_unm,wait_unm;
function getUnmElement(){
	unm = document.getElementById("unm");
	edit_unm = document.getElementById("edit_unm");
	save_unm = document.getElementById("save_unm");
	cancel_unm = document.getElementById("cancel_unm");
	wait_unm = document.getElementById("wait_unm");

}

function setAllAction(){
	edit_unm.onclick = showUserNameEditable;
	cancel_unm.onclick = showUserNameNonEdit;
	save_unm.onclick = saveUserName;
}

var reqObj;
var unmEditField;
function saveUserName(){
	wait_unm.style.display = 'inline';
	reqObj = createRequestObject();
	if(reqObj){
		unmEditField = document.getElementById('unm_id');
		reqObj.open('GET', 'save_user_name.do?unm='+unmEditField.value,true);
		reqObj.onreadystatechange = showUserName;
		reqObj.send(null);
	}
}

	function showUserName(){
		if(reqObj.readyState==4){
			if(reqObj.status==200){
				wait_unm.style.display = 'none';
				var msg = reqObj.responseText;

				if(msg=='yes'){
					showUserNameNonEditable();
				}else{
					showUserNameEdit;
				}
		}
		
	}
}

function showUserNameNonEditable(){
	save_unm.style.display = 'none';
	cancel_unm.style.display = 'none';
	edit_unm.style.display = 'inline';
	
	unmEditFld = document.getElementById('unm_id');
	unm.innerHTML = unmEditFld.value;
}


function showUserNameEditable(){
	userName = unm.innerHTML;
	var newTextField = document.createElement('input');
	newTextField.id = 'unm_id';
	newTextField.value = userName;
	
	unm.innerHTML = '';
	unm.appendChild(newTextField);

	save_unm.style.display = 'inline';
	cancel_unm.style.display = 'inline';
	edit_unm.style.display = 'none';
}

function showUserNameNonEdit(){
	save_unm.style.display = 'none';
	cancel_unm.style.display = 'none';
	edit_unm.style.display = 'inline';
	
	unmEditFld = document.getElementById('unm_id');
	unm.innerHTML = userName;
}

