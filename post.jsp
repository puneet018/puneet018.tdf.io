<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import = "models.*,java.util.*"%>
<html>
	<head>
		<title>Post Page</title>
		<link rel="icon" href="images/title.gif" type = "image/gif"/>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<link rel="stylesheet" type="text/css" href="css/post.css"/>
		<script type="text/javascript" src="js/ajax.js"></script>
		<script type="text/javascript" src="js/post.js"></script>
		
	</head>
	<% ArrayList questions = (ArrayList)request.getAttribute("questions"); %>
	<body background="images/subj.jpg">
	<% User user = (User)session.getAttribute("user"); %>
		<div id = "main_box">
			<div><%@ include file = "header.jsp" %></div>
			<div><%@ include file = "menu.jsp" %></div>
			<div id="main_block">
				<div id = "postpage">
					<div id="engine">
						<input type="text" name="search" id="searchpost" placeholder="Search according to Post">
						<img src="images/search.jpg" id="postsearch">
					</div><br />
					<table id="tbl">
						<caption><a href="topic.do?subName=${param.subName} &subId=${param.subId}">${param.subName}></a><span id="top">${param.topic}</span>
						</caption>
						<% if(user!=null){%>
						<input type="hidden" value="${param.topicId}" id="topic_id">
						<input type="hidden" value="${user}" id="user_id">
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td class = "label_cell">Post Your Question Here</td>
							<td class = "colon_cell">:-></td>
							<td class = "input_cell"></td>
						</tr>

						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>

						<tr>
							<td class = "label_cell">Title of your Question</td>
							<td class = "colon_cell">:</td>
							<td class = "input_cell"><input type="text" name="title" id="title">
							<span id="ttl" class="mg"></span>
							</td>
						</tr>

						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>

						<tr>
							<td class = "label_cell">Describe your Question</td>
							<td class = "colon_cell">:</td>
							<td class = "input_cell"><textarea id="desc" rows="4"cols="30" placeholder="describe your Question in detail"></textarea> <span id="dsc" class="mg"></span>&nbsp;&nbsp;&nbsp;
							<input type="button" value="Post" id="postbutton">
							</td>
						</tr>
						<% }else{%>

						<tr>
							<td colspan = "3"><span id="pg">Please Login To Post New Questions</span>
							</td>
						</tr>
						<% }%>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3"><span id="alrdypstd">Already Posted Questions</span>
							</td>
						</tr>
						
					</table>
					<ul id = "ul">
						<%for(int i=0;i<questions.size();i++){							
								Question question = (Question)questions.get(i);			
						%>
						<li class ="list" id="list_<%= i%>">
							<div class="qstnttlunm" id="qstnttlunm_<%= i %>">
								<input type="hidden" value="<%= question.getUser().getUserId()%>" id="user_id_<%= i %>">
								<%= question.getUser().getUserName()%>
									<%if(user!=null){%>
										<% if (user.getUserType().getUserTypeId()==1){%>
										<span>
											<input type="button" name="view" value="view profile"class="view" id="view_<%= i %>">
										</span>
										<% }%>
										<% if (user.getUserType().getUserTypeId()==2){%>
										<span class="c_span">
											<img src = "images/close.jpg" class = "c_img"
											id = "c_img_<%= i%>"/>
										</span>
										<% }%>									
									<% }%>									
							</div>
							<input type="hidden" value="<%= question.getPostId()%>" id="post_id_<%= i %>">
							<input type="hidden" value="<%= question.getQuestionId()%>" id="questionId_<%= i %>">
							<%-- -----------------second method ----------------------
							<span class="qstnttl" id="qstnttl_<%= i %>">
								<a href = "postdetail.do?postId=<%= question.getPostId()%>"><%= question.getQuestionTitle()%></a>
							</span>
							--%>	

							<div class="qstnttl" id="qstnttl_<%= i %>">
								<%= question.getQuestionTitle()%>
							</div>
							<div class="qstndtl" id="qstndtl_<%= i %>">
								<%= question.getQuestionDetail()%>
							</div>
												
							<div>
								<span class="likeDislikeCount" id="total_like_<%= i %>">
									Likes:<span class="like" id="like_count_<%= i %>"><%= question.getLikeCount()%>
									</span>&nbsp;
									<img src = "images/like.jpg" class = "likeButton" id="like_<%= i%>">
								</span>
								<span class="space">&nbsp;</span>
								<span class="likeDislikeCount" id="total_dislike_<%= i %>">Dislikes:
									<span class="dislike" id="dislike_count_<%= i %>"><%= question.getDislikeCount()%>
									</span>&nbsp;
									<img src = "images/dislike.jpg" class="dislikeButton" id="dislike_<%= i %>">
								</span>	
								<span class="space">&nbsp;</span>
								<span class="likeDislikeCount" id="total_spam_<%= i %>">Spam:
									<span class="spam" id="spam_count_<%= i %>">
									</span>&nbsp;
									<img src = "images/spam1.jpg" class="spamButton" id="spam_<%= i %>">
								</span>	
							</div>
							
						</li>
						
					<% }%>
				</ul>
			</div>
			<div id="search_div">
				<ul id = "search_ul"></ul>
			</div>

			<div id="postdetail" style="display:none"></div>
			<div id = "replyField"></div>
			<div id = "newReply"></div>
			<ul id = "allReply" style="display:none">
				<span id = "alrdyreply">Already Posted Replies</span>
			</ul>
			<div id="user_profile" style="display:none">
				<table id="tbl2">
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr id="qstnunm">
						<td class = "label_celln">User Name</td>
						<td class = "colon_celln">:</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr id="qstnusreml">
						<td class = "label_celln">Email</td>
						<td class = "colon_celln">:</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr id="qstnusrjdt">
						<td class = "label_celln">Joining Date</td>
						<td class = "colon_celln">:</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr id="qstnusrqc">
						<td class = "label_celln">Question Count</td>
						<td class = "colon_celln">:</td>
					</tr>

					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr id="qstnusrrc">
						<td class = "label_celln">Reply Count</td>
						<td class = "colon_celln">:</td>
					</tr>
					
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr id="qstnusrtp">
						<td class = "label_celln">User Type</td>
						<td class = "colon_celln">:</td>
					</tr>
					
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
					
					<tr id="qstnusrac">
						<td class = "label_celln">Status</td>
						<td class = "colon_celln">:</td>
					</tr>
					
					<tr> 
						<td colspan="3">&nbsp;</td>
					</tr>

				</table>
			
			</div>
			<div><%@ include file = "footer.jsp" %></div>	
	</body>
</html>