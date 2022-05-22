<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="cateno" value="${cateVO.cateno} "/>
<c:set var= "adminno" value= "${cateVO.adminno}"/>
<c:set var="name" value="${cateVO.name} "/>
<c:set var="cdate" value="${cateVO.cdate} "/>

<html>
<head>
<jsp:include page="../header.jsp" flush='false'/>
</head>
<body>
<form name="frm" method="post" action='/cate/delete.do?cateno=${cateno}' novalidate>
    <div class="container">
    <br>
    <label for="basic-url" class="form-label">정말 삭제하시겠습니까? </label><br>
    <div class="input-group mb-3">
  
</div>

<label for="basic-url" class="form-label">카테고리 이름: ${name} </label><br>

<button type="submit" class="btn btn-outline-secondary">delete</button>
     </div>
     
<jsp:include page="../footer.jsp" flush='false'/>
</body>
</html>