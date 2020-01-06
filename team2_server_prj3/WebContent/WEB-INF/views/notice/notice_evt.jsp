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
		
		$("#newWriteBtn").click(function () {
			location.href="noti_form.do";
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
	
	<div class="container" style="min-height: 1000px; ">
	  <div class="row">
	    <div class="col" style="min-width: 1200px">
	    <div style="font-size:28px; margin-left: -15px; margin-top: 50px">&emsp;<strong>공지 및 이벤트</strong></div><br/>
	    
	    <form action="notice_home.do" id="searchFrm" method="get">
		  <div class="row" style="margin-top: 50px">
		    <div class="form-group col-md-2" style="margin-left: 200px">
		      <select id="field" name="field" class="form-control">
		        <option value="n_subject" ${param.field eq 'n_subject'?"selected='selected'":""}>제목</option>
       			<option value="n_date" ${param.field eq 'n_date'?"selected='selected'":""}>입력일</option>
		      </select>
		    </div>
		    <div class="input-group col-md-4">
			  <input type="text" class="form-control" name="keyword" value="${param.keyword }" id="keyword" placeholder="한 글자 이상 입력">
			  <div class="input-group-append" id="searchBtn">
			    <button class="btn btn-outline-secondary" type="button" style="height: 38px">
			    	<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="9" cy="9" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
			    </button>
			  </div>
			</div>
			  <div>
			  	<button type="button" class="btn btn-secondary" id="newWriteBtn" style="height: 40px; width: 130px; margin-left: 150px">새 글쓰기</button>
			  </div>
		  </div>
		</form>
	    
	    </div>
	    <div class="w-100"></div>
	    <div class="col" style="min-width: 1200px; margin-top: 20px ">
	    
	    
	    <table class="table table-striped table-hover empty-space"  style="border: 2px solid #E2E3E5; height: 600px">
		  <thead>
		    <tr>
		      <th scope="col" style="text-align: center;">글 번호</th>
		      <th scope="col" style="text-align: center;">게시글</th>
		      <th scope="col" style="text-align: center;">제목</th>
		      <th scope="col" >등록일</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach var="noti" items="${notiBoardList }">
		    <tr>
		      <td scope="row" style="text-align: center;"><c:out value="${noti.rnum }"/></td>
		      <td style="text-align: center;">
		      <c:choose>
					<c:when test="${noti.ntCode eq 'nt_00000001'}"><c:out value="이벤트"/></c:when>
					<c:when test="${noti.ntCode eq 'nt_00000002'}"><c:out value="공지사항"/></c:when>
			  </c:choose>
		      </td>
		      <td style="text-align: center;"><strong><a href="noti_process.do?nCode=${noti.nCode }"><c:out value="${noti.nSub }"/></a></strong></td>
		      <td><c:out value="${noti.nDate }"/></td>
		    </tr>
		   </c:forEach>
		</table>
	    
	    </div>
	    <div class="w-100"></div>
	    <div class="col" style="min-width: 1200px">
	    
	    <div  style="margin:0px auto; width:250px">
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