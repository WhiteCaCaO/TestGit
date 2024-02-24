<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ include file="../include/header.jsp" %>
<div class="container mt-3">  	
  	<h3>${board.user.username }의 글 보기</h3>
  	<div class="mb-3 mt-3">
		<label for="num">Num:</label> <input type="text" class="form-control"
			id="num" value="${board.num }" readonly="readonly">
	</div>
  	<div class="mb-3 mt-3">
      <label for="title">Title:</label>
      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title"
      value="${board.title}">
    </div>
    <div class="mb-3">
      <label for="writer">Writer:</label>
      <input type="text" class="form-control" id="writer" placeholder="Enter writer" name="writer"
      value="${board.user.username}" readonly="readonly">
    </div>
    <div class="mb-3 mt-3">
      <label for="cotent">Content:</label>
      <textarea class="form-control" rows="5" id="content" name="content">${board.content}</textarea>
    </div>
    
	<c:if test="${principal.user.username == board.user.username}">
	<div class="text-end">
	  <button type="button" class="btn btn-secondary btn-sm" id="btnUpdate">수정</button>
	  <button type="button" class="btn btn-danger btn-sm" id="btnDelete">삭제</button>
	</div>
    </c:if>
    
    <div class="mb-3 mt-3">
      <label for="msg">Comment:</label>
      <textarea class="form-control" rows="3" id="msg" name="msg"></textarea>
    </div>
    <button class="btn btn-success btn-sm" id="commentBtn" >댓글 등록</button>
	
	<div id="replyResult"></div>
</div>    
<script>
	$("#btnDelete").click(function(){
		if(!confirm("정말 삭제할까요?")){
 			return false;
 		}
		$.ajax({
			type: "delete",
			url: "/board/delete/${board.num}"
		})
		.done(function(resp){
			alert(resp +"번 삭제성공");
			location.href = "/board/list";
		})
		.fail(function(e){
			alert("삭제 실패"+ e);
		})
	})




	// 전체댓글
	var init = function(){
		$.ajax({
			type: "get",
			url: "/app08/reply/commentList/${board.num}"
		})
		.done(function(resp){
			/* let str = "댓글갯수("+ resp.replyCnt + ")<br>" */
			let str = "";
			$.each(resp, function(key,val){
				str += val.userid +" "
				str += val.content +" "
				str += val.regdate +" "
				if($("#prin").val() == val.userid){
					str += "<a href='javascript:fdel("+val.cnum+")'>삭제</a>"
				}
				str += "<br/>"
			});
							
			$("#replyResult").html(str);
		})
		.fail(function(){
			alert("실패");
		})
					
	}
	// 댓글 추가
	$("#commentBtn").click(function(){
		if($("#prin").val() == "null"){
			alert("로그인하세요");
			location.href = "/app08/customLogin";
			return;
		}
		
		if($("#msg").val() == ""){
			alert("댓글을 입력하세요");
			return;
		}
		
		$.ajax({
			type: "POST",
			url: "/app08/reply/commentInsert",
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify({
				"bnum": $("#num").val(),
				"content": $("#msg").val(),
				/* "userid": $("#prin").val() */
			})
		})
		.done(function(resp){
			alert("댓글 쓰기 성공");
			init();
		})
		.fail(function(e){
			alert("댓글 쓰기 실패" + e);
		})
	})
	
	// 댓글 삭제
	function fdel(cnum){
		$.ajax({
			type: "delete",
			url: "/app08/reply/commentDelete/" + cnum
		})
		.done(function(resp){
			alert(resp +"번 삭제성공");
			init();
		})
		.fail(function(e){
			alert("삭제 실패"+ e);
		})
	}
/* init(); */
</script>  

</body>
</html>

