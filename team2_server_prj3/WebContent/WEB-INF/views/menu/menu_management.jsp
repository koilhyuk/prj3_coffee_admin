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
	$(function() {
		 $("#sidebar").css("height", $("#fix").css("height"));//div id 다시보기
		$("#cateSelect").change(function() {
			selectAjax();
		});// change

		$("#menuSearch").keypress(function(e) {
			if (e.which == 13) {
				selectAjax();
			}
		});

		$("#searchBtn").click(function() {
			selectAjax();
		});// change

		function selectAjax() {
			$( '#goodsAllList > tbody').empty();
			var menuSearch = $("#menuSearch").val();
			if (typeof menuSearch == "undefined") {// 비어 있다면
				menuSearch = "";
			}// end if
			var param = "cateSelect=" + encodeURI($("#cateSelect").val())
					+ "&menuSearch=" + encodeURI(menuSearch);

			$.ajax({
				url : "menu_home_search.do",
				type : "get",
				data : param,
				dataType : "json",
				error : function(xhr) {
					alert("에러코드 : " + xhr.status);
					alert("에러메세지 : " + xhr.statusText);
				},
				success : function(selMenu) {
					var output = "";
					var selectLen = Object.keys(selMenu).length;
					if (selectLen == 0) {
						output += "<tr><td colspan='3' align='center'>조회 상품이 존재하지 않습니다.</td></tr>";
					} else {
						$.each(selMenu.jsaGoodsData, function(i, jsonObject) {
							output += "<tr>";
							output += "<th scope='row'>" + jsonObject.gdName + "</th>";
							output += "<td>" + jsonObject.gcCate + "</td>";
							output += "<td>" + jsonObject.gdPrice + "</td>";
							output += "</tr>";
						})// end each
					}// end else 
					$("#goodsAllList tbody").html(output);
					$("#paginationIndex").html(selMenu.indexList);
				}// success
			}); // ajax
		}//selectAjax

		$("#cateAddBtn").click(function() {
			searchCurCate();

		});//click

		$("#modalCateAddBtn").click(function() {
			var inputAddCate = $("#inputCateAddVal").val();
			if (typeof inputAddCate == "undefined" || inputAddCate == "") {// 비어 있다면
				alert("추가할 카테고리를 입력해주세요.");
				return;
			}// end if

			var param = "inputAddCate=" + encodeURI(inputAddCate);

			$.ajax({
				url : "new_cate_add.do",
				type : "get",
				data : param,
				dataType : "json",
				error : function(xhr) {
					alert("에러코드 : " + xhr.status);
					alert("에러메세지 : " + xhr.statusText);
				},
				success : function(insertFlag) {
					$("#inputCateAddVal").val('');
					$("#cateManagement").modal('hide');
					alert("카테고리가 추가되었습니다.");
				}// success
			}); // ajax 
			searchCurCate();
		});//click

		function searchCurCate() {
			var sel_obj = document.getElementById("beverageCateType");
			for (i = 0; i < sel_obj.options.length; i++) {
				sel_obj.options[i] = null;
			}
			sel_obj.options.length = 0;

			$.ajax({
				url : "menu_modal_cate.do",
				type : "get",
				dataType : "json",
				error : function(xhr) {
					alert("에러코드 : " + xhr.status);
					alert("에러메세지 : " + xhr.statusText);
				},
				success : function(cateTypeData) {
					var output = "";
					console.log(cateTypeData);
					var selectLen = Object.keys(cateTypeData).length;
					if (selectLen == 0) {
						output += "<option>카테고리가 존재하지 않습니다.</option>";
					} else {
						$.each(cateTypeData, function(i, jsonObject) {
							/* 	output += "<option>" + jsonObject.gcCode + "</option>"; */
							output += "<option>" + jsonObject.gcName
									+ "</option>";
						})
					}// end else 
					$("#beverageCateType").append(output);
				}// success
			}); // ajax
			$("#cateManagement").modal('show');

		}//searchCurCate
		

		$("#menuAddBtn").click(function() {
			$("#ModalAddGoodsForm").modal('show');
		});// click

		$("#modalSelectMenu").change(function() {
							var sel_obj = document
									.getElementById("modalSelectCate");
							for (i = 1; i < sel_obj.options.length - 1; i++) {
								sel_obj.options[i] = null;
							}
							sel_obj.options.length = 0;
							var modalSelectMenu = $("#modalSelectMenu").val();
							var selectMenu = "";
						
							if (modalSelectMenu == 'none') {
								alert("분류를 선택하세요.");
								$("#modalSelectCate").append("<option value='none'>선택 없음</option>");
								return;
							} else if (modalSelectMenu == 'B') {
								selectMenu = "음료";
							} else if(modalSelectMenu == 'D') {
								selectMenu = "디저트";
							}
							$("#newMenuAddBtn").val("상세정보 등록 완료");
							

							var param = "selectMenu=" + encodeURI(selectMenu);
							$.ajax({
										url : "add_menu_modal_cate.do",
										type : "get",
										data : param,
										dataType : "json",
										error : function(xhr) {
											alert("에러코드 : " + xhr.status);
											alert("에러메세지 : " + xhr.statusText);
										},
										success : function(cateTypeData) {
											var output = "";
											var selectLen = Object
													.keys(cateTypeData).length;
											if (selectLen == 0) {
												output += "<option value='none'>카테고리 없음</option>";
											} else {
												output += "<option value='none'>선택 없음</option>";
												$.each(cateTypeData,function(i,jsonObject) {
																	/* 	output += "<option>" + jsonObject.gcCode + "</option>"; */
																	output += "<option value='"+jsonObject.gcName +"'>"
																			+ jsonObject.gcName
																			+ "</option>";
																})//end each
											}// end else 
											$("#modalSelectCate").append(output);
										
										}// success
									}); // ajax
						});//change

		$("#newMenuImgSelect").change(function() {
			if (this.files && this.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#new_menu_img').attr('src', e.target.result);
				}
				reader.readAsDataURL(this.files[0]);
			}
		});// change
		
		$("#originMenuImgSelect").change(function() {
			if (this.files && this.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#originMenuImg').attr('src', e.target.result);
				}
				reader.readAsDataURL(this.files[0]);
			}
		});// change

		$("#newMenuAddBtn").click(function() {
			var modalSelectMenu = $("#modalSelectMenu").val();
			var modalSelectCate = $("#modalSelectCate").val();

			if (modalSelectMenu == 'none') {
				alert("분류를 선택하세요.");
				$("#modalSelectMenu").focus();
				return;
			} 

			if(modalSelectCate=='none'){
				alert("카테고리를 선택하세요.");
				$("#modalSelectCate").focus();
				return;
			}
			
			if(!nullChk()){
				return;
			}// end if
				
		var gdName =$("#gdName").val().trim();
		var gdPrice =$("#gdPrice").val().trim();
		var gdKcal =$("#gdKcal").val().trim();
		var gdCaffein =$("#gdCaffein").val().trim();
		var gdSugar =$("#gdSugar").val().trim();
		var gdSalt =$("#gdSalt").val().trim();
		var gdInfo =$("#gdInfo").val().trim();
		

		if(gdKcal==''){
			$("#gdKcal").val(0);
		}
		if(gdCaffein==''){
			$("#gdCaffein").val(0)
		}
		if(gdSugar==''){
			$("#gdSugar").val(0);
		}
		if(gdSalt==''){
			$("#gdSalt").val(0)
		}
			

		
		var modifyForm = document.getElementById('addNewGoodsFrm');
		  //FormData parameter에 담아줌
	      modifyForm.method = "POST";
	      modifyForm .enctype = "multipart/form-data";
	      
	      var modifyFormData = new FormData(modifyForm );
		

	$.ajax({
		url : "new_menu_add_inform.do",
		type : "post",
		enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        data: modifyFormData,
		dataType : "json",
		error : function(xhr) {
			alert("에러코드 : " + xhr.status);
			alert("에러메세지 : " + xhr.statusText);
		},
		success : function(insertData) {
			if (insertData.insertFlag > 0) {
			
			$("#ModalAddGoodsForm").modal('hide');
				 if (modalSelectCate == '커피'||modalSelectCate == '율리치노') {
					$("#newGoodsName").html(insertData.gdName);
					$("#insertRecipeGdName").val(insertData.gdName);
						$("#recipeManager").modal('show');		
				 }else{
					alert(insertData.gdName+"상품 등록되었습니다.");
					 location.href="menu_home.do";
				 }// end else
			
			} else {
				alert("상품 등록이 실패하였습니다.");
			}// end else 
		}// success
	}); // ajax
			
}); //click
				function nullChk() {
					var gdPrice = $("#gdPrice");
					var gdKcal = $("#gdKcal");
					var gdCaffein = $("#gdCaffein");
					var gdSugar = $("#gdSugar");
					var gdSalt = $("#gdSalt");
					var gdName = $("#gdName");
					if (typeof gdName.val().trim() == 'undefined'
							|| gdName.val().trim() == '') {
						alert("상품명을 입력해주세요.");
						gdName.val('');
						gdName.focus();
						return false;
					}

					if (typeof gdPrice.val().trim() == 'undefined'
							|| gdPrice.val().trim() == '') {
						alert("가격을 입력해주세요.");
						gdPrice.focus();
						return false;
					}

					if (!integerChk(gdPrice.val())) {
						gdPrice.val('');
						gdPrice.focus();
						return false;
					}

					if (!integerChk(gdKcal.val())) {
						gdKcal.focus();
						gdKcal.val('');
						return false;
					}
					if (!integerChk(gdCaffein.val())) {
						gdCaffein.focus();
						gdCaffein.val('');
						return false;
					}
					if (!integerChk(gdSugar.val())) {
						gdSugar.focus();
						gdSugar.val('');
						return false;
					}

					if (!integerChk(gdSalt.val())) {
						gdSalt.focus();
						gdSalt.val('');
						return false;
					}
					return true;
				}//nullChk
				
		function integerChk(inputData) {
			var regexp = /^[0-9]*$/
			if (!regexp.test(inputData)) {
				alert("숫자만 입력하세요");
				return false;
			}
			return true;
		}//integerChk
		
		$("#newMenuRecipeBtn").click(function() {
			alert("레시피 등록이 완료되었습니다.")
			var obj = document.insertNewRecipeInFrm;
			
			
			if($("#recipeMilk").val()=='none'){
				$("#recipeMilk").val("");
			}
			if($("#recipeSyrup").val()=='none'){
				$("#recipeSyrup").val("");
			}
			
			if($("#recipeTopping").val()=='none'){
				$("#recipeTopping").val("");
			}
			
			obj.submit();
		}) ; // click
		
	$(document).on('click', '#goodsAllList tbody tr', function(){
		var originGdName= $("#originGdName");
		var originGdPrice= $("#originGdPrice");
		var originGdKcal= $("#originGdKcal");
		var originGdCaffein= $("#originGdCaffein");
		var originGdSugar= $("#originGdSugar");
		var originGdSalt= $("#originGdSalt");
		var originGdInfo= $("#originGdInfo");
		var originMenuImg= $("#originMenuImg");
		
		
		var originShot=$("#originShot");
		var originCream=$("#originCream");
		var originMilk=$("#originMilk");
		var originSyrup=$("#originSyrup");
		var originTopping=$("#originTopping");
		var originType=$("#originType");
		
		
		originGdName.val("");
		originGdPrice.val("");
		originGdKcal.val("");
		originGdCaffein.val("");
		originGdSugar.val("");
		originGdSalt.val("");
		originGdInfo.val("");
		
		
		var sel_obj = document.getElementById("originMenuCate");
		for (i = 1; i < sel_obj.options.length - 1; i++) {
			sel_obj.options[i] = null;
		}
		sel_obj.options.length = 0;
				
		
		var tr = $(this);
		var td = tr.children();
		var gdName = td.eq(0).text().trim();
		var originMenuParam = "gdName="+encodeURI(gdName);	
		
		
	$.ajax({
		url : "origin_menu_inform.do",
		type : "get",
		data : originMenuParam,
		dataType : "json",
		error : function(xhr) {
		alert("여기서 터지는 에러이니?")
		alert("에러코드 : " + xhr.status);
		alert("에러메세지 : " + xhr.statusText);
	},
		success : function(selectGoodsData) {
			$("#detailMenuSelect").val(selectGoodsData.mName).prop("selected",true);
			var output = "";
			$.each(selectGoodsData.cateDatas, function(i,cateJson) {
								/* 	output += "<option>" + jsonObject.gcCode + "</option>"; */
								output += "<option value='"+cateJson.gcName +"'>"
										+ cateJson.gcName
										+ "</option>";
							})//end each
							
			$("#originMenuCate").append(output);
			$("#originMenuCate").val(selectGoodsData.gcName).prop("selected",true);
			
			
			originGdName.val(selectGoodsData.gdName);
			originGdPrice.val(selectGoodsData.gdPrice);
			originGdKcal.val(selectGoodsData.gdKal);
			originGdCaffein.val(selectGoodsData.gdCaffein);
			originGdSugar.val(selectGoodsData.gdSugar);
			originGdSalt.val(selectGoodsData.gdSalt);
			originGdInfo.val(selectGoodsData.gdInfo);
			originMenuImg.attr('src', "http://localhost:8080/team2_server_prj3/common/goodsImages/"+selectGoodsData.gdImg);
			
			
			if (selectGoodsData.gcName=="커피"||selectGoodsData.gcName=="율리치노") {// 커피나 율리치노일 때
			
				originShot.val(selectGoodsData.brShot).prop("selected",true);
				originCream.val(selectGoodsData.brCream).prop("selected",true);
				originType.val(selectGoodsData.brType).prop("selected",true);

				
				originMilk.val(selectGoodsData.brMilk).prop("selected",true);
				originSyrup.val(selectGoodsData.brSyrup).prop("selected",true);
				originTopping.val(selectGoodsData.brTopping).prop("selected",true);
				originShot.attr('disabled', false);
				originCream.attr('disabled', false);
				originType.attr('disabled', false);
				originMilk.attr('disabled', false);
				originSyrup.attr('disabled', false);
				originTopping.attr('disabled', false);
				
			} else {// 커피와 율리치노가 아닐때  레시피 창 없애던가
				originShot.val(0).prop("selected",true);
				originCream.val(0).prop("selected",true);
				originType.val("I").prop("selected",true);

				originMilk.val("none").prop("selected",true);
				originSyrup.val("none").prop("selected",true);
				originTopping.val("none").prop("selected",true);
				
				originShot.attr('disabled', true);
				originCream.attr('disabled', true);
				originType.attr('disabled', true);
				originMilk.attr('disabled', true);
				originSyrup.attr('disabled', true);
				originTopping.attr('disabled', true);

			
			}// end else 
		}// success
	}); // ajax
		$("#ModalDetailGoodsForm").modal('show');
	});//click
	
	$("#modalSelectCate").change(function() {
		var modalSelectCate=  $("#modalSelectCate").val();
			if(modalSelectCate=='커피'||modalSelectCate=='율리치노'){
			$("#newMenuAddBtn").val("레시피 등록하기");
				}else{
			$("#newMenuAddBtn").val("상세정보 등록 완료");
			}// end else
			
	});// click
	
	$("#detailMenuSelect").change(function() {
		var originShot=$("#originShot");
		var originCream=$("#originCream");
		var originMilk=$("#originMilk");
		var originSyrup=$("#originSyrup");
		var originTopping=$("#originTopping");
		var originType=$("#originType");
		var sel_obj = document.getElementById("originMenuCate");
		for (i = 1; i < sel_obj.options.length - 1; i++) {
			sel_obj.options[i] = null;
		}
		sel_obj.options.length = 0;
		var selectMenu = $("#detailMenuSelect").val();
	
		var param = "selectMenu=" + encodeURI(selectMenu);
		$.ajax({
					url : "add_menu_modal_cate.do",
					type : "get",
					data : param,
					dataType : "json",
					error : function(xhr) {
						alert("에러코드 : " + xhr.status);
						alert("에러메세지 : " + xhr.statusText);
					},
					success : function(cateTypeData) {
						var output = "";
							$.each(cateTypeData,function(i,jsonObject) {
												/* 	output += "<option>" + jsonObject.gcCode + "</option>"; */
								output += "<option value='"+jsonObject.gcName +"'>"
										+ jsonObject.gcName
										+ "</option>";
							})//end each
						$("#originMenuCate").append(output);
							
							originShot.attr('disabled', false);
							originCream.attr('disabled', false);
							originType.attr('disabled', false);
							originMilk.attr('disabled', false);
							originSyrup.attr('disabled', false);
							originTopping.attr('disabled', false);
							
							
							if($("#originMenuCate").val()!='커피'&&$("#originMenuCate").val()!='율리치노'){
								originShot.attr('disabled', true);
								originCream.attr('disabled', true);
								originType.attr('disabled', true);
								originMilk.attr('disabled', true);
								originSyrup.attr('disabled', true);
								originTopping.attr('disabled', true);
							}
					
					}// success
				}); // ajax
	});//change
	
	
	$("#originGoodsModifyBtn").click(function() {
		var originShot=$("#originShot");
		var originCream=$("#originCream");
		var originMilk=$("#originMilk");
		var originSyrup=$("#originSyrup");
		var originTopping=$("#originTopping");
		var originType=$("#originType");
		originShot.attr('disabled', false);
		originCream.attr('disabled', false);
		originMilk.attr('disabled', false);
		originSyrup.attr('disabled', false);
		originTopping.attr('disabled', false);
		originType.attr('disabled', false);
		
		
	
		var originMenuCate=$("#originMenuCate");
		var originGdName= $("#originGdName");
		var originGdPrice= $("#originGdPrice");
		var originGdKcal= $("#originGdKcal");
		var originGdCaffein= $("#originGdCaffein");
		var originGdSugar= $("#originGdSugar");
		var originGdSalt= $("#originGdSalt");
		var originGdInfo= $("#originGdInfo");
		var originMenuImg= $("#originMenuImg");

		if(!nullChk2()){
			return;
		}// end if
		
		
		var originGdKcal =$("#originGdKcal").val().trim();
		var originGdCaffein =$("#originGdCaffein").val().trim();
		var originGdSugar =$("#originGdSugar").val().trim();
		var originGdSalt =$("#originGdSalt").val().trim();
		if(originGdKcal==''){
			$("#originGdKcal").val(0);
		}
		if(originGdCaffein==''){
			$("#originGdCaffein").val(0)
		}
		if(originGdSugar==''){
			$("#originGdSugar").val(0);
		}
		if(originGdSalt==''){
			$("#originGdSalt").val(0)
		}
		
		var form = document.getElementById('goodsModifyFrm');
		  //FormData parameter에 담아줌
	      form.method = "POST";
	      form.enctype = "multipart/form-data";
	      var formData = new FormData(form);
		
 		$.ajax({
			url : "menu_origin_modify.do",
			type : "post",
			enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false,
	        data: formData,
			dataType : "json",
			error : function(xhr) {
				alert("에러코드 : " + xhr.status);
				alert("에러메세지 : " + xhr.statusText);
			},
			success : function(updateFlagJson) {
			 	if(updateFlagJson.updateFlag){
					alert($("#originGdName").val().trim()+" 상품이 수정되었습니다.");
				}else{
					alert("상품 수정을 실패하였습니다.");
				} 
				location.replace("menu_home.do");
			}// success
		}); // ajax
	});//click
	
	function nullChk2() {
		var originGdName= $("#originGdName");
		var originGdPrice= $("#originGdPrice");
		var originGdKcal= $("#originGdKcal");
		var originGdCaffein= $("#originGdCaffein");
		var originGdSugar= $("#originGdSugar");
		var originGdSalt= $("#originGdSalt");
		
		
		if (typeof originGdName.val().trim() == 'undefined'
				|| originGdName.val().trim() == '') {
			alert("상품명을 입력해주세요.");
			originGdName.val('');
			originGdName.focus();
			return false;
		}

		if (typeof originGdPrice.val().trim() == 'undefined'
				|| originGdPrice.val().trim() == '') {
			alert("가격을 입력해주세요.");
			originGdPrice.focus();
			return false;
		}

		if (!integerChk(originGdPrice.val())) {
			originGdPrice.val('');
			originGdPrice.focus();
			return false;
		}

		if (!integerChk(originGdKcal.val())) {
			originGdKcal.focus();
			originGdKcal.val('');
			return false;
		}
		if (!integerChk(originGdCaffein.val())) {
			originGdCaffein.focus();
			originGdCaffein.val('');
			return false;
		}
		if (!integerChk(originGdSugar.val())) {
			originGdSugar.focus();
			originGdSugar.val('');
			return false;
		}

		if (!integerChk(originGdSalt.val())) {
			originGdSalt.focus();
			originGdSalt.val('');
			return false;
		}
		return true;
	}//nullChk
	
	
	$("#originGoodsDeleteBtn").click(function() {
		
		var param = "DeleteGdName=" + encodeURI($("#originGdName").val().trim());
		$.ajax({
					url : "menu_origin_delete.do",
					type : "get",
					data : param,
					dataType : "json",
					error : function(xhr) {
						alert("에러코드 : " + xhr.status);
						alert("에러메세지 : " + xhr.statusText);
					},
					success : function(deleteFlagJson) {
					
						if(deleteFlagJson.deleteFlag){
							alert($("#originGdName").val().trim()+" 상품이 삭제되었습니다.");
						}else{
							alert("상품 삭제를 실패하였습니다.");
						}
						location.replace("menu_home.do");
					}// success
				}); // ajax
	});//click
	
	
	$("#originMenuCate").change(function() {
		var originShot=$("#originShot");
		var originCream=$("#originCream");
		var originMilk=$("#originMilk");
		var originSyrup=$("#originSyrup");
		var originTopping=$("#originTopping");
		var originType=$("#originType");
		
		originShot.attr('disabled', false);
		originCream.attr('disabled', false);
		originType.attr('disabled', false);
		originMilk.attr('disabled', false);
		originSyrup.attr('disabled', false);
		originTopping.attr('disabled', false);
		
		if($("#originMenuCate").val()!='커피'&&$("#originMenuCate").val()!='율리치노'){
			originShot.attr('disabled', true);
			originCream.attr('disabled', true);
			originType.attr('disabled', true);
			originMilk.attr('disabled', true);
			originSyrup.attr('disabled', true);
			originTopping.attr('disabled', true);
		}
		
	});// change
	
});// ready
	function searchAllGoods(curpage, url) {
	 	$( '#goodsAllList > tbody').empty();
	 	var menuSearch = $("#menuSearch").val();
	 	var cateSelect = $("#cateSelect").val();
		if (typeof menuSearch == "undefined") {// 비어 있다면
			menuSearch = "";
		}// end if
		var param = "cateSelect=" + encodeURI(cateSelect)
				+ "&menuSearch=" + encodeURI(menuSearch)+"&current_page="+curpage+"&current_url"+url;
		$.ajax({
			url : "menu_home_search.do",
			type : "get",
			data : param,
			dataType : "json",
			error : function(xhr) {
				alert("에러코드 : " + xhr.status);
				alert("에러메세지 : " + xhr.statusText);
			},
			success : function(selMenu) {
				var output = "";
				var selectLen = Object.keys(selMenu).length;
				if (selectLen == 0) {
					output += "<tr><td colspan='3' align='center'>조회 상품이 존재하지 않습니다.</td></tr>";
				} else {
					$.each(selMenu.jsaGoodsData, function(i, jsonObject) {
						output += "<tr>";
						output += "<th scope='row'>" + jsonObject.gdName + "</th>";
						output += "<td>" + jsonObject.gcCate + "</td>";
						output += "<td>" + jsonObject.gdPrice + "</td>";
						output += "</tr>";
					})// end each
				}// end else 
					
				$("#goodsAllList tbody").html(output);
				$("#paginationIndex").html(selMenu.indexList);
			}// success
		}); // ajax 
		
	}//searchAllGoods
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
	    <div class="col" style="min-width: 1000px">
	    
	     <div style="font-size:28px; margin-left: -15px; margin-top: 50px">&emsp;<strong>메뉴관리</strong></div><br/>
				<style type="text/css">
			    .link {color: grey; font-size:22px; margin-left: -5px}
			    </style>
		  <div class="row" style="margin-top: 50px">
		    <div class="form-group col-md-2" style="margin-left: 200px">
		      <select id="cateSelect" name="cateSelect" class="form-control" >
 					<option id="id"  value="전체">전체</option>
 					<c:forEach var="cate" items="${menuCateData}">
 					<c:out value="${param.gcName}"/>
			        	<option id="name"  value="${cate.gcName}" ${cate.gcName eq param.gcName?" selsected='selected'":"" } ><c:out value="${cate.gcName}"/></option>
 					</c:forEach>
		      </select>
		    </div>
		    <div class="input-group col-md-4">
			  <input type="text"  id="menuSearch" name="menuSearch" class="form-control" placeholder="상품명 입력">
			  <div class="input-group-append" id="button-addon4">
			    <button class="btn btn-outline-secondary" type="button" style="height: 38px" id="searchBtn" name="searcBtn">
			    	<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="9" cy="9" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
			    </button>
			  </div>
			</div>
			  <div>
			  	<button type="button" id="cateAddBtn" name="cateAddBtn" class="btn btn-secondary" style="height: 40px; width: 130px; margin-left: 50px">카테고리 추가</button>
			  	<button type="button" id="menuAddBtn" name="menuAddBtn" class="btn btn-primary" style="height: 40px; width: 130px; margin-left: 10px">상품 등록</button>
			  </div>
		  </div>
	    
	    </div>
	    
	    <!-- cate_mangement.jsp modal start-->
							<div class="modal fade" id="cateManagement" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
								<div class="modal-dialog" role="document">
								    <div class="modal-content">
									      <div class="modal-header">
							<!-- 			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							-->
										        <h4 class="modal-title" id="myModalLabel">음료 카테고리 관리</h4>
							
									      </div>
									<div class="modal-body" style="width: 500px; height: 300px">
										<div >
										 
										 <div class="form-group" id="cateType" style="width: 200px; height: 250px; margin-top: 15px;  float: left;">
								<!-- 			    <label for="exampleFormControlSelect2">음료 카테고리 관리</label> -->
										    <select multiple class="form-control" id="beverageCateType" name="beverageCateType" style="height: 240px">
													
										    </select>
										 </div>		
										 
										 <div style=" width: 250px;  height: 250px; margin-top: 15px;; float: right;">
										 	<label style="margin-top: 30px; margin-left: 14px; font-size: 16px; font-weight: bold;">ADD CATEGORY</label>
										 	<div class="input-group col-md-11" style="height:40px ; margin: 1px 0 0 0; padding-right:0px">
											  <input type="text" id="inputCateAddVal" name="inputCateAddVal" class="form-control" placeholder="카테고리명 입력">
											</div>			 
											<div style="width: 220px; height: 50px;margin-left:auto; margin-right: auto; margin-top:50px">
												<button type="button"  id="modalCateAddBtn" name="modalCateAddBtn" class="btn btn-primary btn-lg" style="width: 105px">추가</button>
												<!-- <button type="button" id="modalCateCloseBtn" name="modalCateCloseBtn"class="btn btn-danger btn-lg" style="width: 105px;">삭제</button> -->
											</div>
										 </div> 
										</div>
								    </div>
								    <div class="modal-footer">
								       <button type="button" class="btn btn-default" data-dismiss="modal" style="margin-right: 20px;">닫기</button>
								   </div>
								  </div>
								</div>
							</div>
	    <!--cate_mangement.jsp modal end  -->
	    
	    <!-- Modal new_add_goods Start-->
