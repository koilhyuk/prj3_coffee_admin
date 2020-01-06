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
	        placeholder: '관리자 게시판입니다. 보안에 유의해주시기 바랍니다.',
	        tabsize: 2,
	        height: 660
	   });
		   

	 
 $("#noticeWriteComBtn").click(function() {
	 var obj = document.noticeHomeWriteFrm;
	 var flag= "${noticeWriteFlag}";
	 var comment ="${selectNoticeDetData.ntType eq 'E' ? '이벤트':'공지사항'}";
	 
	 
	 if(flag== 'M'){// ?'수정':'
	 obj.action="notice_home_modify.do";
		 if(confirm("현재"+comment+"를 수정하시겠습니까?")){
			 obj.submit();
		 }
		 
	 }else{// 새로 작성 '작성
	 obj.action="notice_home_new_write.do";
		 if(confirm("현재"+comment+"를 작성하시겠습니까?")){
			 obj.submit();
		 }
	 }
 	
 });//click
 
	 
 $("#noticeHomeListBtn").click(function() {
	 location.replace("main_home.do");
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
	    <form method="post" id="noticeHomeWriteFrm" name="noticeHomeWriteFrm">
	    <input type="hidden" id="noticeCode" name="noticeCode" value="${ selectNoticeDetData.nCode}">
	    <table id="queryTab" style="margin-top: 20px">
			
			<tr style="height:60px; ">
				<td style="width:100px;" class="tFont">타입</td>
				<td>
				<select id="noticeDataType" name="noticeDataType" class="form-control" style="width: 150px">
					<option value="E" ${selectNoticeDetData.ntType eq 'E' ? 'selected="selected"':''}>이벤트</option>
					<option value="N" ${selectNoticeDetData.ntType eq 'N' ? 'selected="selected"':''}>공지사항</option>
				</select>
				</td>
				<c:if test="${noticeWriteFlag eq 'M'}">
					<td style="width:100px;" class="tFont">작성일</td>
					<td><input class="form-control" type="text" style="width:400px" value="${ selectNoticeDetData.nDate}" readonly="readonly" ></td>
				</c:if>
			</tr>
			<tr style="height:60px;">
				<td style="width:100px;" class="tFont">제목</td>
				<td colspan="3"><input class="form-control" type="text" style="width:900px" id="noticeHomeSubject" name="noticeHomeSubject" value="${ selectNoticeDetData.nSubject}" ></td>
			</tr>
			<tr style="height:360px;">
				<td></td>
				<td colspan="3"><textarea id="summernote" class="noresize"  name="noticeHomeContent"  style="width:900px; height:650px;"><c:out value="${selectNoticeDetData.nContent }"/> </textarea></td>
			</tr>
		</table>
	    </form>
		
		<div id="queryButtons">
			<button type="button" id="noticeWriteComBtn" name="noticeWriteComBtn" class="btn btn-primary" style="float: right; margin-top: 10px; margin-right: 100px"><c:out value="${noticeWriteFlag eq 'M' ?'수정':'작성'}"/>완료</button>
			<button type="button" id="noticeHomeListBtn" class="btn btn-secondary" style="float: right; margin-top: 10px; margin-right: 10px">홈으로</button>
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