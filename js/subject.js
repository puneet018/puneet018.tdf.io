window.onload = initAll;
var n_subj,a_subj;
function initAll(){
	getAllElements();
	getAllSearchElements();
	getAllRemoveElements();
	setAllAction();
}
var search_sub,search_button;
function getAllSearchElements(){
	search_sub = document.getElementById('searchSub');
	search_button = document.getElementById('subsearch');
}

function getAllElements(){
	n_subj = document.getElementById('n_subj');
	a_subj = document.getElementById('a_subj');
}

var remove;
function getAllRemoveElements(){
	var i=0;
	while(true){
		remove = document.getElementById('r_subj_'+i);
		if(remove){
			remove.onclick = removeSubject;
		
		}else{
			break;
		}i++;
	}
}

var subjId ;
var subRemReq=null;
function removeSubject(){
	alert(this.id)
	subjId = document.getElementById('subjId_'+this.id.split('_')[2]);
	row = document.getElementById('row_'+this.id.split('_')[2]);
	subRemReq = createRequestObject();
	if (subRemReq){
		subRemReq.open('get','remove_subject.do?subjectId='+subjId.value, true);
		subRemReq.onreadystatechange = showRemoveSubject;
		subRemReq.send(null);
	}

}

function showRemoveSubject(){
	if(subRemReq.readyState==4){
		if(subRemReq.status==200){
			if (subRemReq.responseText=='true'){
					//alert("dddddddd")
					row.style.display = 'none';
			}
		}
	}

}

function setAllAction(){
	if(a_subj){
		a_subj.onclick = addSubject;
	}
	search_button.onclick = searchSubject;
}
var searchReq = null;
function searchSubject(){
	searchReq = createRequestObject();
	if(searchReq){
		searchReq.open('get','search_subject.do?subject='+search_sub.value,true);
		searchReq.onreadystatechange = searchedSub;
		searchReq.send(null);
	}
}

function searchedSub(){
	if (searchReq.readyState==4){
		if (searchReq.status==200){
			if (searchReq.responseText){
				var arr = eval('('+searchReq.responseText+')');
				if (arr){
					if (!(arr[0].subjectName==null)){
					
						var subjectPage = document.getElementById('subjectPage');
						var searchPage = document.getElementById('searchPage');
						subjectPage.style.display = 'none';
						searchPage.style.display = 'block';
						var no = document.getElementById('SerialNo');
						var name = document.getElementById('Subject');
						var count = document.getElementById('topicCount');
						no.innerHTML = 1;
						count.innerHTML = arr[0].topicCount;
						name.innerHTML = '<a href="topic.do?subName='+arr[0].subjectName+'&subId='+arr[0].subjectId+'">'+arr[0].subjectName+'</a>';
					}else{
						alert('Subject Not Found');
					}

				}
			}else{
				alert('Please Write SomeThing To Search');
			}
			
		}
	}
	
}


var subReq;
function addSubject(){
	subReq = createRequestObject();
	if (subReq){
		subReq.open('get','add_subject.do?subject='+n_subj.value, true);
		subReq.onreadystatechange = showNewSubject;
		subReq.send(null);
	}
}

function showNewSubject(){
	if(subReq.readyState==4){
		if(subReq.status==200){
			var arr = eval('('+subReq.responseText+')');
			var obj = document.getElementById('tbl');
			var tr = document.createElement('tr');
			var tr1 = document.createElement('tr');
			var td = document.createElement('td');
			var td1 = document.createElement('td');
			var td2 = document.createElement('td');
			var td3 = document.createElement('td');
			td3.setAttribute("colspan",3);
			td3.innerHTML = '&nbsp';

			td.innerHTML = arr[0].subject_id;
			td1.innerHTML = '<a href="topic.do?subName='+arr[0].subject+'&subId='+arr[0].subject_id+'">'+arr[0].subject+'</a>';
			td2.innerHTML = arr[0].topic_count;
			
			n_subj.value = "";
			
			obj.appendChild(tr1);
			tr1.appendChild(td3);
			obj.appendChild(tr);
			tr.appendChild(td);
			tr.appendChild(td1);
			tr.appendChild(td2);
	


		}
	
	}

}