<div id="ModalAddGoodsForm" class="modal fade bd-example-modal-lg">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header" style=" padding: 8px 15px 8px 15px;">
                <h4 class="modal-title" style="font-weight: bold">상품 추가</h4>
            </div>
            <div class="modal-body">
            <div>
                <form role="form" id="addNewGoodsFrm" name="">
                    <input type="hidden" name="_token" value="">
                    <div class="form-group" style="position: relative;float: left;">
	                    <div class="card" style="width: 300px;height: 350px;">
						  <img class="card-img-top" id="new_menu_img"  name="new_menu_img"   width="300px" height="350px" alt="이미지를 넣어주세요">
						  <!-- src="http://localhost:8080/team1_admin_prj3/common/images/book/noImg.jpg" -->
						</div>
						  <div class="card-body" style="width: 300px;">
						    <input type="file" id="newMenuImgSelect" name="newMenuImgSelect">
						  </div>
					</div>
					<div style="margin-left: 41%; padding-top: 10px;padding-bottom: 10px">
                    <div class="form-group" style="overflow: hidden; height: 70px; ">
	                    <div class="form-group" style=" float:left;width: 220px;margin-right: 10px; ">
	                        <label class="control-label">분류 선택</label>
	                        <div>
	                            <select id="modalSelectMenu" name="modalSelectMenu" class="custom-select custom-select sm">
								  <option value="none" selected>선택 없음</option>
								  <option value="B">음료</option>
								  <option value="D">디저트</option>
								</select>
	                        </div>
	                    </div>
	                    <div class="form-group" style="overflow: hidden; width: 220px">
	                       <label class="control-label">카테고리 선택</label>
	                        <div>
	                            <select id="modalSelectCate" name="modalSelectCate" class="custom-select custom-select sm">
									<option value='none'>선택없음</option>
								  
								</select>
	                        </div>
	                    </div>
                    </div>
                    <div class="form-group" style="overflow: hidden; ">
                        <label class="control-label">상품명</label>
                        <div>
                            <input type="text" class="form-control input-lg" id="gdName" name="gdName">
                        </div>
                    </div>
                    <div class="form-group" style="overflow: hidden; ">
                        <label class="control-label">가격(KRW)</label>
                        <div>
                            <input type="text"  class="form-control input-lg" id="gdPrice"   name="gdPrice">
                        </div>
                    </div>
                    <div id="nutrionFacts">
                    <div class="form-group" style="float:left;width: 220px;margin-right: 10px">
                        <label class="control-label">칼로리(Kcal)</label>
                        <div>
                            <input type="text" class="form-control input-lg" id="gdKcal" name="gdKcal">
                        </div>
                    </div>
                    <div class="form-group" style="overflow: hidden; width: 220px">
                        <label class="control-label">카페인(mg)</label>
                        <div>
                            <input type="text" class="form-control input-lg" id="gdCaffein" name="gdCaffein">
                        </div>
                    </div>
                    <div class="form-group" style=" float:left;width: 220px;margin-right: 10px">
                        <label class="control-label">당(g)</label>
                        <div>
                            <input type="text" class="form-control input-lg" id="gdSugar" name="gdSugar">
                        </div>
                    </div>
                    <div class="form-group" style="overflow: hidden; width: 220px">
                        <label class="control-label">나트륨(mg)</label>
                        <div>
                            <input type="text" class="form-control input-lg" id="gdSalt" name="gdSalt">
                        </div>
                    </div>
                    </div><!--nutrionFacts  -->
                    </div><!-- 상품평부터 나트륨까지 -->
                    <div class="form-group" style="overflow: hidden; height:150px;">
                        <label class="control-label">정보</label>
                        <div>
                        <textarea rows="100" cols="50" class="form-control input-lg"  id="gdInfo" name="gdInfo" style="height:100px;" ></textarea>
