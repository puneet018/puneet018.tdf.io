<%@ page import="models.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>Subject Page</title>
		<link rel="icon" href="images/title.gif" type = "image/gif"/>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<link rel="stylesheet" type="text/css" href="css/subject.css"/>
		<script src="js/ajax.js"> </script>
		<script src="js/subject.js"> </script>
	
	</head>
	<% ArrayList subjects = (ArrayList)application.getAttribute("subjects"); %>
	

	<body background="images/topic.jpg">
		<% User user3 = (User)session.getAttribute("user"); %>

		<div id = "main_box">
			<div><%@ include file = "header.jsp" %></div>
				<div><%@ include file = "menu.jsp" %></div><br />
					<div id="main_block">
						<div id="subjectPage">
							<div id="engine">
								<input type="text" name="search" id="searchSub" placeholder="Search according to Subject">
								<img src="images/search.jpg" id="subsearch">
							</div><br />
							<table id="tbl">
								<%if(user3!=null){%>
									<% if (user3.getUserType().getUserTypeId()==1){%></div> <br /><br />
									<div id="newsubj">
										<span id="nwsbjct">Add Your New Subject Here:</span>&nbsp;&nbsp;&nbsp;
										<input type="text" name="n_subject" id="n_subj" maxlength = "20">
										<input type="button" value="Add Subject" name="a_subject" id="a_subj">
									</div> <br /><br />
									<% }%>
								<% }%>
							<caption>Subjects:</caption>
							<tr  class="tbls">
								<td  id="no">SubjectId</td>
									
								<td id="name">Subjects</td>

								<td id="count">Topic Count</td>
							</tr>
							
							<%for(int i=0; i<subjects.size(); i++){
								Subject subject = (Subject)subjects.get(i);
							%>
							<tr>
								<td colspan = "3">&nbsp;</td>
							</tr>
							<tr  class="tblsyl" id ="row_<%=i%>">
								<input type="hidden" value=<%=subject.getSubjectId()%> id="subjId_<%= i%>">
								<td id="td"><%= i+1 %></td>	
								<td id="td" class="subj"><a href="topic.do?subName=<%= subject.getSubject()%>&subId=<%=subject.getSubjectId()%>"><%= subject.getSubject() %></a></td>
								<td id="td"><%= subject.getTopicCount() %></td>
								
								<%if(user3!=null){%>
									<% if (user3.getUserType().getUserTypeId()==1){%>
										<td>
										<input type="button" value="Remove" name="r_subject" id="r_subj_<%= i%>" class="r_subj">
									</td>
								<% }%>
							<% }%>
							</tr>
							<%}%>

							</table>
						</div>
						<div id="searchPage" style="display:none">
							<table id="tbl">

							<tr>
							<td id="td">SubjectId</td>
								
							<td id="td">Subjects</td>

							<td id="td">Topic Count</td>
							</tr>
							
							<tr>
								<td colspan = "3">&nbsp;</td>
							</tr>
							<tr>
								<td id="SerialNo"></td>
									
								<td id="Subject"></td>

								<td id="topicCount"></td>
							</tr>

	

							</table>
						</div>
					</div>
					<div><%@ include file = "footer.jsp" %></div>
			</div>	
	</body>
</html>