window.onload = initAll;

var repl,title,ttl,desc,dsc,post_button,question_id,like_button,dis_like_button,like_count,question_title,qstndtl;
function initAll(){
	getAllElements();
	setAllAction();
 }

 function getAllElements(){
	repl = document.getElementById('allReply')
	getAllPostFeildElements();
	getAllQuestionDetailElements();
	getLikeDislikeSpamElements();
	getProfilesElements();
	getCloseQuestionElements();
	getAllSearchElements(); 
 }

function getAllSearchElements(){
	topId = document.getElementById('topic_id');
	search_post = document.getElementById('searchpost');
	search_button = document.getElementById('postsearch');
}


var postpage,user_profile;
function getProfilesElements(){
	var i = 0;
	while(true){
		postpage = document.getElementById('postpage');
		user_profile = document.getElementById('user_profile');
	
		var view = document.getElementById('view_'+i);
		if(view){
			view.onclick = viewProfile;
		}else{
			break;
		}
		i++;
	}
}

var viewObj=null;
function viewProfile(){
	viewObj = createRequestObject();
	
	var qstn_usr_id = document.getElementById('user_id_'+this.id.split('_')[1]);
	if(viewObj){
		alert(+qstn_usr_id.value)
		viewObj.open('get','view_profile.do?qstn_usr_id='+qstn_usr_id.value,true);
		viewObj.onreadystatechange = showUserProfile;
		viewObj.send(null);
		postpage.style.display='none';
	}
}

function showUserProfile(){
	if(viewObj.readyState==4){
			if(viewObj.status==200){ 
				 var arr = eval('('+viewObj.responseText+')');
				 user_profile.style.display = 'block';
				 var ntd = document.createElement('td');
				 var etd = document.createElement('td');
				 var jtd = document.createElement('td');
				 var qtd = document.createElement('td');
				 var rtd = document.createElement('td');
				 var uttd = document.createElement('td');
				 var uspan = document.createElement('span');
				 var subspan = document.createElement('span');
				 var actd = document.createElement('td');
				 var acspan = document.createElement('span');
				 var cspan = document.createElement('span');
				 var cimg = document.createElement('img');
				 ntd.innerHTML = arr[0].userName;
				 etd.innerHTML = arr[0].email;
				 jtd.innerHTML = arr[0].joiningDate;
				 qtd.innerHTML = arr[0].questionCount;
				 rtd.innerHTML = arr[0].replyCount;
				 acspan.innerHTML = arr[0].status;
				 var user_type = document.createElement('input');
				 user_type.setAttribute('type','hidden');
				 user_type.setAttribute('value',arr[0].userType);


				 var other_user_id = document.createElement('input');
				 other_user_id.setAttribute('type','hidden');
				 other_user_id.setAttribute('value',arr[0].userId);
				 user_type.id = 'type_id';
				 other_user_id.id = 'other_user_id';
				 
				 var stsid = document.createElement('input');
				 stsid.setAttribute('type','hidden');
				 stsid.setAttribute('value',arr[0].statusId);
				 stsid.id = 'status';
				 if(arr[0].userType==3){
					uspan.innerHTML = "General User";
					var topic = selectTopics();
				 }else if(arr[0].userType==2){
					uspan.innerHTML = "Moderator of "+arr[0].topic;
				 }else{
					uspan.innerHTML = "Administrator";
				 }
				 ntd.className = 'input_cell';
				 etd.className = 'input_cell';
				 jtd.className = 'input_cell';
				 qtd.className = 'input_cell';
				 rtd.className = 'input_cell';
				 uttd.className = 'input_cell';
				 uspan.id = 'userType';
				 subspan.id = 'topics';
				 actd.className = 'input_cell';
				 var ntr = document.getElementById('qstnunm');
				 var etr = document.getElementById('qstnusreml');
				 var jtr = document.getElementById('qstnusrjdt');
				 var qtr = document.getElementById('qstnusrqc');
				 var rtr = document.getElementById('qstnusrrc');
				 var uttr = document.getElementById('qstnusrtp');
				 var actr = document.getElementById('qstnusrac');
				 var other_user = document.getElementById('other_user_id');
				 alert(other_user)
				 cimg.src = 'images/change.jpeg';
				 cimg.id = 'change';
				 cspan.appendChild(cimg);
				 ntr.appendChild(other_user_id);
				 ntr.appendChild(ntd);
				 etr.appendChild(etd);
				 jtr.appendChild(jtd);
				 qtr.appendChild(qtd);
				 rtr.appendChild(rtd);
				 uttr.appendChild(uttd);
				 uttr.appendChild(user_type);
				 uttd.appendChild(uspan);
				 uttd.appendChild(subspan);
				 uttd.appendChild(cspan);
				 actr.appendChild(actd);
				 actd.appendChild(acspan);
				 actd.appendChild(stsid);
				 
				 //var change = document.getElementById('change');
				 cimg.onclick = changeUserType;
			}
	}
}

