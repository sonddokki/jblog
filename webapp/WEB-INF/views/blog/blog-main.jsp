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
				
				<c:if test="${!(empty authUser)}">
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
						<tr>
							<th>${authUser.userName}</th>							
							<td><input id="commentTitle" type="text" name="comments" value=""></td>
							<td><button class="btn_comments" type="submit">저장</button></td>
							<input id="userNo" type="hidden" name="userNo" value="${authUser.userNo}">
						</tr>
					</thead>
					
					<tbody id="commentList">
						<!-- 리스트 영역 -->
					</tbody>
					
				</table>
				
				</div>
				</c:if>
				
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

	$(".btn_comments").on("click", function(e) {
		e.preventDefault(); 
		console.log("코멘트 등록버튼 클릭");	
		
		// 데이터 수집
		let userNo = $("#userNo").val();
		let commentUser = $('#comments th').html()
		let postNo = $("#postNo").val();
		let commentTitle = $("#commentTitle").val();
		
		
		console.log(commentUser);	
		console.log(postNo);
		console.log(commentTitle);
		
		let commentsVo = {
				postNo: $("#postNo").val(),
				userNo: $("#userNo").val(),
				userName: $('#comments th').html(),
				cmtContent: $("#commentTitle").val()
			}
			console.log(commentsVo);		
			
			$.ajax({
				url : "${pageContext.request.contextPath}/${blogVo.id}/admin/CommentInsert",
				type : "post",
				contentType : "application/json",
				data : JSON.stringify(commentsVo),
				
				dataType : "json",
				success : function(result) {
					console.log(result);
					
					//그리기
					
					
					//초기화
					$("#commentTitle").val("");
					
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}				
			});
				
	});





</script>


</html>