<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 query</title>
<style type="text/css">
@import url("http://localhost:8080/team2_server_prj3/common/css/main.css");
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Staatliches&display=swap" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>

<script type="text/javascript">
$(function(){
	   $("#sidebar").css("height", $("#fix").css("height"));//div id 다시보기
	   
	   
	   $('#summernote').summernote({ 
	        tabsize: 2,
	        height: 300
	      });
	   
	 	$("#writeBtn").click(function(){
	 		$("#qnaDetailFrm").submit();
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
	    <div class="col">
		<form id="qnaDetailFrm" action="updateQnaAdmin.do" method="post">
		<table id="queryTab" style="margin-top: 20px">
			<tr style="height:60px;">
				<td style="width:100px;" class="tFont">작성자</td>
				<td><input class="form-control" type="text" style="width:400px" readonly="readonly" value="${qnaDetail.m_id}"></td>
				<td style="width:120px;" class="tFont">&emsp;작성 일자</td>
				<td><input class="form-control" type="text" style="width:378px" readonly="readonly" value="${qnaDetail.q_write_date}"></td>
			</tr>
			<tr style="height:60px;">
				<td style="width:100px;" class="tFont">제목</td>
				<td colspan="3"><input class="form-control" type="text" readonly="readonly" style="width:900px" value="${qnaDetail.q_subject}"></td>
			</tr>
			<tr style="height:360px;">
				<td></td>
				<td colspan="3"><textarea id="summernote" style="width:900px; height:650px;" ><c:out value="${qnaDetail.q_content}"/> </textarea></td>
			</tr>
			<tr style="height:110px;">
				<td class="tFont">답글</td>
				<td colspan="3"><textarea style="width:900px; height:100px;" name="q_answer" id="answer"><c:out value="${qnaDetail.q_answer}"/> </textarea></td>
			</tr>
		</table>
		<input type="hidden" name="q_code" value="${param.code}"/>
		</form>
	</div>
		
		<div id="queryButtons" style="margin-top: 15px; margin-bottom:20px; margin-left: 450px">
			<button type="button" id="writeBtn" class="btn btn-secondary">답글/수정</button>
			<a href="qna_delete.do?code=${param.code}"><button type="button" id="deleteBtn" class="btn btn-secondary">삭제</button></a>
			<a href="qna_home.do"><button type="button" id="listBtn" class="btn btn-secondary">목록</button></a>
		</div>
		
	  </div>
	 </div>
	 
    </div>
		

<div id="mainFooter">

</div>

</div> <!-- queryWrap -->

<div id="mainFooter">

</div>

</body>
</html>