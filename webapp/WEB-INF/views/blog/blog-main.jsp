<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<!-- 기본이미지 -->
					<img id="proImg" src="${pageContext.request.contextPath}/upload/${blogVo.logoFile}">
					
					<!-- 사용자업로드 이미지 -->
					<%-- <img id="proImg" src=""> --%>
					
					<div id="nick">${blogVo.userName}(${blogVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${cateList}" var="CategoryVo" varStatus="status" >
						<tr>
							<li><a href="${pageContext.request.contextPath}/${blogVo.id}?cate=${CategoryVo.cateNo}&post=${param.post}">${CategoryVo.cateName}</a></li>
						</tr>						
						</c:forEach>	
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				
				<div id="postBox" class="clearfix">
						<div id="postTitle" class="text-left"><strong>${postOne.postTitle}</strong></div>
						<div id="postDate" class="text-left"><strong>${postOne.regDate}</strong></div>
						<div id="postNick">${blogVo.userName}(${blogVo.id})님</div>
						<input id="postNo" type="hidden" name="postNo" value="${postOne.postNo}">
				</div>
				<!-- //postBox -->
			
				<div id="post" >
					${postOne.postContent}
				</div>
				
				<!-- //post -->
				
				<!-- 글이 없는 경우 -->
				<!-- 
				<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
							<div id="postDate" class="text-left"><strong></strong></div>
							<div id="postNick"></div>
				</div>
			    
				<div id="post" >
				</div>
				-->
				
				
				<!--코멘트 영역 -->
				<div id="admin-comments" >
					
					<table id="comments">
					<colgroup>
						<col style="width: 100px;">
						<col style="width: 100px;">
						<col style="width: 50px;">
						<col>
						<col style="width: 100px;">
					</colgroup>					
					<thead>
					
					<c:if test="${!(empty authUser)}">
						<tr>
							<th>${authUser.userName}</th>							
							<td><input id="commentTitle" type="text" name="comments" value=""></td>
							<td><button class="btn_comments" type="submit">저장</button></td>
							<input id="userNo" type="hidden" name="userNo" value="${authUser.userNo}">
						</tr>
					</c:if>	
					
					</thead>
					
					<tbody id="commentList">
						<!-- 리스트 영역 -->
					</tbody>
					
				</table>
				
				</div>
				
				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>${postList[0].cateName}</strong></div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						<c:forEach items="${postList}" var="PostVo" varStatus="status" >
						<tr>
							<td class="text-left"><a href="${pageContext.request.contextPath}/${blogVo.id}?cate=${PostVo.cateNo}&post=${PostVo.postNo}">${PostVo.postTitle}</a></td>
							<td class="text-right">${PostVo.regDate}</td>
						</tr>						
						</c:forEach>	
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
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
		

	$(".btn_comments").on("click", function(e) {
		e.preventDefault(); 
		console.log("코멘트 등록버튼 클릭");			
		
		let commentsVo = {
				postNo: $("#postNo").val(),
				userNo: $("#userNo").val(),
				userName: $('#comments th').html(),
				cmtContent: $("#commentTitle").val()
			}
			console.log(commentsVo);		
			
			$.ajax({
				url : "${pageContext.request.contextPath}/${blogVo.id}/admin/commentInsert",
				type : "post",
				contentType : "application/json",
				data : JSON.stringify(commentsVo),
				
				dataType : "json",
				success : function(result) {
					console.log(result);
					
					//그리기
					document.location.reload(true);
					
					//초기화
					$("#commentTitle").val("");
					
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}				
			});
				
	});


	function fetchList(){	
		
		// 데이터 수집
		let post = $("#postNo").val();
		console.log(post);
		
		//서버로 부터 방명록 데이타만 받고 싶다 ajax요청
		$.ajax({
			url : "${pageContext.request.contextPath}/${blogVo.id}/admin/commentlist",
			type : "post",
			data: { postNo: post },
			
			dataType: "json",
			success : function(commentlist){
				/*성공시 처리해야될 코드 작성*/
				//리스트받기
				console.log(commentlist);
				
				for(let i=0; i<commentlist.length; i++){
					render(commentlist[i], "up"); //그리기	
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	}
		
		
	//코멘트 내용을 1개씩 그린다
	// if문을 str안에 넣으면 안에서 이미 비교해버리기때문에 그려지지가 않음
	// if문을 여기서 바로 작동해야함
	function render(commentsVo, dir){
		
		// 로그인하지 않을시 userNo 존재 자체가 뜨지 않기 때문에 앞에 0을 넣어준다.
		let userNo = 0${authUser.userNo};
		
		let str ='';
		str +=' <tr id=t'+commentsVo.cmtNo +'> ';
		str +='		<td>' + commentsVo.userName + '</td> ';
		str +='		<td>' + commentsVo.cmtContent + '</td> ';
		str +='		<td>' + commentsVo.regDate + '</td> ';		
		// 비로그인시 0과 비교하게 되며 로그인시 0이 앞에 붙은 userNo로 작동하기 때문에 정수끼리 비교가 가능하다
		if (userNo == commentsVo.userNo) {
		str +='		<td class="text-center"><img class="btnCmtDel" data-no='+ commentsVo.cmtNo +' src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td> ';
		}		
		str +=' </tr> ';		
		
		if(dir=="up"){
			$("#commentList").prepend(str);
			
		}else if(dir=="down"){
			$("#commentList").append(str);	
		
		}else {
			consoel.log("잘못입력")
		}
		
		
	}
	
	//삭제버튼 눌렀을때
	$("#commentList").on("click", ".btnCmtDel", function(){
		console.log("삭제버튼 클릭");
		
		let $this = $(this);		
		console.log($this);
		
		let cmtNo = $this.data("no");		
		console.log(cmtNo);
		
		$.ajax({
			url : "${pageContext.request.contextPath}/${blogVo.id}/admin/commentDelete",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(cmtNo),
			
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
				
	});


</script>


</html>