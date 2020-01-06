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
	   
	$("#mDeleteBtn").click(function () {
		var obj = document.memberInfofrm;
		obj.action = "remove_member.do";
		obj.submit(); 
	});//click
	
	$("#mDeleteBtn").click(function(){
		if(confirm("회원의 정보를 삭제하시겠습니까?")){
			$("#memberInfofrm").submit();
		}//end if
		
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
	    
	    	<form action="remove_member.do" name="memberInfofrm" id="memberInfofrm" method="post">
				<div class="col-md-8 order-md-1" style="margin-top: 50px; margin-left: 50px">
				      <h2 class="mb-3" style="font-weight: bold;">회원 정보</h2>
				        <div class="form-row">
				          <div class="col" >
				          <label for="nick">이름</label>
				            <input type="text" class="form-control" id="mName" name="mName" readonly="readonly" value="${memberInfo.mName }">
				          </div>
				          <div class="col">
				           <label for="nick">성별</label>
				           <c:choose>
								<c:when test="${memberInfo.mGender eq 'F' }">
								<input type="text" class="form-control" id="mGender" name="mGender" readonly="readonly" value="여자">
								</c:when>
								<c:when test="${memberInfo.mGender eq 'M' }">
								<input type="text" class="form-control" id="mGender" name="mGender" readonly="readonly" value="남자">
								</c:when>
							</c:choose>
				            
				          </div>
				        </div>
				
				        <div class="form-row">
				          <div class="col" >
				          <label for="nick">닉네임</label>
				            <input type="text" class="form-control" id="mNick" name="mNick" readonly="readonly" value="${memberInfo.mNick }">
				          </div>
				          <div class="col">
				           <label for="nick">ID</label>
				            <input type="text" class="form-control" id="mId" name="mId" readonly="readonly" value="${memberInfo.mId }">
				          </div>
				        </div>
				
				        <div class="mb-3" style="margin-top: 10px">
				          <label for="email">Email</label>
				          <input type="email" class="form-control" id="mEmail" name="mEmail" readonly="readonly" value="${memberInfo.mEmail }">
				        </div>
				
				        <div class="form-row">
				        	<div class="col" >
					          <label for="phonenum">휴대전화</label>
					          <input type="text" class="form-control" id="mPhone" name="mPhone" readonly="readonly" value="${memberInfo.mPhone }">
				          	</div>
				        </div>
		
				        <div class="form-row" style="margin-top: 10px">
				        	<div class="col" >
					          <label for="birth">생년월일</label>
					          <input type="text" class="form-control" id="mBirth" name="mBirth" readonly="readonly" value="${memberInfo.mBirth }">
				          	</div>
				        </div>
					<button type="button" class="btn btn-secondary" id="mDeleteBtn" name="mDeleteBtn" style="float: right; margin-top: 20px">회원 삭제</button>
				</div>
			</form>
			<form action="">
					<div class="col-md-8 order-md-1" style="margin-top: 100px; margin-left: 50px; margin-bottom: 30px">
					      <h2 class="mb-3" style="font-weight: bold;">활동 정보</h2>
					        <div class="form-row">
					        	<div class="col" >
						          <label for="phonenum">가입일</label>
						          <input type="text" class="form-control" id="mDate" name="mDate" readonly="readonly" value="${memberInfo.mDate }">
					          	</div>
					        </div>
			
					        <div class="form-row" style="margin-top: 10px">
					        	<div class="col" >
						          <label for="birth">최종 로그인 IP</label>
						          <input type="text" class="form-control" id="mIp" name="mIp" readonly="readonly" value="${memberInfo.mIp }">
					          	</div>
					        </div>
					</div>
				</form>
	    
	  		</div>
			<div class="col">
	
				<form action="">
					<div class="col-md-8 order-md-1"
						style="margin-top: 50px; margin-left: 0px">
						<h2 class="mb-3" style="font-weight: bold;">주문 내역</h2>
						<table class="table table-bordered" style="width: 500px">
							<tr>
								<th scope="col" style="width: 150px">일자</th>
								<th scope="col" style="width: 350px">주문내역</th>
							</tr>
						</table>
						<div style="overflow-x:hidden; width:500px; height:250px;">
						<table class="table table-bordered" style="width: 500px">
						<c:forEach var="mOrder" items="${memberOrder }">
							<tr>
								<td style="width: 150px"><c:out value="${mOrder.orderDate }"/></td>
								<td style="width: 350px"><c:out value="${mOrder.gdName }"/> (<c:out value="${mOrder.cQuantity }"/>잔)<br/></td>
							</tr>
						</c:forEach>								
						</table>
						</div>
					</div>
				</form>
				
				<form action="">
					<div class="col-md-8 order-md-1"style="margin-top: 50px; margin-left: 0px">
						<h2 class="mb-3" style="font-weight: bold;">포인트</h2>
						<h5 class="mb-3" style="font-weight: bold;">보유 레몬 : <c:out value="${mPoint }"/>개</h5>
						<table class="table table-bordered" style="width: 500px">
							<tr>
								<th scope="col" style="width: 150px">일자</th>
								<th scope="col" style="width: 200px">사유</th>
							</tr>
						</table>
						<div style="overflow-x:hidden; width:500px; height:250px;">
						<table class="table table-bordered" style="width: 500px;">
						<c:forEach var="mPoint" items="${memberPoint }">
							<tr>
								<td style="width: 150px">
								<c:choose>
									<c:when test="${mPoint.lemonStatus eq 'N' }"><c:out value="${mPoint.insDate }"/></c:when>
									<c:when test="${mPoint.lemonStatus eq 'Y' }"><c:out value="${mPoint.useDate }"/></c:when>
								</c:choose>
								</td>
								<td style="width: 200px">
								<c:choose>
									<c:when test="${mPoint.lemonStatus eq 'N' }"><font color="blue">레몬 적립</font></c:when>
									<c:when test="${mPoint.lemonStatus eq 'Y' }"><font color="red">레몬 사용</font></c:when>
								</c:choose>
								</td>
							</tr>
						</c:forEach>								
						</table>
						</div>

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
