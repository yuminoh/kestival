<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <jsp:include page="../header.jsp" flush='false'/>
</head>
<body>
<div class="container">


    
 </nav>
<table class="table" height="350px">
  <thead>
    <tr>
      <th scope="col" width="100">번호</th>
      <th scope="col" width="500">제목</th>
      <th scope="col" width="100">작성자</th>
      <th scope="col" width="250" text-align="right">작성일</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="qnaVO" items="${list}">
        <c:set var="qnano" value="${qnaVO.qnano}"/>
        <tr>
            <td scope="row" width="100">${qnaVO.qnano} </td>
            <td scope="row" width="500"><a href="./read.do?qnano=${qnaVO.qnano}">${qnaVO.qtitle}</a> </td>
            <td scope="row" width="100">${qnaVO.qwriter} </td>
            <td scope="row" width="250" text-align="right">${qnaVO.qdate} </td>
        </tr>
    </c:forEach>
  </tbody>
</table>
<nav class="navbar navbar-light bg-light">
<button type="button" class="btn btn-outline-secondary" ><a href="/qna/insert.do" class='nav-link link-dark px-2'>add</a></button>
 
  
  
<jsp:include page="../footer.jsp" flush='false'/>
</div>
</body>
</html>