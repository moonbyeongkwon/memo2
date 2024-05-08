<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="w-50">
	<h1>회원가입</h1>
	
	<form id="signUpForm" method="post" action="/user/sign-up">
		<table class="sign-up-table table table-bordered">
			<tr>
				<th>아이디(5자 이상)<br></th>
				<td>
					<div class="d-flex">
						<input type="text" id="loginId" name="loginId"
						class="form-control col-10" placeholder="아이디를 입력하세요.">
						<button type="button" id="loginIdCheckBtn" class="btn btn-success">중복확인</button>
						<br>
					</div>
					<div id="idCheckLength" class="small text-danger d-none">
						ID를 5자 이상 입력하세요.
					</div>
					<div id="idCheckDuplicated" class="small text-danger d-none">
						이미 사용중인 ID입니다.
					</div>
					<div id="inCheckOk" class="smaal text-success d-none">
						사용 가능한 ID입니다.
					</div>
				</td>
			</tr>
			<tr>
				<th>* 비밀번호</th>
				<td><input type="password" id="password" name="password"
					class="form-control" placeholder="비밀번호를 입력하세요."></td>
			</tr>
			<tr>
				<th>* 비밀번호 확인</th>
				<td><input type="password" id="confirmPassword"
					class="form-control" placeholder="비밀번호를 다시 입력하세요."></td>
			</tr>
			<tr>
				<th>* 이름</th>
				<td><input type="text" id="name" name="name"
					class="form-control" placeholder="이름을 입력하세요."></td>
			</tr>
			<tr>
				<th>* 이메일</th>
				<td><input type="text" id="email" name="email"
					class="form-control" placeholder="이메일을 입력하세요."></td>
			</tr>
		</table>
		<br>
		
		<button type="submit" id="signUpBtn"
			class="btn btn-primary float-rignt">회원가입</button>
	</form>
</div>

<script>
	$(document).ready(function() {
		
		//	아이디 중복
		$("#loginIdCheckBtn").on('click', function() {
			//	경고 문구 초기화
			$("#idCheckLength").addClass("d-none");
			$("#idCheckDuplicated").addClass("d-none");
			$("#idCheckOk").addClass("d-none");
			
			let loginId = $("input[name=loginId]").val().trim();
			if (loginId.length < 5) {
				$("#idCheckLength").removeClass("d-none");
				return;
			}
			
			$.get("/user/is-duplicated-id", {loginId:loginId})
			.done(function(data) {
				if (data.code == 200) {
					if (data.is_duplicated_id) {
						$("#idCheckDuplicated").removeClass("d-none")
					} else {
						$("#idCheckOk").removeClass("d-none");
					}
				} else {
					alert(data.error_message);
				}
			});
		});
		
		//	회원가입
		$("#signUpForm").on('submit', function(e) {
			e.preventDefault();
			
			let loginId = $("input[name=loginId]").val().trim();
			let password = $("#password").val();
			let confirmPassword = $("#confirmPassword").val();
			let name = $("#name").val().trim();
			let email = $("#email").val().trim();
			
			if (!loginId) {
				alert("아이디를 입력하세요.");
				return false;
			}
			if (!password || !confirmPassword) {
				alert("비밀번호를 입력하세요.");
				return false;
			}
			if (password != confirmPassword) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			if (!name) {
				alert("이름을 입력하세요.");
				return false;
			}
			if (!email) {
				alert("이메일을 입력하세요.");
				return false;
			}
			
			//	사용 가능한 아이디인지
			if ($("#idCheckOk").hasClass("d-none")) {
				alert("아이디 중복확인을 해주세요.");
				return false;
			}
			
			let url = $(this).attr("action");
			console.log(url);
			let params = $(this).serialize();
			console.log(params);
			
			$.post(url, params)
			.done(function(data) {
				if (data.code == 200) {
					alert("가입을 환영합니다. 로그인 해주세요.");
					location.href = "/user/sign-in-view";
				} else {
					alert(data.error_message);
				}
			});
			
		});
	});
</script>