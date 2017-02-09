window.onload = initAll;
function initAll(){
	getAllDobElements();
	getAllGenElements();
	getAllCountryElements();
	getAllProfessionElements();
	getAllInterestElements();
	setAllAction();
}

var save_dob,edit_dob,cancel_dob,cdob,sdob,wait_dob,adob;
function getAllDobElements(){
	
	adob = document.getElementById('adob');
	save_dob = document.getElementById('save_dob');
	edit_dob = document.getElementById('edit_dob');
	cancel_dob = document.getElementById('cancel_dob');
	wait_dob= document.getElementById("wait_dob");
	
}
var save_gen;
function getAllGenElements(){
	save_gen = document.getElementById('save_gen_img');
	male = document.getElementById('male');
	female = document.getElementById('female');
}

var edit_con,save_con,cancel_con,acon,ncon,country;
function getAllCountryElements(){
	acon = document.getElementById('acon');
	ncon = document.getElementById('ncon');
	country = document.getElementById('country');
	save_con = document.getElementById('save_con');
	edit_con = document.getElementById('edit_con');
	cancel_con = document.getElementById('cancel_con');
	wait_con = document.getElementById("wait_con");
		
 }
 var save_prof,edit_prof,cancel_prof,nprof,profession,aprof;
function getAllProfessionElements(){
	aprof = document.getElementById('aprof');
	nprof = document.getElementById('nprof');
	profession = document.getElementById('profession');
	save_prof = document.getElementById('save_prof');
	edit_prof = document.getElementById('edit_prof');
	cancel_prof = document.getElementById('cancel_prof');
}

 var edit_int,save_int,cancel_int;
function getAllInterestElements(){

	save_int = document.getElementById('save_int');
	edit_int = document.getElementById('edit_int');
	cancel_int = document.getElementById('cancel_int');
	wait_int = document.getElementById("wait_int");
		
 }

function setAllAction(){
	edit_dob.onclick = editDob;
	save_dob.onclick = saveDob;
	cancel_dob.onclick = noChangeDob;
	
	if(save_gen){
		save_gen.onclick = saveGen;
	}

	edit_con.onclick = editCountry;
	save_con.onclick = saveCountry;
	cancel_con.onclick = noChangeCountry;
	
	edit_prof.onclick = editProfession;
	save_prof.onclick = saveProfession;
	cancel_prof.onclick = cancelProfession;
	
	edit_int.onclick = editInterest;
	save_int.onclick = saveInterest;
	cancel_int.onclick = cancelInterest;
}

//@@@@@@@@@@@@@@@@@@@@@@@ DATE OF BIRTH @@@@@@@@@@@@@@@@@@@@@@@@@@

function editDob(){
	nwtxtfld = document.createElement('input');
	nwtxtfld.setAttribute('type','date');
	nwtxtfld.id = 'dob';
	cdob = adob.innerHTML;
	adob.innerHTML = '';
	sdob = document.getElementById('sdob');
	sdob.appendChild(nwtxtfld);
	save_dob.style.display = 'inline';
	cancel_dob.style.display = 'inline';
	edit_dob.style.display = 'none';

}

var saveDobReq;
function saveDob(){
	ndob = document.getElementById('dob');
	wait_dob.style.display = 'inline';
	//alert(adob.innerHTML)
	saveDobReq = createRequestObject();
	if(saveDobReq){
		saveDobReq.open('get','save_dob.do?dob='+ndob.value,true);
		saveDobReq.onreadystatechange = showSaveDob;
		saveDobReq.send(null);
	}
}

function showSaveDob(){
	if(saveDobReq.readyState==4){
		if(saveDobReq.status==200){
			wait_dob.style.display = 'none';
			var msg = saveDobReq.responseText;
			if(msg=='yes'){
				showDateOfBirthNonEditable();
			}else{
				//showDateOfBirthEdit();
			}
		}
	}
}


function noChangeDob(){
	//var sdob = document.getElementById(sdob);
	//nwtxtfld.style.display='none';
	save_dob.style.display = 'none';
	cancel_dob.style.display = 'none';
	edit_dob.style.display = 'inline';
	sdob.removeChild(nwtxtfld);
	adob.innerHTML=cdob;
}

function showDateOfBirthNonEditable (){	
	save_dob.style.display = 'none';
	cancel_dob.style.display = 'none';
	edit_dob.style.display = 'inline';
	dobEditFld = document.getElementById('dob');
	adob.innerHTML = dobEditFld.value;
	cdob.innerHTML = dobEditFld.value;
	sdob.removeChild(nwtxtfld);
	ndob.style.display = 'none';

}


//@@@@@@@@@@@@@@@@@@@@@ GENDER @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 var saveGenReq=null;
function saveGen(){
	genId = male.checked?1:2;
	saveGenReq = createRequestObject();
	if(saveGenReq){
		saveGenReq.open('get','save_gender.do?genId='+genId,true);
		saveGenReq.onreadystatechange = showGender;
		saveGenReq.send(null);
	}
	
}

function showGender(){
	if(saveGenReq.readyState==4){
		if(saveGenReq.status==200){
			var msg = saveGenReq.responseText;
			if(msg =='yes'){
				showGenderNonEditable();
			}
		}
	}
}

