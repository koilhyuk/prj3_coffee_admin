<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="관리자 로그인 페이지"
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yul's Coffee</title>
<style type="text/css">
	@font-face { font-family: 'Eoe_Zno_EB'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_eight@1.0/Eoe_Zno_EB.woff') format('woff'); font-weight: normal; font-style: normal; }
	@font-face { font-family: 'GoyangIlsan'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/GoyangIlsan.woff') format('woff'); font-weight: bold; font-style: normal; }
	/* margin: 0px auto - 창 크기가 줄어들거나 커져도 항상 가운데를 유지함 */
	#wrap{ min-height: 100vh; margin: 0px auto; background-color: #D5CEC6;}/* 디자인이 완료되었을 때 wrap에 깔린 바탕색이 보이면 안됨 */
	#header{ height: 70px; font-family: 'Staatliches', cursive;  color:#F0F0E2 }/* width:800px; - wrap안에 감싸져있어서 width는 안줘도 되는데 헷갈릴 수 있어서 다들 줌 */
	#container{ height: 1030px; background-color: #D5CEC6}


/* 컨테이너 */
	#login{ min-height:350px;min-width:500px;margin-left: auto; margin-right: auto;margin-top:15%; width: 300px; 
		border: 1px solid white; background-color: white; border-radius: 5px;  background-color:#FFFFFF}
	#loginImg{ margin-left:auto;margin-right:auto; margin-bottom:10%;width: 100px; }
	.form-control-lg{
	width: 300px; 
	margin-bottom: 5%;
	}
	#loginBtn{ width: 300px; background-color: #494342; margin-bottom: 5% ;color: #F0F0E2}
	#loginBtn:hover{ background-color: #A9A19B; color: #141A1D}
	#loginFrm{min-height:300px;min-width:500px;margin-left: auto; margin-right: auto;margin-top:15%; width: 300px; 
		border: 1px solid white; background-color: white; border-radius: 5px;}
	
/* 컨테이너 */

#idPassCon,#idCon,#passCon{color:#FF0000; font-weight: bold; display: none;}

</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Staatliches&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function() {
		
		/*툴팁  */
		$('[data-toggle="tooltip"]').tooltip();
		
		$("#adminPass").keypress(function(key) {
			if(key.which==13){//키가 13이면 실행 (엔터는 13)
	        	$("#loginBtn").click();
	        }
		});
		
		
		$("#loginBtn").click(function() {
			if($("#adminId").val()=="") {
				$("#idCon").show().fadeOut(5000);
				$("#adminId").focus();
				return;
			}//if
			
			if($("#adminPass").val()=="") {
				$("#passCon").show().fadeOut(5000);
				$("#adminPass").focus();
				return;
			}//if
			
			$.ajax({				
			//폼 내부의 데이터를 전송할 주소
			url:"admin_loginChk.do",
			type:"post",
			data:"adminId="+$("#adminId").val()+"&adminPass="+$("#adminPass").val(),
			dataType:"json",
			success: function (json) {
				if(json.login_flag) {
					$("#loginFrm").submit();
				}else{
					$("#idPassCon").show().fadeOut(3000);
				}
			},
			error: function(xhr) {
				alert("문제가 발생했습니다. 다시 로그인 해주십시오.");
			}
			});
		});
	});
</script>
</head>
<body>
<div id="wrap">
<div id="header">
	<jsp:include page="../overlap/header.jsp"/>
</div>
<div id="container">
<div class="form-group form">
<div id="loginfrm">
<form id="loginFrm" action="main_homeOk.do" method="post">
		<div style="margin: 20%">
		<div id="loginImg">
		<img src="http://localhost:8080/team2_server_prj3/common/images/server_login.png" style="width: 100px">
		</div>
		
		<input class="form-control form-control-lg" type="text" placeholder="아이디" id="adminId" name="adminId">
		<input class="form-control form-control-lg" type="password" placeholder="비밀번호" id="adminPass" name="adminPass">
		<p class="text-right" id="idCon">아이디를 입력하세요.</p><!-- 아이디가 없을경우 -->
        <p class="text-right" id="passCon">비밀번호를 입력하세요.</p><!-- 비밀번호가 없을경우 -->
        <p class="text-right" id="idPassCon">아이디 또는 비밀번호를 확인해주세요</p><!-- 비밀번호가 없을경우 -->
		<button type="button" class="btn btn-light" id="loginBtn">관리자 로그인</button>
	</div>
</form>
</div>
</div>
</div>

</div>

</body>
</html>