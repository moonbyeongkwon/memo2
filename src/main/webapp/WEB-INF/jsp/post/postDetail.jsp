<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="w-50">
	<h1>게시글</h1>
	<div class="post-subject">
		<c:if test="${userId == post.userId}">
		<input type="text" id="subject" class="form-control" placeholder="제목을 입력하세요." value="${post.subject}">
		</c:if>
		<c:if test="${userId != post.userId}">
		<span>${post.subject}</span> <br><hr>
	</c:if>
	</div>
	<div class="post-info">
		<b>${postView.user.name}(${postView.user.loginId})</b><br>
		작성일: <fmt:formatDate value="${post.createdAt}" pattern="yyyy년 M월 d일 HH:mm:ss" />
	</div>
	<hr>
	<c:if test="${userId == post.userId}">
	<textarea id="content" class="form-control" placeholder="내용을 입력하세요." rows="10">${post.content}</textarea>
	</c:if>
	
	<c:if test="${userId != post.userId}">
	<span>${post.content}</span>
	</c:if>
	
	<%-- 이미지가 있을 때 영역 노출 --%>
	<c:if test="${not empty post.imagePath}">
	<div class="my-3">
		<img src="${post.imagePath}" alt="업로드 된 이미지" width="300">
	</div>
	</c:if>
	
	<c:if test="${userId == post.userId}">
	<div class="d-flex justify-content-end my-3">
		<input type="file" id="file" accept=".jpg, .png, .gif">
	</div>
	
	<%-- 추천 --%>
	
	<div class="d-flex justify=content-between">
		<button type="button" id="delPostBtn" class="btn btn-secondary mr-4" data-post-id="${post.id}">삭제</button>
		
		<div>
			<a href="/post/post-list-view" class="btn btn-dark">목록</a>
			<button type="button" id="saveBtn" class="btn btn-warning ml-4" data-post-id="${post.id}">수정</button>
		</div>
	</div>
	</c:if>
	<br>
	<c:if test="${userId != post.userId}">
	<a href="/post/post-list-view" class="btn btn-dark">목록</a>
	</c:if>
</div>
<div>
<%-- 댓글 목록 --%>
<div class="comment-list m-2">
	<%-- 댓글 내용들 --%>
	<c:forEach items="${commentViewList}" var="commentView">
	<div class="comments m-1">
		<span class="font-weight-bold">${commentView.user.name}</span>
		<span>${commentView.comment.content}</span>
		
		<%-- 댓글 삭제 버튼(자신의 댓글만 노출) --%>
		<c:if test="${userId eq commentView.comment.userId}">
		<a href="#" class="comment-del-btn" data-comment-id="${commentView.comment.id}">
			<img src="https://media.istockphoto.com/id/1298957635/ko/%EB%B2%A1%ED%84%B0/%EA%B0%80%EB%B9%84%EC%A7%80-%EB%B9%88-%EC%84%A0-%EB%B2%A1%ED%84%B0-%EC%95%84%EC%9D%B4%EC%BD%98%EC%9E%85%EB%8B%88%EB%8B%A4-%ED%8E%B8%EC%A7%91-%EA%B0%80%EB%8A%A5%ED%95%9C-%EC%8A%A4%ED%8A%B8%EB%A1%9C%ED%81%AC-%ED%94%BD%EC%85%80-%EC%99%84%EB%B2%BD%ED%95%9C-%EB%AA%A8%EB%B0%94%EC%9D%BC-%EB%B0%8F-%EC%9B%B9%EC%9A%A9.jpg?s=2048x2048&w=is&k=20&c=jsKvNn0xzLnB6Cxgzvi8JNhp_vT-TRrmfP3jtfZ6N8Y="
			width="30" height="30">
		</a>
		</c:if>
	</div>
	</c:forEach>
</div>

	<%-- 댓글 쓰기 --%>
	<div class="comment-wirte d-flex border-top mt-2">
		<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글을 입력하세요.">
		<button type="button" class="comment-btn btn btn-light" data-post-id="${post.id}" data-user-id="${userId}">댓글 등록</button>
		
	</div>
</div>

<script>
	$(document).ready(function() {
		//	수정
		$("#saveBtn").on('click', function() {
			let postId = $(this).data("post-id");
			let subject = $("#subject").val().trim();
			let content = $("#content").val().trim();
			let fileName = $("#file").val();
			
			if (!subject) {
				alert("제목을 입력하세요.");
				return;
			}
			
			if (!content) {
				alert("내용을 입력하세요.");
				return;
			}
			
			if (fileName) {
				let extension = fileName.split(".").pop().toLowerCase();
				
				if ($.inArray(extension, ['jpg', 'png', 'gif']) == -1) {
					$("#file").val("");
					return;
				}
			}
			
			let formData = new FormData();
			formData.append("postId", postId);
			formData.append("subject", subject);
			formData.append("content", content);
			formData.append("file", $("#file")[0].files[0]);
			
			$.ajax({
				//	request
				type:"PUT"
				, url:"/post/update"
				, data:formData
				, enctype:"multipart/form-data"
				, processData:false
				, contentType:false
				
				//	response
				, success:function(data) {
					if (data.code == 200) {
						alert("게시물이 수정되었습니다.");
						location.reload(true);
					} else {
						alert(data.error_message);
					}
				}
				, error:function(e) {
					alert("게시물을 수정하는데 실패했습니다.");
				}			
			});
		});
		
		$("#delPostBtn").on('click', function() {
			let postId = $(this).data('post-id');
			
			$.ajax({
				type:"DELETE"
				, url:"/post/delete"
				, data:{"postId":postId}
				, success:function(data) {
					if (data.result == "성공") {
						alert("삭제되었습니다.");
						location.href = "/post/post-list-view";
					} else {
						alert(data.error_message);
					}
				}
				, error:function(e) {
					alert("게시물을 삭제하는데 실패했습니다.");
				}
			});
		});
		
		//	댓글 쓰기
		$(".comment-btn").on('click', function() {
			//	로그인 여부
			let userId = $(this).data("user-id");
			if (!userId) {
				alert("로그인을 해주세요.");
				location.href = "/user/sign-in-view";
				return;
			}
			
			//	댓글이 쓰여질 글번호 가져오기
			let postId = $(this).data("post-id");
			
			let content = $(this).siblings("input").val().trim();
			if (!content) {
				alert("댓글 내용을 입력하세요.");
				return;
			}
			
			$.ajax({
				//	request
				type:"POST"
				, url:"/comment/create"
				, data:{"postId":postId, "content":content}
			
				//	response
				, success:function(data) {
					if (data.code == 200) {
						location.reload(true);
					} else if (data.code == 500) {
						alert(data.error_message);
						location.href = "/user/sign-in-view";
					}
				}
				, error:function(e) {
					alert("댓글 작성에 실패했습니다");
				}
			});
			
		});
		
		//	댓글 삭제
		$(".comment-del-btn").on('click', function(e) {
			e.preventDefault();
			let commentId = $(this).data("comment-id");
			
			$.ajax({
				//	request
				type:"DELETE"
				, url:"/comment/delete"
				, data:{"commentId":commentId}
			
				//	response
				, success:function(data) {
					if (data.code == 200) {
						location.reload(true);
					} else {
						alert(data.error_message);
					}
				}
				, error:function(e) {
					alert("댓글을 삭제하는데 실패했습니다.");
				}
			});
		});
	});
</script>