var ouser_id,change_type,type_id,typeObj=null,topic,status_id;
function changeUserType(){
	//change_type = document.getElementById('userType');
	type_id = document.getElementById('type_id');
	ouser_id = document.getElementById('other_user_id');
	topic = document.getElementById('selecttopic');
	status_id = document.getElementById('status');

	typeObj = createRequestObject();
	if(typeObj){
		if(type_id.value==3){
			typeObj.open('get','change_type.do?type_id='+type_id.value+'&other_user_id='+ouser_id.value+'&topic_id='+topic.value+'&status_id='+status_id.value,true);
		}else{
			typeObj.open('get','change_type.do?type_id='+type_id.value+'&other_user_id='+ouser_id.value,true);
		}
		typeObj.onreadystatechange = showChangeUserType;
		typeObj.send(null);
	}

}
var topicReq = null;
function selectTopics(){
	topicReq = createRequestObject();
	if(topicReq){
		topicReq.open('get','select_topic.do?',true);
		topicReq.onreadystatechange = showToSelect;
		topicReq.send(null);
	} 
}


function showToSelect(){
	if(topicReq.readyState==4){
		if(topicReq.status==200){
			var alltop = document.getElementById('topics');
			var arr = eval('('+topicReq.responseText+')');
			alert(topicReq.responseText)
			var select = document.createElement('select');
			select.id = 'selecttopic';
			for(i=0;i<arr.length;i++){
				option = document.createElement('option');
				option.setAttribute('value',arr[i].topicId)
				option.innerHTML = arr[i].topic;
				select.appendChild(option);
			}
			alltop.appendChild(select);
			
		}
	}
}

function showChangeUserType(){
	if(typeObj.readyState==4){
		if(typeObj.status==200){
			if(typeObj.responseText=='yes'){
				showUpdateUserType();
			}
		}	
	}
}

function showUpdateUserType(){
	var top = document.getElementById('topics');
	
	//var topName = top.options[top.selectedIndex].innerHTML;
	var changeTypeId = document.getElementById("type_id");
	var changeType = document.getElementById('userType');
	var utspan = document.createElement('span');
	if(changeTypeId.value == 3){
		var selsub = document.getElementById('selecttopic');
		var ntopic = document.createElement('span');
		ntopic.innerHTML = selsub.options[selsub.selectedIndex].innerHTML;
		changeTypeId.value='2';
		changeType.innerHTML = 'Moderator of '+ntopic.innerHTML;
		top.innerHTML = '';
	}else if(changeTypeId.value == 2){
		changeTypeId.value='3';
		changeType.innerHTML = 'General User';
		var topic = selectTopics();
	}else{
	alert('This is Admin');
	}
	

}

function getCloseQuestionElements(){
	var i = 0;
	while(true){
		var close = document.getElementById('c_img_'+i);
		if(close){
			//alert (close);
			close.onclick = closeQuestion;
		}else{
			break;
		}
		i++;
	}
}
var closeObj = null, list;
function closeQuestion(){
	confirm('you want to delete this question');
	closeObj = createRequestObject();
	var post_id = document.getElementById('post_id_'+this.id.split('_')[2]);
	list = document.getElementById('list_'+this.id.split('_')[2]);
	//alert (post_id.value)
	if(closeObj){
		closeObj.open('get','del_post.do?postId='+post_id.value,true);
		closeObj.onreadystatechange = deleteQuestion;
		closeObj.send(null);
	}
}

function deleteQuestion(){
	if(closeObj.readyState==4){
			if(closeObj.status==200){
				alert(closeObj.responseText);
				if (closeObj.responseText=='true'){
					alert("dddddddd")
					list.style.display = 'none';
				}
			}	
	}
	
}

var reply;
function getReplyField(){
	reply = document.getElementById('reply');
	//alert(reply);
}


