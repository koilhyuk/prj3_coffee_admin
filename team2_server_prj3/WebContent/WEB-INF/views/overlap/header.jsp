<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info=""
%>

<%
	String temp = (String)session.getAttribute("id");
	%>
	<nav class="navbar navbar-light" style="background-color: #333C50;">
		
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" 
		<%if(temp!=null){  %>
		href="main_home.do"
		<%} %>
		 style="color: #D5CEC6; font-size: 30px;">
		<img src="http://localhost:8080/team2_server_prj3/common/images/logo.png" style="width: 40px; height: 45px; padding-bottom: 5px">
			Yul's COFFEE
		</a>
		
		<%
	
		if(temp!=null){ %>
			<div class="nav-item" id="logOut">
	        <span data-toggle="tooltip" data-placement="bottom"  title="로그아웃" >
      		 <a class="nav-link" href="logout.do" style="color: #A9A19B;">
	       		<img src="http://localhost:8080/final_prj/common/images/login.png" style="width: 30px" />
        	 </a>
	        </span>
      	</div>
      	<%} %>
	</nav>
