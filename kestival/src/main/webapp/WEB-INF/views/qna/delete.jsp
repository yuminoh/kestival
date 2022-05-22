<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="qnano" value="${qnaVO.qnano} "/>
<c:set var= "memberno" value= "${qnaVO.memberno}"/>
<c:set var="qwriter" value="${qnaVO.qwriter} "/>
<c:set var="qtitle" value="${qnaVO.qtitle} "/>
<c:set var="qcontext" value="${qnaVO.qcontext} "/>
<c:set var="qdate" value="${qnaVO.qdate} "/>

<html>
<head>
<jsp:include page="../header.jsp" flush='false'/>
</head>
<body>
<form name="frm" method="post" action='/qna/delete.do?qnano=${qnano}' novalidate>
    <div class="container">
    <label for="basic-url" class="form-label">정말 삭제하시겠습니까? </label><br>
    <div class="input-group mb-3">
  
  <span class="input-group-text" id="basic-addon1">작성자: ${qwriter} </span>
  
</div>

<label for="basic-url" class="form-label">제목: ${qtitle} </label><br>

<label for="basic-url" class="form-label">내용: </label>
<div class="input-group">
  <label for="basic-url" class="form-label"> ${qcontext} </label>
  
</div>
<button type="submit" class="btn btn-outline-secondary">delete</button>
     </div>
     
<jsp:include page="../footer.jsp" flush='false'/>
</body>
</html>