//###################### post field #######################3
function getAllPostFeildElements(){	
	title = document.getElementById("title");
	topic_id = document.getElementById("topic_id");
	ttl = document.getElementById("ttl");
	desc = document.getElementById("desc");
	dsc = document.getElementById("dsc");
	post_button = document.getElementById("postbutton");

	//qstndtl = document.getElementById("qstndtl_"+i);
	//question_title = document.getElementById("qstnttl_"+i);
}
//####################### question detail ##################### 
var post_page;
function getAllQuestionDetailElements(){
	
	post_page = document.getElementById("postpage");
	var i = 0;
	while(true){
		question_title = document.getElementById("qstnttl_"+i);
		if(question_title){
			question_title.onclick = questionDetail;
		}else{
			break;
		}
		i++;

	}
	
}

//################## like dislike ###############################
var i=0;

function getLikeDislikeSpamElements(){
	
	while(true){
	 var question_id = document.getElementById("questionId_"+i);
		if(question_id){
			like_button = document.getElementById("like_"+i);
			dis_like_button = document.getElementById("dislike_"+i);
			spam_button = document.getElementById("spam_"+i);
			like_button.onclick = likeDislikeSpam; 
			dis_like_button.onclick = likeDislikeSpam;
			spam_button.onclick = likeDislikeSpam;
		}
		else{
			break;
		}	
		i++;
	}
}

var likeDislikeObject, sts;
function likeDislikeSpam(){
	if(this.abc){
		strr = 's_';
	}else if(this.xyz){
		strr = 'r_';
	}else if(this.pqr){
		strr = 'sear_';
	}else{
		strr='';
	}
	if(document.getElementById(strr+'sear_post_id_'+this.id.split('_')[1])){
		var post_id = document.getElementById(strr+'sear_post_id_'+this.id.split('_')[1]);
		alert(post_id.value)
		likeDislikeObject = createRequestObject();
		if(this.id=='like_'+this.id.split('_')[1]){
			like_count = document.getElementById(strr+"sear_like_count_"+this.id.split('_')[1]);
			sts = 1;
		}else if(this.id=='dislike_'+this.id.split('_')[1]){
			dislike_count = document.getElementById(strr+"sear_dislike_count_"+this.id.split('_')[1]);
			sts = 0;
		}else{
			alert("spammmmmmmmmmmmmmm")
		}
		strr = 'r_';
		
		if(likeDislikeObject){
			likeDislikeObject.open('get','likedislike.do?post_id='+post_id.value+'&status='+sts,true);
			likeDislikeObject.onreadystatechange=showLikeDislike;
			likeDislikeObject.send(null);
		}

	}else{
		var post_id = document.getElementById(strr+'post_id_'+this.id.split('_')[1]);
		alert(post_id.value)
		likeDislikeObject = createRequestObject();
		if(this.id=='like_'+this.id.split('_')[1]){
			like_count = document.getElementById(strr+"like_count_"+this.id.split('_')[1]);
			sts = 1;
		}else if(this.id=='dislike_'+this.id.split('_')[1]){
			dislike_count = document.getElementById(strr+"dislike_count_"+this.id.split('_')[1]);
			sts = 0;
		}
		else{
			alert('spammmmmmmm')
		}
		strr = 'r_';
		
		if(likeDislikeObject){
			likeDislikeObject.open('get','likedislike.do?post_id='+post_id.value+'&status='+sts,true);
			likeDislikeObject.onreadystatechange=showLikeDislikeSpam;
			likeDislikeObject.send(null);
		}
	}
}

function showLikeDislikeSpam(){
	if(likeDislikeObject.readyState==4){
			if(likeDislikeObject.status==200){
				if(sts==1){
					var like = like_count.innerHTML;
					like++;
					like_count.innerHTML = like;
				}else{
					var dislike = dislike_count.innerHTML;
					dislike++;
					dislike_count.innerHTML = dislike;
				}

			}
	}

}

 function setAllAction(){
	if (title){
		title.onblur = titleOnBlur;
		title.onfocus = titleOnFocus;
	}
	if (desc){
		desc.onblur = descOnBlur;
		desc.onfocus = descOnFocus;
	}
	
	if (post_button){
		post_button.onclick = postQuestion;
	}
	
	//reply.onclick = showField;

	search_button.onclick = searchPost;

	//qstndtl.onload = hideQuestion;
 }


 //@@@@@@@@@@@@@@@@@@@ Search For Post @@@@@@@@@@@@@@@@@@@@@@@@@@

