<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-50">
	<h1>게시글</h1>
	<div class="post-info">
		글쓴이: ${postView.user.name}
	</div>
	<hr>
	<c:if test="${userId == post.userId}">
	<input type="text" id="subject" class="form-control" placeholder="제목을 입력하세요." value="${post.subject}">
	<textarea id="content" class="form-control" placeholder="내용을 입력하세요." rows="10">${post.content}</textarea>
	</c:if>
	
	<c:if test="${userId != post.userId}">
	<span>${post.subject}</span> <br><hr>
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
	});
</script>