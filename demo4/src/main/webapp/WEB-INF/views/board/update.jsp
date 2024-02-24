<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-3">  	
	  	<h3>${board.user.username}의 글</h3>
	  	<div class="mb-3 mt-3">
		<label for="num">Num:</label> <input type="text" class="form-control" name="num"
				id="num" value="${board.num}" readonly="readonly">
		</div>
	  	<div class="mb-3 mt-3">
	      <label for="title">Title:</label>
	      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title"
	      value="${board.title}">
	    </div>
	    <div class="mb-3">
	      <label for="writer">Writer:</label>
	      <input type="writer" class="form-control" id="writer" placeholder="Enter writer" name="writer"
	      value="${board.user.username} readonly="readonly">
	    </div>
	    <div class="mb-3 mt-3">
	      <label for="cotent">Content:</label>
	      <textarea class="form-control" rows="5" id="content" name="content">${board.content}</textarea>
	    </div>
	    <button type="button" id="btnModify">수정하기</button>
</body>
</html>