var searchPostReq = null;
function searchPost(){
	var postpage = document.getElementById('postpage');
	var search_div = document.getElementById('search_div');
	searchPostReq = createRequestObject();
	if(searchPostReq){
		searchPostReq.open('get','search_post.do?post='+search_post.value,true);
		searchPostReq.onreadystatechange = searchedPost;
		searchPostReq.send(null);
	}
 }
function searchedPost(){
	if (searchPostReq.readyState==4){
		if (searchPostReq.status==200){
			postpage.style.display = 'none';
			search_div.style.display = 'block';
			var arr = eval('('+searchPostReq.responseText+')');
			var ul = document.getElementById('search_ul');
			for (i=0;i<arr.length;i++){
				var li = document.createElement('li');
				li.id = 'sear_list'+i;
				li.className = 'list';

				var snamediv = document.createElement('div');
				snamediv.id = 'sear_qstnttlunm_'+i;
				snamediv.className = 'qstnttlunm';
				snamediv.innerHTML = arr[i].sQueUserName;
				
				var spost_id = document.createElement('input');
				spost_id.setAttribute('type','hidden');
				spost_id.setAttribute('value',arr[i].spostId);
				spost_id.id = 'sear_post_id_'+i;
				
				var suser_id = document.createElement('input');
				suser_id.setAttribute('type','hidden');
				suser_id.setAttribute('value',arr[i].suserId);
				suser_id.id = 'sear_user_id_'+i;

				var squestion_id = document.createElement('input');
				squestion_id.setAttribute('type','hidden');
				squestion_id.setAttribute('value',arr[i].squestionId);
				squestion_id.id = 'sear_questionId_'+i;
				
				var squesdiv = document.createElement('div');
				squesdiv.id = 'sear_qstnttl_'+i;
				squesdiv.className = 'qstnttl';
				squesdiv.pqr = 23;
				squesdiv.innerHTML = arr[i].squestionTitle;
				
				var squesdetdiv = document.createElement('div');
				squesdetdiv.id = 'sear_qstndtl_'+i;
				squesdetdiv.className = 'qstndtl';
				squesdetdiv.innerHTML = arr[i].squestionDetail;
				
				var slkdiv = document.createElement('div');
				
				var stlikespan = document.createElement('span');
				stlikespan.id = 'sear_total_like_'+i;
				stlikespan.className = 'likeDislikeCount';
				stlikespan.innerHTML = 'Likes';				

				var slikespan = document.createElement('span');
				slikespan.id = 'sear_like_count_'+i;
				slikespan.className = 'like';
				slikespan.innerHTML = arr[i].slikeCount;			

				var slikeImg = document.createElement('img');
				slikeImg.id = 'like_'+i;
				slikeImg.pqr = 23;
				slikeImg.className = 'likeButton';
				slikeImg.src = 'images/like.jpg';	
				slikeImg.onclick = likeDislike;

				var stdlikespan = document.createElement('span');
				stdlikespan.id = 'sear_total_dislike_'+i;
				stdlikespan.className = 'likeDislikeCount';
				stdlikespan.innerHTML = 'Dislikes';				

				var sdlikespan = document.createElement('span');
				sdlikespan.id = 'sear_dislike_count_'+i;
				sdlikespan.className = 'dislike';
				sdlikespan.innerHTML = arr[i].sdisLikeCount;			

				var sdislikeImg = document.createElement('img');
				sdislikeImg.id = 'dislike_'+i;
				sdislikeImg.pqr = 23;
				sdislikeImg.className = 'dislikeButton';
				sdislikeImg.src = 'images/dislike.jpg';	
				sdislikeImg.onclick = likeDislike;

				squesdiv.onclick = questionDetail;
				
				ul.appendChild(li);
				li.appendChild(snamediv);
				snamediv.appendChild(suser_id);
				li.appendChild(spost_id);
				li.appendChild(squestion_id);
				li.appendChild(squesdiv);
				li.appendChild(squesdetdiv);
				li.appendChild(slkdiv);
				slkdiv.appendChild(stlikespan);
				stlikespan.appendChild(slikespan);
				stlikespan.appendChild(slikeImg);
				slkdiv.appendChild(stdlikespan);
				stdlikespan.appendChild(sdlikespan);
				stdlikespan.appendChild(sdislikeImg);

			}
		}
	}
}
//@@@@@@@@@@@@@@@@@@@ new Reply Fields @@@@@@@@@@@@@@@@@@@@@@@@@@

