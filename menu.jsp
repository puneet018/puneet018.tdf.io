<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="models.*"%>
<html>
	<head>
		<title>  </title>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<link rel="stylesheet" type="text/css" href="css/menu.css"/>

	</head>

	<body>
		<% User user1 = (User)session.getAttribute("user"); %>
		<div id="main_box">
			<div id="menu_box">
				<a href="home.do">Home</a>	
				<a href="#">Services</a>	
				<a href="subject.do">Subjects</a>	
				<a href="#">Search</a>
				<% if(user1==null){%>
				<a href="showlogin.do">Login</a>
				<a href="showregister.do">Registration</a>
				<% }else{%>
				<a href="showlogout.do">Logout</a>
				<% }%>
				<a href="#">Privacy</a>
				<a href="#">Contact Us</a>	
				<a href="#">Help</a>	
			</div>
		</div>
