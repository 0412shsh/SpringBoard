<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">😉서하's 게시판😉</h3>
	</div>

	<!-- action="" 생략시 다시 자신의 주소를 호출  -->
	<form role="form" action="/board/regist" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">🌼제 목🌼</label>
				
				<input type="text" class="form-control" id="exampleInputEmail1"
					placeholder="제목을 입력하세요." name="title">
			</div>
			
			<div class="form-group">
				<label for="exampleInputEmail1">🌼작성자🌼</label>
				
				<input type="text" class="form-control" id="exampleInputEmail1"
					placeholder="이름을 입력하세요." name="writer">
			</div>
			
			<div class="form-group">
				<label>🌼내용🌼</label>
				<textarea class="form-control" rows="3" placeholder="내용을 입력하세요." name="content"></textarea>
			</div>
		
			
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-primary">글쓰기</button>
		</div>
	</form>
</div>

<%@ include file="../include/footer.jsp"%>