function showField(){
	var reply_field = document.getElementById('replyField');
	reply_field.innerHTML = "";
	var user_id = document.getElementById("user_id");
	if (user_id!=null){
	var input = document.createElement("textarea");
	input.rows = "3";
	input.cols = "100";
	input.id = 'rptxtarea';
	var button = document.createElement("button");
	//alert(input)
	button.innerHTML = 'reply';
	button.id = 'rplybtn'

	button.onclick = showCurrentReply;

	//var pstpg = document.getElementById('postpage');
	
	//pstpg.appendChild(pstdtl);
	reply_field.appendChild(input);
	reply_field.appendChild(button);
	}else{
		alert("PLEASE LOGIN FOR REPLY")
	}

}
var repReqObj;
function showCurrentReply(){
//alert("in show current reply")
	repReqObj = createRequestObject();
	if(repReqObj){

		//var question_id = document.getElementById('s_question_id_'+)
		rply = document.getElementById("rptxtarea")
		repReqObj.open('GET','post_reply.do?reply='+rply.value+'&topicId='+topic_id.value+'&question_id='+question_id.value,true);
		repReqObj.onreadystatechange=showPostReply;
		repReqObj.send(null);
		
	}
}

function showPostReply(){
	if(repReqObj.readyState==4){
			if(repReqObj.status==200){ 
				 var resp = repReqObj.responseText;
				 if(!(repReqObj.responseText=='')){
					 var arr = eval('('+repReqObj.responseText+')');
						 
						 var newli = document.createElement('li');
						 var span = document.createElement('span');
						 var divv = document.createElement('div');
						 var lddiv = document.createElement('div');
						 var lspan = document.createElement('span');
						 var dspan = document.createElement('span');
						 
						 newli.className = "currentrply";
						 span.className = "unm";
						 divv.className = "nwrp";
						 span.innerHTML = arr[0].reply_unm;
						 divv.innerHTML = arr[0].message;

						 var new_reply = document.getElementById('allReply');
						 new_reply.appendChild(newli);
						 newli.appendChild(span);
						 newli.appendChild(divv);
						// alert(rply.innerHTML);
						 rply.value="";

					
				 }
			}
	}

}


// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Question Detail Field @@@@@@@@@@@@@@@@@@@@@

var strr = '';
var questionObject=null
function questionDetail(){

	if(this.pqr){
		qdetail = 'sear_';
	}else{
		qdetail='';
	}
	repl.style.display = 'block';

	questionObject = createRequestObject();
	if(qdetail){
		var post_id = document.getElementById(qdetail+"post_id_"+this.id.split('_')[2]);
		question_id = document.getElementById(qdetail+"questionId_"+this.id.split('_')[2]);
		user_name = document.getElementById(qdetail+"qstnttlunm_"+this.id.split('_')[2]);
		total_like = document.getElementById(qdetail+"total_like_"+this.id.split('_')[2]);
		total_dislike = document.getElementById(qdetail+"total_dislike_"+this.id.split('_')[2]);
		//alert(user_name+"aaaaaa")
		titel = document.getElementById(qdetail+"qstnttl_"+this.id.split('_')[2]);
		detail = document.getElementById(qdetail+"qstndtl_"+this.id.split('_')[2]);
	}else{
		var post_id = document.getElementById("post_id_"+this.id.split('_')[1]);
		question_id = document.getElementById("questionId_"+this.id.split('_')[1]);
		user_name = document.getElementById("qstnttlunm_"+this.id.split('_')[1]);
		total_like = document.getElementById("total_like_"+this.id.split('_')[1]);
		total_dislike = document.getElementById("total_dislike_"+this.id.split('_')[1]);
		//alert(user_name+"aaaaaa")
		titel = document.getElementById("qstnttl_"+this.id.split('_')[1]);
		detail = document.getElementById("qstndtl_"+this.id.split('_')[1]);
	}

	if(questionObject){
		questionObject.open('GET','postdetail.do?post_id='+post_id.value+'&question_id='+question_id.value,true);
		questionObject.onreadystatechange=showQuestionDetail;
		questionObject.send(null);
		search_page = document.getElementById('search_ul');
		qstndtl = document.getElementById('qstndtl_'+this.id.split('_')[1]);
		//alert("qaqaqa")
		//alert("qstnttl"+this.id.split('_')[1]);
		//qstndtl.style.display='block';
		post_page.style.display='none';
		search_page.style.display='none';
	}

}