<!--                             <input type="text" class="form-control input-lg"  id="gdInfo" name="gdInfo" style="height:100px;" > -->
                        </div>
                    </div>
                     <div class="form-group">
                        <div>
                        <input type="button" id="newMenuAddBtn" name="newMenuAddBtn" class="btn btn-primary" value="상세정보 등록 완료 & 레시피 등록하기" style="float: right;width: 320px"/> 
                        </div>
                    </div>
                </form>
                </div>
            </div>
            <div class="modal-footer">
		       <button type="button" class="btn btn-default" data-dismiss="modal" style="margin-right: 20px;">닫기</button>
		   </div>
            
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal new_add_goods End-->


	   <!-- new_add_recipe start  -->
<div class="modal fade" id="recipeManager" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
	<div class="modal-dialog" role="document">
	    <div class="modal-content">
		      <div class="modal-header">
			        <h4 class="modal-title" id="myModalLabel" ><strong style="color: blue;" id="newGoodsName"></strong> 레시피 추가</h4>
		      </div>
			<div class="modal-body" style="width: 500px; height: 300px">
			<form id="insertNewRecipeInFrm" name="insertNewRecipeInFrm" action="menu_new_recipe.do" method="get">
			<input type="hidden" value="" id="insertRecipeGdName" name="insertRecipeGdName"/>
				<div>
		                  <div id="recipeFacts">
		                    <div class="form-group" style="float:left;width: 220px;margin-right: 10px">
		                        <label class="control-label">샷(Shot)</label>
			                      <div>
		                            <select id="recipeShot" name="recipeShot" class="custom-select custom-select sm">
									  <option value="0" selected>0</option>
									  <option value="1">1</option>
									  <option value="2">2</option>
									  <option value="3">3</option>
									  <option value="4">4</option>
									  <option value="5">5</option>
									</select>
		                        </div>
		                    </div>
		                    <div class="form-group" style="overflow: hidden; width: 220px">
		                        <label class="control-label">휘핑크림(Cream)</label>
		                        <div>
		                            <select id="recipeCream" name="recipeCream" class="custom-select custom-select sm">
										<option value="0" selected>0</option>
										<option value="1">1</option>
									  	<option value="2">2</option>
									  	<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
		                        </div>
		                    </div>
		                    <div class="form-group" style="float:left;width: 220px;margin-right: 10px">
		                        <label class="control-label">우유</label>
			                      <div>
		                            <select id="recipeMilk" name="recipeMilk" class="custom-select custom-select sm">
									  <option value="none" selected>선택 없음</option>
									  <option value="우유">우유</option>
									  <option value="두유">두유</option>
									  <option value="락토프리">락토프리</option>
									</select>
		                        </div>
		                    </div>
		                    <div class="form-group" style="overflow: hidden; width: 220px">
		                        <label   class="control-label">시럽</label>
		                       <div>
		                            <select id="recipeSyrup" name="recipeSyrup" class="custom-select custom-select sm">
									  <option value="none" selected>선택 없음</option>
									  <option value="바닐라">바닐라</option>
									  <option value="카라멜">카라멜</option>
									  <option value="모카">모카</option>
									</select>
		                        </div>
		                    </div>
		                    <div class="form-group" style=" float:left;width: 220px;margin-right: 10px">
		                        <label class="control-label">토핑</label>
		                       <div>
		                            <select id="recipeTopping" name="recipeTopping" class="custom-select custom-select sm">
									  <option value="none" selected>선택 없음</option>
									  <option value="아몬드">아몬드</option>
									  <option value="호두">호두</option>
									  <option value="캐슈넛">캐슈넛</option>
									</select>
		                        </div>
		                    </div>
		                    <div class="form-group" style="overflow: hidden; width: 220px">
		                        <label  class="control-label">타입</label>
				                     <div>
			                            <select id="recipeType" name="recipeType" class="custom-select custom-select sm">
										  <option value="I" selected>ICE</option>
										  <option value="H">HOT</option>
										  <option value="B">BOTH(ICE/HOT)</option>
										</select>
			                        </div>
		                    </div>
		                 </div>
		              </div>
		              </form>
				</div>
	  	  <div class="modal-footer">
		    <input type="button" id="newMenuRecipeBtn" name="newMenuRecipeBtn" class="btn btn-primary" value="레시피 등록완료" style="float: right;width: 200px;"/> 
	   </div>
	    </div>
	  </div>
	</div>
   <!-- new_add_recipe end  -->
   
   
   <!-- Modal detail_goods.jsp start -->
