<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">πμν's κ²μνπ</h3>
	</div>

	<!-- action="" μλ΅μ λ€μ μμ μ μ£Όμλ₯Ό νΈμΆ  -->
	<form role="form" action="/board/regist" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">πΌμ  λͺ©πΌ</label>
				
				<input type="text" class="form-control" id="exampleInputEmail1"
					placeholder="μ λͺ©μ μλ ₯νμΈμ." name="title">
			</div>
			
			<div class="form-group">
				<label for="exampleInputEmail1">πΌμμ±μπΌ</label>
				
				<input type="text" class="form-control" id="exampleInputEmail1"
					placeholder="μ΄λ¦μ μλ ₯νμΈμ." name="writer">
			</div>
			
			<div class="form-group">
				<label>πΌλ΄μ©πΌ</label>
				<textarea class="form-control" rows="3" placeholder="λ΄μ©μ μλ ₯νμΈμ." name="content"></textarea>
			</div>
		
			
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-primary">κΈμ°κΈ°</button>
		</div>
	</form>
</div>

<%@ include file="../include/footer.jsp"%>

