<%@ page import="models.*,java.util.*" %>

<br><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>Profile Page</title>
		<link rel="icon" href="images/title.gif" type = "image/gif"/>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<link rel="stylesheet" type="text/css" href="css/profile.css"/>
		<script type="text/javascript" src="js/profile.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
	</head>

	<body background = "images/prof1.jpg">
	<% User user = (User)session.getAttribute("user"); %>
	
	<form action="nextprofile.do">
		<div><%@ include file = "header.jsp" %></div>
		<div><%@ include file = "menu.jsp" %></div>
		<div id = "main box">
			<div id="main_block"> 
			<span id="nq"><a href="subject.do?">Post a new Question</a></span>
				<table id="tbl">
					<tr>
						<caption>Profile: 
						</caption>
						
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class = "label_cell">User Name</td>
						<td class = "colon_cell">:</td>
						<td class = "input_cell"><span id="unm"><%= user.getUserName() %></span>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<span class="sp">
						<span id="edit_unm" class = "edit"><img src = "images/edit.png" id="edit"></img ></span>
						&nbsp;&nbsp;
						<span id="save_unm" class= "edit_nodisplay"><img src = "images/save.jpg" id="save"></img ></span>
						&nbsp;&nbsp;
						<span id="cancel_unm" class= "edit_nodisplay"><img src = "images/cancel.png" id="cancel"></img ></span>
						<img id="wait_unm" src="images/wait.gif">
						</span>
						</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class = "label_cell">Email</td>
						<td class = "colon_cell">:</td>
						<td class = "input_cell"><span id="eml"><%= user.getEmail() %></span>
						</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class = "label_cell">Joining Date</td>
						<td class = "colon_cell">:</td>
						<td class = "input_cell"><span id="jd"><%= user.getJoiningDate() %></span>
						</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class = "label_cell">Question Count</td>
						<td class = "colon_cell">:</td>
						<td class = "input_cell"><span id="qc"><%= user.getUserQuestionCount() %></span>
						</td>
					</tr>

					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class = "label_cell">Reply Count</td>
						<td class = "colon_cell">:</td>
						<td class = "input_cell"><span id="rc"><%= user.getUserReplyCount() %></span>
						</td>
					</tr>
					
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class = "label_cell">User Type</td>
						<td class = "colon_cell">:</td>
						<td class = "input_cell"><span id="ut"><%= user.getUserType().getUserTypeId()==3?"General User":user.getUserType().getUserTypeId()==2?"Moderator":"Administration"%></span>
						</td>
					</tr>
					
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
					
					<tr>
						<td class = "label_cell">Activation Status</td>
						<td class = "colon_cell">:</td>
						<td class = "input_cell"><span id="as"><%= user.getActivationStatus() %></span>
						</td>
					</tr>
					
					<tr>
						<td class = "label_cell"></td>
						<td class = "colon_cell"></td>
						<td class = "input_cell"><div id="rp"><a href ="resetpassword.do"> Reset Password</a></div>
						</td>
					</tr>

					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>

					<tr>
						<td colspan="3" id="buttonbox"><input type="submit" value="Next"></td>
					</tr>

					<tr> 
						<td colspan="3">&nbsp;</td>
					</tr>



				</table>

			</div>
			<div><%@ include file = "footer.jsp" %></div>

		</div>
	</body>
</html>