<div id="ModalDetailGoodsForm" class="modal fade bd-example-modal-xl" data-backdrop="static" style="height: 1100px">
    <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <div class="modal-header" style=" padding: 8px 15px 8px 15px;">
                <h4 class="modal-title" style="font-weight: bold">상품 상세보기</h4>
            </div>
            <div class="modal-body">
            
            <div style=" width: 1100px; height: 620px">
                <form role="form" id="goodsModifyFrm" name="goodsModifyFrm">
                	<div style=" width: 800px; float: left;">
                    <input type="hidden" name="_token" value="">
                    <div class="form-group" style="position: relative;float: left;">
	                    <div class="card" style="width: 300px;height: 350px; ">
						  <img id="originMenuImg" name="originMenuImg" class="card-img-top"  width="300px" height="350px" alt="이미지를 넣어주세요">
						</div>
						  <div class="card-body"  style="width: 300px;">
						    <input type="file" id="originMenuImgSelect" name="originMenuImgSelect">
						  </div>
					</div>
					<div style="margin-left: 41%; padding-top: 10px;padding-bottom: 10px">
                    <div class="form-group" style="overflow: hidden; height: 70px; ">
	                    <div class="form-group" style=" float:left;width: 220px;margin-right: 30px; ">
	                        <label class="control-label">분류 선택</label>
	                        <div>
                           <select id="detailMenuSelect" name="detailMenuSelect" class="custom-select custom-select sm"> 
								  <option value="음료">음료</option>
								  <option value="디저트">디저트</option>
								</select>
	                        </div>
	                    </div>
	                    <div class="form-group" style="overflow: hidden; width: 220px">
	                       <label class="control-label">카테고리 선택</label>
	                        <div>
	                            <select id="originMenuCate" name="originMenuCate" class="custom-select custom-select sm">
								
								</select>
	                        </div>
	                    </div>
                    </div>
                    <div class="form-group" style="overflow: hidden; ">
                        <label class="control-label">상품명</label>
                        <div>
                            <input type="text" class="form-control input-lg" id="originGdName" name="originGdName" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group" style="overflow: hidden; ">
                        <label class="control-label">가격(KRW)</label>
                        <div>
                            <input type="text" class="form-control input-lg" id="originGdPrice" name="originGdPrice">
                        </div>
                    </div>
                    <div id="nutrionFacts">
                    <div class="form-group" style="float:left;width: 220px;margin-right: 30px">
                        <label class="control-label">칼로리(Kcal)</label>
                        <div>
                            <input type="text" class="form-control input-lg" id="originGdKcal" name="originGdKcal">
                        </div>
                    </div>
                    <div class="form-group" style="overflow: hidden; width: 220px">
                        <label class="control-label">카페인(mg)</label>
                        <div>
                            <input type="text" class="form-control input-lg" id="originGdCaffein" name="originGdCaffein">
                        </div>
                    </div>
                    <div class="form-group" style=" float:left;width: 220px;margin-right: 30px">
                        <label class="control-label">당(g)</label>
                        <div>
                            <input type="text" class="form-control input-lg" id="originGdSugar" name="originGdSugar">
                        </div>
                    </div>
                    <div class="form-group" style="overflow: hidden; width: 220px">
                        <label class="control-label">나트륨(mg)</label>
                        <div>
                            <input type="text" class="form-control input-lg" id="originGdSalt" name="originGdSalt">
                        </div>
                    </div>
                    </div><!--nutrionFacts  -->
                        
                    </div>
                    <div class="form-group" style="overflow: hidden; height:150px;">
                        <label class="control-label">정보</label>
                        <div>
                          <textarea rows="100" cols="50" class="form-control input-lg"  id="originGdInfo" name="originGdInfo" style="height:100px;" ></textarea>
                         <!--    <input type="text" class="form-control input-lg"  name="gdInfo" style="height:130px;" > -->
                        </div>
                    </div>
                </div><!-- left content  -->
                    
                 <div style=" width: 255px; min-height:600px;float: right; padding-left: 45px;padding-top: 10px ;border-left:5px solid #dee2e6" >
                    <label style="font-weight: bold;font-size: 18px">레시피 정보</label>
                 	<div class="form-group" style="width: 170px;">
		                  <label class="control-label">샷(Shot)</label>
			                      <div>
		                            <select  id="originShot" name="originShot"  class="custom-select custom-select sm">
									  <option value="0" >0</option>
									  <option value="1">1</option>
									  <option value="2">2</option>
									  <option value="3">3</option>
									  <option value="4">4</option>
									  <option value="5">5</option>
									</select>
		                        </div>
		                    </div>
		                    <div class="form-group" style="overflow: hidden; width: 170px">
		                        <label class="control-label">휘핑크림(Cream)</label>
		                        <div>
		                            <select id="originCream" name="originCream"  class="custom-select custom-select sm">
										<option value="0" >0</option>
										<option value="1">1</option>
									  	<option value="2">2</option>
									  	<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
		                        </div>
		                    </div>
		                    <div class="form-group" style="width: 170px;">
		                        <label class="control-label">우유</label>
			                      <div>
		                            <select  id="originMilk" name="originMilk"  class="custom-select custom-select sm">
									  <option value="none" >선택 없음</option>
									  <option value="우유">우유</option>
									  <option value="두유">두유</option>
									  <option value="락토프리">락토프리</option>
									</select>
		                        </div>
		                    </div>
		                    <div class="form-group" style="overflow: hidden; width: 170px">
		                        <label class="control-label">시럽</label>
			                       <div>
			                            <select id="originSyrup" name="originSyrup"  class="custom-select custom-select sm">
										  <option value="none" >선택 없음</option>
										  <option value="바닐라">바닐라</option>
										  <option value="카라멜">카라멜</option>
										  <option value="헤이즐넛">헤이즐넛</option>
										  <option value="모카">모카</option>
										</select>
			                        </div>
		                    </div>
		                    <div class="form-group" style=" width: 170px;">
		                        <label class="control-label">토핑</label>
		                       <div>
		                            <select   id="originTopping" name="originTopping"  class="custom-select custom-select sm">
									  <option value="none" >선택 없음</option>
									  <option value="아몬드">아몬드</option>
									  <option value="호두">호두</option>
									  <option value="캐슈넛">캐슈넛</option>
									</select>
		                        </div>
		                    </div>
		                    <div class="form-group" style="overflow: hidden; width: 170px">
		                        <label class="control-label">타입</label>
				                     <div>
			                            <select  id="originType" name="originType"   class="custom-select custom-select sm">
										  <option value="I">ICE</option>
										  <option value="H">HOT</option>
										  <option value="B">BOTH(ICE/HOT)</option>
										</select>
			                        </div>
		                    </div>   
                 </div><!-- right content -->
                </form>
                </div>
            </div><!-- modal body -->
             <div class="modal-footer" style=" padding: 8px 15px 8px 15px;">
             <button type="button" id="originGoodsModifyBtn" name="originGoodsModifyBtn" class="btn btn-info" style="width: 100px">수정</button>
             <button type="button" id="originGoodsDeleteBtn" name="originGoodsDeleteBtn" class="btn btn-danger" style="width: 100px">삭제</button>
             <button type="button" class="btn btn-light" data-dismiss="modal" style="width: 100px">취소</button>
            </div>
        </div><!-- modal-content -->
    </div><!-- modal-dialog -->
