<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
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
	   
	$("#wFin").click(function () {
		 if($("#noticeWriteSub").val().trim() == ""){
	         alert("제목을 입력해주세요.");
	         $("#noticeWriteSub").focus();
	         return;
	      }//end if
		 if($("#summernote").val().trim() == ""){
	         alert("내용을 입력해주세요.");
	         $("#summernote").focus();
	         return;
	      }//end if
		if(confirm("작성을 완료하기 전 규정에 맞게 글을 썼는지 다시 한번 확인해 주시기 바랍니다.")){
			$("#noticeWriteFrm").submit();
		}//end if
		
	});
	
	$("#returnBtn").click(function () {
		location.href="notice_home.do"; 
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
	    
	    <form id="noticeWriteFrm" name="noticeWriteFrm" method="post" action="noti_write_process.do">
	    <table id="queryTab" style="margin-top: 20px">
			<tr style="height:60px; ">
				<td style="width:100px;" class="tFont">타입</td>
				<td style="width:200px;">
				<select id="noticeWriteType" name="noticeWriteType" class="form-control">
			        <option value="E" ${param.nt_code eq 'noti_evt'?"selected='selected'":""}>이벤트</option>
			        <option value="N" ${param.nt_code eq 'noti_info'?"selected='selected'":""}>공지사항</option>
		      	</select>
		      	</td>
			</tr>
			<tr style="height:60px;">
				<td style="width:100px;" class="tFont">제목</td>
				<td colspan="3"><input class="form-control" id="noticeWriteSub" name="noticeWriteSub" type="text" style="width:900px"></td>
			</tr>
			<tr style="height:360px;">
				<td></td>
				<td colspan="3"><textarea id="summernote" class="noresize"  name="noticeWriteContent" style="width:900px; height:650px;"></textarea></td>
			</tr>
		</table>
		
		<div id="queryButtons">
			<button type="button" class="btn btn-secondary" id="wFin" style="float: right; margin-top: 10px; margin-right: 100px">작성완료</button>
			<button type="button" class="btn btn-secondary" id="returnBtn" style="float: right; margin-top: 10px; margin-right: 10px">목록으로</button>
		</div>
		</form>
	    
	    </div>
	  </div>
	</div>
	
	</div>
		
<div id="mainFooter">

</div>

</div>
</body>
</html>