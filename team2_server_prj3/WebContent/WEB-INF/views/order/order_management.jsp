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

<style type="text/css">
.TABLE{border-collapse:collapse; width:100%}
.TABLE thead{float:left; width:800px;}
.TABLE tbody{overflow-y:auto; overflow-x:hidden; float:left; width:800px; height:670px}
.TABLE tbody tr{display:table; width:800px;}

.TABLECalc{border-collapse:collapse; width:100%}
.TABLECalc thead{float:left; width:600px;}
.TABLECalc tbody{overflow-y:auto; overflow-x:hidden; float:left; width:600px; height:670px}
.TABLECalc tbody tr{display:table; width:600px;}
.ui-datepicker-trigger{width: 35px}

table tbody tr:hover {
	background-color: #ECECEC;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">



<script type="text/javascript">
 $(function(){
	 $("#sidebar").css("height", $("#fix").css("height"));//div id 다시보기
	 $("#selectStatus").change(function() {
		 searchCommData();
			$("#statusChangeAllChk").prop("disabled", false);
	
	    	  if($("#selectStatus").val().trim()=='A'||$("#selectStatus").val().trim()=='C'){
	    		  $("#statusChangeAllChk").attr("disabled", true);
	    	  }
		});// change

		$("#searchInput").keypress(function(e) {
			if (e.which == 13) {
				searchCommData();
			}
		});

		$("#inputSearchBtn").click(function() {
			searchCommData();
		});// change
	 
		function searchCommData() {
			$( '#orderDataTab > tbody').empty();
			var searchInput = $("#searchInput").val();
			if (typeof searchInput == "undefined") {// 비어 있다면
				searchInput = "";
			}// end if
			var param = "selectStatus=" + encodeURI($("#selectStatus").val().trim())
					+ "&searchInput=" + encodeURI(searchInput);

			$.ajax({
				url : "order_ajax_search.do",
				type : "get",
				data : param,
				dataType : "json",
				error : function(xhr) {
					alert("에러코드 : " + xhr.status);
					alert("에러메세지 : " + xhr.statusText);
				},
				success : function(searchOrderData) {
					var output = "";
					var searchLen = Object.keys(searchOrderData).length;
					if (searchLen == 0) {
						output += "<tr><td colspan='8' align='center'>조회 주문이 존재하지 않습니다.</td></tr>";
					} else {
						$.each(searchOrderData, function(i, jsonObject) {
							output += "<tr>";
							output +=   "<th scope='row' class='text-center'><input type='checkbox' style='width:20px; height:18px;'  name='statusChangeChk' value='"+i+"'";
							if(jsonObject.oStatus=='C'){
								output += "  disabled='disabled' ";
							}// end if
							 
							output +="></th>";
							output +=   "<th scope='col' width='50' style='text-align: center'>"+jsonObject.rnum;
							output+= "<input type='hidden' id='oCode' name='oCode' value='"+jsonObject.oCode+"'/>"
							output+="</th>";
							output +=     "<td scope='col' width='200' style='text-align: center'>"+jsonObject.gdName+"</td>";
							
							if(jsonObject.oStatus=='C'){// 완료
								output +=     "<th scope='col' width='60' style='text-align: center;  color: red;'>"+jsonObject.oStatus+"</th>";
							}else{
								output +=     "<th scope='col' width='60' style='text-align: center;  color: blue;'>"+jsonObject.oStatus+"</th>";
							}
							
							output +=    "<td scope='col' width='60' style='text-align: center'>"+jsonObject.cQuantity+"</td>";
							output +=    "<td scope='col' width='100' style='text-align: center'>"+jsonObject.cTotalPrice+"</td>";
						    output +=   "<td scope='col' width='100' style='text-align: center'>"+jsonObject.mId+"</td>";
						    output +=   "<td scope='col' width='150' style='text-align: center'>"+jsonObject.mPhone+"</td>";
						    output +=   "<td scope='col' width='150' style='text-align: center'>"+jsonObject.cDate+"</td>"; 
							output += "</tr>";
						})// end each
					}// end else 
					$("#orderDataTab tbody").html(output);
				}// success
			}); // ajax
		}//searchCommData
      
      $("#statusChangeAllChk").click(function() {
    	        
    	   	var tr = $("#orderDataTab tbody tr");
    	  	var td = tr.children();
 			var status = td.eq(3).text().trim();   	  
    	        //클릭되었으면
	    	        if($("#statusChangeAllChk").prop("checked")){
		    	     	   if(status!='P'){
			    	            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
			    	            $("input[name=statusChangeChk]").prop("checked",false);
			    	            //클릭이 안되있으면
		    	        	}else{
		    	        		$("input[name=statusChangeChk]").prop("checked",true);
		    	        	}// end else
	    	        }else{
		    	        if(status!='P'){
			    	          //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
			    	          $("input[name=statusChangeChk]").prop("checked",false);
		    	        }else{
		    	        	$("input[name=statusChangeChk]").prop("checked",false);
		    	        }// end else
    	        	}//end else
      });// click
      
      $("#statusChangeBtn").click(function() {
      		
    	codeO = new Array();
         var j = 0;
    	  for(var i = 0 ; i<$("#orderDataTab tbody tr").size(); i++){
    		  var chk = $("#orderDataTab tbody tr").eq(i).children().find("input[type='checkbox']").is(':checked');
    		  if(chk==true){
    			 codeO[j] = $("#orderDataTab tbody tr").eq(i).find("input[type='hidden']").val();
    		 	j++;
    		  }
    	  }// end for
    	  
    	  if(codeO.length==0){
    		  alert("선택하신 주문이 없습니다.");
    		  return;
    	  }
    	  var param = "codeO=" + codeO;
    		$.ajax({
				url : "order_ajax_modify.do",
				type : "post",
				data : param,
				dataType : "json",
				error : function(xhr) {
					alert("에러코드 : " + xhr.status);
					alert("에러메세지 : " + xhr.statusText);
				},
				success : function(updateJsonObjects) {
					
					if (updateJsonObjects.updateFlag == true) {
						alert("선태하신 주문의 상태가 변경되었습니다.");
						searchCommData();				
					} else {
						alert("주문상태 변경에 실패하였습니다.");
						searchCommData();
					}// end else 
				}// success
			}); // ajax
      });// click
      
      $("#calculateBtn").click(function() {
      	var frontPeriodSelect =$("#datepicker");
      	var backPeriodSelect=$("#datepicker2");
      	var frontPeriodSelectData = new Date();
      	var backPeriodSelectData = new Date();
      	var curDate = new Date();
      	var curDateTemp = curDate.getFullYear()+"-"+(curDate.getMonth()+1)+"-"+curDate.getDate();
      	
		$("#totalCalulate").val("");
      	
      	if(frontPeriodSelect.val()!=""&&backPeriodSelect.val()==""){// 앞 날짜 선택, 뒤에는 비어있을 때
				backPeriodSelect.val(curDateTemp);// 현재 날짜 삽입
	      	if(frontPeriodSelect.val().trim()>backPeriodSelect.val().trim()){
	      		alert("조회할 시작 날짜를 다시 설정해주세요.");
	      		frontPeriodSelect.val("");
	      		return;
	      	}    	  	
    			frontPeriodSelectData=frontPeriodSelect.val();
				backPeriodSelectData=curDateTemp;
	      	
      	}else if(frontPeriodSelect.val()==""&&backPeriodSelect.val()!=""){// 앞 날짜 비어있고, 뒤 날짜 선택
      			frontPeriodSelect.val("1994-10-11");
      			frontPeriodSelectData="1994-10-11";

      			if(backPeriodSelect.val().trim()>curDateTemp){
      				alert("날짜를 초과했습니다. 현재 날짜로 설정 됩니다.");
      				backPeriodSelect.val(curDateTemp);
      				backPeriodSelectData=curDateTemp;
      			}// end if

      			if(frontPeriodSelect.val().trim()>backPeriodSelect.val().trim()){
	      		alert("조회할 시작 날짜를 다시 설정해주세요.");
	      		frontPeriodSelect.val("");
	      		return;
	      	}//end if    	  	
      		
      	}else if(frontPeriodSelect.val()!=""&&backPeriodSelect.val()!=""){ // 두 날짜 선택 되었을때
      		if(frontPeriodSelect.val().trim()>backPeriodSelect.val().trim()){
	      		alert("조회할 시작 날짜를 다시 설정해주세요.");
	      		frontPeriodSelect.val("");
	      		return;
	      	}    	  	
      		
      		backPeriodSelectData=backPeriodSelect.val();
      		frontPeriodSelectData=frontPeriodSelect.val();

      		if(backPeriodSelect.val().trim()>curDateTemp){
  				alert("날짜를 초과했습니다. 현재 날짜로 설정 됩니다.");
  				backPeriodSelect.val(curDateTemp);
  				backPeriodSelectData=curDateTemp;
  			}// end if
  			
  			
      	}else if(frontPeriodSelect.val()==""&&backPeriodSelect.val()==""){// 두 날짜 선택 되어있지 않을때
      		frontPeriodSelect.val("");
      		backPeriodSelect.val("");
      		backPeriodSelectData="1111-11-11";
      		frontPeriodSelectData="1111-11-11";
      	}
      	
      	
      	var param = "frontPeriodSelect=" + frontPeriodSelectData
		+ "&backPeriodSelect=" + backPeriodSelectData;

$.ajax({
	url : "order_ajax_money.do",
	type : "get",
	data : param,
	dataType : "json",
	error : function(xhr) {
		alert("에러코드 : " + xhr.status);
		alert("에러메세지 : " + xhr.statusText);
	},
	success : function(totalJsonObject) {
		var output = "";
		var searchLen = Object.keys(totalJsonObject.tableData).length;
		if (searchLen == 0) {
			output += "<tr><td colspan='6' align='center'>조회 정산이 존재하지 않습니다.</td></tr>";
		} else {
			$.each(totalJsonObject.tableData, function(i, jsonObject) {
				output += "<tr>";
				output += "<th scope='row' width='50' style='text-align: center'>"+jsonObject.rnum +"</th>";
				output += "<td width='100' style='text-align: center'>"+jsonObject.oCode +"</td>";
				output += "<td width='130' style='text-align: center'>"+jsonObject.gdName +"</td>";
				output += "<td width='60' style='text-align: left'>"+jsonObject.cQuantity +"</td>";
				output += "<td width='80' style='text-align: left'>"+jsonObject.cTotalPrice +"</td>";
				output += "<td width='140' style='text-align: left;'>"+jsonObject.cDate +"</td>";
				output += "</tr>";
			})// end each
			
			
		}// end else 
		$("#moneyTable tbody").html(output);
		$("#totalCalulate").val(totalJsonObject.moneyData.totalMoney);
		$("#periodCalculate").val("");
			
		if(totalJsonObject.moneyData.periodMoney!=null){
			$("#periodCalculate").val(totalJsonObject.moneyData.periodMoney);
		}
	}// success
}); // ajax"periodCalculate"
      	
      });// click
      
      //모든 datepicker에 대한 공통 옵션 설정
      $.datepicker.setDefaults({
          dateFormat: 'yy-mm-dd' //Input Display Format 변경
          ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
          ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
          ,changeYear: true //콤보박스에서 년 선택 가능
          ,changeMonth: true //콤보박스에서 월 선택 가능                
          ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
          ,buttonImage: "http://localhost:8080/team2_server_prj3/common/images/calendar.png" //버튼 이미지 경로
          ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
          ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
          ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
       	 	,yearRange: "2000:2020"
        	  ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
          ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
          ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
          ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
          ,minDate: new Date('2000-01-01') //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
          ,maxDate: 0 //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                    
      
      });

      //input을 datepicker로 선언
      $("#datepicker").datepicker();                    
      $("#datepicker2").datepicker();

 });// ready
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
   <div class="container" style="min-height: 1000px ;min-width: 1500px; padding-top: 30px;">
   <div class="row">
   <!-- 주문관리 -->
	<div id="orderM" class="col" style="margin-left: 20px;padding-right: 5px; border-right: 5px solid #E2E3E5">
			 <div  style="font-weight: bold;font-size: 23px;margin: 0 0 10px 15px">주문관리</div> 
		<div id="orderS" style="padding-left: 30px;width: 830px;height: 50px;">
			
			<input type="text" id="searchInput" name="searchInput" class="form-control" placeholder="고객의 ID를 입력하세요." style="width: 500px; float: left; margin:0 30px 15px 50px;">
			<button type="button" id="inputSearchBtn" name="inputSearchBtn" class="btn btn-secondary" style="height: 40px; width: 110px; margin:0 20px 0 10px;  float: left">ID검색</button>
		</div>
		<div>
			<select id="selectStatus" name="selectStatus" class="custom-select" style="float: left; margin:0 30px 10px 80px;width: 500px">
				<option value="A" selected>전체</option>
				<option value="P">주문준비</option>
				<option value="C">수령완료</option>
			</select>
			<button type="button" class="btn btn-primary" id="statusChangeBtn" name="statusChangeBtn" style="height: 40px; width: 110px;  margin-left:10px; margin-top: 0px;  float: left">상태변경</button>
		</div>
		<!-- 주문상태 테이블 -->
	    <div style="float: left">
	          <div style="width:700px; height:300px;">
			   <div id="sales_being_prepared">
				<table id="orderDataTab"  class="table table-striped empty-space TABLE" style="margin-left: 0px; height:720px;width: 700px; border: 2px solid #E2E3E5">
				  <thead>
				    <tr>
				      <th scope="col" class="text-center"><input disabled="disabled" type="checkbox" style="width:20px; height:18px;" id="statusChangeAllChk" name="statusChangeAllChk"></th>
				      <th scope="col" width="50" style="text-align: center">No.</th>
				      <th scope="col" width="200" style="text-align: center">상품명</th>
				      <th scope="col" width="60" style="text-align: center">상태</th>
				      <th scope="col" width="60" style="text-align: center">수량</th>
				      <th scope="col" width="100" style="text-align: left">금액</th>
				      <th scope="col" width="100" style="text-align: left">주문ID</th>
				      <th scope="col" width="150" style="text-align: left">휴대폰번호</th>
				      <th scope="col" width="150" style="text-align: center">주문일</th>
				    </tr>
				  </thead>
				  <tbody>
				  <c:forEach var="saO" items="${searchAllOrder}">
				    <tr>
				      <th scope="row" class="text-center"><input  type="checkbox" ${saO.oStatus eq 'C' ?"disabled='disabled'":""} style="width:20px; height:18px;"  name="statusChangeChk" value="${saO.rnum-1}"></th>
				      <th scope="col" width="50" style="text-align: center"><c:out value="${saO.rnum}"/><input type="hidden" id="oCode" name="oCode" value="${saO.oCode }"/></th>
				      <td scope="col" width="200" style="text-align: center"><c:out value="${saO.gdName}"/></td>
				      <th scope="col" width="60" ${saO.oStatus eq 'C' ?"style='text-align: center; color: red;'":"style='text-align: center; color: blue;'"} ><c:out value="${saO.oStatus}"/></th>
				      <td scope="col" width="60" style="text-align: center"><c:out value="${saO.cQuantity}"/></td>
				      <td scope="col" width="100" style="text-align: center"><c:out value="${saO.cTotalPrice}"/></td>
				      <td scope="col" width="100" style="text-align: center"><c:out value="${saO.mId}"/></td>
				      <td scope="col" width="150" style="text-align: center"><c:out value="${saO.mPhone}"/></td>
				      <td scope="col" width="150" style="text-align: center"><c:out value="${saO.cDate}"/></td>
				    </tr>
				  </c:forEach>
				  </tbody>	    
				</table>
				</div>
			   </div>       
	   </div>
	 </div>
               <!-- 정산 -->  
     <div class="col" style="width: 500px">
     	<div style="padding-left: 57px;">
			<font style="font-size:22px;font-weight: bold;float: left; margin: 0 0 10px 0;">총 정산:</font>
			<input id="totalCalulate" name="totalCalulate"  value="${searchCalMoney.totalMoney}" type="text" class="form-control" readonly="readonly" style="width: 410px; float: left; margin: 0 10px 10px 15px;">	
     	</div> 
     	<div style="padding-left: 15px; ">
			<font style="font-size:22px;font-weight: bold;float: left; margin: 0 0 10px 0;">기간별 정산:</font>
			<input type="text" id="periodCalculate" name="periodCalculate"  class="form-control"  readonly="readonly" style="width: 410px; float: left; margin: 0 10px 10px 15px;">	
     	</div>
     	<!-- 정산 기간 설정 -->
     <div style="padding-left: 15px ;clear: both ;height: 50px">
		<div class="input-group"  style="width: 600px">
			<!-- <input id="frontPeriodSelect" name="frontPeriodSelect" type="date" class="form-control" /> -->
			 <input type="text" id="datepicker" name="datepicker"><div style="font-size:22px; width: 25px; margin-left: 10px"><strong>~</strong></div><input type="text" id="datepicker2" name="datepicker2">
			<button type="button" id="calculateBtn" name="calculateBtn" class="btn btn-secondary" style="height: 40px; width: 80px;margin-left: 15px">검색</button>
		</div>	
      </div>
      <!--정산 테이블  -->
     	  <div >
		    <div id="sales_taken">
			<table id="moneyTable" class="table table-striped empty-space TABLECalc" style="height:720px; margin-left: 0px; text-align:center; border: 2px solid #E2E3E5">
			  <thead>
			    <tr>
			      <th scope="col" width="50" style="text-align: center">No.</th>
			      <th scope="col" width="100" style="text-align: center">주문코드</th>
			      <th scope="col" width="130" style="text-align: center">메뉴명</th>
			      <th scope="col" width="60" style="text-align: right;">수량</th>
			      <th scope="col" width="100" style="text-align: right;">총가격</th>
			      <th scope="col" width="100" style="text-align: right;">주문일자</th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:forEach var="sac"  items="${searchAllCalculate}">
			    <tr>
			      <th scope="row" width="50" style="text-align: center"><c:out value="${sac.rnum}"/></th>
			      <td width="100" style="text-align: center"><c:out value="${sac.oCode}"/></td>
			      <td width="130" style="text-align: center"><c:out value="${sac.gdName}"/></td>
			      <td width="60" style="text-align: left;"><c:out value="${sac.cQuantity}"/></td>
			      <td width="80" style="text-align: left;"><c:out value="${sac.cTotalPrice}"/></td>
			      <td width="140" style="text-align: left;"><c:out value="${sac.cDate}"/></td>
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