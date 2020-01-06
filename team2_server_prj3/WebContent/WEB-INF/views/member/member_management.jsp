<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yul's Coffee</title>
<style type="text/css">
@import url("http://localhost:8080/team2_server_prj3/common/css/main.css");
td a:link{color : black; text-decoration: none;}
td a:visited {color: gray; text-decoration: none;}
td a:hover {color:#E87F4D;}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Staatliches&display=swap" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
 $(function(){
	$("#sidebar").css("height", $("#fix").css("height"));//div id 다시보기
	
	 $("#searchBtn").click(function () {
	      if($("#keyword").val().trim() == ""){
	         alert("한 글자 이상의 검색어가 필요합니다.");
	         $("#keyword").focus();
	         return;
	      }//end if
	      
	      $("#searchFrm").submit();
	});//click	
});
</script>
</head>
<body>
<div id="wrap">
	<div id="header">
		<jsp:include page="../overlap/header.jsp"/>
	</div>
	
	<div id="sidebar">
          <jsp:include page="../overlap/sidebar.jsp"/>
	</div>
	
	<div id="fix">
	
	<div class="container" style="min-height: 1000px">
	  <div class="row">
	    <div class="col" style=" min-width: 1200px">
	    <div style="font-size:28px; margin-left: -15px; margin-top: 50px">&emsp;<strong>회원관리</strong></div><br/>
	    
	    	<div class="col">
	    	
	    	<form action="member_home.do" id="searchFrm">
			  <div class="row" style="margin-top: 50px">
			    <div class="form-group col-md-2" style="margin-left: 575px">
			    <!-- <input type="hidden" name="cmd" value="board_list"/> -->
			      <select id="field" name="field" class="form-control">
			        <option value="m_id" ${param.field eq 'm_id'?"selected='selected'":""}>아이디</option>
        			<option value="m_name" ${param.field eq 'm_name'?"selected='selected'":""}>이름</option>
        			<option value="m_nick" ${param.field eq 'm_nick'?"selected='selected'":""}>닉네임</option>
        			<option value="m_phone" ${param.field eq 'm_phone'?"selected='selected'":""}>전화번호</option>
			      </select>
			    </div>
			    <div class="input-group col-md-4">
				  <input type="text" class="form-control" name="keyword" value="${param.keyword}" id="keyword" placeholder="한 글자 이상 입력">
				  <div class="input-group-append" id="searchBtn">
				    <button class="btn btn-outline-secondary" type="button" style="height: 38px">
				    	<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="9" cy="9" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
				    </button>
				  </div>
				</div>
			  </div>
			</form>
	    	
	    	</div>
	    	<div class="col"></div>
	    </div>
	    <div class="w-100"></div>
	    <div class="col" style="min-width: 1200px">
	    
	    <table class="table table-striped table-hover empty-space" id="member-table" style="border: 2px solid #E2E3E5; height: 600px">
		  <thead>
		    <tr>
		      <th scope="col">번호</th>
		      <th scope="col">이름</th>
		      <th scope="col">ID</th>
		      <th scope="col">닉네임</th>
		      <th scope="col">이메일</th>
		      <th scope="col">전화번호</th>
		      <th scope="col">최근 접속시간</th>
		      <th scope="col">회원 가입일</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach var="mblVo" items="${memBoardList }">
		    <tr>
		      <td scope="row"><c:out value="${mblVo.rnum }"/></td>
		      <td><c:out value="${mblVo.mName }"/></td>
		      <td><strong><a href="member_process.do?mId=${mblVo.mId }"><c:out value="${mblVo.mId }"/></a></strong></td>
		      <td><c:out value="${mblVo.mNick }"/></td>
		      <td><c:out value="${mblVo.mEmail }"/></td>
		      <td><c:out value="${mblVo.mPhone }"/></td>
		      <td><c:out value="${mblVo.mReConn }"/></td>
		      <td><c:out value="${mblVo.mMemberShipDate }"/></td>
		    </tr>
		   </c:forEach>
		  </tbody>
		</table>
	    
	    </div>
	    <div class="w-100"></div>
	    <div class="col" style="min-width: 1200px">
	    
	    <div style="margin:0px auto; width:250px">
			<nav aria-label="Page navigation example">
			  <ul class="pagination" style="justify-content: center;">
				<c:out value="${indexList}" escapeXml="false"/>
			  </ul>
			</nav>
		</div>
	    
	    </div>
	  </div>
	</div>
	
	</div>
		
<div id="mainFooter">

</div>

</div>
</body>
</html>