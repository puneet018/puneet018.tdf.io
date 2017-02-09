window.onload = initAll;
var n_topic,a_topic,sub_id;
function initAll(){
	getAllElements();
	getAllSearchElements();
	setAllAction();
}


var search_top,search_button;
function getAllSearchElements(){
	subId = document.getElementById('subject_id');
	sub = document.getElementById('subject');
	search_top = document.getElementById('searchTop');
	search_button = document.getElementById('topsearch');
}

function getAllElements(){
	//alert('getAllElements')
	sub_id = document.getElementById('subject_id');
	n_topic = document.getElementById('n_topic');
	a_topic = document.getElementById('a_topic');
	getAllRemoveElements();
}
var r_topic,topic_id;
function getAllRemoveElements(){
	var i = 0;
	while(true){
		r_topic = document.getElementById('r_topic_'+i);
		if(r_topic){
			r_topic.onclick = removeTopic;
		}else{
			break;
		}i++;
	}
}
var delTopReq=null,topic;
function removeTopic(){
	topic_id = document.getElementById("topic_id_"+this.id.split('_')[2]);
	topic = document.getElementById("topic_"+this.id.split('_')[2]);
	delTopReq = createRequestObject();
	if(delTopReq){
		delTopReq.open('get','delete_topic.do?topic_id='+topic_id.value+'&subject_id='+subId.value,true);
		delTopReq.onreadystatechange = showDeleteTopic;
		delTopReq.send(null)
	
	}
}

function showDeleteTopic(){
	
	if(delTopReq.readyState==4){
		alert()
		if(delTopReq.status==200){
			if(delTopReq.responseText=='true'){
				topic.style.display = 'none';
			}
		}
	}

}

function setAllAction(){
	//alert('setAllAction')
	if(a_topic){
		a_topic.onclick = addTopic;
	}
	search_button.onclick = searchTopic;
}

var searchTopReq = null;
function searchTopic(){
	searchTopReq = createRequestObject();
	if(searchTopReq){
		searchTopReq.open('get','search_topic.do?topic='+search_top.value,true);
		searchTopReq.onreadystatechange = searchedTop;
		searchTopReq.send(null);
	}
}

function searchedTop(){
	if (searchTopReq.readyState==4){
		if (searchTopReq.status==200){
			if (searchTopReq.responseText){
				var arr = eval('('+searchTopReq.responseText+')');
				if (arr){
					if (!(arr[0].topicName==null)){
					
						var topicPage = document.getElementById('topicPage');
						var searchPage = document.getElementById('searchPage');
						topicPage.style.display = 'none';
						searchPage.style.display = 'block';
						var no = document.getElementById('serialNo');
						var name = document.getElementById('topic');
						var count = document.getElementById('postCount');
						no.innerHTML = 1;
						count.innerHTML = arr[0].postCount;
						name.innerHTML = '<a href="post.do?subName='+sub.value+'&topic='
						+arr[0].topicName+'&subId='+subId.value+'&topicId='+arr[0].topicId+'">'+arr[0].topicName+'</a>'
					}else{
						alert('Topic Not Found');
					}

				}
			}else{
				alert('Please Write SomeThing To Search');
			}
			
		}
	}
}

var topicReq;
function addTopic(){
	topicReq = createRequestObject();
	if (topicReq){
		//alert('topicReq')
		topicReq.open('get','add_topic.do?topic='+n_topic.value+'&subjectId='+sub_id.value, true);
		topicReq.onreadystatechange = showNewTopic;
		topicReq.send(null)
	}
}

function showNewTopic(){
	if(topicReq.readyState==4){
		if(topicReq.status==200){
			var subId = document.getElementById('subject_id');
			var sub = document.getElementById('subject');
			var arr = eval('('+topicReq.responseText+')');
	 		var obj = document.getElementById('tbl');
			var tr = document.createElement('tr');
			var tr1 = document.createElement('tr');
			var td = document.createElement('td');
			var td1 = document.createElement('td');
			var td2 = document.createElement('td');
			var td3 = document.createElement('td');
			td3.setAttribute("colspan",3);
			td3.innerHTML = '&nbsp';

			td.innerHTML = arr[0].topic_id;
			td1.innerHTML = '<a href="post.do?subName='+sub.value+'&topic='
			+arr[0].topic+'&subId='+subId.value+'&topicId='+arr[0].topic_id+'">'+arr[0].topic+'</a>';
			td2.innerHTML = arr[0].post_count;
			
			n_topic.value = "";
			
			obj.appendChild(tr1);
			tr1.appendChild(td3);
			obj.appendChild(tr);
			tr.appendChild(td);
			tr.appendChild(td1);
			tr.appendChild(td2);


		}
	
	}

}