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
<!-- summernote 시작 -->
<!-- <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<!-- summernote 끝 -->

<script type="text/javascript">
$(function(){
	$("#sidebar").css("height", $("#fix").css("height"));//div id 다시보기
	   
	   
	   $('#summernote').summernote({
	        placeholder: '관리자 문의사항입니다. 보안에 유의해주시기 바랍니다.',
	        tabsize: 2,
	        height: 660
	      });

$("#writeBtn").click(function() {
	var obj = document.commFrm;
	if(confirm("답글을 작성하시겠습니까?")){
	obj.action="write_qna_comm.do";

	obj.submit();
	}
	
});//click



$("#listBtn").click(function() {
	var flag= "${inputFlag}";
	var url = "notice_home.do";
	if( flag=='H' ){
		url="main_home.do";
	}

	location.replace(url);

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
	 <div class="col">
		<form id="commFrm" name="commFrm" method="post">
		<input type="hidden" id="qCode" name="qCode" value="${searchQnaData.qCode}"/>
		<table id="queryTab" style="margin-top: 20px">
			<tr style="height:60px;">
				<td style="width:100px;" class="tFont">작성자</td>
				<td><input class="form-control" type="text" style="width:400px" readonly="readonly" value="${searchQnaData.nickName}"></td>
				<td style="width:120px;" class="tFont" >&emsp;작성 일자</td>
				<td><input class="form-control" type="text" style="width:378px" readonly="readonly" value="${searchQnaData.qWriteDate}"></td>
			</tr>
			<tr style="height:60px;">
				<td style="width:100px;" class="tFont">제목</td>
				<td colspan="3"><input class="form-control" type="text" style="width:900px" readonly="readonly" value="${searchQnaData.qSubject}"></td>
			</tr>
			<tr style="height:360px;">
				<td></td>
				<td colspan="3"><textarea id="summernote" class="noresize" style="width:900px; height:650px;"readonly="readonly"><c:out value="${searchQnaData.qContent}"/> </textarea></td>
			</tr>
			<tr style="height:110px;">
				<td class="tFont">답글</td>
				<td colspan="3"><textarea id="qnaComm"  class="noresize" name="qnaComm" style="width:900px; height:100px;"></textarea></td>
			</tr>
		</table>
		</form>
		
	</div>
		<div id="queryButtons" style="margin-top: 15px; margin-bottom:20px; margin-left: 480px">
			<button type="button" id="listBtn" class="btn btn-secondary"><c:out value="${inputFlag eq 'H' ? '홈으로': '목록'}"/> </button>
			<button type="button" id="writeBtn" class="btn btn-primary">답글</button>
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