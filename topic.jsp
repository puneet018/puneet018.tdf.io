<%@ page import="models.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>Topic Page</title>
		<link rel="icon" href="images/title.gif" type = "image/gif"/>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<link rel="stylesheet" type="text/css" href="css/topic.css"/>
		<script src="js/ajax.js"> </script>
		<script src="js/topic.js"> </script>
	
	</head>
	<%User user3 = (User)session.getAttribute("user"); %>
	<% ArrayList topics = (ArrayList)request.getAttribute("topics"); %>
	<body background="images/topic.jpg">
		<form>
			<div><%@ include file = "header.jsp" %></div>
			<div><%@ include file = "menu.jsp" %></div>
			<div id = "main box">
				<div id="main_block"> 
					<div id="topicPage">
						<div id="engine">
							<input type="text" name="search" id="searchTop" placeholder="Search according to Topic">
							<img src="images/search.jpg" id="topsearch">
						</div><br />
						<table id="tbl">
						<input type="hidden" value="${subId}" id="subject_id">
						<input type="hidden" value="${subName}" id="subject">
							<caption>${subName}</caption>
							<tr>
								<td colspan="3">&nbsp;</td>
							</tr>
							<%if(user3!=null){%>
								<% if (user3.getUserType().getUserTypeId()==1){%></div>
									<tr id="newtopic">
									<td class="nwtpc"><span id="nwtpc">Add New Topic In ${subName}:</span></td>
									<td class="n_topic"><input type="text" name="n_topic" id="n_topic" maxlength = "20"></td>
									<td class="a_topic"><input type="button" value="Add Topic" name="a_topic" id="a_topic"></td>
								</tr>
								<% }%>
							<% }%>
							<tr>
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr class="tblsty">
								<td id="td">TopicId</td>
										
								<td id="td">Topics</td>

								<td id="td">Post Count</td>

								<td id="td">Last Post</td>

								<td id="td">Moderator</td>
							</tr>
							<%for(int i=0;i<topics.size();i++){							
								Topic topic = (Topic)topics.get(i);						
							%>
							<tr >
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr class="tblstych" id="topic_<%= i %>">
								<td id="td"><%= i+1 %></td>
								<input type = 'hidden' value = "<%= topic.getTopicId()%>" id = "topic_id_<%= i %>">	
								<td id="top_<%= i %>">
								<a href="post.do?subName=${param.subName}&topic=
								<%= topic.getTopic()%>&subId=${param.subId}&topicId=<%= topic.getTopicId()%>">
								<%= topic.getTopic()%></a>
								
								</td>

								<td id="td"><%= topic.getPostCount() %></td>

								<td id="td"></td>

								<td id="td"></td>

								
								<%if(user3!=null){%>
									<% if (user3.getUserType().getUserTypeId()==1){%>
										<td>
										<input type="button" value="Remove" class="r_topic" id="r_topic_<%= i %>">
									</td>
									<% }%>
								<% }%>
								
							</tr>
							<% } %>
						</table>
					</div>
					<div id="searchPage">
						<table id="tbl">

							<tr>
								<td id="td">TopicId</td>
									
								<td id="td">Topic</td>

								<td id="td">Post Count</td>

								<td id="td">Last Post</td>

								<td id="td">Moderator</td>
							</tr>
							
							<tr>
								<td colspan = "5">&nbsp;</td>
							</tr>
							<tr>
								<td id="serialNo"></td>
									
								<td id="topic"></td>

								<td id="postCount"></td>

								<td id="lastPost"></td>
								
								<td id="Moderator"></td>
							</tr>
						</table>
					
					</div>
				</div>
			</div>
		</form>
	</body>