function showGenderNonEditable(){ 
	alert('aaaa');
	sgen = document.getElementById('sgen');
	save_gen.style.display = 'none';
	sgen.innerHTML = genId==2?'Female':'Male';
}

//@@@@@@@@@@@@@@@@@@@@@@@@ COUNTRY @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
var ccon; 
function editCountry(){
	acon.style.display = 'none';
	ccon = acon.innerHTML;
	acon.innerHTML = '';
	country.style.display = '-webkit-box';
	save_con.style.display = 'inline';
	cancel_con.style.display = 'inline';
	edit_con.style.display = 'none';
}

var saveConReq;
function saveCountry(){
	saveConReq = createRequestObject();
	if(saveConReq){
		saveConReq.open('get','save_con.do?countryId='+country.value,true);
		saveConReq.onreadystatechange = showCountry;
		saveConReq.send(null);
	}
}

function showCountry(){
	if(saveConReq.readyState==4){
		if(saveConReq.status==200){
			var msg = saveConReq.responseText;
			if(msg=='yes'){
				showCountryNonEditable();	
			}else{
			
			}
		}
	}
}

function showCountryNonEditable(){
	save_con.style.display = 'none';
	cancel_con.style.display = 'none';
	edit_con.style.display = 'inline';
	//scon = document.getElementById('scon');
	if(!acon){
		acon = document.createElement('span');
		acon.id = 'acon';
		ncon.appendChild(acon);
	}
	acon.innerHTML = country.options[country.selectedIndex].innerHTML;
	acon.style.display = '-webkit-box';
	ccon = acon.innerHTML;	
	country.style.display = 'none';		
}

function noChangeCountry(){
	save_con.style.display = 'none';
	cancel_con.style.display = 'none';
	edit_con.style.display = 'inline';
	acon.innerHTML = ccon;
	acon.style.display = '-webkit-box';
	country.style.display = 'none';
}
// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ PROFESSION @@@@@@@@@@@@@@@@@@@@@@@@@
function editProfession(){
	aprof.style.display = 'none';
	cprof = aprof.innerHTML;
	aprof.innerHTML = '';
	profession.style.display = '-webkit-box';
	save_prof.style.display = 'inline';
	cancel_prof.style.display = 'inline';
	edit_prof.style.display = 'none';
}

var saveProfReq=null;
function saveProfession(){
	profession = document.getElementById('profession');
	saveProfReq = createRequestObject();
	if(saveProfReq){
		saveProfReq.open('get','save_prof.do?professionId='+profession.value,true);
		saveProfReq.onreadystatechange = showProfession;
		saveProfReq.send(null);
	}
}

function showProfession(){
	if(saveProfReq.readyState==4){
		if(saveProfReq.status==200){
			var msg = saveProfReq.responseText;
			if(msg=='yes'){
				showProfessionNonEditable();	
			}else{
			
			}
		}
	}
}


function showProfessionNonEditable(){
	save_prof.style.display = 'none';
	cancel_prof.style.display = 'none';
	edit_prof.style.display = 'inline';
	if(!aprof){
		aprof = document.createElement('span');
		aprof.id = 'aprof'
		nprof.appendChild(aprof);
	}
	aprof.innerHTML = profession.options[profession.selectedIndex].innerHTML;
	cprof = aprof.innerHTML;
	aprof.style.display = '-webkit-box';
	profession.style.display = 'none';
	

}

function cancelProfession(){
	aprof.innerHTML = cprof;
	aprof.style.display = '-webkit-box';
	profession.style.display = 'none';
	save_prof.style.display = 'none';
	cancel_prof.style.display = 'none';
	edit_prof.style.display = 'inline';
}


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ INTEREST @@@@@@@@@@@@@@@@@@@@@@@@@@
saveIntReq=null;
function saveInterest(){

	interest = document.getElementById('interest');
	saveIntReq = createRequestObject();
	if(saveIntReq){
		saveIntReq.open('get','save_interest.do?interest='+interest.value,true);
		saveIntReq.onreadystatechange = showInterest;
		saveIntReq.send(null);
	}
}

function showInterest(){
	if(saveIntReq.readyState==4){
		if(saveIntReq.status==200){
			var msg = saveIntReq.responseText;
			if(msg =='yes'){
				showInterestNonEditable();
			}
		}
	}
}

function showInterestNonEditable(){
	save_int.style.display = 'none';
	cancel_int.style.display = 'none';
	edit_int.style.display = 'inline';
	aint = document.getElementById('sint');
	aint.innerHTML = interest.value;
}

function editInterest(){
	txtr = document.createElement('textarea');
	txtr.id = 'interest';
	txtr.cols="30";
	txtr.rows="5";
	txtr.innerHTML = aint.innerHTML;
	aint.innerHTML = '';
	sint.appendChild(txtr);
	save_int.style.display = 'inline';
	cancel_int.style.display = 'inline';
	edit_int.style.display = 'none';

}

function cancelInterest(){
	save_int.style.display = 'none';
	cancel_int.style.display = 'none';
	edit_int.style.display = 'inline';
	aint.innerHTML=txtr.innerHTML;
	sint.removeChild(txtr);
}