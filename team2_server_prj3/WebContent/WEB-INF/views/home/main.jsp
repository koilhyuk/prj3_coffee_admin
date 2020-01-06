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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(function(){
	 $("#sidebar").css("height", $("#fix").css("height"));//div id 다시보기
	 
$("#homeQnaTable tbody tr").click(function() {
	var tr = $(this);
	var td = tr.children();
	var code = td.eq(1).text();

	var form = document.createElement('form');
	form.setAttribute('method', 'post');
	form.setAttribute('action', "qna_home_detail.do");
	document.charset = "utf-8";
	var hiddenField = document.createElement('input');
	hiddenField.setAttribute('type', 'hidden');
	hiddenField.setAttribute('name', 'qCode');
	hiddenField.setAttribute('value', code);
	form.appendChild(hiddenField);
	
	document.body.appendChild(form);
	form.submit();


});//click

$("#homeNoticeTable tbody tr").click(function() {
	var tr = $(this);
	var td = tr.children();
	var code = td.eq(1).text();
	var form = document.createElement('form');
	form.setAttribute('method', 'post');
	form.setAttribute('action', "notice_write.do");
	document.charset = "utf-8";
	var hiddenField = document.createElement('input');
	hiddenField.setAttribute('type', 'hidden');
	hiddenField.setAttribute('name', 'nCode');
	hiddenField.setAttribute('value', code);
	form.appendChild(hiddenField);
	
	document.body.appendChild(form);
	form.submit();


});//click
	 
});//ready
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
	
	<div class="container" style="min-height: 1200px">
	  <div class="row">
	    <div class="col" >
	    <!-- 판매진행현황 -->
		    <div style="min-width: 530px ;min-height:230px; float: left; border-right: 5px solid #E2E3E5;margin-top: 20px;">
		    	<h4 class="h4 empty-space"   >판매 진행 현황</h4>
			<div id="saleImg" style="margin-top: 20px">
				<div class="rounded-circle"  style="margin-left: 100px; background-image: url('http://localhost:8080/team2_server_prj3/common/images/r_color2.png'); width: 150px ;height:150px; float:left;">
					<h3 class="h" style="color: blue;">주문준비</h3>
					<h3 class="h3" ><c:out value="${homeOrderCntData.preCnt}"/> 건</h3>
				</div>
				<div class="rounded-circle"  style=" margin-left: 50px; background-image: url('http://localhost:8080/team2_server_prj3/common/images/r_color2.png'); width: 150px ;height:150px; float:left;">
					<h3 class="h" style="color: red;">수령완료</h3> 
					<h3 class="h3"><c:out value="${homeOrderCntData.comCnt}"/> 건</h3> 
				</div>
		    </div>
		    </div>
		    <!-- 판매실적 -->
	    	<div class="col" style="max-width: 570px ; float: left;margin-top:20px" >
	    		<h4 class="h4 empty-space"  >판매 실적 & 판매 현황</h4>
			<div style=" text-align: center;">
			<table  style=" margin-left: auto;margin-right:auto;border: 3px solid #343A40; margin-top: 30px; width: 400px; height: 130px">
				<tr >
					<th class="sellT">TODAY</th>
					<th class="sellT">MONTH</th>
				</tr>
				<tr>
					<td class="sellT sellC" ><c:out value="${homeSellData.daySell}"/>건</td>
					<td class="sellT sellC"><c:out value="${homeSellData.monthSell}"/>건</td>
				</tr>
			</table>
			</div>
	       </div>
	       
	    <div class="w-100"></div>
	    <div class="col">	
	    
	    <div class="empty-space" style="clear: both;padding-top: 30px">
			<h1 class="h2" style="margin-top: 30px">문의사항(미답변)
			</h1>
		<table class="table table-striped table-hover" style="border: 2px solid #E2E3E5; height: 340px" id="homeQnaTable">
		  <thead>
		    <tr>
		      <th scope="col" style="text-align: center; ">번호</th>
		      <th scope="col" style="text-align: center; ">코드</th>
		      <th scope="col" style="text-align: center; ">작성자</th>
		      <th scope="col" style="text-align: center; ">제목</th>
		      <th scope="col" style="text-align: center; ">작성일</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach var="qnaData" items="${homeQnaData }">
		    <tr>
		      <th style="text-align: center; "><c:out value="${qnaData.rnum}"/></th>
		      <td style="text-align: center; "><c:out value="${qnaData.qcode}"/></td>
		      <td style="text-align: center; "><c:out value="${qnaData.nickName}"/></td>
		      <td style="text-align: center; "><c:out value="${qnaData.subject}"/></td>
		      <td style="text-align: center; "><c:out value="${qnaData.writeDate}"/></td>
		    </tr>
		  </c:forEach>

		  </tbody>
		</table>
		</div>
	    
	    </div>
	    <div class="w-100"></div>
	    <div class="col">
	    
	    <div class="empty-space">
		<h1 class="h2" style="margin-top: 30px">공지사항 & 이벤트
			<span style="float: right; margin-bottom: 10px">
				<button type="button" class="btn btn-primary" style="float: right; margin-top: 20px" onclick="location.href='notice_write.do'">작성하기</button>
			</span>
		</h1>
		<table id="homeNoticeTable" style="border: 2px solid #E2E3E5; height: 340px" class="table table-striped table-hover">
		  <thead>
		    <tr>
		      <th scope="col" style="text-align: center; ">번호</th>
		      <th scope="col" style="text-align: center; ">코드</th>
		      <th scope="col" style="text-align: center; ">종류</th>
		      <th scope="col" style="text-align: center; ">제목</th>
		      <th scope="col" style="text-align: center; ">작성일</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach var="noticeData" items="${homeNoticeData}">
		    <tr>
		      <th style="text-align: center; "><c:out value="${noticeData.rnum}"/></th>
		      <td style="text-align: center; "><c:out value="${noticeData.nCode}"/></td>
		      <td style="text-align: center; "><c:out value="${noticeData.nType eq 'E' ? '이벤트':'공지사항'}"/></td>
		      <td style="text-align: center; "><c:out value="${noticeData.nSubject}"/></td>
		      <td style="text-align: center; "><c:out value="${noticeData.nDate}"/></td>
		    </tr>
		  </c:forEach>
		  </tbody>
		</table>
		</div>
	    
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