//@@@@@@@@@@@@@@@@ Question Detail with Replies @@@@@@@@@@@@@@@@@@@@@@@@@@@

function showQuestionDetail(){
	if(questionObject.readyState==4){
		if(questionObject.status==200){
			var resp = questionObject.responseText;
			var arr = eval('('+questionObject.responseText+')');
			var obj = document.getElementById('postdetail');
			obj.style.display = 'block';
			

			var span = document.createElement('span');
			var div = document.createElement('div');
			var divv = document.createElement('div');
			var lddiv = document.createElement('div');
			var spann = document.createElement('span');
			
			
			//likeImage.className = 'dislikeButton';
			//disLikeImage.className = 'dislikeButton';
			var lspan = document.createElement('span');
			var dlspan = document.createElement('span');
			lspan.innerHTML = total_like.innerHTML;
			//lspan.indx = total_like.id.split('_')[2];
			//alert(lspan.getElementsByTagName('img')[0].id+"@@@@@@@@@@@");
			lspan.getElementsByTagName('span')[0].id = "s_"+lspan.getElementsByTagName('span')[0].id;
			lspan.getElementsByTagName('img')[0].abc = 12;
			lspan.getElementsByTagName('img')[0].onclick = likeDislike;

			dlspan.innerHTML = total_dislike.innerHTML;
			dlspan.getElementsByTagName('span')[0].id = "s_"+dlspan.getElementsByTagName('span')[0].id;
			dlspan.getElementsByTagName('img')[0].abc = 12;
			dlspan.getElementsByTagName('img')[0].onclick = likeDislike;

			if(!(qdetail)){
				var qiddiv = document.createElement('input');
				qiddiv.setAttribute('type','hidden');
				qiddiv.setAttribute('id','s_question_id_'+total_like.id.split('_')[2]);
				qiddiv.value = document.getElementById('questionId_'+total_like.id.split('_')[2]).value;

				var input = document.createElement('input');
				input.setAttribute('type','hidden');
				input.setAttribute('id','s_post_id_'+total_like.id.split('_')[2]);
				input.value = document.getElementById('post_id_'+total_like.id.split('_')[2]).value;
			}else{
				var input = document.createElement('input');
				input.setAttribute('type','hidden');
				input.setAttribute('id','s_sear_post_id_'+total_like.id.split('_')[3]);
				input.value = document.getElementById('sear_post_id_'+total_like.id.split('_')[3]).value;
			
				var qiddiv = document.createElement('input');
				qiddiv.setAttribute('type','hidden');
				qiddiv.setAttribute('id','s_sear_question_id_'+total_like.id.split('_')[3]);
				qiddiv.value = document.getElementById('sear_questionId_'+total_like.id.split('_')[3]).value;

			}
			//dlspan.indx = total_dislike.id.split('_')[2];
			//div.innerHTML = question_detail;
			
	
			span.className = 'qstnttlunm';
			div.className = 'title';
			divv.className = 'detail';
			spann.id = 'reply';
			spann.className = 'reply';
						
			span.innerHTML = user_name.innerHTML;
			div.innerHTML = titel.innerHTML;
			divv.innerHTML = detail.innerHTML;
			spann.innerHTML = 'New Reply';

			obj.appendChild(span);
			obj.appendChild(div);
			obj.appendChild(divv);
			obj.appendChild(qiddiv);
			obj.appendChild(input);
			obj.appendChild(spann);
			obj.appendChild(lddiv);
			lddiv.appendChild(lspan);
			lddiv.appendChild(dlspan);
			lddiv.appendChild(spann);
			spann.onclick = showField;
			strr = 'r_';
			for(i = 0;i<arr.length;i++){
				var  repli = document.createElement('li');
				var unmdiv = document.createElement('div');
				var idinput = document.createElement('input');
				var  repdiv = document.createElement('div');
				var  replddiv = document.createElement('div');
				var  replspan = document.createElement('span');
				var  repdspan = document.createElement('span');
				var  replimg = document.createElement('img');
				var  repdimg = document.createElement('img');
				var  replcspan = document.createElement('span');
				var  repdcspan = document.createElement('span');
				var  closespan = document.createElement('span');
				var  closeimg = document.createElement('img');
				closeimg.src = 'images/close.jpg';


				idinput.setAttribute("type","hidden");
				idinput.setAttribute("value",arr[i].reply_post_id);
				idinput.setAttribute("id","r_post_id_"+i);
				replimg.src = 'images/like.jpg';
				repdimg.src = 'images/dislike.jpg';
				replspan.innerHTML = 'Likes:';
				repdspan.innerHTML = 'Dislike:';
				unmdiv.innerHTML = arr[i].reply_unm;
				repdiv.innerHTML = arr[i].reply;
				replcspan.innerHTML = arr[i].reply_lc;
				repdcspan.innerHTML = arr[i].reply_dc;

				repli.className = 'replylist';
				unmdiv.className = 'repunm';
				repdiv.className = 'repl';
				replimg.className = 'likeButton';
				repdimg.className = 'dislikeButton';
				replimg.id = 'like_'+i;
				repdimg.id = 'dislike_'+i;
				replcspan.id = 'r_like_count_'+i;
				repdcspan.id = 'r_dislike_count_'+i;
				closespan.className = 'cr_span';
				closeimg.className = 'c_reply';
				closeimg.id = 'c_reply_'+i;
				repli.id = 'replylist_'+i;				

				repl.appendChild(repli);
				repli.appendChild(unmdiv);
				unmdiv.appendChild(closespan);
				closespan.appendChild(closeimg);
				repli.appendChild(repdiv);
				repli.appendChild(idinput);
				repli.appendChild(replddiv);
				replddiv.appendChild(replspan);
				replddiv.appendChild(repdspan);
				replspan.appendChild(replcspan);
				repdspan.appendChild(repdcspan);
				replspan.appendChild(replimg);
				repdspan.appendChild(repdimg);
				//alert(replimg+"like me");
				//var like_image = document.getElementById('like_'+i);
				//alert('like_'+i);
				replimg.xyz = 12;
				repdimg.xyz = 12;
				replimg.onclick = likeDislike; 
				repdimg.onclick = likeDislike;
				
				closeimg.onclick = closeReply;
			}
		}
	
	}
}

