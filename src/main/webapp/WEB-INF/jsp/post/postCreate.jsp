<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="w-50">
	<h1>글쓰기</h1>
	
	<input type="text" id="subject" class="form-control" placeholder="제목을 입력하세요.">
	<textarea id="content" class="form-control" placeholder="내용을 입력하세요." rows="10"></textarea>
	
	<div class="d-flex justify-content-end my-3">
		<input type="file" id="file" accept=".jpg, .png, .gif">
	</div>
	
	<div>
		<button type="button" id="postListBtn" class="btn btn-dark">목록</button>
		<div>
			<button type="button" id="cleanBtn" class="btn btn-secondary my-3">모두 지우기</button>
			<button type="button" id="saveBtn" class="btn btn-success mx-3">저장</button>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		//	목록
		$("#postListBtn").on('click', function() {
			location.href="/post/post-list-view";
		});
		
		//	모두 지우기
		$("#cleanBtn").on('click', function() {
			$("#subject").val("");
			$("#content").val("");
		});
		
		//	저장
		$("#saveBtn").on('click', function() {
			let subject = $("#subject").val();
			let content = $("#content").val();
			let fileName = $("#file").val();
			console.log(fileName);
			
			//	validation
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
					alert("이미지 파일만 업로드 할 수 있습니다.");
					$("#file").val("");
					return;
				}
			}
			let formData = new FormData();
			formData.append("subject", subject);
			formData.append("content", content);
			formData.append("file", $("#file")[0].files[0]);
			
			//	AJAX
			$.ajax({
				//	request
				type:"POST"
				, url:"/post/create"
				, data:formData
				, enctype:"multipart/form-data"	//	이하 파일 업로드 필수 조건
				, processData:false
				, contentType:false
				
				//	response
				, success:function(data) {
					if (data.code == 200) {
						alert("게시물이 저장되었습니다.");
						location.href = "/post/post-list-view";
					} else {
						alert(data.error_message);
					}
				}
				, error:function(e) {
					alert("게시물을 저장하는데 실패했습니다.");
				}
				
			});
		});
	});
</script>