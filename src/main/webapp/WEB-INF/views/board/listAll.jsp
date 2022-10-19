<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp"%>
<h1>board/listAll.jsp</h1>

<%-- ${boardList } --%>
<div class="box">
	<div class="box-header with-border">
		<h3 class="box-title">❤서하스 게시판❤</h3>
		${msg }
	</div>

	<div class="box-body">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th style="width: 45px">번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th style="width: 60px">조회수</th>
				</tr>
				
				<c:forEach var="vo" items="${boardList }">
				<tr>
					<td><span class="badge bg-orange">${vo.bno }</span></td>
					<td>${vo.title }</td>
					<td>${vo.writer }</td>
					<td>
					<fmt:formatDate pattern="yy-MM-dd HH:mm" value="${vo.regdate }"/>
					</td>
					<td><span class="badge bg-green">${vo.viewcnt }</span></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="box-footer clearfix">
		<ul class="pagination pagination-sm no-margin pull-right">
			<li><a href="#">«</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">»</a></li>
		</ul>
	</div>
</div>

	<script type="text/javascript">
	
	//alert(${msg});
	var result = "${msg}";
	
	if(result == "OK"){
		alert('글 쓰기 완료!');
	}
	
	</script>



<%@ include file="../include/footer.jsp"%>