var closeRep=null;
function closeReply(){
	closeRep = createRequestObject();
	var post_id = document.getElementById('r_post_id_'+this.id.split('_')[2]);
	reply_list = document.getElementById('replylist_'+this.id.split('_')[2]);
	if(closeRep){
		closeRep.open('get','del_post.do?postId='+post_id.value,true);
		closeRep.onreadystatechange = showCloseReply;
		closeRep.send(null);
	}
}

function showCloseReply(){
	alert(closeRep)
	if(closeRep.readyState==4){
		if(closeRep.status==200){
			alert(closeRep.responseText)
			if (closeRep.responseText=='true'){
				reply_list.style.display = 'none';
			}
		}
	}
	
}

//@@@@@@@@@@@@@@@@@@@@@@@@@ Post New Question @@@@@@@@@@@@@@@@@@@@@@@@@

var reqObject;
 function postQuestion(){
	reqObject = createRequestObject();
	if(reqObject){
		reqObject.open('GET','post_question.do?title='+title.value+'&desc='+desc.value+'&topicId='+topic_id.value,true);
		reqObject.onreadystatechange=showPostQuestion;
		reqObject.send(null);
		
	}
	
 }


 function showPostQuestion(){
	if(reqObject.readyState==4){
			if(reqObject.status==200){ 
				 var i = 0;
				 while(true){
					var likeId = document.getElementById('like_'+i);
					if(likeId){
					
					}else{
						break;
					}
					i++;
				 }
				 var resp = reqObject.responseText;
				 //alert(reqObject.responseText+" === ")
				 if(!(reqObject.responseText=='')){
					 var log_id = document.getElementById('log_id');
					 var arr = eval('('+reqObject.responseText+')');
					 var obj = document.createElement('li');
					 var div = document.createElement('div');
					 var div1 = document.createElement('div');
					 var qddiv = document.createElement('div');
					 var uId = document.createElement('input');
					 var pId = document.createElement('input');
					 var qId = document.createElement('input');
					 var vpspan = document.createElement('span');
					 var cqspan = document.createElement('span');
					 var vprofile = document.createElement('input');
					 var span = document.createElement('span');
					 var span1 = document.createElement('span');
					 var span2 = document.createElement('span');
					 var span3 = document.createElement('span');
					 var span4 = document.createElement('span');
					 var close = document.createElement('img');
					 var likeImage = document.createElement('img');
					 var disLikeImage = document.createElement('img');
					 likeImage.src = "images/like.jpg";
					 disLikeImage.src = "images/dislike.jpg";
					 close.src = "images/close.jpg";
					 vprofile.setAttribute('type','button');
					 vprofile.setAttribute('name','view');
					 vprofile.setAttribute('value','view profile');
					 vprofile.className = 'nview';
					 vprofile.id = 'view_'+i;

					 uId.setAttribute('type','hidden');
					 pId.setAttribute('type','hidden');
					 qId.setAttribute('type','hidden');
					 
					 likeImage.className = 'dislikeButton';
					 disLikeImage.className = 'dislikeButton';
					 likeImage.id = 'like_'+i;
					 disLikeImage.id = 'dislike_'+i;
					 obj.id = 'list_'+i;
					 div1.id = 'qstnttlunm_'+i;

					 uId.id = 'user_id_'+i;
					 pId.id = 'post_id_'+i;
					 qId.id = 'questionId_'+i;

					 div1.innerHTML = arr[0].question_title_unm;
					 div.innerHTML = arr[0].title;
					 qddiv.innerHTML = arr[0].question_detail;

					 uId.setAttribute('value',arr[0].u_id);
					 pId.setAttribute('value',arr[0].post_id);
					 qId.setAttribute('value',arr[0].question_id);


					 span2.innerHTML = 'likes:';
					 span3.innerHTML = 'dislike:';
					 span2.className = 'likeDislikeCount';
					 span2.id = 'total_like_'+i;
					 span3.className = 'likeDislikeCount';
					 span3.id = 'total_dislike_'+i;
					 span.innerHTML = arr[0].like_count;
					 span.id = 'like_count_'+i
					 span1.innerHTML = arr[0].dislike_count;
					 span1.id = 'dislike_count_'+i;	
					 cqspan.className = 'c_span';
					 close.className = 'nc_img';
					 close.id = 'c_img_'+i;
					 obj.className = 'list';
					 div.className = 'qstnttl';
					 div1.className = 'qstnttlunm';
					 span.className = 'likeDislikeCount';
					 span1.className = 'likeDislikeCount';
					 span.className = 'like';
					 span1.className = 'dislike';
					 span4.className = 'space';
					 qddiv.className = 'qstndtl';
					 qddiv.id = 'qstndtl_'+i;
					 div.id = 'qstnttl_'+i;
					 div.onclick = questionDetail;

					 document.getElementById('ul').appendChild(obj);
					 obj.appendChild(div1);
					 div1.appendChild(uId);
					 if(log_id.value==2){
						 div1.appendChild(cqspan);
						 cqspan.appendChild(close);
					 }
					 if(log_id.value==1)
						 div1.appendChild(vpspan);
						 vpspan.appendChild(vprofile);
					 }
					 obj.appendChild(pId);
					 obj.appendChild(qId);

					 obj.appendChild(div);

					 obj.appendChild(qddiv);
					 obj.appendChild(span2);
					 //span2.appendChild(span);
					 span2.appendChild(span);
					 span2.appendChild(likeImage);
					 obj.appendChild(span3);
					 span3.appendChild(span1);
					 span3.appendChild(disLikeImage);

					 likeImage.onclick = likeDislike;			 
					 disLikeImage.onclick = likeDislike;
					 close.onclick = closeQuestion;
					 vprofile.onclick = viewProfile;
					 div.oclick = questionDetail;

					 title.value = "";
					 desc.value = "";
				}else{
					alert("Question And Question Title is Mendetory")
				}

		 }
}

 // @@@@@@@@@@@@@@@@ Normal JavaScript @@@@@@@@@@@@@@@@

 function titleOnBlur(){
	if(!title.value){
		ttl.innerHTML = "Please Give The Title Of Your Question";
		ttl.style.innerHTML = "visibile";
		return false;
	}else{
		return true;
	}
 }

 function titleOnFocus(){
	ttl.innerHTML = '';
	ttl.style.innerHTML = "hidden";
 }

 function descOnBlur(){
	if(!desc.value){
		dsc.innerHTML = "Please Describe Your Question";
		dsc.style.innerHTML = "visibile";
		return false;
	}else{
		return true;
	}
 }

 function descOnFocus(){
	dsc.innerHTML = '';
	dsc.style.innerHTML = "hidden";
 }