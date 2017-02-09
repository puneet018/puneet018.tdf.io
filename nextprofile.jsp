<%@ page import="models.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>Profile Page</title>
		<link rel="icon" href="images/title.gif" type = "image/gif"/>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<link rel="stylesheet" type="text/css" href="css/nextprofile.css"/>
		<script type = "text/javascript" src = "js/ajax.js"></script>
		<script type = "text/javascript" src = "js/nextprofile.js"></script>
	
	</head>
	<% ArrayList countries = (ArrayList)application.getAttribute("countries"); %>
	<% ArrayList professions = (ArrayList)application.getAttribute("professions"); %>
	<% User user = (User)session.getAttribute("user"); %>
	<body background = "images/prof1.jpg">
		<div id = "main_box">
			<div><%@ include file = "header.jsp" %></div>
			<div><%@ include file = "menu.jsp" %></div>
			<div id="main_block">
				<table id="ptbl">
					<tr>
						<td colspan="4"></td>
					</tr>
					<tr>
						<td class = "label_cell">Date Of Birth</td>
						<td class = "colon_cell">:</td>
						<td class = "input_cell">
							<span id = "sdob">
								<% if(!(user.getDateOfBirth()==null)){ %>
									<span id = "adob"><%= user.getDateOfBirth()%></span>
							</span>
						</td>
						<td>
							<span class="sp">
								<span id="edit_dob" class="edit"><img src = "images/edit.png" class="editdob" id="edit_dob_img"></img ></span>
								&nbsp;&nbsp;
								<span id="save_dob" class= "edit_nodisplay"><img src =		"images/save.jpg" class="savedob" id="save_dob_img"></img ></span>
								&nbsp;&nbsp;
								<span id="cancel_dob" class= "edit_nodisplay"><img src = "images/cancel.png" class="canceldob" id="cancel_dob_img"></img ></span>
								<img id="wait_dob" class="wait" src="images/wait.gif">
							</span>
						</td>
								<%}else{%>
									<span id = "adob"><input type="date" name="dob" id="dob" /></span>
								</span>
						</td>
						<td>
							<span class="sp">
								<span id="edit_dob" class="edit_nodisplay"><img src = "images/edit.png" class="editdob" id="edit_dob_img"></img ></span>
								&nbsp;&nbsp;
								<span id="save_dob" class= "edit"><img src =		"images/save.jpg" class="savedob" id="save_dob_img"></img ></span>
								&nbsp;&nbsp;
								<span id="cancel_dob" class= "edit"><img src = "images/cancel.png" class="canceldob" id="cancel_dob_img"></img ></span>
								<img id="wait_dob" class="wait" src="images/wait.gif">
							</span>
						</td>
								<% }%>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td class = "label_cell">Gender</td>
						<td class = "colon_cell">:</td>
						<td class = "input_cell">
							<span id="sgen">
								<% if(!(user.getGender().getGenderId()==0)){%>
									<span id="agen"><%= user.getGender().getGenderId()==1?"Male":"Female" %></span>
								<% }else{%>
									<span id="edit_gen">
										Male <input type="radio" name="gender" id="male" value="<%= Gender.MALE %>" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										Female <input type="radio" name="gender" id="female" value="<%= Gender.FEMALE %>" />
									</span>
									</span>
								<span class="sp">
									<span id="save_gen" class= "editgen"><img src = "images/save.jpg" class="savegen" id="save_gen_img"></img ></span>
										&nbsp;&nbsp;
								</span>
							<%}%>
							</td>

					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td class = "label_cell">Country</td>
						<td class = "colon_cell">:</td>
						<td class = "input_cell">
							<span id="scon">
								<% 
									boolean flag = false;
									boolean f = false;
									if(user.getCountry().getCountryId()!=0){%>
									<%	for(int i=0;i<countries.size();i++){
											Country country = (Country)countries.get(i);%>
										<%													if(user.getCountry().getCountryId()==country.getCountryId()){%>
											<span id='acon'><%= country.getCountry()%>
												<% f = true;%>
											</span>
										<%}%>
									<%}%>
								<%}else{ flag = true;}%>
								<span id="ncon">
									<select id="country" <%= flag?"style='display:-webkit-box'":"style='display:none'"%>>
										<%	for(int i=0;i<countries.size();i++){
											Country country = (Country)countries.get(i);%>
											<%													if(user.getCountry().getCountryId()==country.getCountryId()){%>
												<option selected = "selected" value="<%= country.getCountryId()%>"><%= country.getCountry()%></option>
										<%}else{%>
											<option value="<%= country.getCountryId()%>"><%= country.getCountry()%></option>
										<%}}%>
									</select>
								</span>
							</span>
						</td>
						<td>
							<% if(f){%>
								<span class="sp">
									<span id="edit_con" class = "edit"><img src = "images/edit.png" class="editcon" id="edit_con_img"></img ></span>
									&nbsp;&nbsp;
									<span id="save_con" class= "edit_nodisplay"><img src = "images/save.jpg" class="savecon" id="save_con_img"></img ></span>
									&nbsp;&nbsp;
									<span id="cancel_con" class= "edit_nodisplay"><img src = "images/cancel.png" class="cancelcon" id="cancel_con_img"></img ></span>
									<img id="wait_con" class="wait" src="images/wait.gif">
								</span>
							<%}else{%>
								<span class="sp">
									<span id="edit_con" class = "edit_nodisplay"><img src = "images/edit.png" class="editcon" id="edit_con_img"></img ></span>
									&nbsp;&nbsp;
									<span id="save_con" class= "edit"><img src = "images/save.jpg" class="savecon" id="save_con_img"></img ></span>
									&nbsp;&nbsp;
									<span id="cancel_con" class= "edit"><img src = "images/cancel.png" class="cancelcon" id="cancel_con_img"></img ></span>
									<img id="wait_con" class="wait" src="images/wait.gif">
								</span>
							<%}%>
						</td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td class = "label_cell">Profession</td>
						<td class = "colon_cell">:</td>
						<td class = "input_cell">
							<span id="sprof">
								<% 
									boolean pflag = false;
									boolean pf = false;
									if(user.getProfession().getProfessionId()!=0){%>
									<%	for(int i=0;i<professions.size();i++){
											Profession profession = (Profession)professions.get(i);%>
										<%													if(user.getProfession().getProfessionId()==profession.getProfessionId()){%>
											<span id='aprof'><%= profession.getProfession()%>
												<% pf = true;%>
											</span>
										<%}%>
									<%}%>
								<%}else{ pflag = true;}%>
								<span id="nprof">
									<select id="profession" <%= pflag?"style='display:-webkit-box'":"style='display:none'"%>>
										<%	for(int i=0;i<professions.size();i++){
											Profession profession = (Profession)professions.get(i);%>
											<% if(user.getProfession().getProfessionId()==profession.getProfessionId()){%>
												<option selected = "selected" value="<%= profession.getProfessionId()%>"><%= profession.getProfession()%></option>
										<%}else{%>
											<option value="<%= profession.getProfessionId()%>"><%= profession.getProfession()%></option>
										<%}}%>
									</select>
								</span>
							</span>
						</td>
						<td>
							<% if(pf){%>
								<span class="sp">
									<span id="edit_prof" class = "edit"><img src = "images/edit.png" class="editprof" id="edit_prof_img"></img ></span>
									&nbsp;&nbsp;
									<span id="save_prof" class= "edit_nodisplay"><img src = "images/save.jpg" class="saveprof" id="save_prof_img"></img ></span>
									&nbsp;&nbsp;
									<span id="cancel_prof" class= "edit_nodisplay"><img src = "images/cancel.png" class="cancelprof" id="cancel_prof_img"></img ></span>
									<img id="wait_prof" class="wait" src="images/wait.gif">
								</span>
							<%}else{%>
								<span class="sp">
									<span id="edit_prof" class = "edit_nodisplay"><img src = "images/edit.png" class="editprof" id="edit_prof_img"></img ></span>
									&nbsp;&nbsp;
									<span id="save_prof" class= "edit"><img src = "images/save.jpg" class="saveprof" id="save_prof_img"></img ></span>
									&nbsp;&nbsp;
									<span id="cancel_prof" class= "edit"><img src = "images/cancel.png" class="cancelprof" id="cancel_prof_img"></img ></span>
									<img id="wait_prof" class="wait" src="images/wait.gif">
								</span>
							<%}%>
						</td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td class = "label_cell">Interest</td>
						<td class = "colon_cell">:</td>
						<td class = "input_cell">
							<span id="sint">
								<% if(!(user.getInterest()==null)){ %>
									<span id = "aint"><%= user.getInterest()%></span>
							</span>
						</td>
						<td>
							<span class="sp">
								<span id="edit_int" class = "edit"><img src = "images/edit.png" class="editint" id="edit_int_img"></img ></span>
								&nbsp;&nbsp;
								<span id="save_int" class= "edit_nodisplay"><img src = "images/save.jpg" class="saveint" id="save_int_img"></img ></span>
								&nbsp;&nbsp;
								<span id="cancel_int" class= "edit_nodisplay"><img src = "images/cancel.png" class="cancelint" id="cancel_int_img"></img ></span>
								<img id="wait_int" class="wait" src="images/wait.gif">
							</span>
						</td>
								<%}else{%>
									<textarea type="textarea" cols="30" rows="5" name="interest" id="interest"></textarea>
							</span>
						</td>
						<td>
							<span class="sp">
								<span id="edit_int" class = "edit_nodisplay"><img src = "images/edit.png" class="editint" id="edit_int_img"></img ></span>
								&nbsp;&nbsp;
								<span id="save_int" class= "edit"><img src = "images/save.jpg" class="saveint" id="save_int_img"></img ></span>
								&nbsp;&nbsp;
								<span id="cancel_int" class= "edit"><img src = "images/cancel.png" class="cancelint" id="cancel_int_img"></img ></span>
								<img id="wait_int" class="wait" src="images/wait.gif">
							</span>
						</td>
								<%}%>
							
					</tr>
				</table>
			</div>
			<div><%@ include file = "footer.jsp" %></div>
		</div>	
	</body>
</html>