<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title> Login Page </title>
		<link rel="icon" href="images/title.gif" type = "image/gif"/>
		<script type = "text/javascript" src="js/login.js"> </script>
		<link rel = "stylesheet" type= "text/css" href = "css/common.css">
		<link rel = "stylesheet" type= "text/css" href = "css/login.css"/>
		
	</head>
		<body background="images/aa.jpg">
			<form action="login.do">
				<div id = "main_box">
					<div><%@ include file = "header.jsp" %></div>
					<div><%@ include file = "menu.jsp" %></div>
					
					<div id = "main_block">
						<% String msg = (String)request.getAttribute("msg"); %>

						<% if(msg!=null){ %>
							<div id="msg"><%= msg %></div>
						<% } %>
						<table id = "tbl">
							<caption>Login Here</caption>
							<tr>
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr>
								<td class = "label_cell">Email</td>
								<td class = "colon_cell">:</td>
								<td class = "input_cell"> <input type="text" name="email" class="input_field" id ="email"><span id="eml" class="mg"></span>
								</td>
							</tr>
							<tr>
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr>
								<td class = "label_cell">Password</td>
								<td class = "colon_cell">:</td>
								<td class = "input_cell"> <input type="password" name="password" class="input_field" id="password"><span id="pass" class="mg"></span>
								</td>
							</tr>
							<tr>
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3" id="buttonbox"><input type="submit" value="Login"></td>
							</tr>
							<tr> 
								<td colspan="3">&nbsp;</td>
							</tr>
						</table>
						


					</div>


					<div><%@ include file = "footer.jsp" %></div>
				</div>
			</form>
		
		</body>
</html>