</div><!-- modal -->
<!-- Modal detail_goods.jsp end -->


	    <div class="w-100"></div>
	    <div class="col" style="min-width: 1200px">
	    
	    <table id="goodsAllList" class="table table-striped table-hover"  style="border: 2px solid #E2E3E5; height: 600px"  class="table table-striped empty-space" >
		  <thead>
		    <tr>
		      <th scope="col">상품명</th>
			  <th scope="col">카테고리</th>
		      <th scope="col">가격</th>
		    </tr>
		  </thead>
		 	<tbody>

		 		 <c:forEach var="mad"  items="${menuAllData}">
				    <tr>
				      <th scope="row" ><c:out value="${mad.gdName}"/></th>
				      <td><c:out value="${mad.gcCate}"/></td>
				      <td><c:out value="${mad.gdPrice}"/></td>
				    </tr>
		 		 </c:forEach> 
				    
				</tbody>
		</table>
	    </div>
	    <div class="w-100"></div>
	    <div class="col" style="min-width: 1200px">
	    
	    
	      <div id="boardIndexList">
			  <div style="margin:0px auto; width:250px">
				  <nav aria-label="Page navigation example">
					  <ul class="pagination" id="paginationIndex" >
						<c:out value="${indexList}" escapeXml="false"/>
					  </ul>
				</nav>
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