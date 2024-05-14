<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="h-100 mx-4 d-flex justify-content-between align-items-center">
	<%-- logo --%>
	<div>
		<h1 class="font-weight-bold">
			<c:if test="${not empty userId}">
				<a href="/post/post-list-view">게시판</a>
			</c:if>
			<c:if test="${empty userId}">
				<a href="/user/sign-in-view">게시판</a>
			</c:if>
		</h1>
	</div>
	
	<%-- 로그인 정보 --%>
	<div>
		<%-- 로그인 시 --%>
		<c:if test="${not empty userId}">
			<span>${userName}님 안녕하세요.</span>
			<a href="/user/sign-out">로그아웃</a>
		</c:if>
		
		<%-- 비로그인 시 --%>
		<c:if test="${empty userId}">
			<a href="/user/sign-in-view">로그인</a>
		</c:if>
	</div>
</div>