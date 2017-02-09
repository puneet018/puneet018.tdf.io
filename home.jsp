<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>Home Page</title>
		<link rel="icon" href="images/title.gif" type = "image/gif"/>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<link rel="stylesheet" type="text/css" href="css/home.css"/>
		
	</head>

	<body style="background: #04423F">
		<div id = "main_box">
			<div><%@ include file = "header.jsp" %></div>
				<div><%@ include file = "menu.jsp" %></div>
					
					<div id="main_block">
						<div id ="mig">
							<a href="showregister.do"><img src = "images/registrationpage.jpg" id="imgreg">
							<span class="txt" id="registertxt">Click On Image For Registration</span>
							<a href="showlogin.do"><img src = "images/loginpage.jpg" id="imglog">
							<span  class="txt" id="logintxt">Click On Image For Login Page</span>
							<a href="subject.do"><img src = "images/subject.jpg" id="imgsub">
							<span  class="txt" id="subtxt">Click On Image For Subject Page</span>
							</a>
						</div>
						
					</div>
					<div id="r_box">
						</div>

					<div><%@ include file = "footer.jsp" %></div>
			</div>
		</div>
	</body>
</html>