<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yul's Coffee</title>
<style type="text/css">
@import url("http://localhost:8080/team2_server_prj3/common/css/main.css");
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
	   
	   $("#qna-table").click(function () {
			
		   var id = "";
		   var tdArr = new Array(); //배열 선언
			
		   //현재 클릭된 Row(<tr>)
		   var tr = $(this);
		   var td = tr.children();
			
			
			
	   });//click	
	   
	   $("#searchQnaBtn").click(function(){
		  
		   $("#searchQnaFrm").submit();
		   
	   });
	   

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
	    <div class="col" style="min-width: 1200px">
	    
	    <form id="searchQnaFrm" action="qna_home.do" method="post">
		  <div class="row" style="margin-top: 50px">
		    <div class="form-group col-md-2" style="margin-left: 200px">
		      <select id="inputState" name="field" class="form-control">
		        <option value="n" selected>카테고리</option>
		        <option id="phone" value="q_code">글번호</option>
		        <option id="name"  value="m_id">ID</option>
		      </select>
		    </div>
		    <div class="input-group col-md-4">
			  <input type="text" name="keyword" class="form-control" placeholder=""/>
			  <div class="input-group-append" id="button-addon4">
			    <button class="btn btn-outline-secondary" id="searchQnaBtn" type="button" style="height: 38px">
			    	<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="9" cy="9" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
			    </button>
			  </div>
			</div>
		  </div>
		</form>
	    
	    </div>
	    <div class="w-100"></div>
	    <div class="col" style="min-width: 1200px">
	    
	    <table class="table table-striped empty-space" id=qna-table   style="border: 2px solid #E2E3E5">
		  <thead>
		    <tr>
		      <th scope="col">글 번호</th>
		      <th scope="col">작성자ID</th>		      
		      <th scope="col">제목</th>
		      <th scope="col">문의등록일</th>
		      <th scope="col">답변작성일자</th>
		      <th scope="col">답변유무</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach var="qnaVo" items="${searchQnaList}">
		  	<tr>
		  		<th style="text-align: center;"><c:out value="${qnaVo.rnum}"/></th>
		  		<td><c:out value="${qnaVo.m_id }"/></td>
		  		<td><a href="qna_detail.do?code=${qnaVo.q_code}"><c:out value="${qnaVo.q_subject }"/></a></td>
		  		<td><c:out value="${qnaVo.q_write_date }"/></td>
		  		<td><c:out value="${qnaVo.q_comm_date }"/></td>
		  		<td><label style=" ${qnaVo.q_comm_flag eq 'Y' ? 'color: red;':'color: blue;' }"><c:out value="${qnaVo.q_comm_flag eq 'Y' ? '답변 완료':'답변 준비' }"/></label></td>
		  	</tr>
		  </c:forEach>		  
		  </tbody>  
		</table>
 </div>
		
	    <div class="w-100"></div>
	    <div class="col" style="min-width: 1200px">
	    
	    <div style="margin:0px auto; width:250px">
			<nav aria-label="Page navigation example">
			  <ul class="pagination">
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