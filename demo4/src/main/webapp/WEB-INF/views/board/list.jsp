<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-3">
  <h2>BoardList(${count})</h2>       
  <table class="table table-hover">
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${boards.content}" var="board" >
      	<tr>
      		<td>${board.num}</td>
      		
      		<td><a href="view/${board.num}">${board.title}</a></td>
      		<td>${board.user.username}</td>
      		<td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd"/></td>
      		<td>${board.hitcount}</td>
      	</tr>
      </c:forEach>
    </tbody>
  </table>
  <div class="d-flex justify-content-between">
	  <ul class="pagination">
		  <c:if test="${boards.first == false}">
		  	<li class="page-item"><a class="page-link" 
		    href="?Page=${boards.number-1}">Previous</a></li>
		  </c:if>
		  <c:if test="${boards.last == false}">
		    <li class="page-item"><a class="page-link" href="?Page=${boards.number+1}">Next</a></li>
		  </c:if>
	  </ul>
	  
	  <form class="d-flex justify-content-between" action="list" method="get">
	   <select class="form-select form-select-sm mt-3" name="field">
	     <option value="content">내용</option>
	     <option value="title">제목</option>
	   </select>
	   <input type="text" class="form-control" id="word" placeholder="Enter word" name="word">
	   <button class="btn btn-secondary btn-sm">Search</button>
	  </form>
  </div>

</div>
</body>
</html>