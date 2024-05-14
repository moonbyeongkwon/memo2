<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-50">
	<h1>게시글</h1>
	
	<input type="text" id="subject" class="form-control" placeholder="제목을 입력하세요." value="${post.subject}">
	<textarea id="content" class="form-control" placeholder="내용을 입력하세요." rows="10">${post.content}</textarea>
	
	<%-- 이미지가 있을 때 영역 노출 --%>
	<c:if test="${not empty post.imagePath}">
	<div class="my-3">
		<img src="${post.imagePath}" alt="업로드 된 이미지" width="300">
	</div>
	</c:if>
	
	<div class="d-flex justify-content-end my-3">
		<input type="file" id="file" accept=".jpg, .png, .gif">
	</div>
</div>