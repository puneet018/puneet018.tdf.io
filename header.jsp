<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>  </title>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<link rel="stylesheet" type="text/css" href="css/header.css"/>
	</head>

	<body>
	<% User user2 = (User)session.getAttribute("user"); %>
		<div id="main_box">
			<div id="header">
				<img src = "images/download.jpg" height = "120 px" width = "120 px">
				<span id="logotext">TECHNICAL DISCUSSION FORUM</span>
				<% if(user2!=null){%>
					<input type="hidden" id="log_id" value=<%= user2.getUserType().getUserTypeId()%>>
				<a href="showprofile.do">${userName}</a>
				<% }%>
			</div>
		</div>
	</body>
</html>	