<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js">
	
</script>

</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/cate">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/writeFrom">글작성</a></li>
			</ul>
			<!-- //admin-menu -->

			<div id="admin-content">

				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id="cateList">
						<!-- 리스트 영역 -->
					</tbody>
				</table>

				<table id="admin-cate-add">
					<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
					<tr>
						<td class="input-name">카테고리명</td>
						<td><input id="input-name" type="text" name="name" value=""></td>
					</tr>
					<tr>
						<td class="input-desc">설명</td>
						<td><input id="input-desc" type="text" name="desc" value=""></td>
					</tr>
				</table>

				<div id="btnArea">
					<button id="btnAddCate" class="btn_l" type="submit">카테고리추가</button>
				</div>

			</div>
			<!-- //admin-content -->
		</div>
		<!-- //content -->


		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>


	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">

	//DOM이 완성되었을때 --> 그리기 직전
	$(document).ready(function(){
		console.log("ready()");
	    fetchList(); //ajax통신을 이용해서 데이타를 요청하고 + 그린다(render())
		console.log("ready()요청후");
	});
	
	//화면을 그리고 난후
	$(window).load(function(){
		console.log("load()");
		//fetchList();	
		console.log("load()요청후");
	});

	
	$("#btnAddCate").on("click", function(e) {
		e.preventDefault(); 
		console.log("카테고리추가버튼 클릭");		
		// 데이터 수집
		let categoryVo = {
			cateName: $("#input-name").val(),
			description: $("#input-desc").val()
		}
		console.log(categoryVo);		
		
		$.ajax({
			url : "${pageContext.request.contextPath}/${blogVo.id}/admin/cateInsert",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(categoryVo),
			
			dataType : "json",
			success : function(result) {
				/*성공시 처리해야될 코드 작성*/	
				console.log(result);
				
				//그리기
				document.location.reload(true);
				
				//초기화
				$("#input-name").val("");
				$("#input-desc").val("");
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}				
		});		
	});
	
	//삭제버튼 눌렀을때
	$("#cateList").on("click", ".btnCateDel", function(){
		console.log("삭제버튼 클릭");
		
		let $this = $(this);		
		console.log($this);
		
		let cateNo = $this.data("no");		
		console.log(cateNo);
		
		$.ajax({
			url : "${pageContext.request.contextPath}/${blogVo.id}/admin/delete",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(cateNo),
			
			dataType : "json",
			success : function(result) {
				/*성공시 처리해야될 코드 작성*/	
				console.log(result);
				
				//그리기
				document.location.reload(true); // 카테고리 번호순서를 리셋하려면 결국 리로드 해줘야함
				//$("#t"+cateNo).remove(); // 바로 화면에서만 지우면 중간번호가 생략될 수 있음
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}				
		});		
		
		//패스워드, no
		//(event)=>
		//console.log($(event.target));
		
		//let $this = $(this);
		//let no = $this.data("no");
		//console.log($this);
		
		//ajax 요청 db를지운다
		//과제
		
		//화면에서 지운다
		//$("#t"+no).remove();  < --------------------------- 이거 사용하기
		
	});
	
	//ajax통신을 이용해서 데이타를 요청하고 + 그린다(render())
	function fetchList(){
		
		//서버로 부터 방명록 데이타만 받고 싶다 ajax요청
		$.ajax({
			url : "${pageContext.request.contextPath}/${blogVo.id}/admin/list",		
			type : "get",
			
			dataType : "json",
			success : function(categoryList){
				/*성공시 처리해야될 코드 작성*/
				//리스트받기
				console.log(categoryList);
				
				for(let i=0; i<categoryList.length; i++){
					render(categoryList[i], "down"); //그리기	
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	}
		
		
	//방명록 내용을 1개씩 그린다
	function render(categoryVo, dir){
		let str ='';
		str +='<tr id=t'+categoryVo.cateNo +'>';
		str +='		<td>' + categoryVo.num + '</td>';
		str +='		<td>' + categoryVo.cateName + '</td>';
		str +='		<td>' + categoryVo.num + '</td>'; // 포스트수로 변경하기
		str +='		<td>' + categoryVo.description + '</td>';
		str +='		<td class="text-center"><img class="btnCateDel" data-no='+ categoryVo.cateNo +' src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>';
		str +='</tr>';		
		
		if(dir=="up"){
			$("#cateList").prepend(str);
			
		}else if(dir=="down"){
			$("#cateList").append(str);	
		
		}else {
			consoel.log("잘못입력")
		}
		
		
	}
	
	
</script>

</html>